/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
package org.jasig.portlets.FeedbackPortlet.web;

import java.util.Date;

/**
 * ViewFeedbackForm represents the form data for the administrative view.
 *
 * @author Jen Bourey
 */
public class ViewFeedbackForm {
	
	private int items = 50;
	private String userrole;
	private String feedbacktype;
	private String comments;
	private String startDisplayDate;
	private String endDisplayDate;

	
	/**
	 * Get the number of items to be returned.
	 * 
	 * @return
	 */
	public int getItems() {
		return items;
	}
	
	/**
	 * Set the number of items to be returned.
	 * 
	 * @param items
	 */
	public void setItems(int items) {
		this.items = items;
	}
	
	/**
	 * Get the role of the items to be returned.
	 * 
	 * @return
	 */
	public String getUserrole() {
		return userrole;
	}
	
	/**
	 * Set the role of the items to be returned.
	 * 
	 * @param userrole
	 */
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	
	/**
	 * Get the feedback type of the items to be returned.
	 * 
	 * @return
	 */
	public String getFeedbacktype() {
		return feedbacktype;
	}
	
	/**
	 * Set the feedback type of the items to be returned.
	 * 
	 * @param feedbacktype
	 */
	public void setFeedbacktype(String feedbacktype) {
		this.feedbacktype = feedbacktype;
	}
	
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Set the feedback type of the items to be returned.
     * 
     * @param startDisplayDate
     */
    public void setStartDisplayDate(String startDisplayDate) {
        this.startDisplayDate = startDisplayDate;
    }

    public String getStartDisplayDate() {
        return startDisplayDate;
    }

    public void setEndDisplayDate(String endDisplayDate) {
        this.endDisplayDate = endDisplayDate;
    }

    public String getEndDisplayDate() {
        return endDisplayDate;
    }
	
	
	
}


/*
 * ViewFeedbackForm.java
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