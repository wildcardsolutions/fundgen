/**
 * @controller	:	settingDetailsController
 * @author		:	apagador
 * @date		:	2015/05/24
 */
"use strict";

define(
		['app', 'settings-repository'], 
		function (app) {
			console.log("registering controller: settingDetailsController");
			app.register.controller(
					'settingDetailsController', 
					[ 
						'$scope', '$http', '$modalInstance', 'settingsRepository', 'data',
						function ($scope, $http, $modalInstance, settingsRepository, data) {
							
							$scope.data = angular.copy(data);
							$scope.settings = angular.copy(data.settings);
							$scope.added = false;
							
							$scope.reset = function() {
								$scope.settings = angular.copy($scope.data.settings)					 			
					 			$scope.form.$setPristine();
					 			$scope.form.$setUntouched();
							}; 
							
							$scope.close = function(){
								if ($scope.data.update) {
									$modalInstance.dismiss();  
								} else {
									$modalInstance.close($scope.added);  
								}
							}
							
							$scope.save = function(){
								if ($scope.data.update) {
									update();
								} else {
									insert();
								}
							}; 
							
							function update() {
								var msg = "This will update system setting to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					settingsRepository.update($scope.settings)
											.success(function (response) {
												$modalInstance.close(response);
											})
											.error(function (response) {
												alertify.alert(response);
											});
					 				}
					 			});
							}
							
							function insert() {
					 			var msg = "This will add new system setting to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					settingsRepository.insert($scope.settings)
											.success(function (response) {
												alertify.alert("System setting saved to database.", function () {
													$scope.reset();
													$scope.$apply();
													$scope.added = true;
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
