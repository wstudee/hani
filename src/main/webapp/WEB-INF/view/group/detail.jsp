<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$( document ).ready(function() {
	setMemberWeekRecode()
});


function register(){
	document.inputForm.action="/board"; 
	document.inputForm.method="get";
	document.inputForm.submit();
}
function memberJoin(){
	document.inputForm.action="/group/member/${group.sn}"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}

function setMemberWeekRecode(){
	
	/* var memberWeek = document.getElementsByClassName('memeberWeek');
	for(let i = 0 ; i < memberWeek.length ; i++){
		var member = memberWeek[i];
		var email = member.getAttribute('email');
	 */
	var data = {
		groupSn : 1,
	}
	
	$.ajax({
        cache : false,
        contentType: 'application/json',
        url : "${pageContext.request.contextPath}/group/memeberWeek", 
        type : 'POST', 
        data : JSON.stringify(data), 
        success : function(data) {
        	showMemeberWeek(data);
        }, // success 
        error : function(xhr, status) {
        }
    });
}

function showMemeberWeek(data){
	
	for(var i = 0 ;  i  < data.length ; i++){
		
	//todo 구현필요		
	}
}


</script>
<body>
<form name = 'inputForm'>
	<input type="hidden" name = 'sn' value = '${group.sn}'>
</form>
	<div class="container">
		<h3>${group.groupName }</h3>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<c:if test="${isMember}">
			<button type="button" class="btn btn-primary" onclick="register()">글쓰기</button>
			</c:if>
		</div>
		
		<c:forEach items="${group.memeberList }" var="memeber">
		<div class="row"  id='week_${memeber.user.email}' >
			<div class="col">${memeber.user.nickname}</div>
			<div class="col" id='monday_${memeber.user.email}'></div>
			<div class="col" id='tuesday_${memeber.user.email}'></div>
			<div class="col" id='wednesday_${memeber.user.email}'></div>
			<div class="col" id='thursday_${memeber.user.email}'></div>
			<div class="col" id='friday_${memeber.user.email}'></div>
			<div class="col" id='saturday_${memeber.user.email}'></div>
			<div class="col" id='sunday_${memeber.user.email}'></div>
		</div>
		</c:forEach>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="button" class="btn btn-primary" onclick="location.href='/group/list'">목록으로</button>
			<c:if test="${isMember}">
			<button type="button" class="btn btn-primary" onclick="register()">탈퇴하기</button>
			</c:if>
			<c:if test="${!isMember}">
			<button type="button" class="btn btn-primary" onclick="memberJoin()">가입하기</button>
			</c:if>
		</div>
	</div>
</body>
</html>