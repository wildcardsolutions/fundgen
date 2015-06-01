/**
 * 
 */
"use strict";

/**
 * 
 */
var registerMembershipCardsController = function ($scope, $rootScope, $http) {
	
	var vm = this;
	
	vm.error = false;
	
	$http.get("/membershipCardType")
		.success(function (response) {
			$scope.cardTypes = response;
			$scope.card.cardType = response[0].id;
			$scope.card.cardTypeDefault = response[0].id;
			$scope.prefix = response[0].prefix;
		}
	);
	
	$scope.update = function() {
		var cardType = $scope.card.cardType;
		var cards = eval($scope.cardTypes);
		var card = {};
		for (var i = 0; i < cards.length; i++ ) {
			if(cards[i].id === cardType ) {
				$scope.prefix = cards[i].prefix;
				break;
			}
		}
	}
	
	/**
	 * 
	 */
	vm.register = function(card) {
		if (card.seriesStart > card.seriesEnd) {
			alertify.alert("Please specify valid values.");
			return;
		}
		alertify.confirm("This will register membership cards using the series specified. Proceed?", function(e) {
			if (e) {
				$http
					.post("/card-inventory/register", card)
					.success(function(data, status, headers, config) {
						alertify.alert("Successfully registered membership card inventory.");
						vm.reset();
					})
					.error(function(data, status, headers, config) {
						alertify.alert("Failed: " + data.description);
						vm.reset();
					});
			}
		})
	};
	
	vm.reset = function() {
		var tmp = $scope.card.cardTypeDefault;
		$scope.card.cardType = tmp
		$scope.card.seriesStart = null;
		$scope.card.seriesEnd = null;
		
		$scope.card.form.$setPristine();
		$scope.card.form.$setUntouched();
	}
	
};