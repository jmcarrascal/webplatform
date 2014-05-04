function PadronController($scope, $rootScope, $http, $routeParams, I18nFactory) {
	$rootScope.module = "padron-report";
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
			})
			
			
	
	 
}
