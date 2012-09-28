/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlets.FeedbackPortlet;

import java.io.Serializable;
import java.util.Date;

/**
 * FeedbackItem represents a single feedback submission.  The object contains 
 * the feedback itself, as well as information about the submitting user.
 * 
 * @author Jen Bourey
 */
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
    
    /**
     * Undecided feedback
     */
    public static String MAYBE = "MAYBE";
    
    /**
     * Positive feedback
     */
    public static String YES = "YES";
    
    /**
     * Negative feedback
     */
    public static String NO = "NO";

    /**
     * Default constructor
     */
	public FeedbackItem() { }
	
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
	
	/**
	 * Constructs a two-letter abbreviation for popular browser user agent
	 * strings.
	 * 
	 * @return two-letter abbreviation
	 */
	public String getBrowserAbbreviation() {
		if (this.useragent == null)
			return "?";
		else if (this.useragent.contains("MSIE"))
			return "IE";
		else if (this.useragent.contains("Firefox"))
			return "FF";
        else if (this.useragent.contains("Chrome"))
            return "CR";
		else if (this.useragent.contains("AppleWebKit"))
			return "SA";
		else if (this.useragent.contains("Opera"))
			return "OP";
		else
			return "?";
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("user: " + this.userid);
		buf.append(", feedbacktype: " + this.feedbacktype);
		buf.append(", feedback: " + this.feedback);
		return buf.toString();
	}
	
	/**
	 * Get the unique ID.
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Set the unique ID.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Get the submitting user's unique user id.
	 * 
	 * @return
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * Set the submitting user's unique user id.
	 * 
	 * @param userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * Get the submitting user's full name.
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set the submitting user's full name.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get the submitting user's email address.
	 * 
	 * @return
	 */
	public String getUseremail() {
		return useremail;
	}
	
	/**
	 * Set the submitting user's email address.
	 * 
	 * @param useremail
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	/**
	 * Get the user agent for the browser from which 
	 * the feedback was submitted. 
	 * 
	 * @return
	 */
	public String getUseragent() {
		return useragent;
	}
	
	/**
	 * Set the user agent for the browser from which 
	 * the feedback was submitted. 
	 * 
	 * @param useragent
	 */
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	
	/**
	 * Get the time this feedback item was submitted.
	 * 
	 * @return
	 */
	public Date getSubmissiontime() {
		return submissiontime;
	}
	
	/**
	 * Set the time this feedback item was submitted.
	 * 
	 * @param submissiontime
	 */
	public void setSubmissiontime(Date submissiontime) {
		this.submissiontime = submissiontime;
	}
	
	/**
	 * Get the type of this feedback (positive, negative,
	 * or undecided).
	 * 
	 * @return
	 */
	public String getFeedbacktype() {
		return feedbacktype;
	}
	
    /**
     * Set the type of this feedback (positive, negative,
     * or undecided).  <code>null</code> and/or nonsense values count as 
     * undecided.
     * 
     * @param feedbacktype
     */
    public void setFeedbacktype(String feedbacktype) {
        // Must be certain it's a value we can process...
        if (YES.equalsIgnoreCase(feedbacktype)) {
            this.feedbacktype = YES;
        } else if (NO.equalsIgnoreCase(feedbacktype)) {
            this.feedbacktype = NO;
        } else {
            // Anything else counts as MAYBE
            this.feedbacktype = MAYBE;
        }
    }
	
	/**
	 * Get the comments submitted by the user.
	 * 
	 * @return
	 */
	public String getFeedback() {
		return feedback;
	}
	
	/**
	 * Set the comments submitted by the user.
	 * 
	 * @param feedback
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	/**
	 * Get the name of the tab the user was visiting during 
	 * this feedback submission.
	 * 
	 * @return
	 */
	public String getTabname() {
		return tabname;
	}
	
	/**
	 * Set the name of the tab the user was visiting during this
	 * feedback submission.
	 * 
	 * @param tabname
	 */
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	
	/**
	 * Get the user's role.
	 * 
	 * @return
	 */
	public String getUserrole() {
		return userrole;
	}
	
	/**
	 * Set the user's role.
	 * 
	 * @param role
	 */
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