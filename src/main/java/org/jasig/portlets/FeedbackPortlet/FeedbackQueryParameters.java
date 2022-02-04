/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlets.FeedbackPortlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jasig.portlets.FeedbackPortlet.dao.*;
import org.jasig.portlets.FeedbackPortlet.web.ViewFeedbackForm;
import org.jasig.portlets.FeedbackPortlet.*;

public class FeedbackQueryParameters {

    private static final Logger log = LoggerFactory.getLogger(FeedbackQueryParameters.class);

    public static final long MILLIS_IN_A_DAY = (60000L * 60L * 24L); // 60000 millis to a min, 60 min to hour. 24 /day, -1 millisecond to be end of day 
    public static final long MILLIS_IN_30_DAYS = (60000L * 60L * 24L * 30L); // 60*1000 millis = 1 min;  60 min = 1 hour; 24 h = 1 day; 30 days
    
    public static final String START_DISPLAY_COUNT = "start";
    public static final String ITEMS_DISPLAYED = "itemsDisplayed";
    public static final String USER_ROLE = "userrole";
    public static final String FEEDBACK_TYPE = "feedbacktype";
    public static final String COMMENTS_ONLY_DISPLAYED = "comments";
    public static final String START_DISPLAY_DATE = "startDisplayDate";
    public static final String END_DISPLAY_DATE = "endDisplayDate";
    
    public static final String DATE_FORMAT = "MM/dd/yyyy";  // used to define the format in the java date formatter (to parse the date picker)
    // This dateFormat should match the datepicker Format of Month slash Day slash Year 
    
    private HashMap queryParameters;  // Map of objects because its easier to store than dealing with each individually
    
    // see constructor for list of variables
    public FeedbackQueryParameters()
    {
        // this should be all the defaults
        this(0, 50, ""/*role*/, ""/*feedbacktype*/, false, new Date(System.currentTimeMillis() - MILLIS_IN_30_DAYS), new Date(System.currentTimeMillis()));
    }
    
    

    public FeedbackQueryParameters(int start, int items, String role, String feedbacktype, Boolean comments, Date startDate, Date endDate)
    {
        queryParameters = new HashMap();
        // this should be a list of all the used parameters.  If parameters are added/removed, please adjust here so people know to expect them.
        queryParameters.put(START_DISPLAY_COUNT, start);
        queryParameters.put(ITEMS_DISPLAYED, items);
        queryParameters.put(USER_ROLE, role);
        queryParameters.put(FEEDBACK_TYPE, feedbacktype);
        queryParameters.put(COMMENTS_ONLY_DISPLAYED ,comments);
        queryParameters.put(START_DISPLAY_DATE, startDate);
        queryParameters.put(END_DISPLAY_DATE, endDate);
    }
    
        public static FeedbackQueryParameters createParametersFromSession(PortletSession session)
    {
        int startNum = (Integer) session.getAttribute("start");
        int itemNum = (Integer) session.getAttribute("items");
        String feedback = (String) session.getAttribute("feedbacktype");
        String role = (String) session.getAttribute("userrole");
        Date startDisplayDate = (Date) session.getAttribute("startDisplayDate");  // these are both set in 'parseCurrentDateRange'
        Date endDisplayDate = (Date) session.getAttribute("endDisplayDate");
        Boolean comments = (Boolean) session.getAttribute("comments");
        
        FeedbackQueryParameters params = new FeedbackQueryParameters(startNum, itemNum, role, feedback, comments, startDisplayDate, endDisplayDate);
        session.setAttribute("feedbackQueryParameters", params, session.APPLICATION_SCOPE);
        return params;
    }
    
