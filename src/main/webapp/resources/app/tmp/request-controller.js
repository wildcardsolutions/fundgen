/**
 * 
 */
"use strict";

/**
 * 
 */
var requestMembershipCardsController = function ($scope, $rootScope, $http) {

	var vm = this;
	
	$scope.request = {};
	$scope.requestForm = {};
	$scope.requestForm.cards = [];
	
	$http.get("/membershipCardType")
		.success(function (response) {
			$scope.cardTypes = response;
			$scope.request.cardType = response[0].id;
			$scope.defaultCardType = response[0].id;
			$scope.request.cardName = response[0].name;
			$scope.prefix = response[0].prefix;
		}
	);
	
	$scope.update = function() {
		var cardType = $scope.request.cardType;
		var cards = eval($scope.cardTypes);
		var card = {};
		for (var i = 0; i < cards.length; i++ ) {
			if(cards[i].id === cardType ) {
				$scope.prefix = cards[i].prefix;
				$scope.request.cardName = cards[i].name;
				break;
			}
		}
	}
	
	/**
	 * 
	 */
	vm.reset = function() {
		$scope.request.cardType = $scope.defaultCardType
		$scope.request.numberOfCards = null;

		$scope.update();
		
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	}
	
	
	vm.request = function(request) {
		var card = get(request.cardType);
		if (card.cardType) {
			alertify.alert("A request for the selected card type already made.");
			return;
		}
		
		var tmp = {};
		angular.copy(request, tmp)
		$scope.requestForm.cards.push(tmp);
		vm.reset();
	}
	
	vm.cancel = function() {
		var msg = "This will cancel request for membership cards. Proceed?";
		alertify.confirm(msg, function(e) {
			if (e) {
				$scope.requestForm = {}
				$scope.requestForm.cards = [];
				vm.reset();
				$scope.$apply();
			}
		})
	};
	
	
	vm.delete = function(request) {
		for (var i = 0; i < $scope.requestForm.cards.length; i++ ) {
			if($scope.requestForm.cards[i].cardType === request.cardType ) {
				$scope.requestForm.cards.splice(i, 1);     
				break;
			}
		}
	}
	
	vm.submit = function(requestForm) {
		var msg = "This will submit request for membership cards for approval. Proceed?";
		alertify.confirm(msg, function(e) {
			if (e) {
				alertify.alert("Successfully submitted request for approval by chapter head.");
			}
		})
	};
	
	function get(cardType) {
		var requestedCards = eval($scope.requestForm.cards);
		var card = {};
		for (var i = 0; i < requestedCards.length; i++ ) {
			if(requestedCards[i].cardType === cardType ) {
				card = requestedCards[i]
				break;
			}
		}
		return card;
	}
};