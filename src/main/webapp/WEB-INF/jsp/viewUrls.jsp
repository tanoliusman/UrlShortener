


<div class="text-center" ng-init="init()">
	<h1>Urls</h1>
	
	<table id="example" class="display" style="width:100%;text-align:=left;">
        <thead>
            <tr>
            	<th>Live Chart</th>
                <th>Id</th>
                <th>Url</th>
                <th>Short Url</th>
                <th>Expiry</th>
                <th> &nbsp;</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>