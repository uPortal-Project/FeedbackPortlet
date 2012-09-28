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
package org.jasig.portlets.FeedbackPortlet;

/**
 * OverallFeedbackStats represents summary statistics about the feedback
 * in some collection of feedback items.
 * 
 * @author Jen Bourey
 */
public class OverallFeedbackStats {

	private long positiveResponses = 0;
	private long negativeResponses = 0;
	private long undecidedResponses = 0;
	private long uniqueUsers = 0;
	
	/**
	 * Default constructor.
	 */
	public OverallFeedbackStats() { }
	
	/**
	 * Construct a new feedback statistics object.
	 * 
	 * @param pos number of positive responses
	 * @param neg number of negative responses
	 * @param un number of undecided responses
	 * @param unique number of unique users
	 */
	public OverallFeedbackStats(long pos, long neg, long un, long unique) {
		this.positiveResponses = pos;
		this.negativeResponses = neg;
		this.undecidedResponses = un;
		this.uniqueUsers = unique;
	}
	
	/**
	 * Get the number of total responses in the data set.
	 * 
	 * @return
	 */
	public long getTotalResponses() {
		return positiveResponses + negativeResponses + undecidedResponses;
	}

	/**
	 * Get the number of unique users in the data set.
	 * 
	 * @return
	 */
	public long getUniqueUsers() {
		return uniqueUsers;
	}

	/**
	 * Get the number of positive responses in the data set.
	 * 
	 * @return
	 */
	public long getPositiveResponses() {
		return positiveResponses;
	}

	/**
	 * Get the number of negative responses in the data set.
	 * 
	 * @return
	 */
	public long getNegativeResponses() {
		return negativeResponses;
	}

	/**
	 * Get the number of undecided responses in the data set.
	 * 
	 * @return
	 */
	public long getUndecidedResponses() {
		return undecidedResponses;
	}

	/**
	 * Set the number of positive responses in the data set.
	 * 
	 * @param positiveResponses
	 */
	public void setPositiveResponses(long positiveResponses) {
		this.positiveResponses = positiveResponses;
	}

	/**
	 * Set the number of negative responses in the data set.
	 * 
	 * @param negativeResponses
	 */
	public void setNegativeResponses(long negativeResponses) {
		this.negativeResponses = negativeResponses;
	}

	/**
	 * Set the number of undecided responses in the data set.
	 * 
	 * @param undecidedResponses
	 */
	public void setUndecidedResponses(long undecidedResponses) {
		this.undecidedResponses = undecidedResponses;
	}

	/**
	 * Set the number of unique users in the data set.
	 * 
	 * @param uniqueUsers
	 */
	public void setUniqueUsers(long uniqueUsers) {
		this.uniqueUsers = uniqueUsers;
	}	

}


/*
 * OverallFeedbackStats.java
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