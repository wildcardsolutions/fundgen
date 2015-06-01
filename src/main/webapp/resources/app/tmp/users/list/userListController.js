
/**
 * 
 */
var userListController = function ($scope, $rootScope, officerRepository) {
	
	var vm = this;
	
	vm.userList = officerRepository.getOfficers();
	
	
	
	
	
};

	