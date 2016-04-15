<%@include file="../settings.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>

<script src="resources/js/script.js"></script>

<c:forEach items="${audios}" var="audio">
	<div>
		<!-- <a href="${audio.source}">${audio.artist} - ${audio.title }</a>
		 -->
		<audio id="player/${audio.id}">
			<source src="${audio.source}" type="audio/mp3" />
		</audio>		
		<a href="javascript:void(0)" class="btn btn-default" data-toggle="tooltip" title="Preview" onclick="audioPreview(${audio.id})"><i id="stateicon" class="fa fa-play"></i></a>
		
		<p>${audio.artist}- ${audio.title }</p>
		<button value="${audio.id}" class="audio-add-to-user">+</button>
		<button value="${audio.id}" class="audio-add-to-conference">add	to conf</button>
	</div>
	<br>
</c:forEach>
