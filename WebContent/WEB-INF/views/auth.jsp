<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet"
		  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<!-- Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Amatic+SC:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>
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

	<div class="container">
		<div class="col-sm-5"></div>
		<div class="col-sm-4">
			<div class="row">
				<div class="panel panel-info" id="login-panel">
					<div class="panel-heading">
						<h4 class="panel-title">
							<div>
								<span class="text-help">Welcome!</span>
							</div>
						</h4>
					</div>
						<div class="panel-body">
							<p class="text-primary">Simply sign in via VK.COM</p>
							<div id="login_button" onclick="VK.Auth.login(authInfo);"></div>
						</div>
						<div class="panel-footer">
							<p id="node" class="text-primary" hidden="true">Loading...</p>
							<a id="welcome" hidden="true" class="text-primary" href="/SoundConnect/home">LET'S GO!</a>
						</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>



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
