<%@ page import="vn.pbl.core.web.common.WebConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="ResourcesBundle" var="lang"/>
<c:set var="sessionUser" value="<%=WebConstant.LOGIN_NAME%>"/>