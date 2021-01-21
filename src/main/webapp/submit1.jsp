<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script>
$(document).ready(function() {
	$("#outside").click(function() {
		$("#myForm").submit();
	});
	$("#inside").click(function(e) {
		e.preventDefault();      //원래 하는 일 (submit) prevent 한 후에 실행
		console.log("inside button clicked");
		$("#myForm").submit();
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
<h1>submit ex page</h1>
<h1>name : ${param.name }</h1>
<form id="myForm" action="">
	<input type="text" name="name" value="java" /> <br>
	<input type="submit" value="go" /> <br>
	<button id="inside">another submit</button>
</form>
<button id="outside">button outside</button>
</body>
</html>