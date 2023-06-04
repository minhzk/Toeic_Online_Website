<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-notification.html"/>
<html>
<head>
    <title><fmt:message key="label.notification.add" bundle="${lang}"/></title>
    <style>
        .error{
            color:red;
        }
    </style>
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
                <li class="active"><fmt:message key="label.notification.add" bundle="${lang}"/></li>
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
<%--                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id ="formEdit">--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.notification.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="title" id="title"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.notification.content" bundle="${lang}"/></label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input cols="80" rows="10" name="content" id="content"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input id ="btnSubmit" type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}"/>"/>
                            </div>
                        </div>
<%--                    </form>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    $(document).ready(function () {
        CKEDITOR.replace('content');
        $('#btnSubmit').click(function() {
            var title = $('#title').val();
            var content = CKEDITOR.instances.content.getData();
            $.ajax({
                url: '/admin-notification.html', // đường dẫn đến controller
                type: 'post', // phương thức HTTP
                data: {
                    title: title,
                    content: content,
                }, // dữ liệu gửi lên

                success: function(response) {
                    alert('Email sent successfully');
                    location.reload();
                },
                error: function(xhr) {
                    alert('Email sent unsuccessfully');
                }
            });
        });
    });

</script>
</body>
</html>
