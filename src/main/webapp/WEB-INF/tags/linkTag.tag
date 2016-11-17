<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="nbElements" required="false" type="java.lang.Integer" %>

<c:choose>
  <c:when test="${not empty nbElements}">
  'http://localhost:8084/computer-database/${target}?page=${page}&nbElements=${nbElements}'
  </c:when>
  <c:otherwise>
  'http://localhost:8084/computer-database/${target}?page=${page}&nbElements=10'
  </c:otherwise>
</c:choose>