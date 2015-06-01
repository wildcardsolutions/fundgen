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
		 	'settings-repository',
		 	'paging-directive',
		 	'setting-details'
		], 
		function (app) {
			console.log("registering controller: settingsController");
			app.register.controller(
					'settingsController', 
					[
					 	'$scope', 
					 	'$dialogs', 
					 	'settingsRepository', 
					 	'applicationSetting',
					 	function ($scope, $dialogs, settingsRepository, applicationSetting) {
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
								settingsRepository.getPage(page, size, searchString)
									.success(function (response) {
										$scope.settings = response.pageContent;
										$scope.page.count = response.totalPages;
										$scope.page.index = response.pageIndex;
									})
									.error(function (response) {
						 				alertify.alert(response.error);
						            });
							}
					 		
							$scope.updatePageSize = function($event, pageSize) {
								$event.preventDefault();
								$scope.page.size = pageSize;
								$scope.page.index = 1;
								applicationSetting.set("pageSize", $scope.page.size);
								settingsRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
							
							$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
					 		
					 		function get(settingId) {
					 			var settings = eval($scope.settings);
					 			var setting = {};
					 			for (var i = 0; i < settings.length; i++ ) {
					 				if(settings[i].id === settingId ) {
					 					setting = settings[i]
					 				}
					 			}
					 			return setting;
					 		}

					 		vm.add = function(setting) {
					 			var data = {};
					 			data.setting = {};
					 			data.update = false;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/settings/setting-details.html',
					 					'settingDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				console.log(data);
					 				if (data) {
					 					settingsRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
											$scope.page.count = result.data.pages;
											$scope.page.index = result.data.pages;
											$scope.searchString = null;
											loadPage($scope.page.index, $scope.page.size, $scope.searchString);
								        });
					 				}
					 			});
					 		};
					 		
					 		vm.edit = function(setting) {
					 			var data = {};
					 			data.setting = setting;
					 			data.update = true;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/settings/setting-details.html',
					 					'settingDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(settings) {
					 				console.log(settings);
					 				var _settings = get(settings.id);
					 				angular.copy(settings, _settings);
					 			});
					 		};
					 		
					 		
					 		
					 		vm.update = function(setting) {
					 	    	var msg = "This will update property setting on database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					settingsRepository.update(setting)
					 						.success(function (response) {
					 							var _setting = getSetting(setting.id);
					 							angular.copy(response, _setting)
					 							reset();
					 						})
					 						.error(function (response) {
					 							alertify.alert(response);
					 						});
					 				}
					 			});
					 		};
					 		
					 		vm.delete = function(setting) {
					 			var msg = "This will delete " + setting.name + " setting from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					settingsRepository.delete(setting.id)
					 						.success(function () {
					 							var _setting = getSetting(setting.id);
					 							_setting.deleted = true;
					 						})
					 						.error(function (response) {
					 							alertify.alert(response);
					 						});
					 				}
					 			})
					 		};
					 		
					 		vm.undelete = function(setting) {
					 			var msg = "This will undelete [" + setting.name + "] setting from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					settingsRepository.undelete(setting.id)
					 						.success(function () {
					 							var _setting = getSetting(setting.id);
					 							_setting.deleted = false;
					 						})
					 						.error(function (response) {
					 							alertify.alert(response);
					 						});
					 				}
					 			})
					 		};
					 		
					 		vm.search = function(searchString) {
					 			loadPage(1, $scope.page.size, searchString);
					 		};
					 		
					 		vm.clearSearch = function() {
					 			$scope.searchString = null;
					 			$scope.page.index = 1;
					 			$scope.page.size = applicationSetting.get("pageSize");
					 			loadPage($scope.page.index, $scope.page.size);
					 		};
					 	}
					]
			);
		}
);