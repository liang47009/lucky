<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/lucky.js"></script>
<script type="text/javascript">
	var bool = true;
	
	function b(i) {
		if (bool) {
			showLoadingDiv();
			$.ajax({
				type: "POST",
				url: "/buyProps",
				data: { id: i}
			}).done(function( msg ) {
				hiddenLoadingDiv();
				alert( msg );
				bool = true;
			}).error(function () {
				hiddenLoadingDiv();
				alert( "error!" );
				bool = true;
			});
			bool = false;
		}
	}
</script>
</head>
<body>
	<form action="lucky" method="post">
		<input type="submit" value="幸运领奖">
	</form>
	<table>
		<tr height="20">
			<c:forEach begin="0" items="${list}" var="props">
				<td>
					<table border="1">
						<tr>
							<td>${props.name}</td>
						</tr>
						<tr>
							<td height="50"><img alt="${props.name}"
								src="/resources/${props.url}"></td>
						</tr>
						<tr>
							<td>${props.count}</td>
						</tr>
						<tr>
							<td>${props.duration}</td>
						</tr>
						<tr>
							<td><input type="button" value="${props.cast}Y币/领取一份"
								onclick="javascript:b(${props.id});return false;"></td>
						</tr>
					</table>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>
