<%@include file="../settings.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>

<c:forEach items="${followedusers}" var="user">
	<div class="row search-results" id="search-results/${user.id}">
		<div class="col-sm-2">
			<img src="profilepicture.png" />
		</div>
		<div class="col-sm-8">
			<p class="row inner-row track-data text-primary">${user.name}</p>
		</div>
		<div class="col-sm-1">
			<button class="follow btn btn-link" data-toggle="tooltip"
				onclick=unfollowUser('{"id":${user.id}}') title="Unfollow">
				<span class="glyphicon glyphicon-minus" id="follow-glyph"></span>
			</button>
		</div>
		<div class="col-sm-1">
			<button class="user-add-to-conference btn btn-link"
				data-toggle="tooltip" onclick=connectToUsersConf('{"id":${user.id}}') title="Connect to his conference">
				<span class="glyphicon glyphicon-magnet" id="send-to-conf-glyph"></span>
			</button>
		</div>
	</div>
	<br>
</c:forEach>