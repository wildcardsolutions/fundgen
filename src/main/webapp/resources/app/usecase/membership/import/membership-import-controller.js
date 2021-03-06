/**
 * 
 */
"use strict";

/**
 * 
 */
define(
		[
		 	'app',
		 	'membership-information-repository',
		 	'membership-card-type-repository',
		 	'officer-repository',
		 	'declaration-period-service',
			'panel-directive',
		 	'paging-directive',
		 	'action-directive',
		 	'dropdown-directive'
		], 
		function (app) {
			console.log("registering controller: membershipImportController");
			app.register.controller(
					'membershipImportController', 
					[
					 	'$scope', 
					 	'membershipCardTypeRepository', 
					 	'membershipInformationRepository',
					 	'officerRepository',
					 	'declarationPeriodService',
					 	'applicationSetting',
					 	function($scope, membershipCardTypeRepository, membershipInformationRepository, officerRepository, declarationPeriodService, applicationSetting) {
					 		
						}
					]
			);
		}
);

