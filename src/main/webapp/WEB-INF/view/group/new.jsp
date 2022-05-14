<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$( document ).ready(function() {
	
});


function register(){

	/* if(!checkGroupVal()){
		return;
	} */
	$('#groupStatus').val($('input:radio[name=groupStatusRadio]:checked').val());
	
	document.inputForm.action="/group"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}




</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		
		<div class="row justify-content-md-center">
			<div   class="col-5">
				<div  class="justify-content-md-center">
					<div  id="preview" class="justify-content-md-center">
					</div>
				</div>
		<div class="row justify-content-md-center">
			<div  class="col">
				<form name="inputForm" enctype="multipart/form-data">
				<input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
				<input type="hidden" id="groupStatus"	name="groupStatus" 	value=""/>
			    <div class="input-group group-input">
				  <span class="input-group-text" id="basic-addon1">���� �̸�</span>
				  <input type="text" class="form-control" placeholder="����"  aria-describedby="basic-addon1" id="groupName" name= "groupName" maxlength="5" >
				  <div class="invalid-feedback titleErr"> ���� �Է����ּ���. </div>
				</div>
				<div class="input-group group-input">
					<span class="input-group-text" id="basic-addon1">&nbsp&nbsp&nbsp&nbsp������</span>
					<input type="number" name="updateCnt" class="form-control form-control-color" id="exampleColorInput"  style ="margin-right: 1em;">ȸ ���� 
				</div>
				
				<div class="input-group group-input ">
				  <div class="input-group-text ">
				    <input class="form-check-input mt-0" type="radio" value="O" aria-label="Radio button for following text input" name = 'groupStatusRadio'>
				  </div>
				  <input type="text" class="form-control" aria-label="Text input with radio button" value="����" readonly="readonly" checked="checked">
				  <div class="input-group-text">
				    <input class="form-check-input mt-0" type="radio" value="N" aria-label="Radio button for following text input" name = 'groupStatusRadio'>
				  </div>
				  <input type="text" class="form-control" aria-label="Text input with radio button"  value="�����" readonly="readonly">
				</div>
				</div>
			   <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">�׷� ��ǥ ����</span>
				  <input type="file" class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput"  onchange="isFileImage(this,'preview')"  accept="image/gif, image/jpeg, image/png"> 
					<div class="invalid-feedback filesErr"> ������ ������ּ���. </div>
				</div>
				</form>
				<div id='submitErr'></div>
			</div>
			</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="button" class="btn btn-primary" onclick="location.href='/group/list'">�������</button>
			<button type="button" class="btn btn-primary" onclick="register()">���</button>
		</div>
	</div>
</body>
</html>