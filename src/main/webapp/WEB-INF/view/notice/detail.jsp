<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function deletePost(){
	$.ajax({ 
		url: "/notice/${notice.noticeNo}", 
		type: "delete", 
		success: function(data){ 
				alert('삭제하였습니다.')
				},
		error: function (request, status, error){  
			alert('오류가 발생하였습니다. 잠시후에 다시 시도해주시기 바랍니다.')
			} 
	});
}

</script>
<body>
	<div class="container">
		<h3>VIEW</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th>제목</th>
				</tr>
				<tr>
					<td>${notice.title}</td>
				</tr>
				<tr>	
					<th>내용</th>
				</tr>	
				<tr>
					<td>${notice.contents}</td>
				</tr>
			</table>
		</div>
		 <div class="row">
			<button type="submit" class="btn btn-primary" onclick="deletePost()" >글삭제</button>
			<button type="button" class="btn btn-primary"  onclick="deletePost()" >수정</button>
		 </div> 
	</div>
</body>
</html>