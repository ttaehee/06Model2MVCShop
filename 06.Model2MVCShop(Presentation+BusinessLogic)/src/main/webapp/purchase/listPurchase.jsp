
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////    
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
    
<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	
	String userId=((User)session.getAttribute("user")).getUserId();
	
	List<Purchase> list= (List<Purchase>)request.getAttribute("list");
    Page resultPage=(Page)request.getAttribute("resultPage");

	Search search=(Search)request.getAttribute("search");

%> /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetPurchaseList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
	
	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			Purchase purchase = (Purchase)list.get(i);
	%>
	
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=purchase.getTranNo()%>"><%=no%></a>
		</td>
		<td></td>
		<td align="left">
		    <a href="/getUser.do?userId=<%=userId%>"><%=userId%></a>
		</td>
		<td></td>
		<td align="left"><%=purchase.getReceiverName() %></td>
		<td></td>
		<td align="left"><%=purchase.getReceiverPhone() %></td>
		<td></td>

		<td align="left">현재
				
					<%if(purchase.getTranCode().trim().equals("2")){ %>
					구매완료
					<%}else if(purchase.getTranCode().trim().equals("3")){ %>
					배송중
					<%}else if(purchase.getTranCode().trim().equals("4")){ %>
					배송완료
					<%}%>

				상태 입니다.</td>
		<td></td>
		<td align="left">
			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% no--;} %>
	
	%>/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center"><a href="/getPurchase.do?tranNo=${purchase.tranNo}">${ i }</a></td>
			<td></td>
			<td align="left"><a href="/getUser.do?userId=${user.userId}">${user.userId}</a></td>
			<td></td>
			<td align="left">${user.userName}</td>
			<td></td>
			<td align="left">${purchase.receiverPhone}
			</td>		
		<td></td>

		<td align="left">현재
		
					<c:choose>
					    <c:when test="${empty purchase.tranCode}">
					    <a>판매중</a>
					    </c:when>
					    <c:when test="${purchase.tranCode eq '2'}">
					    <a>구매완료</a>
					    </c:when>
					    <c:when test="${purchase.tranCode eq '3'}">
					    <a>배송중</a>
					    </c:when>
					    <c:when test="${purchase.tranCode eq '4'}">
					    <a>배송완료</a>
					    </c:when>
					    
					</c:choose>

				상태 입니다.</td>
		<td></td>

			<c:if test="${purchase.tranCode eq '3'}">
			    <a href="/updateTranCode.do?tranNo="${purchase.tranNo}&tranCode=4">물건도착</a>
			</c:if>
		</td>
		
		<td align="left">
			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
		<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// 		 
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
			
			/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
	
		<jsp:include page="../common/pageNavigator.jsp"/>	
		
		</td>
	</tr>
</table>

<!-- PageNavigation End... -->
</form>

</div>

</body>
</html>