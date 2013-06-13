<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>email</title>
</head>

<body>
	<form name="ef" action="/manager/addEmail" method="post">
		<input type="hidden" name="id" value="${e.id}">
		<table>
			<tbody>
				<tr>
					<td>邮箱地址:</td>
					<td><input name="username" type="text" value="${e.username}"></td>
				</tr>
				<tr>
					<td>邮箱密码:</td>
					<td><input name="password" type="password" value="${e.password}"></td>
				</tr>
				<tr>
					<td>激活码替换字符:</td>
					<td><input name="activation" type="text" value="${e.activation}"></td>
				</tr>
				<tr>
					<td>激活链接替换字符:</td>
					<td><input name="url" type="text" value="${e.url}"></td>
				</tr>
				<tr>
					<td>邮箱标题:</td>
					<td><input name="subject" type="text" value="${e.subject}"></td>
				</tr>
				<tr>
					<td>邮箱内容:</td>
					<td><textarea name="context" cols="50" rows="10">${e.context}</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="确定" ></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
