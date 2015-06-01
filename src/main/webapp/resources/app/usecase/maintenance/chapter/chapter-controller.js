/**
 * 
 */
"use strict";

define(
		[
		 	'app',
		 	'chapter-repository',
		 	'paging-directive',
		 	'chapter-details'
		], 
		function (app) {
			console.log("registering controller: chapterController");
			app.register.controller(
					'chapterController', 
					[
					 	'$scope', '$dialogs', 'chapterRepository', 'applicationSetting',
					 	function($scope, $dialogs, chapterRepository, applicationSetting) {
					 		
					 		var vm = this;
					 		$scope.page = {};
					 		$scope.page.count = applicationSetting.get("chapterPageCount");
					 		$scope.page.index = 1;
					 		$scope.page.size = applicationSetting.get("pageSize");
					 		$scope.searchString = null;
			
					 		loadPage($scope.page.index, $scope.page.size, $scope.searchString);
			
							function loadPage(page, size, searchString) {
								chapterRepository.getPage(page, size, searchString)
									.success(function (response) {
										$scope.chapters = response.pageContent;
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
								chapterRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
									$scope.page.count = result.data.pages;
									loadPage($scope.page.index, $scope.page.size, $scope.searchString);
						        });
							}
							
							$scope.updatePage = function(page) {
								$scope.page.index = page;
								loadPage($scope.page.index, $scope.page.size, $scope.searchString);
							};
							
							function get(id) {
					 			var chapters = eval($scope.chapters);
					 			var chapter = {};
					 			for (var i = 0; i < chapters.length; i++ ) {
					 				if(chapters[i].id === id ) {
					 					chapter = chapters[i]
					 				}
					 			}
					 			return chapter;
					 		}
							
							vm.add = function() {
					 			var data = {};
					 			data.chapter = {};
					 			data.update = false;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/chapter/chapter-details.html',
					 					'chapterDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(data){
					 				if (data) {
					 					chapterRepository.getPageCountForSearchString($scope.page.size, $scope.searchString).then(function(result) {
											$scope.page.count = result.data.pages;
											$scope.page.index = result.data.pages;
											$scope.searchString = null;
											loadPage($scope.page.index, $scope.page.size, $scope.searchString);
								        });
					 				}
					 			});
					 		};
					 		
					 		vm.edit = function(chapter) {
					 			var data = {};
					 			data.chapter = chapter;
					 			data.update = true;
					 			var dlg = $dialogs.create(
					 					'resources/app/modals/chapter/chapter-details.html',
					 					'chapterDetailsController',
					 					data,
					 					{ key: false,
					 						back: 'static'
					 					}
					 			);
					 			
					 			dlg.result.then(function(chapter){
					 				console.log("edit done");
					 				var _chapter = get(chapter.id);
					 				angular.copy(chapter, _chapter);
					 			});
					 		};
					 		
					 		
					 		
					 		vm.delete = function(chapter) {
					 			var msg = "This will delete [" + chapter.chapterName + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					chapterRepository.delete(chapter.id)
						 					.success(function () {
												var _chapter = get(chapter.id);
												_chapter.deleted = true;
											})
											.error(function (response) {
												alertify.alert(response.error);
											});
					 				}
					 			})
					 		};
					 		
					 		vm.undelete = function(chapter) {
					 			var msg = "This will undelete [" + chapter.chapterName + "] from database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					chapterRepository.undelete(chapter.id)
								 			.success(function() {
								 				var _chapter = get(chapter.id);
												_chapter.deleted = false;
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

