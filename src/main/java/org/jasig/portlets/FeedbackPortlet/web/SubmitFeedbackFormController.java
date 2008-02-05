package org.jasig.portlets.FeedbackPortlet.web;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.jasig.portlets.FeedbackPortlet.PortletUserPropertiesResolver;
import org.jasig.portlets.FeedbackPortlet.UserProperties;
import org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore;
import org.jasig.portlets.FeedbackPortlet.service.FeedbackSubmissionListener;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.mvc.SimpleFormController;

public class SubmitFeedbackFormController extends SimpleFormController {

	private static Log log = LogFactory.getLog(SubmitFeedbackFormController.class);
	private FeedbackStore feedbackStore;
	
	public SubmitFeedbackFormController() {
		setCommandName("prefs");
		setCommandClass(SubmitFeedbackForm.class);
	}

	protected void processFormSubmission(ActionRequest request,
			ActionResponse response, Object command, BindException errors) {
		
		// get the form data
		SubmitFeedbackForm form = (SubmitFeedbackForm) command;
		
		// put the form data into a new feedback object
		FeedbackItem feedback = new FeedbackItem();
		feedback.setFeedback(form.getFeedback());
		feedback.setUseragent(form.getUseragent());
		feedback.setFeedbacktype(form.getLike());
		feedback.setTabname(form.getTabname());
		
		// add information about the user to the feedback object
		if (request.getRemoteUser() != null) {
			UserProperties user = userPropertiesResolver.getProperties(request);
			feedback.setUserrole(user.getUserrole());
			if (form.getAnonymous() == null) {
				feedback.setUserid(request.getRemoteUser());
				feedback.setUsername(user.getUsername());
				feedback.setUseremail(user.getUseremail());
			}
		}
		for (FeedbackSubmissionListener listener : feedbackSubmissionListeners) {
			listener.performAction(feedback);
		}
		
		if (log.isDebugEnabled())
			log.debug("created new feedback item " + feedback.toString());
		
		feedbackStore.storeFeedback(feedback);
		
	}

	public void setFeedbackStore(FeedbackStore feedbackStore) {
		this.feedbackStore = feedbackStore;
	}

	private PortletUserPropertiesResolver userPropertiesResolver;
	public void setUserPropertiesResolver(PortletUserPropertiesResolver resolver) {
		this.userPropertiesResolver = resolver;
	}
	
	private List<FeedbackSubmissionListener> feedbackSubmissionListeners;
	public void setFeedbackSubmissionListeners(List<FeedbackSubmissionListener> listeners) {
		this.feedbackSubmissionListeners = listeners;
	}

}
