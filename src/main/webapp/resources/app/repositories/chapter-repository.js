/**
 * @repository:	chapterRepository
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
		['app', 'angularjs'], 
		function (app) {
			console.log("registering repository: chapterRepository");
			app.register.service(
					'chapterRepository', 
					[ 
						'$http', 
				  	function($http) {
						var repositoryBase = "/api/chapter";
						
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
						
						this.searchFor = function (searchString, page, size) {
							return $http.get(repositoryBase + '/search/' + searchString + "/" + page + "/" +size);
						};
						
						this.getAll = function () {
							return $http.get(repositoryBase);
						};
					
						this.get = function (id) {
							return $http.get(repositoryBase + '/' + id);
						};
					
						this.insert = function (card) {
							return $http.post(repositoryBase, card);
						};
					
						this.update = function (card) {
							return $http.put(repositoryBase + '/' + card.id, card)
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
 