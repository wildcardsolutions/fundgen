/**
 * @controller	:	cardDetailsController
 * @author		:	apagador
 * @date		:	2015/05/09
 */
"use strict";

define(
		['app', 'card-inventory-service'], 
		function (app) {
			console.log("registering controller: cardDetailsController");
			app.register.controller(
					'cardDetailsController', 
					[ 
						'$scope', 
					 	'$modalInstance',
					 	'cardInventoryService',
					 	'applicationSetting',
					 	'data',
					 	function($scope, $modalInstance, cardInventoryService, applicationSetting, data) {
					 		
					 		var vm = this;
					 		$scope.card = {};
					 		console.log(data.id);
					 		
					 		initialize(data.id);
					 		
					 		function initialize(cardId) {
					 			cardInventoryService.get(cardId)
					 				.success( function (response) {
					 					console.log(response);
					 					$scope.card = response;
					 				});
					 		}
					 		
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