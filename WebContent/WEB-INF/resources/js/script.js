/**
 * Created by Zeddie on 4/2/2016.
 */
var myAudio;
var onAudioEnded;
var track;
var mainPlayer;

function formatTime(seconds) {
	var minutes = Math.floor(seconds / 60);
	minutes = (minutes >= 10) ? minutes : "" + minutes;
	var seconds = Math.floor(seconds % 60);
	seconds = (seconds >= 10) ? seconds : "0" + seconds;
	return minutes + ":" + seconds;
}

var getAudio = function (object_id, owner, id){
	  var req = owner+'_'+id;
	  console.log('before api');
	 var a = VK.api('audio.getById', {audios: req}, function(data){		  
			  console.log('OK');
			  var sound = data.response[0];
		 	 	var a = document.getElementById(object_id);
		 		a.setAttribute("src", sound.url);
	 		console.log('OK');
		  if(data.error)
			  console.log(data.error);
	  });
	  console.log('after api');
	  console.log(a);
};

var vkLogin = function(username){
	$('#node').show();
	console.log("vkLogin js");
	var address = 'vklogin';
	var json = username;
	$.ajax({
		type: "POST",
		url: address,
		data: json,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: function(data){		
			$('#node').hide();
			$('#welcome').show();
			console.log('OKAY!');
		},
		error: function(xhr, status, error){
			console.log('Something went wrong... Failed to add ');
		}
	});
};

var audioAddToUser = function(au){
	console.log(au);
	var address = 'add-to-user';
	var success = false;
	$.ajax({
		type: "POST",
		url: address,
		data: au,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: function(data){
			// TODO remove this alert after testing, the glyphicon change is enough to notify the user of success
			if(!data)
				$.notify('Something went wrong... Failed to add audio', 'error');
			else
				$.notify('Audio added to your playlist', 'success');
			success = true;
		},
		error: function(xhr, status, error){
			$.notify('Something went wrong. Please try again', 'error');
		}
	});

	if(success && $('#add-to-user-glyph').hasClass('glyphicon-plus'))
	{
		$('#add-to-user-glyph').removeClass('glyphicon-plus');
		$('#add-to-user-glyph').addClass('glyphicon-minus');
	};
};

var audioRemoveFromUser = function(au){
	var address = 'remove-from-user';
	var success = false;
	$.ajax({
		type: "POST",
		url: address,
		data: au,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: function(data){
			// TODO remove this alert after testing, the glyphicon change is enough to notify the user of success
			if(!data)
				$.notify('Something went wrong... Failed to remove audio', 'error');
			else
				$.notify('Audio deleted from your playlist', 'success');
			success = true;
		},
		error: function(xhr, status, error){
			$.notify('Something went wrong... Failed to remove audio', 'error');
		}
	});

	if(success && $('#add-to-user-glyph').hasClass('glyphicon-minus'))
	{
		$('#add-to-user-glyph').removeClass('glyphicon-minus');
		$('#add-to-user-glyph').addClass('glyphicon-plus');
	}
};

var AudioAddToConference = function(au){
	var address = 'add-to-conference';
	$.ajax({
		type: "POST",
		url: address,
		data: au,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: function(data){
			//action like this	
			if(!data)
				$.notify('Something went wrong... Failed to add audio', 'error');
			else
				$.notify('Audio added to current conference', 'success');
			if($('#progressIn').css('width')=="0px") mainPlayer.onended();
		},
		error: function(xhr, status, error){
			$.notify('Something went wrong... Failed to add audio', 'error');
		}
	});
};

var connectToUsersConf = function(id){
	var address = 'fetchconnect';
	$.ajax({
		type: "POST",
		url: address,
		data: id,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		success: function(data){
			//action like this	
			if(!data)
				$.notify('Failed to connect =(', 'error');
			else
				$.notify('Connected', 'success');
		},
		error: function(xhr, status, error){
			$.notify('Failed to connect =(', 'error');
		}
	});
};

