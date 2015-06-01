/**
 * 
 */
"use strict";

define(
		[
		 	'app',
		 	'angularjs',
		 	'roles-repository',
		 	'access-rights',
		 	'paging-directive'
		], 
		function (app) {
			console.log("registering controller: rolesController");
			app.register.controller(
					'rolesController', 
					[
					 	'$scope', 
					 	'$location',
					 	'$anchorScroll',
					 	'$dialogs',
					 	'rolesRepository', 
					 	'applicationSetting',
					 	function($scope, $location, $anchorScroll, $dialogs, rolesRepository, applicationSetting) {
					 		
					 		var vm = this;
					 		$scope.page = {};
					 		$scope.page.count = applicationSetting.get("rolesPageCount");
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize");
					 		//$scope.page.totalElements = 0;
					 		//$scope.page.totalPages = 0;
					 		
					 		console.log("$scope.page.count=" + $scope.page.count);
					 		
					 		$scope.status = null;
					 		$scope.update = false;
					 		$scope.add = true;
					 		$scope.searchString = null;
					 		
					 		$scope.role = {
					 				roleId : null,
					 				roleName : null,
					 				roleDescription : null
					 		};
					 		
					 		$scope.editRole = {};
					 		angular.copy($scope.role, $scope.editRole)
			
					 		loadPage($scope.page.index, $scope.page.size, $scope.searchString);
			
							function loadPage(page, size, searchString) {
								rolesRepository.getPage(page, size, searchString)
									.success(function (response) {
										$scope.roles = response.pageContent;
										$scope.page.count = response.totalPages;
										$scope.page.index = response.pageIndex;
									})
									.error(function (response) {
						 				alertify.alert(response.error);
						            });
							}
							
							$scope.getNumber = function(num) {
								var a =  new Array(num);   
								for (var i=0; i<num; i++) {
									a[i] = i + 1;
								}
								return a;
							};
							
							$scope.updatePageSize = function($event, pageSize) {
								$event.preventDefault();
								$scope.page.size = pageSize;
								$scope.page.index = 1;
								applicationSetting.set("pageSize", $scope.page.size);
								rolesRepository.getPageCount($scope.page.size).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
							
							$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
							
							function get(id) {
					 			var roles = eval($scope.roles);
					 			var role = {};
					 			for (var i = 0; i < roles.length; i++ ) {
					 				if(roles[i].roleId === id ) {
					 					role = roles[i]
					 				}
					 			}
					 			return role;
					 		}
					 		
					 		function reset() {
					 			$scope.update = false;
					 			$scope.add = true;
					 			
					 			$scope.role = {};
					 			angular.copy($scope.role, $scope.editRole)
					 			
					 			$scope.form.$setPristine(true);
					 			$scope.form.$setUntouched(true);
					 		}
					 		
					 		vm.reset = function() {
					 			angular.copy($scope.role, $scope.editRole)
					 			$scope.form.$setPristine(true);
					 			$scope.form.$setUntouched(true);
					 		};
					 		
					 		vm.cancel = function() {
					 			reset();
					 		};
					 		
					 		vm.edit = function(role) {
					 			$scope.update = true;
					 			$scope.add = false;
					 			
					 			angular.copy(role, $scope.role)
					 			angular.copy(role, $scope.editRole)
					 			$location.hash('top');
					 		    $anchorScroll();
					 		};
					 		
					 		vm.add = function(role) {
					 			var msg = "This will add new role information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					rolesRepository.insert(role)
											.success(function (response) {
												rolesRepository.getPageCount($scope.page.size).then(function(result) {
													$scope.page.count = result.data.pages;
													$scope.page.index = result.data.pages;
													loadPage($scope.page.index, $scope.page.size);
										        });
												reset();
											})
											.error(function (response) {
												alertity.alert(response.error);
											});
					 				}
					 			});
					 		};
					 		
					 		vm.update = function(role) {
					 	    	var msg = "This will update card information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					rolesRepository.update(role)
											.success(function (response) {
												var _role = get(role.roleId);
												angular.copy(response, _role)
												reset();
											})
											.error(function (response) {
												alertify.alert(response);
											});
					 				}
					 			});
					 		};
					 		
					 		vm.delete = function(role) {
					 			var msg = "This will delete [" + role.roleName + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					rolesRepository.delete(role.roleId)
						 					.success(function () {
												var _role = get(role.roleId);
												_role.deleted = true;
											})
											.error(function (response) {
												alertify.alert(response.error);
											});
					 				}
					 			})
					 		};
					 		
					 		vm.undelete = function(role) {
					 			var msg = "This will undelete  [" + role.roleName + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					rolesRepository.undelete(role.roleId)
								 			.success(function() {
								 				var _role = get(role.roleId);
												_role.deleted = false;
								 			})
								 			.error(function (error) {
								 				alertify.alert(response);
								            });
					 				}
					 			})
					 		};
							
					 		vm.access = function(role) {
					 			var data = {};
					 			data.role = angular.copy(role);
					 			data.access = {};
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/access-rights/access-rights.html',
					 					'accessRightsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				
					 			});
			//		 	        dlg.result.then(function(data){
			//		 	        	alert(data.role.roleName);
			//		 	        },function(){
			//		 	        	alert("hohoho");
			//		 	        });
					 		};
					 		
					 		
					 		vm.search = function(searchString) {
					 			$scope.searchString = searchString;
					 			loadPage(1, $scope.page.size, $scope.searchString);
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