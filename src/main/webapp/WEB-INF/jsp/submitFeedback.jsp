<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<script src="<rs:resourceURL value="/rs/jquery/1.4.2/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/twitlimit-0.2.0.compressed.js"/>" type="text/javascript"></script>

<link href="<c:url value="/css/feedback.css"/>" type="text/css" rel="stylesheet"/>

<c:set var="n"><portlet:namespace/></c:set>

<portlet:actionURL var="postUrl"/>

<h1><spring:message code="feedback.form.question"/></h1>

<form:form action="${postUrl}" modelAttribute="submitFeedbackForm">       
    <spring:bind path="prefs.*">
    	<c:if test="${status.error}">
    	<div id="${n}error-message" class="error-message portlet-msg-error portlet-msg error" role="alert" style="display:none">
   	 	<p><form:errors path="feedback"/></p>
		</div>
	    </c:if>
	</spring:bind>
	<br>
	<p id="${n}answer">
      	<form:radiobutton id="yes" path="like" value="YES"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.yes"/></label>
      	<form:radiobutton id="no" path="like" value="NO"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.no"/></label>
      	<form:radiobutton id="maybe" path="like" value="MAYBE"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.maybe"/></label>
    </p>

 	<p> 		
 		<label class="portlet-form-field-label"><spring:message code="feedback.form.suggestion"/></label>
 		<textarea id="${n}feedback" path="feedback" name="feedback" rows="${feedbackRows}" style="width:${feedbackWidth}"></textarea> 		
 	</p>
 	<div id="${n}limit" style="margin-bottom: 12px;"></div>

 	<p>
        <form:checkbox path="anonymous" value="true"/>
		<label class="portlet-form-field-label"><spring:message code="feedback.form.anonymous"/></label>
	</p>

 	<input id="${n}useragentstring" type="hidden" name="useragent"/>
 	<input id="${n}feedbacktabname" type="hidden" name="tabname"/>

    <p>
       <button type="submit" id="submit" class="feedback-submit-button" disabled="disabled"><spring:message code="feedback.form.submit"/></button>
    </p>

</form:form>

<script type="text/javascript">

    var ${n} = {};
    ${n}.jQuery = jQuery.noConflict(true);

    ${n}.jQuery(function(){

        var $ = ${n}.jQuery;
        $('#${n}feedback').twitLimit({
            limit: ${feedbackMaxChars},
            message: 'You have %1 characters remaining', 
            counterElem: '#${n}limit', 
            allowNegative: false
        });
                   
        document.getElementById('${n}useragentstring').value = navigator.userAgent;
        
        $("#${n}error-message").slideDown(500);
        
        $('#${n}answer input:radio').click(function (){            
            $('#submit').removeAttr('disabled');                
            $('#submit').removeClass('feedback-submit-button');
            $('#submit').addClass('portlet-form-button');
        });        
        
        // check to see if a tab name parameter was submitted
        if ('${ requestScope.tabName }' != null && '${ requestScope.tabName }' != '') {
            document.getElementById('${n}feedbacktabname').value = '${requestScope.tabName }'; 
        // uPortal 2 tab name
        } else if (document.getElementById('tabName') != null) {
            document.getElementById('${n}feedbacktabname').value = $("#tabName").text();
        // uPortal 3 tab name
        } else if (document.getElementById('portalPageBodyTitle') != null) {
            document.getElementById('${n}feedbacktabname').value = $("#portalPageBodyTitle").text();
        }          
        
    });

</script>
