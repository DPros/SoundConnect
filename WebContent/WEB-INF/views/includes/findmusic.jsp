<%@include file="../settings.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>

<c:forEach items="${audios}" var="audio">
	<a href=\"${audio.source}\">${audio.artist} - ${audio.title }</a><br>
</c:forEach>
