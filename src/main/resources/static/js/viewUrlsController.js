
	urlShortenerApp.controller('viewUrlsController', function($scope, $http,$window) {
		
		$scope.records = [];
		$scope.init = function(){
			 $http.get("http://localhost:8090/url")
	            .then(function successCallback(response){
	            	console.log(response);
	                if(response.data.success == true){
	                	var data = response.data.data;
	                	
	                	 $( data ).each(function( i,record ) {
	 	    	    		    var editColumn =  '<a onclick="angular.element(this).scope().openDashboard(\''+record.shortUrl+'\')"> <i class="fa fa-share-square-o" aria-hidden="true"></i></a>';
			                   var callUrl =   '<button type="button" onclick="angular.element(this).scope().callUrl(\''+record.shortUrl+'\')" class="btn">Call Url</button> </a>';
	                		 $('#example').DataTable().row.add([editColumn,record.id,record.url,record.shortUrl,record.expiryDate,callUrl]).draw();
	                		  });
	                	
	                } else{
	                	$scope.success=false;
	                	$scope.failure=true;
	                	$scope.responseMessage=response.data.message;
	                }
	            }, function errorCallback(response){
	            });
		}
		
		$scope.openDashboard = function (urlId){
			$window.open('http://localhost:8090/dashboard?url='+urlId, '_blank');
		}
		
		$scope.callUrl = function(url){
			 $http.post("http://localhost:8090/url/access", url)
	            .then(function successCallback(response){
	            	console.log(response);
	                if(response.data.success == true){
	                } else{
	                	alert(response.data.message);
	                }
	            }, function errorCallback(response){
	            	
	            });
		}
	});
	