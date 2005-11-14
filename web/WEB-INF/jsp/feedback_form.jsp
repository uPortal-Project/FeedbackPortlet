<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<%@ taglib prefix="spring" uri="/spring" %>

<script language="JavaScript"><!--
function browserInfo(form) {
form.browserName.value=navigator.appName;
form.browserVersion.value=navigator.appVersion;
form.platform.value=navigator.platform;
form.userAgent.value=navigator.userAgent;
}
// --></script>

<p>
Use this form to send us your comments or suggestions about this web site
or its content. For help or technical assistance, please contact your
system administrator.
</p>

<p>
<strong>If you wish to be contacted</strong>, please provide
a valid email address and/or telephone number.
</p>

<form method="post" action="<portlet:actionURL/>" onsubmit="browserInfo(this)">

	<p>
	<spring:bind path="command.name">
	Name: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
	<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
	</spring:bind>
	</p>
	
	<p>
	<spring:bind path="command.netid">
	NetID: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
	<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
	</spring:bind>
	</p>
	
	<p>
	<spring:bind path="command.phoneNumber">
	Telephone Number: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
	<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
	</spring:bind>
	</p>
	
	<p>
	<spring:bind path="command.emailAddress">
	Email Address: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
	<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
	</spring:bind>
	</p>
	
	<p>
	<spring:bind path="command.subject">
	Subject*: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
	<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
	</spring:bind>
	</p>
	
	<p>
	<spring:bind path="command.details">
	Details*: <font color="#FF0000"><c:out value="${status.errorMessage}"/></font><br/>
<textarea wrap="physical" rows="10" cols="60" name="<c:out value="${status.expression}"/>">
<c:out value="${status.value}"/>
</textarea>
	</spring:bind>
	</p>
	
	<spring:bind path="command.userAgent">
	<input type="hidden" name="<c:out value="${status.expression}"/>" />
	</spring:bind>
	<spring:bind path="command.browserName">
	<input type="hidden" name="<c:out value="${status.expression}"/>" />
	</spring:bind>
	<spring:bind path="command.browserVersion">
	<input type="hidden" name="<c:out value="${status.expression}"/>" />
	</spring:bind>
	<spring:bind path="command.platform">
	<input type="hidden" name="<c:out value="${status.expression}"/>" />
	</spring:bind>
	
	<p>
	<strong>* denotes a required field.</strong>
	</p>

	<p>
	<input type="submit" value="Submit" />
	</p>
</form>

