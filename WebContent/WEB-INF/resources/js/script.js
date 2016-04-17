/**
 * Created by Zeddie on 4/2/2016.
 */
var myAudio;

function audioPreview (id) {
	myAudio = document.getElementById("player/"+id);
	console.log(myAudio.id);
	if (myAudio.paused) {
		myAudio.play();
	} else {
		myAudio.pause();
	}
}

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
}

$(document).ready(function () {

	$('.volume-bar').slider({
		range: "%",
		min: 0,
		value: 50,
		max: 100,
		slide: function(e) {
			console.log('slider working')
			var value = $('.volume-bar').slider('value');
			myAudio.volume = value / 100;
		}
	});

	$('.snd').snd('/resources/sound/1.mp3', { autoplay: true });

    /* Shows the home div */
    $('#homebtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('hiddendiv').addClass('visiblediv');
    });

    /* Shows the search-for-music div */
    $('#findbtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('hiddendiv').addClass('visiblediv');
    });

    /* Shows the following div */
    $('#followbtn').click(function (e) {
        e.preventDefault();
        $('#recdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('hiddendiv').addClass('visiblediv');
    });

    /* Shows the recommended users div */
    $('#recbtn').click(function (e) {
        e.preventDefault();
        $('#homediv').removeClass('visiblediv').addClass('hiddendiv');
        $('#finddiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#followdiv').removeClass('visiblediv').addClass('hiddendiv');
        $('#recdiv').removeClass('hiddendiv').addClass('visiblediv');
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
    
    $('.audio-add-to-user').click(function(e){
    	e.preventDefault();

    	var au = $(this).val();
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
		}


	});
    
    $('.audio-add-to-conference').click( function(e){
    	e.preventDefault();
    	var au = $(this).val();
    	var address = 'add-to-conference';
    	$.ajax({
    		type: "POST",
    		url: address,
    		data: au,
    		contentType: "application/json; charset=utf-8",
    		dataType: 'json',
    		success: function(data){
    			//action like this
    			alert('Audio added to current conference');
    		},
    		error: function(xhr, status, error){
    			alert('Something went wrong... Failed to add ');
    		}
    	});
    });    
});