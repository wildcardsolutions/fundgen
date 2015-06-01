/**
 * @directive:	paginate
 * @author:		apagador
 * @date:		2015/05/09
 */
"use strict";

define(
	['app'], 
	function (app) {
		app.register.directive('paginate', function () {	
			return {
				restrict : 'E',
				replace : true,
				template : 
					"<ul class='pagination pagination-sm'>" +
					"<li ng-class='{disabled: !enableStart}'>" +
					"		<span aria-label='Start' ng-click='updatePage($event,1)'>" +
					"			<span aria-hidden='true'><i class='fa fa-fast-backward'></i></span>" +
					"		</span>" +
					"	</li>" +
					"	<li ng-class='{disabled: !enableStart}'>" +
					"		<span aria-label='Previous' ng-click='previousPage($event)'>" +
					"			<span aria-hidden='true'><i class='fa fa-step-backward'></i></span>" +
					"		</span>" +
					"	</li>" +
					"	<li ng-class='{active:  i == currentPage}' ng-repeat='i in getNumber(totalPage) track by $index'  >" +
					"		<span ng-click='updatePage($event, ($index + 1))'>{{$index + 1}}</span>" +
					"	</li>" +
					"	<li ng-class='{disabled: !enableEnd}'>" +
					"		<span href='#' aria-label='End' ng-click='nextPage($event)'>" +
					"			<span aria-hidden='true'><i class='fa fa-step-forward'></i></span>" +
					"		</span>" +
					"	</li>" +
					"	<li ng-class='{disabled: !enableEnd}'>" +
					"		<span aria-label='End' ng-click='lastPage($event)'>" +
					"			<span aria-hidden='true'><i class='fa fa-fast-forward'></i></span>" +
					"		</span>" +
					"	</li>" +
					"</ul>",
				scope : {
						currentPage : "=currentPage",
						totalPage : "=totalPage",
						updatePageMethod : "&updatePageMethod"
				},
				controller : function($scope) {
					//var vm = this;
					
					init();
					
					function init() {
						$scope.enableStart = 1 < $scope.currentPage && $scope.currentPage <= $scope.totalPage;
						$scope.enableEnd = $scope.currentPage < $scope.totalPage && 1 < $scope.totalPage
					}
					
					function update(page) {
						$scope.currentPage = page;
						init();
						$scope.updatePageMethod({page: $scope.currentPage});
					}
					
					$scope.$watch( 
							function (scope) { 
								return scope.totalPage; 
							},
				            function (newValue, oldValue) {
								console.log("oldvalue=" + oldValue + ", newValue=" + newValue);
								if (oldValue != newValue) {
									init();
								}
				            }
				    );
					
					$scope.getNumber = function(num) {
						var a =  new Array(num);   
						for (var i=0; i<num; i++) {
							a[i] = i + 1;
						}
						return a;
					};
					
					$scope.firstPage = function($event) {
						$event.preventDefault();
						if ($scope.enableStart) {
							update(1);
						}
					};
					
					$scope.previousPage = function($event) {
						$event.preventDefault();
						if ($scope.enableStart) {
							update($scope.currentPage - 1);
						}
					};
					
					$scope.nextPage = function($event) {
						$event.preventDefault();
						if ($scope.enableEnd) {
							update($scope.currentPage + 1);
						}
					};
					
					$scope.lastPage = function($event) {
						$event.preventDefault();
						if ($scope.enableEnd) {
							update($scope.totalPage);
						}
					};
					
					$scope.updatePage = function($event, page) {
						$event.preventDefault();
						if (page == $scope.currentPage) {
							return;
						}
						update(page);
					};
				},
				//controllerAs : 'vm',
				//bindToController : true
			}
		});
	}
);