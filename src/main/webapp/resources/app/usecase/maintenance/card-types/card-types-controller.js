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
		 	'paging-directive',
		 	'card-type-details'
		], 
		function (app) {
			console.log("registering controller: cardTypesController");
			app.register.controller(
					'cardTypesController', 
					[
					 	'$scope', '$dialogs', 'membershipCardTypeRepository', 'applicationSetting',
					 	function($scope, $dialogs, membershipCardTypeRepository, applicationSetting) {
					 		var vm = this;
					 		$scope.page = {};
					 	
					 		$scope.page.count = applicationSetting.get("membershipCardTypePageCount");
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize") | 10;
					 		$scope.searchString = null;

					 		loadPage($scope.page.index, $scope.page.size, $scope.searchString);

							function loadPage(page, size, searchString) {
								membershipCardTypeRepository.getPage(page, size, searchString)
									.success(function (response) {
										$scope.cards = response.pageContent;
										$scope.page.count = response.totalPages;
										$scope.page.index = response.pageIndex;
									})
									.error(function (response) {
						 				alertify.alert(response.error);
						            });
							}
							
							
							$scope.updatePageSize = function($event, pageSize) {
								$event.preventDefault();
								$scope.page.index = 1;
								$scope.page.size = pageSize;
								applicationSetting.set("pageSize", $scope.page.size);
								membershipCardTypeRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
							
							
							$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
							
							
					 		function get(id) {
					 			var cards = eval($scope.cards);
					 			var card = {};
					 			for (var i = 0; i < cards.length; i++ ) {
					 				if(cards[i].id === id ) {
					 					card = cards[i]
					 				}
					 			}
					 			return card;
					 		}
					 		
					 		vm.add = function() {
					 			var data = {};
					 			data.cardType = {};
					 			data.update = false;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/card-types/card-type-details.html',
					 					'cardTypeDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				if (data) {
					 					membershipCardTypeRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
											$scope.page.count = result.data.pages;
											$scope.page.index = result.data.pages;
											$scope.searchString = null;
											loadPage($scope.page.index, $scope.page.size, $scope.searchString);
								        });
					 				}
					 			});
					 		};
					 		
					 		vm.edit = function(cardType) {
					 			var data = {};
					 			data.cardType = cardType;
					 			data.update = true;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/card-types/card-type-details.html',
					 					'cardTypeDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(cardType){
					 				console.log(cardType);
					 				var _cardType = get(cardType.id);
					 				angular.copy(cardType, _cardType);
					 			});
					 		};
					 		
					 		vm.delete = function(card) {
					 			var msg = "This will delete " + card.name + " card type from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					membershipCardTypeRepository.delete(card.id)
						 					.success(function () {
												var _card = get(card.id);
												_card.deleted = true;
											})
											.error(function (response) {
												alertify.alert(response);
											});
					 				}
					 			})
					 		};
					 		
					 		vm.undelete = function(card) {
					 			var msg = "This will undelete " + card.name + " card type from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					membershipCardTypeRepository.undelete(card.id)
								 			.success(function() {
								 				var _card = get(card.id);
												_card.deleted = false;
								 			})
								 			.error(function (error) {
								 				alertify.alert(response);
								            });
					 				}
					 			})
					 		};
					 		
					 		vm.search = function(searchString) {
					 			$scope.searchString = searchString;
					 			loadPage(1, $scope.page.size, $scope.searchString);
					 		};
					 		
					 		vm.clearSearch = function() {
					 			$scope.searchString = null;
					 			$scope.page.index = 1;
					 			$scope.page.size = applicationSetting.get("pageSize");
					 			loadPage($scope.page.index, $scope.page.size, $scope.searchString);
					 		};
					 		
						}
					]
			);
		}
);