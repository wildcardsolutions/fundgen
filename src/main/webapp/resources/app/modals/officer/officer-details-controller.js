/**
 * @controller	:	chapterModalController
 * @author		:	apagador
 * @date		:	2015/05/24
 */
"use strict";

define(
		['app', 'officer-repository', 'chapter-repository', 'roles-repository'], 
		function (app) {
			console.log("registering controller: officerDetailsController");
			app.register.controller(
					'officerDetailsController', 
					[ 
						'$scope', '$http', '$modalInstance', 'officerRepository', 'chapterRepository', 'rolesRepository', 'data',
						function ($scope, $http, $modalInstance, officerRepository, chapterRepository, rolesRepository, data) {
							
							$scope.data = angular.copy(data);
							$scope.officer = angular.copy(data.officer);
							$scope.addedOfficer = false;
							
							init($scope.officer);
							
							function init(officer) {
								chapterRepository.getAll()
									.success(function (response) {
										$scope.chapters = response;
										$scope.defaultChapter = response[0].id;
										if (!officer.chapterId) {
											$scope.data.officer.chapterId = response[0].id;
											$scope.officer.chapterId = response[0].id;
										}
										
									});
								rolesRepository.getAll()
									.success(function (response) {
										$scope.roles = response;
										$scope.defaultRole = response[0].roleId;
										if (!officer.roleId) {
											$scope.data.officer.roleId = response[0].roleId;
											$scope.officer.roleId = response[0].roleId;
										}
									});
							}
							
							$scope.reset = function() {
								$scope.officer = angular.copy($scope.data.officer)			
					 			$scope.form.$setPristine();
					 			$scope.form.$setUntouched();
							}; 
							
							$scope.close = function() {
								if ($scope.data.update) {
									$modalInstance.dismiss();  
								} else {
									$modalInstance.close($scope.addedOfficer);  
								}
							}
							
							$scope.save = function(){
								if ($scope.data.update) {
									updateOfficer();
								} else {
									addOfficer();
								}
							}; 
							
							function updateOfficer() {
								var msg = "This will update officer information on the database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					officerRepository.update($scope.officer)
											.success(function (response) {
												$modalInstance.close(response);
											})
											.error(function (response) {
												alertify.alert(response.description);
											});
					 				}
					 			});
							}
							
							function addOfficer() {
					 			var msg = "This will add new officer information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					officerRepository.insert($scope.officer)
											.success(function (response) {
												alertify.alert("Officer information saved to database.", function () {
													$scope.reset();
													$scope.$apply();
													$scope.addedOfficer = true;
												});
											})
											.error(function (response) {
												alertify.alert(response.description);
											});
					 				}
					 			});
							}
						}
					]
			);
		}
	);
