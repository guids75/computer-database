<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="css/myApp.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
  		<script src="js/jquery.min.js"></script>
  		<script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <p>Choose the action you want to do with the companies :</p>
	        <form action="companiesMenuSubmit" method="POST">
	    		<div>
	        	<button type="submit" class="btn btn-primary" name="companiesAction" value="listCompanies">List of the companies</button>
	    		</div>
			</form>
        </p>
    </body>
</html>
