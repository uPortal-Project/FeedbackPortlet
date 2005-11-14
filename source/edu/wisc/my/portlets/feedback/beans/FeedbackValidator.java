/*******************************************************************************
* Copyright 2005, The Board of Regents of the University of Wisconsin System.
* All rights reserved.
*
* A non-exclusive worldwide royalty-free license is granted for this Software.
* Permission to use, copy, modify, and distribute this Software and its
* documentation, with or without modification, for any purpose is granted
* provided that such redistribution and use in source and binary forms, with or
* without modification meets the following conditions:
*
* 1. Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* 3. Redistributions of any form whatsoever must retain the following
* acknowledgement:
*
* "This product includes software developed by The Board of Regents of
* the University of Wisconsin System.”
*
*THIS SOFTWARE IS PROVIDED BY THE BOARD OF REGENTS OF THE UNIVERSITY OF
*WISCONSIN SYSTEM "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING,
*BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE BOARD OF REGENTS OF
*THE UNIVERSITY OF WISCONSIN SYSTEM BE LIABLE FOR ANY DIRECT, INDIRECT,
*INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
*LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
*PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
*OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
*ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*******************************************************************************/
package edu.wisc.my.portlets.feedback.beans;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator to apply desired requirements for fields in the 
 * Feedback form:
 * 
 * - if the telephone number field is not empty, make sure it matches
 *    the regular expression that defines a valid phone number.
 * - if the email address field is not empty, make sure it matches
 *    the regular expression that defines a valid email address.
 * - verify the subject field contains data
 * - verify the details field contains data
 * 
 * This class also truncates the subject to 68 characters, a limit
 * defined by the format of the email message.
 * 
 * @author nblair@doit.wisc.edu
 * @version $Header$
 */
public class FeedbackValidator implements Validator {

    private static final String PHONE_REGEXP =
        "(\\({0,1})(\\d{3})(\\){0,1})(\\s|-)*(\\d{3})(\\s|-)*(\\d{4})";
    
    private static final String EMAIL_REGEXP =
        "^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}$";
    
    private static final int MAX_SUBJECT_LENGTH = 68;
    
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEXP);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEXP, Pattern.CASE_INSENSITIVE);
    
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return Feedback.class.equals(clazz);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors errors) {
        Feedback feedback = (Feedback) command;
        ValidationUtils.rejectIfEmpty(errors, "subject", "required.subject", "Subject is required");
        ValidationUtils.rejectIfEmpty(errors, "details", "required.details", "Feedback is required");
        validatePhone(feedback.getPhoneNumber(), errors);
        validateEmail(feedback.getEmailAddress(), errors);
        
        // TODO check feedback substring call
        // TODO determine if this should be moved to the MessageFormatterImpl
        if(feedback.getSubject().length() >= MAX_SUBJECT_LENGTH) {
            feedback.setSubject(feedback.getSubject().substring(0, MAX_SUBJECT_LENGTH - 1));
        }
    }

    private void validatePhone(String phone, Errors errors) {
        if(phone != null && !"".equals(phone)) {
            if(!phonePattern.matcher(phone).matches()) {
                errors.rejectValue("phoneNumber", "invalid.phone", "Phone number is invalid");
            }
        }
    }
    
    private void validateEmail(String email, Errors errors) {
        if(email != null && !"".equals(email)) {
            if(!emailPattern.matcher(email).matches()) {
                errors.rejectValue("emailAddress", "invalid.email", "Email address is invalid");
            }
        }
    }
}
