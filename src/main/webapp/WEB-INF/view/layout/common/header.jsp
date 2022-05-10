<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<header class="p-3 mb-3 border-bottom">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#" class="nav-link px-2 link-secondary">공지사항</a></li>
          <li><a href="${pageContext.request.contextPath}/board/list" class="nav-link px-2 link-dark">인증게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/group/list" class="nav-link px-2 link-dark">내 그룹</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">Products</a></li>
        </ul>

       <!-- <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
       </form>
 -->
		
		<div class="col-md-3 text-end">
		<sec:authorize access="isAnonymous()">
        	<button type="button" onclick="location.href='${pageContext.request.contextPath}/account/login' " class="btn btn-outline-primary me-2">Login</button>
        	<button type="button" onclick="location.href='${pageContext.request.contextPath}/account/register'"  class="btn btn-primary">Sign-up</button>
        </sec:authorize>
     
							
      	</div>

  		 <sec:authorize access="isAuthenticated()">
		 <sec:authentication property="principal" var="principal" />
        <div style="margin-right: 1em;"><span style="font-weight: 900">${principal.nickname}</span> 님 환영합니다.</div>
        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="/profileFile/${principal.profilePicPath.attachedFileNo}" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
          <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="#">N</a></li>
            <li><a class="dropdown-item" href="#">My Group</a></li>
            <li><a class="dropdown-item" href="/account/profile">Profile</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/account/logout">LogOut</a></li>
          </ul>
        </div>
        </sec:authorize>
        
      </div>
    </div>
  </header>
