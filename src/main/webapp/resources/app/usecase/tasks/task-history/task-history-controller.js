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
			console.log("registering controller: taskHistoryController");
			app.register.controller(
					'taskHistoryController', 
					[
					 	'$scope', 
					 	function ($scope) {
					 		var vm = this;
					 		init();
					 		
					 		function init() {
					 			console.log('initializing taskHistoryController');
					 		}
					 	}
					]
			);
		}
);