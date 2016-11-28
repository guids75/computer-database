<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

<body>			
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${pages.nbElements} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name"
							value="<c:if test='not empty ${search}'>${search}</c:if>" /><input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th id="name" onclick="$.fn.orderBy('name');">Computer name</th>
						<th id="introduced" onclick="$.fn.orderBy('introduced');">Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th id="discontinued" onclick="$.fn.orderBy('discontinued');">Discontinued date</th>
						<!-- Table header for Company -->
						<th id="company" onclick="$.fn.orderBy('company');">Company</th>

						<tag:pageTag />
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<tag:linkTag target="dashboard" actualPage="${pages.actualPage-1}"
					nbElementsByPage="${pages.nbElementsByPage}" search="${search}"
					pageHandler="-1" />
				<tag:linkTag target="dashboard" actualPage="${pages.actualPage}"
					nbElementsByPage="${pages.nbElementsByPage}" search="${search}"
					pageHandler="0" />
				<tag:linkTag target="dashboard" actualPage="${pages.actualPage+1}"
					nbElementsByPage="${pages.nbElementsByPage}" search="${search}"
					pageHandler="1" />
			</ul>


			<div class="btn-group btn-group-sm pull-right" role="group">
				<tag:linkTag target="dashboard" actualPage="1" nbElementsByPage="10"
					search="${search}" />
				<tag:linkTag target="dashboard" actualPage="1" nbElementsByPage="50"
					search="${search}" />
				<tag:linkTag target="dashboard" actualPage="1"
					nbElementsByPage="100" search="${search}" />
			</div>
		</div>
	</footer>



</body>
</html>