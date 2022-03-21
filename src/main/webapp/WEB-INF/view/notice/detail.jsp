<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
/* 
function deletePost1(){
	$.ajax({ 
		url: "/notice/${notice.noticeNo}", 
		type: "delete", 
		dataType: "json", 
		success: function(data){
				if(data.resultCode==="SUCCEESS"){
					alert('삭제하였습니다.');
					location.href="/notice";
				}else{
					alert('오류가 발생하였습니다. 잠시후에 다시 시도해주시기 바랍니다.');		
				}
		},
		error: function (request, status, error){  
			alert('오류가 발생하였습니다. 잠시후에 다시 시도해주시기 바랍니다.')
			} 
	});
}

 */
function deletePost(){
	document.notice._method.value = 'DELETE';
	document.notice.submit();
}

function goModifyPage(){
	document.notice._method.value = 'POST';
	document.notice.submit();
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
			<button type="button" class="btn btn-primary"  onclick="goModifyPage()" >수정</button>
		 </div> 
		 <form name="notice" method="post" action="/notice/${notice.noticeNo}">
		    <input type="hidden" name="_method" value=""/>
		</form>
	</div>
</body>
</html>