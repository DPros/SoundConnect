<%@ page import="org.springframework.web.util.HtmlUtils" %>


<%@include file="../settings.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="btn-group hiddendiv" id="search-btn-group">
	<button type="button" class="btn btn-link" id="pause-search">
		<span class="glyphicon glyphicon-play btn btn-link" id="search-pause-glyph"></span>
	</button>
	<button type="button" class="mute button btn-link" id="mute-search">
		<span class="glyphicon glyphicon-volume-off btn btn-link" id="search-mute-glyph"></span>
	</button>
</div>
<audio id="player/search" src="" currentTime=""></audio>
<div id="progressOut-search">
	<div id="progressIn-search">
		<div id="time-search">00:00</div>
	</div>
</div>

<c:forEach items="${audios}" var="audio">
	<div class="row search-results" id="search-results/${audio.id}">
		<div class="col-sm-2">
			<a href="javascript:void(0)" class="btn btn-link" id="previewbtn" data-toggle="tooltip" title="Preview" onclick="clickPreviewPlay('player/search', ${audio.ownerId}, ${audio.id })">
				<span class="glyphicon glyphicon-play" id="play-glyph/${audio.id}"></span>
			</a>
			<div class="volume">
				<span class="btn btn-lg btn-link glyphicon glyphicon-volume-down" id="volume-glyph/${audio.id}"></span>
			</div>
		</div>
		<div class="col-sm-8">
			<p class="row inner-row track-data text-primary">${audio.artist} - ${audio.title}</p>
			<div class="row inner-row" id="slider">
				<input class="volume-bar" id="rangeinput" type="range" />
				<span class="highlight"></span>
			</div>
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