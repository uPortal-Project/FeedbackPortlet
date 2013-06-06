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
<link href="<c:url value="/css/feedback.css"/>" rel="stylesheet" type="text/css" />

<!-- assigning a variable to the name so it can be called in a non-conflicting way -->      
<c:set var="n"><portlet:namespace/></c:set>
<script src="<rs:resourceURL value="/rs/jquery/1.8.3/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script src="<rs:resourceURL value="/rs/jqueryui/1.8.13/jquery-ui-1.8.13.min.js"/>" type="text/javascript"></script>
        
<script type="text/javascript"><rs:compressJs>
    var ${n} = ${n} || {}; //create a unique variable to assign our namespace too
    ${n}.jQuery = jQuery.noConflict(true); //assign jQuery to this namespace
    
    /*  runs when the document is finished loading.  This prevents things like the 'div' from being fully created */
    ${n}.jQuery(document).ready(function () { 
        var $ = ${n}.jQuery; //reassign $ for normal use of jQuery
        // handle the 'select number of items to display' so that it shows the current setting.
        $("#${n}itemsShown").val("${items}");

        // handle 'filter by user role' so that it shows the current setting
		$("#${n}userRoleShown").val("${userrole}");

        // handle the 'filter by feedback type' to show the current setting. 
        $("#${n}feedbackTypeShown").val("${feedbacktype}");

        // handle the two text boxes associated with filter by date  	
    	
    	$( "#${n}datepicker1" ).datepicker().val("${startDisplayDate}");		
		$( "#${n}datepicker2" ).datepicker().val("${endDisplayDate}");
		
        $("#${n}pagingGoButton").click(function() {
            var pageInt = parseInt("0" + $("#${n}pagingBox").get(0).value);
            pageInt = pageInt-1;
            if (pageInt < 1)
            {
                pageInt = 1;
            } else if (pageInt > ${ totalItems })
            {
                pageInt = ${ totalItems };
            }
            var portletParam = "<portlet:renderURL><portlet:param name="start" value="03171879"/></portlet:renderURL>"; // value decided because it inlikely to be randomly generated and easily replaced
            window.location=(portletParam.replace("start=03171879", "start=" + pageInt.toString()));
         } );
    });
    
