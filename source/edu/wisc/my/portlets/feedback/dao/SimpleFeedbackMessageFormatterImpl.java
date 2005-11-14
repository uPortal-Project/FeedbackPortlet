/**
 * 
 */
package edu.wisc.my.portlets.feedback.dao;

import org.springframework.mail.SimpleMailMessage;

import edu.wisc.my.portlets.feedback.beans.Feedback;

/**
 * Basic IFeedbackMessageFormatter implementation
 * Sends name, netid, email, phone, details
 * 
 * @author nblair@doit.wisc.edu
 * @version $Header$
 */
public class SimpleFeedbackMessageFormatterImpl implements
		IFeedbackMessageFormatter {

	// recipient address
    private String targetEmail;
    // from address - so bounces can be properly handled
    private String fromAddress;
    /**
     * @param fromAddress The fromAddress to set.
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    /**
     * @param targetEmail The targetEmail to set.
     */
    public void setTargetEmail(String targetEmail) {
        this.targetEmail = targetEmail;
    }
    
	/* (non-Javadoc)
	 * @see edu.wisc.my.portlets.feedback.dao.IFeedbackMessageFormatter#format(edu.wisc.my.portlets.feedback.beans.Feedback)
	 */
	public SimpleMailMessage format(Feedback feedback) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(targetEmail);
		message.setFrom(fromAddress);
		        
		message.setSubject("[Feedback] " + feedback.getSubject());
		         
		StringBuffer details = new StringBuffer();
		details.append("NAME: ");
		details.append(null == feedback.getName() ?  "<empty>" : feedback.getName());
		details.append("\n");
		details.append("\n");
		        
		details.append("NETID: ");
		details.append(null == feedback.getNetid() ? "<empty>" : feedback.getNetid());
		details.append("\n");
		details.append("\n");
		
		details.append("EMAIL: ");
		details.append(null == feedback.getEmailAddress() ? "<empty>" : feedback.getEmailAddress());
		details.append("\n");
		details.append("\n");
		         
		details.append("PHONE: ");
		details.append(null == feedback.getPhoneNumber() ? "<empty>" : feedback.getPhoneNumber());
		details.append("\n");
		details.append("\n");
		       
		details.append("DETAILS: ");
		details.append(feedback.getDetails());
       
		message.setText(details.toString());
		
		return message;
	}

}
