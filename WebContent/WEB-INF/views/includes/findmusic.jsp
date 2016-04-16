<%@include file="../settings.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>

<script src="resources/js/script.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:forEach items="${audios}" var="audio">
	<div>
		<!-- <a href="${audio.source}">${audio.artist} - ${audio.title }</a>
		 -->
		<audio id="player/${audio.id}">
			<source src="${audio.source}" type="audio/mp3" />
		</audio>		
		<a href="javascript:void(0)" class="btn btn-link" id="previewbtn" data-toggle="tooltip" title="Preview" onclick="audioPreview(${audio.id})">
			<span class="glyphicon glyphicon-play"></span>
		</a>
		
		<p>${audio.artist}- ${audio.title }</p>
		<button value="${audio.id}" class="audio-add-to-user">+</button>
		<button value="${audio.id}" class="audio-add-to-conference">add	to conf</button>
	</div>
	<br>
</c:forEach>
