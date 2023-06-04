<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title><dec:title default="Login page"/></title>
    <!-- page specific plugin styles -->
    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/fonts/fonts.googleapis.com.css'/>"/>
    <!-- ace settings handler -->
    <script src="<c:url value='/template/admin/assets/js/ace-extra.min.js'/>"></script>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <dec:head/>
</head>
<body class="login-layout">
<%--<%@ include file="/common/admin/menu.jsp"%>--%>
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <%@ include file="/common/login/header.jsp"%>
                    <div class="position-relative">
                        <dec:body/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<%@ include file="/common/admin/footer.jsp"%>--%>

<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>
<!--[if lte IE 8]>
    <script src="<c:url value='/template/admin/assets/js/excanvas.min.js'/>"></script>
    <![endif]-->
<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js'/>"></script>
<!-- ace scripts -->
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js'/>"></script>
</body>
</html>
