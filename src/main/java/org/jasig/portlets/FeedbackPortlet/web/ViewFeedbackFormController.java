package org.jasig.portlets.FeedbackPortlet.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore;
import org.jasig.portlets.FeedbackPortlet.service.FeedbackSubmissionListener;
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.SimpleFormController;

public class ViewFeedbackFormController extends SimpleFormController {

	private static Log log = LogFactory.getLog(ViewFeedbackFormController.class);
	private FeedbackStore feedbackStore;
	
	public ViewFeedbackFormController() {
		setCommandName("viewFeedbackForm");
		setCommandClass(ViewFeedbackForm.class);
	}

	@Override
	protected void onSubmitAction(ActionRequest request,
			ActionResponse response, Object command, BindException errors)
			throws Exception {
		
		PortletSession session = request.getPortletSession();
		ViewFeedbackForm form = (ViewFeedbackForm) command;
		String role = form.getUserrole();
		if (role != null && !role.equals(""))
			session.setAttribute("userrole", role);
		else 
			session.removeAttribute("userrole");
		String type = form.getFeedbacktype();
		if (type != null && !type.equals(""))
			session.setAttribute("feedbacktype", type);
		else
			session.removeAttribute("feedbacktype");
		Integer items = form.getItems();
		if (items > 0)
			session.setAttribute("items", items);
		else
			session.removeAttribute("items");
		session.setAttribute("start", 0);
		log.debug("evaluated form");
	}

	
	@Override
	protected Object formBackingObject(PortletRequest request) throws Exception {
		log.debug("backing object");
		ViewFeedbackForm form = new ViewFeedbackForm();
		return form;
	}

	
	@Override
	protected ModelAndView showForm(RenderRequest request,
			RenderResponse response, BindException errors, Map controlModel)
			throws Exception {

		PortletSession session = request.getPortletSession();
		if (session.getAttribute("initialized") == null) {
			session.setAttribute("initialized", true);
			session.setAttribute("start", 0);
			session.setAttribute("items", 50);
		}

		int startNum = (Integer) session.getAttribute("start");
		int itemNum = (Integer) session.getAttribute("items");
		String feedback = (String) session.getAttribute("feedbacktype");
		String role = (String) session.getAttribute("userrole");
		log.debug("start: " + startNum + ", items: " + itemNum + ", feedback: " + feedback + ", role: " + role);
		
		String start = request.getParameter("start");
		if (start != null && !start.equals("")) {
			startNum = Integer.parseInt(start);
			if (startNum < 0) startNum = 0;
			session.setAttribute("start", startNum);
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("feedback", feedbackStore.getFeedback(startNum, itemNum, role, feedback));
		model.put("stats", feedbackStore.getStats());
		model.put("overallstats", feedbackStore.getStatsByRole());
		model.put("start", startNum);
		model.put("items", itemNum);
		model.put("totalItems", feedbackStore.getFeedbackTotal(role, feedback));
		log.debug(request.getAttribute(getCommandName()));
		log.debug("showing form");
		request.setAttribute(getCommandName(), new ViewFeedbackForm());
		
		return new ModelAndView("/viewFeedback", "model", model);
		
	}
	
	@Override
	protected ModelAndView onSubmitRender(RenderRequest request,
			RenderResponse response, Object command, BindException errors)
			throws Exception {
		log.debug("rendering submit");
		return showForm(request, response, errors);
	}

	public void setFeedbackStore(FeedbackStore feedbackStore) {
		this.feedbackStore = feedbackStore;
	}
	
}
