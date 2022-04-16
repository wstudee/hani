<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>

#preview{
	height: 500px;
    width: 500px;
    position: relative;
    text-align: center;
    background-color: #eeeeee;
    margin-bottom: 3%;
    position: relative;
    text-align: center;
    
}
#preview div{
	vertical-align: middle;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    display: inline-block;
    position: absolute;
}

</style>
<script type="text/javascript">
$( document ).ready(function() {
	var dt = new Date();
	var str = dt.getFullYear()+'-'+(dt.getMonth()+1)+'-'+dt.getDate();
	$('#date').text(str);
	
});





function register(){

	document.inputForm.action="/board"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}

function isFileImage(e){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	  case 'jpg':
	  case 'jpeg':
	  case 'bmp':
	  case 'png':
	  case 'tif':
		  setThumbnail(e);
		  break;
	  default:
	    alert('�̹��� ���ϸ� ���ε尡 �����մϴ�.');
	  file.value = '';
	}
}


function setThumbnail(event){
	var reader = new FileReader();
	
	reader.onload = function(event){
		$('#preview').css("background-image","url("+event.target.result+")");
		
	};
	
	reader.readAsDataURL(event.files[0]);
}

function chageInput(){
	
	$('#titleShow').text($('#title').val());
	$('#titleCont').text($('#contents').val());
	
}


</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div  class="row justify-content-md-center">
			<div  id="preview" class="justify-content-md-center">
				<div>
					<p id='date'></p>
					<p id="titleShow"></p>
					<p id="titleCont"></p>
				</div>
			</div>
		</div>
		<div class="row">
			<div  class="col">
				<form name="inputForm" enctype="multipart/form-data">
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="text" class="form-control" placeholder="����" aria-label="Username" aria-describedby="basic-addon1" id="title" name= "title" maxlength="5" onchange="chageInput()">
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="text" class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"  id="contents" maxlength="15" name = "contents" onchange="chageInput()"> 
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="file" class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput"  onchange="isFileImage(this)"  accept="image/gif, image/jpeg, image/png"> 
				</div>
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">�������</button>
			<button type="button" class="btn btn-primary" onclick="register()">���</button>
		</div>
	</div>
</body>
</html>