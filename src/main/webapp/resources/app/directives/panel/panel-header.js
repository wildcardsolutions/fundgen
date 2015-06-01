/**
 * @directive:	panelFooter
 * @author:		apagador
 * @date:		2015/05/09
 */

"use strict";

define(
		['app'], 
		function (app) {
			app.register.directive('panelHeader', function() {	
				return {
					restrict : 'E',
					replace: true,
					transclude: true,
					template : 
						"<div class='panel-heading'>" +
						" 	<label class='controller-title'>" +
						"		<i class='{{icon}}'></i>" +
						"		&nbsp;{{title}}" +
						"	</label>" +
						"	<ng-transclude></ng-transclude>" +
						"</div>",
					scope : {
							title : "@title",
							icon : "@icon",
					}
				}
		});
});

