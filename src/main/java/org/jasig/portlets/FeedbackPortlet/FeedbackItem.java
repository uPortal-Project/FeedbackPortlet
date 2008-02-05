package org.jasig.portlets.FeedbackPortlet;

import java.io.Serializable;
import java.util.Date;

public class FeedbackItem implements Serializable {
    private static final long serialVersionUID = 1L;

	private long id = -1;
	private String userid;
	private String username;
	private String useremail;
	private String useragent;
	private Date submissiontime = new Date();
	private String feedback;
	private String tabname = "Academics";
	private String userrole = "Staff";
    private String feedbacktype = FeedbackItem.MAYBE;
    public static String MAYBE = "MAYBE";
    public static String YES = "YES";
    public static String NO = "NO";

	public FeedbackItem() { }
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("user: " + this.userid);
		buf.append(", feedbacktype: " + this.feedbacktype);
		buf.append(", feedback: " + this.feedback);
		return buf.toString();
	}
	
	public FeedbackItem(long id, String userid, String username,
			String useremail, String useragent, Date submissiontime,
			String feedbacktype, String feedback, String tabname, String role) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.useragent = useragent;
		this.submissiontime = submissiontime;
		this.feedbacktype = feedbacktype;
		this.feedback = feedback;
		this.tabname = tabname;
		this.userrole = role;
	}
	
	public String getBrowserAbbreviation() {
		if (this.useragent == null)
			return "?";
		else if (this.useragent.contains("MSIE"))
			return "IE";
		else if (this.useragent.contains("Firefox"))
			return "FF";
		else if (this.useragent.contains("AppleWebKit"))
			return "SA";
		else if (this.useragent.contains("Opera"))
			return "OP";
		else
			return "?";
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUseragent() {
		return useragent;
	}
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	public Date getSubmissiontime() {
		return submissiontime;
	}
	public void setSubmissiontime(Date submissiontime) {
		this.submissiontime = submissiontime;
	}
	public String getFeedbacktype() {
		return feedbacktype;
	}
	public void setFeedbacktype(String feedbacktype) {
		this.feedbacktype = feedbacktype;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getTabname() {
		return tabname;
	}
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String role) {
		this.userrole = role;
	}
	
	

}
