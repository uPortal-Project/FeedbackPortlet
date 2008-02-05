package org.jasig.portlets.FeedbackPortlet.dao;

import java.util.List;
import java.util.Map;

import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.jasig.portlets.FeedbackPortlet.OverallFeedbackStats;

public interface FeedbackStore {

    public void storeFeedback(FeedbackItem feedback);
    
    public List<FeedbackItem> getFeedback();

    public List<FeedbackItem> getFeedback(int start, int items);

	public List<FeedbackItem> getFeedback(int start, int items, String role, String feedbacktype);

    public OverallFeedbackStats getStats();
    
    public Map<String, OverallFeedbackStats> getStatsByRole();

	public long getFeedbackTotal(String role, String feedbacktype);
	
}
