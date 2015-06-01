/**
 * @directive:	panel
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
		[
		 	'app',
		 	'panel-header-directive',
		 	'panel-body-directive',
		 	'panel-footer-directive'
		], 
		function (app) {
			app.register.directive('panel', function() {	
				return {
					restrict : 'E',
					transclude: true,
					replace : true,
					template : 
						"<div class='panel {{panelType}}'>" +
						"	<div class='panel-heading'>" +
						" 		<label class='controller-title'>" +
						"			<i class='{{icon}}'></i>" +
						"			&nbsp;{{title}}" +
						"		</label>" +
						"	</div>" +
						"	<ng-transclude></ng-transclude>" +
						"</div>",
					scope : {
						title : "@title",
						icon : "@icon",
						panelType : "@panelType"
					}
				};
		});
});
