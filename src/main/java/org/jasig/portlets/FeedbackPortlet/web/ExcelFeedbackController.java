package org.jasig.portlets.FeedbackPortlet.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ExcelFeedbackController extends AbstractController {


	private static Log log = LogFactory.getLog(ExcelFeedbackController.class);
	private FeedbackStore feedbackStore;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("feedback", feedbackStore.getFeedback());
		
		return new ModelAndView("viewExcelFeedback", "model", model);

	}

	public void setFeedbackStore(FeedbackStore feedbackStore) {
		this.feedbackStore = feedbackStore;
	}


}
