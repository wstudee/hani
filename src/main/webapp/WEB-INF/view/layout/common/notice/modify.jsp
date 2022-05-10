<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function modify(){
	document.inputForm.action="/notice/${notice.noticeNo}"; 
	document.inputForm._method.value="PUT";
	document.inputForm.submit();
}

function fileDelete(attNo){
	
	$.ajax({
	    url: "/notice/deleteFile",
	    type: "POST",
	    dataType: "text",
	    data:JSON.stringify ({attachedFileNo : attNo}),
	    contentType:'application/json',
	    success: function(data){
	 		if(data==='SUCCEESS'){
	 			alert("���� �Ϸ�");
	 			$('#file'+attNo).hide();
	 		}else{
	 			alert("���� ����");
	 		}
	    },
	    error: function (request, status, error){        
	    	alert("���� ����");
	    }
	});

}


function isFileImage(){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	  case 'jpg':
	  case 'bmp':
	  case 'jpeg':
	  case 'png':
	  case 'tif':
	    break;
	  default:
	    alert('�̹��� ���ϸ� ���ε尡 �����մϴ�.');
	  file.value = '';
	}
}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<div  class="col">
				<form name="inputForm"  method="post"  enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="exampleFormControlInput1">����</label>
				    <input type="text" class="form-control" id="title" name= "title" value ='${notice.title}' >
				  </div>
				  <div class="form-group">
				    <textarea class="form-control" id="contents" rows="3" name = "contents">${notice.contents}</textarea>
				  </div>
				  <input type="hidden" name = '_method' value = "">
				  <div class="form-group">
				    <label for="exampleFormControlInput1">÷������</label>
				    <input type="file" class="form-control" name= "files"  id="fileInput"  onchange="isFileImage()" multiple accept="image/gif, image/jpeg, image/png">
				  </div>		   
				<c:if test="${not empty notice.fileList }" >
					<c:forEach var="file" items="${notice.fileList}" varStatus="status">
						<div id="file${file.attachedFileNo}">
							<a href="/noticeFile/${file.attachedFileNo}">${file.fileName}<br></a><button type="button" onclick="fileDelete(${file.attachedFileNo})">X</button>
						</div>					
					</c:forEach>
				</c:if>
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/notice/list'">�������</button>
			<button type="button" class="btn btn-primary" onclick="modify()">����</button>
		</div>
	</div>
</body>
</html>