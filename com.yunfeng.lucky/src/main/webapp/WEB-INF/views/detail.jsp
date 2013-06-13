<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幸运领卡</title>
<link href="/resources/style/global.css" rel="stylesheet"
	type="text/css" />
<link href="/resources/style/font.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="5">
			<tr>
				<td width="10%" align="center" valign="top"><img
					src="/resources/${dd.url}" width="160" height="120" /></td>
				<td width="30%" valign="top">
					<div class="color_orange">${dd.name}</div>
					<div>总份数:${dd.totalCount}</div>
					<hr />
					<div>
						已领取份数:${dd.alreadyCount}<span style="margin-left: 20px">已领取人数:${dd.size}</span>
					</div>
					<div>
						最新领取者:&nbsp;<span class="color_green">${dd.lastUserName}</span>
					</div>
					<div class="redBox">
						<div class="esTime">剩余时间:${dd.time}</div>
						<c:if test="${dd.totalCount != dd.alreadyCount}">
							<div style="text-align: right">
								<a href="/buyProps?pid=${dd.pid}"><input name="buy"
									type="button" value="${dd.cost}Y币领取一份" /></a>
							</div>
						</c:if>
						<div style="text-align: center">(每份${dd.cost}Y币,领取的次数越多,赢取的几率越大)</div>
					</div>
				</td>
				<td valign="top">
					<div class="userList">
						<div class="title">最新领取Top10</div>
						<div class="label">
							领取人员<span class="offset">领取时间</span>
						</div>
						<div class="lists">
							<table width="100%" border="0" cellspacing="0" cellpadding="3">
								<c:forEach begin="0" items="${dd.totalUser }" var="u">
									<tr>
										<td width="250">${u.username }</td>
										<td>${u.lastTime }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">卡号介绍</a></li>
		</ul>
		<div id="tabs-1" class="size_12">卡号介绍文本..........</div>
	</div>
</body>
</html>
