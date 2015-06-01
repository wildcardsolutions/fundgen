/**
 * @directive:	panelBody
 * @author:		apagador
 * @date:		2015/05/09
 */
/**
 * @directive:	panel
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
		['app'], 
		function (app) {
			app.register.directive('panelBody', function() {	
				return {
					restrict : 'E',
					replace: true,
					transclude: true,
					template : 
							"<div class='panel-body'>" +
							"	<ng-transclude></ng-transclude>" +
							"</div>",
				}
		});
});
