<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Hello</title>  
</head>  
<body> 
<script src="//vk.com/js/api/openapi.js" type="text/javascript"></script>
<script type="text/javascript">
  VK.init({
    apiId: 5368927
   });
</script> 
<div id="login_button" onclick="VK.Auth.login(authInfo, 8);"></div>

<script language="javascript">
function authInfo(response) {
  if (response.session) {
    console.log('user: '+response.session.mid);
  } else {
    console.log('Not auth: please sign in');
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