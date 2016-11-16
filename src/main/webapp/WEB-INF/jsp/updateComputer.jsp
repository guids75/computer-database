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
        <p>Choose the entity you want to manage :</p>
                    <% 
            String attribut = (String) request.getAttribute("entityChosen");
            out.println( 5);
            %>
        </p>
    </body>
</html>