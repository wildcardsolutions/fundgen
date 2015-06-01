/**
 * @controller	:	accessRightsController
 * @author		:	apagador
 * @date		:	2015/05/09
 */
"use strict";

define(
		['app', 'membership-information-repository'], 
		function (app) {
			console.log("registering controller: memberDetailsController");
			app.register.controller(
					'memberDetailsController', 
					[ 
						'$scope', 
					 	'$modalInstance',
					 	'membershipInformationRepository',
					 	'applicationSetting',
					 	'data',
					 	function($scope, $modalInstance, membershipInformationRepository, applicationSetting, data) {
					 		
					 		var vm = this;
					 		
					 		$scope.close = function(){
					 			$modalInstance.dismiss('closed');  
					 		}
					 		
					 		$scope.cancel = function(){
					 		    $modalInstance.dismiss('canceled');  
					 		};
					 		
						}
					]
			);
		}
	);

/**
 * 
 */
//angular.module('springbootApp').controller(
//		'showMemberDetailsController', 
//		[
//		 	'$scope', 
//		 	'$modalInstance',
//		 	'membershipInformationRepository',
//		 	'applicationSetting',
//		 	'data',
//		 	function($scope, $modalInstance, membershipInformationRepository, applicationSetting, data) {
//		 		
//		 		var vm = this;
//		 		
//		 		$scope.close = function(){
//		 			$modalInstance.dismiss('closed');  
//		 		}
//		 		
//		 		$scope.cancel = function(){
//		 		    $modalInstance.dismiss('canceled');  
//		 		};
//		 		
//			}
//		]
//);