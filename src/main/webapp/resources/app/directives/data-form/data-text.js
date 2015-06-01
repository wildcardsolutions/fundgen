/**
 * @directive:	dataText
 * @author:		apagador
 * @date:		2015/05/09
 */
angular.module('springbootApp').directive('dataText', function() {	
	return {
		restrict : 'E',
		replace : true,
		template : 
			"<div>" +
			"<span class='col-sm-2'>" +
			"	<label class='control-label' for='vm.id'>{{vm.label}}</label>" +
			"</span>" +
			"<span class='col-sm-4'>" +
			"	<input type='text' class='form-control' id='vm.id' ng-model='vm.model' ng-readonly='vm.readOnly' />" +
			"</span>" +
			"</div",	
		scope : {
			label : "@label",
			id : "@id",
			name : "@name",
			model : "=model",
			readOnly : "=readOnly"
		},
		controllerAs : 'vm',
		bindToController : true,
		controller : function() {
			var vm = this;
			
			vm.readOnly = vm.readOnly || false;
			console.log("initialized");
		}
	}
});
