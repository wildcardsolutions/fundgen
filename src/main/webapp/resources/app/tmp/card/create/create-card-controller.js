/**
 * 
 */
"use strict";

/**
 * 
 */
var createCardController = function ($scope, $rootScope, $http) {
	
	var vm = this;
	
	vm.error = false;

	vm.cardTypes = $http.get("/membershipCardType");
	
	vm.card = {
			cardType : "CLASSIC",
			seriesStart: "",
			seriesEnd: ""
	};
	
	vm.create = function(card) {
		alertify.confirm("This will register membership card using the series specified. Proceed?", function(e) {
			if (e) {
				$http
					.post("/membershipCard/create", card)
					.success(function(data, status, headers, config) {
						alert("success");
					})
					.error(function(data, status, headers, config) {
						alert(data.status);
						alert(data.description);
					});
			}
		})
	};
	
	vm.reset = function() {
		vm.card = {
				cardType : "CLASSIC",
				seriesStart: "",
				seriesEnd: ""
		};
		
		$scope.card.form.cardType.$pristine = true;
		$scope.card.form.seriesStart.$pristine = true;
		$scope.card.form.seriesEnd.$pristine = true;
		
		$scope.card.form.cardType.$dirty = false;
		$scope.card.form.seriesStart.$dirty = false;
		$scope.card.form.seriesEnd.$dirty = false;

		$scope.card.form.seriesStart.$error.number = false
		$scope.card.form.seriesEnd.$error.number = false
		
		$scope.card.form.seriesStart.$error.max = false
		$scope.card.form.seriesEnd.$error.max = false
		
		$scope.card.form.error = false;
	};
	
}