    public void updateParametersWithForm(ViewFeedbackForm form) throws Exception
    {

        // update the requested user role
        String role = form.getUserrole();
        if (role != null)
        {
            queryParameters.put(USER_ROLE, role);
        }
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        Date startDisplayDate;
        Date endDisplayDate;
        if(!form.getStartDisplayDate().equals(""))
        {
        	startDisplayDate = dateFormatter.parse(form.getStartDisplayDate());
        }
        else
        {
        	//Defaulting start display date if it is empty
        	String startDefaultDate = dateFormatter.format(new Date(System.currentTimeMillis() - MILLIS_IN_30_DAYS));
        	startDisplayDate = dateFormatter.parse(startDefaultDate);
        }
        if (startDisplayDate != null)
        {
            queryParameters.put(START_DISPLAY_DATE, startDisplayDate);
        }
        
        if(!form.getEndDisplayDate().equals(""))
        {        	
        	endDisplayDate = dateFormatter.parse(form.getEndDisplayDate());
        }
        else
        {
        	//Defaulting end display date if it is empty
        	String endDefaultDate = dateFormatter.format(new Date(System.currentTimeMillis()));
        	endDisplayDate = dateFormatter.parse(endDefaultDate);   	
        }        
        if (endDisplayDate != null)
        {
            queryParameters.put(END_DISPLAY_DATE, endDisplayDate);
        }
        
        
        // update the requested feedback type
        String type = form.getFeedbacktype();
        if (type != null)
        {
            queryParameters.put(FEEDBACK_TYPE, type);
        }
        
        // update the requested number of items
        Integer items = form.getItems();
        if (items > 0)
        {
            queryParameters.put(ITEMS_DISPLAYED, items);
        }
        
        // reset the start index
        queryParameters.put(START_DISPLAY_COUNT, 0);

        // update the requested comments only criteria
        Boolean comments = form.getComments();
        if (comments != null)
        {
            queryParameters.put(COMMENTS_ONLY_DISPLAYED, comments);
        }
    }
    
    public Map putIntoModelMap()
    {
        HashMap model = new HashMap();
        int startNum = getInt(START_DISPLAY_COUNT);
        int itemNum = getInt(ITEMS_DISPLAYED);
        String feedback = getString(FEEDBACK_TYPE);
        String role = getString(USER_ROLE);
        Date startDisplayDate = getDate(START_DISPLAY_DATE);  // these are both set in 'parseCurrentDateRange'
        Date endDisplayDate = getDate(END_DISPLAY_DATE);
        Boolean comments = getBoolean(COMMENTS_ONLY_DISPLAYED);
        
        //parseCurrentDateRange(request, startDisplayDate, endDisplayDate); // moved to private method to clean up code
        model.put("userrole", role);
        model.put("feedbacktype", feedback);// 'feedback' is defined by getting 'feedbackType', not to be confused with the feedback itself
        model.put("comments", comments);


        
        model.put("start", startNum);
        model.put("items", itemNum);
        
        
        // Date format required to parse user input dates
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        if(log.isDebugEnabled()) {
            log.debug("The start display date is:"+dateFormatter.format(startDisplayDate));
            log.debug("The end display date is:"+dateFormatter.format(endDisplayDate));
        }
        model.put("startDisplayDate",dateFormatter.format(startDisplayDate) );
        model.put("endDisplayDate", dateFormatter.format(endDisplayDate) );
        return model;
    }
    
    /*
     * Gets a number, which must be stored as an INT but might not translate as well into other formats as a primitive
     * if there is no int stored by that parameter, it returns 0 which is default in java
     */
    public int getInt(String parameterName)
    {
        Integer returnNum = (Integer)queryParameters.get(parameterName);
        if (returnNum != null)
        { 
            return returnNum.intValue();
        }
        else { return 0; }
    }
    
    /*
     * sets the variables for 
     */
    public void setPagingStart(int newStart)
    {
        queryParameters.put(START_DISPLAY_COUNT, new Integer(newStart));
    }
    
    /*
     * rather than pigeon hole into two date parameters, the other being end date, this is generic so more can be added later with ease
     */
    public Date getDate(String parameterName)
    {
        return ((Date)queryParameters.get(parameterName)); 
    }
    
    /*
     * turns the date ahead a whole day so it can be used to obtain the end date and include the entire day in its time frame
     */
    public Date getEndDate(String parameterName)
    {
        Date theTime = (Date)queryParameters.get(parameterName);
        if (theTime != null)
        {
            return ( new Date(theTime.getTime() + MILLIS_IN_A_DAY) ) ;
        }
        else 
        {
            return null;
        }
    }
    
    /*
     * returns a String representing the object.  This is usually used to get a string, but just in case 'toString' is used to be safe
     */
    public String getString(String parameterName)
    {
        Object returnedObject = queryParameters.get(parameterName);
        if (returnedObject != null)
        {
            return (returnedObject.toString());
        }
        else
            return null;
    }
    
    /*
     * returns a boolean.   Right now comments is the only boolean, but I can see there being more
     * if no boolean stored in that variable, it returns false
     */
    public boolean getBoolean(String parameterName)
    {
        Object returnedObject = queryParameters.get(parameterName);
        if (returnedObject != null)
        {
            return ((Boolean)returnedObject).booleanValue();
        }
        else
            return false;
    }
    
    /*
     * It might be easier for some people just to refer to a generic get object and cast it themselves if needed.
     */
    public Object getObject(String parameterName)
    {
        return queryParameters.get(parameterName);
    }
    
}
