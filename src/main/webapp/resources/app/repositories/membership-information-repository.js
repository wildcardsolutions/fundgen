/**
 * @directive:	pageSize
 * @author:		apagador
 * @date:		2015/05/16
 */
"use strict";

/**
 * 
 */
define(
		['app', 'angularjs'], 
		function (app) {
			console.log("registering repository: membershipInformationRepository");
			app.register.service(
					'membershipInformationRepository', 
					[ 
					  	'$http', 
					  	function($http) {
							var repositoryBase = "/api/members";
							
							this.getPage = function (page, size, searchString) {
								var url = repositoryBase + '/page/' + page + '/' + size
								if (null!=searchString && ""!=searchString) {
									url = url + "/" + searchString;
								}
								return $http.get(url);
							};
							
							this.getPageCount = function (size) {
								return $http.get(repositoryBase + '/pages/' + size);
							};
							
							this.getAll = function () {
								return $http.get(repositoryBase);
							};
						
							this.get = function (id) {
								return $http.get(repositoryBase + '/' + id);
							};
						
							this.insert = function (member) {
								return $http.post(repositoryBase, member);
							};
						
							this.update = function (member) {
								return $http.put(repositoryBase + '/' + member.id, member)
							};
						
							this.delete = function (id) {
								return $http.delete(repositoryBase + '/' + id);
							};
							
							this.undelete = function (id) {
								return $http.post(repositoryBase + '/undelete/' + id);
							};
							
							this.autoCompleteLastname = function(searchString) {
								return $http.get(repositoryBase + "/autocomplete/lastname/" + searchString);
							};
							
							this.autoCompleteFirstname = function(searchString) {
								return $http.get(repositoryBase + "/autocomplete/firstname/" + searchString);
							};
							
							this.autoCompleteMiddlename = function(searchString) {
								return $http.get(repositoryBase + "/autocomplete/middlename/" + searchString);
							};
							
							this.autoCompleteAddress = function(searchString) {
								return $http.get(repositoryBase + "/autocomplete/address/" + searchString);
							};
						}
					]
			);
		}
	);


//angular.module('springbootApp').service(
//		'membershipInformationRepository', 
//		[ 
//		  	'$http', 
//		  	function($http) {
//				var repositoryBase = "/api/members";
//				
//				this.getPage = function (page, size) {
//					return $http.get(repositoryBase + '/' + page + '/' + size);
//				};
//				
//				this.getPageCount = function (size) {
//					return $http.get(repositoryBase + '/pages/' + size);
//				};
//				
//				this.getAll = function () {
//					return $http.get(repositoryBase);
//				};
//			
//				this.get = function (id) {
//					return $http.get(repositoryBase + '/' + id);
//				};
//			
//				this.insert = function (member) {
//					return $http.post(repositoryBase, member);
//				};
//			
//				this.update = function (member) {
//					return $http.put(repositoryBase + '/' + member.id, member)
//				};
//			
//				this.delete = function (id) {
//					return $http.delete(repositoryBase + '/' + id);
//				};
//				
//				this.undelete = function (id) {
//					return $http.post(repositoryBase + '/undelete/' + id);
//				};
//				
//				this.autoCompleteLastname = function(searchString) {
//					return $http.post(repositoryBase + "/autocomplete/lastname/" + searchString);
//				};
//				
//				this.autoCompleteFirstname = function(searchString) {
//					return $http.post(repositoryBase + "/autocomplete/firstname/" + searchString);
//				};
//				
//				this.autoCompleteMiddlename = function(searchString) {
//					return $http.post(repositoryBase + "/autocomplete/middlename/" + searchString);
//				};
//				
//				this.autoCompleteAddress = function(searchString) {
//					return $http.post(repositoryBase + "/autocomplete/address/" + searchString);
//				};
//			}
//		]
//); 