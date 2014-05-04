function DashboardController($scope, $rootScope, $http, $routeParams, I18nFactory) {
	$rootScope.module = "dashboard";
   	$rootScope.i18n = I18nFactory.getLanguage({ lang: "es", module: "dashboard" });	

	
   	$('.dropdown-menu li').each(function(){
					var $this = $(this);
					if($this.children('ul').length) {
						$this.addClass('sub-dropdown');
						$this.children('ul').addClass('sub-menu');
					}
				});
				
				$('.sub-dropdown').on('mouseenter',function(){
					$(this).addClass('active').children('ul').addClass('sub-open');
				}).on('mouseleave', function() {
					$(this).removeClass('active').children('ul').removeClass('sub-open');
				});	 
				
				$scope.buscar = function () {
					alert($rootScope.user.token);
					var token = $rootScope.user.token;
					$http({
				        method: 'GET',
				        //headers: {"Auth-Token": token},
				        url: 'http://www.transfurlong.com.ar/webplatform/rest/viaje_equipo/' + $("#idNroEquipo").val()      
				    }).success(function (result) {
				        $scope.viajes = result;
				        console.log($scope.viajes);
				    }).error(function (result, status) {
				    	alert(status);
				    	$scope.viajes = null;
				    });
					
				};
				$scope.aceptarViaje = function () {
					$http({
				        method: 'GET',
				        url: $rootScope.baseUrl + '/rest/aceptar_viaje/' + $scope.itemSelected      
				    }).success(function (result) {
				       
				    }).error(function (result, status) {
				    	$scope.showError('Error desconocido al aceptar el viaje');
				    	
				    });
					
				};
				
				
				
}


										