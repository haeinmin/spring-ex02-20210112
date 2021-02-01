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
	$("#btn-1").click(function() {
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno": 212, "reply":"new reply", "replyer":"user01"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);
			}
		});
	});
	$("#btn-2").click(function() {
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"reply":"new reply", "replyer":"user01"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);
			}
		});
	});
	$("#btn-3").click(function() {
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno":212, "reply":"new reply", "replyer":"user01"}',
			complete: function(jqXHR, status) {
				if (status==="success") {
					console.log("등록 성공");
					console.log(jqXHR.responseText)
				} else if (status === "error") {
					console.log("등록 실패");
				}
			}
		});
	});
	$("#btn-4").click(function() {
		$.ajax({
			type: "get",
			url: "/replies/pages/212/1",
			complete: function(jqXHR, status) {
				if (status==="success") {
					console.log(jqXHR.responseText)
				}
			}
		});
	});
	$("#btn-5").click(function() {
		$.ajax({
			type: "delete",
			url: "/replies/50",
			complete: function(jqXHR, status) {
				if (status==="success") {
					console.log(jqXHR.responseText)
				}
			}
		});
	});
	$("#btn-6").click(function() {
		$.ajax({
			type: "put",
			url: "/replies/64",
			contentType: "application/json; charset=utf-8",
			data: '{"bno":212, "reply":"수정된 댓글", "replyer":"새로운 유저"}',
			complete: function(jqXHR, status) { /* 첫번째는 ajax parameter */
				if (status==="success") {
					console.log(jqXHR.responseText)
				}
			}
		});
	});
});
</script>
</head>
<body>
<h1>AJAX ex2</h1>
<div>
<button id="btn-1">댓글 등록 성공</button>
<button id="btn-2">댓글 등록 실패</button>
<button id="btn-3">댓글 등록 성공 또는 실패</button>
<button id="btn-4">댓글 목록</button>
<button id="btn-5">댓글 삭제</button>
<button id="btn-6">댓글 수정</button>
</div>
</body>
</html>