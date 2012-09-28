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
package org.jasig.portlets.FeedbackPortlet.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.jasig.portlets.FeedbackPortlet.FeedbackQueryParameters;
import org.jasig.portlets.FeedbackPortlet.OverallFeedbackStats;

/**
 * FeedbackStore defines an interface for saving feedback data.
 * 
 * @author Jen Bourey
 */
public interface FeedbackStore {

	/**
	 * Store a feedback item.
	 * 
	 * @param feedback
	 */
    public void storeFeedback(FeedbackItem feedback);
    
    /**
     * Retrieve all feedback in the data store.
     * 
     * @return list of all feedback items
     */
    public List<FeedbackItem> getFeedback();
	
	/**
	 * 
	 * @param start  -- starting point
	 * @param items  -- number of items displayed
	 * @param role
	 * @param feedbacktype
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<FeedbackItem> getFeedback(FeedbackQueryParameters params);

	/**
	 * Get statistics on the feedback data.
	 * 
	 * @return feedback statistics
	 */
    public OverallFeedbackStats getStats();
    
    /**
     * Get statistics on the feedback data separated by user role.
     * 
     * @return Map of feedback statistics, separated by user role
     */
    public Map<String, OverallFeedbackStats> getStatsByRole();
	
    /**
     * Get the total number of feedback items for a given role and 
     * feedback type.  If the role or feedback type are left null, the 
     * method will include items with any role or feedback type, respectively.
     * 
     * @param role desired role, or <code>null</code> for all roles
     * @param feedbacktype feedback type of the desired items, or 
     *              <code>null</code> for all items
     * @param startDate 
     * @param endDate 
     * @return number of matching feedback items
     */
    public long getFeedbackTotal(FeedbackQueryParameters params);
	
}


/*
 * FeedbackStore.java
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