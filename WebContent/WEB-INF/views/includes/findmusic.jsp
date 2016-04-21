<%@ page import="org.springframework.web.util.HtmlUtils" %>


<%@include file="../settings.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="row hiddendiv" id="searchaudio">
	<div class="btn-group col-sm-4" id="search-btn-group">
		<button type="button" class="btn btn-link" id="pause-search">
			<span class="glyphicon glyphicon-play btn btn-link" id="search-pause-glyph"></span>
		</button>
		<button type="button" class="mute btn btn-link" id="mute-search">
			<span class="glyphicon glyphicon-volume-off btn btn-link" id="search-mute-glyph"></span>
		</button>
	</div>
	<div class="col-sm-8" id="progressOut-search">
		<div id="progressIn-search">
			<div id="time-search">00:00</div>
		</div>
	</div>
	<audio id="player/search" src="" currentTime=""></audio>
</div>

<c:forEach items="${audios}" var="audio">
	<div class="row search-results" id="search-results/${audio.id}">
		<div class="col-sm-2">
			<a href="javascript:void(0)" class="btn btn-link" id="previewbtn" data-toggle="tooltip" title="Preview" onclick="clickPreviewPlay('player/search', ${audio.ownerId}, ${audio.id })">
				<span class="glyphicon glyphicon-play" id="play-glyph/${audio.id}"></span>
			</a>
		</div>
		<div class="col-sm-8">
			<p class="row inner-row track-data text-primary">${audio.artist} - ${audio.title}</p>
		</div>
		<div class="col-sm-1">
			<button class="audio-add-to-user btn btn-link" data-toggle="tooltip" onclick=audioAddToUser('{"id":${audio.id},"ownerId":"${audio.ownerId}","length":${audio.length},"title":"${audio.title}","artist":"${audio.artist}","genre":${audio.genre}}') title="Add to your playlist">
				<span class="glyphicon glyphicon-plus" id="add-to-user-glyph"></span>
			</button>
		</div>
		<div class="col-sm-1">
			<button class="audio-add-to-conference btn btn-link" data-toggle="tooltip" onclick=AudioAddToConference('{"id":${audio.id},"ownerId":"${audio.ownerId}","length":${audio.length},"title":"${audio.title}","artist":"${audio.artist}","genre":${audio.genre}}') title="Send to the conference">
				<span class="glyphicon glyphicon-send" id="add-to-conf-glyph"></span>
			</button>
		</div>
	</div>
	<br>
</c:forEach>