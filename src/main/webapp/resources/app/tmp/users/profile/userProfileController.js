/**
 * 
 */
"use strict";

/**
 * 
 */
var userProfileController = function ($scope, $rootScope, officerRepository) {
	
	var vm = this;
	
	vm.error = false;
	
	vm.user = {
			firstname : "",
			middlename : "",
			lastname : "",
			designation : "",
			chapter : {
				chapterId: 1,
			    chapterName: "NHQ",
			    chapterLocation: "Metro Manila"
			}
	};

	vm.save = function(user) {
		officerRepository.insertOfficer(user);
	};
	
	vm.reset = function() {
		reset();
	};
	
	
	function reset() {
		vm.user = {
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
