<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><fmt:message key="label.home" bundle="${lang}"/> </title>
    <!-- Bootstrap -->
    <link href="<c:url value="/template/web/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/web/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <!--Font-->
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css'>
    <!-- SCRIPT
    ============================================================-->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="<c:url value="/template/web/js/bootstrap.min.js"/>"></script>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <dec:head />
</head>
<body>
<%@ include file="/common/web/header.jsp" %>
<div class="container">
    <dec:body/>
</div>
<%@ include file="/common/web/footer.jsp" %>
<script>
    var container = document.getElementById("avatarContainer");
    var list = document.getElementById("myList");

    container.addEventListener("click", function() {
        if (list.style.display === "none") {
            list.style.display = "block";
        } else {
            list.style.display = "none";
        }
    });
    var notificationContainer = document.getElementById("notificationContainer");
    var notificationList = document.getElementById("notificationList");

    notificationContainer.addEventListener("click", function() {
        if (notificationList.style.display === "none") {
            notificationList.style.display = "block";
        } else {
            notificationList.style.display = "none";
        }
    });

</script>
</body>
</html>
