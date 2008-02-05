package org.jasig.portlets.FeedbackPortlet;

public class OverallFeedbackStats {

	private long positiveResponses = 0;
	private long negativeResponses = 0;
	private long undecidedResponses = 0;
	private long uniqueUsers = 0;
	
	public OverallFeedbackStats() { }
	
	public OverallFeedbackStats(long pos, long neg, long un, long unique) {
		this.positiveResponses = pos;
		this.negativeResponses = neg;
		this.undecidedResponses = un;
		this.uniqueUsers = unique;
	}
	
	public long getTotalResponses() {
		return positiveResponses + negativeResponses + undecidedResponses;
	}

	public long getUniqueUsers() {
		return uniqueUsers;
	}

	public long getPositiveResponses() {
		return positiveResponses;
	}

	public long getNegativeResponses() {
		return negativeResponses;
	}

	public long getUndecidedResponses() {
		return undecidedResponses;
	}

	public void setPositiveResponses(long positiveResponses) {
		this.positiveResponses = positiveResponses;
	}

	public void setNegativeResponses(long negativeResponses) {
		this.negativeResponses = negativeResponses;
	}

	public void setUndecidedResponses(long undecidedResponses) {
		this.undecidedResponses = undecidedResponses;
	}

	public void setUniqueUsers(long uniqueUsers) {
		this.uniqueUsers = uniqueUsers;
	}
	
	

}
