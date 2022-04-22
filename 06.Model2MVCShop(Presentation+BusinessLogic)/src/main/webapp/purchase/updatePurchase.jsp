<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL �������� �ּ� ó�� //////////////////////// 
<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
%>	/////////////////////// EL / JSTL �������� �ּ� ó�� //////////////////////// --%>

<html>
<head>
<title>������ ����ȸ</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${purchase.tranNo}" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${user.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
	    <%-- /////////////////////// EL / JSTL �������� �ּ� ó�� ////////////////////////
			<%if(purchase.getPaymentOption().trim().equals("1")){%>
				���ݱ���
			<% }else{ %>
			    �ſ뱸��
			<%} %>
			<% System.out.println(purchase.getPaymentOption().trim()); %>
		/////////////////////// EL / JSTL �������� �ּ� ó�� //////////////////////// --%>
			
			<c:choose>
			    <c:when test="${purchase.paymentOption eq '1'}">
			    <a>���ݱ���</a>
			    </c:when>
			    <c:when test="${purchase.paymentOption eq '2'}">
			    <a>�ſ뱸��</a>
			    </c:when>
			</c:choose>
					
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>