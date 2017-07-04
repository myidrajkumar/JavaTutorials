package com.rajkumar.java.utils.lync;

public class EmbeddedMeLink implements TargetDomain{
	
	private String embeddedMeLinkSelf;
	private String embeddedMeLinkMakeMeAvailable;
	private String embeddedMeLinkCallForwardingSettings;
	private String embeddedMeLinkPhones;
	private String embeddedMeLinkPhoto;
	private String embeddedMeLinkNote;
	private String embeddedMeLinkPresence;
	private String embeddedMeLinkLocation;
	private String embeddedMeLinkReportMyActivity;
	
	public String getEmbeddedMeLinkNote() {
		return TargetDomain.URL_STRING + embeddedMeLinkNote;
	}
	public void setEmbeddedMeLinkNote(String embeddedMeLinkNote) {
		this.embeddedMeLinkNote = embeddedMeLinkNote;
	}
	public String getEmbeddedMeLinkPresence() {
		return TargetDomain.URL_STRING +  embeddedMeLinkPresence;
	}
	public void setEmbeddedMeLinkPresence(String embeddedMeLinkPresence) {
		this.embeddedMeLinkPresence = embeddedMeLinkPresence;
	}
	public String getEmbeddedMeLinkLocation() {
		return embeddedMeLinkLocation;
	}
	public void setEmbeddedMeLinkLocation(String embeddedMeLinkLocation) {
		this.embeddedMeLinkLocation = embeddedMeLinkLocation;
	}
	public String getEmbeddedMeLinkReportMyActivity() {
		return embeddedMeLinkReportMyActivity;
	}
	public void setEmbeddedMeLinkReportMyActivity(String embeddedMeLinkReportMyActivity) {
		this.embeddedMeLinkReportMyActivity = embeddedMeLinkReportMyActivity;
	}
	
	public String getEmbeddedMeLinkSelf() {
		return embeddedMeLinkSelf;
	}
	public void setEmbeddedMeLinkSelf(String embeddedMeLinkSelf) {
		this.embeddedMeLinkSelf = embeddedMeLinkSelf;
	}
	public String getEmbeddedMeLinkMakeMeAvailable() {
		return TargetDomain.URL_STRING + embeddedMeLinkMakeMeAvailable;
	}
	public void setEmbeddedMeLinkMakeMeAvailable(String embeddedMeLinkMakeMeAvailable) {
		this.embeddedMeLinkMakeMeAvailable = embeddedMeLinkMakeMeAvailable;
	}
	public String getEmbeddedMeLinkCallForwardingSettings() {
		return embeddedMeLinkCallForwardingSettings;
	}
	public void setEmbeddedMeLinkCallForwardingSettings(String embeddedMeLinkCallForwardingSettings) {
		this.embeddedMeLinkCallForwardingSettings = embeddedMeLinkCallForwardingSettings;
	}
	public String getEmbeddedMeLinkPhones() {
		return embeddedMeLinkPhones;
	}
	public void setEmbeddedMeLinkPhones(String embeddedMeLinkPhones) {
		this.embeddedMeLinkPhones = embeddedMeLinkPhones;
	}
	public String getEmbeddedMeLinkPhoto() {
		return TargetDomain.URL_STRING + embeddedMeLinkPhoto;
	}
	public void setEmbeddedMeLinkPhoto(String embeddedMeLinkPhoto) {
		this.embeddedMeLinkPhoto = embeddedMeLinkPhoto;
	}

}
