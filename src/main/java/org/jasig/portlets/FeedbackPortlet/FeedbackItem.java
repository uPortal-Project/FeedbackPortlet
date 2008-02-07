/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
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


/*
 * FeedbackItem.java
 * 
 * Copyright (c) Feb 5, 2008 Yale University. All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * YALE UNIVERSITY OR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 * and this list of conditions in any related documentation and, if feasible, in
 * the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 * software developed by Yale University," in any related documentation and, if
 * feasible, in the redistributed software.
 * 
 * 3. The names "Yale" and "Yale University" must not be used to endorse or
 * promote products derived from this software.
 */