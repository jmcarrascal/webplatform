function AltaController($resource, $rootScope, $scope,  $http, $routeParams, $location, I18nFactory) {
	$rootScope.module = "registration-new";
	$rootScope.i18n = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });
	
	// TODO refactor para hacer accesible a todos los controllers y a Angular
	$scope.formatDateToDisplayFormat = function(dateString) {
		if (dateString && dateString != '') {
			return Date.parseExact(dateString, 'yyyy-MM-dd').toString('dd/MM/yyyy');
		} else {
			return null;
		}
	};
	
	$scope.formatDateToServerFormat = function(dateString) {
		if (dateString && dateString != '') {
			return Date.parseExact(dateString, 'dd/MM/yyyy').toString('yyyy-MM-dd');
		} else {
			return null;
		}
	};
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/config/pais'
    }).success(function (result) {
            $scope.paisGestor = result;
        	$http({
                method: 'GET',
                url: $rootScope.baseUrl + '/rest/tipodocumento/pais/' + result.id
            }).success(function (result2) {
                $scope.tipoDocumentoResidenciaList = result2;
            });
    });
	
	$http({
            method: 'GET',
            url: $rootScope.baseUrl + '/rest/estadocivil/getAll'            
    }).success(function (result) {
            $scope.estadoCivilList = result;
    });
		
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/nacionalidad/getAll'            
	}).success(function (result) {
	        $scope.nacionalidadList = result;
	});

	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/pais/other'            
    }).success(function (result) {
            $scope.paisPagadorList = result;
    });
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/sexo/getAll'            
	}).success(function (result) {
	        $scope.sexoList = result;
	});
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/prestacion/getAll'            
    }).success(function (result) {
            $scope.prestacionList = result;
    });
	
	$scope.documentosPagadorList = new Array();
	$scope.documentosResidenciaList = new Array();
	$scope.beneficioList = new Array();
	$scope.altaCompletada = false;
	
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
	
	$scope.onPaisPagadorChange = function () {
		var value = $('#selectPaisPagador option:selected').val();
		if (value != null && value != '') {
			$http({
		        method: 'GET',
		        url: $rootScope.baseUrl + '/rest/tipodocumento/pais/'+ value        
	        }).success(function (result) {
	            $scope.tipoDocumentoPagadorList = result;
	        }).error(function () {
	        	$scope.tipoDocumentoPagadorList = null;
	        });
		} else {
			$scope.tipoDocumentoPagadorList = null;
		}
	};
	
	$scope.agregarDocumentoResidencia = function() {
		
		$scope.hideAlerts();
		
		// FIXME evitar ingreso de duplicados
		
		var identificacion = {
			tipoDocumento: {
				pais: { id: $scope.paisGestor.id, descripcion: $scope.paisGestor.descripcion },
				descripcion: $('#selectTipoDocumentoPaisResidencia option:selected').text(),
				id: $scope.tipoDocumentoResidencia
			},
			numeroDocumento: $scope.numeroDocumentoResidencia,
			numeroInscripcion: $scope.numeroInscripcionDocumentoResidencia,
			organismoInscripcion: $scope.organismoInscripcionDocumentoResidencia
		};
		
		var fechaEmision = $('#inputFechaEmisionResidencia').val();
		if (fechaEmision && fechaEmision != '') {
			identificacion.fechaEmisionDocumento = $scope.formatDateToServerFormat(fechaEmision);
		}
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/identificacion/validate',
            data: $.param({json: JSON.stringify(identificacion)}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.documentosResidenciaList.push(identificacion);
        		$scope.limpiarCamposDocumentoResidencia();
        	} else {
        		$scope.showError(result.result);
        	}
        }).error(function (result, status) {
        	$scope.showError(result.result);
        });
	};
	
	$scope.borrarDocumentoResidencia = function(index) {
		$scope.hideAlerts();
		$scope.documentosResidenciaList.splice(index, 1);
	};
	
	$scope.mostrarListaDocumentosResidencia = function() {
		return $scope.documentosResidenciaList && $scope.documentosResidenciaList.length > 0;
	};
	
	$scope.agregarDocumentoPagador = function() {
		
		$scope.hideAlerts();
		
		// FIXME evitar ingreso de duplicados
		
		var identificacion = {
				tipoDocumento: {
					pais: { id: $scope.paisPagador, descripcion: $('#selectPaisPagador option:selected').text() },
					descripcion: $('#selectTipoDocumentoPagador option:selected').text(),
					id: $scope.tipoDocumentoPagador
				},
				numeroDocumento: $scope.numeroDocumentoPagador,
				numeroInscripcion: $scope.numeroInscripcionDocumentoPagador,
				organismoInscripcion: $scope.organismoInscripcionDocumentoPagador
			};
		
		var fechaEmision = $('#inputFechaEmisionPagador').val();
		if (fechaEmision && fechaEmision != '') {
			identificacion.fechaEmisionDocumento = $scope.formatDateToServerFormat(fechaEmision);
		}
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/identificacion/validate',
            data: $.param({json: JSON.stringify(identificacion)}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.documentosPagadorList.push(identificacion);
        		$scope.limpiarCamposDocumentoPagador();
        	} else {
        		$scope.showError(result.result);
        	}
        }).error(function (result, status) {
        	$scope.showError(result.result);
        });
	};
	
	$scope.borrarDocumentoPagador = function(index) {
		
		$scope.hideAlerts();
		
		var documento = $scope.documentosPagadorList[index];
		if (documento) {
			var idPaisPagador = documento.tipoDocumento.pais.id;
			var paisPagador = $.grep($scope.paisPagadorList, function (item) {
				return item.id == idPaisPagador;
			})[0];
			if (paisPagador && paisPagador.datosPais.beneficioPagador && $scope.beneficioList.length > 0) {
				// FIXME internacionalizar
				$scope.showError('No se puede eliminar el documento mientras existan beneficios asociados');
			} else {
				$scope.documentosPagadorList.splice(index, 1);
			}
			
		}
	};
	
	$scope.mostrarListaDocumentosPagador = function() {
		return $scope.documentosPagadorList && $scope.documentosPagadorList.length > 0;
	};
	
	$scope.mostrarTabBeneficio = function() {
		var documentosPagador = $scope.documentosPagadorList;
		var paisesPagadores = $scope.paisPagadorList;
		
		if (documentosPagador && paisesPagadores) {
			var idPaisDocumento;
			for (var i = 0; i < documentosPagador.length; i++) {
				idPaisDocumento = documentosPagador[i].tipoDocumento.pais.id;
				for (var j = 0; j < paisesPagadores.length; j++) {
					if (paisesPagadores[j] != undefined && paisesPagadores[j].id == idPaisDocumento && paisesPagadores[j].datosPais.beneficioPagador) {
						$scope.paisBeneficio = paisesPagadores[j];
						return true;
					}
				}
			}
		}
		
		$scope.paisBeneficio = null;	
		return false;
	};
	
	$scope.agregarBeneficio = function() {
		
		$scope.hideAlerts();
		
		// FIXME evitar ingreso de duplicados
		
		var beneficio = {
			paisPagador: { id: $scope.paisBeneficio.id, descripcion: $scope.paisBeneficio.descripcion },
			prestacion: { id: $scope.prestacion, descripcion: $('#selectPrestacion option:selected').text() },
			numeroBeneficio: $scope.numeroBeneficio,
		};
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/beneficio/validate',
            data: $.param({json: JSON.stringify(beneficio)}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.beneficioList.push(beneficio);
        		$scope.limpiarCamposBeneficio();
        	} else {
        		$scope.showError(result.result);
        	}
        }).error(function (result, status) {
        	$scope.showError(result.result);
        });
	};
	
	$scope.borrarBeneficio = function(index) {
		$scope.beneficioList.splice(index, 1);
	};
	
	$scope.mostrarListaBeneficios = function() {
		return $scope.beneficioList && $scope.beneficioList.length > 0;
	};
	
	$scope.existIdentificacionesPagador = function() {
		return $scope.documentosPagadorList && $scope.documentosPagadorList.length > 0;
	};
	
	$scope.guardar = function () {
		
		$scope.hideAlerts();
		
		// Armar objeto beneficiario
		var beneficiario = $.extend(true, {}, $scope.beneficiario);
		beneficiario.domicilio = $.extend(true, {}, $scope.domicilio);
		beneficiario.sexo = { codigo: $scope.sexo };
		beneficiario.nacionalidad = { id: $scope.nacionalidad };
		beneficiario.estadoCivil = { id: $scope.estadoCivil };
		var fechaNacimiento = $('#inputFechaNacimiento').val();
		if (fechaNacimiento && fechaNacimiento != '') {
			beneficiario.fechaNacimiento = $scope.formatDateToServerFormat(fechaNacimiento);
		}
		
		var identificaciones = new Array().concat($scope.documentosResidenciaList);
		identificaciones = identificaciones.concat($scope.documentosPagadorList);
		beneficiario.identificaciones = identificaciones;
		
		beneficiario.beneficios = $scope.beneficioList;
		
		var data = { json: JSON.stringify(beneficiario) };
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/beneficiario/new',
            data: $.param(data),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.completed) {
        		$scope.altaCompletada = true;
        		$('.form-control').attr('readonly', 'readonly');
        		$('.delete-from-list').hide();
        	}
        	if (result.success) {
        		$scope.showSuccess(result.result);
        	} else {
        		$scope.showError(result.result);
        	}
        }).error(function (result, status) {
        	$scope.showError(result.result);
        });
	};
	
	$scope.limpiarCamposBeneficiario = function () {
		$scope.beneficiario = null;
		$scope.domicilio = null;
		$scope.sexo = null;
		$scope.nacionalidad = null;
		$scope.estadoCivil = null;
		$('#inputFechaNacimiento').val('');
		$scope.documentosResidenciaList = new Array();
		$scope.documentosPagadorList = new Array();
		$scope.beneficioList = new Array();
	};
	
	$scope.limpiarCamposDocumentoResidencia = function () {
		$scope.tipoDocumentoResidencia = null;
		$scope.numeroDocumentoResidencia = null;
		$scope.numeroInscripcionDocumentoResidencia = null;
		$scope.organismoInscripcionDocumentoResidencia = null;
		$('#inputFechaEmisionResidencia').val('');
	};
	
	$scope.limpiarCamposDocumentoPagador = function () {
		$scope.paisPagador = null;
		$scope.tipoDocumentoPagador = null;
		$scope.numeroDocumentoPagador = null;
		$scope.numeroInscripcionDocumentoPagador = null;
		$scope.organismoInscripcionDocumentoPagador = null;
		$('#inputFechaEmisionPagador').val('');
	};
	
	$scope.limpiarCamposBeneficio = function () {
		$scope.prestacion = null;
		$scope.numeroBeneficio = null;
	};
	
	$scope.volver = function() {
		$location.path('/dashboard');
	};
	
	$scope.nuevaAlta = function() {
		$scope.hideAlerts();
		$scope.limpiarCamposBeneficiario();
		$scope.limpiarCamposDocumentoResidencia();
		$scope.limpiarCamposDocumentoPagador();
		$scope.limpiarCamposBeneficio();
		$('#tabBeneficiario').click();
		$('#inputNombre').focus();
		$scope.altaCompletada = false;
		$('.form-control').attr('readonly', 'readonly');
	};
	
	// FIXME aplicar i18n a los datepickers
	$('#fechaNac').datepicker({
		language: 'es',
		format: 'dd/mm/yyyy',
		startDate: '-100y',
		endDate: '+0d'
	});
	
	$('#fechaEmisionResidencia').datepicker({
		language: 'es',
		format: 'dd/mm/yyyy',
		startDate: '-100y',
		endDate: '+0d'
	});
	
	$('#fechaEmisionPagador').datepicker({
		language: 'es',
		format: 'dd/mm/yyyy',
		startDate: '-100y',
		endDate: '+0d'
	});
}
