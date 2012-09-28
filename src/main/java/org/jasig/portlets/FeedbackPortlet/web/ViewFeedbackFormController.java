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
package org.jasig.portlets.FeedbackPortlet.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.jasig.portlets.FeedbackPortlet.FeedbackQueryParameters;
import org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Controller
@RequestMapping("VIEW")
public class ViewFeedbackFormController {

    private static Log log = LogFactory.getLog(ViewFeedbackFormController.class);
    
    public static final long MILLIS_IN_A_DAY = (60000L * 60L * 24L); // 60000 millis to a min, 60 min to hour. 24 /day, -1 millisecond to be end of day 
    private static final long MILLIS_IN_30_DAYS = (60000L * 60L * 24L * 30L); // 60*1000 millis = 1 min;  60 min = 1 hour; 24 h = 1 day; 30 days
    
    private static final String DATEPICKER_FORMAT = "dateformat-m-sl-d-sl-Y"; // used for defining date format in javascript date picker
    // this format of 'month-slash-day-slash-year' should match the dateFormat "Month/Day/Year" in the FeedbackQueryParameter

    
    
    
    /*public ViewFeedbackFormController() {
        setCommandName("viewFeedbackForm");
        setCommandClass(ViewFeedbackForm.class);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    protected void onSubmitAction(ActionRequest request, ActionResponse response, 
            @ModelAttribute("viewFeedbackForm") ViewFeedbackForm form)
            throws Exception {
        
        PortletSession session = request.getPortletSession();
        
        FeedbackQueryParameters queryParameters = (FeedbackQueryParameters) session.getAttribute("feedbackQueryParameters", session.APPLICATION_SCOPE);
        // gather the appropriate data from the form
        queryParameters.updateParametersWithForm(form);
        session.setAttribute("feedbackQueryParameters", queryParameters, session.APPLICATION_SCOPE);


    }

    
    /*@Override
    protected Object formBackingObject(PortletRequest request) throws Exception {
        log.debug("backing object");
        ViewFeedbackForm form = new ViewFeedbackForm();
        return form;
    }*/

    
    @RequestMapping
    protected ModelAndView showForm(RenderRequest request)
            throws Exception {

        PortletSession session = request.getPortletSession();
        
        
        // if this is the first request, initialize the restriction information
        if (session.getAttribute("feedbackQueryParameters", session.APPLICATION_SCOPE) == null) {
            session.setAttribute("feedbackQueryParameters", new FeedbackQueryParameters(), session.APPLICATION_SCOPE);
        }
        
        FeedbackQueryParameters queryParameters = (FeedbackQueryParameters) session.getAttribute("feedbackQueryParameters", session.APPLICATION_SCOPE);

        int startNum;
        // this seems to overwrites whatever attribute is in session with what is in request.
        String start = request.getParameter("start");
        if (start != null && !start.equals("")) {
            startNum = Integer.parseInt(start);
            if (startNum < 0) { startNum = 0; }
            session.setAttribute("start", startNum);
            queryParameters.setPagingStart(startNum);
        }
        List<FeedbackItem> theFeedbackItems = feedbackStore.getFeedback(queryParameters); 
        Map<String, Object> model = new HashMap<String, Object>();
        model.putAll(queryParameters.putIntoModelMap());
        model.put("totalItems", feedbackStore.getFeedbackTotal(queryParameters));
        // get the overall statistics for the feedback data
        model.put("stats", feedbackStore.getStats());
        
        // get the statistics for the feedback data separated by user role
        model.put("overallstats", feedbackStore.getStatsByRole());
        model.put("feedback", theFeedbackItems);
        model.put("datePickerFormat", DATEPICKER_FORMAT); // puts in the date picker format for use by the date picker.
        request.setAttribute("viewFeedbackForm", new ViewFeedbackForm());
        model.put("viewFeedbackForm", new ViewFeedbackForm());
        
        return new ModelAndView("/viewFeedback", model);
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