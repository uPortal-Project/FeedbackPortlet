<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<script src="<rs:resourceURL value="/rs/jquery/1.4.2/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/twitlimit-0.2.0.compressed.js"/>" type="text/javascript"></script>

<c:set var="n"><portlet:namespace/></c:set>

<portlet:actionURL var="postUrl"/>

<h1><spring:message code="feedback.form.question"/></h1>

<form:form commandName="prefs" action="${postUrl}">

 	<p>
      	<form:radiobutton path="like" value="YES"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.yes"/></label>
      	<form:radiobutton path="like" value="NO"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.no"/></label>
      	<form:radiobutton path="like" value="MAYBE"/>
      	<label class="portlet-form-field-label"><spring:message code="feedback.answer.maybe"/></label>
 	</p>

 	<p>
 		<textarea id="${n}feedback" name="feedback" rows="${feedbackRows}" cols="${feedbackCols}"></textarea>
 	</p>
 	<div id="${n}limit" style="margin-bottom: 12px;"></div>

 	<p>
        <form:checkbox path="anonymous" value="true"/>
		<label class="portlet-form-field-label"><spring:message code="feedback.form.anonymous"/></label>
	</p>

 	<input id="<portlet:namespace/>useragentstring" type="hidden" name="useragent"/>
 	<input id="<portlet:namespace/>feedbacktabname" type="hidden" name="tabname"/>

    <p>
       <button type="submit" class="portlet-form-button"><spring:message code="feedback.form.submit"/></button>
    </p>

</form:form>

<script type="text/javascript">

    var ${n} = {};
    ${n}.jQuery = jQuery.noConflict(true);

    ${n}.jQuery(function(){

        var $ = ${n}.jQuery;
        $('#${n}feedback').twitLimit({
            message: 'You have %1 characters remaining', 
            counterElem: '#${n}limit', 
            allowNegative: false
        });
                   
        document.getElementById('<portlet:namespace/>useragentstring').value = navigator.userAgent;
        
        // check to see if a tab name parameter was submitted
        if ('${ requestScope.tabName }' != null && '${ requestScope.tabName }' != '') {
            document.getElementById('<portlet:namespace/>feedbacktabname').value = '${requestScope.tabName }'; 
        // uPortal 2 tab name
        } else if (document.getElementById('tabName') != null) {
            document.getElementById('<portlet:namespace/>feedbacktabname').value = document.getElementById('tabName').innerHTML;
        // uPortal 3 tab name
        } else if (document.getElementById('portalPageBodyTitle') != null) {
            document.getElementById('<portlet:namespace/>feedbacktabname').value = document.getElementById('portalPageBodyTitle').innerHTML;
        }

    });

</script>
