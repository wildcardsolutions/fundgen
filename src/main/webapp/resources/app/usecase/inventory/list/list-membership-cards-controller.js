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
		 	'card-details',
		 	'member-details',
		 	'paging-directive'
		], 
		function (app) {
			console.log("registering controller: listMembershipCardsController");
			app.register.controller(
					'listMembershipCardsController', 
					[
						'$scope', 
						'$dialogs',
						'$location',
						'$routeParams',
						'cardInventoryService',
						'membershipCardTypeRepository', 
						'applicationSetting',
						function($scope, $dialogs, $location, $routeParams, cardInventoryService, membershipCardTypeRepository, applicationSetting) {
							var vm = this;
					 		
					 		$scope.card = {};
					 		$scope.page = {};
					 		$scope.list = {};
					 		$scope.list.membershipCards = {};
					 		
//					 		if ($routeParams.cardType) {
//					 			$scope.card.cardType = parseInt($routeParams.cardType);
//					 		}
//					 		console.log("$scope.card.cardType=" + $scope.card.cardType)
//					 		
//					 		if ($routeParams.seriesStart) {
//					 			$scope.card.seriesStart = parseInt($routeParams.seriesStart);
//					 		} 
//					 		console.log("$scope.card.seriesStart=" + $routeParams.seriesStart)
//					 		
//					 		if ($routeParams.seriesEnd) {
//					 			$scope.card.seriesEnd = parseInt($routeParams.seriesEnd);
//					 		} 
//					 		console.log("$scope.card.seriesStart=" + $routeParams.seriesStart)
//					 		
//					 		if ($routeParams.pageIndex) {
//					 			$scope.page.index = $routeParams.pageIndex;
//					 			
//					 		} else {
//					 			$scope.page.index = 1;
//					 		}
//					 		console.log("$scope.page.index=" + $scope.page.index)
//					 		
//					 		if ($routeParams.pageSize) {
//					 			$scope.page.size = $routeParams.pageSize;
//					 		} else {
//					 			$scope.page.size = applicationSetting.get("pageSize");
//					 		}
					 		
					 		
					 		$scope.page.count = 0;
					 		$scope.page.enableStart = false;
					 		$scope.page.enableEnd = false;
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize");
					 			
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
					 		
					 		function list(card) {
					 			
					 			
					 		}
					 		
					 		$scope.update = function() {
					 			console.log("$scope.update");
					 			var cardType = $scope.card.cardType;
					 			var cards = eval($scope.cardTypes);
//					 			$scope.card.seriesStart= null;
//					 			$scope.card.seriesEnd= null;
//					 			$scope.list.membershipCards = {};
//					 			$scope.page.count = 0;
//						 		$scope.page.enableStart = false;
//						 		$scope.page.enableEnd = false;
					 			for (var i = 0; i < cards.length; i++ ) {
					 				if(cards[i].id === cardType ) {
					 					$scope.prefix = cards[i].prefix;
					 					break;
					 				}
					 			}
					 			
					 			console.log("$scope.prefix=" + $scope.prefix);
					 			
					 		}
					 		
					 		$scope.getNumber = function(num) {
								var a =  new Array(num);   
								for (var i=0; i<num; i++) {
									a[i] = i + 1;
								}
								return a;
							};
							
							$scope.updatePageSize = function() {
								$scope.page.index = 1;
								applicationSetting.set("pageSize", $scope.page.size);
								vm.list($scope.card);
							}
							
							$scope.updatePage = function($event, page) {
								$event.preventDefault();
								if (page == $scope.page.index) {
									return;
								}
								$scope.page.index = page;
								if ($scope.page.count == $scope.page.index) {
									$scope.page.enableEnd = false;
									$scope.page.enableStart = true;
								} else if (1 == $scope.page.index) {
									$scope.page.enableEnd = true;
									$scope.page.enableStart = false;
								} else {
									$scope.page.enableEnd = true;
									$scope.page.enableStart = true;
								}
								vm.list($scope.card);
							};

					 		vm.list = function(card) {
					 			console.log(card.seriesStart + ", " + card.seriesEnd);
					 			if (card.seriesStart > card.seriesEnd) {
					 				alertify.alert("Please specify valid values.");
					 				return;
					 			}

					 			card.pageIndex = $scope.page.index;
					 			card.pageSize = $scope.page.size;

					 			cardInventoryService.list(card)
					 				.success(function (response) {
					 					$scope.list.membershipCards = response.pageContent;
					 					$scope.page.count = response.totalPages;
					 			 		$scope.page.index = response.pageIndex;
					 			 		$scope.page.enableStart = ($scope.page.index>1);
					 			 		$scope.page.enableEnd = ($scope.page.index<$scope.page.count);
					 			 		//$location.path("/inventory/list/" + card.cardType + "/" + card.seriesStart + "/" + card.seriesEnd + "/" + card.pageIndex + "/" + card.pageSize, false);
					 				})
					 				.error(function(data, status, headers, config) {
					 					 var messages = data.error || "Request failed";
					 					alertify.alert("Failed: " + messages);
					 					vm.reset();
					 				});
					 		};
					 		
					 		vm.reset = function() {
					 			var cardType = $scope.card.cardTypeDefault;
					 			$scope.card.cardType = cardType
					 			$scope.card.seriesStart = null;
					 			$scope.card.seriesEnd = null;
					 			$scope.list.show = false;
					 			
					 			$scope.page.count = 0;
			 			 		$scope.page.index = 1;
			 			 		$scope.page.enableStart = false;
			 			 		$scope.page.enableEnd = false;
					 			
			 			 		$scope.list.membershipCards = {};
			 			 		
					 			$scope.card.form.$setPristine();
					 			$scope.card.form.$setUntouched();
					 			
					 			$scope.update();
					 		}
					 		
					 		
					 		vm.showCardDetails = function(cardId) {
					 			var data = {};
					 			data.id = cardId;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/card-details/card-details.html',
					 					'cardDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				
					 			});
					 		}
					 		
					 		vm.showMemberDetails = function(memberId) {
					 			var data = {};
					 			data.id = memberId;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/member-details/member-details.html',
					 					'memberDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				
					 			});
					 		}
						}
					]
			);
		}
);
