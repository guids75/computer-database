<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Main Menu</h1>
                    <form action="mainMenuSubmit" method="POST">
                        <fieldset>
                            <div class="form-group">
					        	<button type="submit" class="btn btn-primary" name="entityType" value="companies">Companies</button>
								<button type="submit" class="btn btn-primary" name="entityType" value="computers">Computers</button>
                            </div>                  
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>