<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>My first column chart by Highcharts</title>
        <!-- 1. Add JQuery and Highcharts in the head of your page -->
        <script type="text/javascript" src="/js/jquery.min.js"></script>
        <script src="/js/highstock.js"></script>
		<script src="/js/exporting.js"></script>
		<script src="/js/export-data.js"></script>
		<script src="/js/sockjs.min.js"></script>
		<script src="/js/stomp.min.js"></script>
        <!-- 3. Add the JavaScript with the Highchart options to initialize the chart -->
        <script type="text/javascript">
        var value = 0;
        var url = "";

             jQuery(document).ready(function() { 
            	 url = getUrlParameter("url");
            	 $("#shortUrl").html(url);
            	 connect();

        	Highcharts.setOptions({
        	  global: {
        	    useUTC: false
        	  }
        	});

        	// Create the chart
        	Highcharts.stockChart('container', {
        	  chart: {
        	    events: {
        	      load: function () {

        	        // set up the updating of the chart each second
        	        var series = this.series[0];
        	        setInterval(function () {
        	          var x = (new Date()).getTime(), // current time
        	            y = value;
        	          series.addPoint([x, y], true, true);
        	          value = 0;
        	        }, 1000);
        	      }
        	    }
        	  },

        	  rangeSelector: {
        	    buttons: [{
        	      count: 1,
        	      type: 'minute',
        	      text: '1M'
        	    }, {
        	      count: 5,
        	      type: 'minute',
        	      text: '5M'
        	    }, {
        	      type: 'all',
        	      text: 'All'
        	    }],
        	    inputEnabled: false,
        	    selected: 0
        	  },

        	  title: {
        	    text: 'Live random data'
        	  },

        	  exporting: {
        	    enabled: false
        	  },

        	  series: [{
        	    name: 'Random data',
        	    data: (function () {
        	      // generate an array of random data
        	      var data = [],
        	        time = (new Date()).getTime(),
        	        i;

        	      for (i = -999; i <= 0; i += 1) {
        	        data.push([
        	          time + i * 1000,
        	          0
        	        ]);
        	      }
        	      return data;
        	    }())
        	  }]
        	});

        }); 
        
        var stompClient = null;

        function connect() {
            var socket = new SockJS('/gs-guide-websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function (greeting) {
                    showGreeting(greeting.body);
                });
            });
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendName() {
            stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
        }

        function showGreeting(message) {
        	var object = jQuery.parseJSON( message).name;
        	if(object == url){
        		value = 1;
        	}
        }

        $(function () {
            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            $( "#connect" ).click(function() { connect(); });
            $( "#disconnect" ).click(function() { disconnect(); });
            $( "#send" ).click(function() { sendName(); });
        });
        
        
        var getUrlParameter = function getUrlParameter(sParam) {
            var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
        };
        </script>
         
    </head>
    <body>
         <h2><span id="shortUrl"></span></h2>
      <div id="container" style="height: 400px; min-width: 310px"></div> 
      
    </body>
</html>