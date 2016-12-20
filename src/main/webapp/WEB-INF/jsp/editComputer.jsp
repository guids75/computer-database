<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<c:set var="path" value="${pageContext.request.contextPath}/resources" />
<link href="${path}/css/validator.css" rel="stylesheet" media="screen">
<link href="${path}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${path}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${path}/css/main.css" rel="stylesheet" media="screen">
<link href="${path}/css/jquery-ui.css" rel="stylesheet" media="screen">
<script src="${path}/js/jquery.min.js"></script>
<script src="${path}/js/bootstrap.min.js"></script>
<script src="${path}/js/dashboard.js"></script>
<script src="${path}/js/jquery.validate.min.js"></script>
<script src="${path}/js/computerDate.js"></script>
<script src="${path}/js/jquery-ui.js"></script>


</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<div>
		<textarea name="hideError" ${hide}>${errors}</textarea>
	</div>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<springForm:form action="editComputer" method="POST"
						class="computerValidatorForm" modelAttribute="computerDto">
						<input type="hidden" value="${computer.id}" id="id" name="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label>
								<springForm:input type="text" class="form-control" id="name"
									placeholder="${computer.name}" name="name"
									value="${computer.name}" path="name" />
								<springForm:errors path="name" cssClass="alert alert-danger" />
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<springForm:input type="date" class="form-control"
									id="introduced" placeholder="${computer.introduced}"
									name="introduced" value="${computer.introduced}"
									path="introduced" />
								<springForm:errors path="introduced"
									cssClass="alert alert-danger" />
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<springForm:input type="date" class="form-control"
									id="discontinued" placeholder="${computer.discontinued}"
									name="discontinued" value="${computer.discontinued}"
									path="discontinued" />
								<springForm:errors path="discontinued"
									cssClass="alert alert-danger" />
							</div>
							<div class="form-group">
								<label for="companyId">Company</label>
								<springForm:select class="form-control" id="companyId"
									name="companyId" path="companyId">
									<c:forEach var="company" items="${listCompanies}">
										<c:choose>
											<c:when test="${company.id == computer.companyId}">
												<springForm:option name="company" value="${company.id}"
												path="companyId" selected="true">${company.name}</springForm:option>
											</c:when>
											<c:otherwise>
												<springForm:option name="company" value="${company.id}"
													path="companyName">${company.name}</springForm:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</springForm:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</springForm:form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>