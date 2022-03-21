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
					alert('�����Ͽ����ϴ�.');
					location.href="/notice";
				}else{
					alert('������ �߻��Ͽ����ϴ�. ����Ŀ� �ٽ� �õ����ֽñ� �ٶ��ϴ�.');		
				}
		},
		error: function (request, status, error){  
			alert('������ �߻��Ͽ����ϴ�. ����Ŀ� �ٽ� �õ����ֽñ� �ٶ��ϴ�.')
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
					<th>����</th>
				</tr>
				<tr>
					<td>${notice.title}</td>
				</tr>
				<tr>	
					<th>����</th>
				</tr>	
				<tr>
					<td>${notice.contents}</td>
				</tr>
			</table>
		</div>
		 <div class="row">
			<button type="submit" class="btn btn-primary" onclick="deletePost()" >�ۻ���</button>
			<button type="button" class="btn btn-primary"  onclick="goModifyPage()" >����</button>
		 </div> 
		 <form name="notice" method="post" action="/notice/${notice.noticeNo}">
		    <input type="hidden" name="_method" value=""/>
		</form>
	</div>
</body>
</html>