/**
 * 
 */
"use strict";

define(
		['angular-route', 'ui-bootstrap', 'angular-dialogs'], 
		function () {
			console.log("initializing...");
			var app = angular.module('app', ['ngRoute', 'ui.bootstrap', 'dialogs.services']);
			
			app.config(function ($routeProvider, $httpProvider, $controllerProvider, $compileProvider, $filterProvider, $provide) {
				
				 var resolveDependencies = function ($q, $rootScope, dependencies) {
		                var defer = $q.defer();
		                require(dependencies, function () {
		                    defer.resolve();
		                    $rootScope.$apply()
		                });
		                return defer.promise;
				 };
				 
				 app.register = {
						 controller: $controllerProvider.register,
						 directive: $compileProvider.directive,
						 filter: $filterProvider.register,
						 factory: $provide.factory,
						 service: $provide.service
			    };
			
				$routeProvider
					.when("/myaccount", {
						templateUrl: "resources/app/usecase/my-account/my-account.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['my-account']);
		                    }
						}
					})
					.when("/mychapter", {
						templateUrl: "resources/app/usecase/my-chapter/my-chapter.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['my-chapter']);
		                    }
						}
					})
					.when("/tasks/mytasks", {
						templateUrl: "resources/app/usecase/tasks/my-tasks/my-tasks.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['my-tasks']);
		                    }
						}
					})
					.when("/tasks/grouptasks", {
						templateUrl: "resources/app/usecase/tasks/group-tasks/group-tasks.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['group-tasks']);
		                    }
						}
					})
					.when("/tasks/taskhistory", {
						templateUrl: "resources/app/usecase/tasks/task-history/task-history.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['task-history']);
		                    }
						}
					})
					.when("/membership/import", {
						templateUrl: "resources/app/usecase/membership/import/membership-import.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['membership-import']);
		                    }
						}
					})
					.when("/membership/register", {
						templateUrl: "resources/app/usecase/membership/registration/membership-registration.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['membership-registration']);
		                    }
						}
					})
					.when("/membership/updates", {
						templateUrl: "resources/app/usecase/membership/update/membership-update.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['membership-update']);
		                    }
						}
					})
					.when("/membership/renewals", {
						templateUrl: "resources/app/usecase/membership/renewal/membership-renewal.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['membership-renewal']);
		                    }
						}
					})
					.when("/inventory/register", {
						templateUrl: "resources/app/usecase/inventory/register/register-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['register-membership-cards']);
		                    }
						}
					})
					.when("/inventory/levels/:cardType", {
						templateUrl: "resources/app/usecase/inventory/levels/inventory-levels.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['inventory-levels']);
		                    }
						}
					})
					.when("/inventory/discard", {
						templateUrl: "resources/app/usecase/inventory/discard/discard-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['discard-membership-cards']);
		                    }
						}
					})
					.when("/inventory/allocate", {
						templateUrl: "resources/app/usecase/inventory/allocate/allocate-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['allocate-membership-cards']);
		                    }
						}
					})
					.when("/inventory/request", {
						templateUrl: "resources/app/usecase/inventory/request/request-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['request-membership-cards']);
		                    }
						}
					})
					.when("/inventory/list", {
						templateUrl: "resources/app/usecase/inventory/list/list-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['list-membership-cards']);
		                    },
//							'init' : function($http, applicationSetting) {
//								return $http.get("/api/chapter/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
//									applicationSetting.set("chapterPageCount", result.data.pages);
//									return applicationSetting.get("chapterPageCount");
//						        });
//							}
						}
					})
					.when("/inventory/list/:cardType/:seriesStart/:seriesEnd/:pageIndex/:pageSize", {
						templateUrl: "resources/app/usecase/card-inventory/views/listMembershipCards.html"
					})
					.when("/maintenance/chapter", {
						templateUrl: "resources/app/usecase/maintenance/chapter/chapter.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['chapters']);
		                    },
							'init' : function($http, applicationSetting) {
								return $http.get("/api/chapter/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("chapterPageCount", result.data.pages);
									return applicationSetting.get("chapterPageCount");
						        });
							}
						}
					})
					.when("/maintenance/officer", {
						templateUrl: "resources/app/usecase/maintenance/officer/officer.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['officers']);
		                    },
							'init' : function($http, applicationSetting) {
								return $http.get("/api/officer/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("officerPageCount", result.data.pages);
									return applicationSetting.get("officerPageCount");
						        });
							}
						}
					})
					.when("/maintenance/cardTypes", {
						templateUrl: "resources/app/usecase/maintenance/card-types/card-types.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['card-types']);
		                    },
							'init' : function($http, applicationSetting) {
								return $http.get("/api/membershipCardType/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("membershipCardTypePageCount", result.data.pages);
									return applicationSetting.get("membershipCardTypePageCount");
						        });
							}
						}
					})
					.when("/maintenance/settings", {
						templateUrl: "resources/app/usecase/maintenance/settings/settings.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['settings']);
		                    },
							init : function($http, applicationSetting) {
								return $http.get("/api/settings/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("settingsPageCount", result.data.pages);
									return applicationSetting.get("settingsPageCount");
						        });
							}
						}
					})
					.when("/maintenance/roles", {
						templateUrl: "resources/app/usecase/maintenance/roles/roles.html",
						resolve : {
							load:  function ($q, $rootScope) {
								console.log("loading roles");
		                        return resolveDependencies($q, $rootScope, ['roles']);
		                    },
							init : function($http, applicationSetting) {
								console.log("querying page size");
								return $http.get("/api/role/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("rolesPageCount", result.data.pages);
									return applicationSetting.get("rolesPageCount");
						        });
							}
						}
					})
					.when("/login", {
						controller: "loginController",
						templateUrl: "resources/app/usecase/login/login.html"
					})
					.otherwise({
			            redirectTo:'/'
					});

				$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			});
			
			 
			
			app.service('applicationSetting', [ '$http', function($http) {
				var setting = {};
				
				this.set = function(key, value) {
					setting[key] = value;
				};
				
				this.get= function(key) {
					return setting[key];
				};
				
				this.remove = function(key) {
					delete setting[key];
				};
				
			}]); 
			
			app.run(
					[
					 	'$route', 
					 	'$rootScope', 
					 	'$location', 
					 	'datepickerPopupConfig', 
					 	'applicationSetting', 
					 	function ($route, $rootScope, $location, datepickerPopupConfig, applicationSetting) {
						    var original = $location.path;
						    $rootScope.userLoggedIn = false;
						    
						    datepickerPopupConfig.showButtonBar = false;
						    applicationSetting.set("pageSize", 10);
						    console.log("pageSize=" + applicationSetting.get("pageSize"));
						    $location.path = function (path, reload) {
						        if (reload === false) {
						            var lastRoute = $route.current;
						            var un = $rootScope.$on('$locationChangeSuccess', function () {
						                $route.current = lastRoute;
						                un();
						            });
						        }
						        return original.apply($location, [path]);
						    };
					 }])


			return app;
	
		}
);