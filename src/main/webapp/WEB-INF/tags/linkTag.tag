<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="nbElements" required="false" type="java.lang.Integer" %>

"${target}?page=${page}&nbElements=${nbElements}"
