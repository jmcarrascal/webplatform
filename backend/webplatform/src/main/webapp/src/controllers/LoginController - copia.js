function LoginController($scope,  $http, $routeParams) {
	
  $(document).ready(function(){
                
				//* boxes animation
				form_wrapper = $('.login_box');
				
				
				//* validation
				$('#login_form').validate({
					onkeyup: false,
					errorClass: 'error',
					validClass: 'valid',
					rules: {
						username: { required: true, minlength: 3 },
						password: { required: true, minlength: 3 }
					},
					highlight: function(element) {
						$(element).closest('div').addClass("f_error");
						setTimeout(function() {
							boxHeight()
						}, 200)
					},
					unhighlight: function(element) {
						$(element).closest('div').removeClass("f_error");
						setTimeout(function() {
							boxHeight()
						}, 200)
					},
					errorPlacement: function(error, element) {
						$(element).closest('div').append(error);
					}
				});
            });
			
			$('#validateUserForm').submit(function(e) {
	        $.post("http://localhost:8080/siaci/rest/usuario/validate", $(this).serialize(), function(response) {
	        	console.log(response);
	        	$.each(response, function(i, item) {
	        		console.log(i + ' ' + item);
	        		if (i == 'success') {
						
						if ( item == true) {
							
							window.location = "#/getManifiestoCAAll";
						}else{
							alert ("Error de Usuario")
						} 
					}
				});
	        	
	        });	        
			e.preventDefault(); // prevent actual form submit and page reload
			});

	 
}