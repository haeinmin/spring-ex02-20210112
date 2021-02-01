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
<title>Insert title here</title>
<script>
$(document).ready(function() {
	$("#btn-7").click(function() {
		$.get("/replies/7", {})
		.done(function(data, status, xhr) {
			console.log("보기 성공");
			// console.log(jqXQR.responseText)
			console.log(data);
		}).fail(function() {
			console.log("보기 실패");
		});
	});
	$("#btn-8").click(function() {
		$.get("/replies/7", function(data) {
			console.log("보기 성공");
			console.log(data);
		});
	});
	$("#btn-9").click(function() {
		$.get("/replies/7", function(data) {
			console.log(data);
			console.log(data.rno);
			console.log(data.bno);
			console.log(data.reply);
			console.log(data.replyer);
		}, "json");
	});
	$("#btn-10").click(function() {
		$.get("/replies/7", function(data) {
			console.log(data);
		}, "text");
	});
	$("#btn-11").click(function() {
		$.getJSON("/replies/7", function(data) {
			console.log(data);
			console.log(data.rno);
			console.log(data.bno);
			console.log(data.reply);
			console.log(data.replyer);
		});
	});
});
</script>
</head>
<body>
<h1>AJAX ex5</h1>
<div>
<button id="btn-7">댓글 하나만 보기</button>
<button id="btn-8">댓글 하나만 보기</button>
<button id="btn-9">댓글 하나만 보기</button>
<button id="btn-10">댓글 하나만 보기</button>
<button id="btn-11">댓글 하나만 보기</button>
</div>
</body>
</html>