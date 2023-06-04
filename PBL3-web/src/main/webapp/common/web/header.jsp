<%@include file="/common/taglib.jsp"%>
<!--HEADER ROW-->
<div id="header-row">
    <div class="container">
        <div class="row">
            <!--LOGO-->
            <div class="span2"><a class="brand" href="#"><img src="/template/web/img/toeic-logo.png"/></a></div>
            <!-- /LOGO -->

            <!-- MAIN NAVIGATION -->
            <div class="span10">
                <div class="navbar  pull-right">
                    <div class="navbar-inner">
                        <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
                                <li class="active"><a href="/home.html">Home</a></li>

                                <li class="dropdown">
                                    <a href="about.html" class="dropdown-toggle" data-toggle="dropdown">About <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="about.html">Company</a></li>
                                        <li><a href="about.html">History</a></li>
                                        <li><a href="about.html">Team</a></li>
                                    </ul>
                                </li>
                                <c:if test="${empty login_name}">
                                    <li class="regis"><a href="/registration.html"><fmt:message key="label.registration" bundle="${lang}"/></a></li>
                                </c:if>
                                <c:if test="${not empty login_name}">
                                <li>
                                    <div id="notificationContainer" style="position: relative;">
                                        <button alt="Avatar" class="notification" style="color: dimgrey"><ion-icon name="notifications"></ion-icon></button>
                                        <ul id="notificationList" class="dropdown-menu" style="display: none; position: absolute; top: 100%; right: 0;">
                                            <li><a href="/notification.html"><fmt:message key="notification" bundle="${lang}"/></a></li>
<%--                                                <li><a href="#">Notification 2</a></li>--%>
<%--                                                <li><a href="#">Notification 3</a></li>--%>
                                        </ul>
                                    </div>
                                </li>

                                <li>
                                    <div id="avatarContainer" style="position: relative; cursor: pointer;">
                                        <img src="/image/avatar.png" alt="Avatar" class="img-circle" width="40px" height="40px" >
                                        <ul id="myList" class="dropdown-menu" style="display: none;position: absolute; top: 100%; right: 0;">
                                            <li><a href="/user-information.html"><fmt:message key="label.information" bundle="${lang}"/></a></li>
                                            <li><a href="/user-result.html"><fmt:message key="label.result" bundle="${lang}"/></a></li>
                                            <c:url var="logoutUrl" value="/logout.html">
                                                <c:param name="action" value="logout"/>
                                            </c:url>
                                            <li><a href="${logoutUrl}"><fmt:message key="label.logout" bundle="${lang}"/></a></li>
                                        </ul>
                                    </div>
                                </li>
                                </c:if>
                                <c:if test="${empty login_name}">
                                    <c:url var="loginUrl" value="/login.html">
                                        <c:param name="action" value="login"/>
                                    </c:url>
                                    <li><a href="${loginUrl}"><fmt:message key="label.login" bundle="${lang}"/></a></li>
                                </c:if>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
            <!-- MAIN NAVIGATION -->
        </div>
    </div>
</div>
<!-- /HEADER ROW -->