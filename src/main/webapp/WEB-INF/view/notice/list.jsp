<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
<script type="text/javascript">
	function goDetail(sn){
		location.href= '/notice/'+sn;
	}

	function search(){
		
		var searchWord = $('#searchWord').val()
		location.href= '/notice/list?searchWord='+searchWord
		
	}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
		<div class="row">
			�˻��� : <input type= "text" name = "searchWord" id = "searchWord" /> <button onclick="search()">�˻�</button>
		</div>
			<table class="table">
				<colgroup>
					<col width="50%">
					<col width="50%">
				</colgroup>
				<tr>
					<th>����</th>
					<th>����</th>
				</tr>
				<c:forEach items="${list}" var="notice">
					<tr onclick="goDetail(${notice.noticeNo})" >
						<td>${notice.title}</td>
						<td>${notice.contents}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		       <a class="page-link" href="${path}?page=${page.prePage}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach var="i" begin="${page.listStart}" end="${page.lastEnd}">
		    <li class="page-item"><a class="page-link" href="${path}?page=${i}" aria-label="${i}">${i}</a></li>
			</c:forEach>
			<li class="page-item">
		      <a class="page-link"  href="${path}?page=${page.nextPage}"  aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
		
		 <div class="row">
		 	<button type="button" class="btn btn-primary" onclick="location.href='/notice'">�۵��</button>
		 	<button type="button" class="btn btn-primary" onclick="location.href='/notice/list'">�������</button>	
	 	</div>
		 	
	</div>
</body>
</html>