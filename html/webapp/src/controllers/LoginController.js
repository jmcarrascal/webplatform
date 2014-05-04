function LoginController($scope, $rootScope, $location, $http, LoginService, I18nFactory){
	
	$rootScope.module = "login";
	
	
	if ($rootScope.defaultLanguage.abrev != null && $rootScope.defaultLanguage.abrev !== undefined){
		$rootScope.i18n = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });
	} else {
		I18nFactory.getDefaultLanguage(function (data){
    	    $rootScope.defaultLanguage = data;
    	    $rootScope.i18n = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });
        });
	}
			
			$scope.login = function() {
				$('.alert-danger').hide();
				LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function(user) {
					$rootScope.user = user;
					$http.defaults.headers.common['Auth-Token'] = user.token;
					//$http.defaults.headers.common['charset']='UTF-8';
					//$http.defaults.cache = false;
					//alert(user.token);
					$location.path("/dashboard");			
				});
			};	
			
			
		 $scope.header = 'views/menu.html';	
			
			
	
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

	$rootScope.isUserInRole = function (role) {
		return $rootScope.user
			&& $rootScope.user.roles[role];
	};
}

function Ctrl($scope) {
 
  $scope.footer = 'inc/footer.htm';
}