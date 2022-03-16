<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<table class="table">
				<colgroup>
					<col width="50%">
					<col width="50%">
				</colgroup>
				<tr>
					<th>제목</th>
					<th>내용</th>
				</tr>
					<tr>
						<td>${notice.title}</td>
						<td>${notice.contents}</td>
					</tr>
			</table>
		</div>
		 <div class="row">
		 	<!-- <button type="button" class="btn btn-primary" onclick="location.href='/register'">글등록</button>	</div> -->
	</div>
</body>
</html>