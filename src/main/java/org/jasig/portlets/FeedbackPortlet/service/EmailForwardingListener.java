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
