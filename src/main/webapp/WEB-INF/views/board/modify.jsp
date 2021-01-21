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
	$("#remove-btn").click(function(e) {
		e.preventDefault();
		//change action attribute value of #modify-form
		$("#modify-form").attr("action", "${root}/board/remove");
		$("#modify-form").submit();
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
	<u:navbar></u:navbar>
	<div class="container-sm mb-3">
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
				<!-- responsive web design according to the size of the window -->
				<h1>게시물 수정</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
				<!--  <form action="${pageContext.request.contextPath }/board/register">  경로가 같은 경우 action생략해도 됨  -->
				<form id="modify-form" method="post" action="${root}/board/modify">
				<div class="form-group">
					<label for="input3">번호</label> <input class="form-control"
						name="bno" type="text" readonly value="${board.bno }" />
				</div>
					<div class="form-group">
						<label for="input1">Title</label> <input type="text" name="title"
							class="form-control" id="input1" value='<c:out value="${board.title }"/>'>
					</div>

					<div class="form-group">
						<label for="textarea1">Content</label>
						<textarea class="form-control" id="textarea1" rows="3"
							name="content"><c:out value="${board.content }"></c:out></textarea>
					</div>

					<div class="form-group">
						<label for="input2">Writer</label> <input readonly name="writer"
							type="text" class="form-control" id="input2"
							value='<c:out value="${board.writer }" />'>
					</div>
					<input hidden value="${cri.pageNum }" name="pageNum" />
					<input hidden value="${cri.amount }" name="amount" />
					<button type="submit" class="btn btn-primary">수정</button>
					<button id="remove-btn" type="submit" class="btn btn-danger">삭제</button>

				</form>

			</div>
		</div>
	</div>
	
</body>
</html>