</rs:compressJs></script>


	<h3><spring:message code="feedback.admin.title"/></h3>
	
	<table class="feedback-summary" width="100%">
	   <tr>
       <td style="width:120px">	
           <c:set var="stats" value="${stats}"/>
    	   <img src="http://chart.apis.google.com/chart?cht=p&chd=t:${stats.positiveResponses * 100 / stats.totalResponses },${stats.undecidedResponses * 100 / stats.totalResponses },${stats.negativeResponses * 100 / stats.totalResponses }&chco=4C794D,999999,792230&chs=100x100"/>
	   </td>
	   <td class="">
		   	<span style="font-weight: bold">${ stats.totalResponses }</span> <spring:message code="feedback.admin.stats.total"/><br/> 
		   	<span style="font-weight: bold">${ stats.uniqueUsers }</span> <spring:message code="feedback.admin.stats.unique"/><br/>
	  		<span style="font-weight: bold; color: #4C794D"><fmt:formatNumber value="${ stats.positiveResponses / stats.totalResponses }" type="percent"/></span> <spring:message code="feedback.admin.stats.answer.yes"/><br/>
	  		<span style="font-weight: bold; color: #792230"><fmt:formatNumber value="${ stats.negativeResponses / stats.totalResponses }" type="percent"/></span> <spring:message code="feedback.admin.stats.answer.no"/><br/>
	  		<span style="font-weight: bold; color: #999999"><fmt:formatNumber value="${ stats.undecidedResponses / stats.totalResponses }" type="percent"/></span> <spring:message code="feedback.admin.stats.answer.maybe"/><br/>
	   </td>
	   	<td>
	   		<table class="feedback-list" cellspacing="0">
	   			<tr>
	   				<td></td>
	   				<c:forEach items="${ overallstats }" var="entry">
	   					<td><span style="text-transform:capitalize">${ entry.key }</span> (${ entry.value.uniqueUsers })</td>
	   				</c:forEach>
	   			</tr>
	   			<tr class="alt">
	   				<td><spring:message code="feedback.answer.yes"/></td>
	   				<c:forEach items="${ overallstats }" var="entry">
	   					<td><fmt:formatNumber value="${ entry.value.positiveResponses / entry.value.totalResponses }" type="percent"/></td>
	   				</c:forEach>
	   			</tr>
	   			<tr class="main">
	   				<td><spring:message code="feedback.answer.no"/></td>
	   				<c:forEach items="${ overallstats }" var="entry">
	   					<td><fmt:formatNumber value="${ entry.value.negativeResponses / entry.value.totalResponses }" type="percent"/></td>
	   				</c:forEach>
	   			</tr>
	   			<tr class="alt">
	   				<td><spring:message code="feedback.answer.maybe"/></td>
	   				<c:forEach items="${ overallstats }" var="entry">
	   					<td><fmt:formatNumber value="${ entry.value.undecidedResponses / entry.value.totalResponses }" type="percent"/></td>
	   				</c:forEach>
	   			</tr>

	   		</table>
	   		
	   	</td>
	   </tr>
	   </table>
	   <br/>
	
    <portlet:actionURL var="postUrl"></portlet:actionURL>
	<form:form action="${ postUrl }" modelAttribute="viewFeedbackForm">
	      <table style="padding:0; margin:5px 0px; border:none; width:100%">
	      	<tr>
		<td>
			<spring:message code="feedback.admin.form.show"/>: 
			<form:select id="${n}itemsShown" path="items">
				<form:option value="10"/>
				<form:option value="20"/>
				<form:option value="50"/>
			</form:select>

			<spring:message code="feedback.admin.form.role"/>: 
			<form:select id="${n}userRoleShown" path="userrole">
				<form:option value="" label="all"><spring:message code="feedback.admin.type.all"/></form:option>
				<form:option value="student"><spring:message code="feedback.admin.type.student"/></form:option>
				<form:option value="staff"><spring:message code="feedback.admin.type.staff"/></form:option>
				<form:option value="faculty"><spring:message code="feedback.admin.type.faculty"/></form:option>
			</form:select>

			<spring:message code="feedback.admin.form.type"/>: 
			<form:select id="${n}feedbackTypeShown" path="feedbacktype">
				<form:option value="" label="all"><spring:message code="feedback.admin.stats.answer.all"/></form:option>
				<form:option value="YES" ><spring:message code="feedback.admin.stats.answer.yes"/></form:option>
				<form:option value="NO" label="negative"><spring:message code="feedback.admin.stats.answer.no"/></form:option>
				<form:option value="MAYBE" label="undecided"><spring:message code="feedback.admin.stats.answer.maybe"/></form:option>
			</form:select>
            <spring:message code="feedback.admin.form.comments"/>:
            <input id="comments" name="comments" type="checkbox" value="yes" <c:if test="${comments==true}"> checked="checked"</c:if> />
                <div id="${n}datePicker">
                <errorElement id="${n}datePickerError"></errorElement>
                <spring:message code="feedback.admin.filter.title"/>
                <br>
                <label for="${n}datePicker1"><spring:message code="feedback.admin.filter.startdate"/></label> :
              	<form:input path="startDisplayDate" id="${n}datepicker1" />
                <br>
                <label for="${n}datePicker2"><spring:message code="feedback.admin.filter.enddate"/></label> :
              	<form:input path="endDisplayDate" id="${n}datepicker2" />
                </div>
			<button type="submit"><spring:message code="feedback.admin.form.submit"/></button>
			</td>
			<td style="text-align: right" style="white-space: nowrap">
					<spring:message code="feedback.admin.form.showing"/>
					<span style="font-weight: bold;">${ start + 1 } - 
					   ${ (start + items) > totalItems ? totalItems : start + items }</span> 
					   <spring:message code="feedback.admin.form.of"/> <span style="font-weight: bold;">${ totalItems }</span>
					<c:if test="${ start > 0 }">
						<a href="<portlet:renderURL><portlet:param name="start" value="${ start - items }"/></portlet:renderURL>">&lt; <spring:message code="feedback.admin.form.prev"/></a>
					</c:if>
					<c:if test="${ start > 0 and start + items < totalItems }">|</c:if>
					<c:if test="${ start + items < totalItems }">
						<a href="<portlet:renderURL><portlet:param name="start" value="${ start + items }"/></portlet:renderURL>"><spring:message code="feedback.admin.form.next"/> &gt;</a>
					</c:if>
					<br>
                    <spring:message code="feedback.admin.form.jumpto"/><input type="text" id=${n}pagingBox path="start">
                    <button type="button" id=${n}pagingGoButton><spring:message code="feedback.admin.form.goto"/></button>
    		</td>
		</tr>
	</table>
	</form:form>
	<table class="feedback-list" cellspacing="0">
		<tr>
			<th></th>
			<th><spring:message code="feedback.admin.rowtitle.page"/></th>
			<th><spring:message code="feedback.admin.rowtitle.name"/></th>
			<th><spring:message code="feedback.admin.rowtitle.role"/></th>
			<th><spring:message code="feedback.admin.rowtitle.browser"/></th>
			<th><spring:message code="feedback.admin.rowtitle.time"/></th>
		</tr>
		<c:forEach items="${feedback}" var="item" varStatus="status">
			<tr class="${ status.index % 2 == 0 ? 'main' : 'alt' }">
				<td style="width: 25px; text-align: center; border-bottom: thin solid #999; vertical-align: top" rowspan="2"><img src="<c:url value="/images/${ fn:toLowerCase(item.feedbacktype) }.png"/>"/></td>
				<td>${ item.tabname }</td>
				<c:choose>
					<c:when test="${ not empty item.useremail }">
						<td>${ item.username } <a href="mailto:${ item.useremail }"><img style="vertical-align: middle" src="<c:url value="/images/email.png"/>"/></a></td>
					</c:when>
					<c:when test="${ not empty item.username }">
						<td>${ item.username }</td>
					</c:when>
					<c:otherwise>
						<td><spring:message code="feedback.admin.anonymous"/></td>
					</c:otherwise>
				</c:choose>
				<td style="text-transform:capitalize">${ item.userrole }</td>
				<td>${ item.browserAbbreviation }</td>
				<td><fmt:formatDate value="${ item.submissiontime }"/></td>
			</tr>
			<tr class="${ status.index % 2 == 0 ? 'main' : 'alt' } bottom">
				<td colspan="5">${ item.feedback }</td>
			</tr>
		</c:forEach>
	</table>
	
	<br/>
	<p>
		<a href="<c:url value="/excelFeedback"/>" target="_blank"><img style="vertical-align: middle;" src="<c:url value="/images/excel.png"/>"/> <spring:message code="feedback.admin.export"/></a>
	</p>
