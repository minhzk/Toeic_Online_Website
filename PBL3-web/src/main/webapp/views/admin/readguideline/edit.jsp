<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-guideline-read-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.guideline.read.edit" bundle="${lang}"/></title>
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
                <li class="active"><fmt:message key="label.guideline.read.edit" bundle="${lang}"/></li>
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
                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id ="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.read.guideline.title" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.title" id="title" value="${item.pojo.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id ="uploadImage"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.readguideline.upload.image.view" bundle="${lang}"/></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.image}">
                                    <c:set var="image" value="/repository/${item.pojo.image}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150ox">
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.read.guideline.content" bundle="${lang}"/></label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.content}">
                                    <c:set var="content" value="${item.pojo.content}"/>
                                </c:if>
                                <textarea name="pojo.content" cols="80" rows="10" id="readGuidelineContent">${content}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}"/>"/>
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.readGuidelineId}">
                            <input type="hidden" name="pojo.readGuidelineId" value="${item.pojo.readGuidelineId}"/>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    var readGuidelineId = '';
    <c:if test="${not empty item.pojo.readGuidelineId}">
    readGuidelineId = ${item.pojo.readGuidelineId};
    </c:if>
    $(document).ready(function () {
        CKEDITOR.replace('readGuidelineContent');
        validateData();
        $('#uploadImage').change(function () {
            readURL(this, "viewImage");
        });
    });
    function validateData() {
        $('#formEdit').validate({
            ignore:[],
            rules:[] ,
            messages: []
        });
        $( "#title" ).rules( "add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.empty"  bundle="${lang}"/>'
            }
        });
        if(readGuidelineId == '') {
            $( "#uploadImage" ).rules( "add", {
                required: true,
                messages: {
                    required: '<fmt:message key="label.empty"  bundle="${lang}"/>'
                }
            });
        }
        $( "#readGuidelineContent" ).rules( "add", {
            required: function () {
                CKEDITOR.instances.readGuidelineContent.updateElement();
            },
            messages: {
                required: '<fmt:message key="label.empty"  bundle="${lang}"/>'
            }
        });
    }
    function readURL(input, imageId)  {
        if(input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageId).attr('src',reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
