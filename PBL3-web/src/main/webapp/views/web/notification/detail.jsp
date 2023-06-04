<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
    <style>
        .container.notification-container {
            margin-top: 72px;
        }
    </style>
</head>
<body>
    <div class="container notification-container">
        <c:forEach items="${items}" var="list">
            <h4 style="color: red" id="name_member">${list.createdDate}</h4>
            <h4 style="color: black" id="name_member">${list.title}</h4>
            <textarea disabled class="input-xxlarge showtext" rows="3"
                      name="cmtvocabularycontent">${list.content}</textarea>
        </c:forEach>
    </div>
</body>
</html>