var unfollowUser = function(id){
	var address = 'unfollow';
		$.ajax({
			type: "POST",
			url: address,
			data: id,
			contentType: "application/json; charset=utf-8",
			dataType: 'json',
			success: function(data){
				//action like this	
				if(!data)
					$.notify('Failed in unfollowing =(', 'error');
				else
					$.notify('Unfollowed', 'success');
			},
			error: function(xhr, status, error){
				alert('Something went wrong... Failed to connect ');
			}
		});
};

var onSearchAudioPlayed = function(search_player) {
	console.log(search_player);
	if (!search_player.paused) {
		search_player.pause();
		mainPlayer.muted=false;
		$('#search-pause-glyph').removeClass('glyphicon-pause');
		$('#search-pause-glyph').addClass('glyphicon-play');
	} else {
		mainPlayer.muted=true;
		search_player.play();
		$('#search-pause-glyph').removeClass('glyphicon-play');
		$('#search-pause-glyph').addClass('glyphicon-pause');
	}
};
var onSearchAudioMutes = function(search_player) {
	console.log(search_player);
	if (!search_player.muted) {
		search_player.muted = true;
		$('#search-mute-glyph').removeClass('glyphicon-volume-off');
		$('#search-mute-glyph').addClass('glyphicon-volume-up');
	} else {
		search_player.muted = false;
		$('#search-mute-glyph').removeClass('glyphicon-volume-up');
		$('#search-mute-glyph').addClass('glyphicon-volume-off');
	}
};

function audioPreview (object, owner, id) {
	getAudio(object, owner, id);
	var myAudio = document.getElementById("player/search");
	if (myAudio.paused) {
		console.log('play!');
		myAudio.play();
	} else {
		console.log('pause!');
		myAudio.pause();
	}
};

function clickPreviewPlay(object, owner, id) {
	var search_player = document.getElementById(object);
	var updateProgressSearch;

	if($('#play-glyph\\/'+id).hasClass('glyphicon-play'))
	{
		$('.preview-trigger').addClass('glyphicon-play');
		$('.preview-trigger').removeClass('glyphicon-remove');
		$('#play-glyph\\/'+id).removeClass('glyphicon-play');
		$('#play-glyph\\/'+id).addClass('glyphicon-remove');
		
		getAudio(object, owner, id);

		$('#searchaudio').removeClass('hiddendiv');
	//	$('#searchaudio').addClass('visiblediv');

		$('#pause-search').click(function (e) {
			if (!search_player.paused) {
				search_player.pause();
				mainPlayer.muted = false;
				$('#search-pause-glyph').removeClass('glyphicon-pause');
				$('#search-pause-glyph').addClass('glyphicon-play');
			} else {
				mainPlayer.muted = true;
				search_player.play();
				$('#search-pause-glyph').removeClass('glyphicon-play');
				$('#search-pause-glyph').addClass('glyphicon-pause');
			}
		});
		$('#mute-search').click(function (e) {
			if (!search_player.muted) {
				search_player.muted = true;
				$('#search-mute-glyph').removeClass('glyphicon-volume-off');
				$('#search-mute-glyph').addClass('glyphicon-volume-up');
			} else {
				search_player.muted = false;
				$('#search-mute-glyph').removeClass('glyphicon-volume-up');
				$('#search-mute-glyph').addClass('glyphicon-volume-off');
			}
		});

		updateProgressSearch = function() {
			var progress = $("#progressIn-search");
			var value = 0;
			if(search_player.duration == 'Infinity')
				value = 100;
			else if (search_player.currentTime > 0) {
				value = Math.floor((100 / search_player.duration) * search_player.currentTime);
			}
			progress.stop().css({'width':value + '%'},500);
			$('#time-search').html(formatTime(search_player.currentTime))
		}
		search_player.addEventListener("timeupdate", updateProgressSearch, false);
	//	search_player.play();
	//	$('#pause-search').click();
	//	onSearchAudioPlayed(search_player);
	//	$('#progressOut-search').removeClass('hiddendiv');
	//	$('#progressOut-search').addClass('visiblediv');
	}
	else
	{
		//onSearchAudioMutes(search_player);
		$('.preview-trigger').addClass('glyphicon-play');
		$('.preview-trigger').removeClass('glyphicon-remove');

		search_player.src = "";
		search_player.currentTime = "";
		search_player.paused = true;
		/*search_player.removeEventListener("timeupdate", updateProgressSearch, false);*/

		//$('#progressOut-search').removeClass('visiblediv');
		//$('#progressOut-search').addClass('hiddendiv');
		$('#searchaudio').removeClass('visiblediv');
		$('#searchaudio').addClass('hiddendiv');
	};

};

