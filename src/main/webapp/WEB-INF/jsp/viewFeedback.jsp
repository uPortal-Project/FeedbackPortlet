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

<link href="<c:url value="/css/feedback.css"/>" rel="stylesheet" type="text/css" />
<style>

            /*
            Max width before this PARTICULAR table gets nasty
            This query will take effect for any screen smaller than 768px
            and also iPads specifically.
            */

            /*
             * Table reflow headers for feedback-result in mobile view
             * TODO when CSS4 element() function will land in browsers other than FF -> remove these inline CSS. http://caniuse.com/#feat=css-element-function
             */

            @media
                only screen and (max-width: 768px)  {


                    /*
                    Label the data
                    <th>Population</th>
                    <th>Unique users</th>
                    <th>yes</th>
                    <th>no</th>
                    <th>maybe</th>
                    */
                    .feedback-list td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.list.population"/> :";font-weight:bold; }
                    .feedback-list td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.list.unique.user"/> :";font-weight:bold; }
                    .feedback-list td:nth-of-type(3):before { content: "<spring:message code="feedback.answer.yes"/> :";font-weight:bold; }
                    .feedback-list td:nth-of-type(4):before { content: "<spring:message code="feedback.answer.no"/> :"; font-weight:bold;}
                    .feedback-list td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.stats.answer.maybe"/> [1] :"; font-weight:bold;}

                    /*
                    Label the data
                    <th>Response</th>
                    <th>Page</th>
                    <th>Nom</th>
                    <th>Role</th>
                    <th>Navigateur</th>
                    <th>Date</th>
                    */
                    .feedback-result tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
                    .feedback-result tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
                    .feedback-result td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
                    .feedback-result td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
                    .feedback-result td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
                    .feedback-result td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

            }

            /*
             * Table reflow headers for feedback-result in multi-columns dashboard
             * TODO when CSS4 element() function will land in browser -> remove these inline CSS. http://caniuse.com/#feat=css-element-function
             */

            @media only screen and (min-width: 992px) and (max-width: 3840px), and (min-device-width: 992px) and (max-device-width: 3840px) {

.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-7 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-6 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-5 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-4 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-3 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-2 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}

.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(1):before { content: "<spring:message code="feedback.admin.rowtitle.response"/> :";font-weight:bold; }
.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody tr:not(.bottom) td:nth-of-type(2):before { content: "<spring:message code="feedback.admin.rowtitle.page"/> :";font-weight:bold; }
.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(3):before { content: "<spring:message code="feedback.admin.rowtitle.name"/> :";font-weight:bold; }
.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(4):before { content: "<spring:message code="feedback.admin.rowtitle.role"/> :"; font-weight:bold;}
.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(5):before { content: "<spring:message code="feedback.admin.rowtitle.browser"/> :"; font-weight:bold;}
.portal-page-column.col-md-1 .feedback-portlet.bootstrap-styles-by-6 .col-md-12 table.reflow.feedback-result tbody td:nth-of-type(6):before { content: "<spring:message code="feedback.admin.rowtitle.time"/> :"; font-weight:bold;}


            }

        </style>

<!-- assigning a variable to the name so it can be called in a non-conflicting way -->
<c:set var="n"><portlet:namespace/></c:set>
<script src="<rs:resourceURL value="/rs/jquery/1.11.0/jquery-1.11.0.min.js"/>" type="text/javascript"></script>
<script src="/ResourceServingWebapp/rs/jquery-migrate/1.2.1/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="<rs:resourceURL value="/rs/jqueryui/1.8.24/jquery-ui-1.8.24.min.js"/>" type="text/javascript"></script>

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

      <div class="container-fluid feedback-portlet awesome-bootstrap-checkbox bootstrap-styles-by-6"> <!-- feedback-admin begin -->

