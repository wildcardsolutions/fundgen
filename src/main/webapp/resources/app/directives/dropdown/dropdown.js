/**
 * @directive:	dropdown
 * @author:		apagador
 * @date:		2015/05/16
 */
"use strict";

define(
	['app'], 
	function (app) {
		app.register.directive('dropdown', function () {	
			return {
				restrict : 'E',
				replace : true,
				template : 
					"<div class='dropdown'>" +
					"	<button class='btn btn-default dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='true'>" +
					"		Dropdown " +
					"		<span class='caret'></span>" +
					"	</button>" +
					"	<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" +
					"		<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>Action</a></li>" +
					"		<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>Another action</a></li>" +
					"		<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>Something else here</a></li>" +
					"		<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>Separated link</a></li>" +
					"		</ul>" +
					"</div>",
				scope : {
					currentSelection : "=currentSelection",
				},
				controller : function() {
					var vm = this;
					
					init();
					
					function init() {
						console.log("dropdown.init()");
					}
					
				},
				controllerAs : 'vm',
				bindToController : true
			}
		});
	}
);