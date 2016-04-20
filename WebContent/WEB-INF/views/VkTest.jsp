<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<head>
<title>VK Sound check</title>
</head>
<boddy>
<script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<div id="login_button" onclick="VK.Auth.login(authInfo);"></div>

<script language="javascript">
VK.init({
    apiId: 5368927
  });
function authInfo(response) {
  if (response.session) {
    alert('user: '+response.session.mid);
  } else {
    alert('not auth');
  }
}
VK.Auth.getLoginStatus(authInfo);
VK.UI.button('login_button');
</script>
<script type="text/javascript">
  
 
  function getVKurl(query){
	  VK.api('audio.search', {q: document.getElementById("qbox").value}, function(data){
	 	if(data.response){
	 		for(var i=1; i<data.response.length; ++i){
	 			var sound = data.response[i];
		 	 	var a = document.createElement("A");
		 	 	//console.log(sound);
		 		a.setAttribute("href", sound.url);
		 		a.innerHTML= sound.artist+' - '+sound.title;
		  		document.getElementById("resultDiv").appendChild(a);
		  		document.getElementById("resultDiv").appendChild(document.createElement("br"));
	 		}
	  	}
	  });
  };
  
  function getAudio(object_id, owner, id){
	  var req = owner+'_'+id;
	  VK.api('audio.getById', {audios: req}, function(data){
		  if(data.response){
			  var sound = data.response[0];
		 	 	var a = document.getElementById(object_id);
		 	 	//console.log(sound);
		 		a.setAttribute("src", sound.url);
	 		console.log('OK');
		  }else if(data.error)
		  console.log(data.error);
	  });
  };
</script>
<h1 text-align:"center">VK API search demo</h1>
<br>
<input type="text" id="qbox">
<button onclick="getVKurl()">Search for music via VK api!</button>
<div id="resultDiv"></div> 
<audio id="aud" controls>
</audio>
<button onclick='getAudio(3419630, 73253172)'>click</button>

</body>
</html>