'use strict';

var module = angular.module('myApp');

module.factory('EmployeeService', ['$http', '$q', function($http, $q) {
	
	function addComment(empId, comment) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addComment/', {empId: empId, comment: comment})
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding comment');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateComment(comment) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateComment/', comment)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating comment');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function deleteComment(commentId) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'deleteComment/' + commentId)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error deleting comment');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addSkill(empId, skill) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addSkill/' + 'skill/', {empId: empId, skillName: skill})
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateSkill(skill) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateSkill/' + 'skill/', skill)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function deleteSkill(skillId) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'deleteSkill/' + 'skill/' + skillId)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error deleting skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addTraining(empId, skill) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addSkill/' + 'training/', {empId: empId, skillName: skill})
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding trained skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateTraining(skill) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateSkill/' + 'training/', skill)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating trained skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function deleteTraining(skillId) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'deleteSkill/' + 'training/' + skillId)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error deleting trained skill');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addProfile(profile) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addProfile/', profile)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding profile');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateProfile(profile) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateProfile/', profile)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating profile');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function deleteProfile(empId) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'deleteProfile/' + empId)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error deleting profile');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function getVerticalAccount() {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'getVerticalAccount/')
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error fetching verticals/accounts');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function getAccounts(verticalID) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'getAccounts/' + verticalID)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error fetching accounts with id');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function getVerticalSpocs(verticalID) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'getVerticalSPOC/' + verticalID)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error fetching vertical spoc');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function getAccountSpocs(accountID) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'getAccountSPOC/' + accountID)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error fetching account spoc');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addEmployee(emp) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addEmployee/', emp)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding employee');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addEmpVertical(vertical, empId) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addEmpVertical/' + empId, vertical)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding employee vertical');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function addEmpAccount(account, empId) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'addEmpAccount/' + empId, account)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error adding employee account');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateEmployee(emp) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateEmployee/', emp)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating employee');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateVertical(vertical, empId) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateVertical/' + empId, vertical)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating vertical');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateAccount(account, empId) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateAccount/' + empId, account)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating account');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function updateRM(rm, empId) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'updateRM/' + empId, rm)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error updating rm');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	function insertHistory(empId, changes) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI + 'insertHistory/' + empId + '/' + changes)
			.then(
			function(response) {
				deferred.resolve(response.data);
			},
			function(errResponse) {
				console.error('Error inserting history');
				deferred.reject(errResponse);
			}
		);
		return deferred.promise;
	}
	
	return {
		addComment: addComment,
		updateComment: updateComment,
		deleteComment: deleteComment,
		addSkill: addSkill,
		updateSkill: updateSkill,
		deleteSkill: deleteSkill,
		addTraining: addTraining,
		updateTraining: updateTraining,
		deleteTraining: deleteTraining,
		addProfile: addProfile,
		updateProfile: updateProfile,
		deleteProfile: deleteProfile,
		getVerticalAccount: getVerticalAccount,
		getAccounts: getAccounts,
		getVerticalSpocs: getVerticalSpocs,
		getAccountSpocs: getAccountSpocs,
		addEmployee: addEmployee,
		addEmpVertical: addEmpVertical,
		addEmpAccount: addEmpAccount,
		updateEmployee: updateEmployee,
		updateVertical: updateVertical,
		updateAccount: updateAccount,
		updateRM: updateRM,
		insertHistory: insertHistory
	}
}]);