$(document).ready(function () {

	mainPlayer = $('#main-player')[0];
	// scrollbar initialization
	$('#homediv').perfectScrollbar();
	$('#finddiv').perfectScrollbar();
	$('#mymusicdiv').perfectScrollbar();
	$('#followdiv').perfectScrollbar();
	$('#recdiv').perfectScrollbar();
//	$('#profilediv').perfectScrollbar();
	$('#mymusicdiv').perfectScrollbar();
	$('#member-div').perfectScrollbar();

//	$('#pause').click(function() {
//		if (!mainPlayer.paused) {
//			mainPlayer.pause();
//			$('#main-pause-glyph').removeClass('glyphicon-pause');
//			$('#main-pause-glyph').addClass('glyphicon-play');
//		} else {
//			mainPlayer.play();
//			$('#main-pause-glyph').removeClass('glyphicon-play');
//			$('#main-pause-glyph').addClass('glyphicon-pause');
//		}
//	});

	/*UPDATE PROGRESS BAR*/
	function updateProgress() {
		var progress = $("#progressIn");
		var value = 0;

		//If duration = infinity set value to 100

		if(mainPlayer.duration == 'Infinity')
			value = 100;
		//else if it is > 0 calculate percentage to highlight

		else if (mainPlayer.currentTime > 0) {
			value = Math.floor((100 / mainPlayer.duration) * mainPlayer.currentTime);
			if (value < 50) value += 10;
		}

		//set the width of the progress bar

		progress.stop().css({'width':value + '%'},500);

		//set the new timestamp
		$('#time').html(formatTime(mainPlayer.currentTime))
	}

// add event listener for audio time updates
	mainPlayer.addEventListener("timeupdate", updateProgress, false);


	$('#mute').click(function (e) {
		if (!mainPlayer.muted) {
			mainPlayer.muted = true;
			$('#mute-glyph').removeClass('glyphicon-volume-off');
			$('#mute-glyph').addClass('glyphicon-volume-up');
		} else {
			mainPlayer.muted = false;
			$('#mute-glyph').removeClass('glyphicon-volume-up');
			$('#mute-glyph').addClass('glyphicon-volume-off');
		}
	});

	$('.volume-bar').slider({
		range: "%",
		min: 0,
		value: 50,
		max: 100,
		slide: function(e) {
			console.log('slider working');
			var value = $('.volume-bar').slider('value');
			myAudio.volume = value / 100;
		}
	});

	$('#collapse-conf').click(function (e) {
		if ($('#conf-glyph').hasClass('glyphicon-eye-open')) {
			$('#conf-glyph').removeClass('glyphicon-eye-open');
			$('#conf-glyph').addClass('glyphicon-eye-close');
		}
		else if ($('#conf-glyph').hasClass('glyphicon-eye-close')) {
			$('#conf-glyph').removeClass('glyphicon-eye-close');
			$('#conf-glyph').addClass('glyphicon-eye-open');
		}
	});

	$('#collapse-members').click(function (e) {
		if ($('#members-glyph').hasClass('glyphicon-eye-open')) {
			$('#members-glyph').removeClass('glyphicon-eye-open');
			$('#members-glyph').addClass('glyphicon-eye-close');
		}
		else if ($('#members-glyph').hasClass('glyphicon-eye-close')) {
			$('#members-glyph').removeClass('glyphicon-eye-close');
			$('#members-glyph').addClass('glyphicon-eye-open');
		}
	});

	mainPlayer.onended = function() {
		mainPlayer.src = "";
		track = undefined;
		$('#progressIn').css({'width': 0+'%'},500);
		var address = 'player/content';
		$.ajax({
			type: "GET",
			url: address,
			success: function (list) {
				$('#music-div').html(list);
				$('#member-div').html($('#music-div').find('#member-content').html());
				$('#member-content').hide();
				if(track!==undefined){
					getAudio("main-player", track.ownerId, track.id);
					mainPlayer.currentTime = track.startTime;
					mainPlayer.load();
					var sp =  document.getElementById('player/search');
					if(sp!=undefined)sp.pause();
					mainPlayer.play();
				}
			},
			error: function (xhr, status, error) {
				$.notify('Something went wrong... Failed to retrieve audio', 'error');
			}
		});
	};


	// call to the function when the document is first loaded, may need to be replaced with window.loaded
	//if (!firstAudioReq)
	//{
	//}

//	$('.snd').snd('/resources/sound/1.mp3', { autoplay: true });

    /* Shows the home div */
    $('#homebtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('hiddendiv').addClass('visiblediv');
    });

    /* Shows the search-for-music div */
    $('#findbtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#profilediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('hiddendiv').addClass('visiblediv');
    });

    /* Shows the following div */
    $('#followbtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#profilediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('hiddendiv').addClass('visiblediv');
        
        var address = 'followings';
        var id =0;
    	$.ajax({
    		type: "POST",
    		url: address,
    		data: id,
    		contentType: "application/json; charset=utf-8",
    		success: function(data){
    			$('#my-followings-list').html(data);
    		},
    		error: function(xhr, status, error){
    			$.notify('Something went wrong... Failed to get data', 'error');
    			console.log(xhr);
    		}
    	});
    });

    /* Shows the recommended users div */
    $('#recbtn').click(function (e) {
        e.preventDefault();
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#profilediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#recdiv').removeClass('hiddendiv').addClass('visiblediv');
        
        var address = 'recommended';
        var id =0;
    	$.ajax({
    		type: "POST",
    		url: address,
    		data: id,
    		contentType: "application/json; charset=utf-8",
    		success: function(data){
    			$('#my-recommended-list').html(data);
    		},
    		error: function(xhr, status, error){
    			alert('Something went wrong... Failed to get data'+error+status+xhr);
    			console.log(xhr);
    		}
    	});
    });

	/* Shows the user's profile */
	$('#profilebtn').click(function (e) {
		e.preventDefault();
		$('#homediv').removeClass('visiblediv').addClass('hiddendiv');
		$('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#profilediv').removeClass('hiddendiv').addClass('visiblediv');
	});


	/* Shows the my music div */
	$('#mymusicbtn').click(function (e) {
		e.preventDefault();
		$('#homediv').removeClass('visiblediv').addClass('hiddendiv');
		$('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
		$('#profilediv').removeClass('visiblediv').addClass('hiddendiv');
		$('#mymusicdiv').removeClass('hiddendiv').addClass('visiblediv');

		var address = 'list-music'; // where to post
		var userid = 0; //STUB
		$.ajax({
			type: "POST",
			url: address,
			data: userid,
			contentType: "application/json; charset=utf-8",
			success: function (data) {
				$('#my-music-list').html(data);
			},
			error: function(xhr, status, error) {
				$.notify('Something went wrong... Failed to get data, please try again', 'error');
			}
		});
	});
    
    $('#audio-search-form').on('submit',function(e){
    	e.preventDefault();
		var searchtext = $('#audio-search-form').find('input').val();  
		var address = 'search';
		$.ajax({
		  type: "POST",
		  url: address,
		  data: searchtext,
		  contentType: "application/json; charset=utf-8",
			success: function (data) {
				$('#audio-search-results').html(data);
          	},
			error: function(xhr, status, error) {
				$.notify('Something went wrong, please try again', 'error');
			}
		});
	});   
});

$(window).load(function() {
	mainPlayer.onended();
});
