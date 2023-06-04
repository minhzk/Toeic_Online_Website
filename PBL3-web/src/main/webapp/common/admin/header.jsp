<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<style>
    .navbar {
        background: linear-gradient(185.46deg, #A293EC 23.92%, #5F67EC 112.43%);
        border-radius: 30px;
    }
    .ace-nav>li.light-blue>a {
        background-color: transparent;
        opacity: .9;
    }
    .ace-nav>li.light-blue>a:hover {
        opacity: 1;
        background: transparent;
    }
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-cog" aria-hidden="true"></i>
                    Trang quản trị
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="font-size: 1.4em;">
                        Welcome, ${login_name}

                    </a>
                <li class="light-blue dropdown-modal">
                    <c:url var="logoutUrl" value="/logout.html">
                        <c:param name="action" value="logout"/>
                    </c:url>
                    <a href="${logoutUrl}" style="font-size: 1.4em;">
                        <i class="ace-icon fa fa-power-off"></i>
                        <fmt:message key="label.logout" bundle="${lang}"/>
                    </a>
                </li>
                </li>
            </ul>
        </div>
    </div>
</div>
