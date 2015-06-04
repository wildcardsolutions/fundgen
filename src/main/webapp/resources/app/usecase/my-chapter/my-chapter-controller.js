"use strict";

define(
		[
		 'app'
		], 
		function (app) {
			console.log("registering controller: myChapterController");
			app.register.controller(
					'myChapterController', 
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