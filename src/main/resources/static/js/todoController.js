/**
 * 
 */

var myApp = angular.module('myApp',[]);

myApp.controller('todoController', ['$scope','$http', function($scope, $http) {
  $scope.todoTextValue = '';
  $scope.todoList = [];
  $scope.showSuccess =false;
  $scope.showError = false;
  $scope.successText = '';
  $scope.errorText = '';
  $scope.changed = false;
  $scope.init = function () {
	   
	  $http({
		  method: 'GET',
		  url: '/todo'
		}).then(function successCallback(response) {
			console.log(response.data);
		  $scope.todoList= response.data;
		  }, function errorCallback(response) {
			  
		  });
	}
 
//  $('.add-todo').on('keypress',function (e) {
//      e.preventDefault
//      if (e.which == 13) {
//           if($(this).val() != ''){
//           var todo = $(this).val();
//            $scope.createTodo(todo); 
//            $scope.countTodos();
//           }else{
//               // some validation
//           }
//      }
//});
//  
  $scope.todoKeyPress = function(e){
	  if (e.which == 13) {
          if($scope.todoTextValue != ''){
           $scope.createTodo(); 
          }else{
              // some validation
          }
     }
  }
  
  $scope.showErrorFunc = function(text){
	  $scope.showError = true;
	$scope.errorText = text;
  }
  
  $scope.showSuccessFunc = function(text){
	  $scope.showSuccess = true;
	  $scope.successText = text;
  }
  
  $scope.hideMessages = function(){
	  $scope.showError = false;
	  $scope.showSuccess = false;
  }
  
  $scope.deleteTodo = function(id){
	  $scope.hideMessages();
	  $http({
		  method: 'DELETE',
		  url: '/todo',
		  data: id
		}).then(function successCallback(response) {
			if(response.data !=null && response.data == true){
				$scope.init();
				$scope.showSuccessFunc("Selected Todo deleted successfully.");
			}else{
				$scope.showErrorFunc( "Unable to delete selecte TODO.");
			}
		  }, function errorCallback(response) {
			  $scope.showErrorFunc( "Unable to delete selecte TODO.");
		  });
  }
  $scope.todoCompleted = function(id){
	 $scope.hideMessages();
	  var arrayTodo = [];
	  arrayTodo.push(id);
	  $http({
		  method: 'PUT',
		  url: '/todo',
		  data: arrayTodo
		}).then(function successCallback(response) {
			if(response.data !=null && response.data == true){
				$scope.init();
				$scope.showSuccessFunc("Selected Todo marked as completed.");
			}else{
				$scope.showErrorFunc( "Unable to udpate Completed status for Selected Todo.");
			}
		  }, function errorCallback(response) {
			  $scope.showErrorFunc( "Unable to udpate Completed status for Selected Todo.");
		  });
  }
  
  
  $scope.markAllCompleted = function(){
	  var idArray = [];
	  angular.forEach($scope.todoList, function (item) {
		  if(item.isCompleted == false){
			  idArray.push(item.id);
		  }
	  });
	  $http({
		  method: 'PUT',
		  url: '/todo',
		  data: idArray
		}).then(function successCallback(response) {
			if(response.data !=null && response.data == true){
				$scope.init();
				$scope.showSuccessFunc("All Todo marked as completed.");
			}else{
				$scope.showErrorFunc( "Unable to update Completed status for All Todo.");
			}
		  }, function errorCallback(response) {
			  $scope.showErrorFunc( "Unable to udpate Completed status for All Todo.");
		  });
  }

	//create task
	$scope.createTodo = function(){
		 $scope.hideMessages();
		 $http({
			  method: 'POST',
			  url: '/todo',
			  data: {todoText:angular.copy($scope.todoTextValue),isCompleted:false}
			}).then(function successCallback(response) {
				if(response.data !=null){
					$scope.todoList.push(response.data);
					$scope.showSuccessFunc("New Todo Added Successfully");
				}
			  }, function errorCallback(response) {
				  $scope.showErrorFunc("Unable to create new Todo. Internel Error occured.")
			  });
//		$scope.todoList.push({todoText:angular.copy($scope.todoTextValue),isCompleted:true});
		$scope.todoTextValue = '';
	}
	$scope.init();
}]);