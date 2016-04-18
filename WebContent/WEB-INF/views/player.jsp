<%@include file="settings.jsp"%>

<c:if test="${not empty conference.tracks}">
	<script>
		var track = {
				name: "${conference.tracks[0].title}",
				artist: "${conference.tracks[0].artist}",
				startTime: "${time}",
				length: "${conference.tracks[0].length}"
		};
	</script>

	<c:forEach items="${conference.tracks}" var="track" varStatus="status" begin="1">
		${track.title } - ${track.artist }
	</c:forEach>
</c:if>