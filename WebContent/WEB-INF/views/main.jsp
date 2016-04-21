<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundConnect</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/player/snd.js"></script>
<script src="js/script.js"></script>
<script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>

<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}
</style>
</head>
<body>
<script language="javascript">
VK.init({
    apiId: 5368927
  });
</script>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-3 sidenav" id="left-panel">
				<div class="panel panel-default" id="party-panel">
				    <div class="panel-heading">
				        <h4 class="panel-title">
				            <a id="collapse-conf" data-toggle="collapse" href="#collapse1">
				                <span class="btn btn-link">Conference</span>
				                <span class="glyphicon glyphicon-eye-open btn btn-link" id="conf-glyph"></span>
				            </a>
				        </h4>
				    </div>
				    <div id="collapse1" class="panel-collapse collapse in">
				        <div class="panel-body">
							<audio id="main-player" src="" currentTime="" controls autoplay>
							</audio>
				        </div>
				        <div class="panel-footer scrollfixed" id="music-div">
				            Insert track names here.
				        </div>
				    </div>
				</div>
			</div>
			<div class="col-sm-6 text-left" id="centre-panel">
				<div class="container-fluid" id="navbar-container">
					<nav class="navbar navbar-default navbar-fixed-top">
						<div class="container-fluid">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle"
									data-toggle="collapse" data-target="#myNavbar">
									<span class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="#">Logo</a>
							</div>
							<div class="collapse navbar-collapse" id="myNavbar">
								<ul class="nav navbar-nav">
									<li class="active"><button type="button"
											class="btn btn-link" id="homebtn">Home</button></li>
									<li><button type="button" class="btn btn-link"
											id="findbtn">Find Music</button></li>
									<li><button type="button" class="btn btn-link"
											id="followbtn">Following</button></li>
									<li><button type="button" class="btn btn-link" id="recbtn">Recommended</button></li>
									<li><button type="button" class="btn btn-link"
											id="mymusicbtn">My Music</button></li>
								</ul>
								<ul class="nav navbar-nav navbar-right">
									<li><a href="/SoundConnect/logout"> <span
											class="glyphicon glyphicon-log-in"></span> Log out
									</a></li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
				<div id="homediv" class="visiblediv scrollfixed">
					<h1>Welcome</h1>
					<h2>SoundConnect</h2>
					<p>... is a special service that allows you to communicate with your friends using the only one language -- MUSIC!</p>
					<hr>
					<h3>Test</h3>
					<p>Lorem ipsum...</p>
				</div>
				<div id="finddiv" class="hiddendiv scrollfixed">
					<div class="input-group col-md-12" id="custom-search-input">
						<form action="search" method="post" id="audio-search-form">
							<input type="search" class="search-query form-control"
								placeholder="Search" name="query" /> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-link" id="searchbtn">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</form>
						<br>
						<div id="audio-search-results"></div>
					</div>
				</div>
				<div id="followdiv" class="hiddendiv scrollfixed">
					<div id="my-followings-list"></div>
				</div>
				<div id="recdiv" class="hiddendiv scrollfixed">
					<jsp:include page="includes/recommended.jsp" />
				</div>
				<div id="mymusicdiv" class="hiddendiv scrollfixed">
					<div id="my-music-list"></div>
				</div>
			</div>
			<div class="col-sm-3 sidenav" id="right-panel">
				<jsp:include page="includes/members.jsp" />
			</div>
		</div>
	</div>

</body>
</html>