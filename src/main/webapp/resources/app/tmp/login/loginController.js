/**
 * 
 */
"use strict";

/**
 * 
 */
var loginController = function ($scope, $rootScope, userAuthenticationService) {
	
	var vm = this;
	
	vm.credentials = {
		username : "test",
		password : "test"
	};
	
	vm.error = false;
	
	vm.login = function(credentials) {
		if (userAuthenticationService.authenticate(credentials)) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	};
	
	vm.reset = function() {
		vm.credentials = {
				username : "",
				password : ""
		};
		$scope.login.form.username.$pristine = true;
		$scope.login.form.password.$pristine = true;
		$scope.login.form.username.$dirty = false;
		$scope.login.form.password.$dirty = false;
		$scope.login.form.error = false;
	};
	
}
