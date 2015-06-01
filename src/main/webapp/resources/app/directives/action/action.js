/**
 * @directive:	action
 * @author:		apagador
 * @date:		2015/05/09
 */

"use strict";

/**
 * 
 */
define(
	['app'], 
	function (app) {
		app.register.directive('action', function () {	
			return {
				restrict : 'E',
				replace : true,
				template : 
					"<button ng-show='vm.show' ng-disabled='vm.disabled' ng-click='vm.click()' type='button' class='btn btn-{{vm.type}} btn-{{vm.size}}'>" +
					"	<i class='{{vm.icon}}'></i>" +
					"	&nbsp;&nbsp;{{vm.caption}}" +
					"</button>",
				scope : {
					caption : "@caption",
					icon : "@icon",
					type : "@type",
					size : "@size",
					show : "=?",
					disabled : "=?",
					param : "=param",
					callback : "&callback"
				},
				controllerAs : 'vm',
				bindToController : true,
				controller : function() {
					var vm = this;
					
					vm.show = vm.show || true;
					vm.disabled = vm.disabled || false;
					vm.type = vm.type || 'default';
					
					vm.click = function() {
						if (!vm.param) {
							vm.callback({param: vm.param})
						} else {
							vm.callback();
						}
					}
				}
			}
		});
	}
);
//
//
//angular.module('springbootApp')
//	.directive('action', function() {	
//		return {
//			restrict : 'E',
//			replace : true,
//			template : 
//				"<button ng-show='vm.show' ng-disabled='vm.disabled' ng-click='vm.click()' type='button' class='btn btn-{{vm.type}} btn-{{vm.size}}'>" +
//				"	<i class='{{vm.icon}}'></i>" +
//				"	&nbsp;&nbsp;{{vm.caption}}" +
//				"</button>&nbsp;",
//			scope : {
//				caption : "@caption",
//				icon : "@icon",
//				type : "@type",
//				size : "@size",
//				show : "=?",
//				disabled : "=?",
//				param : "=param",
//				callback : "&callback"
//			},
//			controllerAs : 'vm',
//			bindToController : true,
//			controller : function() {
//				var vm = this;
//				
//				vm.show = vm.show || true;
//				vm.disabled = vm.disabled || false;
//				vm.type = vm.type || 'default';
//				
//				vm.click = function() {
//					if (!vm.param) {
//						vm.callback({param: vm.param})
//					} else {
//						vm.callback();
//					}
//				}
//			}
//		}
//	});
