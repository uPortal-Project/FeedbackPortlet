/*
 * Created on Feb 5, 2008
 *
 * Copyright(c) Yale University, Feb 5, 2008.  All rights reserved.
 * (See licensing and redistribution disclosures at end of this file.)
 * 
 */
package org.jasig.portlets.FeedbackPortlet.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.jasig.portlets.FeedbackPortlet.OverallFeedbackStats;
import org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * HibernateFeedbackStore is a hibernate implementation of the FeedbackStore interface.
 * 
 * @author Jen Bourey
 */
public class HibernateFeedbackStore extends HibernateDaoSupport implements
		FeedbackStore {

	private static Log log = LogFactory.getLog(HibernateFeedbackStore.class);
	private long millisInADay = (60000L * 60L * 24L); // 60000 millis to a min, 60 min to hour. 24 /day, -1 millisecond to be end of day 

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#storeFeedback(org.jasig.portlets.FeedbackPortlet.FeedbackItem)
	 */
	public void storeFeedback(FeedbackItem feedback) {
		try {
			final Session session = this.getSession(false);

			// If the FeedbackItem is new it must be saved first
			if (feedback.getId() == -1) {
				session.save(feedback);
			}

			session.update(feedback);
			session.flush();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getFeedback()
	 */
	public List<FeedbackItem> getFeedback() {
		List<FeedbackItem> results;
		try {
			final Session session = this.getSession(false);
			Criteria crit = session.createCriteria(FeedbackItem.class);
			crit.addOrder(Order.desc("submissiontime"));
			results = crit.list();

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getFeedback(int, int)
	 */
	public List<FeedbackItem> getFeedback(int start, int items) {
		List<FeedbackItem> results;
		try {
			final Session session = this.getSession(false);
			Criteria crit = session.createCriteria(FeedbackItem.class);
			crit.addOrder(Order.desc("submissiontime"));
			crit.setFirstResult(start);
			crit.setMaxResults(items);
			results = crit.list();

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getFeedback(int, int, java.lang.String, java.lang.String)
	 */
	public List<FeedbackItem> getFeedback(int start, int items, String role,
			String feedbacktype) {
	    List<FeedbackItem> results;
		try {
		    final Session session = this.getSession(false);
			Criteria crit = session.createCriteria(FeedbackItem.class);
			crit.addOrder(Order.desc("submissiontime"));
			crit.setFirstResult(start);
			if (role != null)
				crit.add(Expression.eq("userrole", role));
			if (feedbacktype != null)
				crit.add(Expression.eq("feedbacktype", feedbacktype));
			crit.setMaxResults(items);
			results = crit.list();

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return results;

	}
	
	/** 
	 * Searches the feedback and pulls the results between (inclusively) the dates listed.   One does not actually have to be less than the other
	 * @param Date1
	 * @param Date2
	 * @return list of items
	 */
	public List<FeedbackItem> getFeedback(int start, int items, String role, String feedbacktype, Date date1, Date date2)
	{
	    // figure out which date comes first, so that they will always have the later be the date checked last
	    Date startDate = new Date(date1.getTime());
	    Date endDate = new Date(date2.getTime());
	    if (date2.before(date1))
	    {
	        startDate.setTime(date2.getTime());
	        endDate.setTime(date1.getTime());
	    }
	    endDate.setTime(endDate.getTime()+millisInADay); // sets 24 hours ahead so date = 1st millisecond of following day (inclusive between dates)
	    
	    // declare both the entire list and the list of filtered data
        List<FeedbackItem> results;
        
        try {
            final Session session = this.getSession(false);
            Criteria crit = session.createCriteria(FeedbackItem.class);
            crit.addOrder(Order.desc("submissiontime"));
            //crit.setFirstResult(start);
            if (role != null)
                 crit.add(Expression.eq("userrole", role));
            if (feedbacktype != null)
                 crit.add(Expression.eq("feedbacktype", feedbacktype));
            // Dates are on by default and throws an error if not entered, so they should never be null 
            crit.add(Expression.between("submissiontime", startDate, endDate));
            
            crit.setMaxResults(items);
             
            results = crit.list();
       } catch (HibernateException ex) {
           throw convertHibernateAccessException(ex);
       }
       
       return results;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getFeedbackTotal(java.lang.String, java.lang.String)
	 */
	public long getFeedbackTotal(String role, String feedbacktype) {
		try {
			final Session session = this.getSession(false);
			String sql = "select count(item.id) from FeedbackItem item";
			if (role != null || feedbacktype != null) {
				sql = sql.concat(" where");
			}
			if (role != null) {
				sql = sql.concat(" userrole = :userrole");
			}
			if (role != null && feedbacktype != null)
				sql = sql.concat(" and");
			if (feedbacktype != null) {
				sql = sql.concat(" feedbacktype = :feedbacktype");
			}
			Query query = session.createQuery(sql);
			if (role != null)
				query.setString("userrole", role);
			if (feedbacktype != null)
				query.setString("feedbacktype", feedbacktype);
			return (Long) query.uniqueResult();
		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
	}
	
	   /*
     * (non-Javadoc)
     * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getFeedbackTotal(java.lang.String, java.lang.String)
     */
    public long getFeedbackTotal(String role, String feedbacktype, Date date1, Date date2) {
        try {
            final Session session = this.getSession(false);
            String sql = "select count(item.id) from FeedbackItem item";
            if (role != null || feedbacktype != null) {
                sql = sql.concat(" where");
            }
            if (role != null) {
                sql = sql.concat(" userrole = :userrole");
            }
            if (role != null && feedbacktype != null)
                sql = sql.concat(" and");
            if (feedbacktype != null) {
                sql = sql.concat(" feedbacktype = :feedbacktype");
            }
            if (date1 != null && date2 != null)
            {
                java.sql.Date startDate = new java.sql.Date(date1.getTime());
                java.sql.Date endDate = new java.sql.Date(date2.getTime());
                sql.concat(" and submissiontime BETWEEN ");
                sql.concat(startDate.toString());
                sql.concat(" AND ");
                sql.concat(endDate.toString());
            }
            Query query = session.createQuery(sql);
            if (role != null)
                query.setString("userrole", role);
            if (feedbacktype != null)
                query.setString("feedbacktype", feedbacktype);
            return (Long) query.uniqueResult();
        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }
    }

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getStats()
	 */
	public OverallFeedbackStats getStats() {
		OverallFeedbackStats stats = new OverallFeedbackStats();
		try {
			final Session session = this.getSession(false);

			stats
					.setUniqueUsers((Long) session
							.createQuery(
									"select count(distinct item.userid) " +
									"from FeedbackItem item")
							.uniqueResult());

			Iterator i = session
					.createQuery(
							"select item.feedbacktype, count(item) from " +
							"FeedbackItem item group by item.feedbacktype")
					.list().iterator();
			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				String feedbacktype = (String) row[0];
				Long count = (Long) row[1];
				if (feedbacktype.equals(FeedbackItem.YES))
					stats.setPositiveResponses(count);
				else if (feedbacktype.equals(FeedbackItem.NO))
					stats.setNegativeResponses(count);
				else
					stats.setUndecidedResponses(count);
			}

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return stats;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jasig.portlets.FeedbackPortlet.dao.FeedbackStore#getStatsByRole()
	 */
	public Map<String, OverallFeedbackStats> getStatsByRole() {
		OverallFeedbackStats stats = new OverallFeedbackStats();
		Map<String, OverallFeedbackStats> statsMap = new HashMap<String, OverallFeedbackStats>();
		try {
			final Session session = this.getSession(false);

			Iterator i = session
					.createQuery(
							"select item.userrole, count(distinct item.userid) " +
							"from FeedbackItem item group by item.userrole")
					.list().iterator();
			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				String userrole = (String) row[0];
				Long count = (Long) row[1];
				stats = new OverallFeedbackStats();
				stats.setUniqueUsers(count);
				statsMap.put(userrole, stats);
			}

			i = session
					.createQuery(
							"select item.userrole, item.feedbacktype, count(item) " +
							"from FeedbackItem item group by item.userrole, " +
							"item.feedbacktype")
					.list().iterator();
			while (i.hasNext()) {
				Object[] row = (Object[]) i.next();
				String userrole = (String) row[0];
				String feedbacktype = (String) row[1];
				Long count = (Long) row[2];

				stats = (OverallFeedbackStats) statsMap.get(userrole);
				if (feedbacktype.equals(FeedbackItem.YES))
					stats.setPositiveResponses(count);
				else if (feedbacktype.equals(FeedbackItem.NO))
					stats.setNegativeResponses(count);
				else
					stats.setUndecidedResponses(count);
			}

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		return statsMap;
	}

}

/*
 * HibernateFeedbackStore.java
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