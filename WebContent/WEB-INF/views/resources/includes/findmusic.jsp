<%@include file="../settings.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="input-group col-md-12" id="custom-search-input">
	<form action="search" method="post" >
		<input type="search" class="search-query form-control"
			placeholder="Search" name="query" /> <span class="input-group-btn">
			<button type="submit" class="btn btn-primary" id="searchbtn">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</span>
	</form>
	<br>
	<div id="resultDiv">
	<c:forEach items="${audios}" var="audio">
        <a href=\"${audio.source}\">${audio.artist} - ${audio.title }</a><br>
      </c:forEach>
	</div>
</div>
<div class="col-md-12" id="search-results">Insert search results
	here</div>
