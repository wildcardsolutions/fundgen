/**
 * 
 */
"use strict";

/**
 * 
 */
var cardController = function ($scope, $rootScope, $http) {
	
	var vm = this;
	
	$scope.update = false;
	$scope.add = true;
	
	$scope.card = {};
    $scope.card.id = null;
	$scope.card.name = null;
	$scope.card.prefix = null;
	$scope.card.minimumAge = null;
	$scope.card.maximumAge = null;
	
	$scope.editCard = {};
    $scope.editCard.id = null;
	$scope.editCard.name = null;
	$scope.editCard.prefix = null;
	$scope.editCard.minimumAge = null;
	$scope.editCard.maximumAge = null;

	$http.get("/membershipCardType")
		.success(function (response) {
			$scope.cards = response;
		});
	
	function get(id) {
		var cards = eval($scope.cards);
		var card = {};
		for (var i = 0; i < cards.length; i++ ) {
			if(cards[i].id === id ) {
				card = cards[i]
			}
		}
		return card;
	}
	
	function reset() {
		$scope.update = false;
		$scope.add = true;
		
		$scope.card.id = null;
		$scope.card.name = null;
		$scope.card.prefix = null;
		$scope.card.minimumAge = null;
		$scope.card.maximumAge = null;
		
		angular.copy($scope.card, $scope.editCard)
		
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	}
	
	vm.reset = function() {
		angular.copy($scope.card, $scope.editCard)
		$scope.form.$setPristine();
		$scope.form.$setUntouched();
	};
	
	vm.cancel = function() {
		reset();
	};
	
	vm.add = function(card) {
		var msg = "This will add new card information to database. Proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				$http.post("/membershipCardType", card)
					.success(function (response) {
						$scope.cards.push(response);
						$scope.$apply();
						reset();
					})
					.fail(function (response) {
						alertity.alert(response);
					});
			}
		});
	};
	
	vm.edit = function(card) {
		$scope.update = true;
		$scope.add = false;
		angular.copy(card, $scope.card)
		angular.copy(card, $scope.editCard)
	};
	
	vm.update = function(card) {
    	var msg = "This will update card information to database. Proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				$http.put("/membershipCardType/" + card.id, card)
					.success(function (response) {
						var _card = get(card.id);
						angular.copy(response, _card)
						reset();
					})
					.error(function (response) {
						alertify.alert(response);
					});
			}
		});
	};
	
	vm.delete = function(card) {
		var msg = "This will delete " + card.name + " card type from database. Proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				$http.delete("/membershipCardType/" + card.id)
					.success(function () {
						var _card = get(card.id);
						_card.deleted = true;
					})
					.error(function (response) {
						alertify.alert(response);
					});
			}
		})
	};
	
	vm.undelete = function(card) {
		var msg = "This will undelete " + card.name + " card type from database. Proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				$http.post("/membershipCardType/undelete/" + card.id)
					.success(function () {
						var _card = get(card.id);
						_card.deleted = false;
					})
					.error(function (response) {
						alertify.alert(response);
					});
			}
		})
	};
}
