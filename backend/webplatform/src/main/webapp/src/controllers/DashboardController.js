function DashboardController($scope, $rootScope, $http, $routeParams,
		I18nFactory, $templateCache) {
	$rootScope.module = "dashboard";
	$rootScope.i18n = I18nFactory.getLanguage({
		lang : $rootScope.defaultLanguage.abrev,
		module : $rootScope.module
	});

	$("#idViajes").hide();
	$("#idBtnAceptar").hide();

	$('.dropdown-menu li').each(function() {
		var $this = $(this);
		if ($this.children('ul').length) {
			$this.addClass('sub-dropdown');
			$this.children('ul').addClass('sub-menu');
		}
	});

	$('.sub-dropdown').on('mouseenter', function() {
		$(this).addClass('active').children('ul').addClass('sub-open');
	}).on('mouseleave', function() {
		$(this).removeClass('active').children('ul').removeClass('sub-open');
	});

	$scope.buscar = function() {
		var alea = Math.random() * 2
		$http(
				{
					method : 'GET',
					url : $rootScope.baseUrl + '/rest/viaje_equipo/'
							+ $("#idNroEquipo").val() + '/?aleatorio=' + alea,
					cache : false
				}).success(
				function(result) {

					$scope.viajes = result;
					$scope.itemSelected = $scope.viajes[0];
					if (result == '') {
						$templateCache.removeAll();
						$("#idMsgViajes").html(
								"No hay viajes registrados para este equipo: "
										+ $("#idNroEquipo").val());
						$("#idViajes").hide();
						$("#idBtnAceptar").hide();

					} else {
						$templateCache.removeAll();
						$("#idMsgViajes").html(
								"Viajes a recepcionar para el equipo: "
										+ $("#idNroEquipo").val());
						$("#idViajes").show();
						$("#idBtnAceptar").show();

					}
					console.log($scope.viajes);
				}).error(function(result, status) {
			$("#idMsgViajes").html('Error desconocido al obtener viajes');
			$scope.viajes = null;
		});

	};
	$scope.aceptarViaje = function() {
		var alea = Math.random() * 3
		$http(
				{
					method : 'GET',
					url : $rootScope.baseUrl + '/rest/aceptar_viaje/'
							+ $scope.itemSelected.nroViaje + '/' + $scope.itemSelected.localidad_id + '/?aleatorio='
							+ alea
				})
				.success(
						function(result) {
							alert("Se ha realizado el aviso de recepcion para el viaje: "
									+ $scope.itemSelected.nroViaje
									+ " del equipo: " + $("#idNroEquipo").val());
							$("#idViajes").hide();
							$("#idBtnAceptar").hide();
							$("#idMsgViajes").val("");
							$("#idNroEquipo").val("");
							$("#idMsgViajes").hide();
						}).error(function(result, status) {
					$scope.showError('Error desconocido al aceptar el viaje');

				});

	};

}
