/**
 * 
 */
"use strict";

/**
 * 
 */
var rolesController = function ($scope, $rootScope, $http, $dialogs) {
	
	var vm = this;

	$scope.add = true;
	
	$scope.role = {};
    $scope.role.roleId = "";
	$scope.role.roleName = "";
	$scope.role.roleDescription = "";
	
	$scope.editRole = {};
    $scope.editRole.roleId = "";
	$scope.editRole.roleName = "";
	$scope.editRole.roleDescription = "";
	
	
	$http.get("/roles")
		.success(function (response) {
			$scope.roles = response;
		}
	);
	
	vm.add = function(role) {
		var msg = "This will add new role to database. Proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				$http.post("/roles", role)
					.success(function (response) {
						$scope.roles.push(response);
						reset();
					})
					.error(function(data, status, headers, config) {
						alertify.alert("Failed: " + data.description);
					});
			}
		});
	};
	
	vm.edit = function(role) {
		$scope.update = true;
		$scope.add = false;
		angular.copy(role, $scope.role)
		angular.copy(role, $scope.editRole)
	};
	
	 vm.update = function(role) {
		 var msg = "This will update role information to database. Proceed?";
			alertify.confirm(msg, function (e) {
				if (e) {
					$http.put("/roles", role)
						.success(function (response) {
							var _tmp = get(role.roleId);
							angular.copy(response, _tmp)
							reset();
						})
						.error(function (response) {
							alertify.alert(response);
						});
				}
			});
		};
	
	vm.delete = function(role) {
//		var msg = "This will delete [" + role.roleName + "] from database. Proceed?";
//		alertify.confirm(msg, function (e) {
//			if (e) {
//				$http.delete("/roles/" + role.roleId)
//					.success(function () {
//						var _role = get(role.roleId);
//						_role.deleted = true;
//					})
//					.error(function(data, status, headers, config) {
//						alertify.alert("Failed: " + data.description);
//					});
//			}
//		})
		
		var dlg = $dialogs.confirm(
				'Please Confirm',
				"This will delete [" + role.roleName + "] from database. Proceed?");
		
        dlg.result.then(function(btn){
			if (e) {
				$http.delete("/roles/" + role.roleId)
					.success(function () {
						var _role = get(role.roleId);
						_role.deleted = true;
					})
					.error(function(data, status, headers, config) {
						alertify.alert("Failed: " + data.description);
					});
			}
        });
	};
	
	vm.cancel = function() {
		$scope.update = false;
		$scope.add = true;
		
		$scope.role.roleId = null;
		$scope.role.roleName = null;
		$scope.role.roleDescription = null;
		
		angular.copy($scope.role, $scope.editRole)
		
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	};
	
	vm.reset = function() {
		angular.copy($scope.role, $scope.editRole)
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	};
	
	vm.access = function(role) {
		//var dlg = $dialogs.notify('Something Happened','Something happened that I need to tell you.');
		//var dlg = $dialogs.error('Something Happened');
		var data = {};
		data.role = angular.copy(role);
		data.access = {};
		var dlg = $dialogs.create(
				'resources/app/usecase/maintenance/roles/access/access-rights.html',
				'accessRightsController',
				data,
				{ key: false,
					back: 'static'
				}
		);
		
		dlg.result.then(function(data){
			
		});
//        dlg.result.then(function(data){
//        	alert(data.role.roleName);
//        },function(){
//        	alert("hohoho");
//        });
	};
	
	function get(id) {
		var items = eval($scope.roles);
		var item = {};
		for (var i = 0; i < items.length; i++ ) {
			if(items[i].roleId === id ) {
				item = items[i]
			}
		}
		return item;
	}
	
	function reset() {
		$scope.update = false;
		$scope.add = true;
		
		$scope.role.roleId = null;
		$scope.role.roleName = null;
		$scope.role.roleDescription = null;
		
		angular.copy($scope.role, $scope.editRole)
		
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	}
}
