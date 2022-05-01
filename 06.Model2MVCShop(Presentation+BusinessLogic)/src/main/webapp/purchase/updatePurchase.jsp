<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// 
<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
%>	/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>

<html>
<head>
<title>구매후 상세조회</title>
</head>

<body>

<form name="updatePurchase">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${user.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
	    <%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
			<%if(purchase.getPaymentOption().trim().equals("1")){%>
				현금구매
			<% }else{ %>
			    신용구매
			<%} %>
			<% System.out.println(purchase.getPaymentOption().trim()); %>
		/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
		
			<c:choose>
			    <c:when test="${purchase.paymentOption eq '1'}">
			    <a>현금구매</a>
			    </c:when>
			    <c:when test="${purchase.paymentOption eq '2'}">
			    <a>신용구매</a>
			    </c:when>
			</c:choose>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>