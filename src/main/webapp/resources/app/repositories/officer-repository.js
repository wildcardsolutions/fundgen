/**
 * 
 */
"use strict";

/**
 * 
 */
define(
	['app', 'angularjs'], 
	function (app) {
		console.log("registering repository: officerRepository");
		app.register.service(
				'officerRepository', 
				[ 
					'$http', 
					function($http) {
					var repositoryBase = "/api/officer";
					
					this.getPage = function (page, size, searchString) {
						var url = repositoryBase + '/page/' + page + '/' + size
						if (null!=searchString && ""!=searchString) {
							url = url + "/" + searchString;
						}
						return $http.get(url);
					};
					
					this.getPageCount = function (size) {
						return $http.get(repositoryBase + '/getPageCountBySize/' + size);
					};
					
					this.getPageCountForSearchString = function (size, searchString) {
						var url = repositoryBase + '/getPageCountBySize/' + size;
						if (null!=searchString && ""!=searchString) {
							url = url + "/" + searchString;
						}
						return $http.get(url);
					};
					
					this.getAll = function () {
						return $http.get(repositoryBase);
					};
				
					this.get = function (id) {
						return $http.get(repositoryBase + '/' + id);
					};
				
					this.insert = function (officer) {
						return $http.post(repositoryBase, officer);
					};
				
					this.update = function (officer) {
						return $http.put(repositoryBase + '/' + officer.officerId, officer)
					};
				
					this.delete = function (id) {
						return $http.delete(repositoryBase + '/' + id);
					};
					
					this.undelete = function (id) {
						return $http.post(repositoryBase + '/undelete/' + id);
					};
				}
			]
		);
	}
);