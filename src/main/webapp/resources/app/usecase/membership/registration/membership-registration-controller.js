/**
 * 
 */
"use strict";

/**
 * 
 */
define(
		[
		 	'app',
		 	'membership-information-repository',
		 	'membership-card-type-repository',
		 	'officer-repository',
		 	'declaration-period-service',
			'panel-directive',
		 	'paging-directive',
		 	'action-directive',
		 	'dropdown-directive'
		], 
		function (app) {
			console.log("registering controller: membershipRegistrationController");
			app.register.controller(
					'membershipRegistrationController', 
					[
					 	'$scope', 
					 	'membershipCardTypeRepository', 
					 	'membershipInformationRepository',
					 	'officerRepository',
					 	'declarationPeriodService',
					 	'applicationSetting',
					 	function($scope, membershipCardTypeRepository, membershipInformationRepository, officerRepository, declarationPeriodService, applicationSetting) {
					 		var vm = this;
					 		$scope.page = {};
					 
					 		$scope.member = {};
					 		$scope.effectivityDate = {};
					 		
					 		loadPage();

							function loadPage() {
								declarationPeriodService.getDeclarationPeriods()
									.success(function (response) {
										$scope.declarationPeriods = response;
										$scope.member.declarationPeriod = response[0].id;
										$scope.defaultDeclarationPeriod = response[0].id;
										
										$scope.effectivityDate.minDate = response[0].dateStart;
										$scope.effectivityDate.maxDate = response[0].dateEnd;
										$scope.member.effectivityDate = new Date(response[0].dateStart);
									});
								
								membershipCardTypeRepository.getAll()
									.success(function (response) {
										$scope.cardTypes = response;
										$scope.member.cardType = response[0].id;
										$scope.defaultCardType = response[0].id;
										$scope.prefix = response[0].prefix;
									});
							}
							
							function getDeclarationPeriod(id) {
								var periods = eval($scope.declarationPeriods);
								var period = {};
								for (var i = 0; i < periods.length; i++ ) {
									if(periods[i].id === id ) {
										period = periods[i]
										break;
									}
								}
								return period;
							}
							
							$scope.updateDeclarationPeriod = function(declarationPeriod) {
								console.log("updateDeclarationPeriod(): " + declarationPeriod);
								var period = getDeclarationPeriod(declarationPeriod);
								console.log("updateDeclarationPeriod(): dateStart=" + period.dateStart);
								console.log("updateDeclarationPeriod(): dateEnd=" + period.dateEnd);
								$scope.effectivityDate.minDate = period.dateStart;
								$scope.effectivityDate.maxDate = period.dateEnd;
								$scope.member.effectivityDate = new Date(period.dateStart);
							};
							
							$scope.updatePrefix = function() {
								var cardType = $scope.member.cardType;
								console.log("updatePrefix() cardType=" + cardType);
								var cards = eval($scope.cardTypes);
								var card = {};
								for (var i = 0; i < cards.length; i++ ) {
									if(cards[i].id === cardType ) {
										$scope.prefix = cards[i].prefix;
										break;
									}
								}
							}
							
							$scope.getMatchingFirstNames = function (filter) {
								 return membershipInformationRepository.autoCompleteFirstname(filter)
								 		.then(function(response){
								 			return response.data;
								 		});
							};
							
							$scope.getMatchingLastNames = function (filter) {
								 return membershipInformationRepository.autoCompleteLastname(filter)
								 		.then(function(response){
								 			return response.data;
								 		});
							};
							
							$scope.getMatchingMiddleNames = function (filter) {
								 return membershipInformationRepository.autoCompleteMiddlename(filter)
								 		.then(function(response){
								 			return response.data;
								 		});
							};
							
							$scope.getMatchingAddresses = function (filter) {
								 return membershipInformationRepository.autoCompleteAddress(filter)
								 		.then(function(response){
								 			return response.data;
								 		});
							};
					 		
						}
					]
			);
		}
);


