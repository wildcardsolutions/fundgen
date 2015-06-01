/**
 * @controller	:	chapterModalController
 * @author		:	apagador
 * @date		:	2015/05/24
 */
"use strict";

define(
		['app', 'chapter-repository'], 
		function (app) {
			console.log("registering controller: chapterDetailsController");
			app.register.controller(
					'chapterDetailsController', 
					[ 
						'$scope', '$http', '$modalInstance', 'chapterRepository', 'data',
						function ($scope, $http, $modalInstance, chapterRepository, data) {
							
							$scope.data = angular.copy(data);
							$scope.chapter = angular.copy(data.chapter);
							$scope.addedChapter = false;
							
							$scope.reset = function() {
								$scope.chapter = angular.copy($scope.data.chapter)					 			
					 			$scope.form.$setPristine();
					 			$scope.form.$setUntouched();
							}; 
							
							$scope.close = function(){
								if ($scope.data.update) {
									$modalInstance.dismiss();  
								} else {
									$modalInstance.close($scope.addedChapter);  
								}
							}
							
							$scope.save = function(){
								if ($scope.data.update) {
									updateChapter();
								} else {
									addChapter();
								}
							}; 
							
							function updateChapter() {
								var msg = "This will update chapter information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					chapterRepository.update($scope.chapter)
											.success(function (response) {
												$modalInstance.close(response);
											})
											.error(function (response) {
												alertify.alert(response);
											});
					 				}
					 			});
							}
							
							function addChapter() {
					 			var msg = "This will add new chapter information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					chapterRepository.insert($scope.chapter)
											.success(function (response) {
												alertify.alert("Chapter information saved to database.", function () {
													$scope.reset();
													$scope.$apply();
													$scope.addedChapter = true;
												});
											})
											.error(function (response) {
												alertify.alert(response.error);
											});
					 				}
					 			});
							}
						}
					]
			);
		}
	);
