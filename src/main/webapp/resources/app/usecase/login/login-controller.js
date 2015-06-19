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
		 	'angularjs',
		 	'user-authentication-service',
		], 
		function (app) {
			console.log("registering controller: loginController");
			app.register.controller(
					'loginController', 
					[
					 	'$rootScope', '$scope', '$location', 'userAuthenticationService', 'applicationSetting',
					 	function($rootScope, $scope, $location, userAuthenticationService, applicationSetting) {
					 		var vm = this;
					 		
					 		$scope.credentials = {
					 				username : null,
					 				password : null
					 			};
					 			
				 			vm.error = false;
				 			
				 			vm.login = function(credentials) {
				 				console.log($scope.credentials.username);
				 				console.log($scope.credentials.password);
				 				console.log(credentials);
				 				userAuthenticationService.authenticate($scope.credentials, function(authenticated) {
					 				if (authenticated) {
					 					console.log("login succeeded");
					 					$rootScope.authenticated = true;
					 					$location.path("/", true);
					 				} else {
					 					console.log("login failed");
					 					$rootScope.authenticated = false;
					 				}
				 				});
				 			};
				 			
				 			vm.reset = function() {
				 				$scope.credentials = {
				 						username : null,
				 						password : null
				 				};
				 				$scope.form.$setPristine();
				 				$scope.form.$setUntouched();
				 				//angular.element('#username').focus();
				 			};
						}
					]
			);
		}
);

//
//var loginController = function ($scope, $rootScope) {
//	
//	var vm = this;
//	
//	vm.credentials = {
//		username : "test",
//		password : "test"
//	};
//	
//	vm.error = false;
//	
//	vm.login = function(credentials) {
////		if (userAuthenticationService.authenticate(credentials)) {
////			$rootScope.authenticated = true;
////		} else {
////			$rootScope.authenticated = false;
////		}
//	};
//	
//	vm.reset = function() {
//		vm.credentials = {
//				username : null,
//				password : null
//		};
//		$scope.form.$setPristine();
//		$scope.form.$setUntouched();
//	};
//	
//}
