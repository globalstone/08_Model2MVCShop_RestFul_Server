<%-- <%@page import="com.model2.mvc.service.domain.Purchase"%> --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%-- 
<%
Purchase vo = (Purchase)request.getAttribute("addVO");
%>
--%>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/purchase/updatePurchase/${addVO.tranNo }" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${ addVO.purchaseProd.prodNo }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${ addVO.buyer.userId }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>${ addVO.paymentOption }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${ addVO.receiverName }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${ addVO.receiverPhone }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${ addVO.divyAddr }</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${ addVO.divyRequest }</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${ addVO.divyDate }</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>