<div class="jumbotron text-center">
	
	<h1>Call Short Url</h1>
	<div ng-show="success" class="alert alert-success">
	  <strong>Success!</strong> {{responseMessage}}
	</div>
	<div ng-show ="failure" class="alert alert-danger">
	  <strong>Danger!</strong> {{responseMessage}}.
	</div>
	 <input ng-model="urlValue">
	<input type="button" ng-click="callUrl()" value="Submit"/>
	<p>{{ urlValue }}</p>
	<p>{{ message }}</p>
</div>