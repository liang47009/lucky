<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>start</title>

<link href="/resources/style/global.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/style/font.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<c:forEach begin="0" items="${start}" var="s">
		<div class="card">
			<span class="title">${s.name}</span> <img src="/resources/${s.url}"
				width="160" height="120" />
			<%-- <a href="/detail?pid=${s.id}">
				<img src="/resources/${s.url}" width="160" height="120" />
			</a> --%>
			<div>
				开奖份数 : <span id="giftNum">${s.count}</span>
			</div>
			<div style="visibility: visible">
				<div class="esTime">${s.duration}</div>
				<input name="buy" type="button" value="${s.cast}Y币领取一份">
				<%-- <input name="buy" type="button" value="${s.cast}Y币领取一份"
					onclick="javascript:b(${s.id});return false;" /> --%>
			</div>
		</div>
	</c:forEach>
</body>
</html>