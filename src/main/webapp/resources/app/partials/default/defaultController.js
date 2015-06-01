var defaultController = function ($scope, $rootScope) {
	
	/**
	 * 
	 */
	var vm = this;
	
	/**
	 * 
	 */
	vm.credentials = {
		username : "test",
		password : "test"
	};
	
	/**
	 * 
	 */
	vm.login = function(credentials) {
		userAuthenticationService.login(credentials);
	};
	
	
	/**
	 * 
	 */
	vm.reset = function() {
		
		vm.credentials = {
				username : "",
				password : ""
		};
		
		$scope.login.form.username.$pristine = true;
		$scope.login.form.password.$pristine = true;
		$scope.login.form.username.$dirty = false;
		$scope.login.form.password.$dirty = false;
	};
	
}