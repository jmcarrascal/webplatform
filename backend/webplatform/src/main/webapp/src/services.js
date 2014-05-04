'use strict';

/* Services */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}
 'audit':  {method:'GET'},

 */

var services = angular.module('ng.services', ['ngResource']);

services.factory('I18nFactory', function ($resource) {
	return $resource('http://www.transfurlong.com.ar/webplatform/rest/i18n/:action/:lang/:module', {}, {
		getLanguage: { method: 'GET', params: {'action' : 'getLanguage'}, isArray: false },
		getDefaultLanguage: { method: 'GET', params: {'action' : 'getDefaultLanguage'}, isArray: false },
		getOthersLanguages: { method: 'GET', params: {'action' : 'getOthersLanguages'}, isArray: true },
		getInformationLanguage: { method: 'GET', params: {'action' : 'getInformationLanguage'}, isArray: false }
    });
});

