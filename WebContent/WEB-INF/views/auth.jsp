<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Hello</title>  
</head>  
<body>  
${message} 
<script src="//vk.com/js/api/openapi.js" type="text/javascript"></script>
<script type="text/javascript">
  VK.init({
    apiId: 5368927
  });
 
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
</script>
<h1 text-align:"center">VK API search demo</h1>
<br>
<input type="text" id="qbox">
<button onclick="getVKurl()">Search for music via VK api!</button>
<div id="resultDiv"></div> 
</body>  
</html>  