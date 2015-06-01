/**
 * 
 */
"use strict";

define(
		[
		 	'app',
		 	'officer-repository',
		 	'paging-directive',
		 	'officer-details'
		], 
		function (app) {
			console.log("registering controller: officerController");
			app.register.controller(
					'officerController', 
					[
					 	'$scope', 
					 	'$dialogs', 
					 	'officerRepository', 
					 	'applicationSetting',
					 	function($scope, $dialogs, officerRepository, applicationSetting) {
					 		var vm = this;

					 		$scope.page = {};
					 		$scope.page.count = applicationSetting.get("settingsPageCount");
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize");
					 		$scope.page.totalElements = 0;
					 		$scope.page.totalPages = 0;
					 		
					 		$scope.searchString = null;
					 		
					 		loadPage($scope.page.index, $scope.page.size, $scope.searchString);
					 		
					 		function loadPage(page, size, searchString) {
					 			officerRepository.getPage(page, size, searchString)
									.success(function (response) {
										$scope.officers = response.pageContent;
										$scope.page.count = response.totalPages;
										$scope.page.index = response.pageIndex;
									})
									.error(function (response) {
						 				alertify.alert(response.error);
						            });
							}
					 		
					 		$scope.updatePageSize = function($event, pageSize) {
					 			$event.preventDefault()
								$scope.page.index = 1;
					 			$scope.page.size = pageSize;
								applicationSetting.set("pageSize", $scope.page.size);
								officerRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
					 		
					 		$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
					 		
					 		function get(id) {
					 			var officers = eval($scope.officers);
					 			var officer = {};
					 			for (var i = 0; i < officers.length; i++ ) {
					 				if(officers[i].officerId === id ) {
					 					officer = officers[i]
					 				}
					 			}
					 			return officer;
					 		}
					 		
					 		vm.add = function() {
					 			var data = {};
					 			data.officer = {};
					 			data.update = false;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/officer/officer-details.html',
					 					'officerDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				console.log(data);
					 				if (data) {
					 					officerRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
											$scope.page.count = result.data.pages;
											$scope.page.index = result.data.pages;
											$scope.searchString = null;
											loadPage($scope.page.index, $scope.page.size, $scope.searchString);
								        });
					 				}
					 			});
					 		};
					 		
					 		vm.edit = function(officer) {
					 			var data = {};
					 			data.officer = officer;
					 			data.update = true;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/officer/officer-details.html',
					 					'officerDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(officer) {
					 				console.log(officer);
					 				var _officer = get(officer.officerId);
					 				angular.copy(officer, _officer);
					 			});
					 		};
					 		
					 		vm.delete = function (officer) {
					 			var msg = "This will delete [" + officer.lastname + ", " + officer.firstname + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					officerRepository.delete(officer.officerId)
						 					.success(function () {
						 						var _officer = get(officer.officerId);
						 						_officer.deleted = true;
											})
											.error(function (response) {
												alertify.alert(response.error);
											});
					 				}
					 			});
					 		};
					 		
					 		vm.undelete = function(chapter) {
					 			var msg = "This will undelete[" + officer.lastname + ", " + officer.firstname + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					officerRepository.undelete(chapter.id)
								 			.success(function() {
								 				var _officer = get(officer.officerId);
						 						_officer.deleted = false;
								 			})
								 			.error(function (response) {
								 				alertify.alert(response.error);
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
