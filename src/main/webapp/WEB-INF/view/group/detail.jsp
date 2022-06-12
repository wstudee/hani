<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$( document ).ready(function() {
	setThisWeek(new Date());
	setMemberWeekRecode();
	
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

function leavingGroup(){
	document.inputForm.action="/group/member/${group.sn}"; 
	document.inputForm.method="post";
	document.inputForm._method.value="delete";
	document.inputForm.submit();
}

function setThisWeek(d){

	var thisMonday = getMonday(d);
	var thisWeek = 	dateStringFormat(thisMonday);
	document.getElementById("startDay").value = thisWeek;
	
	var thisSunday = new Date(thisMonday.setDate(thisMonday.getDate()+6));
	var thisSundayText = dateStringFormat(thisSunday);
	document.getElementById("lastDay").value = thisSundayText;
	
	var weekText = thisWeek+'~'+thisSundayText
	document.getElementById("weekDay").innerText = weekText;
	makeWeekArray()
}

var weekDays = [];
function makeWeekArray(){
	weekDays = [];
	var startStr = document.getElementById("startDay").value;
	weekDays.push(startStr);
	var startDay = new Date(startStr);
	for (let index = 0; index < 6; index++) {
		var nextDay = new Date(startDay.setDate(startDay.getDate()+1))
		var dayStr = dateStringFormat(nextDay);
		weekDays.push(dayStr);
	}



}

function preWeek(){
	var startStr = document.getElementById("startDay").value;
	var startDay = new Date(startStr);
	var preMonday = new Date(startDay.setDate(startDay.getDate()-7));
	setThisWeek(preMonday);
	setMemberWeekRecode()
}
function nextWeek(){
	var startStr = document.getElementById("startDay").value;
	var startDay = new Date(startStr);
	var preMonday = new Date(startDay.setDate(startDay.getDate()+7));
	
	if(preMonday > getMonday(new Date())){
		return;
	}
	setThisWeek(preMonday);
	setMemberWeekRecode()
}



function getMonday(d) {
  d = new Date(d);
  var day = d.getDay(),
      diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
  return new Date(d.setDate(diff));
}

function dateStringFormat(d){
	var year = d.getFullYear();
	var month = d.getMonth()+1 > 10 ? d.getMonth()+1 :"0"+(d.getMonth()+1);
	var date = d.getDate() > 10 ? d.getDate() : "0"+ d.getDate();
	
	return year+"-"+month+"-"+date;
}

function setMemberWeekRecode(){
	var startDate  = document.getElementById("startDay").value;
	var lastDay  = document.getElementById("lastDay").value
	var data = {
		groupSn : ${group.sn},
		startDate : startDate,
		lastDay : lastDay,
	}
	
	
	$.ajax({
        cache : false,
        contentType: 'application/json',
        url : "${pageContext.request.contextPath}/group/memberWeek", 
        type : 'POST', 
        data : JSON.stringify(data), 
        success : function(data) {
        	showMemberWeek(data);
        }, // success 
        error : function(xhr, status) {
        }
    });
}

function showMemberWeek(data){
	
	for(var i = 0 ;  i  < data.length ; i++){
		memData = data[i];
		var memTr = document.getElementById('week_'+memData.email);
		
		var memInfo = memData.info;
		var htmlText = "<td>"+memTr.getAttribute("nickName")+"</td>";
		var count = 0;
		
		for(var j =0; j < weekDays.length ; j++){
			var info = memInfo[weekDays[j]]
			if(info > 0){
				count++;
				htmlText+= '<td onClick="location.href=\'/board\/'+info+'\'">&#128170;</td></a>';
			}else{
				htmlText+= '<td>&#128683;</td>'
			}
		}
		var per = Math.floor(count/${group.updateCnt}*100);
		
		var emo = '';
		if(per>=100){
			emo = '&#128536;';
		}else if(per>=66){
			emo = '&#128521;';
		}else if(per>=33){
			emo = '&#128528;';
		}else{
			emo = '&#128552;';
		}
		htmlText+= '<td>'+per+'% 달성 '+emo+'</td>'
		memTr.innerHTML = '';
		memTr.innerHTML = htmlText;
		
	}
}




</script>
<body>
	<form name = 'inputForm'>
		<input type="hidden" name = "_method" value = ""/>
		<input type="hidden" name = 'sn' value = '${group.sn}'>
		<input type="hidden" name = 'startDay'  id = 'startDay' value = ''>
		<input type="hidden" name = 'lastDay'  id = 'lastDay' value = ''>
	</form>
		<div class="container">
		<h3>${group.groupName }</h3>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<c:if test="${isMember}">
			<button type="button" class="btn btn-primary" onclick="register()">글쓰기</button>
			</c:if>
		</div>
	<div class="bg-light p-5 rounded">
	<div class = 'row justify-content-md-center'>
	<a class="col col-sm-1 page-link" href="#" aria-label="Previous" onclick="preWeek()">
        <span aria-hidden="true">&laquo;</span>
      </a>
    <span class="col-md-auto" id='weekDay'>2022.05.23~</span>
      <a class="col col-sm-1 page-link" href="#" aria-label="Next" onclick="nextWeek()">
        <span aria-hidden="true">&raquo;</span>
      </a>
	</div>
		<table class="table table-striped table-hover">
		  <thead>
		    <tr>
		      <th scope="col">닉네임</th>
		      <th scope="col">월요일</th>
		      <th scope="col">화요일</th>
		      <th scope="col">수요일</th>
		      <th scope="col">목요일</th>
		      <th scope="col">금요일</th>
		      <th scope="col">토요일</th>
		      <th scope="col">일요일</th>
		      <th scope="col">이번주</th>
		    </tr>
		  </thead>
		  <tbody>
		
		<c:forEach items="${group.memberList }" var="member">
		 <tr id='week_${member.user.email}' nickName="${member.user.nickname}" >
			
		</tr>
		</c:forEach>
		</tbody>
		</table>
  </div>

		<c:import url="/group/baordList"> 
		  <c:param name="grouSn" value='${group.sn}' /> 
		</c:import>
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="button" class="btn btn-primary" onclick="location.href='/group/list'">목록으로</button>
			<c:if test="${isMember}">
			<button type="button" class="btn btn-danger" onclick="leavingGroup()">탈퇴하기</button>
			</c:if>
			<c:if test="${!isMember}">
			<button type="button" class="btn btn-primary" onclick="memberJoin()">가입하기</button>
			</c:if>
		</div>
	</div>
</body>
</html>