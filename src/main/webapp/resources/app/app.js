/**
 * 
 */
"use strict";

define(
		['angular-route', 'angular-cookies', 'ui-bootstrap', 'angular-dialogs'], 
		function () {
			console.log("initializing...");
			var app = angular.module('app', ['ngRoute', 'ngCookies', 'ui.bootstrap', 'dialogs.services']);
			
			app.config(function ($routeProvider, $locationProvider, $httpProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $cookiesProvider) {
				
				 var resolveDependenciesWithAuthentication = function ($q, $rootScope, $location, dependencies) {
		                var defer = $q.defer();
		                
		                if ($rootScope.authenticated === true) { // fire $routeChangeSuccess
			                console.log('Yes they can!');
			                require(dependencies, function () {
			                    defer.resolve();
			                    $rootScope.$apply()
			                });
			                return defer.promise;
			            } else { // fire $routeChangeError
			                console.log('No they cant!');
			                $location.path('/login', true);

			                // I don't think this is still necessary:
			                return $q.reject("'/login'");
			            }
		                
		                
				 };
				 
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['my-account']);
		                    }
						}
					})
					.when("/mychapter", {
						templateUrl: "resources/app/usecase/my-chapter/my-chapter.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['my-chapter']);
		                    }
						}
					})
					.when("/tasks/mytasks", {
						templateUrl: "resources/app/usecase/tasks/my-tasks/my-tasks.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['my-tasks']);
		                    }
						}
					})
					.when("/tasks/chaptertasks", {
						templateUrl: "resources/app/usecase/tasks/chapter-tasks/chapter-tasks.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['chapter-tasks']);
		                    },
		                    init : function($http, applicationSetting) {
								return $http.get("/api/task/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("chapterTaskPageCount", result.data.pages);
									return applicationSetting.get("chapterTaskPageCount");
						        });
							}
						}
					})
					.when("/tasks/taskhistory", {
						templateUrl: "resources/app/usecase/tasks/task-history/task-history.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['task-history']);
		                    },
		                    init : function($http, applicationSetting) {
								return $http.get("/api/task/getPageCountBySize/" + applicationSetting.get("pageSize")).then(function(result) {
									applicationSetting.set("historyTaskPageCount", result.data.pages);
									return applicationSetting.get("historyTaskPageCount");
						        });
							}
						}
					})
					.when("/membership/import", {
						templateUrl: "resources/app/usecase/membership/import/membership-import.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['membership-import']);
		                    }
						}
					})
					.when("/membership/register", {
						templateUrl: "resources/app/usecase/membership/registration/membership-registration.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['membership-registration']);
		                    }
						}
					})
					.when("/membership/updates", {
						templateUrl: "resources/app/usecase/membership/update/membership-update.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['membership-update']);
		                    }
						}
					})
					.when("/membership/renewals", {
						templateUrl: "resources/app/usecase/membership/renewal/membership-renewal.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['membership-renewal']);
		                    }
						}
					})
					.when("/inventory/register", {
						templateUrl: "resources/app/usecase/inventory/register/register-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['register-membership-cards']);
		                    }
						}
					})
					.when("/inventory/levels/:cardType", {
						templateUrl: "resources/app/usecase/inventory/levels/inventory-levels.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['inventory-levels']);
		                    }
						}
					})
					.when("/inventory/discard", {
						templateUrl: "resources/app/usecase/inventory/discard/discard-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['discard-membership-cards']);
		                    }
						}
					})
					.when("/inventory/allocate", {
						templateUrl: "resources/app/usecase/inventory/allocate/allocate-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['allocate-membership-cards']);
		                    }
						}
					})
					.when("/inventory/request", {
						templateUrl: "resources/app/usecase/inventory/request/request-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['request-membership-cards']);
		                    }
						}
					})
					.when("/inventory/list", {
						templateUrl: "resources/app/usecase/inventory/list/list-membership-cards.html",
						resolve : {
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['list-membership-cards']);
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['chapters']);
		                    },
							init: function($http, applicationSetting) {
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['officers']);
		                    },
							init: function($http, applicationSetting) {
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['card-types']);
		                    },
							init: function($http, applicationSetting) {
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['settings']);
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
							load:  function ($q, $rootScope, $location) {
		                        return resolveDependenciesWithAuthentication($q, $rootScope, $location, ['roles']);
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
						templateUrl: "resources/app/usecase/login/login.html",
						resolve : {
							load:  function ($q, $rootScope) {
		                        return resolveDependencies($q, $rootScope, ['login-controller']);
		                    },
						}
					})
					.when("/", {
						
					})
					.otherwise({
			            redirectTo:'/login'
					});

				$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				$httpProvider.defaults.headers.post["_csrf"] = $cookiesProvider['XSRF-TOKEN'];
				//$http.defaults.headers.post["_csrf"] = $cookies['XSRF-TOKEN'];
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
			
			app.service('persistenceService', [ '$cookie', function($cookie) {
				
				this.set = function(key, value) {
					$cookie.put(key, value);
				};
				
				this.get= function(key) {
					$cookie.get(key);
				};
				
				this.clear = function(key) {
					$cookie.put(key, "");
				};
				
			}]); 
			
			app.run(
					[
					 	'$http',
					 	'$window',
					 	'$cookies',
					 	'$cookieStore',
					 	'$route', 
					 	'$rootScope', 
					 	'$location', 
					 	'datepickerPopupConfig', 
					 	'applicationSetting', 
					 	function ($http, $window, $cookies, $cookieStore, $route, $rootScope, $location, datepickerPopupConfig, applicationSetting) {
						    var original = $location.path;
						    
						    if ($cookieStore.get("authenticated") === "true") {
						    	console.log("authenticated");
						    	$rootScope.authenticated = true;
						    } else {
						    	console.log("not yet authenticated");
						    	$rootScope.authenticated = false;
						    }
						    
						   
						    
						    datepickerPopupConfig.showButtonBar = false;
						    applicationSetting.set("pageSize", 10);
						    console.log("pageSize=" + applicationSetting.get("pageSize"));
						    
						    $location.path = function (path, reload) {
						        if (reload === false) {
						            var lastRoute = $route.current;
						            var un = $rootScope.$on('$locationChangeSuccess', function () {
						                $route.current = lastRoute;
						                //un();
						            });
						        }
						        return original.apply($location, [path]);
						    };
						    
//				            $rootScope.$on('$routeChangeError', function (ev, current, previous, rejection) {
//				                if (rejection && rejection.needsAuthentication === true) {
//				                    var returnUrl = $location.url();
//				                    $log.log('returnUrl=' + returnUrl);
//				                    $location.path('/login').search({ returnUrl: returnUrl });
//				                }
//				            });
						    
						    $rootScope.logout = function() {
						    	console.log("logout");
						    	  $http.post("/logout", { 
						    		  "_csrf" : $cookies['XSRF-TOKEN']
						    		  //"_csrf" : $cookieStore.get('XSRF-TOKEN')
						    	  }).success(function() {
							    	  $rootScope.authenticated = false;
							    	  $cookieStore.put("authenticated", "");
							    	  $location.path("/login", true);
						    	  }).error(function(data) {
						    		  $cookieStore.put("authenticated", "");
						    		  $rootScope.authenticated = false;
						    		  $location.path("/login", true);
						    	  });
					    	}
						    
//						    var windowElement = angular.element($window);
//						    windowElement.on('beforeunload', function ($event) {
//						        // do whatever you want in here before the page unloads.        
//
//						        // the following line of code will prevent reload or navigating away.
//						    	//console.log("reloading...");
//						        $event.preventDefault();
//						    });
					  
						    //$http.defaults.headers.post["X-XSRF-TOKEN"] = $cookies['XSRF-TOKEN'];
						    $http.defaults.headers.common["X-XSRF-TOKEN"] = $cookies['XSRF-TOKEN'];
						    //$http.defaults.headers.common["X-XSRF-TOKEN"] = $cookieStore.get('XSRF-TOKEN')
						    
					 }])


			return app;
	
		}
);