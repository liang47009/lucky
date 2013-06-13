<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sus</title>

<link href="/resources/style/global.css" rel="stylesheet" type="text/css" />
<link href="/resources/style/font.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<c:forEach begin="0" items="${sus}" var="p">
		<div class="card">
			<span class="title"><a href="info.html">${p.name}</a></span> <a
				href="/detail?pid=${p.id}"><img src="/resources/${p.url}" width="160"
				height="120" /></a>
			<div>
				市场价:￥<span id="giftNum">${p.price}</span>
			</div>
			<div style="visibility: visible; padding-top: 20px;">
				恭喜<span class="color_green">${p.username}</span>
			</div>
		</div>
	</c:forEach>
</body>
</html>