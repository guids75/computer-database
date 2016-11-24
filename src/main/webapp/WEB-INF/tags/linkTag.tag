<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="actualPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="nbElementsByPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>

<c:if test="not empty ${search}">

</c:if>

				<c:choose>
					<c:when test="${not empty search}">
						"${target}?actualPage=${actualPage}&nbElementsByPage=${nbElementsByPage}&search=${search}"
					</c:when>
					<c:otherwise>
						"${target}?actualPage=${actualPage}&nbElementsByPage=${nbElementsByPage}"
					</c:otherwise>
				</c:choose>