//var membershipRegistrationController = function ($scope, $rootScope, $http) {
//	
//	var vm = this;
//	
//	$scope.member = {};
//	$scope.member = {};
//	
//	$scope.birthDate = {};
//	$scope.effectivityDate = {};
//	
//	$scope.dateOptions = {
//			formatYear: 'yy',
//			startingDay: 1,
//			showButtonBar : false,
//			showWeeks : false
//	};
//	 
//	$scope.format = "yyyy-MM-dd";
//		  
//	$scope.openEffectivityDate = function($event) {
//		$event.preventDefault();
//		$event.stopPropagation();
//		$scope.effectivityDate.opened = true;
//	};
//	
//	$scope.openDateOfBirth = function($event) {
//		$event.preventDefault();
//		$event.stopPropagation();
//		$scope.birthDate.opened = true;
//	};
//	
//	
//	$scope.updatePrefix = function() {
//		var cardType = $scope.member.cardType;
//		console.log("updatePrefix() cardType=" + cardType);
//		var cards = eval($scope.cardTypes);
//		var card = {};
//		for (var i = 0; i < cards.length; i++ ) {
//			if(cards[i].id === cardType ) {
//				$scope.prefix = cards[i].prefix;
//				break;
//			}
//		}
//	}
//	
//	
//	
//	vm.computeAge = function(dateOfBirth) {
//		console.log("computeAge() dateOfBirth=" + dateOfBirth);
//		if (dateOfBirth.$valid) {
//			 $scope.member.age =  calculateAge($scope.member.dateOfBirth)
//			 
//		}
//	}
//	
//	
//	
//	
//	$scope.getMatchingNames = function (filter, nameType) {
//		 return $http.get("/members/autocomplete/" + nameType + "/" + filter)
//		 		.then(function(response){
//		 			return response.data;
//		 		});
//	};
//	
//	$http.get("/declarationPeriod")
//		.success(function (response) {
//			$scope.declarationPeriods = response;
//			$scope.member.declarationPeriod = response[0].id;
//			$scope.defaultDeclarationPeriod = response[0].id;
//			
//			$scope.effectivityDate.minDate = response[0].dateStart;
//			$scope.effectivityDate.maxDate = response[0].dateEnd;
//			$scope.member.effectivityDate = new Date(response[0].dateStart);
//		}
//	);
//	
//	$http.get("/membershipCardType")
//		.success(function (response) {
//			$scope.cardTypes = response;
//			$scope.member.cardType = response[0].id;
//			$scope.defaultCardType = response[0].id;
//			$scope.prefix = response[0].prefix;
//		}
//	);
//
//	vm.save = function(member) {
//		console.log("save()");
//		$http.post("/members/register", member)
//		.success(function(data, status, headers, config) {
//						alertify.alert("Successfully allocated membership cards to chapter.");
//						//vm.reset();
//					})
//					.error(function(data, status, headers, config) {
//						alertify.alert("Failed: " + data.description);
//						//vm.reset();
//					});
//
//	};
//	
//	vm.reset = function() {
//		console.log("reset()");
//		$scope.member = {};
//		$scope.member = {};
//		
//		$scope.member.declarationPeriod = $scope.defaultDeclarationPeriod;
//		$scope.member.cardType = $scope.defaultCardType;
//		
//		$scope.member.cardNumber = null;
//		
//		$scope.form.$setPristine();
//		$scope.form.$setUntouched();
//	};
//	
//	vm.updateDeclarationPeriod = function(declarationPeriod) {
//		console.log("updateDeclarationPeriod(): " + declarationPeriod);
//		var period = getDeclarationPeriod(declarationPeriod);
//		console.log("updateDeclarationPeriod(): dateStart=" + period.dateStart);
//		console.log("updateDeclarationPeriod(): dateEnd=" + period.dateEnd);
//		$scope.effectivityDate.minDate = period.dateStart;
//		$scope.effectivityDate.maxDate = period.dateEnd;
//		$scope.member.effectivityDate = new Date(period.dateStart);
//	};
//	
//	
//	function calculateAge(birthday) { 
//	    var ageDifMs = Date.now() - birthday.getTime();
//	    var ageDate = new Date(ageDifMs);
//	    return Math.abs(ageDate.getUTCFullYear() - 1970);
//	}
//	
//	
//	
//	function getDeclarationPeriod(id) {
//		var periods = eval($scope.declarationPeriods);
//		var period = {};
//		for (var i = 0; i < periods.length; i++ ) {
//			if(periods[i].id === id ) {
//				period = periods[i]
//				break;
//			}
//		}
//		return period;
//	}
//};