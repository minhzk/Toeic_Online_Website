<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="requestUrl" value="/admin-notification.html"/>
<c:url value="/admin-notification.html" var="notificationEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="formUrl" value="/admin-notification.html"/>
<html>
<head>
    <title><fmt:message key="label.notification.list" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.notification.list" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form action="${formUrl}" method="get" id="formUrl">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <c:url var="addUrl" value="/admin-notification.html">
                                                <c:param name="urlType" value="url_edit"/>
                                            </c:url>
                                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" onclick="update(this)" href="${addUrl}" >
                                                    <span>
                                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                    </span>
                                            </a>
                                            <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="deleteAll" disabled onclick="warningBeforeDelete()"
                                                    data-toggle="tooltip" title="<fmt:message key='label.delete.all' bundle='${lang}'/>">
                                                     <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                    </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <fmt:bundle basename="ResourcesBundle">
                                <display:table id="tableList" name="items.listResult" partialList="true" size="${items.totalItems}"
                                               pagesize="${items.maxPageItems}" sort="external" requestURI="${requestUrl}" export="true"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;">
                                    <display:column title="<fieldset class='form-group'>
                                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                                            </fieldset>" class="center select-cell" headerClass="center select-cell">
                                        <fieldset>
                                            <input type="checkbox" name="checkList" id="checkbox_${tableList.notificationId}" value="${tableList.notificationId}" class="check-box-element"/>
                                        </fieldset>
                                    </display:column>
                                    <display:column property="createdDate" titleKey="label.notification.date" sortable="true" sortName="title"/>
                                    <display:column property="title" titleKey="label.notification.title" sortable="true" sortName="content"/>
                                    <display:column property="content" titleKey="label.notification.content" sortable="true" sortName="content"/>
                                </display:table>
                            </fmt:bundle>
                        </div>
                        <input type="hidden" name="urlType" id="urlType" value="url_list"/>
                        <input type="hidden" name="crudaction" id="crudaction"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {

    });
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            $('#crudaction').val('redirect_delete');
            $('#formUrl').submit();
        });
    }
</script>
</body>
</html>