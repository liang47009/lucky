<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火热进行中</title>
<link href="/resources/style/global.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/style/font.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<c:forEach begin="0" items="${hot}" var="props">
		<div class="card">
			<span class="title">${props.name}</span> <a
				href="/detail?pid=${props.id}"> <img
				src="/resources/${props.url}" width="160" height="120" />
			</a>
			<div>
				开奖份数 : <span id="giftNum">${props.count}</span>
			</div>
			<div style="visibility: visible">
				<div class="esTime">${props.duration}</div>
				<a href="/buyProps?pid=${props.id}"><input name="buy"
					type="button" value="${props.cast}Y币领取一份" /></a>
			</div>
		</div>
	</c:forEach>
</body>
</html>