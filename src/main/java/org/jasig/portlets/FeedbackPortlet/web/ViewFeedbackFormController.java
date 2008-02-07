/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
package org.jasig.portlets.FeedbackPortlet.web;

import java.util.HashMap;
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
import org.springframework.validation.BindException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.SimpleFormController;

/**
 * ViewFeedbackFormController allows an administrator to view feedback entries
 * and summary statistics describing the feedback items in the data store.  The
 * view also allows admins to restrict data being viewed to particular user roles
 * or feedback types, as well as page through large amounts of feedback items.
 *
 * @author Jen Bourey
 */
public class ViewFeedbackFormController extends SimpleFormController {

	private static Log log = LogFactory.getLog(ViewFeedbackFormController.class);
	
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

		// update the requested user role
		String role = form.getUserrole();
		if (role != null && !role.equals(""))
			session.setAttribute("userrole", role);
		else 
			session.removeAttribute("userrole");
		
		// update the requested feedback type
		String type = form.getFeedbacktype();
		if (type != null && !type.equals(""))
			session.setAttribute("feedbacktype", type);
		else
			session.removeAttribute("feedbacktype");
		
		// update the requested number of items
		Integer items = form.getItems();
		if (items > 0)
			session.setAttribute("items", items);
		else
			session.removeAttribute("items");
		
		// reset the start index
		session.setAttribute("start", 0);

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
		
		// if this is the first request, initialize the restriction information
		if (session.getAttribute("initialized") == null) {
			session.setAttribute("initialized", true);
			session.setAttribute("start", 0);
			session.setAttribute("items", 50);
		}

		// get the desired restriction information from the session
		int startNum = (Integer) session.getAttribute("start");
		int itemNum = (Integer) session.getAttribute("items");
		String feedback = (String) session.getAttribute("feedbacktype");
		String role = (String) session.getAttribute("userrole");
		String start = request.getParameter("start");
		if (start != null && !start.equals("")) {
			startNum = Integer.parseInt(start);
			if (startNum < 0) startNum = 0;
			session.setAttribute("start", startNum);
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		// get the feedback items
		model.put("feedback", feedbackStore.getFeedback(startNum, itemNum, role, feedback));

		// get the overall statistics for the feedback data
		model.put("stats", feedbackStore.getStats());
		
		// get the statistics for the feedback data separated by user role
		model.put("overallstats", feedbackStore.getStatsByRole());
		
		model.put("start", startNum);
		model.put("items", itemNum);
		model.put("totalItems", feedbackStore.getFeedbackTotal(role, feedback));

		request.setAttribute(getCommandName(), new ViewFeedbackForm());
		
		return new ModelAndView("/viewFeedback", "model", model);
		
	}
	
	@Override
	protected ModelAndView onSubmitRender(RenderRequest request,
			RenderResponse response, Object command, BindException errors)
			throws Exception {
		return showForm(request, response, errors);
	}

	private FeedbackStore feedbackStore;
	public void setFeedbackStore(FeedbackStore feedbackStore) {
		this.feedbackStore = feedbackStore;
	}
	
}


/*
 * ViewFeedbackFormController.java
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