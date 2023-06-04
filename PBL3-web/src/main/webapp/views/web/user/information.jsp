<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="editUserUrl" value="/user-information.html">
</c:url>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin người dùng</title>
    <meta charset="utf-8">
    <style>

        .tab button:hover {
            opacity: 1;
        }
        /* Style cho tab */

        /* Style cho button chọn tab */
        .tab button {
            background-color: #006dcc;
            cursor: pointer;
            width: 100%;
            border-radius: 20px;
            padding: 10px;
            margin-top: 6px;
            font-size: 1.2em;
            opacity: 0.8;
            transition: 0.4s;
        }

        /* Style cho button chọn tab khi được click */
        .tab button.active {
            background-color: #ccc;
            color: #000;
        }

        /* Style cho nội dung của tab */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }

        /* Style cho form */
        form {
            background-color: transparent;
            padding: 20px;
            margin-top: 20px;
            width: 50%;
            margin-left: auto;
            margin-right: auto;
            border-radius: 20px;
            backdrop-filter: blur(20px);
            border: 2px solid rgba(255,255,255,.5);
            box-shadow: 0 0 30px rgba(0,0,0,.5);
        }

        /* Style cho các label */
        label {
            display: block;
            margin-bottom: 8px;
        }

        /* Style cho input */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 8px 8px;
            margin: 0px 0px 12px;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
            border-radius: 6px;
        }
        select#gender {
            margin-bottom: 12px;
        }
        input[type=number], input[type=tel] {
            margin-bottom: 12px;
            border-radius: 6px;
        }
        input[type="submit"] {
            margin-top: 12px;
            width: 100%;
            height: 40px;
            background: #f5f5f5;
            color: #000;
            font-size: 1.2em;
            font-weight: 500;
            border-radius: 16px;
            opacity: 0.9;
        }
        input[type="submit"]:hover {
            opacity: 1;
        }

        /* Style cho button */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        /* Style cho button khi hover */
        button:hover {
            opacity: 0.8;
        }
        #myForm label {
            color: #000;
            font-weight: 500;
            margin-bottom: 4px;
            font-size: 1.1em;
        }
        /*div#contact-info {*/
        /*    background: linear-gradient(185.46deg, #A293EC 23.92%, #5F67EC 112.43%);*/
        /*}*/

    </style>
</head>
<body>
<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'contact-info')"><fmt:message key="label.information" bundle="${lang}"/></button>
    <button class="tablinks" onclick="openTab(event, 'change-password')"><fmt:message key="label.password.change" bundle="${lang}"/></button>
</div>

<div id="contact-info" class="tabcontent">
    <a style="font-size: 2.2em; font-weight: 600; "><fmt:message key="label.information" bundle="${lang}"/></a>
    <form id="myForm" action="${editUserUrl}" method="post" >
        <label for="name"><fmt:message key="label.name" bundle="${lang}"/></label>
        <input type="text" id="name" name="name" required value="${item.pojo.name}"><br>

        <label for="age"><fmt:message key="label.age" bundle="${lang}"/></label>
        <input type="number" id="age" name="age" required value="${item.pojo.age}"><br>

        <label for="gender"><fmt:message key="label.gender" bundle="${lang}"/></label>
        <select id="gender" name="gender" required >
            <option value="male" ${item.pojo.gender == 'male' ? 'selected' : ''}>Male</option>
            <option value="female" ${item.pojo.gender == 'female' ? 'selected' : ''}>Female</option>
        </select><br>

        <label for="address"><fmt:message key="label.address" bundle="${lang}"/></label>
        <input type="text" id="address" name="address" required value="${item.pojo.address}"><br>

        <label for="phone"><fmt:message key="label.phone" bundle="${lang}"/></label>
        <input type="tel" id="phone" name="phone" required value="${item.pojo.phone}"><br>

        <input type="submit" value="Cập nhật thông tin">
        <c:if test="${not empty item.pojo.userId}">
            <input type="hidden" name ="pojo.userId" value="${item.pojo.userId}"/>
            <input type="hidden" name="password" value="${item.pojo.password}"/>
        </c:if>
    </form>
</div>

<div id="change-password" class="tabcontent">
    <h1>Đổi mật khẩu</h1>
    <form>
        <label for="current-password">Mật khẩu hiện tại:</label>
        <input type="password" id="current-password" name="current-password" required><br>

        <label for="new-password">Mật khẩu mới:</label>
        <input type="password" id="new-password" name="new-password" required><br>

        <label for="confirm-password">Xác nhận mật khẩu mới:</label>
        <input type="password" id="confirm-password" name="confirm-password" required><br>

        <input type="submit" value="Đổi mật khẩu">
    </form>
</div>
<script>
    function openTab(evt, tabName) {
        // Get all elements with class="tabcontent" and hide them
        var tabcontent = document.getElementsByClassName("tabcontent");
        for (var i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        var tablinks = document.getElementsByClassName("tablinks");
        for (var i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }
    $(document).ready(function() {
        // Intercept the form submission
        $("#myForm").submit(function(event) {
            // Prevent the form from submitting normally
            event.preventDefault();

            // Send the form data using AJAX
            $.ajax({
                url: $(this).attr("action"),
                type: $(this).attr("method"),
                data: $(this).serialize(),
                success: function(response) {
                    // Handle the successful response
                    alert("Cập nhật thông tin thành công");
                    console.log(response);
                },
                error: function(xhr, status, error) {
                    // Handle the error response
                    console.log(error);
                }
            });
        });
    });
</script>

</body>
</html>