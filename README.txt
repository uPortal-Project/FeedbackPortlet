###################################################
# FeedbackPortlet
# Author: Nicholas Blair, nblair@doit.wisc.edu
# Version: 1.0RC1
# Date: 11/14/2005
# Contact: nblair@doit.wisc.edu
###################################################


### Purpose ###

This portlet provides an easily extendable system for user submitted feedback
forms. 


### Design ###

1. Display a form with typical feedback fields (name, phone, email, comments, etc).
2. Upon submission, email the data to a specified address (or system)


### Implementation ###

Required Libraries:
- JAF 1.0.2, activation.jar (Included)
- JavaMail 1.3.2, mail.jar (Included)
- JSTL, jstl.jar (Included)
- Jakarta standard tag libs, standard.jar (Included)
- Spring Framework 1.2.5, spring_1.2.5.jar (Included)
- Spring Portlet MVC build 20050928, spring-portlet_20050928.jar (Included)
-- (see http://opensource2.atlassian.com/confluence/spring/display/JSR168/Home)

This portlet is ready for deployment, no additional libraries are needed.
All development and testing was run with uPortal 2.5.1 quickstart, and 
Jakarta Tomcat 5.0.28.

The Spring Portlet MVC is an add on for the spring framework that provides the
servlet/web MVC functionality (in the spring framework) for portlets.

Model: The Feedback is stored in a java bean class, named 
edu.wisc.my.portlets.feedback.beans.Feedback. Feedback objects get passed
into an edu.wisc.my.portlets.feedback.dao.IFeedbackMessageFormatter, which
returns a org.springframework.mail.SimpleMailMessage.

View: The display pages are made with JSP. You will likely want to modify these
to display information relevant to your computer help organization. 
Please - NO SCRIPTLETS!!! If you want scriptlets, please do not submit them 
back to CVS.

Controller: classname - edu.wisc.my.portlets.feedback.web.FeedbackFormController.
Extends org.springframework.web.portlet.mvc.SimpleFormController.
There are two fields that need to be set, an IFeedbackFormatter implementation
and an org.springframework.mail.MailSender (a JavaMailSender is listed in
the spring configuration).


### Installation/Usage ###

1. 'ant dist' will create the war file in the dist folder.
Deploy the war file to your portlet container, and you are set.



