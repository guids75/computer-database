<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<c:set var="path" value="${pageContext.request.contextPath}/resources" />
<link href="${path}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${path}/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="${path}/css/main.css" rel="stylesheet" media="screen">
<script src="${path}/js/jquery.min.js"></script>
<script src="${path}/js/bootstrap.min.js"></script>
<script src="${path}/js/dashboard.js"></script>
<script src="${path}/js/internationalizationStrings.js"></script>
<body>

	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><spring:message
					code="label.application" /> </a> <span style="float: right"> <a
				href="?lang=en"><img src="${path}/images/englishFlag.png"
					width="45" height="25" />en</a> | <a href="?lang=fr"><img
					src="${path}/images/frenchFlag.png" width="45" height="25" />fr</a>
			</span>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${pages.nbElements}
				<spring:message code="label.found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message
					code="label.searchName" />"
							value="<c:if test='not empty ${search}'>${search}</c:if>" /><input
							type="submit" id="searchsubmit"
							value="<spring:message
					code="label.filterByName" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="label.addComputer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="label.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>
		<form id="orderForm" action="dashboard" method="GET">
			<input type="hidden" name="order" value="">
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



						<th><a id="name" onclick="$.fn.orderBy('computer_name');"><spring:message
									code="label.computerName" /><span aria-hidden="true"></span> </a></th>
						<th><a id="introduced"
							onclick="$.fn.orderBy('computer_introduced');"><spring:message
									code="label.introduced" /><span aria-hidden="true"></span> </a></th>
						<!-- Table header for Discontinued Date -->
						<th><a id="discontinued"
							onclick="$.fn.orderBy('computer_discontinued');"><spring:message
									code="label.discontinued" /><span aria-hidden="true"></span> </a></th>
						<!-- Table header for Company -->
						<th><a id="company" onclick="$.fn.orderBy('company_name');"><spring:message
									code="label.company" /><span aria-hidden="true"></span> </a></th>

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