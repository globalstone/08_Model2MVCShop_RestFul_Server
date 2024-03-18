<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 
<%@page import="java.util.*"%>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.service.domain.Product"%>
<%//@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@page import="com.model2.mvc.common.Search"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>

<%
	List<Product> list =(List<Product>)request.getAttribute("list");
	Search search=(Search)request.getAttribute("search");
	Page resultPage = (Page)request.getAttribute("resultPage");
	//Purchase pur = new Purchase();
	String menu = (String)request.getAttribute("menu");
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	
%>
--%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

	function fncGetUserList(currentPage) {
		
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

			<c:if test="${ menu =='manage' }">
		<form name="detailForm" action="/product/listProduct/${menu}"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 관리</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					
					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" } >상품명</option>
					</select> <input type="text" name="searchKeyword" value="${ search.searchKeyword }" class="ct_input_g"
						style="width: 200px; height: 19px" /></td>
							
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetUserList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${ resultPage.totalCount } 건수, 현재 ${ resultPage.currentPage } 페이지
					</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				
				<c:set var="i" value="0"/>
				<c:forEach var="prod" items="${list}">
					<c:set var='i' value="${i+1}"/> 
				<tr class="ct_list_pop">
					<td align="center">${ i }</td>
					<td></td>

					<td align="left"><a
						href="/product/updateProduct/${ prod.prodNo }/${ menu }">${ prod.prodName }</a></td>

					<td></td>
					<td align="left">${ prod.price }</td>
					<td></td>
					<td align="left">${ prod.manuDate }</td>
					<td></td>
					<td align="left"><c:if test="${ ! empty prod.proTranCode }">
									 <c:if test="${ fn:trim(prod.proTranCode)==1}">
									 	구매완료 <a href="/purchase/updateTranCode?prodNo=${ prod.prodNo }&tranCode=2">배송하기</a>
									 </c:if>
									 <c:if test="${ fn:trim(prod.proTranCode)==2}">
									 	배송중
									 </c:if>
									 <c:if test="${ fn:trim(prod.proTranCode)==3}">
									 	배송완료
									 </c:if>
									 </c:if>
									 <c:if test="${ empty prod.proTranCode }">
									 	판매중
									 </c:if>
					</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
				</c:forEach>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center">
						<input type= "hidden" id = "currentPage" name="currentPage" value = ""/>
					
					<jsp:include page="../common/pageNavigator.jsp"/>
					
					</td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->

		</form>
		
		</c:if>
		<c:if test="${ menu=='search'}">
		<form name="detailForm" action="/product/listProduct/${menu}"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="1" ${ !empty search.searchCondition && search.searchCondition == 1 ? "selected" : "" } >상품명</option>
					</select> <input type="text" name="searchKeyword" value="${ search.searchKeyword }" class="ct_input_g"
						style="width: 200px; height: 19px" /></td>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetUserList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${ resultPage.totalCount } 건수, 현재 ${ resultPage.currentPage } 페이지
					</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<c:set var="i" value="0" />
				<c:forEach var="prod" items="${list}">
				<c:set var="i" value="${ i+1 }" />
				<tr class="ct_list_pop">
					<td align="center">${ i }</td>
					<td></td>

					<td align="left"><a
						href="/product/getProduct/${ prod.prodNo }/${ menu }">${ prod.prodName }</a></td>

					<td></td>
					<td align="left">${ prod.price }</td>
					<td></td>
					<td align="left">${ prod.manuDate }</td>
					<td></td>
					<td align="left"><c:if test="${ !empty prod.proTranCode }">
									 <c:if test="${ fn:trim(prod.proTranCode)==3 }">
											재고없음
									</c:if>
									 <c:if test="${ fn:trim(prod.proTranCode) !=3 }">
										재고없음
									</c:if>
									</c:if>
									<c:if test="${ empty prod.proTranCode }">
										판매중
									</c:if>
					</td>
				</tr>
				<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
				</c:forEach>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center">
						<input type="hidden" id="currentPage" name="currentPage" value=""/>
						<jsp:include page="../common/pageNavigator.jsp" />
						
					</td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->
			</c:if>
		</form>
	</div>

</body>
</html>
