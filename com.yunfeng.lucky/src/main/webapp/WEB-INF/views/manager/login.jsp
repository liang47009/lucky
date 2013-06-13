<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理者登录</title>
<script src="/resources/Scripts/md5.js" ></script>
<script>
function s(thisinput) {
	//默认的提交处理，自定义的提交前处理方法不要用submit作为函数名  
	with (thisinput) {
		var hash = b64_md5(thisinput.value);
		thisinput.value = hash;
	}
}
</script>
</head>

<body>
	<form action="/manager/login" onsubmit="submit(); return false;" method="post">
		<span>帐号：<input id="u" name="username" type="text" /><br /></span>
		<span>密码：<input id="p" name="password" type="password" onchange="s(this);"/><br /></span>
		<span><input type="submit" value="登录" /><br /></span>
	</form>
</body>
</html>
