/**
 * 
 */
"use strict";

/**
 * 
 */
var loginController = function ($scope, $rootScope) {
	
	var vm = this;
	
	vm.credentials = {
		username : "test",
		password : "test"
	};
	
	vm.error = false;
	
	vm.login = function(credentials) {
//		if (userAuthenticationService.authenticate(credentials)) {
//			$rootScope.authenticated = true;
//		} else {
//			$rootScope.authenticated = false;
//		}
	};
	
	vm.reset = function() {
		vm.credentials = {
				username : null,
				password : null
		};
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	};
	
}
