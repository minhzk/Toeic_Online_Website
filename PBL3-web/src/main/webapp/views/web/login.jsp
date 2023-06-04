
<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:url var="formUrl" value="/login.html"/>
<html>
<head>
    <title>Login Page</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'poppins', sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            width: 100%;
            background: url('/template/web/img/background-moutain.jpg') no-repeat;
            background-size: cover;
            background-position: center;

        }

        .widget-main{
            position: relative;
            width: 400px;
            height: 440px;
            background: transparent;
            border: 2px solid rgba(255,255,255,.5);
            border-radius: 20px;
            backdrop-filter: blur(20px);
            box-shadow: 0 0 30px rgba(0,0,0,.5);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .wrapper.active-popup {
            transform: scale(1);
        }

        .wrapper.active {
            height: 520px;
        }

        .widget-main .form-box {
            width: 100%;
            padding: 40px;

        }

        .wrapper .form-box.login {
            transition: transform .18s ease;
            transform: translateX(0);
        }

        .wrapper.active .form-box.login{
            transition: none;
            transform: translateX(-400px);
        }

        .wrapper .form-box.register {
            position: absolute;
            transition: none;
            transform: translateX(400px);
        }
        .wrapper.active .form-box.register {
            transition: transform .18s ease;
            transform: translateX(0);
        }

        .widget-main .icon-close {
            position: absolute;
            top: 0;
            right: 0;
            width: 45px;
            height: 45px;
            background: #162938;
            font-size: 2em;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            border-bottom-left-radius: 20px;
            cursor: pointer;
            z-index: 1;
            opacity: 0.8;
        }

        .wrapper .icon-close:hover {
            opacity: 1;
        }

        .form-box h2 {
            font-size: 2em;
            color: #162938;
            text-align: center;
        }

        .input-box {
            position: relative;
            width: 100%;
            height: 50px;
            border-bottom: 2px solid #162938;
            margin: 30px 0;
        }
        .input-box label {
            position: absolute;
            top: 50%;
            left: 5px;
            transform: translateY(-50%);
            font-size: 1em;
            color: #162938;
            font-weight: 500;
            pointer-events: none;
            transition: .5s;

        }
        .input-box input:focus~label,
        .input-box input:valid~label{
            top: -3px;
        }

        .input-box input {
            width: 100%;
            height: 100%;
            background: transparent;
            border: none;
            outline: none;
            font-size: 1em;
            color: #162938;
            font-weight: 600;
            padding: 0 35px 0 5px;

        }

        .input-box .icon {
            position: absolute;
            right: 8px;
            font-size: 1.2em;
            color: #162938;
            line-height: 57px;
        }

        .login-with-gg .gg-icon {
            position: absolute;
            color: rgb(244, 56, 56);
            font-size: 1.2em;
            left: 22%;
            top: 12px;
        }

        .remember-forgot {
            font-size: .9em;
            color: #162938;
            font-weight: 500;
            margin: -15px 0 15px;
            display: flex;
            justify-content: space-between;
        }

        .remember-forgot label input {
            accent-color: #162938;
            margin-right: 3px;
        }

        .remember-forgot a {
            color: #162938;
            text-decoration: none;
        }

        .remember-forgot a:hover {
            text-decoration: underline;
        }

        .btn {
            position: relative;
            width: 100%;
            height: 45px;
            background: #162938;
            border: none;
            outline: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1em;
            color: #fff;
            font-weight: 500;
        }

        .login-register {
            font-size: 0.9em;
            color: #162938;
            text-align: center;
            font-weight: 500;
            margin: 25px 0 10px;
        }

        .login-register p a {
            color: #162938;
            text-decoration: none;
            font-weight: 600;
        }

        .login-register p a:hover {
            text-decoration: underline;
        }
        input:-webkit-autofill,
        input:-webkit-autofill:hover,
        input:-webkit-autofill:focus,
        input:-webkit-autofill:active {
            transition: background-color 5000s ease-in-out 0s;
        }

    </style>
</head>
<body>
    <div id="login-box" class="login-box" style="">
        <div class="widget-body" style="">
            <div class="widget-main">
                <form action="${formUrl}" method="post" class="form-box">
                    <h2>Login</h2>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}" style="display: flex;border-radius: 10px; border: 2px solid red; justify-content: center;align-content: center; padding: 10px; margin-top: 20px">
                                ${messageResponse}
                        </div>
                        <script>
                            const widgetMain = document.querySelector('.widget-main');

                            widgetMain.classList.add('error');

                        </script>
                    </c:if>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="mail-outline"></ion-icon></span>
                        <input type="email" name="pojo.email"  required>
                        <label >Email</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                        <input type="password" name="pojo.password" required>
                        <label >Password</label>
                    </div>
                    <div class="remember-forgot">
                        <label>
                            <input type="checkbox">Remember me
                        </label>
                        <a href="#">Forgot Password?</a>
                    </div>
                    <button type="submit" class="btn">Login</button>
                    <div class="login-register">
                        <p style="font-size: 1.2em;">Don't have a account? <a href="/registration.html" class="register-link">Register</a></p>
                    </div>
                </form>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
</div><!-- /.login-box -->
</body>
</html>
