<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
		<script src="<c:url value="/js/jquery-3.3.1.min.js" />" ></script>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="<c:url value="/js/angular.min.js"/>"></script>

	<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

	<link href="<c:url value="/css/bootstrap.min.css"/> " rel="stylesheet" id="bootstrap-css">
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/jquery-1.11.1.min.js" />"></script>
<!------ Include the above in your HEAD tag ---------->

<script type="text/javascript" src="<c:url value="/js/jquery-ui.min.js" />"></script>
</head>
<body ng-app="myApp">

<div class="container"  ng-controller="todoController">
    <div class="row">
        <div class="col-md-6">
            <div class="todolist not-done">
             <h1>Todos</h1>
             <div ng-if="showSuccess" class="alert alert-success">
				  <strong>Success!</strong> {{successText}}
				</div>
				<div ng-if="showError" class="alert alert-danger">
				  <strong>Danger!</strong> {{errorText}}
				</div>
                <input ng-model="todoTextValue" type="text" class="form-control add-todo" placeholder="Add todo" ng-keypress="todoKeyPress($event)">
                    <button id="checkAll" class="btn btn-success" ng-click = "markAllCompleted()">Mark all as done</button>
                    
                    <hr>
                    <ul id="sortable" class="list-unstyled">
                    <li class="ui-state-default"  ng-repeat="todo in todoList |  filter:{ isCompleted: false} as results">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" ng-click="todoCompleted(todo.id)" value="" />{{todo.todoText}}
                            </label>
                        </div>
                    </li>
                </ul>
                <div class="todo-footer">
                    <strong><span class="count-todos">{{results.length}}</span></strong> Items Left
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="todolist">
             <h1>Already Done</h1>
                <ul id="done-items" class="list-unstyled">
                    <li ng-repeat="todo in todoList |  filter:{ isCompleted: true}">{{todo.todoText}} 
                    	<button ng-click="deleteTodo(todo.id)" class="remove-item btn btn-default btn-xs pull-right"><span class="glyphicon glyphicon-remove"></span></button>
                    </li>
                    
                </ul>
            </div>
        </div>
    </div>
</div>
	
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<c:url value="/js/todoController.js" />" ></script>
</body>
<script>
$("#sortable").sortable();
$("#sortable").disableSelection();
</script>
</html>
