<%@include file="settings.jsp"%>

<script>
	var currentSong = ${conference.tracks[0]};
</script>

<c:forEach items="${conference.tracks}" var="track" varStatus="status" begin="1">
	${track.title } - ${track.artist }
</c:forEach>