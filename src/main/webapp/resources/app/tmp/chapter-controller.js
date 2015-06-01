/**
 * 
 */
"use strict";

/**
 * 
 */
var chapterController = function ($scope, $http, applicationSetting) {
	
	var vm = this;
	
	vm.error = false;
	
	$scope.pageCount = applicationSetting.get("pageCount");
	
	$scope.chapterUpdate = false;
	$scope.chapterAdd = true;
	
	$scope.chapter = {};
    $scope.chapter.id = "";
	$scope.chapter.chapterName = "";
	$scope.chapter.chapterLocation = "";
	
	$scope.editChapter = {};
	$scope.editChapter.id = "";
	$scope.editChapter.chapterName = "";
	$scope.editChapter.chapterLocation = "";

	$scope.page = 1;
	$scope.enableStart = false;
	$scope.enableEnd = ($scope.pageCount > $scope.page);
	$scope.size = applicationSetting.get("pageSize");
	
	loadPage($scope.page, $scope.size);

	function loadPage(page, size) {
		$http.get("/chapter/" + page + "/" + size)
			.success(function (response) {
				$scope.chapters = response;
			}
		);
	}
	
	
	$scope.getNumber = function(num) {
		var a =  new Array(num);   
		for (var i=0; i<num; i++) {
			a[i] = i + 1;
		}
		return a;
	};
	
	
	$scope.updatePageSize = function() {
		$scope.page = 1;
		applicationSetting.set("pageSize", $scope.size);
		$http.get("/chapter/getPageCountBySize/" + $scope.size).then(function(result) {
			$scope.pageCount = result.data.pages;
			$scope.enableStart = false;
			$scope.enableEnd = ($scope.pageCount > $scope.page);
			loadPage($scope.page, $scope.size);
        });
		
	}
	
	$scope.updatePage = function($event, page) {
		$event.preventDefault();
		if (page == $scope.page) {
			return;
		}
		$scope.page = page;
		if ($scope.pageCount == $scope.page) {
			$scope.enableEnd = false;
			$scope.enableStart = true;
		} else if (1 == $scope.page) {
			$scope.enableEnd = true;
			$scope.enableStart = false;
		} else {
			$scope.enableEnd = true;
			$scope.enableStart = true;
		}
		loadPage($scope.page, $scope.size);
	};
	
	
	vm.deleteChapter = function(chapter) {
		var msg = "This will delete " + chapter.chapterName + " chapter. Do you want to proceed?";
		alertify.confirm(msg, function (e) {
			if (e) {
				var index = -1
				var chapters = eval($scope.chapters);
				for (var i = 0; i < chapters.length; i++ ) {
					if( chapters[i].id === chapter.id ) {
						index = i;
						break;
					}
				}
				$scope.chapters.splice(index, 1);		
				$scope.$apply();
			}
		})
		
	};
	
	vm.editChapter = function(chapter) {
		$scope.chapterUpdate = true;
		$scope.chapterAdd = false;

		angular.copy(chapter, $scope.chapter)
		angular.copy(chapter, $scope.editChapter)
	};
	
    vm.updateChapter = function(chapter) {
    	var msg = "This will update chapter information on database. Proceed?";
		alertify.confirm(msg, function (e) {

			$scope.editChapter = {};
			$scope.editChapter.id = null;
			$scope.editChapter.chapterName = null;
			$scope.editChapter.chapterLocation = null;
			
			$scope.chapterUpdate = false;
			$scope.chapterAdd = true;
			
			$scope.$apply();
		});
	};
	
	vm.addChapter = function(chapter) {
		var msg = "This will add new chapter information to database. Proceed?";
		alertify.confirm(msg, function (e) {
			var c = {};
			c.id = "";
			c.chapterName = "";
			c.chapterLocation = "";
			
			angular.copy(chapter, c)
			$scope.chapters.push(c);
			
			$scope.editChapter = {};
			$scope.editChapter.id = null;
			$scope.editChapter.chapterName = null;
			$scope.editChapter.chapterLocation = null;
			
			$scope.$apply();
		});
	};
	
	vm.cancelForm = function() {
		$scope.chapterUpdate = false;
		$scope.chapterAdd = true;
		
		$scope.chapter.id = null;
		$scope.chapter.chapterName = null;
		$scope.chapter.chapterLocation = null;
		
		angular.copy($scope.chapter, $scope.editChapter)
		
		$scope.card.form.$setPristine();
		$scope.card.form.$setUntouched();
	};
	
	vm.resetForm = function() {
		angular.copy($scope.chapter, $scope.editChapter)
		$scope.card.form.$setPristine();
		$scope.card.form.$setUntouched();
	};
	
}
