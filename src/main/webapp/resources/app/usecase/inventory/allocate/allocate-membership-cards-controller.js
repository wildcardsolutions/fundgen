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
		 	'chapter-repository',
		 	'card-inventory-service',
		], 
		function (app) {
			console.log("registering controller: allocateMembershipCardsController");
			app.register.controller(
					'allocateMembershipCardsController', 
					[
						'$scope', 
						'cardInventoryService',
						'membershipCardTypeRepository', 
						'chapterRepository',
						'applicationSetting',
						function($scope, cardInventoryService, membershipCardTypeRepository, chapterRepository, applicationSetting) {
							
							var vm = this;
							
							$scope.card = {};
							
							initializePage();
							
							function initializePage() {
								chapterRepository.getAll()
					 			.success(function (response) {
					 				$scope.chapters = response;
					 				$scope.card.chapter = response[0].id;
					 				$scope.card.chapterDefault = response[0].id;
					 			});
								
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
							
							vm.reset = function() {
								var tmpCardType = $scope.card.cardTypeDefault,
									tmpChapter = $scope.card.chapterDefault
									
								$scope.card.chapter = tmpChapter
								$scope.card.cardType = tmpCardType
								$scope.card.seriesStart = null;
								$scope.card.seriesEnd = null;
								$scope.form.$setPristine(true);
								$scope.form.$setUntouched(true);
								
								$scope.update();
							}
							
							vm.allocate = function(card) {
								if (card.seriesStart > card.seriesEnd) {
									alertify.alert("Please specify valid values.");
									return;
								}
								alertify.confirm("This will allocate membership cards to selected chapter. Proceed?", function(e) {
									if (e) {
										cardInventoryService.allocate(card)
											.success(function(data, status, headers, config) {
												alertify.alert("Successfully allocated membership cards to chapter.");
												vm.reset();
											})
											.error(function(data, status, headers, config) {
												alertify.alert("Failed: " + data.description);
												vm.reset();
											});
									}
								})
							};
						}
					]
			);
		}
);

//
//
///**
// * 
// */
//angular.module('springbootApp').controller(
//		'allocateMembershipCardsController', 
//		[
//		 	'$scope', 
//		 	'cardInventoryService',
//		 	'membershipCardTypeRepository', 
//		 	'chapterRepository',
//		 	'applicationSetting',
//		 	function($scope, cardInventoryService, membershipCardTypeRepository, chapterRepository, applicationSetting) {
//		 		
//		 		var vm = this;
//		 		
//		 		$scope.card = {};
//		 		
//		 		initializePage();
//		 		
//		 		function initializePage() {
//		 			chapterRepository.getAll()
//			 			.success(function (response) {
//			 				$scope.chapters = response;
//			 				$scope.card.chapter = response[0].id;
//			 				$scope.card.chapterDefault = response[0].id;
//			 			});
//		 			
//			 		membershipCardTypeRepository.getAll()
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
//		 		vm.reset = function() {
//		 			var tmpCardType = $scope.card.cardTypeDefault,
//		 				tmpChapter = $scope.card.chapterDefault
//		 				
//		 			$scope.card.chapter = tmpChapter
//		 			$scope.card.cardType = tmpCardType
//		 			$scope.card.seriesStart = null;
//		 			$scope.card.seriesEnd = null;
//		 			$scope.form.$setPristine(true);
//		 			$scope.form.$setUntouched(true);
//		 			
//		 			$scope.update();
//		 		}
//		 		
//		 		vm.allocate = function(card) {
//		 			if (card.seriesStart > card.seriesEnd) {
//		 				alertify.alert("Please specify valid values.");
//		 				return;
//		 			}
//		 			alertify.confirm("This will allocate membership cards to selected chapter. Proceed?", function(e) {
//		 				if (e) {
//		 					cardInventoryService.allocate(card)
//		 						.success(function(data, status, headers, config) {
//		 							alertify.alert("Successfully allocated membership cards to chapter.");
//		 							vm.reset();
//		 						})
//		 						.error(function(data, status, headers, config) {
//		 							alertify.alert("Failed: " + data.description);
//		 							vm.reset();
//		 						});
//		 				}
//		 			})
//		 		};
//			}
//		]
//);