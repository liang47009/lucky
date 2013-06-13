<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="/resources/style/global.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/style/font.css" rel="stylesheet" type="text/css" />
<link href="/resources/Scripts/jquery/themes/base/jquery.ui.core.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/Scripts/jquery/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/Scripts/jquery/themes/base/jquery.ui.tabs.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/Scripts/jquery/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/Scripts/jquery/themes/base/jquery.ui.theme.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="/resources/Scripts/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="/resources/Scripts/jquery/ui/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/resources/Scripts/jquery/ui/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/resources/Scripts/jquery/ui/ui/jquery.ui.tabs.js"></script>
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
			<li><a href="/hot" target="tab">火热进行中</a></li>
			<li><a href="/start" target="tab">即将开始</a></li>
			<li><a href="/sus" target="tab">成功领取</a></li>
			<li><a href="/rule" target="tab">规则说明</a></li>
		</ul>
	</div>
	<iframe name="tab" width="100%" height="500" frameborder="0" src="/hot"></iframe>
</body>
</html>