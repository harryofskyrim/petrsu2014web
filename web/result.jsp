<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>

<%@page import="ru.petrsu.imo.InitFoldClass"%>
<%@page import="ru.petrsu.imo.Student"%>
<%@page import="ru.petrsu.imo.Utils"%>
<jsp:useBean id="InitFoldClass" class="ru.petrsu.imo.InitFoldClass">
    <jsp:setProperty name="InitFoldClass" property="year" value="${param.txtYear}"/>
    <jsp:setProperty name="InitFoldClass" property="group" value="${param.txtGroup}"/>
</jsp:useBean>

<!DOCTYPE html>
    <br>   
    
    <% 
	List<Student> students = new ArrayList<Student>();
	List<File> allFiles = InitFoldClass.getFiles();
	for (File file : allFiles) {
	    Student student = new Student(file.getPath());
	    if (Utils.isStudentForSearch(student, 
		    request.getParameter("txtStudent"), request.getParameter("txtKeyphrase"), 
		    request.getParameter("teacher"))) {
		if (request.getParameter("mustindex") != null) {
		    if (student.isIndex())
			students.add(student);
		} else 
		    students.add(student);
	    }
	}

	pageContext.setAttribute("students", students);
    %>

    <c:choose>
      <c:when test="${empty students}">
	<h2>Ничего не найдено по вашему запросу</h2>  
      </c:when>
      <c:otherwise>
	

    <h2>Работы, найденные по вашему запросу (всего ${fn:length(students)}):</h2>
    
    <table border="1">
            <tr>
                <th>№</th>
                <th>Название работы</th>
                <th>Студент</th>
                <th>Преподаватель</th>
                <th>Год</th>
                <th>Курс</th>
                <th>Группа</th> 
                <th>Пр. отчет</th> 
                <th>Пр. ЭП</th> 
                <th>Отчет</th>
                <th>ЭП</th>
            </tr>            
        <%
            int i = 0;  
        %> 
	    <c:forEach var="student" items="${students}">
                <tr>
                    <td><%=i+1%></td>
                    <td>${student.workTitle}</td>
                    <td><a href="${student.linkToPath}" target="_blank">${student.studentName}</a></td>
                    <td>${student.adviserName}</td>
                    <td>${student.year}</td>
                    <td>${student.course}</td>
                    <td>${student.group}</td>
                    <td>${student.linkToSemiReport}</td>
                    <td>${student.linkToInterimPres}</td>
                    <td>${student.linkToReport}</td>
                    <td>${student.linkToPres}</td>
                    <%i = i + 1;%>
                </tr>
	    </c:forEach>      
	</c:otherwise>
    </c:choose>
    </table>
