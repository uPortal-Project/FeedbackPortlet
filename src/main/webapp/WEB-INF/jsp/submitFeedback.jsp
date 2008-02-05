<html xmlns="http://www.w3c.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:portlet="http://java.sun.com/portlet"
    xmlns:html="/WEB-INF/tags/html" xmlns:form="http://www.springframework.org/tags/form"
    xml:lang="en" lang="en">
    <jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>
    
    <body>
    	
    	<portlet:actionURL var="postUrl"/>

        <form:form commandName="prefs" action="${postUrl}">
        	<h1>Do you like uPortal?</h1>
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
				<label class="portlet-form-field-label">make anonymous</label>
        	</p>
        	<input id="useragentstring" type="hidden" name="useragent"/>
        	<input id="feedbacktabname" type="hidden" name="tabname"/>
            <button type="submit" class="portlet-form-button">Submit</button>
    	</form:form>

    	<script type="text/javascript">
    		dojo.byId('useragentstring').value = navigator.userAgent;
    		var tabname = dojo.byId('tabName');
    		if (tabname != null)
	    		dojo.byId('feedbacktabname').value = tabname.innerHTML;
    	</script>

    </body>
    
</html>