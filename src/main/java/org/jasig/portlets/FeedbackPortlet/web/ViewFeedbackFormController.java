/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
package org.jasig.portlets.FeedbackPortlet.web;

import java.text.DateFormat;
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
    
    long millisIn30Days = (60000L * 60L * 24L * 30L); // 60*1000 millis = 1 min;  60 min = 1 hour; 24 h = 1 day; 30 days
    
    private String datePickerFormat = "dateformat-m-sl-d-sl-Y"; // used for defining date format in javascript date picker
    private String dateFormat = "MM/dd/yyyy";  // used to define the format in the java date formatter (to parse the date picker)
    
    
    
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
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        Date startDisplayDate = dateFormatter.parse(form.getStartDisplayDate());
        if (startDisplayDate != null && !startDisplayDate.equals(""))
        {
            session.setAttribute("startDisplayDate", startDisplayDate);
        }
        else
        {
            session.removeAttribute("startDisplayDate");
        }
        
        
        Date endDisplayDate = dateFormatter.parse(form.getEndDisplayDate());
        if (startDisplayDate != null && !startDisplayDate.equals(""))
        {
            session.setAttribute("endDisplayDate", endDisplayDate);
        }
        else
        {
            session.removeAttribute("endDisplayDate");
        }
        
        
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
            session.setAttribute("startDisplayDate", new Date(System.currentTimeMillis() - millisIn30Days) );// defaults to 30 days ago
            session.setAttribute("endDisplayDate", new Date(System.currentTimeMillis()) ); 
            session.setAttribute("datePickerFormat", datePickerFormat);
        }

        // get the desired restriction information from the session
        int startNum = (Integer) session.getAttribute("start");
        int itemNum = (Integer) session.getAttribute("items");
        String feedback = (String) session.getAttribute("feedbacktype");
        String role = (String) session.getAttribute("userrole");
        Date startDisplayDate = (Date) session.getAttribute("startDisplayDate");  // these are both set in 'parseCurrentDateRange'
        Date endDisplayDate = (Date) session.getAttribute("endDisplayDate");
        
        String start = request.getParameter("start");

        if (startDisplayDate == null) {
            log.debug("There has been some kind of error causing the display date to be null.  Correcting"); 
            startDisplayDate = new Date(); 
        }
        if (endDisplayDate == null){
            log.debug("There has been some kind of error causing the display date to be null.  Correcting"); 
            endDisplayDate = new Date(); 
        }
        parseCurrentDateRange(request, startDisplayDate, endDisplayDate); // moved to private method to clean up code
        
        
        if (start != null && !start.equals("")) {
            startNum = Integer.parseInt(start);
            if (startNum < 0) { startNum = 0; }
            session.setAttribute("start", startNum);
        }
        
        List<FeedbackItem> theFeedbackItems = feedbackStore.getFeedback(startNum, itemNum, role, feedback, startDisplayDate, endDisplayDate);
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        // get the feedback items
        // model.put("feedback", feedbackStore.getFeedback(startNum, itemNum, role, feedback));
        model.put("feedback", theFeedbackItems);
        model.put("userrole", role);
        model.put("feedbacktype", feedback);// 'feedback' is defined by getting 'feedbackType', not to be confused with the feedback itself

        // get the overall statistics for the feedback data
        model.put("stats", feedbackStore.getStats());
        
        // get the statistics for the feedback data separated by user role
        model.put("overallstats", feedbackStore.getStatsByRole());
        
        model.put("start", startNum);
        model.put("items", itemNum);
        model.put("totalItems", feedbackStore.getFeedbackTotal(role, feedback, startDisplayDate, endDisplayDate));
        
        // Date format required to parse user input dates
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        model.put("startDisplayDate",dateFormatter.format(startDisplayDate) );
        model.put("endDisplayDate", dateFormatter.format(endDisplayDate) );
        model.put("datePickerFormat", datePickerFormat);

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
    
    /*
     * clean code practices might suggest any noise blocks of code like if-else blocks are best moved to seperate methods
     * Seperated for ease of reading
     * 
     * This attempts to pull the time form the requests given by the user, if it doesn't work it just uses the default
     */
    private void parseCurrentDateRange(RenderRequest request, Date startDisplayDate, Date endDisplayDate)
    {
        // Date format required to parse user input dates
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        String startDisplay = request.getParameter("startDisplayDate");
        String endDisplay = request.getParameter("endDisplayDate");
        if (startDisplay == null) // goes left to right, so if null '.isEmpty()' shouldn't fire
        {
            //startDisplayDate.setTime(System.currentTimeMillis() - millisIn30Days);
            log.debug("the render request failed to return anything for the date filter.  This may be normal");
        }
        else if (startDisplay != null && !startDisplay.isEmpty())
        { 
            long time;
            try {
                time = dateFormatter.parse(startDisplay).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                time = System.currentTimeMillis();
            }
            startDisplayDate.setTime(time);   // I have to set the time this way so it carries out of the method
        }
        if (endDisplay == null) 
        {
            //endDisplayDate.setTime(System.currentTimeMillis());
            log.debug("the render request failed to return anything for the date filter.  This may be normal");
        }
        else if (endDisplay != null && !endDisplay.isEmpty())
        {
            long time;
            try {
                time = dateFormatter.parse(endDisplay).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                time = System.currentTimeMillis();
            }
            endDisplayDate.setTime(time);
        }
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