<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style>
	table, tr, th, td{
		border: 1px solid black;
		text-align: center;
		border-collapse: collapse;}
	table{
		width: 100%;
	}
</style>
</head>
<body>
	<h1>상품 목록 페이지</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>등록날짜</th>
		</tr>
		
		<c:forEach items="${list }" var="pvo" varStatus="">
		
		<tr>
			<td>${pvo.pno }</td>
			<td><a href="detail.pd?pno=${pvo.pno }">${pvo.pname }</a></td>
			<td>${pvo.regdate }</td>
		</tr>
		
		</c:forEach>
	</table>
	<a href="register.pd"><button type="button">상품 등록</button></a>
	<a href="index.jsp"><button type="button">메인으로 이동</button></a>
</body>
</html>