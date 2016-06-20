/**
 * Angular App
 */

var app = angular.module('ValueListPageManager',[]);

app.controller('PageManagerController', function($scope){
	$scope.greeting = "hello";
	$scope.pageNames = ['ProjectOverview','Interface Overview','Using Default Implementations','Paging Results','Complex Queries','Configuring the AdapterConversionService','Further Extension','Using in Spring MVC'];	
	$scope.currentPageName = "ProjectOverview";
	console.log("currentPageName is " + $scope.currentPageName);
	
	$scope.setInterfaceOverview = function(){
		console.log("currentPageName is " + $scope.currentPageName);
		$scope.currentPageName = "ProjectOverview";
		console.log("set new page to Interface Overview");
	};
	
});