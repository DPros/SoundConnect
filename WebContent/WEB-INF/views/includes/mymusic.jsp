<%@include file="../settings.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/17/16
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<script src="js/script.js"></script>


<c:forEach items="${myaudios}" var="audio">
    <div class="row search-results" id="search-results/${audio.id}">
        <!-- <a href="${audio.source}">${audio.artist} - ${audio.title }</a>
		 -->
        <div class="col-sm-2">
            <audio id="player/${audio.id}" preload="none">
     <          <source src="${audio.source}" type="audio/mp3" />
            </audio>
            <a href="javascript:void(0)" class="btn btn-link" id="previewbtn" data-toggle="tooltip" title="Preview" onclick="clickPreviewPlay(${audio.id})">
                <span class="glyphicon glyphicon-play" id="play-glyph/${audio.id}"></span>
            </a>
            <div class="volume">
                <span class="btn btn-lg btn-link glyphicon glyphicon-volume-down" id="volume-glyph/${audio.id}"></span>
            </div>
        </div>
        <div class="col-sm-8">
            <p class="row inner-row track-data">${audio.artist}- ${audio.title }</p>
            <div class="row inner-row" id="slider">
                <input class="volume-bar" id="rangeinput" type="range" />
                <span class="highlight"></span>
            </div>
        </div>
        <div class="col-sm-1">
            <button value='{"id":${audio.id},"source":"${audio.source}","length":${audio.length},"title":"${audio.title}","artist":"${audio.artist}","genre":${audio.genre}}' class="audio-remove-from-user btn btn-link" data-toggle="tooltip" title="Remove from your playlist">
                <span class="glyphicon glyphicon-minus" id="remove-from-user-glyph"></span>
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

