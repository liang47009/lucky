<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布</title>

</head>

<body>
	<form name="propsForm" action="/manager/addProps" method="post"
		enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>卡号名称：</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>logo：</td>
					<td><input id="uploadImage" type="file" name="logo"
						onchange="PreviewImage();" /></td>
					<td><img id="uploadPreview"></td>
				</tr>
				<tr>
					<td>卡号：</td>
					<td><input type="text" name="activation"></td>
				</tr>
				<tr>
					<td>卡号激活链接：</td>
					<td><input type="text" name="link"></td>
				</tr>
				<tr>
					<td>卡号描述：</td>
					<td><textarea name="description" cols="50" rows="10"></textarea></td>
				</tr>

				<tr>
					<td>总份数：</td>
					<td><input type="text" name="totalCount"></td>
				</tr>
				<tr>
					<td>每份价格：</td>
					<td><input type="text" name="cost"></td>
				</tr>
				<tr>
					<td>市场价：</td>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td><input type="text" name="start">(yyyy:MM:dd HH:mm)</td>
				</tr>
				<tr>
					<td>周期时长：</td>
					<td><input size="2" maxlength="2" type="text" name="hour">时<input
						size="2" maxlength="2" type="text" name="secend">分</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="提交"><input
						type="button" value="重置"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		function PreviewImage() {
			var oFReader = new FileReader();
			oFReader
					.readAsDataURL(document.getElementById("uploadImage").files[0]);
			oFReader.onload = function(oFREvent) {
				document.getElementById("uploadPreview").src = oFREvent.target.result;
			};
		};
	</script>
</body>
</html>
