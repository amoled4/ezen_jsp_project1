<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, tr, th, td{
		border: 1px solid black;
		text-align: center;
		border-collapse: collapse;
	}
	table{
		width: 100%;
	}
</style>
</head>
<body>
	<h1>제품 상세 페이지</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>가격</th>
			<th>등록날짜</th>
			<th>세부내용</th>
		</tr>
		
		<tr>
			<td>${pvo.pno }</td>
			<td>${pvo.pname }</td>
			<td>${pvo.price }</td>
			<td>${pvo.regdate }</td>
			<td>${pvo.madeby }</td>
		</tr>
		
	</table>
	
		<a href="modify.pd?pno=${pvo.pno }"><button type="button">수정</button></a>
		<a href="remove.pd?pno=${pvo.pno }"><button type="button">삭제</button></a>
		<a href="index.jsp"><button type="button">메인 페이지</button></a>
</body>
</html>