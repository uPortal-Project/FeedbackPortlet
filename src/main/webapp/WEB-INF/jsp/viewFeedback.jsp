<html xmlns="http://www.w3c.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:portlet="http://java.sun.com/portlet"
    xmlns:html="/WEB-INF/tags/html" xmlns:form="http://www.springframework.org/tags/form"
    xml:lang="en" lang="en">
    <jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

	<head>
		<style type="text/css">
			table.feedback { width: 100%;}
			table.feedback tr th{ background-color: #2758B3; color: #fff; text-align: left;  padding:3px; }
			.feedback tr td { padding:6px; }
			.feedback tr.main td { background-color: #fff; }
			.feedback tr.alt td { background-color: #E6EEFB; }
			.feedback tr.bottom td { border-bottom: thin solid #999; }
		</style>
		
		<script type="application/x-javascript">
			function draw() {
				var canvas = document.getElementById("canvas");
				var ctx = canvas.getContext("2d");

				var slices = new Array();
				<c:set var="stats" value="${model.stats}"/>
				slices.push({percent:${stats.positiveResponses * 100 / stats.totalResponses },color:'green'});
				slices.push({percent:${stats.negativeResponses * 100 / stats.totalResponses },color:'red'});
				slices.push({percent:${stats.undecidedResponses * 100 / stats.totalResponses },color:'gray'});

				var i;
				var start = 0;
				var end;
				for (i = 0; i < slices.length; i++) {
					var slice = slices[i];
					end = start + (2*Math.PI*(slice.percent/100));
					ctx.fillStyle = slice.color;
					ctx.beginPath();
					ctx.moveTo(50, 50);
					ctx.arc(50, 50, 45, start, end, false);
					ctx.lineTo(50, 50);
					ctx.fill();
					start = end;
				}
			}
	</script>
	</head>
    
    <body>

		<h1>Recent Feedback</h1>

		<table width="100%"><tr><td style="width:120px">	
	    <canvas id="canvas" width="100" height="100"></canvas>
	    </td>
	    <td>
	    	<span style="font-weight: bold">${ stats.totalResponses }</span> Total responses<br/> 
	    	<span style="font-weight: bold">${ stats.uniqueUsers }</span> Unique users<br/>
    		<span style="font-weight: bold; color: green"><fmt:formatNumber value="${ stats.positiveResponses / stats.totalResponses }" type="percent"/></span> Positive<br/>
    		<span style="font-weight: bold; color: red"><fmt:formatNumber value="${ stats.negativeResponses / stats.totalResponses }" type="percent"/></span> Negative<br/>
    		<span style="font-weight: bold; color: gray"><fmt:formatNumber value="${ stats.undecidedResponses / stats.totalResponses }" type="percent"/></span> Undecided<br/>
	    </td>
	    	<td>
	    		<table class="feedback" cellspacing="0">
	    			<tr>
	    				<td></td>
	    				<c:forEach items="${ model.overallstats }" var="entry">
	    					<td><span style="text-transform:capitalize">${ entry.key }</span> (${ entry.value.uniqueUsers })</td>
	    				</c:forEach>
	    			</tr>
	    			<tr class="alt">
	    				<td>yes</td>
	    				<c:forEach items="${ model.overallstats }" var="entry">
	    					<td><fmt:formatNumber value="${ entry.value.positiveResponses / entry.value.totalResponses }" type="percent"/>
	    				</c:forEach>
	    			</tr>
	    			<tr class="main">
	    				<td>no</td>
	    				<c:forEach items="${ model.overallstats }" var="entry">
	    					<td><fmt:formatNumber value="${ entry.value.negativeResponses / entry.value.totalResponses }" type="percent"/>
	    				</c:forEach>
	    			</tr>
	    			<tr class="alt">
	    				<td>maybe</td>
	    				<c:forEach items="${ model.overallstats }" var="entry">
	    					<td><fmt:formatNumber value="${ entry.value.undecidedResponses / entry.value.totalResponses }" type="percent"/>
	    				</c:forEach>
	    			</tr>	    			
	    		</table>
	    		
	    	</td>
	    </tr>
	    </table>
	    <br/>

        <portlet:actionURL var="postUrl"></portlet:actionURL>
		<form:form commandName="viewFeedbackForm" action="${ postUrl }" method="post">
        <table style="padding:0; margin:0; border:none; width:100%">
        	<tr>
			<td>
				Show: 
				<form:select path="items">
					<form:option value="10"/>
					<form:option value="20"/>
					<form:option value="50"/>
				</form:select>
				Role: 
				<form:select path="userrole">
					<form:option value="" label="all"/>
					<form:option value="student"/>
					<form:option value="staff"/>
					<form:option value="faculty"/>
				</form:select>
				Type: 
				<form:select path="feedbacktype">
					<form:option value="" label="all"/>
					<form:option value="YES" label="positive"/>
					<form:option value="NO" label="negative"/>
					<form:option value="MAYBE" label="undecided"/>
				</form:select>
				<button type="submit">Update</button>
				</td>
				<td style="text-align: right">
				Showing <span style="font-weight: bold">${ model.start + 1 } - ${ (model.start + model.items) > model.totalItems ? model.totalItems : model.start + model.items }</span> of ${ model.totalItems }
				<c:choose>
					<c:when test="${ model.start > 0 }">
						<a href="<portlet:renderURL><portlet:param name="start" value="${ model.start - model.items }"/></portlet:renderURL>">&lt; prev</a>
					</c:when>
					<c:otherwise>&lt; prev</c:otherwise>
				</c:choose> | 
				<c:choose>
					<c:when test="${ model.start + model.items < model.totalItems }">
						<a href="<portlet:renderURL><portlet:param name="start" value="${ model.start + model.items }"/></portlet:renderURL>">next &gt;</a>
					</c:when>
					<c:otherwise>next &gt;</c:otherwise>
				</c:choose>
			</td>
			</tr>
		</table>
		</form:form>
		<table class="feedback" cellspacing="0">
			<tr>
				<th></th>
				<th>Page</th>
				<th>Name</th>
				<th>Role</th>
				<th>Browser</th>
				<th>Time</th>
			</tr>
			<c:forEach items="${model.feedback}" var="item" varStatus="status">
				<tr class="${ status.index % 2 == 0 ? 'main' : 'alt' }">
					<td style="width: 25px; text-align: center; border-bottom: thin solid #999; vertical-align: top" rowspan="2"><img src="<c:url value="/images/${ fn:toLowerCase(item.feedbacktype) }.png"/>"/></td>
					<td ><a href="">${ item.tabname }</a></td>
					<c:choose>
						<c:when test="${ not empty item.useremail }">
							<td><a target="_blank" href="http://scripts.its.yale.edu/cgi-bin/ph/directory.yale.edu?Query=netid%3D${ item.userid }">${ item.username }</a> <a href="mailto:${ item.useremail }"><img style="vertical-align: middle" src="<c:url value="/images/email.png"/>"/></a></td>
						</c:when>
						<c:when test="${ not empty item.username }">
							<td><a target="_blank" href="http://scripts.its.yale.edu/cgi-bin/ph/directory.yale.edu?Query=netid%3D${ item.userid }">${ item.username }</a></td>
						</c:when>
						<c:otherwise>
							<td>Anonymous</td>
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
			<a href="<c:url value="/excelFeedback"/>" target="_blank"><img style="vertical-align: middle;" src="<c:url value="/images/excel.png"/>"/> export</a>
		</p>
		
		<script type="text/javascript">
			draw();
		</script>
		
    </body>
    
</html>