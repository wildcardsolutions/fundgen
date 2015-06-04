/**
 * 
 */
"use strict";

define(
		[
		 	'app',
		 	'membership-card-type-repository',
		 	'card-inventory-service',
		 	'paging-directive'
		], 
		function (app) {
			console.log("registering controller: requestMembershipCardsController");
			app.register.controller(
					'requestMembershipCardsController', 
					[
						'$scope', 
						'cardInventoryService',
						'membershipCardTypeRepository', 
						'applicationSetting',
						function($scope, cardInventoryService, membershipCardTypeRepository, applicationSetting) {
							
							var vm = this;
							
							$scope.request = {};
							$scope.requestForm = {};
							$scope.requestForm.cards = [];
							
							initializePage();
							
							function initializePage() {
								membershipCardTypeRepository.getAll()
									.success(function (response) {
										$scope.cardTypes = response;
										$scope.request.cardType = response[0].id;
										$scope.defaultCardType = response[0].id;
										$scope.request.cardName = response[0].name;
										$scope.prefix = response[0].prefix;
									})
									.error(function (response) {
						 				alertify.alert(response.error);
						            });
							}
							
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
							
							vm.reset = function() {
								$scope.request.cardType = $scope.defaultCardType
								$scope.request.numberOfCards = null;
					
								$scope.update();
								
								$scope.form.$setPristine();
								$scope.form.$setUntouched();
							}
							
							vm.delete = function(request) {
								var msg = "This will remove [" + request.cardName + "] from the request list. Proceed?"
								alertify.confirm(msg, function(e) {
									if (e) {
										for (var i = 0; i < $scope.requestForm.cards.length; i++ ) {
							 				if($scope.requestForm.cards[i].cardType === request.cardType ) {
							 					$scope.requestForm.cards.splice(i, 1);    
							 					$scope.$apply();
							 					break;
							 				}
							 			}
									}
								});
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
							
							vm.submit = function(requestForm) {
								var msg = "This will submit request for membership cards for approval. Proceed?";
								alertify.confirm(msg, function(e) {
									if (e) {
											var form = {};
											form.cards = {};
											for (var i =0;i<$scope.requestForm.cards.length; i++) {
												//var item = {};
												//item[$scope.requestForm.cards[i].cardName] = $scope.requestForm.cards[i].numberOfCards;
												console.log($scope.requestForm.cards[i].cardName);
												form.cards[$scope.requestForm.cards[i].cardName] = $scope.requestForm.cards[i].numberOfCards;
												
											}
											console.log(form);
											cardInventoryService.request(form)
												.success(function(response) {
													alertify.alert("Request for membership cards successfully submitted.");
													
													$scope.requestForm = {}
								 					$scope.requestForm.cards = [];
													vm.reset();
												})
												.error(function(data, status, headers, config) {
													alertify.alert("Failed: " + data.description);
													vm.reset();
												});
						 					
										
									}
								})
							};
							
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
						}
					]
			);
		}
);
