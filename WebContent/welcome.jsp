<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body style="width: 450px;">
	<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<h1 style="width: 193px;">
						<a href="<c:url value='/' />"><img alt=""
							src="FIFA_World_Cup.png" style="width: 203px; height: 234px;"></a>
					</h1>
				</div>
				<div class="content">
					<div class="menu_nav">
						<ul>
							<li><a href="teamStats.jsp" target="_top"><span>View
										Team Stats</span></a></li>
							<li><a href="cardStats.jsp" target="_top"><span>View
										Card Details</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>