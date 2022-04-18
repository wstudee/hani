<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
<script type="text/javascript">
	function goDetail(sn){
		location.href= '/board/'+sn;
	}

	function search(){
		var searchWord = $('#searchWord').val();
		var searchCriteria = $('#searchCriteria').val();
		location.href= '/board/list?searchWord='+searchWord+"&searchCriteria="+searchCriteria;
		
	}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<select id="searchCriteria"> 
				<option value="total">전체</option>
				<option value="title">제목</option>
				<option value="contents">내용</option>
			</select> 
			<input type= "text" name = "searchWord" id = "searchWord"  value='${searchWord}'/> <button onclick="search()">검색</button>
		</div>
		<div class="row">
			<table class="table">
				<colgroup>
					<col width="50%">
					<col width="30%">
					<col width="20%">
				</colgroup>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
				<c:forEach items="${list}" var="board">
					<tr onclick="goDetail(${board.boardNo})" >
						<td>${board.title}</td>
						<td>${board.regUser.email}</td>
						<td>
							<fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }" />
						</td>
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
		 	<button type="button" class="btn btn-primary" onclick="location.href='/board'">글등록</button>
		 	<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>	
	 	</div>
		 	
	</div>
</body>
</html>