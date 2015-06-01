/**
 * @directive:	paging
 * @author:		apagador
 * @date:		2015/05/16
 */
"use strict";

define(
	['app'], 
	function (app) {
		app.register.directive('paging', function () {	
			return {
				restrict : 'E',
				replace : true,
				template : 
					"<ul class='pagination pagination-sm'>" +
					"	<li ng-class='{disabled: !vm.enableStart}'>" +
					"		<a href='' aria-label='Start' ng-click='vm.updatePage($event,1)'>" +
					"			<span aria-hidden='true'>&laquo;</span>" +
					"		</a>" +
					"	</li>" +
					"	<li ng-class='{active:  i == vm.currentPage}' ng-repeat='i in vm.getNumber(vm.totalPage) track by $index'  >" +
					"		<a href='' ng-click='vm.updatePage($event, ($index + 1))'>{{$index + 1}}</a>" +
					"	</li>" +
					"	<li ng-class='{disabled: !vm.enableEnd}'>" +
					"		<a href='' aria-label='End' ng-click='vm.updatePage($event,vm.totalPage)'>" +
					"			<span aria-hidden='true'>&raquo;</span>" +
					"		</a>" +
					"	</li>" +
					"</ul>",
				scope : {
						currentPage : "=currentPage",
						totalPage : "=totalPage",
						updatePageMethod : "&updatePageMethod"
				},
				controller : function() {
					var vm = this;
					
					init();
					
					function init() {
						vm.enableStart = 1 < vm.currentPage;
						vm.enableEnd = vm.currentPage < vm.totalPage
					}
					
					vm.getNumber = function(num) {
						var a =  new Array(num);   
						for (var i=0; i<num; i++) {
							a[i] = i + 1;
						}
						return a;
					};
					
					vm.updatePage = function($event, page) {
						$event.preventDefault();
						if (page == vm.currentPage) {
							return;
						}
					
						console.log("vm.updatePage=" + page);
						
						vm.currentPage = page;
						if (vm.totalPage == vm.currentPage) {
							vm.enableStart = true;
							vm.enableEnd  = false;
						} else if (1 == vm.currentPage) {
							vm.enableStart = false;
							vm.enableEnd  = true;
						} else {
							vm.enableStart = true;
							vm.enableEnd  = true;
						}
						vm.updatePageMethod({page: page});
					};
				},
				controllerAs : 'vm',
				bindToController : true
			}
		});
	}
);