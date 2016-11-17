<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page import="com.excilys.formation.computerdatabase.model.Company" %>
<%@ page import="java.util.List" %>

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
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            ${numberCompanies} Companies found
            </h1>
            <tag:pageTag nbElementsByPage="10" actualPage="1" nbPages="5" />
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
			</div>
        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->
                        <th>
                            Company name
                        </th>
 
				<c:forEach var="company" items="${listCompanies}" >
                <!-- Browse attribute computers -->
                <tbody id="results">
                    <tr>
                        <td>
                            <a href="editComputer.html" onclick=""><c:out value="${company.name}" /></a>
                        </td>
                    </tr>
                    <tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href=<tag:linkTag target="companiesMenuSubmit" page="${actualPage-1}" nbElements="10"/> aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach var="page" begin="1" end="${numberPages}" >
	              <li><a href=<tag:linkTag target="companiesMenuSubmit" page="${page}"/>>${page}</a></li>
	          </c:forEach>
              <li>
                 <a href=<tag:linkTag target="companiesMenuSubmit" page="${actualPage+1}" nbElements="10"/> aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
		
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default" name="nbElements" onclick="window.location.href=<tag:linkTag target="companiesMenuSubmit" page="1" nbElements="10"/>;">10</button>
            <button type="button" class="btn btn-default" name="nbElements" onclick="window.location.href=<tag:linkTag target="companiesMenuSubmit" page="1" nbElements="50"/>;">50</button>
            <button type="button" class="btn btn-default" name="nbElements" onclick="window.location.href=<tag:linkTag target="companiesMenuSubmit" page="1" nbElements="100"/>;">100</button>
        </div>
</div>
    </footer>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/dashboard.js"></script>

</body>
</html>