<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>

<%@page import="ru.petrsu.imo.Utils"%>

<!DOCTYPE html>
    
    <% 
	List<String> groups = Utils.getGroups(request.getParameter("txtYear"), "*");

	pageContext.setAttribute("groups", groups);
    %>


    <c:choose>
      <c:when test="${empty groups}">
      </c:when>
      <c:otherwise>
	<option value="all" id="all_group">Все</option>
	<c:forEach var="group" items="${groups}">
	    <option value="${group}" id="${group}">${group}</option>
	</c:forEach>
      </c:otherwise>
    </c:choose>
