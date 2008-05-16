<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

 	<portlet:actionURL var="postUrl"/>

    <h1>Do you like uPortal?</h1>

    <form:form commandName="prefs" action="${postUrl}">

     	<p>
	      	<form:radiobutton path="like" value="YES"/>
	      	<label class="portlet-form-field-label">Yes</label>
	      	<form:radiobutton path="like" value="NO"/>
	      	<label class="portlet-form-field-label">No</label>
	      	<form:radiobutton path="like" value="MAYBE"/>
	      	<label class="portlet-form-field-label">Maybe</label>
     	</p>

     	<p>
     		<textarea name="feedback"></textarea>
     	</p>

     	<p>
	        <form:checkbox path="anonymous" value="true"/>
			<label class="portlet-form-field-label">make me anonymous</label>
    	</p>

     	<input id="<portlet:namespace/>useragentstring" type="hidden" name="useragent"/>
     	<input id="<portlet:namespace/>feedbacktabname" type="hidden" name="tabname"/>

        <p>
           <button type="submit" class="portlet-form-button">Submit</button>
        </p>

    </form:form>

 	<script type="text/javascript">
 		document.getElementById('<portlet:namespace/>useragentstring').value = navigator.userAgent;
 		
 		// check to see if a tab name parameter was submitted
 		var tabname = '${ requestScope.tabName }';
 		if ('${ requestScope.tabName }' != null && '${ requestScope.tabName }' != '') {
            document.getElementById('<portlet:namespace/>feedbacktabname').value = '${requestScope.tabName }'; 
 		// uPortal 2 tab name
 		} else if (document.getElementById('tabName') != null) {
      		document.getElementById('<portlet:namespace/>feedbacktabname').value = document.getElementById('tabName').innerHTML;
        // uPortal 3 tab name
	  	} else if (document.getElementById('portalPageBodyTitle') != null) {
	  	    document.getElementById('<portlet:namespace/>feedbacktabname').value = document.getElementById('portalPageBodyTitle').innerHTML;
	  	}
 	</script>
