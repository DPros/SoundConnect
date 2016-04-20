<%@include file="settings.jsp"%>
<%@ page import="java.util.List" %>

<c:if test="${not empty conference.tracks}">
	<script>
		var track = {
				name: "${conference.tracks[0].title}",
				artist: "${conference.tracks[0].artist}",
				startTime: "${time}",
				length: "${conference.tracks[0].length}",
				id: "${conference.tracks[0].id}",
				ownerId: "${conference.tracks[0].ownerId}"
		};
	</script>

	<c:forEach items="${conference.tracks}" var="track">
		${track.title } - ${track.artist } </br>
	</c:forEach>
</c:if>