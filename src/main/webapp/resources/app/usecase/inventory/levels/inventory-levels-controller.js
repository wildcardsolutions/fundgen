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
			console.log("registering controller: inventoryLevelsController");
			app.register.controller(
					'inventoryLevelsController', 
					[
						'$scope', 
						'$routeParams',
						'$location',
						'cardInventoryService',
						'membershipCardTypeRepository', 
						'applicationSetting',
						function($scope, $routeParams, $location, cardInventoryService, membershipCardTypeRepository, applicationSetting) {
							
							var vm = this;
							console.log($routeParams.cardType);
							
							$scope.currentTab = $routeParams.cardType
							
							$scope.tabs = [];
							
							
							initializePage();
							
							function initializePage() {
								membershipCardTypeRepository.getAll()
									.success(function (response) {
										$scope.cardTypes = response;
										console.log($scope.cardTypes);
										$scope.tabs.push({'path': 'all', 'label' : 'All', 'active' : 'all' === $scope.currentTab});
										for (var i = 0; i < $scope.cardTypes.length; i++ ) {
											if (!$scope.cardTypes[i].deleted) {
												var path = $scope.cardTypes[i].name.toLowerCase().replace(/ /g, '-')
												$scope.tabs.push({
													'path': path, 
													'label' :  $scope.cardTypes[i].name, 
													'active' : path === $scope.currentTab});
											}
										}
									});
							}
							
							$scope.update = function (cardTypeName) {
								$location.path("/inventory/levels/" + cardTypeName.toLowerCase().replace(/ /g, '-'), false);
								$scope.currentTab = cardTypeName;
							};
							
						}
					]
			);
		}
);

/**
 * 
 */
//var inventoryLevelsController = function ($scope, $routeParams, $location, $rootScope, $http) {
//	
//	var vm = this;
//	$scope.tabs = [];
//	vm.currentTab = $routeParams.cardType
//	
//	
//	$http.get("/membershipCardType")
//		.success(function (response) {
//			$scope.cardTypes = response;
//			$scope.tabs.push({'all' : false});
//			for (var i = 0; i < $scope.cardTypes.length; i++) {
//				$scope.cardTypes[i].title = $scope.cardTypes[i].name.toLowerCase().replace(/ /g, '-');
//				var title = $scope.cardTypes[i].title;
//				var active = (title === vm.currentTab);
//				$scope.tabs.push({title : active});
//			} 
//			console.log($scope.tabs.length);
//			for (var i = 0; i < $scope.tabs.length; i++) {
//				console.log("i=" + i + ", " + $scope.tabs[i].all);
//			}
//			updateTab(vm.currentTab);
//		}
//	);
//	
//	
//	
//	$scope.update = function (cardTypeName) {
//		$location.path("/inventory/levels/" + cardTypeName, false);
//		updateTab(cardTypeName)
//		
//	};
//	
//	function updateTab(cardTypeName) {
//		vm.currentTab = cardTypeName;
//
//	}
//	
//};