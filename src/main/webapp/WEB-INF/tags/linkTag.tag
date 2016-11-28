<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="target" required="true" type="java.lang.String"%>
<%@ attribute name="actualPage" required="true" type="java.lang.Integer"%>
<%@ attribute name="nbElementsByPage" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="pageHandler" required="false"
	type="java.lang.Integer"%>

<%-- Determine the link --%>
<c:choose>
	<c:when test="${not empty search}">
		<c:set var="link"
			value="${target}?actualPage=${actualPage}&nbElementsByPage=${nbElementsByPage}&search=${search}" />
	</c:when>
	<c:otherwise>
		<c:set var="link"
			value="${target}?actualPage=${actualPage}&nbElementsByPage=${nbElementsByPage}" />
	</c:otherwise>
</c:choose>

<c:if test="${pageHandler == 2}">
	${link}
</c:if>

<%-- Determine if it handles the pages or the number of elements by page --%>
<c:choose>

	<%-- Previous --%>
	<c:when test="${pageHandler == -1}">
		<c:if test="${pages.hasPrev() == true}">
			<li><a href="${link}" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
	</c:when>

	<%-- Pages --%>
	<c:otherwise>
		<c:choose>
			<c:when test="${pageHandler == 0}">
				<c:choose>
					<c:when test="${actualPage - 2 < 1}">
						<c:set var="begin" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${actualPage - 2}" />
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${actualPage + 2 > pages.nbPages}">
						<c:set var="begin" value="${pages.nbPages}" />
					</c:when>
					<c:otherwise>
						<c:set var="end" value="${actualPage + 2}" />
					</c:otherwise>
				</c:choose>

				<c:forEach var="page" begin="${begin}" end="${end}">
					<li><a href=<tag:linkTag target="dashboard" actualPage="${page}"
					nbElementsByPage="${nbElementsByPage}"
					search="${search}" pageHandler="2"/>>${page}</a></li>
				</c:forEach>
			</c:when>

			<%-- Next --%>
			<c:otherwise>
				<c:choose>
					<c:when test="${pageHandler == 1}">

						<c:if test="${pages.hasNext() == true}">
							<li><a href="${link}" aria-label="Next"> <span
									aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</c:when>

					<%-- NbElementsByPage --%>
					<c:otherwise>
						<c:if test="${empty pageHandler}">
							<button type="button" class="btn btn-default"
								name="nbElementsByPage"
								onclick='window.location.href="${link}";'>${nbElementsByPage}</button>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
