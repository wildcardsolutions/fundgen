/**
 * 
 */
"use strict";

/**
 * 
 */
define(
		['app'], 
		function (app) {
			app.register.service(
					'cardInventoryService', 
					[ 
					  	'$http', 
					  	function($http) {
							var repositoryBase = "/api/inventory/";

							this.get = function(cardId) {
								return $http.get(repositoryBase + cardId);
							};
							
							this.allocate = function(requestInfo) {
								return $http.post(repositoryBase + "allocate", requestInfo);
							};
							
							this.discard = function(requestInfo) {
								return $http.post(repositoryBase + "discard", requestInfo);
							};
							
							this.levels = function(requestInfo) {
							
							};
							
							this.list = function(requestInfo) {
								return $http.get(repositoryBase +"list/" + requestInfo.pageIndex + "/" + requestInfo.pageSize + "/" + requestInfo.cardType + "/" + requestInfo.seriesStart + "/" + requestInfo.seriesEnd)
							};
							
							this.register = function(requestInfo) {
								return $http.post(repositoryBase + "register", requestInfo);
							};
							
							this.request = function(requestInfo) {
								return $http.post(repositoryBase + "request", requestInfo);
							};
					  	}
					]
			);
		}
	);

/**
 * 
 */
//angular.module('springbootApp').service(
//		'cardInventoryService', 
//		[ 
//		  	'$http', 
//		  	function($http) {
//				var repositoryBase = "/api/card-inventory/";
//
//				this.allocate = function(requestInfo) {
//					return $http.post(repositoryBase + "allocate", requestInfo);
//				};
//				
//				this.discard = function(requestInfo) {
//					return $http.post(repositoryBase + "discard", requestInfo);
//				};
//				
//				this.levels = function(requestInfo) {
//				
//				};
//				
//				this.list = function(requestInfo) {
//					return $http.get(repositoryBase +"list/" + requestInfo.pageIndex + "/" + requestInfo.pageSize + "/" + requestInfo.cardType + "/" + requestInfo.seriesStart + "/" + requestInfo.seriesEnd)
//				};
//				
//				this.register = function(requestInfo) {
//					return $http.post(repositoryBase + "register", requestInfo);
//				};
//				
//				this.request = function(requestInfo) {
//					
//				};
//		  	}
//		]
//); 