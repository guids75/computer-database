<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="css/myApp.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
       <!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"> -->
  		<script src="js/jquery.min.js"></script>
  		<script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <p>Choose the action you want to do with the computers :</p>
	        <form action="computersMenuSubmit" method="POST">
	    		<div>
	        	<button type="submit" class="btn btn-primary" name="computersAction" value="listComputers">List of the computers</button>
	        	<button type="submit" class="btn btn-primary" name="computersAction" value="addComputer">Add a computer</button>
	        	<button type="submit" class="btn btn-primary" name="computersAction" value="updateComputer">Update a computer</button>
	        	<button type="submit" class="btn btn-primary" name="computersAction" value="deleteComputer">Delete a computer</button>
	    		</div>
			</form>
        </p>
    </body>
</html>