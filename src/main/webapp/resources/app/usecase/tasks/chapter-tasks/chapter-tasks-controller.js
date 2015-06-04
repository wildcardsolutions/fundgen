/**
 * 
 */
"use strict";

define(
		[
		 	'app',
			'panel-directive'
		], 
		function (app) {
			console.log("registering controller: groupTasksController");
			app.register.controller(
					'groupTasksController', 
					[
					 	'$scope', 
					 	function ($scope) {
					 		var vm = this;
					 		init();
					 		
					 		function init() {
					 			console.log('initializing groupTasksController');
					 		}
					 	}
					]
			);
		}
);