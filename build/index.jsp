<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="ru.petrsu.imo.Utils"%>

<!DOCTYPE html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="http://www.cs.petrsu.ru/style/cs.css" />
        <title>index</title>
        <script type="text/javascript" src="jquery-1.9.1.min.js"></script>
        <script type="text/javascript">
            function getResults() {
                jQuery.get('result.jsp',$('#f').serialize(), function(data) { 
                    $('#results').html(data);
                });
            }
	    function changeYear() {
                jQuery.get('getgroups.jsp',$('#f').serialize(), function(data) { 
                    $('#group').html(data);
                });
	    }

	    $(document).ready(function() {
                $('#btnSearchKeyphrase').click(getResults);
		$('#txtYear').change(changeYear);
		changeYear();
            });
        </script>
    </head>
    <% 
	List<String> years = Utils.getYears();
	pageContext.setAttribute("years", years);
	
	String currentYear = Utils.getCurrentYear();
	pageContext.setAttribute("cYear", currentYear);
	
    %>
    
    <body>
<div align="center" class="header">
	<img src="http://www.cs.petrsu.ru/style/cs_logo.png" align="left" alt="" />
	<a href="http://www.cs.petrsu.ru/index.php.ru"><img src="http://www.cs.petrsu.ru/style/cs_title_ru.png" alt="Кафедра Информатики и Математического Обеспечения" border="0" /></a><br />
	<a href="http://www.petrsu.ru">ПетрГУ</a> |
	<a href="http://www.cs.petrsu.ru/department/index.php.ru">О кафедре</a> |
	<a href="http://maemo.cs.karelia.ru">Мобильные платформы</a> |
	<a href="http://www.cs.petrsu.ru/lab/index.php.ru">Лаборатория ИТС</a> |
	<a href="http://www.cs.petrsu.ru/filial/index.php.ru">Филиал в КарНЦ РАН</a> |
	<a href="http://www.cs.petrsu.ru/fdpw/index.php.ru">Семинары НФИ/AMICT</a><br />
	<a href="http://www.cs.petrsu.ru/staff/index.php.ru">Сотрудники</a> |
	<a href="http://alumni.karelia.ru/?a=lst&amp;d=imo">Выпускники</a> |
	<a href="http://www.cs.petrsu.ru/studies/index.php.ru">Учебный процесс</a> |
	<a href="http://www.cs.petrsu.ru/studies/kurs/kurs.php.ru">Курсовые и выпускные работы</a> |
	<a href="http://www.cs.petrsu.ru/facilities/index.php.ru">Вычислительные ресурсы</a><br/>
	<a href="http://www.cs.petrsu.ru/publications/index.php.ru">Публикации</a> |
	<a href="http://www.cs.petrsu.ru/news/index.php.ru">Архив новостей</a> |
	<a href="http://www.cs.petrsu.ru/contact/index.php.ru">Контактная информация</a> |
	<a href="http://www.cs.petrsu.ru/search/index.php.ru">Поиск</a>
	<br clear="all" />
</div>
<div class="content">
	<hr />
        <h1>Поиск и демонстрация курсовых работ</h1>
           <form id="f" >
                <table class="transparent">
                  <tr>
		    <td>Календарный год начала работы</td>
		    <td>
			<select name="txtYear" id="txtYear">
                            <option value="all" id="all">Все</option>
			    <c:forEach var="year" items="${years}">
                                <option value="${year}" id="${year}">${year}</option>
			    </c:forEach>      
			</select>
		    </td>
                  </tr>
                  <tr>
                    <td>Группа</td>
		    <td>
			<select name="txtGroup" id="group">
			</select>
		    </td>
                  </tr>
                  <tr>
                    <td>Фамилия студента</td>
                    <td><input type="text" name="txtStudent"/></td>
                  </tr>
                    <tr>
                        <td>Ключевая фраза</td>
                        <td><input type="text" name="txtKeyphrase"/></td>
                    </tr>
		    <tr>
                        <td>Преподаватель</td>
                        <td><input type="text" name="teacher"/></td>
                    </tr>
                  <tr>
                    <td>Наличие index.xml</td>
		    <td><input type="checkbox" name="mustindex" value="index"></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td><input type="button" id="btnSearchKeyphrase" value="Найти"  />
                  </tr>
                </table>  
                <!--table>
                    <tr>
                        <td>Отчет за год</td>
                        <td>
                            <select>
                            </select>
                        </td>
                        <td><input type="button" value="Сформировать отчет"/></td>
                    </tr>
                    <tr>
                        <td>Отчет по преподавателю</td>
                        <td><input type="text"/></td>
                        <td><input type="button" value="Сформировать отчет"/></td>
                    </tr>
                </table-->
            <div id="results"></div>
         </form>
    </body>
