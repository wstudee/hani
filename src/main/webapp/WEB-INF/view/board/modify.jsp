<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">

$(function(){ 
	boardSetting('${file.attachedFileNo}','${board.textcolor}','${board.bordercolor}')
});



function modify(){
	
	if(!checkVal()){
		return;
	}
	
	
	document.inputForm.action="/board/${board.boardNo}"; 
	document.inputForm._method.value="PUT";
	document.inputForm.submit();
}

function alertToDel(e){
	if(e.value == ''){
		$('#fileInput').click()
		return;
	}
	if( confirm("������ ����� ������ ���� �� ��� �����մϴ�.")){
		fileDelete(${file.attachedFileNo});
		$('#fileInput').click()
	}
	
}

function fileDelete(attNo){
	
	/* if(confirm("������ �����Ͻðڽ��ϱ�?")){
		$.ajax({
		    url: "/board/deleteFile",
		    type: "POST",
		    dataType: "text",
		    data:JSON.stringify ({attachedFileNo : attNo, boardSN : ${board.boardNo} }),
		    contentType:'application/json',
		    success: function(data){
		 		if(data==='SUCCEESS'){
		 			
		 			$('#file'+attNo).remove();
		 			$('#preview').css('background-image','none' )
		 			$('#fileName').val();
		 		}else{
		 			alert("���� ����");
		 		}
		    },
		    error: function (request, status, error){        
		    	alert("���� ����");
		    }
		});
	}
	 */
	

}

function fillFileName(){
	var file = document.getElementById('fileInput');
	document.getElementById('fileName').innerText = file.value;
}

</script>
<body>
	<div class="container">
		<h3>LIST</h3>


		<div class="row">
			<div class="col">
				<div class="justify-content-md-center">
					<div id="preview" class="justify-content-md-center">
						<div>
							<p id='date'>
								<fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }" />
							</p>
							<p id="titleShow">${board.title}</p>
							<p id="titleCont">${board.contents}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col">
				<form name="inputForm"  method="post"  enctype="multipart/form-data">
				 <input type="hidden" name = '_method' value = "">
				 <input type="hidden" name = 'group' value = "${board.group.sn}">
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="text" class="form-control" placeholder="����"  aria-describedby="basic-addon1" id="title" name= "title" maxlength="5" onchange="chageInput()" value ='${board.title}' >
				  <div class="invalid-feedback titleErr"> ���� �Է����ּ���. </div>
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="text" class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"  id="contents" maxlength="15" name = "contents" onchange="chageInput()" value='${board.contents}' > 
					<div class="invalid-feedback contentsErr"> ���븦 �Է����ּ���. </div>
				</div>
				<div class="input-group mb-3">
					 <span class="input-group-text" id="basic-addon1">����&nbsp&nbsp&nbsp&nbsp����</span>
					<input type="color" name="textcolor" class="form-control form-control-color" id="exampleColorInput" value="${board.textcolor}" title="Choose your color" onchange="chageColor(this)">
				</div>
				<div class="input-group mb-3">
					 <span class="input-group-text" id="basic-addon1">�׵θ�&nbsp&nbsp&nbsp����</span>
					<input type="color" name="bordercolor" class="form-control form-control-color" id="exampleColorInput" value="${board.bordercolor}" title="Choose your color" onchange="chageBorderColor(this)">
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">������ ����</span>
				  <input type="file" style="display: none " class="form-control" placeholder="���ٿ��" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput" onchange="isFileImage(this,'preview',fillFileName)"  accept="image/gif, image/jpeg, image/png"> 
				  <button  type="button"  class='form-control fileName' onclick="alertToDel(this)" id= 'fileName' value = '${file.fileName}'>${file.fileName}</button>
				  <sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="principal" />
						<c:if test="${principal.username eq board.regUser.email}">
							<button type="button" class='btn' onclick="fileDelete(${file.attachedFileNo})">����</button>
						</c:if>
					</sec:authorize>
				</div> 
					<div class="invalid-feedback filesErr"> ������ ������ּ���. </div>
			</div>
			</form>
			<div id='submitErr'></div>
		</div>
	</div>

	<div class="d-grid gap-2 d-md-block justify-content-md-end">
		<button type="button" class="btn btn-primary"
			onclick="location.href='/board/list'">�������</button>
		<button type="button" class="btn btn-primary" onclick="modify()">����</button>
	</div>
	</div>
</body>
</html>