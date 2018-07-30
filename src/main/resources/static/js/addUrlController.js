	urlShortenerApp.controller('addUrlController', function($scope, $http) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';
		
		$scope.success = false;
		$scope.failure = false;
		$scope.urlValue="";
		$scope.responseMessage = "";
		
		
		
		$scope.addUrl = function(){
			$scope.success=false;
			$scope.failure=false;
			 $http.post("http://localhost:8090/url/shortener", $scope.urlValue)
	            .then(function successCallback(response){
	            	console.log(response);
	                if(response.data.success == true){
	                	$scope.success=true;
	                	$scope.failure=false;
	                	$scope.responseMessage=response.data.message;
	                } else{
	                	$scope.success=false;
	                	$scope.failure=true;
	                	$scope.responseMessage=response.data.message;
	                }
	            }, function errorCallback(response){
	            	$scope.success=false;
                	$scope.failure=true;
                	$scope.responseMessage="POST-ing of data failed";
	            });
		}
	});
