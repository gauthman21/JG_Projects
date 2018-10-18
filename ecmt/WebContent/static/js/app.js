'use strict';

var App = angular.module("myApp", ["ngRoute"]);

var REST_SERVICE_URI;

App.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : function() {
			return "static/templates/landingPage.html";
		}
	})
	.when("/home", {
		templateUrl : function() {
			return "static/templates/landingPage.html";
		}
	})
	.when("/employee", {
		templateUrl : function() {
			return "static/templates/employeePage.html";
		}
	})
	.when("/report", {
		templateUrl : function() {
			return "static/templates/reportPage.html";
		}
	})
	.when("/attrition", {
		templateUrl : function() {
			return "static/templates/attritionPage.html";
		}
	})
	.when("/admin", {
		templateUrl : function() {
			return "static/templates/admin.html";
		}
	})
});

App.run(['$location', function($location) {
	REST_SERVICE_URI = 'http://' + $location.host() + ':' + $location.port() + '/ecmt/';
}]);

App.filter('reverse', function() {
	return function(items) {
		return items.slice().reverse();
	}
});
