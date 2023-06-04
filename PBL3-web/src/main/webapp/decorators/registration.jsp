<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
  <title><dec:title default="registration page"/></title>
  <dec:head/>
</head>
<body>
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <%@ include file="/common/registration/header.jsp"%>
          <div class="position-relative">
            <dec:body/>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
