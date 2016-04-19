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
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-3 sidenav" id="left-panel">
				<jsp:include page="includes/musicpanel.jsp" />
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
									<li><c:if
											test="${pageContext.request.userPrincipal.name != null}">
										
											Welcome : ${pageContext.request.userPrincipal.name} | <a
												href="javascript:formSubmit()"> <span
												class="glyphicon glyphicon-log-in"></span> Log out
											</a>
										</c:if></li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
				<div id="homediv" class="visiblediv scrollfixed">
					<h1>Welcome</h1>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Cras nibh lacus, aliquam et faucibus ac, vehicula sit amet odio.
						Nullam vestibulum ornare efficitur. Donec consectetur, sapien
						porta sagittis feugiat, mauris est tempor dolor, nec aliquet enim
						turpis nec augue. Pellentesque habitant morbi tristique senectus
						et netus et malesuada fames ac turpis egestas. Mauris vel lectus
						ut neque finibus blandit. Aliquam vel nunc consequat, tincidunt
						enim sed, consequat risus. Nullam auctor pellentesque turpis, et
						gravida lectus facilisis ut. Etiam sagittis leo quis magna
						convallis euismod. Vestibulum aliquet risus tortor, in tincidunt
						mauris faucibus at. Ut eget pulvinar justo, eget rutrum felis.
						Aenean dictum augue eget leo vestibulum scelerisque. Cras rhoncus
						dapibus orci ut imperdiet. Sed in nulla in lorem tincidunt auctor
						eget eget dui. Donec tempus neque eget leo ullamcorper, a viverra
						nunc volutpat. Sed cursus ex nec felis faucibus suscipit. Fusce
						mollis felis sem, ut rhoncus sem mollis ac. Nulla finibus feugiat
						justo, ut fermentum urna mattis posuere. Mauris dolor massa,
						ornare eu fringilla vel, laoreet quis nulla. Praesent vulputate
						sollicitudin turpis, sit amet sagittis diam scelerisque ac. Sed a
						blandit risus. Suspendisse potenti. Mauris a libero condimentum
						felis viverra vehicula. Curabitur sapien orci, volutpat ut neque
						a, fringilla luctus tortor. Nam feugiat mattis massa. Duis
						volutpat, leo sit amet bibendum aliquam, metus dui pellentesque
						quam, eget luctus erat ligula non mi. Mauris eu eros hendrerit,
						congue erat at, posuere urna. Aenean vestibulum, dui eget blandit
						ornare, ex lorem fermentum odio, ac convallis lectus sem nec
						mauris. Fusce ligula orci, aliquam vel porta sit amet, vehicula id
						arcu. Morbi facilisis vitae massa sed sagittis. Aenean tempor
						imperdiet nibh et lobortis. Cras quis dui gravida, lobortis dui a,
						feugiat sem. Donec consequat dui quam, a lacinia urna condimentum
						quis. Nunc scelerisque, massa sed eleifend egestas, purus justo
						mollis tellus, id interdum eros est tempus metus. Nam ac est in
						nisi mattis malesuada quis eget nunc. Morbi malesuada neque et leo
						dignissim blandit. Vivamus sem neque, pharetra eu metus a,
						elementum mattis felis. Lorem ipsum dolor sit amet, consectetur
						adipiscing elit. Pellentesque vulputate cursus luctus. Cras nunc
						nisl, vulputate quis vehicula quis, auctor eu justo. Cras
						consectetur vestibulum lectus, at commodo est egestas sed. Nullam
						rutrum non magna vitae convallis. Nulla facilisi. Suspendisse
						potenti. Aenean vehicula blandit enim, et eleifend eros tincidunt
						in. Morbi a massa sollicitudin, semper dui in, elementum velit.
						Donec lorem massa, ultricies vel eros sit amet, vulputate
						ullamcorper nulla. Integer tempus quis arcu ut tincidunt. Mauris
						sed velit a nulla commodo luctus. Nullam et elementum dolor.
						Aliquam nec urna iaculis nisi finibus hendrerit ut commodo metus.
						Cum sociis natoque penatibus et magnis dis parturient montes,
						nascetur ridiculus mus. Vivamus interdum posuere tortor, vitae
						ultricies enim convallis a. Pellentesque gravida fringilla mi.
						Cras porta justo mauris, ac congue massa lacinia sed. Interdum et
						malesuada fames ac ante ipsum primis in faucibus. Vivamus quam
						tellus, scelerisque ac dignissim eget, accumsan eget tellus.
						Aliquam in pellentesque diam. Class aptent taciti sociosqu ad
						litora torquent per conubia nostra, per inceptos himenaeos.
						Vivamus volutpat, libero vel luctus porta, massa ante mattis nisl,
						at vehicula magna orci a sem. Pellentesque nec lacus ligula. Ut a
						eros non ipsum lobortis condimentum. Quisque aliquam viverra ex
						vitae imperdiet. Curabitur scelerisque tincidunt justo, eu
						vulputate purus scelerisque a. Maecenas turpis est, congue tempus
						feugiat et, lobortis et ante. Aenean aliquam lorem at tortor
						malesuada tincidunt. Curabitur a orci sed augue porttitor
						convallis. Vivamus vitae ultrices ante, eu elementum enim. Proin
						aliquam aliquam ligula eu facilisis. Nunc vel ante lorem. Etiam
						semper nunc nec sapien luctus, ut fermentum libero convallis.
						Nulla facilisi. Maecenas volutpat vulputate egestas. Nulla tempus
						pretium finibus. Cras eleifend dignissim pulvinar. In pulvinar
						magna dui, nec sollicitudin erat tincidunt a. Nulla id varius
						felis. Ut porta erat nec quam varius dignissim. Maecenas non nulla
						tempor, laoreet urna cursus, molestie lorem. Maecenas ac
						vestibulum leo, eget vestibulum tortor. Suspendisse sed enim at
						dolor egestas auctor. Pellentesque et mauris massa. Maecenas
						lobortis leo ac malesuada suscipit. Sed in porttitor lectus, quis
						convallis nunc. Aliquam lacus lacus, malesuada vel lorem sit amet,
						congue ultrices est. Ut ante ex, tempor quis nunc sit amet, mattis
						auctor eros. Sed eu interdum nunc, non mollis enim. Phasellus
						rhoncus sapien id lorem ultricies, in pharetra ante bibendum.
						Morbi non erat luctus mauris pellentesque accumsan. Aliquam quis
						justo non nisl porta consectetur. Pellentesque eu scelerisque
						augue. Aenean dui lorem, imperdiet faucibus laoreet nec, ornare ut
						justo. Maecenas felis lorem, blandit eu quam vel, lacinia
						dignissim mi. Fusce quis molestie erat, nec imperdiet leo.
						Maecenas fermentum ante risus, sit amet luctus leo venenatis vel.
						Aliquam sed ipsum ac ex pellentesque condimentum at sit amet
						mauris. Duis pharetra massa pulvinar ornare auctor. Fusce
						scelerisque mauris ac nisi tincidunt rhoncus. Duis quis felis
						pharetra, consequat lacus nec, vestibulum leo. Proin eu tortor id
						nunc luctus iaculis. Vivamus lobortis sodales lorem sit amet
						suscipit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Vestibulum dictum in ante vel tincidunt. Duis fermentum
						scelerisque ligula at aliquet. Mauris aliquet sem quis elementum
						egestas. Donec efficitur sem non orci molestie mollis. Donec ac
						risus vel nisl finibus laoreet ac vitae magna. Duis id dictum
						felis. Sed ut dolor nunc. Nulla porta porta ante, id iaculis
						mauris congue eget. Vivamus malesuada posuere consectetur. Etiam
						tincidunt ultrices quam, sed congue sem dignissim quis. Phasellus
						lacinia felis enim, sit amet consequat nisi tristique vitae. Proin
						eu consectetur lectus. Aliquam mattis turpis vel elementum
						iaculis. Proin id nisl ac lacus eleifend varius in nec dolor.
						Aliquam erat volutpat. Nunc et leo eu sapien ultricies vulputate
						nec at nulla. Donec ornare tempor nibh nec ornare. Nullam varius
						varius nisl eu auctor. Proin eleifend neque sapien, sit amet
						pharetra eros varius vitae. Suspendisse egestas, risus eu posuere
						varius, ligula enim consectetur lacus, quis ultrices ex ligula
						vitae ex. Aliquam eu rhoncus lacus, sit amet sagittis felis.

						Maecenas in nunc enim. Phasellus scelerisque, nisl nec ornare
						venenatis, ipsum neque tempus odio, quis fermentum tortor odio ut
						nunc. Donec blandit convallis urna, nec blandit dolor sagittis et.
						Nam facilisis.</p>
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
					<jsp:include page="includes/following.jsp" />
				</div>
				<div id="recdiv" class="hiddendiv scrollfixed">
					<jsp:include page="includes/recommended.jsp" />
				</div>
				<div id="mymusicdiv" class="hiddendiv scrollfixed">
					<jsp:include page="includes/mymusic.jsp" />
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