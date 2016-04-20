<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.registerForm.name.focus();'>
	<h1>Register to SoundConnect</h1>

	<div id="register-box">

		<h2>Register below easy!</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='registerForm' action="/SoundConnect/register"
			method='POST'>

			<table>
				<tr>
					<td>Name:</td>
					<td><input type='text' name='name' required></td>
				</tr>
				<tr>
					<td>Login (must be unique):</td>
					<td><input type='text' name='username' required></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' required /></td>
				</tr>
				<tr>
					<td>Confirm the password:</td>
					<td><input type='password' name='passconf' required /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>