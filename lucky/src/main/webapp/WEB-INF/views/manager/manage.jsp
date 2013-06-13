<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理</title>
</head>
<body>
	<table border="1">
		<tbody>
			<tr>
				<td>卡号名称</td>
				<td>创建时间</td>
				<td>开始时间</td>
				<td>参与人数</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach begin="0" items="${m }" var="md">
				<tr>
					<td>${md.name }</td>
					<td>${md.ct }</td>
					<td>${md.start }</td>
					<td>${md.count }</td>
					<c:if test="${md.state==0 }">
						<td>尚未开始</td>
						<td><a href="/manager/delProps?pid=${md.id}">删除</a>|<a
							href="/manager/editProps?pid=${md.id}">编辑</a></td>
					</c:if>
					<c:if test="${md.state==1 }">
						<td>正在进行</td>
						<td></td>
					</c:if>
					<c:if test="${md.state==2 }">
						<td>已经结束</td>
						<td><a href="/manager/delProps?pid=${md.id}">删除</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>