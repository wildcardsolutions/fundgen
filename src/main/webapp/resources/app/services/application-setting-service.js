/**
 * 
 */
define(
		[
		 	'angularjs',
		], 
		function () {
			angular.module('springBootApp')
				.service('applicationSetting',  function() {
					var setting = {};
					
					this.set = function(key, value) {
						setting[key] = value;
					};
					
					this.get= function(key) {
						return setting[key];
					};
					
					this.remove = function(key) {
						delete setting[key];
					};
					
				}); 
		}
);
