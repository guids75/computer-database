<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/validator.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/jquery-ui.css" rel="stylesheet" media="screen">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/computerValidator.js"></script>
<script src="js/jquery-ui.js"></script>

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
		<c:choose>
		<c:when test='${empty errors}'>
			<c:set var="hide" value="visibility:hidden" />
		</c:when>
		<c:otherwise></c:otherwise>
		</c:choose>
		
		<div>
		<textarea name="hideError" ${hide}>${errors}</textarea>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST" class="computerValidatorForm">
						<input type="hidden" value="${computer.id}" id="id" name="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label><input
									type="text" class="form-control" id="computerName"
									placeholder="${computer.name}" name="computerName" value="${computer.name}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label><input
									type="date" class="form-control" id="introduced"
									placeholder="${computer.introduced}" name="introduced" value="${computer.introduced}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label><input
									type="date" class="form-control" id="discontinued"
									placeholder="${computer.discontinued}" name="discontinued" value="${computer.discontinued}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="company" items="${listCompanies}">
										<c:choose>
											<c:when test="${company.id == computer.companyId}">
												<option name="company" value="${company.id}" selected>${company.name}</option>
											</c:when>
											<c:otherwise>
												<option name="company" value="${company.id}">${company.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>