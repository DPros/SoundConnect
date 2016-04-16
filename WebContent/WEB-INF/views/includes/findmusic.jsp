<%@include file="../settings.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>


<c:forEach items="${audios}" var="audio">
	<div class="row search-results">
		<!-- <a href="${audio.source}">${audio.artist} - ${audio.title }</a>
		 -->
		<div class="col-sm-2">
			<audio id="player/${audio.id}" preload="none">
				<source src="${audio.source}" type="audio/mp3" />
			</audio>
			<a href="javascript:void(0)" class="btn btn-link" id="previewbtn" data-toggle="tooltip" title="Preview" onclick="clickPreviewPlay(${audio.id})">
				<span class="glyphicon glyphicon-play" id="play-glyph/${audio.id}"></span>
			</a>
		</div>
		<div class="col-sm-8">
			<p class="track-data">${audio.artist}- ${audio.title }</p>
		</div>
		<div class="col-sm-1">
			<button value='{"id":${audio.id},"source":"${audio.source}","length":${audio.length},"title":"${audio.title}","artist":"${audio.artist}","genre":${audio.genre}}' class="audio-add-to-user btn btn-link" data-toggle="tooltip" title="Add to your playlist">
				<span class="glyphicon glyphicon-plus" id="add-to-user-glyph"></span>
			</button>
		</div>
		<div class="col-sm-1">
			<button value='{"id":${audio.id},"source":"${audio.source}","length":${audio.length},"title":"${audio.title}","artist":"${audio.artist}","genre":${audio.genre}}' class="audio-add-to-conference btn btn-link" data-toggle="tooltip" title="Send to the conference">
				<span class="glyphicon glyphicon-send" id="add-to-conf-glyph"></span>
			</button>
		</div>
	</div>
	<br>
</c:forEach>
