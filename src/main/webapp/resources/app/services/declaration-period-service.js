/**
 * 
 */
"use strict";

/**
 * 
 */
define(
		['app', 'angularjs'], 
		function (app) {
			app.register.service(
					'declarationPeriodService', 
					[ 
					  	'$http', 
					  	function($http) {
							var repositoryBase = "/api/declarationPeriods";
						
							this.getDeclarationPeriods = function () {
								return $http.get(repositoryBase);
							};
						
						}
					]
			);
		}
	);