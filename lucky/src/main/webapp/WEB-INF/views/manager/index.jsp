<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理</title>

<style type="text/css">
.tab ul {
	list-style: none;
}

.tab li {
	float: left;
	margin: 10px;
}
</style>
</head>
<body>
	<div class="tab">
		<ul>
			<li><a href="/manager/publish" target="tab">发布</a></li>
			<li><a href="/manager/manage" target="tab">管理</a></li>
			<li><a href="/manager/email" target="tab">邮件</a></li>
		</ul>
	</div>
	<iframe name="tab" width="100%" height="500" frameborder="0" src="/manager/publish"></iframe>
</body>
</html>