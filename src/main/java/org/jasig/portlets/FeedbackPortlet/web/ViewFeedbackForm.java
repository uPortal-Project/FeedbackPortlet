package org.jasig.portlets.FeedbackPortlet.web;

public class ViewFeedbackForm {
	
	private int items = 50;
	private String userrole;
	private String feedbacktype;
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getFeedbacktype() {
		return feedbacktype;
	}
	public void setFeedbacktype(String feedbacktype) {
		this.feedbacktype = feedbacktype;
	}

	
}
