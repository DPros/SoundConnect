<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Hello</title>
</head>
<body>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>
	<script src="js/script.js"></script>


	<h1>WELCOME</h1>
	<h3>Simply sign in via VK.COM</h3>
	<div id="login_button" onclick="VK.Auth.login(authInfo);"></div>
	<br>
	<p id="node" hidden="true">Loading...</p>
	<a id="welcome" hidden="true" href="/SoundConnect/home">LET'S GO!</a>
	<script language="javascript">
		VK.init({
			apiId : 5368927
		});
		function authInfo(response) {
			if (response.session) {
				console.log('user: ' + response.session.mid);
				vkLogin(response.session.mid);
			} else {
				console.log('not auth');
			}
		}
		VK.Auth.getLoginStatus(authInfo);
		VK.UI.button('login_button');
	</script>

</body>
</html>
