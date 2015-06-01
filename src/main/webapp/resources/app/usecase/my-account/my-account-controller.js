"use strict";

define(
		[
		 'app'
		], 
		function (app) {
			console.log("registering controller: myAccountController");
			app.register.controller(
					'myAccountController', 
					[
					 	'$scope', 
					 	function ($scope) {
					 		var vm=this;
					 		console.log('boo');
					 		init();
					 		
					 		function init() {
					 			console.log('nag-iinit');
					 		}
					 	}
					]
			);
		}
);