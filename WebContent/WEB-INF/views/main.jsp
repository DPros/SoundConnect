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
	<link rel="stylesheet" type="text/css" href="css/audiostyle.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="css/perfect-scrollbar.css" />
	<!-- Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Amatic+SC:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/notify.js"></script>
	<script src="js/perfect-scrollbar.jquery.js"></script>
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
			<div class="row">
				<div class="container-fluid" id="navbar-container">
					<nav class="navbar navbar-default navbar-fixed-top">
						<div class="container-fluid">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle"
										data-toggle="collapse" data-target="#myNavbar">
									<span class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="#">SoundConnect</a>
							</div>
							<div class="col-sm-4"></div>
							<div class="collapse navbar-collapse" id="myNavbar">
								<ul class="nav navbar-nav col-sm-8	" id="mainNavbar">
									<li class="active central-menu"><button type="button"
															   class="btn btn-link btn-lg" id="homebtn">HOME</button></li>
									<li class="central-menu"><button type="button" class="btn btn-link btn-lg"
												id="findbtn">FIND MUSIC</button></li>
									<li class="central-menu"><button type="button" class="btn btn-link btn-lg"
												id="followbtn">FOLLOWING</button></li>
									<li class="central-menu"><button type="button" class="btn btn-link btn-lg" id="recbtn">RECOMMENDED</button></li>
									<li class="central-menu"><button type="button" class="btn btn-link btn-lg"
												id="mymusicbtn">MY MUSIC</button></li>
								</ul>
								<ul class="nav navbar-nav navbar-right">
									<li><a href="/SoundConnect/logout" id="logout"> <span
											class="glyphicon glyphicon-log-out"></span> Log out
									</a></li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
			</div>
			<div class="col-sm-3 sidenav" id="left-panel">
				<div class="panel panel-info" id="party-panel">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a id="collapse-conf" data-toggle="collapse" href="#collapse1">
								<span class="text-warning">Conference</span>
								<span class="glyphicon glyphicon-eye-open btn btn-link" id="conf-glyph"></span>
							</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">
							<div class="btn-group" id="main-player-btn-group">
								<button type="button" class="mute button btn-link" id="mute">
									<span class="glyphicon glyphicon-volume-off btn btn-link" id="mute-glyph"></span>
								</button>
							</div>
							<audio id="main-player" src="" currentTime="" autoplay></audio>
							<div id="progressOut">
								<div id="progressIn">
									<div id="time">00:00</div>
								</div>
							</div>
						</div>
						<div class="panel-footer scrollfixed" id="music-div">
							Insert track names here.
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 text-left" id="centre-panel">
				<div id="homediv" class="visiblediv scrollfixed">
					<h2 class="text-primary txt">Welcome to Sound Connect!</h2>
					<p class="text-primary txt txt-small">A special service that allows you to communicate with your friends using the only one language - MUSIC!</p>
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
					<div id="my-recommended-list"></div>
				</div>
				<div id="profilediv" class="hiddendiv scrollfixed">
					<jsp:include page="includes/profile.jsp" />
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