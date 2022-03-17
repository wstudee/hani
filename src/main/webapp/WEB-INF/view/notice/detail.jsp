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
				alert('�����Ͽ����ϴ�.')
				},
		error: function (request, status, error){  
			alert('������ �߻��Ͽ����ϴ�. ����Ŀ� �ٽ� �õ����ֽñ� �ٶ��ϴ�.')
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
			<button type="button" class="btn btn-primary"  onclick="deletePost()" >����</button>
		 </div> 
	</div>
</body>
</html>