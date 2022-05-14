<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <title>RUN HANI!</title>
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
  </head>
<!-- 커스텀 CSS -->
<link rel="stylesheet" type="text/css" href="/resource/css/base.css">
<link rel="stylesheet" type="text/css" href="/resource/css/${task}.css">
<!-- <link rel="stylesheet" type="text/css" href="/resource/css/user.css">
<link rel="stylesheet" type="text/css" href="/resource/css/group.css"> -->

<!-- 커스텀 js -->
<!-- <script src="/resource/js/board.js"></script>

 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

      <tiles:insertAttribute name="header"/>

   <body>
    <section class="content">
      <tiles:insertAttribute name="body"/> <!-- body -->
    </section>
   <%--  <tiles:insertAttribute name="footer"/> --%> 
  </body>
      
</html>
