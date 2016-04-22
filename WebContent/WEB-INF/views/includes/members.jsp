<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../settings.jsp"%>

           	<c:forEach items="${members }" var="member">
           		<div>${member.username } <a class="glyphicon glyphicon-star" data-holder="${member.id }"></a></div>
           	</c:forEach>

