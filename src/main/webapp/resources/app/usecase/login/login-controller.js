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
		 	'angular-cookies', 
		 	'user-authentication-service',
		], 
		function (app) {
			console.log("registering controller: loginController");
			app.register.controller(
					'loginController', 
					[
					 	'$rootScope', '$scope', '$location', '$cookieStore', 'userAuthenticationService', 'applicationSetting',
					 	function($rootScope, $scope, $location, $cookieStore, userAuthenticationService, applicationSetting) {
					 		var vm = this;
					 		
					 		$scope.credentials = {
					 				username : null,
					 				password : null
					 			};
					 			
				 			vm.error = false;
				 			
				 			vm.login = function(credentials) {
				 				userAuthenticationService.authenticate($scope.credentials, function(authenticated, data) {
					 				if (authenticated) {
					 					console.log("login succeeded");
					 					console.log(data);
					 					$cookieStore.put("authenticated", "true");
					 					$rootScope.authenticated = true;
					 					$location.path("/", true);
					 				} else {
					 					alertify.alert("Login failed.", function() {
					 						vm.reset();
					 						$scope.$apply();
					 					});
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
				 				angular.element('#username').focus();
				 			};

						}
					]
			);
		}
);
