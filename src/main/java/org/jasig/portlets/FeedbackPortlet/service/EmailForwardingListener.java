/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
package org.jasig.portlets.FeedbackPortlet.service;

import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

public class EmailForwardingListener implements FeedbackSubmissionListener {

	public void performAction(FeedbackItem item) {
		
		// only forward on email with comments
		if (item.getFeedback() == null || item.getFeedback().equals(""))
			return;

		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		
		// set the user's email address as the from and reply to
		if (item.getUseremail() != null && !item.getUseremail().equals("")) {
			message.setFrom(item.getUseremail());
			message.setReplyTo(item.getUseremail());
		}
		
		// construct the message text
		String text = message.getText();
		if (item.getUsername() != null && !item.getUsername().equals(""))
			text = StringUtils.replace(text, "%USERNAME%", item.getUsername());
		else
			text = StringUtils.replace(text, "%USERNAME%", "Anonymous");
			
		if (item.getUserrole() != null && !item.getUserrole().equals(""))
			text = StringUtils.replace(text, "%USERROLE%", item.getUserrole());
		else
			text = StringUtils.replace(text, "%USERROLE%", "unknown");

		text = StringUtils.replace(text, "%USERAGENT%", item.getUseragent());
		
		if (item.getTabname() != null && !item.getTabname().equals(""))
			text = StringUtils.replace(text, "%TABNAME%", item.getTabname());
		else
			text = StringUtils.replace(text, "%TABNAME%", "none");
		
		text = StringUtils.replace(text, "%FEEDBACKTYPE%", item.getFeedbacktype());
		text = StringUtils.replace(text, "%FEEDBACK%", item.getFeedback());
		message.setText(text);

		mailSender.send(message);
	
	}
	
	private SimpleMailMessage mailMessage;
	private MailSender mailSender;
	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}


/*
 * EmailForwardingListener.java
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