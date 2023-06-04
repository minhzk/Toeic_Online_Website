<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="requestUrl" value="/user-result.html"/>
<html>
<head>
    <title><fmt:message key="label.user.result.list" bundle="${lang}"/></title>
</head>
<body>
<div class="table-responsive">
    <fmt:bundle basename="ResourcesBundle">
        <display:table id="tableList" name="items.listResult" partialList="true" size="${items.totalItems}"
                       pagesize="${items.maxPageItems}" requestURI="${requestUrl}" export="true"
                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                       style="margin: 3em 0 1.5em;">
            <display:column property="examination.examinationId" titleKey="label.examination.name" sortable="true" sortName="title"/>
            <display:column property="listenScore" titleKey="label.listen.score" sortable="true" sortName="content"/>
            <display:column property="readingScore" titleKey="label.read.score" sortable="true" sortName="content"/>
            <display:column property="createdDate" titleKey="lable.result.day" sortable="true" sortName="createdDate" defaultorder="ascending"/>
        </display:table>
    </fmt:bundle>
</div>
</body>
</html>
