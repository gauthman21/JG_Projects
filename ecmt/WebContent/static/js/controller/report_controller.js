'use strict';

var module = angular.module('myApp');

module.controller('ReportController', ['$scope', '$window', '$location', 'Search', function($scope, $window, $location, Search) {
	
	var self = this;
	self.employeeList = [];
	self.tableHeaders = [{name: 'empId', display: 'Emp ID'}, {name: 'name', display: 'Name'}, {name: 'status', display: 'Status'},
	                     {name: 'wtr', display: 'WTR'}, {name: 'wl', display: 'WL'}, {name: 'cl', display: 'CL'},
	                     {name: 'hl', display: 'HL'}, {name: 'phone', display: 'Phone'}, {name: 'email', display: 'Email'}];
	
	$scope.search = $window.localStorage.getItem('search');
	self.employeeList = JSON.parse($window.localStorage.getItem('report'));
	
	$scope.viewEmployee = function(empId) {
		Search.searchID(empId)
			.then(function(profile) {
				profile.fromReport = true;
				$window.localStorage.setItem('profile', JSON.stringify(profile));
				$location.path("/employee");
			}, function(errResponse) {}
		);
	}
	
	$scope.sort = function(tab) {
		if($scope.select === tab && $scope.order === 2)
			$scope.order = 1;
		else if($scope.select === tab && $scope.order === 1)
			$scope.order = 2;
		else
			$scope.order = 1;
		$scope.select = tab;
		if($scope.order === 1)
			$scope.filter = tab;
		else
			$scope.filter = '-' + tab;
	}
	
}]);