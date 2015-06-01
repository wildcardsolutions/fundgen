/**
 * @repository:	rolesRepository
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
		['app', 'angularjs'], 
		function (app) {
			console.log("registering repository: rolesRepository");
			app.register.service(
					'rolesRepository', 
					[ 
					 	'$http', 
					  	function($http) {
							var repositoryBase = "/api/role";
							
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
							
							this.searchFor = function (searchString, page, size) {
								return $http.get(repositoryBase + '/search/' + searchString + "/" + page + "/" +size);
							};
							
							this.getAll = function () {
								return $http.get(repositoryBase);
							};
						
							this.get = function (id) {
								return $http.get(repositoryBase + '/' + id);
							};
						
							this.insert = function (obj) {
								return $http.post(repositoryBase, obj);
							};
						
							this.update = function (obj) {
								return $http.put(repositoryBase + '/' + obj.roleId, obj)
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
