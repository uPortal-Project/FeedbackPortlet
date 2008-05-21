<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

    <link href="<c:url value="/css/feedback.css"/>" type="text/css" rel="stylesheet"/>
    
   	<h1><spring:message code="feedback.success.title"/></h1>
   	
   	<p><spring:message code="feedback.success.message"/></p>
   	
   	<p><a href="<portlet:renderURL/>"><spring:message code="feedback.success.more"/></a></p>

</html>