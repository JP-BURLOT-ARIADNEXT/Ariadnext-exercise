
	    $.get("/user", function(data) {
	    	if(data){
	    		console.log(data);
		        $("#user_name").html(data.name);
		        $("#user_pic").attr('src', data.imgUrl);
		        $(".unauthenticated").hide();
		        $(".authenticated").show();   		
		        $(".idCheck").removeClass('disabled');   		
	    	}
	    });
	    var logout = function() {
		    $.post("/logout", function() {
		        $("#user_name").html('');
		        $("#user_pic").attr('src', '');
		        $(".unauthenticated").show();
		        $(".authenticated").hide();
		    });
		    return true;
		};
		$(document).ready(function(){
		    $.ajaxSetup({
				  beforeSend : function(xhr, settings) {
				    if (settings.type == 'POST' || settings.type == 'PUT'
				        || settings.type == 'DELETE' || settings.type == 'GET') {
				      if (!(/^http:.*/.test(settings.url) || /^https:.*/
				        .test(settings.url))) {
				        // Only send the token to relative URLs i.e. locally.
				        xhr.setRequestHeader("X-XSRF-TOKEN",
				          Cookies.get('XSRF-TOKEN'));
				      }
					}
				}
			});
		    
		    // Soummision des images pour vérification par l'API Ariadnext
		    $('#sendImages').click(function(){

		        var form = $('#imgAnalysisForm');
		        var f = new FormData(form[0]);
		        var url = form.attr('action');
		        setLoading(true);		        

		        $.ajax({
		        	type: "POST",
		            url: url,
		            data: f, 
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            success: function(data) {
		            	setLoading(false);		        
		            	console.log(data); 
		            	$('#msgContainer').show();
		            	$('#errorContainer').hide();
		            	$('#msgContainer').html(data.message);
		            },error: function(xhr, textStatus, errorThrown){
		            	setLoading(false);		  
		            	console.log("error",xhr, textStatus, errorThrown);
		            	$('#msgContainer').hide();
		            	$('#errorContainer').show();
		            	$('#errorContainer').html(xhr.responseJSON.message);
		            }
				});
		    });
		    
		    // Gestion du nom de fichier
		    $('input[type=file]').on('change',function(){
                //get the file name
                var fileName = $(this).val().replace("C:\\fakepath\\", "");
                //replace the "Choose a file" label
                $(this).next('.custom-file-label').html(fileName);
            })
		});
	    function setLoading(isLoading){
	        var btn = $('#sendImages');
	        btn.attr('disabled', isLoading);
	        if(isLoading){
		        btn.find('.loading').show();
		        btn.find('.not-loading').hide();
	        } else {
		        btn.find('.loading').hide();
		        btn.find('.not-loading').show();		        	
	        }
	    }