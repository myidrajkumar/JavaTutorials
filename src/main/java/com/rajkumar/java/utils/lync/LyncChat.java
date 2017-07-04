package com.rajkumar.java.utils.lync;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


public class LyncChat implements TargetDomain {

    private boolean needsTobeSkipped = false;

	private String lyncDomain = "https://lyncdiscover.nagra.com/";

	private String yourUserURL = null;
	private String oathTokenStr = null;
	private String xFrameStr = null;
	private String accessToken = null;
	private String applicationURL = null;
	private LyncChatJSON lyncChatJSON = new LyncChatJSON();
	private boolean isSpecficPerson = false;
	

	private String contactSaveDir = "C:/MyImage/";
	
	private Map<Integer,PresenceStatus> presenceStatusMap = new HashMap<>();
	Scanner scanner = new Scanner(new InputStreamReader(System.in));
	
	public LyncChat() {
		
		initializePresenceStatus();
		
	}

	private void initializePresenceStatus() {
		
		PresenceStatus presenceStatus1 = new PresenceStatus();
		presenceStatus1.setPresenceCode(1);
		presenceStatus1.setUserDisplayStr("Away");
		presenceStatus1.setActualStr("Away");
		
		PresenceStatus presenceStatus2 = new PresenceStatus();
		presenceStatus2.setPresenceCode(2);
		presenceStatus2.setUserDisplayStr("Be Right Back");
		presenceStatus2.setActualStr("BeRightBack");
		
		PresenceStatus presenceStatus3 = new PresenceStatus();
		presenceStatus3.setPresenceCode(3);
		presenceStatus3.setUserDisplayStr("Busy");
		presenceStatus3.setActualStr("Busy");
		
		PresenceStatus presenceStatus4 = new PresenceStatus();
		presenceStatus4.setPresenceCode(4);
		presenceStatus4.setUserDisplayStr( "Do Not Disturb");
		presenceStatus4.setActualStr("DoNotDisturb");
		
		PresenceStatus presenceStatus5 = new PresenceStatus();
		presenceStatus5.setPresenceCode(5);
		presenceStatus5.setUserDisplayStr( "Idle But Busy");
		presenceStatus5.setActualStr("IdleBusy");
		
		PresenceStatus presenceStatus6 = new PresenceStatus();
		presenceStatus6.setPresenceCode(6);
		presenceStatus6.setUserDisplayStr("Idle But Online");
		presenceStatus6.setActualStr("IdleOnline");
		
		PresenceStatus presenceStatus7 = new PresenceStatus();
		presenceStatus7.setPresenceCode(7);
		presenceStatus7.setUserDisplayStr( "Offline");
		presenceStatus7.setActualStr("Offline");
		
		PresenceStatus presenceStatus8 = new PresenceStatus();
		presenceStatus8.setPresenceCode(8);
		presenceStatus8.setUserDisplayStr( "Available");
		presenceStatus8.setActualStr("Available");
		
		presenceStatusMap.put(1,presenceStatus1);
		presenceStatusMap.put(2,presenceStatus2);
		presenceStatusMap.put(3,presenceStatus3);
		presenceStatusMap.put(4,presenceStatus4);
		presenceStatusMap.put(5,presenceStatus5);
		presenceStatusMap.put(6,presenceStatus6);
		presenceStatusMap.put(7,presenceStatus7);
		presenceStatusMap.put(8,presenceStatus8);
		
	}

	public void initiateLyncChat() throws IOException {

		// Connect to Lync Domain
		connectToLyncDomain();

		// Connect to your Lync user URL
		connectToYourLyncUserURL();

		// Connect and Get access token
		getAccessToken();

		// Connect and Get Lync applications URL
		getLyncApplicationURL();

		// Connect and Get permissible URLs
		getPermissibleURLs();

		// Update the lync request
		if (!needsTobeSkipped) {
			updateMyLyncRequestParams();
		}

		// Getting my presence information
		getMyPresenceStatus();

		// Setting my presence information
		// setMyPresenceStatus();

		// Resetting my presence information is possible but currently i do not
		// know how to do and i am not interested

		// Getting my note
		// getMyNote();

		// Setting my note
		// setMyNote();

        // Resetting my note is possible but currently i do not know how to do
        // and i am not interested

        // Getting my photo
		 retrieveMyPhoto();

		// Get and Save Other Contact photos
		// getOtherContactsPhotos();

	}

