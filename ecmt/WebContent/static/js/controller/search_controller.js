'use strict';

var module = angular.module('myApp');

module.controller('SearchController', ['$scope', '$window', '$location', 'Search', function($scope, $window, $location, Search) {
	
	var self = this;
	self.fields = {empId:'', reportOption:'', skill:'', tenureStart:'', tenureEnd:''};
	//$scope.import = '';
	
	self.options = ['Billable','Non-Billable' , 'Buffer', 'Bench', 'Skill', 'Tenure (Days)', 'All Employees'];
	
	$scope.skillSearch = false;
	$scope.tenureSearch = false;
	
	$scope.searchingTenure = function() {
		return $scope.tenureSearch;
	}
	
	$scope.searchingSkill = function() {
		return $scope.skillSearch;
	}
	
	$scope.reportChange = function() {
		if(self.fields.reportOption == 'Skill')
			$scope.skillSearch = true;
		else
			$scope.skillSearch = false;
		if(self.fields.reportOption == 'Tenure (Days)')
			$scope.tenureSearch = true;
		else
			$scope.tenureSearch = false;
	}
	
	$scope.switchDatabaseConn = function() {
		//var answer = window.confirm("Switch database connection?");
		if($scope.check == true) {
			
			Search.switchDBConn($scope.check);
			$window.alert($scope.check);
			//Search.switchDBConn($scope.check);
		}else{
			$window.alert($scope.check);
		}
	}
	
	$scope.searchID = function() {
		Search.searchID(self.fields.empId)
			.then(function(profile) {
				profile.fromReport = false;
				$window.localStorage.setItem('profile', JSON.stringify(profile));
				$location.path("/employee");
			}, function(errResponse) {console.log("Unable to receive employee information");}
		);
	}
	
	$scope.searchReport = function() {
		if(self.fields.tenureStart == '')
			self.fields.tenureStart = 0;
		if(self.fields.reportOption == 'Tenure (Days)')
			$window.localStorage.setItem('search', 'Tenure (' + self.fields.tenureStart + ' - ' + self.fields.tenureEnd + ' days)');
		else if(self.fields.reportOption == 'Skill')
			$window.localStorage.setItem('search', 'Skill (' + self.fields.skill + ')');
		else
		$window.localStorage.setItem('search', self.fields.reportOption);
		$window.localStorage.setItem('field1', self.fields.reportOption);
		$window.localStorage.setItem('field2', self.fields.skill);
		$window.localStorage.setItem('field3', self.fields.tenureStart);
		$window.localStorage.setItem('field4', self.fields.tenureEnd);
		Search.searchReport(self.fields.reportOption, self.fields.skill, self.fields.tenureStart, self.fields.tenureEnd)
			.then(function(report) {
				$window.localStorage.setItem('report', JSON.stringify(report));
				$location.path("/report");
			}, function(errResponse) {console.log("Unable to receive report");}
		);
	}
	
	$scope.add = function() {
		$window.localStorage.removeItem('profile');
		$location.path("/employee");
	}
	
	//This allows the html admin button to link to the /admin page.
	$scope.admin = function() {
		$window.localStorage.removeItem('profile');
		$location.path("/admin");
	}
	
	$scope.importFile = function() {
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$scope.$apply(function() {
				$scope.import = reader.result;
				console.log(reader.result);
				
				Search.importBatch(reader.result)
					.then(function(report) {
						
					}, function(errResponse) {console.log("Unable to add batch");}
				);
				
			});
		};
		
		var csvFileInput = document.getElementById("importFile");
		var csvFile = csvFileInput.files[0];
		
		reader.readAsText(csvFile);
	}
	
}]);
