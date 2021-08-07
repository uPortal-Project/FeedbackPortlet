# Feedback Portlet

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jasig.portlet/FeedbackPortlet/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.jasig.portlet/FeedbackPortlet)
[![build status](https://github.com/uPortal-Project/FeedbackPortlet/workflows/CI/badge.svg?branch=master)](https://github.com/uPortal-Project/FeedbackPortlet/actions)

## About

The portlet is a JSR-168 Spring PortletMVC portlet. It uses hibernate for its data store.

User feedback submission features:

*   Simple user UI for comment submission
*   Users may make their feedback anonymous, but the default is to collect user information
*   Automatically collect information about the user and his or her browser

Administrative feedback features:

*   Admin interface for viewing feedback with recent comments and overall stats summaries
*   Easily filter feedback by type or user role
*   Shortcuts for sending email to non-anonymous users
*   Excel data export
*   Optional listener to forward feedback submissions on to an email address

Possible future features:

*   forward a comment to a ticketing system
*   provide a flag to indicate if an admin has responded to the ticket

## Installation

``` shell
# Clone the repository
git clone https://github.com/Jasig/FeedbackPortlet

# Open the cloned folder
cd FeedbackPortlet

# Install maven dependencies and build
mvn install

# Navigate to uPortal folder
cd ../path/to/uportal

# Deploy Feedback Portlet to uPortal
ant deployPortletApp -DportletApp /path/to/../FeedbackPortlet/target/FeedbackPortlet.war
```
