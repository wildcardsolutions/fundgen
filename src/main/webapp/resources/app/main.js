/**
 * 
 */
"use strict";

/**
 * list all dependencies
 */
require.config({
	baseUrl : 'resources/app',
	paths : {
		'jquery' 								: '../js/jquery/jquery.min',
		'angularjs'								: '../js/angularjs/1.3.0/angular.min',
		'angular-route'							: '../js/angularjs/1.3.0/angular-route.min',
		'angular-resource'						: '../js/angularjs/1.3.0/angular-resource.min',
		'angular-cookies'						: '../js/angularjs/1.3.0/angular-cookies.min',
		'angular-sanitize'						: '../js/angularjs/1.3.0/angular-sanitize.min',
		'ui-bootstrap'							: '../js/ui-bootstrap/0.11.2/ui-bootstrap.min',
		'angular-dialogs'						: '../js/angular-dialog-service/v2/dialogs.v2.min',
		'alertify'								: '../js/alertify/alertify.min',
		'app' 									: 'app',
		'my-account'							: 'usecase/my-account/my-account-controller',
		'my-chapter'							: 'usecase/my-chapter/my-chapter-controller',
		'my-tasks'								: 'usecase/tasks/my-tasks/my-tasks-controller',
		'group-tasks'							: 'usecase/tasks/group-tasks/group-tasks-controller',
		'task-history'							: 'usecase/tasks/task-history/task-history-controller',
		'membership-import' 					: 'usecase/membership/import/membership-import-controller',
		'membership-registration' 				: 'usecase/membership/registration/membership-registration-controller',
		'membership-renewal' 					: 'usecase/membership/renewal/membership-renewal-controller',
		'membership-update' 					: 'usecase/membership/update/membership-update-controller',
		'allocate-membership-cards' 			: 'usecase/inventory/allocate/allocate-membership-cards-controller',
		'discard-membership-cards' 				: 'usecase/inventory/discard/discard-membership-cards-controller',
		'inventory-levels' 						: 'usecase/inventory/levels/inventory-levels-controller',
		'list-membership-cards' 				: 'usecase/inventory/list/list-membership-cards-controller',
		'register-membership-cards' 			: 'usecase/inventory/register/register-membership-cards-controller',
		'request-membership-cards' 				: 'usecase/inventory/request/request-membership-cards-controller',
		'card-types'							: 'usecase/maintenance/card-types/card-types-controller',
		'chapters'								: 'usecase/maintenance/chapter/chapter-controller',
		'officers'								: 'usecase/maintenance/officer/officer-controller',
		'roles'									: 'usecase/maintenance/roles/roles-controller',
		'settings'								: 'usecase/maintenance/settings/settings-controller',
		'access-rights'							: 'modals/access-rights/access-rights-controller',
		'card-details'							: 'modals/card-details/card-details-controller',
		'card-type-details'						: 'modals/card-types/card-type-details-controller',
		'chapter-details'						: 'modals/chapter/chapter-details-controller',
		'member-details'						: 'modals/member-details/member-details-controller',
		'officer-details'						: 'modals/officer/officer-details-controller',
		'setting-details'						: 'modals/settings/setting-details-controller',
		'panel-directive'						: 'directives/panel/panel',
		'panel-header-directive'				: 'directives/panel/panel-header',
		'panel-body-directive'					: 'directives/panel/panel-body',
		'panel-footer-directive'				: 'directives/panel/panel-footer',
		'paging-directive'						: 'directives/paging/paginate',
		'action-directive'						: 'directives/action/action',
		'dropdown-directive'					: 'directives/dropdown/dropdown',
		'chapter-repository' 					: 'repositories/chapter-repository',
		'membership-card-repository' 			: 'repositories/membership-card-repository',
		'membership-card-type-repository' 		: 'repositories/membership-card-type-repository',
		'membership-information-repository' 	: 'repositories/membership-information-repository',
		'officer-repository' 					: 'repositories/officer-repository',
		'roles-repository' 						: 'repositories/roles-repository',
		'settings-repository' 					: 'repositories/settings-repository',
		'card-inventory-service' 				: 'services/card-inventory-service',
		'declaration-period-service' 			: 'services/declaration-period-service'	
	},
	shim : {
		'angularjs': {
	        exports: 'angularjs'
	    } ,
	    'angular-route'	: ['angularjs'],
	    'ui-bootstrap' : ['angularjs', 'jquery'],
	    'angular-dialogs' : ['angularjs'],
	    
//	    'app' : {
//	    	exports: 'app'
//	    }
	}, 
	// bootstrap dependencies
//	deps: ['app-init']
});

/**
 * load application
 */
require(
		[
		 	'angularjs', 
		 	'app'
		],
		function () {
			angular.bootstrap(document, ['app']);
		}
);

