'use strict';

var module = angular.module('myApp');

module.factory('Search', ['$http', '$q', '$window', function($http, $q, $window) {
	
	function searchReport(option, skill, start, end) {
		var deferred = $q.defer();
		if(option == 'Tenure (Days)') {
			$http.get(REST_SERVICE_URI + 'getReportTenure/' + start + '/' + end)
				.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					deferred.reject(errResponse);
				}
			);
		}
		else if(option == 'Skill') {
			$http.post(REST_SERVICE_URI + 'getReportSkill/', skill)
				.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					deferred.reject(errResponse);
				}
			);
		}
		else {
			$http.get(REST_SERVICE_URI + 'getReport/' + option)
				.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					deferred.reject(errResponse);
				}
			);
		}
		return deferred.promise;
	}
	
	function switchDBConn(conn) {
		var deferred = $q.defer();
		$window.alert(conn);
		$http.get(REST_SERVICE_URI + 'databaseConn/' + conn)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error switching database connection.');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function searchID(empId) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'getEmployee/' + empId)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function importBatch(batch) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addBatch/', batch)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	return {
		searchReport: searchReport,
		searchID : searchID,
		importBatch: importBatch
	}
}]);