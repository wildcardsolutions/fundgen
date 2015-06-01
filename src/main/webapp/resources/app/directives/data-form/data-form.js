/**
 * @directive:	dataForm
 * @author:		apagador
 * @date:		2015/05/09
 */
angular.module('springbootApp').directive('dataForm', function() {	
	return {
		restrict : 'E',
		transclude: true,
		replace : true,
		template : 
			"<form role='form' name='form'>" +
			"	<ng-transclude></ng-transclude>" +
			"</form>",
		scope : {
			name : "@name",
		}
	}
});
