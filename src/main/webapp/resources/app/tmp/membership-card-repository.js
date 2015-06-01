/**
 * @repository:	membershipCardRepository
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
		['app', 'angularjs'], 
		function (app) {
			console.log("registering repository: membershipCardRepository");
			app.register.service(
					'membershipCardRepository', 
					[ 
					 	'$http', 
						function($http) {
							var repositoryBase = "/api/cards/";
							
							this.get = function(id) {
								return $http.get(repositoryBase + id);
							}
						
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
							
						};
					}
				]
			);
		}
);
