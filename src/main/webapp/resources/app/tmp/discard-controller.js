/**
 * 
 */
"use strict";

/**
 * 
 */
var discardMembershipCardsController = function ($scope, $rootScope, $http) {
	
	var vm = this;
	
	vm.error = false;
	
	$http.get("/membershipCardType")
		.success(function (response) {
			$scope.cardTypes = response;
			$scope.card.cardType = response[0].id;
			$scope.card.cardTypeDefault = response[0].id;
			$scope.card.discardType = 1;
			$scope.prefix = response[0].prefix;
		}
	);
	
	/**
	 * 
	 */
	vm.discard = function(card) {
		console.log(card.seriesStart + ", " + card.seriesEnd);
		if (card.seriesStart > card.seriesEnd) {
			alertify.alert("Please specify valid values.");
			return;
		}
		alertify.confirm("This will discard membership card(s) specified. Proceed?", function(e) {
			if (e) {
				$http
					.post("/card-inventory/discard", card)
					.success(function(data, status, headers, config) {
						alertify.alert("Successfully discarded membership cards from inventory.");
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
		var cardType = $scope.card.cardTypeDefault;
		$scope.card.cardType = cardType
		$scope.card.seriesStart = null;
		$scope.card.seriesEnd = null;
		$scope.card.reason = null;
		$scope.card.cardNumber = null;
		
		$scope.update();
		
		$scope.card.form.$setPristine();
		$scope.card.form.$setUntouched();
	}
	
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
};