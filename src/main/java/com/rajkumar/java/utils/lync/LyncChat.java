package com.rajkumar.java.utils.lync;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

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

public class LyncChat implements TargetDomain {
  
  private static final String IMG_PATH = "C:/MyImage/rajkumar.jpg"; //NOSONAR

  private boolean needsTobeSkipped = false;
  
  private String lyncDomain = "https://lyncdiscover.nagra.com/";
  
  private String yourUserUrl = null;
  private String oathTokenStr = null;
  private String frameStr = null;
  private String accessToken = null;
  private String applicationUrl = null;
  private LyncChatJson lyncChatJson = new LyncChatJson();
  private boolean isSpecficPerson = false;
  
  private String contactSaveDir = "C:/MyImage/";
  
  private Map<Integer, PresenceStatus> presenceStatusMap = new HashMap<>();
  Scanner scanner = new Scanner(new InputStreamReader(System.in));
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Initialize Lync Chat.
   */
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
    presenceStatus4.setUserDisplayStr("Do Not Disturb");
    presenceStatus4.setActualStr("DoNotDisturb");
    
    PresenceStatus presenceStatus5 = new PresenceStatus();
    presenceStatus5.setPresenceCode(5);
    presenceStatus5.setUserDisplayStr("Idle But Busy");
    presenceStatus5.setActualStr("IdleBusy");
    
    PresenceStatus presenceStatus6 = new PresenceStatus();
    presenceStatus6.setPresenceCode(6);
    presenceStatus6.setUserDisplayStr("Idle But Online");
    presenceStatus6.setActualStr("IdleOnline");
    
    PresenceStatus presenceStatus7 = new PresenceStatus();
    presenceStatus7.setPresenceCode(7);
    presenceStatus7.setUserDisplayStr("Offline");
    presenceStatus7.setActualStr("Offline");
    
    PresenceStatus presenceStatus8 = new PresenceStatus();
    presenceStatus8.setPresenceCode(8);
    presenceStatus8.setUserDisplayStr("Available");
    presenceStatus8.setActualStr("Available");
    
