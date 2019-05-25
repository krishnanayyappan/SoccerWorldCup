<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card Stats</title>
</head>
<body>
	<div class="main">
		<div class="header">
			<div class="header_resize">

				<!-- TO MAKE THE URL REFERENCES WORK YOU MUST HAVE SESSION ID DISABLED IN URL - SEE WEB.XML -->

				<div class="logo">
					<h1 style="width: 193px;">
						<a href="<c:url value='/' />"><img alt=""
							src="FIFA_World_Cup.png" style="width: 203px; height: 234px;"></a>
					</h1>
					<p align="center">
						<a href="welcome.jsp" target="_top"><span>Return to Main Page</span></a> 
					</p>
				</div>
			</div>
		</div>
	</div>
	<h4>View Card Stats - Question B</h4>
	<form name="CardForm" action="CardStats" method="post">
		<table>
			<tbody>
				<tr>
					<td style="max-width: 50px;">Team Name</td>
					<td><input id="teamname" type="text" name="teamname" value=""
						size="30" placeholder="Type here" /></td>
					<td><input id="startdateError" name="startdateError"
						value="${CSerrorMsgs.teamnameError}" type="text"
						style="background-color: white; color: red; border: none; width: 400px"
						disabled="disabled" maxlength="60"></td>
				</tr>
				<tr>
					<td>Cards</td>
					<td><input id="red" type="checkbox" name="red" value="Y"
						size="30" />Red Card <input id="yellow" type="checkbox"
						name="yellow" value="Y" size="30" />Yellow Card</td>
					<td><input id="colorError" name="colorError"
						value="${CSerrorMsgs.colorError}" type="text"
						style="background-color: white; color: red; border: none; width: 400px"
						disabled="disabled" maxlength="60"></td>
				</tr>

			</tbody>
		</table>

		<input id="reset" type="reset" value="Clear" name="clear"
			style="width: 105px;"> <input id="submit" type="submit"
			value="Submit" name="submit" style="width: 105px;" />
	</form>

	<div class="mainbar">
		<div class="submb"></div>
	</div>

	<table class="myTable" border="1">
		<tr class="myTableRow">
			<th class="myTableHead" width="50" align="center">Game ID</th>
			<th class="myTableHead" width="200" align="center">Player Name</th>
			<th class="myTableHead" width="100" align="center">Card Color</th>
		</tr>

		<c:forEach items="${CARDS}" var="item">
			<tr class="myTableRow">
				<td class="myTableCell" width="50" align="center"><c:out
						value="${item.gameid}" /></td>
				<td class="myTableCell" width="200" align="center"><c:out
						value="${item.pname}" /></td>
				<td class="myTableCell" width="100" align="center"><c:out
						value="${item.color}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>