if (typeof String.prototype.endsWith !== 'function') {
    String.prototype.endsWith = function(suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}

angular.module('app', ['ngResource', 'ng.services', 'ngCookies']).
  //definimos las rutas de la 'app'
  config(['$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
  
  $routeProvider.    	
      when('/dashboard', {
          templateUrl: 'src/views/dashboard.html',
          controller: DashboardController
          }).
            when('/login', {
          templateUrl: 'src/views/login.html',
          controller: LoginController
          }).
          
      //cualquier ruta no definida
      otherwise({
          redirectTo: '/login'});
		  
	  //$locationProvider.hashPrefix('!');
			
			/* Intercept http errors */
			var interceptor = function ($rootScope, $q, $location) {

		        function success(response) {
		            return response;
		        }

		        function error(response) {
		        	
		            var status = response.status;
		            var config = response.config;
		            var method = config.method;
		            var url = config.url;

		            if (status == 401 || status == 403 ) {
		            	if (window.location.href.endsWith('#/login')) {
		            		$('.alert-danger').show();
		            	} else {
		            		window.location = "index.html#/login";
		            	}
		            } else {
		            	$rootScope.error = method + " on " + url + " failed with status " + status;
		            }
		            
		            return $q.reject(response);
		        }

		        return function (promise) {
		            return promise.then(success, error);
		        };
		    };
		    $httpProvider.responseInterceptors.push(interceptor);
 
}]).factory('LoginService', function($resource, $rootScope, $location, $http) {
	console.log('Login Service');
	$rootScope.baseUrl = getBaseUrl($location);
	$http.defaults.headers.common['Accept-Language']='es';
	return $resource(getLoginUrl($location), {},
			{
				authenticate: {
					method: 'POST',					
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				}
			}
		);
}).run(function($rootScope, I18nFactory) {
	 $rootScope.defaultLanguage = [];
     $rootScope.i18n = [];
     
     $rootScope.changeLanguage = function(lang) {
   	    $rootScope.i18n = I18nFactory.getLanguage({ lang: lang, module: $rootScope.module });
   	    
        $rootScope.defaultLanguage = I18nFactory.getInformationLanguage({ lang: lang });
        $rootScope.othersLanguages = I18nFactory.getOthersLanguages({ lang: lang });
    };
    
    I18nFactory.getDefaultLanguage(function (data){
    	$rootScope.defaultLanguage = data;
    	$rootScope.othersLanguages = I18nFactory.getOthersLanguages({ lang: data.abrev });
    });
    
});

function getBaseUrl($location) {
	var protocol = $location.protocol();
	var host = $location.host();
	var port = $location.port();
	
	if (protocol != '' && host != '' && port != '') {
		return protocol + '://' + host + ':' + port + '/webplatform';
	} else {
		return "http://www.transfurlong.com.ar/webplatform";
	}
}


function getLoginUrl($location) {
	var protocol = $location.protocol();
	var host = $location.host();
	var port = $location.port();
	
	var url;
	if (protocol != '' && host != '' && port != '') {
		url = protocol + '://' + host + ':' + port + '\:' + port + '/webplatform';
	} else {
		url = "http://www.transfurlong.com.ar/webplatform";
	}
	return url + "/rest/user/validate";
}
