<%@tag import="com.excilys.formation.computerdatabase.dto.ComputerDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="computer" items="${listComputers}">
	<!-- Browse attribute computers -->
	<tbody id="results">
		<tr>
			<td class="editMode"><input type="checkbox" name="cb" class="cb"
				value="${computer.id}"></td>

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
					<c:set var="discontinued"
						value="&discontinued=${computer.discontinued}" />
				</c:when>
				<c:otherwise>
					<c:set var="discontinued" value="" />
				</c:otherwise>
			</c:choose>

			<td><a
				href="editComputer?id=${computer.id}&
									computerName=${computer.name}&introduced=${computer.introduced}
									&discontinued=${computer.discontinued}&companyId=${computer.companyId}"
				onclick="$('#cb').submit();"><c:out value="${computer.name}" /></a></td>
			<td><c:out value="${computer.introduced}" /></td>
			<td><c:out value="${computer.discontinued}" /></td>
			<td><c:out value="${computer.companyName}" /></td>
		</tr>
</c:forEach>