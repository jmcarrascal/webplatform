function PendienteEnviosController($rootScope, $scope,  $http, $routeParams, $location, I18nFactory) {
	$rootScope.module = "shipping-pending";
	$rootScope.i18n = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });
	
	$('#fechaAltaD').datepicker();
	$('#fechaAltaH').datepicker();
	$('#fechaLiqD').datepicker();
	$('#fechaLiqH').datepicker();
	
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
	
	$scope.showError = function (errorMessage) {
		$scope.error = { success: false, result: errorMessage };
		$('.alert-danger').show();
	};
	
	$scope.showSuccess = function (successMessage) {
		$scope.success = { success: true, result: successMessage };
		$('.alert-success').show();
	};
	
	$scope.hideAlerts = function () {
		$scope.error = null;
		$scope.success = null;
		$('.alert').hide();
	};
	
	$scope.crearTabla = function() {
		return $('#dt_a').dataTable({
			"sDom": "<'row'<'col-sm-6'<'dt_actions'>l><'col-sm-6'f>r>t<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aoColumns": [
			    {"sTitle": "Padrón", "mData": "padron"},
			    {"sTitle": "Nombre", "mData": "nombre"},
			    {"sTitle": "Apellido Materno", "mData": "apellidoMaterno"},
			    {"sTitle": "Apellido Paterno", "mData": "apellidoPaterno"},
			    {"sTitle": "Estado", "mData": "estadoBeneficiario.descripcion"}
			],
			// FIXME internacionalizar!
			"oLanguage": {
			    "sProcessing":     "Procesando...",
			    "sLengthMenu":     "Mostrar _MENU_ registros",
			    "sZeroRecords":    "No se encontraron resultados",
			    "sEmptyTable":     "Ningún dato disponible en esta tabla",
			    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
			    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
			    "sInfoPostFix":    "",
			    "sSearch":         "Buscar:",
			    "sInfoThousands":  ",",
			    "sLoadingRecords": "Cargando...",
			    "oPaginate": {
			        "sFirst":    "Primero",
			        "sLast":     "Último",
			        "sNext":     "Siguiente",
			        "sPrevious": "Anterior"
				},
			    "oAria": {
			        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
			        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
				}
			}
		});
	};
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/pais/other'        
    }).success(function (result) {
        $scope.paisPagadorList = result;
    }).error(function (result, status) {
    	$scope.showError('Error desconocido al obtener paises');
    	$scope.paisPagadorList = null;
    });
	
	$scope.buscar = function () {
		$scope.hideAlerts();
		
		if (!$scope.tabla) {
			$scope.tabla = $scope.crearTabla();
		}
		
		var url = $rootScope.baseUrl + '/rest/beneficiario/pendientes/' + $scope.tipoBusqueda + '/' + $scope.tipoOperacion;
		if ($scope.tipoBusqueda == 'pais') {
			url += '/' + $scope.pais;
		}
		
		$http({
	        method: 'GET',
	        url: url        
	    }).success(function (result) {
	    	$scope.tabla.fnClearTable();
	        if (result == null || result.length == 0) {
	        	$scope.showError('No se encontraron resultados');
	        	$('#divResultados').hide();
	        } else {
	        	$scope.tabla.fnAddData(result);
	        	$('#divResultados').show();
	        }
	    }).error(function (result, status) {
	    	$scope.showError('Error desconocido al realizar busqueda');
	    	$('#divResultados').hide();
	    });
		
	};

	$scope.cancelar = function () {
		$location.path('/dashboard');
	};
	
	$scope.enviar = function () {
		$scope.hideAlerts();
		
		var url = $rootScope.baseUrl + '/rest/beneficiario/pendientes/' + $scope.tipoBusqueda + '/' + $scope.tipoOperacion;
		if ($scope.tipoBusqueda == 'pais') {
			url += '/' + $scope.pais;
		}
		url += '/enviar';
		
		$http({
	        method: 'GET',
	        url: url        
	    }).success(function (result) {
	    	if (result.success) {
	    		$scope.showSuccess(result.result);
	    		$('#divResultados').hide();
	    	} else {
	    		$scope.showError(result.result);
	    	}
	    }).error(function (result, status) {
	    	$scope.showError('Error desconocido al enviar');
	    });
	};
}
