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
          <li><a href="${pageContext.request.contextPath}/board/list" class="nav-link px-2 link-dark">board</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">Customers</a></li>
          <li><a href="#" class="nav-link px-2 link-dark">Products</a></li>
        </ul>

        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
        </form>

		
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
            <li><a class="dropdown-item" href="#">New project...</a></li>
            <li><a class="dropdown-item" href="#">Settings</a></li>
            <li><a class="dropdown-item" href="#">Profile</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/account/logout">LogOut</a></li>
          </ul>
        </div>
        </sec:authorize>
        
      </div>
    </div>
  </header>
<%--     <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				 
				<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="navbar-toggler-icon"></span>
				</button> <a class="navbar-brand" href="#">Brand</a>
				<div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1" aria-expanded="false" style="">
					<ul class="navbar-nav">
						<li class="nav-item active">
							 <a class="nav-link" href="/notice/list">NOTICE <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							  <a class="nav-link" href="/board/list">BOARD <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item dropdown">
							 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">Dropdown link</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
								 <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
								<div class="dropdown-divider">
								</div> <a class="dropdown-item" href="#">Separated link</a>
							</div>
						</li>
					</ul>
					<form class="form-inline">
						<input class="form-control mr-sm-2" type="text"> 
						<button class="btn btn-primary my-2 my-sm-0" type="submit">
							Search
						</button>
					</form>
					<ul class="navbar-nav ml-md-auto">
						<li class="nav-item active">
						<sec:authorize access="isAnonymous()">
						<li class="nav-item active">
							 <a class="nav-link" href="/account/login">Login <span class="sr-only">(current)</span></a>
						</li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
						 <sec:authentication property="principal" var="principal" />
						 	<li class="nav-item">
							<a class="nav-link" href="/account/info"><span style="font-weight: 900">${principal.nickname}</span> 님 환영합니다.</a>
							</li> 
							<li class="nav-item active">
							<a class="nav-link" href="/account/logout">Log Out <span class="sr-only">(current)</span></a>
							</li>
						</sec:authorize>
						</li>
						<li class="nav-item dropdown">
							 <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">Dropdown link</a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
								 <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
								<div class="dropdown-divider">
								</div> <a class="dropdown-item" href="#">Separated link</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>
 --%>
