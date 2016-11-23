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

${search} iciiiiiiii 

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" value="<c:if test='not empty ${search}'>${search}</c:if>"/><input
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
							style="vertical-align: top;"> - <a href="deleteComputer"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

						<c:forEach var="computer" items="${listComputers}">
							<!-- Browse attribute computers -->
							<tbody id="results">
								<tr>
									<td class="editMode"><input type="checkbox" name="cb"
										class="cb" value="${computer.id}"></td>

									<c:choose>
										<c:when test="${not empty computer.introduced}">
											<c:set var="introduced" value="&introduced=${computer.introduced}" />
										</c:when>
										<c:otherwise>
											<c:set var="introduced" value="" />
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${not empty computer.discontinued}">
											<c:set var="discontinued" value="&discontinued=${computer.discontinued}" />
										</c:when>
										<c:otherwise>
											<c:set var="discontinued" value="" />
										</c:otherwise>
									</c:choose>
									
									<td><a
										href="editComputer?id=${computer.id}&
									computerName=${computer.name}&introduced=${computer.introduced}
									&discontinued=${computer.discontinued}&companyId=${computer.companyId}"
										onclick="$('#cb').submit();"><c:out
												value="${computer.name}" /></a></td>
									<td><c:out value="${computer.introduced}" /></td>
									<td><c:out value="${computer.discontinued}" /></td>
									<td><c:out value="${computer.companyName}" /></td>
								</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${pages.hasPrev() == true}">
					<li><a
						href=<tag:linkTag target="dashboard" page="${pages.actualPage-1}" nbElements="${pages.nbElementsByPage}"/>
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<c:choose>
					<c:when test="${pages.actualPage - 2 < 1}">
						<c:set var="begin" value="${1}" />
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${pages.actualPage - 2}" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pages.actualPage + 2 > pages.nbPages}">
						<c:set var="begin" value="${pages.nbPages}" />
					</c:when>
					<c:otherwise>
						<c:set var="end" value="${pages.actualPage + 2}" />
					</c:otherwise>
				</c:choose>

				<c:forEach var="page" begin="${begin}" end="${end}">
					<li><a
						href=<tag:linkTag target="dashboard" page="${page}" nbElements="${pages.nbElementsByPage}"/>>${page}</a></li>
				</c:forEach>
				<c:if test="${pages.hasNext() == true}">
					<li><a
						href=<tag:linkTag target="dashboard" page="${pages.actualPage+1}" nbElements="${pages.nbElementsByPage}"/>
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default" name="nbElements"
					onclick='window.location.href=<tag:linkTag target="dashboard" page="1" nbElements="10"/>;'>10</button>
				<button type="button" class="btn btn-default" name="nbElements"
					onclick='window.location.href=<tag:linkTag target="dashboard" page="1" nbElements="50"/>;'>50</button>
				<button type="button" class="btn btn-default" name="nbElements"
					onclick='window.location.href=<tag:linkTag target="dashboard" page="1" nbElements="100"/>;'>100</button>
			</div>
		</div>

	</footer>

</body>
</html>