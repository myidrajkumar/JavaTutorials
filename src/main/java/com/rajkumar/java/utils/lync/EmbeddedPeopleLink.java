package com.rajkumar.java.utils.lync;

public class EmbeddedPeopleLink implements TargetDomain{
	
	private String embeddedPeopleLinkSelf;
	private String embeddedPeopleLinkPresenceSubscriptions;
	private String embeddedPeopleLinkSubscribedContacts;
	private String embeddedPeopleLinkPresenceSubscriptionMemberships;
	private String embeddedPeopleLinkMyGroups;
	private String embeddedPeopleLinkMyGroupMemberships;
	private String embeddedPeopleLinkMyContacts;
	private String embeddedPeopleLinkMyPrivacyRelationships;
	private String embeddedPeopleLinkMyContactsAndGroupsSubscription;
	private String embeddedPeopleLinkSearch;
	
	public String getEmbeddedPeopleLinkSelf() {
		return URL_STRING + embeddedPeopleLinkSelf;
	}
	public void setEmbeddedPeopleLinkSelf(String embeddedPeopleLinkSelf) {
		this.embeddedPeopleLinkSelf = embeddedPeopleLinkSelf;
	}
	public String getEmbeddedPeopleLinkPresenceSubscriptions() {
		return embeddedPeopleLinkPresenceSubscriptions;
	}
	public void setEmbeddedPeopleLinkPresenceSubscriptions(String embeddedPeopleLinkPresenceSubscriptions) {
		this.embeddedPeopleLinkPresenceSubscriptions = embeddedPeopleLinkPresenceSubscriptions;
	}
	public String getEmbeddedPeopleLinkSubscribedContacts() {
		return embeddedPeopleLinkSubscribedContacts;
	}
	public void setEmbeddedPeopleLinkSubscribedContacts(String embeddedPeopleLinkSubscribedContacts) {
		this.embeddedPeopleLinkSubscribedContacts = embeddedPeopleLinkSubscribedContacts;
	}
	public String getEmbeddedPeopleLinkPresenceSubscriptionMemberships() {
		return embeddedPeopleLinkPresenceSubscriptionMemberships;
	}
	public void setEmbeddedPeopleLinkPresenceSubscriptionMemberships(
			String embeddedPeopleLinkPresenceSubscriptionMemberships) {
		this.embeddedPeopleLinkPresenceSubscriptionMemberships = embeddedPeopleLinkPresenceSubscriptionMemberships;
	}
	public String getEmbeddedPeopleLinkMyGroups() {
		return URL_STRING + embeddedPeopleLinkMyGroups;
	}
	public void setEmbeddedPeopleLinkMyGroups(String embeddedPeopleLinkMyGroups) {
		this.embeddedPeopleLinkMyGroups = embeddedPeopleLinkMyGroups;
	}
	public String getEmbeddedPeopleLinkMyGroupMemberships() {
		return URL_STRING + embeddedPeopleLinkMyGroupMemberships;
	}
	public void setEmbeddedPeopleLinkMyGroupMemberships(String embeddedPeopleLinkMyGroupMemberships) {
		this.embeddedPeopleLinkMyGroupMemberships = embeddedPeopleLinkMyGroupMemberships;
	}
	public String getEmbeddedPeopleLinkMyContacts() {
		return URL_STRING + embeddedPeopleLinkMyContacts;
	}
	public void setEmbeddedPeopleLinkMyContacts(String embeddedPeopleLinkMyContacts) {
		this.embeddedPeopleLinkMyContacts = embeddedPeopleLinkMyContacts;
	}
	public String getEmbeddedPeopleLinkMyPrivacyRelationships() {
		return URL_STRING + embeddedPeopleLinkMyPrivacyRelationships;
	}
	public void setEmbeddedPeopleLinkMyPrivacyRelationships(String embeddedPeopleLinkMyPrivacyRelationships) {
		this.embeddedPeopleLinkMyPrivacyRelationships = embeddedPeopleLinkMyPrivacyRelationships;
	}
	public String getEmbeddedPeopleLinkMyContactsAndGroupsSubscription() {
		return embeddedPeopleLinkMyContactsAndGroupsSubscription;
	}
	public void setEmbeddedPeopleLinkMyContactsAndGroupsSubscription(
			String embeddedPeopleLinkMyContactsAndGroupsSubscription) {
		this.embeddedPeopleLinkMyContactsAndGroupsSubscription = embeddedPeopleLinkMyContactsAndGroupsSubscription;
	}
	public String getEmbeddedPeopleLinkSearch() {
		return URL_STRING + embeddedPeopleLinkSearch;
	}
	public void setEmbeddedPeopleLinkSearch(String embeddedPeopleLinkSearch) {
		this.embeddedPeopleLinkSearch = embeddedPeopleLinkSearch;
	}

}
