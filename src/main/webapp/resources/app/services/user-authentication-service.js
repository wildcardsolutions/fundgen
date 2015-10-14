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
			app.register.service(
					'userAuthenticationService', 
					[ 
					  	'$http', '$rootScope',
					  	function($http, $rootscope) {
					  		
					  	    
							this.authenticate = function (credentials, callback) {
								console.log(credentials);
								 var headers = credentials ? {authorization : "Basic "
							  	        + btoa(credentials.username + ":" + credentials.password)
							  	    } : {};
							  	    
//							  	$http.post("/login", $.param(credentials))
//							  		.success(function(data) {
//										if (data.name) {
//											callback(true);
//										} else {
//											callback(false);
//										}
//									})
//									.error(function() {
//										callback(false);
//									});
							  	    
								$http.get("/user",  {headers : headers})
									.success(function(data) {
										console.log("data=" + data);
										if (data.name) {
											callback(true, data);
										} else {
											callback(false, null);
										}
									})
									.error(function() {
										callback(false, null);
									});
							};
						
							this.logout = function () {
								return $http.post(repositoryBase + '/logout');
							};
						}
					]
			);
		}
	);