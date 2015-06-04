/**
 * 
 */
"use strict";

define(
		[
		 	'app',
		 	'task-service',
		 	'paging-directive'
		], 
		function (app) {
			console.log("registering controller: chapterTasksController");
			app.register.controller(
					'chapterTasksController', 
					[
					 	'$scope', 
					 	'$dialogs',
					 	'taskService',
					 	'applicationSetting',
					 	function ($scope, $dialogs, taskService, applicationSetting) {

					 		var vm = this;
					 		
					 		$scope.tasks = {};
					 		$scope.page = {};
					 		
					 		$scope.page.count = applicationSetting.get("chapterTaskPageCount");
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize") | 10;
					 		$scope.searchString = null;
													
							loadPage($scope.page.index, $scope.page.size, $scope.searchString);
					 		
							function loadPage(page, size, searchString) {
								taskService.getChapterTaskPage(page, size, searchString)
									.success(function (response) {
										$scope.tasks = response.pageContent;
										$scope.page.count = response.totalPages;
										$scope.page.index = response.pageIndex;
						 			});
							}
							
							$scope.updatePageSize = function($event, pageSize) {
								$event.preventDefault();
								$scope.page.index = 1;
								$scope.page.size = pageSize;
								applicationSetting.set("pageSize", $scope.page.size);
								taskService.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
							
							$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
							
							function removeTask(taskId) {
								
							}
							
							vm.cancel = function(task, index) {
					 			var msg = "This will cancel task from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					taskService.cancelTask(task.id)
						 					.success(function () {
												$scope.tasks.splice(index, 1);											
											})
											.error(function (response) {
												alertify.alert(response.error);
											});
					 				}
					 			})
					 		};
					 	}
					]
			);
		}
);