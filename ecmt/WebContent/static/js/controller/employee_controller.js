'use strict';

var module = angular.module('myApp');

module.controller('EmployeeController', ['$scope', '$window', '$location', 'EmployeeService', 'Search', function($scope, $window, $location, EmployeeService, Search) {
	
	var self = this;
	var previousProfile;
	
	self.emp = {employee: {empId:'', oldEmpId:'', name:'', status:'', tenure:'', phone:'', email:'', doj:'', cl:'', wl:'', hl:'', wtr:''},
				vertical: {vertical:{id:'', name:''}, spoc:{id:'', name:'', phone:'', email:''}},
				account: {account:{id:'', name:''}, spoc:{id:'', name:'', phone:'', email:''}},
				rm: {rm:'', phone:'', email:''},
				skills: [],
				trainings: [],
				history: [],
				comments: []};
	
	self.statusOptions = ['Billable', 'Buffer', 'Bench', 'SI', 'Training'];
	self.wtrOptions = ['N/A', 'Yes', 'No'];
	self.verticles = [];
	self.accounts = [];
	self.verticalSpocs = [];
	self.accountSpocs = [];
	self.newSkill = '';
	self.newTrainedSkill = '';
	self.tenure = '';
	self.comment = '';
	self.conn = '';
	
	self.states = {'AL': 'Alabama', 'AK': 'Alaska', 'AZ': 'Arizona', 'AR': 'Arkansas', 'CA': 'California',
			'CO': 'Colorado', 'CT': 'Connecticut', 'DE': 'Delaware', 'DC': 'District of Columbia', 'FL': 'Florida',
			'GA': 'Georgia', 'HI': 'Hawaii', 'ID': 'Idaho', 'IL': 'Illinois', 'IN': 'Indiana', 'IA': 'Iowa',
			'KS': 'Kansas', 'KY': 'Kentucky', 'LA': 'Louisiana', 'ME': 'Maine', 'MD': 'Maryland', 'MA': 'Massachusetts',
			'MI': 'Michigan', 'MN': 'Minnesota', 'MS': 'Mississippi', 'MO': 'Missouri', 'MT': 'Montana',
			'NE': 'Nebraska', 'NV': 'Nevada', 'NH': 'New Hampshire', 'NJ': 'New Jersey', 'NM': 'New Mexico',
			'NY': 'New York', 'NC': 'North Carolina', 'ND': 'North Dakota', 'OH': 'Ohio', 'OK': 'Oklahoma',
			'OR': 'Oregon', 'PA': 'Pennsylvania', 'RI': 'Rhode Island', 'SC': 'South Carolina', 'SD': 'South Dakota',
			'TN': 'Tennessee', 'TX': 'Texas', 'UT': 'Utah', 'VT': 'Vermont', 'VA': 'Virginia',
			'WA': 'Washington', 'WV': 'West Virginia', 'WI': 'Wisconsin', 'WY': 'Wyoming'};
	
	EmployeeService.getVerticalAccount()
		.then(function(theGoods) {
			self.verticles = theGoods.vertDetails;
			self.accounts = theGoods.accDetails;
			if(self.emp.vertical.vertical == undefined)
				self.emp.vertical.vertical = {id:0, name:'Not Assigned'};
			else {
				EmployeeService.getVerticalSpocs(self.emp.vertical.vertical.id)
					.then(function(spocs) {
						self.verticalSpocs = spocs;
					}, function(errResponse) {}
				);
			}
			if(self.emp.account.account.name == '')
				self.emp.account.account = {id:0, name:'Not Assigned'};
			else {
				EmployeeService.getAccounts(self.emp.vertical.vertical.id)
					.then(function(accounts) {
						self.accounts = accounts;
						if(self.emp.account.account == null)
							self.emp.account.account = self.accounts[0];
					}, function(errResponse) {}
				);
				EmployeeService.getAccountSpocs(self.emp.account.account.id)
					.then(function(spocs) {
						self.accountSpocs = spocs;
					}, function(errResponse) {}
				);
			}
		}, function(errResponse) {}
	);
	
	var profile = JSON.parse($window.localStorage.getItem('profile'));
	
	if(profile) {
		self.emp.employee.empId = profile.employee.empId;
		self.emp.employee.oldEmpId = profile.employee.empId;
		self.emp.employee.name = profile.employee.name;
		self.emp.employee.status = profile.employee.status;
		var values = profile.employee.tenure.split("-");
		self.emp.employee.tenure = new Date(values[0], parseInt(values[1])-1, values[2]);
		self.tenure = Math.floor((new Date().getTime() - self.emp.employee.tenure.getTime())/(86400000));
		self.emp.employee.phone = profile.employee.phone;
		self.emp.employee.email = profile.employee.email;
		values = profile.employee.doj.split("-");
		self.emp.employee.doj = new Date(values[0], parseInt(values[1])-1, values[2]);
		self.emp.vertical.vertical = profile.vertical.vertical;
		self.emp.vertical.spoc = profile.vertical.spoc;
		self.emp.account.account = profile.account.account;
		self.emp.account.spoc = profile.account.spoc;
		self.emp.rm = profile.rm;
		self.emp.employee.cl = profile.employee.cl;
		self.emp.employee.wl = profile.employee.wl;
		self.emp.employee.hl = profile.employee.hl;
		self.emp.employee.wtr = profile.employee.wtr;
		self.emp.skills = [];
		self.emp.trainings = [];
		self.emp.comments = [];
		self.emp.history = [];
		
		if(profile.skills.skills) {
			profile.skills.skills.forEach(function(skill) {
				self.emp.skills.push({skillName: skill.skillName, edit: false, key: skill.key});
			});
		}
		if(profile.trainings.skills) {
			profile.trainings.skills.forEach(function(skill) {
				self.emp.trainings.push({skillName: skill.skillName, edit: false, key: skill.key});
			});
		}
		if(profile.comments.comments) {
			profile.comments.comments.forEach(function(comment) {
				values = comment.date.split("-");
				self.emp.comments.push({comment: comment.comment, date: new Date(values[0], parseInt(values[1])-1, values[2]), edit:false, commentId: comment.commentId});
			});
		}
		self.emp.comments.sort(compare);
		if(profile.history.comments) {
			profile.history.comments.forEach(function(comment) {
				values = comment.date.split("-");
				self.emp.history.push({text: comment.comment, date: new Date(values[0], parseInt(values[1])-1, values[2])});
			});
		}
	
		$scope.isEditing = false;
		$scope.showReportBtn = profile.fromReport;
		$scope.addingEmp = false;
	}
	else {
		$scope.isEditing = true;
		$scope.showReportBtn = false;
		$scope.addingEmp = true;
		
		self.emp.employee.wtr = self.wtrOptions[0];
		self.emp.employee.status = self.statusOptions[2];
		self.emp.vertical.vertical = self.verticles[1];
	}
	
	$scope.isAddingSkill = false;
	$scope.isAddingTrainedSkill = false;
	
	$scope.editing = function() {
		return $scope.isEditing;
	}
	
	$scope.notEditing = function() {
		return !$scope.isEditing;
	}
	
	$scope.addingSkill = function() {
		return $scope.isAddingSkill;
	}
	
	$scope.addingTrainedSkill = function() {
		return $scope.isAddingTrainedSkill;
	}
	
	$scope.fromReport = function() {
		return $scope.showReportBtn;
	}
	
	$scope.edit = function() {
		$scope.isEditing = true;
		previousProfile = angular.copy(self.emp);
	}
	
	$scope.submit = function() {
		$scope.isEditing = false;
		
		if(!angular.equals(previousProfile, self.emp)) {
			if($scope.addingEmp) {
				EmployeeService.addEmployee(self.emp.employee).then(function() {
					EmployeeService.addEmpVertical(self.emp.vertical, self.emp.employee.empId).then(function() {
						EmployeeService.addEmpAccount(self.emp.account, self.emp.employee.empId).then(function() {
							EmployeeService.updateRM(self.emp.rm, self.emp.employee.empId).then(function() {
								EmployeeService.insertHistory(self.emp.employee.empId, "Employee created").then(function(newHistory) {
									updateProfile()
									if(newHistory) {
										self.emp.history = [];
										newHistory.comments.forEach(function(comment) {
											values = comment.date.split("-");
											self.emp.history.push({text: comment.comment, date: new Date(values[0], parseInt(values[1])-1, values[2])});
										});
									}
									$scope.addingEmp = false;
								}, function(errResponse) {});
							}, function(errResponse) {});
						}, function(errResponse) {});
					}, function(errResponse) {});
				}, function(errResponse) {});
			}
			else {
				var changes = '';
				EmployeeService.updateEmployee(self.emp.employee).then(function(empResponse) {
					changes += empResponse;
					EmployeeService.updateVertical(self.emp.vertical, self.emp.employee.empId).then(function(vertResponse) {
						changes += vertResponse;
						EmployeeService.updateAccount(self.emp.account, self.emp.employee.empId).then(function(accResponse) {
							changes += accResponse;
							EmployeeService.updateRM(self.emp.rm, self.emp.employee.empId).then(function(rmResponse) {
								changes += rmResponse;
								if(changes != '') {
									
									changes = "Changes made to: " + changes.slice(0, -2);
									
									EmployeeService.insertHistory(self.emp.employee.empId, changes)
										.then(function(newHistory) {
											updateProfile()
											if(newHistory) {
												self.emp.history = [];
												newHistory.comments.forEach(function(comment) {
													values = comment.date.split("-");
													self.emp.history.push({text: comment.comment, date: new Date(values[0], parseInt(values[1])-1, values[2])});
												});
											}
										}, function(errResponse) {}
									);
								}
							}, function(errResponse) {});
						}, function(errResponse) {});
					}, function(errResponse) {});
				}, function(errResponse) {});
				
				$scope.submitNewSkill();
				$scope.submitNewTrainedSkill();
				self.emp.skills.forEach(function(s) {
					if(s.edit)
						$scope.submitSkill(s);
				});
				self.emp.trainings.forEach(function(t) {
					if(t.edit)
						$scope.submitSkill(t);
				});
				self.emp.comments.forEach(function(c) {
					if(c.edit)
						$scope.submitComment(c);
				});
			}
		}
	}
	
	$scope.addSkill = function() {
		$scope.isAddingSkill = true;
	}
	
	$scope.submitNewSkill = function() {
		if(self.newSkill != '') {
			EmployeeService.addSkill(self.emp.employee.empId, self.newSkill)
				.then(function(skill) {
					self.emp.skills.push({skillName:skill.skillName, key:skill.key, edit:false});
					self.newSkill = '';
					updateProfile();
				}, function(errResponse) {}
			);
		}
		$scope.isAddingSkill = false;
	}
	
	$scope.addTrainedSkill = function() {
		$scope.isAddingTrainedSkill = true;
	}
	
	$scope.submitNewTrainedSkill = function() {
		if(self.newTrainedSkill != '') {
			EmployeeService.addTraining(self.emp.employee.empId, self.newTrainedSkill)
				.then(function(skill) {
					self.emp.trainings.push({skillName:self.newTrainedSkill, edit:false});
					self.newTrainedSkill = '';
					updateProfile();
				}, function(errResponse) {}
			);
		}
		$scope.isAddingTrainedSkill = false;
	}
	
	$scope.editTraining = function(skill) {
		skill.edit = true;
	}
	
	$scope.deleteNewTrainedSkill = function() {
		self.newTrainedSkill = '';
		$scope.isAddingTrainedSkill = false;
	}
	
	$scope.editSkill = function(skill) {
		skill.edit = true;
	}
	
	$scope.submitSkill = function(skill) {
		if(skill.skillName != '') {
			EmployeeService.updateSkill(skill)
				.then(function() {
					updateProfile();
				}, function(errResponse) {}
			);
		}
		skill.edit = false;
	}
	
	$scope.submitTraining = function(skill) {
		if(skill.skillName != '') {
			EmployeeService.updateTraining(skill)
				.then(function() {
					updateProfile();
				}, function(errResponse) {}
			);
		}
		skill.edit = false;
	}
	
	$scope.deleteNewSkill = function() {
		self.newTrainedSkill = '';
		$scope.isAddingSkill = false;
	}
	
	$scope.editComment = function(comment) {
		comment.edit = true;
	}
	
	$scope.submitComment = function(comment) {
		EmployeeService.updateComment(comment)
			.then(function() {
				updateProfile();
			}, function(errResponse) {}
		);
		comment.edit = false;
		self.emp.comments.sort(compare);
	}
	
	$scope.deleteSkill = function(skill) {
		var answer = window.confirm("Delete this skill?");
		if(answer == true) {
			EmployeeService.deleteSkill(skill.key)
				.then(function() {
					self.emp.skills.splice(self.emp.skills.indexOf(skill), 1);
					updateProfile();
				}, function(errResponse) {}
			);
		}
	}
	
	$scope.deleteTraining = function(skill) {
		var answer = window.confirm("Delete this skill?");
		if(answer == true) {
			EmployeeService.deleteTraining(skill.key)
				.then(function() {
					self.emp.trainings.splice(self.emp.trainings.indexOf(skill), 1);
					updateProfile();
				}, function(errResponse) {}
			);
		}
	}
	
	$scope.deleteComment = function(comment) {
		var answer = window.confirm("Delete this comment?");
		if(answer == true) {
			EmployeeService.deleteComment(comment.commentId)
				.then(function() {
					self.emp.comments.splice(self.emp.comments.indexOf(comment), 1);
					updateProfile();
				}, function(errResponse) {}
			);
		}
	}
	
	$scope.del = function() {
		var answer;
		if($scope.addingEmp) {
			answer = window.confirm("Cancel adding new employee?");
			if(answer == true)
				$location.path("/home");
		}
		else {
			answer = window.confirm("Delete this employee?");
			if(answer == true)
				console.log("Delete employee");
			else
				console.log("Canceled deletion");
		}
	}
	
	$scope.updateTenure = function() {
		if(self.emp.employee.tenure != undefined)
			self.tenure = Math.floor((new Date().getTime() - self.emp.employee.tenure.getTime())/(86400000));
		else
			self.tenure = '';
	}
	
	$scope.addComments = function() {
		if(self.comment != '') {
			EmployeeService.addComment(self.emp.employee.empId, self.comment)
				.then(function(comment) {
					var values = comment.date.split("-");
					self.emp.comments.push({comment:comment.comment, date:new Date(values[0], parseInt(values[1])-1, values[2]), edit:false});
					self.comment = '';
					updateProfile();
				}, function(errResponse) {}
			);
		}
	}
	
	$scope.deleteComments = function(comment) {
		EmployeeService.deleteComment(comment.commentId)
			.then(function() {
				self.emp.comments.splice(self.emp.comments.indexOf(comment), 1);
				updateProfile();
			}, function(errResponse) {}
		);
	}
	
	$scope.missingFields = function() {
		return (self.emp.employee.empId == '' ||
				self.emp.employee.name == '' ||
				self.emp.employee.tenure == '' ||
				self.emp.employee.doj == '' ||
				self.emp.employee.phone == '' ||
				self.emp.employee.email == '' ||
				self.emp.employee.wl == '' ||
				self.emp.employee.hl == '' ||
				self.emp.vertical.vertical.id == 0);
	}
	
	$scope.verticalChange = function() {
		EmployeeService.getVerticalSpocs(self.emp.vertical.vertical.id)
			.then(function(spocs) {
				self.verticalSpocs = spocs;
			}, function(errResponse) {}
		);
		EmployeeService.getAccounts(self.emp.vertical.vertical.id)
			.then(function(accounts) {
				self.accounts = accounts;
				if(self.emp.account.account == null)
					self.emp.account.account = self.accounts[0];
			}, function(errResponse) {}
		);
	}
	
	$scope.accountChange = function() {
		if(self.emp.account.account == null)
			self.emp.account.account = self.accounts[0];
		EmployeeService.getAccountSpocs(self.emp.account.account.id)
			.then(function(spocs) {
				self.accountSpocs = spocs;
			}, function(errResponse) {}
		);
	}
	
	$scope.toReport = function() {
		Search.searchReport($window.localStorage.getItem('field1'), $window.localStorage.getItem('field2'), $window.localStorage.getItem('field3'), $window.localStorage.getItem('field4'))
			.then(function(report) {
				$window.localStorage.setItem('report', JSON.stringify(report));
				$location.path("/report");
			}, function(errResponse) {console.log("Unable to receive report");}
		);
	}
	
	$scope.phoneControl = function() {
		var phone = self.emp.employee.phone;
		var length = phone.length;
		
		for(var i=0; i<length; i++)
		{
			if(!(phone[i] == '(' ||
					phone[i] == ')' ||
					phone[i] == '-' ||
					(phone[i] >= '0' && phone[i] <= '9')))
			{
				phone = phone.slice(0, i) + phone.slice(i+1);
				i--;
				length--;
			}
		}
		for(var i=1; i<length; i++)
		{
			if(i != 4 && i != 8 && !(phone[i] >= '0' && phone[i] <= '9'))
			{
				phone = phone.slice(0, i) + phone.slice(i+1);
				i--;
				length--;
			}
		}
		if(length > 0 && phone[0] != '(')
		{
			phone = '(' + phone;
			length++;
		}
		if(length > 4 && phone[4] != ')')
		{
			phone = phone.slice(0, 4) + ')' + phone.slice(4);
			length++;
		}
		if(length > 8 && phone[8] != '-')
		{
			phone = phone.slice(0, 8) + '-' + phone.slice(8);
			length++;
		}
		
		self.emp.employee.phone = phone;
	}
	
	function updateProfile() {
		Search.searchID(self.emp.employee.empId).then(function(profile) {
			profile.fromReport = $scope.showReportBtn;
			$window.localStorage.setItem('profile', JSON.stringify(profile));
		}, function(errResponse) {});
	}
	
	function compare(a, b) {
		if(a.date < b.date)
			return -1;
		if(a.date > b.date)
			return 1;
		return 0;
	}
}]);