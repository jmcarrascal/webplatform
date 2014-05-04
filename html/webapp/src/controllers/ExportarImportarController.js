function ExportarImportarController($rootScope, $scope, $http, $routeParams, I18nFactory) {
	$rootScope.module = "export-import";
	$rootScope.i18n = I18nFactory.getLanguage({ lang: $rootScope.defaultLanguage.abrev, module: $rootScope.module });
	
	$scope.resultadoRestImport = function(response) {
		var xhr = response.currentTarget;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				response = $scope.$eval(xhr.responseText)[0];
				$scope.importFile = response;
				console.log("result:",$scope.importFile);
				console.log("result.name:", $scope.importFile.name);
				console.log("result.archivo_almacenado",$scope.importFile.archivo_almacenado);
				console.log("eval(result.archivo_almacenado)",eval($scope.importFile.archivo_almacenado));
				if(eval($scope.importFile.archivo_almacenado)){
					var id=$scope.importFile.archivo_almacenado_como.split("\.")[0];
					$scope.addToTable(id,$scope.importFile.name,
							          "/rest/beneficiario/importFile/",
							          $scope.importFile.archivo_almacenado_como);
				}else{
					alert("Error:"+$scope.importFile.razon);
				}	
			}
		}
	};
	
	$scope.addToTable = function(id,descripcion,dir,file_name){		
		console.log("Se agregó a la tabla el archivo: ",file_name);
		var fila = "<tr><td>"+descripcion+"</td>"+
		"<td><a target='_blank' class='hint--right hint--info' data-hint='Descargar' "+
		"href='"+$rootScope.baseUrl + dir + file_name +"/download'>"+
	    "<i class='icon-download-alt'></i></a>"+
        "<a class='hint--right hint--info' data-hint='Eliminar' type='button'>"+
	    "<i class='icon-remove'></i></a></td></tr>";
		$('#tableBody').append(fila);
	};
	
	$scope.import = function() {
		console.log("inicializando summit de importacion");
		var data, xhr;

		data = new FormData();
		data.append('file', $('#file')[0].files[0]);

		xhr = new XMLHttpRequest();

		var url = $rootScope.baseUrl + '/rest/beneficiario/import';
		var token = $http.defaults.headers.common['Auth-Token'];

		// console.log(url);
		// console.log("Token>>>" + token);

		xhr.open('POST', url, true);
		xhr.setRequestHeader("Auth-Token", token);
		xhr.onreadystatechange = $scope.resultadoRestImport;
		xhr.send(data);
	};

	// ocultar y mostrar segun elección
	$scope.checkboxSelection = '1';
	$scope.checkboxTipoExportSelection = '1';

	$scope.isCheckboxSelected = function(index) {
		return index === $scope.checkboxSelection;
	};
	
	$scope.isCheckboxExportSelected = function(index, index2) {
		var resultado = (index === $scope.checkboxSelection) && (index2 === $scope.checkboxTipoExportSelection);
		return resultado;
	};
	
	$scope.addToTablePorExport = function(){
		alert("agregar archivo a la tabla");
	};
	
	$scope.addListToTable = function(list){		
		for(i=0,n=list.length;i<n;i++){
			console.log(list[i].pais.id);
			console.log(list[i].pais.descripcion);
			console.log(list[i].fileName);
			$scope.addToTable(list[i].pais.id,list[i].pais.descripcion,
			          "/rest/beneficiario/exportFile/",
			          list[i].fileName);
		}
	};	
	
	
	$scope.findIdPais = function(){		
		console.log("buscando id del país");
		if($scope.idPais==null){
			$http({
				method : 'GET',
				url : $rootScope.baseUrl + '/rest/config/pais'
			}).success(function(result) {
				console.log("id del país actual", result.id);
				$scope.idPais = result.id;
			});
		}
	};
	
	$scope.exportPorGestonado = function() {
		$('#tableBody').html("");
		$http({
			method : 'GET',
			url : $rootScope.baseUrl + '/rest/beneficiario/export/padron/gestionado'
		}).success(function(result) {
			$scope.listExport = result;
			console.log($scope.listExport);
			console.log($scope.listExport.length);
			$scope.addListToTable($scope.listExport);
		}).error(function() {
			$scope.listExport = null;
		});
	};
	
	$scope.exportPorPagado = function() {
		$('#tableBody').html("");
		$http({
			method : 'GET',
			url : $rootScope.baseUrl + '/rest/beneficiario/export/padron/pagado'
		}).success(function(result) {
			$scope.listExport = result;
			console.log($scope.listExport);
			console.log($scope.listExport.length);
			$scope.addListToTable($scope.listExport);
		}).error(function() {
			$scope.listExport = null;
		});
	};
}
