/**
 * 
 */
"use strict";

/**
 * 
 */
var membershipController = function ($scope, $rootScope, $http) {
	
	var vm = this;
	
	vm.error = false;
	
	vm.serial = 0;
	vm.cardType = "PC"
	vm.cardTypes = $http.get("/membershipCard/types");
	
	$http.get("/declarationPeriod")
		.success(function (response) {
			$scope.declarationPeriods = response;
		}
	);
		
		
	vm.member = {
			declarationPeriod : "CURRENT",
			membershipType : "CLASSIC"
	};
	
	vm.save = function(member) {
		
	};
	
	vm.reset = function() {
		reset();
	};
	
	$scope.updateDeclarationPeriod = function() {
		alert("hehehe");
	};
	
	function reset() {
		vm.member = {
				firstname : "",
				lastname : ""
		};
		
		$scope.user.form.firstname.$pristine = true;
		$scope.user.form.middlename.$pristine = true;
		$scope.user.form.lastname.$pristine = true;
		$scope.user.form.designation.$pristine = true;
		$scope.user.form.username.$pristine = true;
		$scope.user.form.password.$pristine = true;
		
		$scope.user.form.firstname.$dirty = false;
		$scope.user.form.middlename.$dirty = false;
		$scope.user.form.lastname.$dirty = false;
		$scope.user.form.designation.$dirty = false;
		$scope.user.form.username.$dirty = false;
		$scope.user.form.password.$dirty = false;
		
		$scope.user.form.error = false;
	}
}
