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

<script src="<rs:resourceURL value="/rs/jquery/1.8.3/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/twitlimit-0.2.0.compressed.js"/>" type="text/javascript"></script>

<link href="<c:url value="/css/feedback.css"/>" type="text/css" rel="stylesheet"/>

<c:set var="n"><portlet:namespace/></c:set>

<portlet:actionURL var="postUrl"/>

<div>
<h3><spring:message code="feedback.form.question"/></h3>

<form:form action="${postUrl}" modelAttribute="submitFeedbackForm">       
    <spring:bind path="prefs.*">
    	<c:if test="${status.error}">
    	<div id="${n}error-message" class="error-message portlet-msg-error portlet-msg error" role="alert" style="display:none">
   	 	<p><form:errors path="feedback"/></p>
		</div>
	    </c:if>
	</spring:bind>

    <div data-role="fieldcontain">
    <fieldset data-role="controlgroup" id="${n}answer">
          <input type="radio" id="yes" name="like" value="YES"/>
          <label for="yes" class="portlet-form-field-label"><spring:message code="feedback.answer.yes"/></label>
          <input type="radio" id="no" name="like" value="NO"/>
          <label for="no" class="portlet-form-field-label"><spring:message code="feedback.answer.no"/></label>
          <input type="radio" id="maybe" name="like" value="MAYBE"/>
          <label for="maybe" class="portlet-form-field-label"><spring:message code="feedback.answer.maybe"/></label>
    </fieldset>
    </div>

 	<p> 		
 		<label class="portlet-form-field-label"><spring:message code="feedback.form.suggestion"/></label>
         <textarea id="${n}feedback" name="feedback" rows="${feedbackRows}" style="width:${feedbackWidth}"></textarea>
 	</p>
 	<div id="${n}limit" style="margin-bottom: 12px;"></div>

     <p>
        <fieldset data-role="controlgroup">
            <input type="checkbox" id="${n}anonymous" name="anonymous" value="true"/>
            <label for="${n}anonymous" class="portlet-form-field-label"><spring:message code="feedback.form.anonymous"/></label>
        </fieldset>
    </p>

 	<input id="${n}useragentstring" type="hidden" name="useragent"/>
 	<input id="${n}feedbacktabname" type="hidden" name="tabname"/>

    <p>
       <input type="submit" id="${n}submitfeedback" class="feedback-submit-button ui-btn-hidden" disabled="disabled" aria-disabled="true" value="<spring:message code="feedback.form.submit"/>">
    </p>

</form:form>
</div>

<script type="text/javascript"><rs:compressJs>
   // Enable submitFeedback work here only on Mobile
    var $ = up.jQuery;
    $(document).bind("pageinit", function (e) {
        var $page = $(e.target);
        $page.find("input:radio[name=like]").change(function () {
            $(this).checkboxradio("refresh");
            $page.find("input:radio[name=like]").each(function (index, element) {
                var $radio = $(this);
                var $label = $radio.next();
                if ($label.hasClass("ui-radio-on")) {
                    if ($('#${n}submitfeedback').attr('disabled')) $('#${n}submitfeedback').removeAttr('disabled');
                    if ($('#${n}submitfeedback').attr('aria-disabled')) $('#${n}submitfeedback').removeAttr('aria-disabled');
                    if ($('#${n}submitfeedback').prop('disabled')) $('#${n}submitfeedback').prop('disabled', false);
                    if($('#${n}submitfeedback').hasClass('feedback-submit-button')) $('#${n}submitfeedback').removeClass('feedback-submit-button');
                    if(!$('#${n}submitfeedback').hasClass('portlet-form-button')) $('#${n}submitfeedback').addClass('portlet-form-button');
                    if($('#${n}submitfeedback').parent().hasClass('ui-disabled')) $('#${n}submitfeedback').parent().removeClass('ui-disabled');
                }
            });
        });
    });

    var ${n} = {};
    ${n}.jQuery = jQuery.noConflict(true);

    ${n}.jQuery(function(){

        var $ = ${n}.jQuery;
        $('#${n}feedback').twitLimit({
            limit: ${feedbackMaxChars},
            message: '<spring:message code="feedback.form.charactersremaining" arguments="%1"/>',
            counterElem: '#${n}limit', 
            allowNegative: false
        });
                   
        document.getElementById('${n}useragentstring').value = navigator.userAgent;
        
        $("#${n}error-message").slideDown(500);
        
        $('#submitFeedbackForm').submit(function (){
            // disable submit
            $('input[type=submit]', this).attr('disabled','disabled');
            $('#${n}submitfeedback').attr('disabled','disabled');
            $('#${n}submitfeedback').addClass('feedback-submit-button');
            $('#${n}submitfeedback').removeClass('portlet-form-button');
            $('#${n}submitfeedback').parent().addClass('ui-disabled');
        });

       // Enable submitFeedback work here only on Desktop
       $("#${n}answer input:radio, #${n}answer label").click(function (){
            if ($('#${n}submitfeedback').attr('disabled')) $('#${n}submitfeedback').removeAttr('disabled');
            if ($('#${n}submitfeedback').attr('aria-disabled')) $('#${n}submitfeedback').removeAttr('aria-disabled');
            if ($('#${n}submitfeedback').prop('disabled')) $('#${n}submitfeedback').prop('disabled', false);
            if($('#${n}submitfeedback').hasClass('feedback-submit-button')) $('#${n}submitfeedback').removeClass('feedback-submit-button');
            if(!$('#${n}submitfeedback').hasClass('portlet-form-button')) $('#${n}submitfeedback').addClass('portlet-form-button');
            if($('#${n}submitfeedback').parent().hasClass('ui-disabled')) $('#${n}submitfeedback').parent().removeClass('ui-disabled');
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

</rs:compressJs></script>
