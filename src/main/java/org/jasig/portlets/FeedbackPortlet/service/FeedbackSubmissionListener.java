package org.jasig.portlets.FeedbackPortlet.service;

import org.jasig.portlets.FeedbackPortlet.FeedbackItem;

public interface FeedbackSubmissionListener {
	
	public void performAction(FeedbackItem item);

}
