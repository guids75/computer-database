<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<c:set var="path" value="${pageContext.request.contextPath}/resources" />
<link href="${path}/css/validator.css" rel="stylesheet" media="screen">
<link href="${path}/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${path}/css/font-awesome.css" rel="stylesheet"
	media="screen">
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
			<a class="navbar-brand" href="dashboard"><spring:message
					code="label.application" /> </a> <span style="float: right"> <a
				href="?lang=en"><img src="${path}/images/englishFlag.png" width="45" height="25"/>en</a>
				| <a href="?lang=fr"><img src="${path}/images/frenchFlag.png" width="45"
					height="25"/>fr</a>
			</span>
		</div>
	</header>

	<c:set var="val">
		<spring:message code="label.datePattern" />
	</c:set>
	<input id="datePattern" type="hidden" value="${val}" />

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message
					code="label.addComputer" /></h1>
					<springForm:form action="addComputer" method="POST"
						modelAttribute="computerDto" class="computerValidatorForm">
						<fieldset>
							<div class="form-group">
								<label for="name"><spring:message
					code="label.computerName" /></label>
								<springForm:input type="text" class="form-control" id="name"
									placeholder="Computer name" name="name" path="name" />
								<springForm:errors path="name" cssClass="alert-danger" />
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
					code="label.introduced" /></label>
								<springForm:input type="date" class="form-control"
									id="introduced" placeholder="Introduced date" name="introduced"
									path="introduced" />
								<springForm:errors path="introduced" cssClass="alert-danger" />
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
					code="label.discontinued" /></label>
								<springForm:input type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date"
									name="discontinued" path="discontinued" />
								<springForm:errors path="discontinued" cssClass="alert-danger" />
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
					code="label.company" /></label>
								<springForm:select class="form-control" id="companyId"
									name="companyId" path="companyId">
									<c:forEach var="company" items="${listCompanies}">
										<springForm:option name="company" value="${company.id}"
											path="companyName">${company.name}</springForm:option>
									</c:forEach>
								</springForm:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value='<spring:message
					code="label.add" />' class="btn btn-primary" /> <spring:message
					code="label.or" /> <a
								href="dashboard" class="btn btn-default"><spring:message
					code="label.cancel" /></a>
						</div>
					</springForm:form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>