<!-- Bootstrap Grid helper
        <button class="btn btn-default btn-xs" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
           <span class="glyphicon glyphicon-th" aria-hidden="true"></span> Bootstrap Grid
        </button>
        <div class="collapse" id="collapseExample">
        <div class="row">
          <div class="col-xs-12 col-sm-4 col-md-2" style="border:1px solid black; background-color:red;">a- md2</div>
          <div class="col-xs-12 col-sm-4 col-md-2" style="border:1px solid black; background-color:yellow;">b- md2</div>
          <div class="col-xs-12 col-sm-4 col-md-2" style="border:1px solid black; background-color:green;">c- md2</div>
          <div class="col-xs-12 col-sm-12 col-md-6" style="border:1px solid black; background-color:grey;">d- md6</div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-8 col-md-4" style="border:1px solid black; background-color:#65DD9F;">a- md4</div>
          <div class="col-xs-12 col-sm-4 col-md-2" style="border:1px solid black; background-color:red;">b- md2</div>
          <div class="col-xs-12 col-sm-12 col-md-6" style="border:1px solid black; background-color:grey;">d- md6</div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-6 col-md-3" style="border:1px solid black; background-color:blue;">e- md3</div>
          <div class="col-xs-12 col-sm-6 col-md-3" style="border:1px solid black; background-color:pink;">f- md3</div>
          <div class="col-xs-12 col-sm-12 col-md-6" style="border:1px solid black; background-color:grey;">d- md6</div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-4 col-md-2" style="border:1px solid black; background-color:red;">g- md2</div>
          <div class="col-xs-12 col-sm-8 col-md-4" style="border:1px solid black; background-color:#33FF66;">h- md4</div>
          <div class="col-xs-12 col-sm-12 col-md-6" style="border:1px solid black; background-color:grey;">d- md6</div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-2 col-md-1" style="border:1px solid black; background-color:#00FFFF;">i- md1</div>
          <div class="col-xs-12 col-sm-10 col-md-5" style="border:1px solid black; background-color:#D8D8D8;">j- md5</div>
          <div class="col-xs-12 col-sm-12 col-md-6" style="border:1px solid black; background-color:grey;">d- md6</div>
        </div>
        </div> end helper -->

        <div class="row">
	  <h3><spring:message code="feedback.admin.title"/></h3>
          <div class="col-xs-12 col-sm-2 col-md-2">
             <c:set var="stats" value="${stats}"/>
             <img src="https://chart.apis.google.com/chart?cht=p&chd=t:${stats.positiveResponses * 100 / stats.totalResponses },${stats.undecidedResponses * 100 / stats.totalResponses },${stats.negativeResponses * 100 / stats.totalResponses }&chco=5cb85c,A5A5A5,d9534f&chs=100x100" alt="<spring:message code='feedback.admin.title'/>" aria-describedby="feeddback-list-summary" />
             <div>&nbsp;</div>
	  </div>
	  <div class="col-xs-12 col-sm-4 col-md-3" id="feeddback-list-summary">
             <span style="font-weight: bold">${ stats.totalResponses }</span> <spring:message code="feedback.admin.stats.total"/><br />
             <span style="font-weight: bold">${ stats.uniqueUsers }</span> <spring:message code="feedback.admin.stats.unique"/><br />
             <span style="font-weight: bold; color: #5cb85c"><fmt:formatNumber value="${ stats.positiveResponses / stats.totalResponses }" type="percent"/></span> <span class="glyphicon glyphicon-ok-sign" style="color:#5cb85c;" aria-hidden="true"></span> <spring:message code="feedback.admin.stats.answer.yes"/><br />
             <span style="font-weight: bold; color: #d9534f"><fmt:formatNumber value="${ stats.negativeResponses / stats.totalResponses }" type="percent"/></span> <span class="glyphicon glyphicon-remove-sign" style="color:#d9534f;" aria-hidden="true"></span> <spring:message code="feedback.admin.stats.answer.no"/><br />
             <span style="font-weight: bold; color: #A5A5A5"><fmt:formatNumber value="${ stats.undecidedResponses / stats.totalResponses }" type="percent"/></span> <span class="glyphicon glyphicon-question-sign" style="color:#A5A5A5;" aria-hidden="true"></span> <spring:message code="feedback.admin.stats.answer.maybe"/><br />
             <div>&nbsp;</div>
          </div>
          <div class="col-xs-12 col-sm-6 col-md-7"><!-- col-md-7 begin -->
             <table class="reflow feedback-list table-striped">
                <caption><spring:message code="feedback.admin.list.caption"/></caption>
                <thead>
                   <tr>
                      <th scope="col"><spring:message code="feedback.admin.list.population"/></th>
                      <th scope="col"><spring:message code="feedback.admin.list.unique.user"/></th>
                      <th scope="col"><spring:message code="feedback.answer.yes"/></th>
                      <th scope="col"><spring:message code="feedback.answer.no"/></th>
                      <th scope="col"><spring:message code="feedback.admin.stats.answer.maybe"/><sup> <a href="#${n}feedback-list-hearder-anchor">[1]</a></sup></th>
                   </tr>
                </thead>
                <tbody>
                   <c:forEach items="${ overallstats }" var="entry">
                      <tr>
                         <td><span style="text-transform:capitalize">${ entry.key }</span>&nbsp;</td>
                         <td><span style="text-transform:capitalize">${ entry.value.uniqueUsers }</span>&nbsp;</td>
                         <td><fmt:formatNumber value="${ entry.value.positiveResponses / entry.value.totalResponses }" type="percent"/></td>
                         <td><fmt:formatNumber value="${ entry.value.negativeResponses / entry.value.totalResponses }" type="percent"/></td>
                         <td><fmt:formatNumber value="${ entry.value.undecidedResponses / entry.value.totalResponses }" type="percent"/></td>
                      </tr>
                   </c:forEach>
                </tbody>
             </table>
             <p><a id="${n}feedback-list-hearder-anchor" name="${n}feedback-list-hearder-anchor">[1]</a>&nbsp: <spring:message code="feedback.answer.maybe"/></p>
          </div><!-- col-md-7 end -->
       </div><!-- row end -->

       <div class="row">
          <hr class="col-xs-12 col-sm-12 col-md-12" />
       </div>

        <portlet:actionURL var="postUrl"></portlet:actionURL>
          <form:form  action="${ postUrl }" modelAttribute="viewFeedbackForm">
             <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6" style="min-height:50px;max-width:410px"><!-- col-xs-12 begin -->
                   <div class="form-group">
                      <label class="sr-only" for="${n}itemsShown"><spring:message code="feedback.admin.form.show"/></label>
                      <div class="input-group">
                        <div class="input-group-addon" style="min-width:75px"><spring:message code="feedback.admin.form.show"/></div>
                        <form:select cssClass="form-control" id="${n}itemsShown" path="items">
                           <form:option value="10"/>
                           <form:option value="20"/>
                           <form:option value="50"/>
                        </form:select>
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="sr-only" for="${n}userRoleShown"><spring:message code="feedback.admin.form.role"/></label>
                      <div class="input-group">
                        <div class="input-group-addon" style="min-width:75px"><spring:message code="feedback.admin.form.role"/></div>
                        <form:select cssClass="form-control" id="${n}userRoleShown" path="userrole">
                           <form:option value="" label="all"><spring:message code="feedback.admin.type.all"/></form:option>
                           <form:option value="student"><spring:message code="feedback.admin.type.student"/></form:option>
                           <form:option value="staff"><spring:message code="feedback.admin.type.staff"/></form:option>
                           <form:option value="faculty"><spring:message code="feedback.admin.type.faculty"/></form:option>
                           <form:option value="researcher"><spring:message code="feedback.admin.type.researcher"/></form:option>
                           <form:option value="others"><spring:message code="feedback.admin.type.others"/></form:option>
                           <form:option value="guests"><spring:message code="feedback.admin.type.guests"/></form:option>
                         </form:select>
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="sr-only" for="${n}feedbackTypeShown"><spring:message code="feedback.admin.form.type"/></label>
                      <div class="input-group">
                        <div class="input-group-addon" style="min-width:75px"><spring:message code="feedback.admin.form.type"/></div>
                        <form:select cssClass="form-control" id="${n}feedbackTypeShown" path="feedbacktype">
                           <form:option value="" label="all"><spring:message code="feedback.admin.stats.answer.all"/></form:option>
                           <form:option value="YES" ><spring:message code="feedback.admin.stats.answer.yes"/></form:option>
                           <form:option value="NO" label="negative"><spring:message code="feedback.admin.stats.answer.no"/></form:option>
                           <form:option value="MAYBE" label="undecided"><spring:message code="feedback.admin.stats.answer.maybe"/></form:option>
                        </form:select>
                     </div>
                   </div>
                   <div class="form-group" style="min-height:30px;padding-top:10px">
                      <div class="checkbox checkbox-inline">
                         <input id="comments" name="comments" type="checkbox" value="yes" <c:if test="${comments==true}"> checked="checked"</c:if> />
                         <label for="comments"><spring:message code="feedback.admin.form.comments"/></label>
                      </div>
                   </div>
                </div><!-- col-xs-12 end -->
                <div class="col-xs-12 col-sm-6 col-md-6" style="min-height:50px;"><!-- col-xs-12 begin -->
                   <div id="${n}datePicker">
                      <errorElement id="${n}datePickerError"></errorElement>
                      <p style="font-weight:bold; min-width:120px; line-height: 35px; margin-bottom:15px;"><spring:message code="feedback.admin.filter.title"/>:</p>
                      <div class="form-group">
                         <label class="sr-only" for="${n}datepicker1"><spring:message code="feedback.admin.filter.startdate"/></label>
                         <div class="input-group">
                           <div class="input-group-addon" style="min-width:120px"><spring:message code="feedback.admin.filter.startdate"/></div>
                           <form:input path="startDisplayDate" id="${n}datepicker1" cssClass="form-control" />
                         </div>
                      </div>
                      <div class="form-group">
                         <label class="sr-only" for="${n}datepicker2"><spring:message code="feedback.admin.filter.enddate"/></label>
                         <div class="input-group">
                           <div class="input-group-addon" style="min-width:120px"><spring:message code="feedback.admin.filter.enddate"/></div>
                           <form:input path="endDisplayDate" id="${n}datepicker2" cssClass="form-control" />
                         </div>
                      </div>
                   </div>
                   <div style="min-height:30px;margin-top:10px">
                      <div class="form-group">
                         <button type="submit"class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span> <spring:message code="feedback.admin.form.submit"/></button>
                      </div>
                   </div>
                </div><!-- col-xs-12 end -->
             </div>
             <div class="row">
                 <div class="col-xs-12 col-sm-6 col-md-6" style="min-height:50px;max-width:410px">
                    <div class="form-inline form-group">
                       <label class="sr-only" for="${n}pagingBox"><spring:message code="feedback.admin.form.jumpto"/></label>
                       <div class="input-group pull-left" style="max-width:300px">
                           <div class="input-group-addon" style="min-width:120px"><spring:message code="feedback.admin.form.jumpto"/></div>
                           <input type="text" id=${n}pagingBox path="start" class="form-control" />
                       </div>
                       <button type="button"  class="btn btn-default pull-left" id=${n}pagingGoButton><spring:message code="feedback.admin.form.goto"/></button>
                    </div>
                 </div>
                 <div class="col-xs-12 col-sm-6 col-md-6" style="min-height:30px;">
                   <p style="float:left;margin-top:8px;margin-right:8px;">
                      <spring:message code="feedback.admin.form.showing"/>
                      <span style="font-weight: bold;">${ start + 1 } - ${ (start + items) > totalItems ? totalItems : start + items }</span> <spring:message code="feedback.admin.form.of"/> <span style="font-weight: bold;">${ totalItems }</span>
                   </p>
                   <ul class="pagination" style="float:left; margin:0">
                      <c:if test="${ start > 0 }">
                         <li><a href="<portlet:renderURL><portlet:param name="start" value="${ start - items }"/></portlet:renderURL>"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>&nbsp;<spring:message code="feedback.admin.form.prev"/></a></li>
                      </c:if>
                  <!--<c:if test="${ start > 0 and start + items < totalItems }">|</c:if>-->
                      <c:if test="${ start + items < totalItems }">
                         <li><a href="<portlet:renderURL><portlet:param name="start" value="${ start + items }"/></portlet:renderURL>"><spring:message code="feedback.admin.form.next"/>&nbsp;<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
                       </c:if>
                    </ul>
                 </div>
            </div><!-- end row -->
          </form:form>
          <div class="col-xs-12 col-sm-12 col-md-12">&nbsp;</div>
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12">
          <table class="reflow feedback-result table-striped-by-2" cellspacing="0">
             <caption><spring:message code="feedback.admin.rowtitle.caption"/></caption>
             <thead>
                <tr>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.response"/></th>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.page"/></th>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.name"/></th>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.role"/></th>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.browser"/></th>
                   <th scope="col"><spring:message code="feedback.admin.rowtitle.time"/></th>
		</tr>
             </thead>
             <tbody>
		<c:forEach items="${feedback}" var="item" varStatus="status">
                <tr class="${ status.index % 2 == 0 ? 'main' : 'alt' }" style="border-bottom:none;">
                   <td>
                      <c:if test="${ fn:toLowerCase(item.feedbacktype) == 'yes' }">
                         <span class="glyphicon glyphicon-ok-sign" style="color:#5cb85c;font-size:18px" title="yes" aria-label="yes"></span>&nbsp;<spring:message code="feedback.answer.yes"/>
                      </c:if>
                      <c:if test="${ fn:toLowerCase(item.feedbacktype) == 'no' }">
                         <span class="glyphicon glyphicon-remove-sign" style="color:#d9534f;font-size:18px" title="no" aria-label="no"></span>&nbsp;<spring:message code="feedback.answer.no"/>
                      </c:if>
                      <c:if test="${ fn:toLowerCase(item.feedbacktype) == 'maybe' }">
                         <span class="glyphicon glyphicon-question-sign" style="color:#A5A5A5;font-size:18px" title="maybe" aria-label="maybe"></span>&nbsp;<spring:message code="feedback.admin.stats.answer.maybe"/>
                      </c:if>
                   </td>
                   <td><c:out value="${ item.tabname }"/>&nbsp;</td>
                   <c:choose>
                      <c:when test="${ not empty item.useremail }">
                         <td>${ item.username } <a href="mailto:${ item.useremail }"><span class="glyphicon glyphicon-envelope" aria-label="email"></span></a></td>
                      </c:when>
                      <c:when test="${ not empty item.username }">
                         <td>${ item.username }</td>
                      </c:when>
                      <c:otherwise>
                         <td><spring:message code="feedback.admin.anonymous"/></td>
                      </c:otherwise>
                   </c:choose>
                   <td style="text-transform:capitalize">${ item.userrole }&nbsp;</td>
                   <td>${ item.browserAbbreviation }</td>
                   <td><fmt:formatDate value="${ item.submissiontime }"/></td>
                </tr>
                <tr class="${ status.index % 2 == 0 ? 'main' : 'alt' } bottom">
                   <th scope="row" class="row-bottom-header"><spring:message code="feedback.admin.rowheader.comment"/></td>
                   <td colspan="5"><c:out value="${ item.feedback }"/></td>
                </tr>
		</c:forEach>
             </tbody>
          </table>
          </div>
       </div>
          <div>
             <br/>
          </div>
          <p>
             <a role="button" class="btn btn-default"  href="<c:url value="/excelFeedback"/>" target="_blank"><img src="<c:url value="/images/excel.png"/>" alt=""/> <spring:message code="feedback.admin.export"/></a>
          </p>
      </div><!-- feedback-portlet end -->
