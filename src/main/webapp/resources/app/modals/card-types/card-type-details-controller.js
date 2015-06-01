/**
 * @controller	:	cardTypeDetailsController
 * @author		:	apagador
 * @date		:	2015/05/09
 */
"use strict";

define(
		['app', 'membership-card-type-repository'], 
		function (app) {
			console.log("registering controller: cardTypeDetailsController");
			app.register.controller(
					'cardTypeDetailsController', 
					[ 
						'$scope', 
					 	'$modalInstance',
					 	'membershipCardTypeRepository',
					 	'applicationSetting',
					 	'data',
					 	function($scope, $modalInstance, membershipCardTypeRepository, applicationSetting, data) {
					 		
					 		var vm = this;
					 		$scope.data = angular.copy(data);
					 		$scope.cardType = angular.copy(data.cardType);
					 		$scope.added = false;
					 	
					 		$scope.reset = function() {
					 			$scope.cardType = angular.copy(data.cardType);		
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
								var msg = "This will update membership card type information to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					membershipCardTypeRepository.update($scope.cardType)
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
					 			var msg = "This will add new membership card type to database. Proceed?";
					 			alertify.confirm(msg, function (e) {
					 				if (e) {
					 					membershipCardTypeRepository.insert($scope.cardType)
											.success(function (response) {
												alertify.alert("Card type information saved to database.", function () {
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