	private void getOtherContactsPhotos() throws IOException {
		String wholeContactURL = lyncChatJSON.getEmbedded().getEmbeddedPeople().getEmbeddedPeopleLink()
				.getEmbeddedPeopleLinkSearch();
		doSearchAndGetPhoto(wholeContactURL, xFrameStr, accessToken, this.isSpecficPerson);

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) throws Exception {

		LyncChat http = new LyncChat();
		http.initiateLyncChat();

	}

	// HTTP GET request
	private void connectToLyncDomain() throws IOException {

	    System.out.println("Step 1 - Connect to Lync Domain");

		URL url = new URL(lyncDomain);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + lyncDomain);

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			final JSONObject linksObj = new JSONObject(response.toString());
			JSONObject otherObj = linksObj.getJSONObject(LyncConstants._LINKS);
			JSONObject userObj = otherObj.getJSONObject("user");
			JSONObject xframeObj = otherObj.getJSONObject("xframe");
			yourUserURL = (String) userObj.get("href");
			xFrameStr = (String) xframeObj.get("href");

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private void connectToYourLyncUserURL() throws IOException {

	    System.out.println("Step 2 - Connect to Your User URL ");

		if(yourUserURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		
		URL url = new URL(yourUserURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + yourUserURL);

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
		    System.out.println(LyncConstants.LINE_SEPERATOR);
			return;

		} else {

			String authStr = con.getHeaderField("WWW-Authenticate").split("href=")[1];
			oathTokenStr = authStr.substring(1, authStr.indexOf('\'', 1));

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private void getAccessToken() throws IOException {

	    System.out.println("Step 3 - Get Access Token ");

		if(oathTokenStr == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL obj = new URL(oathTokenStr);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + oathTokenStr);

		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, "application/x-www-form-urlencoded;charset='utf-8");
		con.setRequestMethod("POST");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);

		con.setDoOutput(true);

		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", "urn:microsoft.rtc:windows"));

		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, LyncConstants.UTF_8));
		writer.write(getQuery(parameters));
		writer.flush();
		writer.close();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK) { // success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			final JSONObject linksObj = new JSONObject(response.toString());

			accessToken = linksObj.getString("access_token");

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
		}
	}

	// HTTP GET request
	private void getLyncApplicationURL() throws IOException {

	    System.out.println("Step 4 - Get Applications URL ");

		if(yourUserURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL url = new URL(yourUserURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + yourUserURL);

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			final JSONObject linksObj = new JSONObject(response.toString());
			JSONObject otherObj = linksObj.getJSONObject(LyncConstants._LINKS);
			JSONObject applicationsObj = otherObj.getJSONObject("applications");

			applicationURL = applicationsObj.getString("href");

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private void getPermissibleURLs() throws IOException {

	    System.out.println("Step 4 - Get all our permissiable URL ");

		if(applicationURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL obj = new URL(applicationURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + applicationURL);

		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("POST");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		JSONObject postData = new JSONObject();
		postData.put("UserAgent", "UCWA Samples");
		postData.put("EndpointId", "a917c6f4-976c-4cf3-847d-cdfffa28ccdf");
		postData.put("Culture", "en-IN");

		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, LyncConstants.UTF_8));
		writer.write(postData.toString());
		writer.flush();
		writer.close();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_CREATED) { // success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			final JSONObject finalResJSONObj = new JSONObject(response.toString());

			String cultureObj = (String) finalResJSONObj.get("culture");
			lyncChatJSON.setCulture(cultureObj);

			String userObj = (String) finalResJSONObj.get("userAgent");
			lyncChatJSON.setUserAgent(userObj);

			Link link = new Link();
			JSONObject linksObj = finalResJSONObj.getJSONObject(LyncConstants._LINKS);

			JSONObject linksSelfObj = linksObj.getJSONObject("self");
			String linksSelfHref = linksSelfObj.getString("href");
			link.setLinkSelf(linksSelfHref);

			JSONObject linksPoliciesObj = linksObj.getJSONObject("policies");
			String linksPoliciesHref = linksPoliciesObj.getString("href");
			link.setLinkPolicy(linksPoliciesHref);

			JSONObject linksBatchObj = linksObj.getJSONObject("batch");
			String linksBatchHref = linksBatchObj.getString("href");
			link.setLinkBatch(linksBatchHref);

			JSONObject linksEventsObj = linksObj.getJSONObject("events");
			String linksEventsHref = linksEventsObj.getString("href");
			link.setLinkEvents(linksEventsHref);
			lyncChatJSON.setLink(link);

			Embedded embedded = new Embedded();
			JSONObject embeddedObj = finalResJSONObj.getJSONObject("_embedded");

			EmbeddedMe embeddedMe = new EmbeddedMe();

			JSONObject embeddedMeObj = embeddedObj.getJSONObject("me");
			String nameObj = embeddedMeObj.getString("name");
			embeddedMe.setEmbeddedMeName(nameObj);

			String uriObj = embeddedMeObj.getString("uri");
			embeddedMe.setEmbeddedMeURI(uriObj);

			JSONArray emailAddressesObj = embeddedMeObj.getJSONArray("emailAddresses");
			embeddedMe.setEmbeddedMeEMailAddresses((String) emailAddressesObj.get(0));

			String titleObj = embeddedMeObj.getString("title");
			embeddedMe.setEmbeddedMeTitle(titleObj);

			String departmentObj = embeddedMeObj.getString("department");
			embeddedMe.setEmbeddedMeDepartment(departmentObj);

			EmbeddedMeLink embeddedMeLink = new EmbeddedMeLink();
			JSONObject embeddedMeLinkObj = embeddedMeObj.getJSONObject(LyncConstants._LINKS);

			JSONObject embeddedMeLinkSelfObj = embeddedMeLinkObj.getJSONObject("self");
			String embeddedMeLinkSelfObjHref = embeddedMeLinkSelfObj.getString("href");
			embeddedMeLink.setEmbeddedMeLinkSelf(embeddedMeLinkSelfObjHref);

			try {
				JSONObject embeddedMeLinkMakeMeAvailableObj = embeddedMeLinkObj.getJSONObject("makeMeAvailable");
				String embeddedMeLinkMakeMeAvailableObjHref = embeddedMeLinkMakeMeAvailableObj
						.getString("href");
				embeddedMeLink.setEmbeddedMeLinkMakeMeAvailable(embeddedMeLinkMakeMeAvailableObjHref);

			} catch (Exception e) {
				needsTobeSkipped = true;
			}

			if (needsTobeSkipped) {
				JSONObject embeddedMeLinkNoteObj = embeddedMeLinkObj.getJSONObject("note");
				String embeddedMeLinkNoteHref = embeddedMeLinkNoteObj.getString("href");
				embeddedMeLink.setEmbeddedMeLinkNote(embeddedMeLinkNoteHref);

				JSONObject embeddedMeLinkPresenceObj = embeddedMeLinkObj.getJSONObject("presence");
				String embeddedMeLinkPresenceObjHref = embeddedMeLinkPresenceObj.getString("href");
				embeddedMeLink.setEmbeddedMeLinkPresence(embeddedMeLinkPresenceObjHref);

				JSONObject embeddedMeLinkLocationObj = embeddedMeLinkObj.getJSONObject("location");
				String embeddedMeLinkLocationObjHref = embeddedMeLinkLocationObj.getString("href");
				embeddedMeLink.setEmbeddedMeLinkLocation(embeddedMeLinkLocationObjHref);

				JSONObject embeddedMeLinkReportMyActivityObj = embeddedMeLinkObj.getJSONObject("reportMyActivity");
				String embeddedMeLinkReportMyActivityObjHref = embeddedMeLinkReportMyActivityObj
						.getString("href");
				embeddedMeLink.setEmbeddedMeLinkReportMyActivity(embeddedMeLinkReportMyActivityObjHref);

			}

			JSONObject embeddedMeLinkCallForwardingSettingsObj = embeddedMeLinkObj
					.getJSONObject("callForwardingSettings");
			String embeddedMeLinkCallForwardingSettingsObjHref = embeddedMeLinkCallForwardingSettingsObj
					.getString("href");
			embeddedMeLink.setEmbeddedMeLinkCallForwardingSettings(embeddedMeLinkCallForwardingSettingsObjHref);

			JSONObject embeddedMeLinkPhonesObj = embeddedMeLinkObj.getJSONObject("phones");
			String embeddedMeLinkPhonesObjHref = embeddedMeLinkPhonesObj.getString("href");
			embeddedMeLink.setEmbeddedMeLinkPhones(embeddedMeLinkPhonesObjHref);

			JSONObject embeddedMeLinkPhotoObj = embeddedMeLinkObj.getJSONObject("photo");
			String embeddedMeLinkPhotoObjHref = embeddedMeLinkPhotoObj.getString("href");
			embeddedMeLink.setEmbeddedMeLinkPhoto(embeddedMeLinkPhotoObjHref);

			embeddedMe.setEmbeddedMeLink(embeddedMeLink);

			String embeddedMeRel = embeddedMeObj.getString("rel");
			embeddedMe.setEmbeddedMeRel(embeddedMeRel);

			embedded.setEmbeddedMe(embeddedMe);

			EmbeddedPeople embeddedPeople = new EmbeddedPeople();
			JSONObject embeddedPeopleObj = embeddedObj.getJSONObject("people");

			EmbeddedPeopleLink embeddedPeopleLink = new EmbeddedPeopleLink();
			JSONObject embeddedPeopleLinkObj = embeddedPeopleObj.getJSONObject(LyncConstants._LINKS);

			JSONObject embeddedPeopleLinkSelfObj = embeddedPeopleLinkObj.getJSONObject("self");
			String embeddedPeopleLinkSelfObjHref = embeddedPeopleLinkSelfObj.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkSelf(embeddedPeopleLinkSelfObjHref);

			JSONObject embeddedPeopleLinkPresenceSubscriptionsObj = embeddedPeopleLinkObj
					.getJSONObject("presenceSubscriptions");
			String embeddedPeopleLinkPresenceSubscriptionsObjHref = embeddedPeopleLinkPresenceSubscriptionsObj
					.getString("href");
			embeddedPeopleLink
					.setEmbeddedPeopleLinkPresenceSubscriptions(embeddedPeopleLinkPresenceSubscriptionsObjHref);

			JSONObject embeddedPeopleLinkSubscribedContactsObj = embeddedPeopleLinkObj
					.getJSONObject("subscribedContacts");
			String embeddedPeopleLinkSubscribedContactsObjHref = embeddedPeopleLinkSubscribedContactsObj
					.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkSubscribedContacts(embeddedPeopleLinkSubscribedContactsObjHref);

			JSONObject embeddedPeopleLinkPresenceSubscriptionMembershipsObj = embeddedPeopleLinkObj
					.getJSONObject("presenceSubscriptionMemberships");
			String embeddedPeopleLinkPresenceSubscriptionMembershipsObjHref = embeddedPeopleLinkPresenceSubscriptionMembershipsObj
					.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkPresenceSubscriptionMemberships(
					embeddedPeopleLinkPresenceSubscriptionMembershipsObjHref);

			JSONObject embeddedPeopleLinkMyGroupsObj = embeddedPeopleLinkObj.getJSONObject("myGroups");
			String embeddedPeopleLinkMyGroupsObjHref = embeddedPeopleLinkMyGroupsObj.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkMyGroups(embeddedPeopleLinkMyGroupsObjHref);

			JSONObject embeddedPeopleLinkMyGroupMembershipsObj = embeddedPeopleLinkObj
					.getJSONObject("myGroupMemberships");
			String embeddedPeopleLinkMyGroupMembershipsObjHref = embeddedPeopleLinkMyGroupMembershipsObj
					.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkMyGroupMemberships(embeddedPeopleLinkMyGroupMembershipsObjHref);

			JSONObject embeddedPeopleLinkMyContactsObj = embeddedPeopleLinkObj.getJSONObject("myContacts");
			String embeddedPeopleLinkMyContactsObjHref = embeddedPeopleLinkMyContactsObj.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkMyContacts(embeddedPeopleLinkMyContactsObjHref);

			JSONObject embeddedPeopleLinkMyPrivacyRelationshipsObj = embeddedPeopleLinkObj
					.getJSONObject("myPrivacyRelationships");
			String embeddedPeopleLinkMyPrivacyRelationshipsObjHref = embeddedPeopleLinkMyPrivacyRelationshipsObj
					.getString("href");
			embeddedPeopleLink
					.setEmbeddedPeopleLinkMyPrivacyRelationships(embeddedPeopleLinkMyPrivacyRelationshipsObjHref);

			JSONObject embeddedPeopleLinkMyContactsAndGroupsSubscriptionObj = embeddedPeopleLinkObj
					.getJSONObject("myContactsAndGroupsSubscription");
			String embeddedPeopleLinkMyContactsAndGroupsSubscriptionObjHref = embeddedPeopleLinkMyContactsAndGroupsSubscriptionObj
					.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkMyContactsAndGroupsSubscription(
					embeddedPeopleLinkMyContactsAndGroupsSubscriptionObjHref);

			JSONObject embeddedPeopleLinkSearchObj = embeddedPeopleLinkObj.getJSONObject("search");
			String embeddedPeopleLinkSearchObjHref = embeddedPeopleLinkSearchObj.getString("href");
			embeddedPeopleLink.setEmbeddedPeopleLinkSearch(embeddedPeopleLinkSearchObjHref);

			embeddedPeople.setEmbeddedPeopleLink(embeddedPeopleLink);

			String embeddedPeopleRel = embeddedPeopleObj.getString("rel");
			embeddedPeople.setEmbeddedPeopleRel(embeddedPeopleRel);
			embedded.setEmbeddedPeople(embeddedPeople);

			EmbeddedOnlineMeetings embeddedOnlineMeetings = new EmbeddedOnlineMeetings();
			JSONObject embeddedOnlineMeetingsObj = embeddedObj.getJSONObject("onlineMeetings");

			EmbeddedOnlineMeetingsLink embeddedOnlineMeetingsLink = new EmbeddedOnlineMeetingsLink();
			JSONObject embeddedOnlineMeetingsLinkObj = embeddedOnlineMeetingsObj.getJSONObject(LyncConstants._LINKS);

			JSONObject embeddedOnlineMeetingsLinkSelfObj = embeddedOnlineMeetingsLinkObj.getJSONObject("self");
			String embeddedOnlineMeetingsLinkSelfObjHref = embeddedOnlineMeetingsLinkSelfObj.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkSelf(embeddedOnlineMeetingsLinkSelfObjHref);

			JSONObject embeddedOnlineMeetingsLinkMyOnlineMeetingsObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("myOnlineMeetings");
			String embeddedOnlineMeetingsLinkMyOnlineMeetingsObjHref = embeddedOnlineMeetingsLinkMyOnlineMeetingsObj
					.getString("href");
			embeddedOnlineMeetingsLink
					.setEmbeddedOnlineMeetingsLinkMyOnlineMeetings(embeddedOnlineMeetingsLinkMyOnlineMeetingsObjHref);

			JSONObject embeddedOnlineMeetingsLinkOnlineMeetingDefaultValuesObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("onlineMeetingDefaultValues");
			String embeddedOnlineMeetingsLinkOnlineMeetingDefaultValuesObjHref = embeddedOnlineMeetingsLinkOnlineMeetingDefaultValuesObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkOnlineMeetingDefaultValues(
					embeddedOnlineMeetingsLinkOnlineMeetingDefaultValuesObjHref);

			JSONObject embeddedOnlineMeetingsLinkOnlineMeetingEligibleValuesObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("onlineMeetingEligibleValues");
			String embeddedOnlineMeetingsLinkOnlineMeetingEligibleValuesObjHref = embeddedOnlineMeetingsLinkOnlineMeetingEligibleValuesObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkOnlineMeetingEligibleValues(
					embeddedOnlineMeetingsLinkOnlineMeetingEligibleValuesObjHref);

			JSONObject embeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomizationObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("onlineMeetingInvitationCustomization");
			String embeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomizationObjHref = embeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomizationObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomization(
					embeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomizationObjHref);

			JSONObject embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("onlineMeetingPolicies");
			String embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObjHref = embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkOnlineMeetingPolicies(
					embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObjHref);

			JSONObject embeddedOnlineMeetingsLinkPhoneDialInInformationObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("phoneDialInInformation");
			String embeddedOnlineMeetingsLinkPhoneDialInInformationObjHref = embeddedOnlineMeetingsLinkPhoneDialInInformationObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkPhoneDialInInformation(
					embeddedOnlineMeetingsLinkPhoneDialInInformationObjHref);

			JSONObject embeddedOnlineMeetingsLinkMyAssignedOnlineMeetingObj = embeddedOnlineMeetingsLinkObj
					.getJSONObject("myAssignedOnlineMeeting");
			String embeddedOnlineMeetingsLinkMyAssignedOnlineMeetingObjHref = embeddedOnlineMeetingsLinkMyAssignedOnlineMeetingObj
					.getString("href");
			embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkMyAssignedOnlineMeeting(
					embeddedOnlineMeetingsLinkMyAssignedOnlineMeetingObjHref);

			embeddedOnlineMeetings.setEmbeddedOnlineMeetingsLink(embeddedOnlineMeetingsLink);

			String embeddedOnlineMeetingsRel = embeddedOnlineMeetingsObj.getString("rel");
			embeddedOnlineMeetings.setEmbeddedOnlineMeetingsRel(embeddedOnlineMeetingsRel);
			embedded.setEmbeddedOnlineMeetings(embeddedOnlineMeetings);

			EmbeddedCommunication embeddedCommunication = new EmbeddedCommunication();
			JSONObject embeddedcommunicationObj = embeddedObj.getJSONObject("communication");

			EmbeddedCommunicationLink embeddedCommunicationLink = new EmbeddedCommunicationLink();
			JSONObject embeddedcommunicationLinkObj = embeddedcommunicationObj.getJSONObject(LyncConstants._LINKS);

			JSONObject embeddedcommunicationLinkSelfObj = embeddedcommunicationLinkObj.getJSONObject("self");
			String embeddedcommunicationLinkSelfObjHref = embeddedcommunicationLinkSelfObj.getString("href");
			embeddedCommunicationLink.setEmbeddedCommunicationLinkSelf(embeddedcommunicationLinkSelfObjHref);

			JSONObject embeddedcommunicationLinkStartPhoneAudioObj = embeddedcommunicationLinkObj
					.getJSONObject("startPhoneAudio");
			String embeddedcommunicationLinkStartPhoneAudioObjHref = embeddedcommunicationLinkStartPhoneAudioObj
					.getString("href");
			embeddedCommunicationLink
					.setEmbeddedCommunicationLinkStartPhoneAudio(embeddedcommunicationLinkStartPhoneAudioObjHref);

			JSONObject embeddedcommunicationLinkConversationsObj = embeddedcommunicationLinkObj
					.getJSONObject("conversations");
			String embeddedcommunicationLinkConversationsObjHref = embeddedcommunicationLinkConversationsObj
					.getString("href");
			embeddedCommunicationLink
					.setEmbeddedCommunicationLinkConversations(embeddedcommunicationLinkConversationsObjHref);

			JSONObject embeddedcommunicationLinkStartMessagingObj = embeddedcommunicationLinkObj
					.getJSONObject("startMessaging");
			String embeddedcommunicationLinkStartMessagingObjHref = embeddedcommunicationLinkStartMessagingObj
					.getString("href");
			embeddedCommunicationLink
					.setEmbeddedCommunicationLinkStartMessaging(embeddedcommunicationLinkStartMessagingObjHref);

			JSONObject embeddedcommunicationLinkStartOnlineMeetingObj = embeddedcommunicationLinkObj
					.getJSONObject("startOnlineMeeting");
			String embeddedcommunicationLinkembeddedcommunicationLinkStartOnlineMeetingObjHref = embeddedcommunicationLinkStartOnlineMeetingObj
					.getString("href");
			embeddedCommunicationLink.setEmbeddedCommunicationLinkStartOnlineMeeting(
					embeddedcommunicationLinkembeddedcommunicationLinkStartOnlineMeetingObjHref);

			JSONObject embeddedcommunicationLinkJoinOnlineMeetingObj = embeddedcommunicationLinkObj
					.getJSONObject("joinOnlineMeeting");
			String embeddedcommunicationLinkJoinOnlineMeetingObjHref = embeddedcommunicationLinkJoinOnlineMeetingObj
					.getString("href");
			embeddedCommunicationLink
					.setEmbeddedCommunicationLinkJoinOnlineMeeting(embeddedcommunicationLinkJoinOnlineMeetingObjHref);

			embeddedCommunication.setEmbeddedCommunicationLink(embeddedCommunicationLink);

			String embeddedcommunicationObjRel = embeddedcommunicationObj.getString("rel");
			embeddedCommunication.setEmbeddedCommunicationRel(embeddedcommunicationObjRel);

			String embeddedcommunicationObjETag = embeddedcommunicationObj.getString("etag");
			embeddedCommunication.setEmbeddedCommunicationETag(embeddedcommunicationObjETag);

			embedded.setEmbeddedCommunication(embeddedCommunication);

			String relObj = (String) finalResJSONObj.get("rel");
			lyncChatJSON.setRel(relObj);
			lyncChatJSON.setEmbedded(embedded);

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
		}
	}

	// HTTP GET request
	private void updateMyLyncRequestParams() throws IOException {

		String makeMeAvailableURL = lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink()
				.getEmbeddedMeLinkMakeMeAvailable();

		if(makeMeAvailableURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL url = new URL(makeMeAvailableURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + makeMeAvailableURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("POST");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		JSONArray jsonArr = new JSONArray();
		jsonArr.put("Messaging");

		JSONObject postData = new JSONObject();
		postData.put("SupportedModalities", jsonArr);

		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, LyncConstants.UTF_8));
		writer.write(postData.toString());
		writer.flush();
		writer.close();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private String[] getMyPresenceStatus() throws IOException {

		String presenceURL = lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink().getEmbeddedMeLinkPresence();

		if(presenceURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL url = new URL(presenceURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + presenceURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			final JSONObject responseObj = new JSONObject(response.toString());

			String availability = responseObj.getString("availability");
			JSONObject linksObj = responseObj.getJSONObject(LyncConstants._LINKS);
			JSONObject selfObj = linksObj.getJSONObject("self");
			String linkSelf = selfObj.getString("href");
			String rel = responseObj.getString("rel");

			System.out.println(LyncConstants.LINE_SEPERATOR);
			return new String[] { availability, linkSelf, rel };

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);
			return new String[] {};
		}

	}

	// HTTP GET request
	private void setMyPresenceStatus() throws IOException {

		String presenceURL = lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink().getEmbeddedMeLinkPresence();
		
		if(presenceURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		URL url = new URL(presenceURL);
		System.out.println(LyncConstants.TRYING_TO_CONNECT + presenceURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("POST");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		JSONObject postData = new JSONObject();
		
		
		System.out.println("Available presence statuses are ");
		
		List<Integer> presenceStatusList = new ArrayList<>(presenceStatusMap.keySet());
		Collections.sort(presenceStatusList);
				
		for(Integer presenceStatusCode : presenceStatusList) {
		    System.out.println(presenceStatusMap.get(presenceStatusCode).getPresenceCode() + ".  " + presenceStatusMap.get(presenceStatusCode).getUserDisplayStr() + ",");
		}
		
		
		System.out.print(" Select your option as corresponsing number ");
		
		Integer selectedPresence = scanner.nextInt();

		postData.put("availability", presenceStatusMap.get(selectedPresence).getActualStr());
		
		
		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, LyncConstants.UTF_8));
		writer.write(postData.toString());
		writer.flush();
		writer.close();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private String[] getMyNote() throws IOException {

		String noteURL = lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink().getEmbeddedMeLinkNote();
		
		URL url = new URL(noteURL);
		if(noteURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		System.out.println(LyncConstants.TRYING_TO_CONNECT + noteURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			final JSONObject responseObj = new JSONObject(response.toString());

			String message = responseObj.getString(LyncConstants.MESSAGE);
			JSONObject linksObj = responseObj.getJSONObject(LyncConstants._LINKS);
			JSONObject selfObj = linksObj.getJSONObject("self");
			String linkSelf = selfObj.getString("href");
			String type = responseObj.getString("type");
			String rel = responseObj.getString("rel");

			System.out.println("My note is ==> " + message);
			System.out.println(LyncConstants.LINE_SEPERATOR);
			return new String[] { message, linkSelf, rel, type };

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			
			System.out.println(LyncConstants.LINE_SEPERATOR);
			return new String[] {};
		}

	}

	// HTTP GET request
	private void setMyNote() throws IOException {

		String noteURL = lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink().getEmbeddedMeLinkNote();
		
		URL url = new URL(noteURL);
		if(noteURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		System.out.println(LyncConstants.TRYING_TO_CONNECT + noteURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestProperty(LyncConstants.CONTENT_TYPE, LyncConstants.APPLICATION_JSON);
		con.setRequestMethod("POST");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		System.out.println("Please enter your note: ");
		String myNote = scanner.nextLine();
		
		
		JSONObject postData = new JSONObject();
		postData.put(LyncConstants.MESSAGE, "working working working");
		postData.put(LyncConstants.MESSAGE, myNote);
		

		OutputStream os = con.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, LyncConstants.UTF_8));
		writer.write(postData.toString());
		writer.flush();
		writer.close();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	// HTTP GET request
	private void retrieveMyPhoto() throws IOException {

		String photoURL =
				lyncChatJSON.getEmbedded().getEmbeddedMe().getEmbeddedMeLink().getEmbeddedMeLinkPhoto();
		
		URL url = new URL(photoURL);
		if(photoURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		System.out.println(LyncConstants.TRYING_TO_CONNECT + photoURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedInputStream in = new BufferedInputStream(con.getInputStream());
			File source = new File("C:/MyImage/rajkumar.jpg");
			FileOutputStream fos = new FileOutputStream(source);

			int current;
			while ((current = in.read()) != -1) {
				fos.write(current);
			}

			fos.close();
			in.close();
			
			System.out.println(LyncConstants.LINE_SEPERATOR);

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			
			System.out.println(LyncConstants.LINE_SEPERATOR);
		}

	}

	private String saveImage(String targetURL, String contactName) throws IOException {

		File source = new File("C:/MyImage/" + contactName + ".jpg");

		URL url = new URL(targetURL);
		if(targetURL == null) {
			throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
		}
		System.out.println(LyncConstants.TRYING_TO_CONNECT + targetURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedInputStream in = new BufferedInputStream(con.getInputStream());

			FileOutputStream fos = new FileOutputStream(source);

			int current;
			while ((current = in.read()) != -1) {
				fos.write(current);
			}

			fos.close();
			in.close();
			return null;

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
			return null;
		}

	}

	// HTTP GET request
	private String doSearchAndGetPhoto(String targetURL, String xFrameStr, String accessToken, boolean isSpecficPerson) throws IOException {
		
		if(isSpecficPerson) {
		    queryLyncToRetrieveContactForSearchPattern(targetURL, xFrameStr, accessToken, "rajkumar");
		} else {
		    List<String> contactNamePatternList = getAllContactSearchPatternList();

	        for (String contactPattern : contactNamePatternList) {
	            queryLyncToRetrieveContactForSearchPattern(targetURL, xFrameStr, accessToken, contactPattern);
	        }
		}

		
		return null;
	}

	private void queryLyncToRetrieveContactForSearchPattern(String targetURL, String xFrameStr, String accessToken,
			String name) throws IOException {

		String queryURL = targetURL + "?query=" + name;

		URL url = new URL(queryURL);
		
		System.out.println(LyncConstants.TRYING_TO_CONNECT + queryURL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty(LyncConstants.USER_AGENT_STR, LyncConstants.USER_AGENT);
		con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE, LyncConstants.EN_US_EN_Q_0_5);
		con.setRequestProperty(LyncConstants.REFERER, xFrameStr);
		con.setRequestProperty(LyncConstants.ACCEPT_ENCODING, LyncConstants.GZIP_DEFLATE);
		con.setRequestProperty(LyncConstants.ACCEPT, LyncConstants.APPLICATION_JSON);
		con.setRequestProperty(LyncConstants.AUTHORIZATION, LyncConstants.BEARER + accessToken);

		con.setDoOutput(true);

		int responseCode = con.getResponseCode();
		System.out.println(LyncConstants.GET_RESPONSE_CODE + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			JSONObject contatcsWholeObj = new JSONObject(response.toString());
			JSONObject embeddedObj = contatcsWholeObj.getJSONObject("_embedded");
			JSONArray contactObj = embeddedObj.getJSONArray("contact");

			for (int i = 0; i < contactObj.length(); i++) {

				JSONObject contact = (JSONObject) contactObj.get(i);
				String contactName = contact.getString("name");
				rerievePhoto(contact, contactName, contactSaveDir);
			}

		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(LyncConstants.GET_REQUEST_NOT_WORKED);
			System.out.println(response.toString());
		}
	}

	private void rerievePhoto(JSONObject contact, String contactName,
			String baseDir) throws IOException {
		if (contactName != null) {

			File source = new File(baseDir + "/" + contactName + ".jpg");

			if (source.exists()) {
				return;
			}

			JSONObject contactLinkObj = contact.getJSONObject(LyncConstants._LINKS);

			if (!contactLinkObj.isNull("contactPhoto")) {
				JSONObject contactLinkPhotoObj = contactLinkObj.getJSONObject("contactPhoto");
				String contactPhotoURL = URL_STRING + contactLinkPhotoObj.getString("href");
				saveImage(contactPhotoURL, contactName);
			}
		}
	}

	private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "utf-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "utf-8"));
		}

		return result.toString();
	}

	private List<String> getAllContactSearchPatternList() {

		List<String> contactList = new ArrayList<>();

		String[] namesArr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };

		String[] namesArr1 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };

		String[] namesArr2 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };

		String[] namesArr3 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };

		for (String name1 : namesArr) {
			for (String name2 : namesArr1) {
				for (String name3 : namesArr2) {
					for (String name4 : namesArr3) {
						StringBuilder sb = new StringBuilder();
						sb.append(name1);
						sb.append(name2);
						sb.append(name3);
						sb.append(name4);
						contactList.add(sb.toString());
					}
				}
			}
		}

		return contactList;

	}

}
