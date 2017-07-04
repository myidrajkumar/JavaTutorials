package com.rajkumar.java.utils.lync;

public class EmbeddedCommunicationLink implements TargetDomain{
	
	private String embeddedCommunicationLinkSelf;
	private String embeddedCommunicationLinkStartPhoneAudio;
	private String embeddedCommunicationLinkConversations;
	private String embeddedCommunicationLinkStartMessaging;
	private String embeddedCommunicationLinkStartOnlineMeeting;
	private String embeddedCommunicationLinkJoinOnlineMeeting;
	
	public String getEmbeddedCommunicationLinkSelf() {
		return embeddedCommunicationLinkSelf;
	}
	public void setEmbeddedCommunicationLinkSelf(String embeddedCommunicationLinkSelf) {
		this.embeddedCommunicationLinkSelf = embeddedCommunicationLinkSelf;
	}
	public String getEmbeddedCommunicationLinkStartPhoneAudio() {
		return embeddedCommunicationLinkStartPhoneAudio;
	}
	public void setEmbeddedCommunicationLinkStartPhoneAudio(String embeddedCommunicationLinkStartPhoneAudio) {
		this.embeddedCommunicationLinkStartPhoneAudio = embeddedCommunicationLinkStartPhoneAudio;
	}
	public String getEmbeddedCommunicationLinkConversations() {
		return embeddedCommunicationLinkConversations;
	}
	public void setEmbeddedCommunicationLinkConversations(String embeddedCommunicationLinkConversations) {
		this.embeddedCommunicationLinkConversations = embeddedCommunicationLinkConversations;
	}
	public String getEmbeddedCommunicationLinkStartMessaging() {
		return URL_STRING + embeddedCommunicationLinkStartMessaging;
	}
	public void setEmbeddedCommunicationLinkStartMessaging(String embeddedCommunicationLinkStartMessaging) {
		this.embeddedCommunicationLinkStartMessaging = embeddedCommunicationLinkStartMessaging;
	}
	public String getEmbeddedCommunicationLinkStartOnlineMeeting() {
		return embeddedCommunicationLinkStartOnlineMeeting;
	}
	public void setEmbeddedCommunicationLinkStartOnlineMeeting(String embeddedCommunicationLinkStartOnlineMeeting) {
		this.embeddedCommunicationLinkStartOnlineMeeting = embeddedCommunicationLinkStartOnlineMeeting;
	}
	public String getEmbeddedCommunicationLinkJoinOnlineMeeting() {
		return embeddedCommunicationLinkJoinOnlineMeeting;
	}
	public void setEmbeddedCommunicationLinkJoinOnlineMeeting(String embeddedCommunicationLinkJoinOnlineMeeting) {
		this.embeddedCommunicationLinkJoinOnlineMeeting = embeddedCommunicationLinkJoinOnlineMeeting;
	}

}
