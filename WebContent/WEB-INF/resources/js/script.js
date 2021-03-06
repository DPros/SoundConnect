/**
 * Created by Zeddie on 4/2/2016.
 */
var myAudio;
var onAudioEnded;

var audioAddToUser = function(au){
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
				alert('Something went wrong... Failed to add ');
			else
				alert('Audio added to your playlist');
			success = true;
		},
		error: function(xhr, status, error){
			alert('Something went wrong... Failed to add ');
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
				alert('Something went wrong... Failed to add ');
			else
				alert('Audio deleted from your playlist');
			success = true;
		},
		error: function(xhr, status, error){
			alert('Something went wrong... Failed to add ');
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
				alert('Something went wrong... Failed to add ');
			else
				alert('Audio added to current conference');
		},
		error: function(xhr, status, error){
			alert('Something went wrong... Failed to add ');
		}
	});
};

function audioPreview (id) {
	myAudio = document.getElementById("player/"+id);
	console.log(myAudio.id);
	if (myAudio.paused) {
		myAudio.play();
	} else {
		myAudio.pause();
	}
};

function clickPreviewPlay(id) {
	if($('#play-glyph\\/'+id).hasClass('glyphicon-play'))
	{
		$('#play-glyph\\/'+id).removeClass('glyphicon-play');
		$('#play-glyph\\/'+id).addClass('glyphicon-pause');
	}
	else
	{
		$('#play-glyph\\/'+id).addClass('glyphicon-play');
		$('#play-glyph\\/'+id).removeClass('glyphicon-pause');
	}
	if(!($('#search-results\\/'+id).hasClass('current-track')))
	{
		$('#search-results\\/'+id).addClass('current-track');
	}
	else $('#search-results\\/'+id).removeClass('current-track');
	audioPreview(id);
};

$(document).ready(function () {

	// scrollbar initialization
	$('#homediv').perfectScrollbar();
	$('#finddiv').perfectScrollbar();
	$('#mymusicdiv').perfectScrollbar();
	$('#followdiv').perfectScrollbar();
	$('#recdiv').perfectScrollbar();
//	$('#profilediv').perfectScrollbar();
	$('#mymusicdiv').perfectScrollbar();
	$('#member-div').perfectScrollbar();

	//Ps.initialize(document.getElementById('finddiv'));
	//Ps.initialize(document.getElementById('followdiv'));

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

	onAudioEnded = function () {
		var address = 'get-from-conference'; // TODO what's the actual address?
		var confAudio;
		// stubs for testing
		var data = {src: 'sound/2.mp3'};
		$('.snd').snd(data.src, onAudioEnded);
		/*$.ajax({
			type: "GET",
			url: address,
			data: confAudio, // TODO is this the variable where the result of the request is stored?
			contentType: "application/json; charset=utf-8",
			dataType: 'json', // TODO get next song json
			success: function (data) {
				if (!data)
					alert('Something went wrong... Failed to retrieve audio');
				else {
					alert('Audio track retrieved');
					$('.snd').snd(data.src, {autoplay: true}, onAudioEnded);
				}
			},
			error: function (xhr, status, error) {
				alert('Something went wrong... Failed to retrieve audio');
			}
		});*/
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
				alert("Failed to load data =( Please try again");
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
					alert("Please try again");
			}
		});
	});   
});

$(window).load(function() {
	onAudioEnded();
});
