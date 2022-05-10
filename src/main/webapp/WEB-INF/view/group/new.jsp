<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$( document ).ready(function() {
	
	var dt = new Date();
	var month = dt.getMonth()+1 < 10 ? "0"+(dt.getMonth()+1) : (dt.getMonth()+1); 
	var date  = dt.getDate() < 10 ? "0"+ dt.getDate() : dt.getDate() ; 
	var str = dt.getFullYear()+'-'+month+'-'+date;
	$('#date').text(str);
	
});


function register(){

	if(!checkVal()){
		return;
	}
	
	document.inputForm.action="/board"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}




</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		
		<div class="row">
			<div  class="col">
				<form name="inputForm" enctype="multipart/form-data">
				<input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">���� �̸�</span>
				  <input type="text" class="form-control" placeholder="����"  aria-describedby="basic-addon1" id="groupName" name= "groupName" maxlength="5" >
				  <div class="invalid-feedback titleErr"> ���� �Է����ּ���. </div>
				  <div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="open" value="1" name= 'groupStatus'>
					  <label class="form-check-label" for="inlineRadio1">����</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="noOpne" value="0" name = 'groupStatus'> 
					  <label class="form-check-label" for="inlineRadio2">�����</label>
					</div>
				</div>
				<div class="input-group mb-3">
					 <span class="input-group-text" id="basic-addon1">�����Ͽ�</span>
					<input type="number" name="color" class="form-control form-control-color" id="exampleColorInput" value="#000000" title="Choose your color" onchange="chageColor(this)">ȸ ���� 
				</div>
			   <!--  <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="file" class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput"  onchange="isFileImage(this)"  accept="image/gif, image/jpeg, image/png"> 
					<div class="invalid-feedback filesErr"> ������ ������ּ���. </div>
				</div> -->
				</form>
				<div id='submitErr'></div>
			</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">�������</button>
			<button type="button" class="btn btn-primary" onclick="register()">���</button>
		</div>
	</div>
</body>
</html>