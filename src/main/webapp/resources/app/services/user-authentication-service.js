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
							  	    
								$http.get("/user",  {headers : headers})
									.success(function(data) {
										if (data.name) {
											callback(true);
										} else {
											callback(false);
										}
									})
									.error(function() {
										callback(false);
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