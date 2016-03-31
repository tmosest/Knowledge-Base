<div ng-controller="AlbumCtrl" id="timer">{{counter}}</div>

var myApp = angular.module("myApp", []);

myApp.controller("AlbumCtrl", AlbumCtrl);

// Angular Controller For Timer Counter.
function AlbumCtrl($scope,$timeout) {
    $scope.counter = 60;				// Counter starts at 0
    $scope.onTimeout = function(){
        $scope.counter--;
		if($scope.counter == 0) {
			//TODO should make an ajax call to log auto log out in activities
			window.location = "http://54.209.78.72/api-tests/loan-calc/loV3/logout.php";
			$scope.counter = 60;
		}
        mytimeout = $timeout($scope.onTimeout,1000);
    }
    var mytimeout = $timeout($scope.onTimeout,1000);
    
    $scope.stop = function(){
        $timeout.cancel(mytimeout);
    }           
}

// Funciton: used to reset the timer on  an api call
function reset_timer() {
    var scope = angular.element($("#timer")).scope();
    scope.$apply(function(){
        scope.counter = 60;
    })
}
