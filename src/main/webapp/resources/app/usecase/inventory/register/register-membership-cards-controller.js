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
		 	'membership-card-type-repository',
		 	'card-inventory-service',
		 	'paging-directive'
		], 
		function (app) {
			console.log("registering controller: registerMembershipCardsController");
			app.register.controller(
					'registerMembershipCardsController', 
					[
						'$scope', 
					 	'cardInventoryService',
					 	'membershipCardTypeRepository', 
					 	'applicationSetting',
					 	function($scope, cardInventoryService, membershipCardTypeRepository, applicationSetting) {
					 		var vm = this;
					 		
					 		$scope.card = {};
					 		
					 		initializePage();
					 		
					 		function initializePage() {
					 			membershipCardTypeRepository.getAll()
						 			.success(function (response) {
						 				$scope.cardTypes = response;
						 				$scope.card.cardType = response[0].id;
						 				$scope.card.cardTypeDefault = response[0].id;
						 				$scope.prefix = response[0].prefix;
						 			});
					 		}
					 		
					 		$scope.update = function() {
					 			var cardType = $scope.card.cardType;
					 			var cards = eval($scope.cardTypes);
					 			var card = {};
					 			for (var i = 0; i < cards.length; i++ ) {
					 				if(cards[i].id === cardType ) {
					 					$scope.prefix = cards[i].prefix;
					 					break;
					 				}
					 			}
					 		}
					 		
					 		
					 		vm.register = function(card) {
					 			if (card.seriesStart > card.seriesEnd) {
					 				alertify.alert("Please specify valid values.");
					 				return;
					 			}
					 			alertify.confirm("This will register membership cards using the series specified. Proceed?", function(e) {
					 				if (e) {
					 					cardInventoryService.register(card)
					 						.success(function(data, status, headers, config) {
					 							alertify.alert("Successfully registered membership card inventory.");
					 							vm.reset();
					 						})
					 						.error(function(data, status, headers, config) {
					 							alertify.alert("Failed: " + data.description);
					 							vm.reset();
					 						});
					 				}
					 			})
					 		};
					 		
					 		vm.reset = function() {
					 			var tmp = $scope.card.cardTypeDefault;
					 			$scope.card.cardType = tmp
					 			$scope.card.seriesStart = null;
					 			$scope.card.seriesEnd = null;
					 			
					 			$scope.update();
					 			
					 			$scope.form.$setPristine();
					 			$scope.form.$setUntouched();
					 		}
						}
					]
			);
		}
);

//
///**
// * 
// */
//angular.module('springbootApp').controller(
//		'registerMembershipCardsController', 
//		[
//		 	'$scope', 
//		 	'cardInventoryService',
//		 	'cardRepository', 
//		 	'applicationSetting',
//		 	function($scope, cardInventoryService, cardRepository, applicationSetting) {
//		 		var vm = this;
//		 		
//		 		$scope.card = {};
//		 		
//		 		initializePage();
//		 		
//		 		function initializePage() {
//			 		cardRepository.getAll()
//			 			.success(function (response) {
//			 				$scope.cardTypes = response;
//			 				$scope.card.cardType = response[0].id;
//			 				$scope.card.cardTypeDefault = response[0].id;
//			 				$scope.prefix = response[0].prefix;
//			 			});
//		 		}
//		 		
//		 		$scope.update = function() {
//		 			var cardType = $scope.card.cardType;
//		 			var cards = eval($scope.cardTypes);
//		 			var card = {};
//		 			for (var i = 0; i < cards.length; i++ ) {
//		 				if(cards[i].id === cardType ) {
//		 					$scope.prefix = cards[i].prefix;
//		 					break;
//		 				}
//		 			}
//		 		}
//		 		
//		 		
//		 		vm.register = function(card) {
//		 			if (card.seriesStart > card.seriesEnd) {
//		 				alertify.alert("Please specify valid values.");
//		 				return;
//		 			}
//		 			alertify.confirm("This will register membership cards using the series specified. Proceed?", function(e) {
//		 				if (e) {
//		 					cardInventoryService.register(card)
//		 						.success(function(data, status, headers, config) {
//		 							alertify.alert("Successfully registered membership card inventory.");
//		 							vm.reset();
//		 						})
//		 						.error(function(data, status, headers, config) {
//		 							alertify.alert("Failed: " + data.description);
//		 							vm.reset();
//		 						});
//		 				}
//		 			})
//		 		};
//		 		
//		 		vm.reset = function() {
//		 			var tmp = $scope.card.cardTypeDefault;
//		 			$scope.card.cardType = tmp
//		 			$scope.card.seriesStart = null;
//		 			$scope.card.seriesEnd = null;
//		 			
//		 			$scope.update();
//		 			
//		 			$scope.form.$setPristine();
//		 			$scope.form.$setUntouched();
//		 		}
//			}
//		]
//);