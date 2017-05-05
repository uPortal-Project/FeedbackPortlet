<%--

    Licensed to Apereo under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Apereo licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<link href="<c:url value="/css/feedback.css"/>" type="text/css" rel="stylesheet"/>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">

<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

<div>
    <div role="alert" aria-live="polite">
        <div style="font-weight: bold;"><spring:message code="feedback.success.title"/></div>
        <p><spring:message code="feedback.success.message"/></p>
    </div>

    <a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored" href="<portlet:renderURL/>" style="text-decoration: none;">
        <i aria-hidden="true" class="material-icons" style="padding-right: 10px;">refresh</i>
        <spring:message code="feedback.success.more"/>
    </a>
</div>
