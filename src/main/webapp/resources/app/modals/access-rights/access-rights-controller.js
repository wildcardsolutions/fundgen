/**
 * @controller	:	accessRightsController
 * @author		:	apagador
 * @date		:	2015/05/09
 */
"use strict";

define(
		['app'], 
		function (app) {
			console.log("registering controller: accessRightsController");
			app.register.controller(
					'accessRightsController', 
					[ 
						'$scope', '$http', '$modalInstance', 'data',
						function ($scope, $http, $modalInstance, data) {
							
							$scope.data = angular.copy(data);

							$scope.access = {};
							$scope.access.resources = {};
							$scope.access.role = angular.copy(data.role);
							
							$http.get("/application-resources/" + data.role.roleId)
								.success(function (response) {
									$scope.access.resources = response;
								}
							);
							
							$scope.close = function(){
								$modalInstance.dismiss('closed');  
							}
							
							$scope.cancel = function(){
							    $modalInstance.dismiss('canceled');  
							}; // end cancel
							  
							$scope.save = function(){
								
//								for (var i in $scope.access.resources) {
//									alert($scope.access.resources[i]);
//								}
								
								var msg = "This will update access rights for " + $scope.data.role.roleName  + ". Proceed?";
								alertify.confirm(msg, function (e) {
									if (e) {
										var form= {};
										form.resources = {};
										for (var key in $scope.access.resources) {
											for (var resource in $scope.access.resources[key]) {
												form.resources[resource] = $scope.access.resources[key][resource];
												//resources.push({ resource : $scope.access.resources[key][resource]})
												//alert(resource + "=" +$scope.access.resources[key][resource]);
											}
										}
										
										$http.post("/application-resources/" + data.role.roleId, form)
											.success(function (response) {
												alertify.alert("Successfully updated access rights for " + data.role.roleName, function() {
													$modalInstance.close($scope.data);
												});
											})
											.error(function(data, status, headers, config) {
												alertify.alert("Failed: " + data.description);
											});
									}
								});
								
								
							}; // end save
							  
//							$scope.hitEnter = function(evt){
//							    if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.name,null) || angular.equals($scope.name,''))) {
//							    	$scope.save();
//							    }			
//							}; 
						}
					]
			);
		}
	);
