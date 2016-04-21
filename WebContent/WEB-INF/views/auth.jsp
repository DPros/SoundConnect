<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Hello</title>  
</head>  
<body> 
	<div id="login_button" onclick="VK.Auth.login(authInfo);"></div>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>
<script src="js/script.js"></script>
<script language="javascript">
VK.init({
    apiId: 5368927
  });
function authInfo(response) {
  if (response.session) {
    alert('user: '+response.session.mid);
    vkLogin(response.session.mid);    
  } else {
    alert('not auth');
  }
}
VK.Auth.getLoginStatus(authInfo);
VK.UI.button('login_button');
</script>


<form method="POST" >
<h3>Session creator beta</h3>
<h5>(for testing)</h5>
<input type="text" name="userId" value="Enter user id" onfocus="if (this.value=='Enter user id') this.value='';"/>
<input type="text" name="confId" value="Enter conference id" onfocus="if (this.value=='Enter conference id') this.value='';"/>
<input type="submit" value="Create session!" />
</form>

${session}
<br>
<a href="/SoundConnect/logout"> LOGOUT! </a>

</body>  
</html>  