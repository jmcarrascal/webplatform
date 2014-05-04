function BusquedaController($resource, $rootScope, $scope,  $http, $routeParams, $location, I18nFactory) {
	$rootScope.module = "registration-search";
	$rootScope.i18n   = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });

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
	
	// FIXME internacionalizar
	$('#divFechaAlta').datepicker({language: 'es'}).on('changeDate', function(event) { $('#hiddenFechaAlta').val(event.date.toString('MM/dd/yyyy')); });
	$('#divFechaNacimiento').datepicker({language: 'es'});
	$('#divFechaFallecimiento').datepicker({language: 'es'});
	$('#divFechaEmision').datepicker({language: 'es'});
	$('#divFechaEmision2').datepicker({language: 'es'});

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
	
	$scope.showError = function (errorMessage) {
		$scope.error = { success: false, result: errorMessage };
		$('#alertError').show();
	};
	
	$scope.showSuccess = function (title, successMessage) {
		$scope.success = { success: true, title: title, result: successMessage };
		$('#alertSuccess').show();
	};
	
	$scope.showErrorVer = function (errorMessage) {
		$scope.errorVer = { success: false, result: errorMessage };
		$('#alertErrorVer').show();
	};
	
	$scope.showErrorMod = function (errorMessage) {
		$scope.errorMod = { success: false, result: errorMessage };
		$('#alertErrorMod').show();
	};
	
	$scope.showErrorRechazar = function (errorMessage) {
		$scope.errorRechazar = { success: false, result: errorMessage };
		$('#alertErrorRechazar').show();
	};
	
	$scope.hideAlerts = function () {
		$scope.error = null;
		$scope.success = null;
		$('.alert').hide();
	};
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/config/pais'        
    }).success(function (result) {
        $scope.paisGestor = result;
    }).error(function (result, status) {
    	$scope.showError('Error desconocido al obtener país gestor');
    	$scope.paisGestor = null;
    });
	
	$http({
        method: 'GET',
        url: $rootScope.baseUrl + '/rest/pais/getAll'        
    }).success(function (result) {
        $scope.paises = result;
    }).error(function (result, status) {
    	$scope.showError('Error desconocido al obtener paises');
    	$scope.paises = null;
    });
	
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
			    {"sTitle": "Apellido Paterno", "mData": "apellidoPaterno"},
			    {"sTitle": "Apellido Materno", "mData": "apellidoMaterno"},
			    {"sTitle": "País Residencia", "mData": "paisResidencia.descripcion"},
			    {"sTitle": "Estado", "mData": "estadoBeneficiario.descripcion"},
			    {"sTitle": "Acciones", "mData": "id", mRender: renderAcciones, "bSortable": false}
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
	
	$scope.filter = new Object();
	$scope.combosModCargados = false;
	
	window.renderAcciones = function (id, type, beneficiario) {
		
		var html = '';
		
		// Agregar boton Ver
		html += '<a data-toggle="modal" href="#modal_form" class=" hint--top hint--info" data-hint="Ver"'
				+ ' onclick="ver(' + id + ')"><i class="icon-eye-open"></i></a>\n';
		
		var estadoBeneficiario = beneficiario.estadoBeneficiario.codigo;
		var estadoSeguimiento = beneficiario.seguimiento[beneficiario.seguimiento.length-1].estado.codigo;
		var paisResidencia = beneficiario.paisResidencia;
		
		// Agregar boton Modificar
		if ((estadoBeneficiario == 'P' || estadoBeneficiario == 'C')
				&& ((paisResidencia.id == $scope.paisGestor.id && estadoSeguimiento == 'E' && $rootScope.isUserInRole('USER_G'))
						|| estadoSeguimiento == 'RE'
						|| estadoSeguimiento == 'REM')) {
			html += '<a data-toggle="modal" href="#modal_Edit" class=" hint--top hint--info" data-hint="Modificar"';
			html += ' onclick="modificar(' + id + ')"><i class="icon-edit"></i></a>';
		}
		
		if (estadoSeguimiento == 'PA' && paisResidencia.id != $scope.paisGestor.id) {
			html += '<a data-toggle="modal" href="#modal_confirmar" class=" hint--top hint--info" data-hint="Confirmar"'
				+ ' onclick="confirmar(' + id +')"><i class="icon-ok"></i></a>\n';
			html += '<a data-toggle="modal" href="#modal_rechazar" class=" hint--top hint--info" data-hint="Rechazar"'
				+ ' onclick="rechazar(' + id + ')"><i class="icon-remove"></i></a>';
		}

		return html;
	};
	
	window.ver = function(id) {
		$scope.hideAlerts();		
		$('#tabDatosPersonalesVer').click();
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.beneficiarioVer = result;
	    }).error(function (result, status) {
	    	$scope.showErrorVer('Error desconocido al obtener beneficiario');
	    	$scope.beneficiarioVer = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/identificacion/residencia/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.identificacionesResidenciaVer = result;
	    }).error(function (result, status) {
	    	$scope.showErrorVer('Error desconocido al obtener identificaciones del pais de residencia');
	    	$scope.identificacionesResidenciaVer = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/identificacion/pagador/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.identificacionesPagadorVer = result;
	    }).error(function (result, status) {
	    	$scope.showErrorVer('Error desconocido al obtener identificaciones del pais de pagador');
	    	$scope.identificacionesPagadorVer = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/beneficio/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.beneficiosVer = result;
	    }).error(function (result, status) {
	    	$scope.showErrorVer('Error desconocido al obtener beneficios');
	    	$scope.beneficiosVer = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/vinculos/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.vinculosVer = result;
	    }).error(function (result, status) {
	    	$scope.showErrorVer('Error desconocido al obtener vinculos');
	    	$scope.vinculosVer = null;
	    });
	};
	
	window.modificar = function(id) {
		
		$scope.hideAlerts();		
		$scope.initModificar();
		$('#tabDatosPersonalesMod').click();
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.beneficiarioMod = result;
	        $scope.domicilioMod = result.domicilio;
	        $scope.nacionalidadMod = result.nacionalidad.id;
	        $scope.sexoMod = result.sexo.codigo;
	        $scope.estadoCivilMod = result.estadoCivil.id;
	        $('#inputFechaNacimientoMod').val($scope.formatDateToDisplayFormat(result.fechaNacimiento));
	        
			$http({
		        method: 'GET',
		        url: $rootScope.baseUrl + '/rest/beneficiario/permisos/' + id        
		    }).success(function (result) {
		        $scope.permisos = result;
		        if ($scope.permisos) {
		        	var urlPaisesPagadores = '';
		    		if ($scope.permisos.agregarIdentificacionesPaisesPagadoresNuevos && !$scope.permisos.agregarIdentificacionesPaisesPagadoresActuales) {
	    				urlPaisesPagadores = '/rest/pais/sinvinculos/beneficiario/' + $scope.beneficiarioMod.id;
	    			} else {
	    				urlPaisesPagadores = '/rest/pais/other';
	    			}
		    		$http({
		    			method: 'GET',
		    			url: $rootScope.baseUrl + urlPaisesPagadores            
		    		}).success(function (result) {
		    			$scope.paisPagadorList = result;
		    		});
		        }
		    }).error(function (result, status) {
		    	$scope.showErrorMod('Error desconocido al obtener permisos de modificacion del beneficiario');
		    	$scope.permisos = null;
		    });
	    }).error(function (result, status) {
	    	$scope.showErrorMod('Error desconocido al obtener beneficiario');
	    	$scope.beneficiarioMod = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/identificacion/residencia/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.identificacionesResidenciaMod = result;
	    }).error(function (result, status) {
	    	$scope.showErrorMod('Error desconocido al obtener identificaciones del pais de residencia');
	    	$scope.identificacionesResidenciaMod = null;
	    });

		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/identificacion/pagador/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.identificacionesPagadorMod = result;
	    }).error(function (result, status) {
	    	$scope.showErrorMod('Error desconocido al obtener identificaciones del pais de pagador');
	    	$scope.identificacionesPagadorMod = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/beneficio/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.beneficiosMod = result;
	    }).error(function (result, status) {
	    	$scope.showErrorMod('Error desconocido al obtener beneficios');
	    	$scope.beneficiosMod = null;
	    });
		
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/vinculos/beneficiario/' + id        
	    }).success(function (result) {
	        $scope.vinculosMod = result;
	        $scope.vinculosBaja = new Array();
	        for (var i = 0; i < $scope.vinculosMod.length; i++) {
	        	$scope.vinculosBaja[i] = {
	        		baja: false,
	        		motivoEstado: ''
	        	};
	        }
	    }).error(function (result, status) {
	    	$scope.showErrorMod('Error desconocido al obtener vinculos');
	    	$scope.vinculosVer = null;
	    });
	};
	
	window.confirmar = function (id) {
		
		if (!confirm('\u00BFConfirma que desea aprobar al beneficiario?')) {
			return;
		}
		
		$http({
	        method: 'POST',
	        url: $rootScope.baseUrl + '/rest/beneficiario/confirmar',
	        data: $.param({ idBeneficiario: id}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
	    }).success(function (result) {
	    	if (result.success) {
	    		$scope.showSuccess('Exito', result.result);
	    		$scope.ejecutarBusqueda();
	    	} else {
	    		$scope.showError(result.result);
	    	}
        }).error(function (result, status) {
        	$scope.showError('Error desconocido');
	    });
	};
	
	window.rechazar = function (id) {
		$scope.initRechazar();
		$scope.idBeneficiarioRechazar = id;
	};
	
	$scope.initModificar = function() {
		
		$scope.documentoResidenciaMod = null;
		$scope.documentoPagadorMod = null;
		
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
		
	};
	
	$scope.initRechazar = function () {
		$scope.idTipoMotivo = null;
		$scope.motivoEstado = null;
		$('#txtOtrosMotivos').attr('placeholder', '');
		$http({
	        method: 'GET',
	        url: $rootScope.baseUrl + '/rest/tipomotivo/getAll'            
	    }).success(function (result) {
	        $scope.tipoMotivoList = result;
	    }).error(function (result) {
	    	$scope.tipoMotivoList = null;
	    	$scope.showErrorRechazar('Error al obtener motivos de rechazo');
	    });
	};
	
	$scope.onTipoMotivoChange = function () {
		var placeholder = '';
		if ($scope.idTipoMotivo == 0) {
			placeholder = 'Introduzca aqu\u00ED el motivo de rechazo';
		}
		$('#txtOtrosMotivos').attr('placeholder', placeholder);
	};
	
	$scope.limpiarCamposMod = function () {
        $scope.beneficiarioMod = null;
        $scope.domicilioMod = null;
        $scope.nacionalidadMod = null;
        $scope.sexoMod = null;
        $scope.estadoCivilMod = null;
        $scope.documentoResidenciaMod = null;
        $scope.documentoPagadorMod = null;
        $scope.identificacionesResidenciaMod = null;
        $scope.identificacionesPagadorMod = null;
        $scope.vinculosMod = null;
        $scope.vinculosBaja = null;
        $('#inputFechaNacimientoMod').val('');
        $('#inputFechaFallecimientoMod').val('');
	};
	
	$scope.limpiarCamposVer = function () {
        $scope.beneficiarioVer = null;
        $scope.identificacionesResidenciaVer = null;
        $scope.identificacionesPagadorVer = null;
        $scope.beneficiosVer = null;
	};
	
	$scope.onPaisDocumentacionChange = function() {
		var value = $scope.filter.idPaisDocumento;
		if (value && value != '') {
			$http({
		        method: 'GET',
		        url: $rootScope.baseUrl + '/rest/tipodocumento/pais/'+ value
	        }).success(function (result) {
	            $scope.tiposDocumento = result;
	        }).error(function () {
	        	$scope.tiposDocumento = null;
	        });
		} else {
			$scope.tiposDocumento = null;
		}
	};
    
	$scope.buscar = function () {
		
		$scope.hideAlerts();
		
		if (!$scope.tabla) {
			$scope.tabla = $scope.crearTabla();
		}
		
		$scope.ejecutarBusqueda();
	};
	
	$scope.ejecutarBusqueda = function () {
		var filter = $scope.filter;
		var fechaAlta = $('#hiddenFechaAlta').val();
		if (fechaAlta && fechaAlta != '') {
			filter.fechaAlta = fechaAlta;
		}
		
		$http({
	        method: 'POST',
	        url: $rootScope.baseUrl + '/rest/beneficiario/find',
	        data: $.param(filter),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	$scope.tabla.fnClearTable();
        	if (result.length > 0) {
        		$scope.tabla.fnAddData(result);
        		$('#divResultados').show();
        	} else {
        		$scope.showError('No se encontraron resultados');
        		$('#divResultados').hide();
        	}
        }).error(function () {
       		$scope.tabla.fnClearTable();
    		$scope.showError('Error desconocido al buscar');
    		$('#divResultados').hide();
        });
	};
		
	$scope.agregarDocumentoResidencia = function() {
		
		// FIXME evitar ingreso de duplicados
		
		var identificacion = {
			tipoDocumento: {
				pais: { id: $scope.paisGestor.id, descripcion: $scope.paisGestor.descripcion },
				codigo: $('#selectTipoDocumentoPaisResidenciaMod option:selected').text(),
				id: $scope.documentoResidenciaMod.tipoDocumento.id
			},
			numeroDocumento: $scope.documentoResidenciaMod.numeroDocumento,
			numeroInscripcion: $scope.documentoResidenciaMod.numeroInscripcion,
			organismoInscripcion: $scope.documentoResidenciaMod.organismoInscripcion,
			beneficiario: { id: $scope.beneficiarioMod.id }
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
        		$scope.identificacionesResidenciaMod.push(identificacion);
        		$scope.documentoResidenciaMod = null;
        	} else {
        		$scope.showErrorMod('Error: ' + result.result);
        	}
        }).error(function (result, status) {
        	$scope.showErrorMod('Error desconocido');
        });
	};
	
	$scope.borrarDocumentoResidencia = function(index) {
		$scope.hideAlerts();
		$scope.identificacionesResidenciaMod.splice(index, 1);
	};
	
	$scope.borrarDocumentoPagador = function(index) {
		$scope.hideAlerts();
		var documento = $scope.identificacionesPagadorMod[index];
		if (documento) {
			var idPaisPagador = documento.tipoDocumento.pais.id;
			var paisPagador = $.grep($scope.paisPagadorList, function (item) {
				return item.id == idPaisPagador;
			})[0];
			if (paisPagador && paisPagador.datosPais.beneficioPagador && $scope.beneficiosMod.length > 0) {
				// FIXME internacionalizar
				$scope.showErrorMod('No se puede eliminar el documento mientras existan beneficios asociados');
			} else {
				$scope.identificacionesPagadorMod.splice(index, 1);
			}
		}
	};
	
	$scope.mostrarTabBeneficio = function() {
		var documentosPagador = $scope.identificacionesPagadorMod;
		var paises = $scope.paises;
		var paisResidencia = $scope.beneficiarioMod ? $scope.beneficiarioMod.paisResidencia : null;
		
		if (documentosPagador && paises) {
			var idPaisDocumento;
			for (var i = 0; i < documentosPagador.length; i++) {
				idPaisDocumento = documentosPagador[i].tipoDocumento.pais.id;
				for (var j = 0; j < paises.length; j++) {
					if (paisResidencia && paises[j].id != paisResidencia.id
							&& paises[j] != undefined && paises[j].id == idPaisDocumento && paises[j].datosPais.beneficioPagador) {
						$scope.paisBeneficio = paises[j];
						return true;
					}
				}
			}
		}
		
		$scope.paisBeneficio = null;	
		return false;
	};
	
	$scope.mostrarTabVinculos = function() {
		if (!$scope.beneficiarioMod) return false;
		var ultimoSeguimiento = $scope.beneficiarioMod.seguimiento[$scope.beneficiarioMod.seguimiento.length-1];
		return (ultimoSeguimiento.estado.codigo != 'E');		
	};
	
	$scope.puedeEliminarIdentificacionResidencia = function (identificacion) {
		return $scope.paisGestor.id == identificacion.tipoDocumento.pais.id
			&& $scope.permisos && ($scope.permisos.eliminarIdentificaciones || !(identificacion.id));
	};
	
	$scope.puedeEliminarIdentificacionPagador = function (id) {
		return $scope.permisos && ($scope.permisos.eliminarIdentificaciones || !id);
	};
	
	$scope.puedeEliminarBeneficio = function (id) {
		return $scope.permisos && ($scope.permisos.eliminarBeneficios || !id);
	};
	
	$scope.agregarDocumentoPagador = function () {
		var identificacion = {
				tipoDocumento: {
					pais: { id: $scope.documentoPagadorMod.tipoDocumento.pais.id, descripcion: $('#selectPaisPagador option:selected').text() },
					codigo: $('#selectTipoDocumentoPaisPagadorMod option:selected').text(),
					id: $scope.documentoPagadorMod.tipoDocumento.id
				},
				numeroDocumento: $scope.documentoPagadorMod.numeroDocumento,
				numeroInscripcion: $scope.documentoPagadorMod.numeroInscripcion,
				organismoInscripcion: $scope.documentoPagadorMod.organismoInscripcion,
				beneficiario: { id: $scope.beneficiarioMod.id }
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
        		$scope.identificacionesPagadorMod.push(identificacion);
        		$scope.documentoPagadorMod = null;
        	} else {
        		$scope.showErrorMod('Error: ' + result.result);
        	}
        }).error(function (result, status) {
        	$scope.showErrorMod('Error desconocido');
        });
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
	
	$scope.agregarBeneficio = function() {
		
		// FIXME evitar ingreso de duplicados
		
		var beneficio = {
			paisPagador: { id: $scope.paisBeneficio.id, descripcion: $scope.paisBeneficio.descripcion },
			prestacion: { id: $scope.prestacionMod, descripcion: $('#selectPrestacionMod option:selected').text() },
			numeroBeneficio: $scope.numeroBeneficioMod,
			beneficiario: { id: $scope.beneficiarioMod.id }
		};
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/beneficio/validate',
            data: $.param({json: JSON.stringify(beneficio)}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.beneficiosMod.push(beneficio);
        		$scope.prestacionMod = null;
        		$scope.numeroBeneficioMod = null;
        	} else {
        		$scope.showErrorMod('Error: ' + result.result);
        	}
        }).error(function (result, status) {
        	$scope.showErrorMod('Error desconocido');
        });
	};
	
	$scope.borrarBeneficio = function(index) {
		$scope.hideAlerts();
		$scope.beneficiosMod.splice(index, 1);
	};
	
	$scope.permitirBajaVinculo = function (index) {
		if ($scope.permisos && $scope.permisos.bajaVinculos
				&& index >= 0 && $scope.vinculosMod && $scope.vinculosMod.length > index) {
			return $scope.vinculosMod[index].estadoBeneficio.codigo == 'C';
		} else {
			return false;
		}
	};
	
	$scope.guardarModificacion = function () {
		
		var beneficiario = $.extend(true, {}, $scope.beneficiarioMod);
		beneficiario.domicilio = $.extend(true, {}, $scope.domicilioMod);
		beneficiario.nacionalidad = { id: $scope.nacionalidadMod };
		beneficiario.sexo = { codigo: $scope.sexoMod };
		beneficiario.estadoCivil = { id: $scope.estadoCivilMod };
		var fechaNacimiento = $('#inputFechaNacimientoMod').val();
		if (fechaNacimiento && fechaNacimiento != '') {
			beneficiario.fechaNacimiento = $scope.formatDateToServerFormat(fechaNacimiento);
		}
		var fechaFallecimiento = $('#inputFechaFallecimientoMod').val();
		if (fechaFallecimiento && fechaFallecimiento != '') {
			beneficiario.fechaFallecimiento = $scope.formatDateToServerFormat(fechaFallecimiento);
		}
		
		var identificaciones = new Array().concat($scope.identificacionesResidenciaMod);
		identificaciones = identificaciones.concat($scope.identificacionesPagadorMod);
		beneficiario.identificaciones = identificaciones;
		
		beneficiario.beneficios = $scope.beneficiosMod;
		
		var vinculos = new Array();
		for (var i = 0; i < $scope.vinculosMod.length; i++) {
			vinculos.push($.extend(true, {}, $scope.vinculosMod[i]));
			if ($scope.vinculosBaja[i].baja) {
				vinculos[i].motivoEstado = $scope.vinculosBaja[i].motivoEstado;
				vinculos[i].estadoBeneficio.codigo = 'B';
				delete vinculos[i].estadoBeneficio.id;
				delete vinculos[i].estadoBeneficio.descripcion;
				delete vinculos[i].beneficiario;
			}
		}
		
		beneficiario.vinculos = vinculos;
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/beneficiario/update',
            data: $.param({json:JSON.stringify(beneficiario)}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.showSuccess('Modificaci\u00F3n correcta', result.result);
        		$('#inputFechaFallecimientoMod').val(null);
        		$('#btnCerrar').click();
        		$scope.ejecutarBusqueda();
        	} else {
        		$scope.showErrorMod('Error: ' + result.result);
        	}
        }).error(function (result, status) {
        	$scope.showErrorMod('Error desconocido');
        });
	};
	
	$scope.guardarRechazo = function () {
		var data = {
			idBeneficiario: $scope.idBeneficiarioRechazar,
			idTipoMotivo: $scope.idTipoMotivo
		};
		if ($scope.idTipoMotivo == 0) {
			data.motivoEstado = $scope.motivoEstado;
		}
		
    	$http({
            method: 'POST',
            url: $rootScope.baseUrl + '/rest/beneficiario/rechazar',
            data: $.param(data),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result) {
        	if (result.success) {
        		$scope.showSuccess('Exito', result.result);
        		$('#btnCerrarModalRechazo').click();
        		$scope.ejecutarBusqueda();
        	} else {
        		$scope.showErrorRechazar('Error: ' + result.result);
        	}
        }).error(function (result, status) {
        	$scope.showErrorRechazar('Error desconocido');
        });
	};
	
	$scope.volver = function () {
		$location.path("/dashboard");
	};
	
	$scope.cerrarMod = function () {
		$scope.limpiarCamposMod();
	};
	
	$scope.cerrarVer = function () {
		$scope.limpiarCamposVer();
	};
	
	$scope.cerrarRechazo = function () {
		$scope.idBeneficiarioRechazar = null;
		$scope.idTipoMotivo = null;
		$scope.motivoEstado = null;
		$('#txtOtrosMotivos').attr('placeholder', '');
	};
	
}
