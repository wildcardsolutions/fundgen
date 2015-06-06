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
					'taskService', 
					[ 
					  	'$http', 
					  	function($http) {
							var repositoryBase = "/api/task";

							this.history = function() {
								return $http.get(repositoryBase + cardId);
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
							
							this.getTaskHistoryPage = function (page, size, searchString) {
								var url = repositoryBase + '/history/' + page + '/' + size
								if (null!=searchString && ""!=searchString) {
									url = url + "/" + searchString;
								}
								return $http.get(url);
							};
							
							this.getChapterTaskPage = function (page, size, searchString) {
								var url = repositoryBase + '/chapter/' + page + '/' + size
								if (null!=searchString && ""!=searchString) {
									url = url + "/" + searchString;
								}
								return $http.get(url);
							};
							
							this.cancelTask = function(id) {
								var url = repositoryBase + '/cancel/' + id
								return $http.post(url);
							};
					  	}
					]
			);
		}
	);
