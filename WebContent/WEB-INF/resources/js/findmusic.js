
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
		}


	});

	$('.audio-remove-from-user').click(function(e){
		e.preventDefault();
    	var au = $(this).val();
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
    			if(!data)
    				alert('Something went wrong... Failed to add ');
    			else
    				alert('Audio added to current conference');
    		},
    		error: function(xhr, status, error){
    			alert('Something went wrong... Failed to add ');
    		}
    	});
    });    

