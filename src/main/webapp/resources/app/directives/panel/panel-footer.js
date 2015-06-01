/**
 * @directive:	panelFooter
 * @author:		apagador
 * @date:		2015/05/09
 */

"use strict";

define(
		['app'], 
		function (app) {
			app.register.directive('panelFooter', function() {	
				return {
					restrict : 'E',
					replace: true,
					transclude: true,
					template : 
						"<div class='panel-footer'>" +
						"	<ng-transclude></ng-transclude>" +
						"</div>",
				}
		});
});

