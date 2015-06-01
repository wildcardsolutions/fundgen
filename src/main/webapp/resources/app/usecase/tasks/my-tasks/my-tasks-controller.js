/**
 * 
 */
'use strict';

define(
		[
		 	'app',
			'panel-directive'
		], 
		function (app) {
			console.log('registering controller: myTasksController');
			app.register.controller(
					'myTasksController', 
					[
					 	'$scope', 
					 	function ($scope) {
					 		var vm=this;
					 		init();
					 		
					 		function init() {
					 			console.log('initializing myTasksController');
					 		}
					 	}
					]
			);
		}
);