    presenceStatusMap.put(1, presenceStatus1);
    presenceStatusMap.put(2, presenceStatus2);
    presenceStatusMap.put(3, presenceStatus3);
    presenceStatusMap.put(4, presenceStatus4);
    presenceStatusMap.put(5, presenceStatus5);
    presenceStatusMap.put(6, presenceStatus6);
    presenceStatusMap.put(7, presenceStatus7);
    presenceStatusMap.put(8, presenceStatus8);
    
  }
  
  /**
   * Initialize lync chat.
   * 
   * @throws IOException exception
   */
  public void initiateLyncChat() throws IOException {
    
    // Connect to Lync Domain
    connectToLyncDomain();
    
    // Connect to your Lync user URL
    connectToYourLyncUserUrl();
    
    // Connect and Get access token
    getAccessToken();
    
    // Connect and Get Lync applications URL
    getLyncApplicationUrl();
    
    // Connect and Get permissible URLs
    getPermissibleUrls();
    
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
  
  /**
   * Retrieving photos.
   * 
   * @throws IOException exception
   */
  public void getOtherContactsPhotos() throws IOException {
    
    String wholeContactUrl = lyncChatJson.getEmbedded().getEmbeddedPeople()
        .getEmbeddedPeopleLink().getEmbeddedPeopleLinkSearch();
    doSearchAndGetPhoto(wholeContactUrl, frameStr, accessToken,
        this.isSpecficPerson);
    
    logger.info(Constants.LINE_SEPERATOR);
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) throws Exception {
    
    LyncChat http = new LyncChat();
    http.initiateLyncChat();
    
  }
  
  // HTTP GET request
  private void connectToLyncDomain() throws IOException {
    
    logger.info("Step 1 - Connect to Lync Domain");
    
    URL url = new URL(lyncDomain);
    logger.info(LyncConstants.TRYING_TO_CONNECT + lyncDomain);
    
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
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
      yourUserUrl = (String) userObj.get("href");
      frameStr = (String) xframeObj.get("href");
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  // HTTP GET request
  private void connectToYourLyncUserUrl() throws IOException {
    
    logger.info("Step 2 - Connect to Your User URL ");
    
    if (yourUserUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    
    URL url = new URL(yourUserUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + yourUserUrl);
    
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      logger.info(LyncConstants.LINE_SEPERATOR);
      return;
      
    } else {
      
      String authStr = con.getHeaderField("WWW-Authenticate").split("href=")[1];
      oathTokenStr = authStr.substring(1, authStr.indexOf('\'', 1));
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  // HTTP GET request
  private void getAccessToken() throws IOException {
    
    logger.info("Step 3 - Get Access Token ");
    
    if (oathTokenStr == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL obj = new URL(oathTokenStr);
    logger.info(LyncConstants.TRYING_TO_CONNECT + oathTokenStr);
    
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
    
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        "application/x-www-form-urlencoded;charset='utf-8");
    con.setRequestMethod("POST");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    
    con.setDoOutput(true);
    
    List<NameValuePair> parameters = new ArrayList<>();
    parameters
        .add(new BasicNameValuePair("grant_type", "urn:microsoft.rtc:windows"));
    
    OutputStream os = con.getOutputStream();
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(os, LyncConstants.UTF_8));
    writer.write(getQuery(parameters));
    writer.flush();
    writer.close();
    os.close();
    
    int responseCode = con.getResponseCode();
    logger.info("Response Code : " + responseCode);
    
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      final JSONObject linksObj = new JSONObject(response.toString());
      
      accessToken = linksObj.getString("access_token");
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
  }
  
  // HTTP GET request
  private void getLyncApplicationUrl() throws IOException {
    
    logger.info("Step 4 - Get Applications URL ");
    
    if (yourUserUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL url = new URL(yourUserUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + yourUserUrl);
    
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      final JSONObject linksObj = new JSONObject(response.toString());
      JSONObject otherObj = linksObj.getJSONObject(LyncConstants._LINKS);
      JSONObject applicationsObj = otherObj.getJSONObject("applications");
      
      applicationUrl = applicationsObj.getString("href");
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  // HTTP GET request
  private void getPermissibleUrls() throws IOException {
    
    logger.info("Step 4 - Get all our permissiable URL ");
    
    if (applicationUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL obj = new URL(applicationUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + applicationUrl);
    
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
    
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("POST");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    JSONObject postData = new JSONObject();
    postData.put("UserAgent", "UCWA Samples");
    postData.put("EndpointId", "a917c6f4-976c-4cf3-847d-cdfffa28ccdf");
    postData.put("Culture", "en-IN");
    
    OutputStream os = con.getOutputStream();
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(os, LyncConstants.UTF_8));
    writer.write(postData.toString());
    writer.flush();
    writer.close();
    os.close();
    
    int responseCode = con.getResponseCode();
    logger.info("Response Code : " + responseCode);
    
    if (responseCode == HttpsURLConnection.HTTP_OK
        || responseCode == HttpsURLConnection.HTTP_CREATED) { // success
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      final JSONObject finalResJsonObj = new JSONObject(response.toString());
      
      String cultureObj = (String) finalResJsonObj.get("culture");
      lyncChatJson.setCulture(cultureObj);
      
      String userObj = (String) finalResJsonObj.get("userAgent");
      lyncChatJson.setUserAgent(userObj);
      
      Link link = new Link();
      JSONObject linksObj = finalResJsonObj.getJSONObject(LyncConstants._LINKS);
      
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
      lyncChatJson.setLink(link);
      
      JSONObject embeddedObj = finalResJsonObj.getJSONObject("_embedded");
      
      EmbeddedMe embeddedMe = new EmbeddedMe();
      
      JSONObject embeddedMeObj = embeddedObj.getJSONObject("me");
      String nameObj = embeddedMeObj.getString("name");
      embeddedMe.setEmbeddedMeName(nameObj);
      
      String uriObj = embeddedMeObj.getString("uri");
      embeddedMe.setEmbeddedMeUri(uriObj);
      
      JSONArray emailAddressesObj = embeddedMeObj
          .getJSONArray("emailAddresses");
      embeddedMe.setEmbeddedMeEMailAddresses((String) emailAddressesObj.get(0));
      
      String titleObj = embeddedMeObj.getString("title");
      embeddedMe.setEmbeddedMeTitle(titleObj);
      
      String departmentObj = embeddedMeObj.getString("department");
      embeddedMe.setEmbeddedMeDepartment(departmentObj);
      
      EmbeddedMeLink embeddedMeLink = new EmbeddedMeLink();
      JSONObject embeddedMeLinkObj = embeddedMeObj
          .getJSONObject(LyncConstants._LINKS);
      
      JSONObject embeddedMeLinkSelfObj = embeddedMeLinkObj
          .getJSONObject("self");
      String embeddedMeLinkSelfObjHref = embeddedMeLinkSelfObj
          .getString("href");
      embeddedMeLink.setEmbeddedMeLinkSelf(embeddedMeLinkSelfObjHref);
      
      try {
        JSONObject embeddedMeLinkMakeMeAvailableObj = embeddedMeLinkObj
            .getJSONObject("makeMeAvailable");
        String embeddedMeLinkMakeMeAvailableObjHref = embeddedMeLinkMakeMeAvailableObj
            .getString("href");
        embeddedMeLink.setEmbeddedMeLinkMakeMeAvailable(
            embeddedMeLinkMakeMeAvailableObjHref);
        
      } catch (Exception exception) {
        Utils.getException(exception);
        needsTobeSkipped = true;
      }
      
      if (needsTobeSkipped) {
        JSONObject embeddedMeLinkNoteObj = embeddedMeLinkObj
            .getJSONObject("note");
        String embeddedMeLinkNoteHref = embeddedMeLinkNoteObj.getString("href");
        embeddedMeLink.setEmbeddedMeLinkNote(embeddedMeLinkNoteHref);
        
        JSONObject embeddedMeLinkPresenceObj = embeddedMeLinkObj
            .getJSONObject("presence");
        String embeddedMeLinkPresenceObjHref = embeddedMeLinkPresenceObj
            .getString("href");
        embeddedMeLink.setEmbeddedMeLinkPresence(embeddedMeLinkPresenceObjHref);
        
        JSONObject embeddedMeLinkLocationObj = embeddedMeLinkObj
            .getJSONObject("location");
        String embeddedMeLinkLocationObjHref = embeddedMeLinkLocationObj
            .getString("href");
        embeddedMeLink.setEmbeddedMeLinkLocation(embeddedMeLinkLocationObjHref);
        
        JSONObject embeddedMeLinkReportMyActivityObj = embeddedMeLinkObj
            .getJSONObject("reportMyActivity");
        String embeddedMeLinkReportMyActivityObjHref = embeddedMeLinkReportMyActivityObj
            .getString("href");
        embeddedMeLink.setEmbeddedMeLinkReportMyActivity(
            embeddedMeLinkReportMyActivityObjHref);
        
      }
      
      JSONObject embeddedMeLinkCallForwardingSettingsObj = embeddedMeLinkObj
          .getJSONObject("callForwardingSettings");
      String embeddedMeLinkCallForwardingSettingsObjHref = embeddedMeLinkCallForwardingSettingsObj
          .getString("href");
      embeddedMeLink.setEmbeddedMeLinkCallForwardingSettings(
          embeddedMeLinkCallForwardingSettingsObjHref);
      
      JSONObject embeddedMeLinkPhonesObj = embeddedMeLinkObj
          .getJSONObject("phones");
      String embeddedMeLinkPhonesObjHref = embeddedMeLinkPhonesObj
          .getString("href");
      embeddedMeLink.setEmbeddedMeLinkPhones(embeddedMeLinkPhonesObjHref);
      
      JSONObject embeddedMeLinkPhotoObj = embeddedMeLinkObj
          .getJSONObject("photo");
      String embeddedMeLinkPhotoObjHref = embeddedMeLinkPhotoObj
          .getString("href");
      embeddedMeLink.setEmbeddedMeLinkPhoto(embeddedMeLinkPhotoObjHref);
      
      embeddedMe.setEmbeddedMeLink(embeddedMeLink);
      
      String embeddedMeRel = embeddedMeObj.getString("rel");
      embeddedMe.setEmbeddedMeRel(embeddedMeRel);
      
      Embedded embedded = new Embedded();
      embedded.setEmbeddedMe(embeddedMe);
      
      JSONObject embeddedPeopleObj = embeddedObj.getJSONObject("people");
      
      EmbeddedPeopleLink embeddedPeopleLink = new EmbeddedPeopleLink();
      JSONObject embeddedPeopleLinkObj = embeddedPeopleObj
          .getJSONObject(LyncConstants._LINKS);
      
      JSONObject embeddedPeopleLinkSelfObj = embeddedPeopleLinkObj
          .getJSONObject("self");
      String embeddedPeopleLinkSelfObjHref = embeddedPeopleLinkSelfObj
          .getString("href");
      embeddedPeopleLink
          .setEmbeddedPeopleLinkSelf(embeddedPeopleLinkSelfObjHref);
      
      JSONObject peopleLinkPresenceSubscriptionsObj = embeddedPeopleLinkObj
          .getJSONObject("presenceSubscriptions");
      String embeddedPeopleLinkPresenceSubscriptionsObjHref = peopleLinkPresenceSubscriptionsObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkPresenceSubscriptions(
          embeddedPeopleLinkPresenceSubscriptionsObjHref);
      
      JSONObject embeddedPeopleLinkSubscribedContactsObj = embeddedPeopleLinkObj
          .getJSONObject("subscribedContacts");
      String embeddedPeopleLinkSubscribedContactsObjHref = embeddedPeopleLinkSubscribedContactsObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkSubscribedContacts(
          embeddedPeopleLinkSubscribedContactsObjHref);
      
      JSONObject presenceSubscriptionMembershipsObj = embeddedPeopleLinkObj
          .getJSONObject("presenceSubscriptionMemberships");
      String presenceSubscriptionMembershipsObjHref = presenceSubscriptionMembershipsObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkPresenceSubscriptionMemberships(
          presenceSubscriptionMembershipsObjHref);
      
      JSONObject embeddedPeopleLinkMyGroupsObj = embeddedPeopleLinkObj
          .getJSONObject("myGroups");
      String embeddedPeopleLinkMyGroupsObjHref = embeddedPeopleLinkMyGroupsObj
          .getString("href");
      embeddedPeopleLink
          .setEmbeddedPeopleLinkMyGroups(embeddedPeopleLinkMyGroupsObjHref);
      
      JSONObject embeddedPeopleLinkMyGroupMembershipsObj = embeddedPeopleLinkObj
          .getJSONObject("myGroupMemberships");
      String embeddedPeopleLinkMyGroupMembershipsObjHref = embeddedPeopleLinkMyGroupMembershipsObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkMyGroupMemberships(
          embeddedPeopleLinkMyGroupMembershipsObjHref);
      
      JSONObject embeddedPeopleLinkMyContactsObj = embeddedPeopleLinkObj
          .getJSONObject("myContacts");
      String embeddedPeopleLinkMyContactsObjHref = embeddedPeopleLinkMyContactsObj
          .getString("href");
      embeddedPeopleLink
          .setEmbeddedPeopleLinkMyContacts(embeddedPeopleLinkMyContactsObjHref);
      
      JSONObject myPrivacyRelationshipsObj = embeddedPeopleLinkObj
          .getJSONObject("myPrivacyRelationships");
      String myPrivacyRelationshipsObjHref = myPrivacyRelationshipsObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkMyPrivacyRelationships(
          myPrivacyRelationshipsObjHref);
      
      JSONObject myContactsAndGroupsSubscriptionObj = embeddedPeopleLinkObj
          .getJSONObject("myContactsAndGroupsSubscription");
      String myContactsAndGroupsSubscriptionObjHref = myContactsAndGroupsSubscriptionObj
          .getString("href");
      embeddedPeopleLink.setEmbeddedPeopleLinkMyContactsAndGroupsSubscription(
          myContactsAndGroupsSubscriptionObjHref);
      
      JSONObject embeddedPeopleLinkSearchObj = embeddedPeopleLinkObj
          .getJSONObject("search");
      String embeddedPeopleLinkSearchObjHref = embeddedPeopleLinkSearchObj
          .getString("href");
      embeddedPeopleLink
          .setEmbeddedPeopleLinkSearch(embeddedPeopleLinkSearchObjHref);
      
      EmbeddedPeople embeddedPeople = new EmbeddedPeople();
      embeddedPeople.setEmbeddedPeopleLink(embeddedPeopleLink);
      
      String embeddedPeopleRel = embeddedPeopleObj.getString("rel");
      embeddedPeople.setEmbeddedPeopleRel(embeddedPeopleRel);
      embedded.setEmbeddedPeople(embeddedPeople);
      
      JSONObject embeddedOnlineMeetingsObj = embeddedObj
          .getJSONObject("onlineMeetings");
      
      EmbeddedOnlineMeetingsLink embeddedOnlineMeetingsLink = new EmbeddedOnlineMeetingsLink();
      JSONObject embeddedOnlineMeetingsLinkObj = embeddedOnlineMeetingsObj
          .getJSONObject(LyncConstants._LINKS);
      
      JSONObject embeddedOnlineMeetingsLinkSelfObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("self");
      String embeddedOnlineMeetingsLinkSelfObjHref = embeddedOnlineMeetingsLinkSelfObj
          .getString("href");
      embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkSelf(
          embeddedOnlineMeetingsLinkSelfObjHref);
      
      JSONObject meetingsLinkMyOnlineMeetingsObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("myOnlineMeetings");
      String meetingsLinkMyOnlineMeetingsObjHref = meetingsLinkMyOnlineMeetingsObj
          .getString("href");
      embeddedOnlineMeetingsLink.setEmbeddedOnlineMeetingsLinkMyOnlineMeetings(
          meetingsLinkMyOnlineMeetingsObjHref);
      
      JSONObject onlineMeetingDefaultValuesObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("onlineMeetingDefaultValues");
      String onlineMeetingDefaultValuesObjHref = onlineMeetingDefaultValuesObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkOnlineMeetingDefaultValues(
              onlineMeetingDefaultValuesObjHref);
      
      JSONObject onlineMeetingEligibleValuesObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("onlineMeetingEligibleValues");
      String onlineMeetingEligibleValuesObjHref = onlineMeetingEligibleValuesObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkOnlineMeetingEligibleValues(
              onlineMeetingEligibleValuesObjHref);
      
      JSONObject invitationCustomizationObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("onlineMeetingInvitationCustomization");
      String invitationCustomizationObjHref = invitationCustomizationObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkOnlineMeetingInvitationCustomization(
              invitationCustomizationObjHref);
      
      JSONObject embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("onlineMeetingPolicies");
      String onlineMeetingPoliciesObjHref = embeddedOnlineMeetingsLinkOnlineMeetingPoliciesObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkOnlineMeetingPolicies(
              onlineMeetingPoliciesObjHref);
      
      JSONObject embeddedOnlineMeetingsLinkPhoneDialInInformationObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("phoneDialInInformation");
      String phoneDialInInformationObjHref = embeddedOnlineMeetingsLinkPhoneDialInInformationObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkPhoneDialInInformation(
              phoneDialInInformationObjHref);
      
      JSONObject myAssignedOnlineMeetingObj = embeddedOnlineMeetingsLinkObj
          .getJSONObject("myAssignedOnlineMeeting");
      String myAssignedOnlineMeetingObjHref = myAssignedOnlineMeetingObj
          .getString("href");
      embeddedOnlineMeetingsLink
          .setEmbeddedOnlineMeetingsLinkMyAssignedOnlineMeeting(
              myAssignedOnlineMeetingObjHref);
      
      EmbeddedOnlineMeetings embeddedOnlineMeetings = new EmbeddedOnlineMeetings();
      embeddedOnlineMeetings
          .setEmbeddedOnlineMeetingsLink(embeddedOnlineMeetingsLink);
      
      String embeddedOnlineMeetingsRel = embeddedOnlineMeetingsObj
          .getString("rel");
      embeddedOnlineMeetings
          .setEmbeddedOnlineMeetingsRel(embeddedOnlineMeetingsRel);
      embedded.setEmbeddedOnlineMeetings(embeddedOnlineMeetings);
      
      JSONObject embeddedcommunicationObj = embeddedObj
          .getJSONObject("communication");
      
      EmbeddedCommunicationLink embeddedCommunicationLink = new EmbeddedCommunicationLink();
      JSONObject embeddedcommunicationLinkObj = embeddedcommunicationObj
          .getJSONObject(LyncConstants._LINKS);
      
      JSONObject embeddedcommunicationLinkSelfObj = embeddedcommunicationLinkObj
          .getJSONObject("self");
      String embeddedcommunicationLinkSelfObjHref = embeddedcommunicationLinkSelfObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkSelf(
          embeddedcommunicationLinkSelfObjHref);
      
      JSONObject embeddedcommunicationLinkStartPhoneAudioObj = embeddedcommunicationLinkObj
          .getJSONObject("startPhoneAudio");
      String linkStartPhoneAudioObjHref = embeddedcommunicationLinkStartPhoneAudioObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkStartPhoneAudio(
          linkStartPhoneAudioObjHref);
      
      JSONObject embeddedcommunicationLinkConversationsObj = embeddedcommunicationLinkObj
          .getJSONObject("conversations");
      String linkConversationsObjHref = embeddedcommunicationLinkConversationsObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkConversations(
          linkConversationsObjHref);
      
      JSONObject embeddedcommunicationLinkStartMessagingObj = embeddedcommunicationLinkObj
          .getJSONObject("startMessaging");
      String communicationLinkStartMessagingObjHref = embeddedcommunicationLinkStartMessagingObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkStartMessaging(
          communicationLinkStartMessagingObjHref);
      
      JSONObject communicationLinkStartOnlineMeetingObj = embeddedcommunicationLinkObj
          .getJSONObject("startOnlineMeeting");
      String communicationLinkStartOnlineMeetingObjHref = communicationLinkStartOnlineMeetingObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkStartOnlineMeeting(
          communicationLinkStartOnlineMeetingObjHref);
      
      JSONObject embeddedcommunicationLinkJoinOnlineMeetingObj = embeddedcommunicationLinkObj
          .getJSONObject("joinOnlineMeeting");
      String joinOnlineMeetingObjHref = embeddedcommunicationLinkJoinOnlineMeetingObj
          .getString("href");
      embeddedCommunicationLink.setEmbeddedCommunicationLinkJoinOnlineMeeting(
          joinOnlineMeetingObjHref);
      
      EmbeddedCommunication embeddedCommunication = new EmbeddedCommunication();
      embeddedCommunication
          .setEmbeddedCommunicationLink(embeddedCommunicationLink);
      
      String embeddedcommunicationObjRel = embeddedcommunicationObj
          .getString("rel");
      embeddedCommunication
          .setEmbeddedCommunicationRel(embeddedcommunicationObjRel);
      
      String embeddedcommunicationObjETag = embeddedcommunicationObj
          .getString("etag");
      embeddedCommunication
          .setEmbeddedCommunicationETag(embeddedcommunicationObjETag);
      
      embedded.setEmbeddedCommunication(embeddedCommunication);
      
      String relObj = (String) finalResJsonObj.get("rel");
      lyncChatJson.setRel(relObj);
      lyncChatJson.setEmbedded(embedded);
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
  }
  
  // HTTP GET request
  private void updateMyLyncRequestParams() throws IOException {
    
    String makeMeAvailableUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkMakeMeAvailable();
    
    if (makeMeAvailableUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL url = new URL(makeMeAvailableUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + makeMeAvailableUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("POST");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    JSONArray jsonArr = new JSONArray();
    jsonArr.put("Messaging");
    
    JSONObject postData = new JSONObject();
    postData.put("SupportedModalities", jsonArr);
    
    OutputStream os = con.getOutputStream();
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(os, LyncConstants.UTF_8));
    writer.write(postData.toString());
    writer.flush();
    writer.close();
    os.close();
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK
        || responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  // HTTP GET request
  private String[] getMyPresenceStatus() throws IOException {
    
    String presenceUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkPresence();
    
    if (presenceUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL url = new URL(presenceUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + presenceUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      final JSONObject responseObj = new JSONObject(response.toString());
      
      String availability = responseObj.getString("availability");
      JSONObject linksObj = responseObj.getJSONObject(LyncConstants._LINKS);
      JSONObject selfObj = linksObj.getJSONObject("self");
      String linkSelf = selfObj.getString("href");
      String rel = responseObj.getString("rel");
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      return new String[] {availability, linkSelf, rel};
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      return new String[] { };
    }
    
  }
  
  /**
   *  HTTP GET request.
   *  
   * @throws IOException exception
   */
  public void setMyPresenceStatus() throws IOException {
    
    String presenceUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkPresence();
    
    if (presenceUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    URL url = new URL(presenceUrl);
    logger.info(LyncConstants.TRYING_TO_CONNECT + presenceUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("POST");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    logger.info("Available presence statuses are ");
    
    List<Integer> presenceStatusList = new ArrayList<>(
        presenceStatusMap.keySet());
    Collections.sort(presenceStatusList);
    
    for (Integer presenceStatusCode : presenceStatusList) {
      logger.info(
          presenceStatusMap.get(presenceStatusCode).getPresenceCode() + ".  "
              + presenceStatusMap.get(presenceStatusCode).getUserDisplayStr()
              + ",");
    }
    
    logger.info(" Select your option as corresponsing number ");
    
    Integer selectedPresence = scanner.nextInt();
    
    JSONObject postData = new JSONObject();
    postData.put("availability",
        presenceStatusMap.get(selectedPresence).getActualStr());
    
    OutputStream os = con.getOutputStream();
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(os, LyncConstants.UTF_8));
    writer.write(postData.toString());
    writer.flush();
    writer.close();
    os.close();
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  /**
   *  HTTP GET request.
   *  
   * @return notes
   * @throws IOException exception
   */
  public String[] getMyNote() throws IOException {
    
    String noteUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkNote();
    
    URL url = new URL(noteUrl);
    if (noteUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    logger.info(LyncConstants.TRYING_TO_CONNECT + noteUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      
      final JSONObject responseObj = new JSONObject(response.toString());
      
      String message = responseObj.getString(LyncConstants.MESSAGE);
      JSONObject linksObj = responseObj.getJSONObject(LyncConstants._LINKS);
      JSONObject selfObj = linksObj.getJSONObject("self");
      String linkSelf = selfObj.getString("href");
      String type = responseObj.getString("type");
      String rel = responseObj.getString("rel");
      
      logger.info("My note is ==> " + message);
      logger.info(LyncConstants.LINE_SEPERATOR);
      return new String[] {message, linkSelf, rel, type};
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      return new String[] { };
    }
    
  }
  
  /**
   *  HTTP GET request.
   *  
   * @throws IOException exception
   */
  public void setMyNote() throws IOException {
    
    String noteUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkNote();
    
    URL url = new URL(noteUrl);
    if (noteUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    logger.info(LyncConstants.TRYING_TO_CONNECT + noteUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestProperty(LyncConstants.CONTENT_TYPE,
        LyncConstants.APPLICATION_JSON);
    con.setRequestMethod("POST");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    logger.info("Please enter your note: ");
    String myNote = scanner.nextLine();
    
    JSONObject postData = new JSONObject();
    postData.put(LyncConstants.MESSAGE, "working working working");
    postData.put(LyncConstants.MESSAGE, myNote);
    
    OutputStream os = con.getOutputStream();
    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(os, LyncConstants.UTF_8));
    writer.write(postData.toString());
    writer.flush();
    writer.close();
    os.close();
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  // HTTP GET request
  private void retrieveMyPhoto() throws IOException {
    
    String photoUrl = lyncChatJson.getEmbedded().getEmbeddedMe()
        .getEmbeddedMeLink().getEmbeddedMeLinkPhoto();
    
    URL url = new URL(photoUrl);
    if (photoUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    logger.info(LyncConstants.TRYING_TO_CONNECT + photoUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      File source = new File(IMG_PATH);
      try(BufferedInputStream in = new BufferedInputStream(con.getInputStream());
          FileOutputStream fos = new FileOutputStream(source);) {
        int current;
        while ((current = in.read()) != -1) {
          fos.write(current);
        }
      }
      
      logger.info(LyncConstants.LINE_SEPERATOR);
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      
      logger.info(LyncConstants.LINE_SEPERATOR);
    }
    
  }
  
  private String saveImage(String targetUrl, String contactName)
      throws IOException {
    
    File source = new File("C:/MyImage/" + contactName + ".jpg");
    
    URL url = new URL(targetUrl);
    if (targetUrl == null) {
      throw new AssertionError(LyncConstants.UNABLE_TO_GET_URL);
    }
    logger.info(LyncConstants.TRYING_TO_CONNECT + targetUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      
      try( BufferedInputStream in = new BufferedInputStream(con.getInputStream());
          FileOutputStream fos = new FileOutputStream(source);) {
        
        int current;
        while ((current = in.read()) != -1) {
          fos.write(current);
        }
        
      }
     
      return null;
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
      return null;
    }
    
  }
  
  // HTTP GET request
  private String doSearchAndGetPhoto(String targetUrl, String frameStr,
      String accessToken, boolean isSpecficPerson) throws IOException {
    
    if (isSpecficPerson) {
      queryLyncToRetrieveContactForSearchPattern(targetUrl, frameStr,
          accessToken, "rajkumar");
    } else {
      List<String> contactNamePatternList = getAllContactSearchPatternList();
      
      for (String contactPattern : contactNamePatternList) {
        queryLyncToRetrieveContactForSearchPattern(targetUrl, frameStr,
            accessToken, contactPattern);
      }
    }
    
    return null;
  }
  
  private void queryLyncToRetrieveContactForSearchPattern(String targetUrl,
      String frameStr, String accessToken, String name) throws IOException {
    
    String queryUrl = targetUrl + "?query=" + name;
    
    URL url = new URL(queryUrl);
    
    logger.info(LyncConstants.TRYING_TO_CONNECT + queryUrl);
    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
    // add request header
    con.setRequestMethod("GET");
    con.setRequestProperty(LyncConstants.USER_AGENT_STR,
        LyncConstants.USR_AGNT);
    con.setRequestProperty(LyncConstants.ACCEPT_LANGUAGE,
        LyncConstants.EN_US_EN_Q_0_5);
    con.setRequestProperty(LyncConstants.REFERER, frameStr);
    con.setRequestProperty(LyncConstants.ACCEPT_ENCODING,
        LyncConstants.GZIP_DEFLATE);
    con.setRequestProperty(LyncConstants.ACCEPT,
        LyncConstants.APPLICATION_JSON);
    con.setRequestProperty(LyncConstants.AUTHORIZATION,
        LyncConstants.BEARER + accessToken);
    
    con.setDoOutput(true);
    
    int responseCode = con.getResponseCode();
    logger.info(LyncConstants.GET_RESPONSE_CODE + responseCode);
    if (responseCode == HttpsURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      // print result
      logger.info(response.toString());
      JSONObject contatcsWholeObj = new JSONObject(response.toString());
      JSONObject embeddedObj = contatcsWholeObj.getJSONObject("_embedded");
      JSONArray contactObj = embeddedObj.getJSONArray("contact");
      
      for (int i = 0; i < contactObj.length(); i++) {
        
        JSONObject contact = (JSONObject) contactObj.get(i);
        String contactName = contact.getString("name");
        rerievePhoto(contact, contactName, contactSaveDir);
      }
      
    } else {
      
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getErrorStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      logger.info(LyncConstants.GET_REQUEST_NOT_WORKED);
      logger.info(response.toString());
    }
  }
  
  private void rerievePhoto(JSONObject contact, String contactName,
      String baseDir) throws IOException {
    
    if (contactName != null) {
      
      File source = new File(baseDir, contactName + ".jpg");
      
      if (source.exists()) {
        return;
      }
      
      JSONObject contactLinkObj = contact.getJSONObject(LyncConstants._LINKS);
      
      if (!contactLinkObj.isNull("contactPhoto")) {
        JSONObject contactLinkPhotoObj = contactLinkObj
            .getJSONObject("contactPhoto");
        String contactPhotoUrl = URL_STRING
            + contactLinkPhotoObj.getString("href");
        saveImage(contactPhotoUrl, contactName);
      }
    }
  }
  
  private String getQuery(List<NameValuePair> params)
      throws UnsupportedEncodingException {
    
    StringBuilder result = new StringBuilder();
    boolean first = true;
    
    for (NameValuePair pair : params) {
      if (first) {
        first = false;
      } else {
        result.append("&");
      }
      
      result.append(URLEncoder.encode(pair.getName(), "utf-8"));
      result.append("=");
      result.append(URLEncoder.encode(pair.getValue(), "utf-8"));
    }
    
    return result.toString();
  }
  
  private List<String> getAllContactSearchPatternList() {
    
    List<String> contactList = new ArrayList<>();
    
    String[] namesArr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
        "z"};
    
    String[] namesArr1 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
        "z"};
    
    String[] namesArr2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
        "z"};
    
    String[] namesArr3 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
        "z"};
    
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
