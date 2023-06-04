<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <title> Registration </title>
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

    .wrapper{
      position: relative;
      width: 400px;
      height: 520px;
      background: transparent;
      border: 2px solid rgba(255,255,255,.5);
      border-radius: 20px;
      backdrop-filter: blur(20px);
      box-shadow: 0 0 30px rgba(0,0,0,.5);
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .wrapper .form-box {
      width: 100%;
      padding: 40px;

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
      top: -5px;
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

<div class="wrapper">
  <div class="form-box register ">
    <h2>Registration</h2>
    <c:if test="${not empty messageResponse}">
      <div class="alert alert-block alert-${alert}">
        <button type="button" class="close" data-dismiss="alert">
          <i class="ace-icon fa fa-times"></i>
        </button>
          ${messageResponse}
      </div>
    </c:if>
    <form action="/registration.html" method="post">
      <div class="input-box">
        <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
        <input type="text" name="name" id="name" required>
        <label>Username</label>
      </div>
      <div class="input-box">
        <span class="icon"><ion-icon name="mail-outline"></ion-icon></span>
        <input type="email" name="email"  required>
        <label>Email</label>
      </div>
      <div class="input-box">
        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
        <input type="password" name="password"  required>
        <label>Password</label>
      </div>
      <div class="remember-forgot">
        <label>
          <input type="checkbox">I agree to the terms & conditions
        </label>
      </div>
      <button type="submit" class="btn">Register</button>
      <div class="login-register">
        <c:url var="loginUrl" value="/login.html">
          <c:param name="action" value="login"/>
        </c:url>
        <p style="font-size: 1.2em">Already have an account? <a href="${loginUrl}">Login</a></p>
      </div>
    </form>
  </div>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>