<!DOCTYPE html>

<!-- define angular app -->
<html ng-app="urlShortenerApp">

<head>
  <!-- SCROLLS -->
  <link rel="stylesheet" href="/css/bootstrap.min.css" />
  <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" />
 <script src="/js/jquery-3.3.1.js" ></script>
 <script src="/js/jquery.dataTables.min.js"></script>
 <link rel="stylesheet" type="text/css" href="/css/jquery.dataTables.min.css">
 <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
  <!-- SPELLS -->
  <script src="/js/angular.min.js"></script>
  <script src="/js/angular-route.js"></script>
  <script src="/js/todoController.js" ></script>
  <script src="/js/callUrlController.js" ></script>
  <script src="/js/addUrlController.js" ></script>
  <script src="/js/viewUrlsController.js" ></script>
    <script src="/js/graphController.js" ></script>
</head>

<!-- define angular controller -->
<body ng-controller="mainController">

  <nav class="navbar navbar-default">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand" href="/">Url Shortener</a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="#callUrl"><i class="fa fa-shield"></i> Url Call</a></li>
        <li><a href="#viewUrls"><i class="fa fa-table"></i> View Urls</a></li>
        <li><a href="#graph"><i class="fa fa-align-left"></i> Graph</a></li>
      </ul>
    </div>
  </nav>

  <div id="main">
  
    <!-- angular templating -->
		<!-- this is where content will be injected -->
    <div ng-view></div>
    
  </div>
  
  <footer class="text-center">
    <p>Single Page url shortener</p>
  
  </footer>
  
</body>

</html>
