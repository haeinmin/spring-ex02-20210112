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
</head>
<body>
	<u:navbar></u:navbar>
	<div class="container-sm mb-3">
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
				<!-- responsive web design according to the size of the window -->
				<h1>게시물 작성</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12 col-sm-6 offset-sm-3">
				<!--  <form action="${pageContext.request.contextPath }/board/register">  경로가 같은 경우 action생략해도 됨  -->
				<form method="post">
					<div class="form-group">
						<label for="input1">Title</label> <input type="text" name="title"
							class="form-control" id="input1" placeholder="제목을 입력하세요">
					</div>

					<div class="form-group">
						<label for="textarea1">Content</label>
						<textarea class="form-control" id="textarea1" rows="3"
							name="content"></textarea>
					</div>

					<div class="form-group">
						<label for="input1">Writer</label> <input name="writer"
							type="text" class="form-control" id="input1"
							placeholder="이름을 입력하세요">
					</div>

					<button type="submit" class="btn btn-primary">Submit</button>


				</form>

			</div>
		</div>
	</div>
	
</body>
</html>