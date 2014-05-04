function IdiomaController($scope,$http) {
	
	$scope.selectPortugues = function(){
		$scope.flag = "flag-ar";
		$scope.flagSelect ="flag-br";
		$scope.idiomaNoSelect = "Español";
		console.log("Idioma actual portugues");
	};
	
	$scope.selecEspanol = function(){
		$scope.flag = "flag-br";
		$scope.flagSelect ="flag-ar";
		$scope.idiomaNoSelect = "Portugues";
		console.log("Idioma actual español");
	};
	
	$scope.changeIdioma = function() {
		if ($scope.flag == "flag-br") {
			$scope.selectPortugues();
			$http.defaults.headers.common['Accept-Language']='pt';
		}else{
			$scope.selecEspanol();
			$http.defaults.headers.common['Accept-Language']='es';
		}
	};
	
	$scope.getIdiomaActual = function(){
		var idioma = $http.defaults.headers.common['Accept-Language'];
		if(idioma=="pt"){
			$scope.selectPortugues();
		}else{
			$scope.selecEspanol();
		}
	};
	
	$scope.getIdiomaActual();
}