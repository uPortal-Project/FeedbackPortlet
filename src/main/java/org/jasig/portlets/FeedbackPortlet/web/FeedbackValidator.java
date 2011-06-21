package org.jasig.portlets.FeedbackPortlet.web;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class FeedbackValidator implements Validator {
	
	private int feedbackMaxChars;
	
	public void setFeedbackMaxChars(int feedbackMaxChars)
	{
	    this.feedbackMaxChars = feedbackMaxChars;
	}
    
    public boolean supports(Class givenClass) {
	    return givenClass.equals(SubmitFeedbackForm.class);
	}	
	
	public void validate(Object object, Errors errors) {
	    SubmitFeedbackForm form = (SubmitFeedbackForm) object;
	    if(form.getFeedback().length()>feedbackMaxChars){
	        errors.rejectValue("feedback","error.feedback");
		}		
	}	
}
