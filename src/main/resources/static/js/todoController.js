	// create the module and name it scotchApp
	var urlShortenerApp = angular.module('urlShortenerApp', ['ngRoute']);

	// configure our routes
	urlShortenerApp.config(function($routeProvider) {
		$routeProvider

			// route for the home page
			.when('/', {
				templateUrl : 'addUrl',
				controller  : 'addUrlController'
			})

			// route for the about page
			.when('/callUrl', {
				templateUrl : 'callUrl',
				controller  : 'callUrlController'
			})

			// route for the contact page
			.when('/viewUrls', {
				templateUrl : 'viewUrls',
				controller  : 'viewUrlsController'
			}).when('/graph', {
				templateUrl : 'graph',
				controller  : 'graphController'
			});
	});

	urlShortenerApp.controller('mainController', function($scope) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';
	});