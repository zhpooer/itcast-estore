<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Estore购物商城</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style.css" type="text/css"
	media="screen" />
<!--[if IE 6]><link rel="stylesheet" href="${pageContext.request.contextPath}/style.ie6.css" type="text/css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="${pageContext.request.contextPath}/style.ie7.css" type="text/css" media="screen" /><![endif]-->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/script.js"></script>
<script type="text/javascript">
function changeNum(id, maxnum, num) {
	var regex = /^[0-9]+$/;
    if(regex.test(num)) {
        if(num>maxnum) {
            // 存货不足
            alert("商品库存数量不能草果库存数量");
        } else {
            window.location.href="${pageContext.request.contextPath}/" + 
            "servlet/updateCartItem?id=" + id + "&num="  + num;
        }
    } else {
        alert("请输入正确格式")
    }
}
</script>
</head>
<body>
	<div id="art-main">
		<div class="art-sheet">
			<div class="art-sheet-tl"></div>
			<div class="art-sheet-tr"></div>
			<div class="art-sheet-bl"></div>
			<div class="art-sheet-br"></div>
			<div class="art-sheet-tc"></div>
			<div class="art-sheet-bc"></div>
			<div class="art-sheet-cl"></div>
			<div class="art-sheet-cr"></div>
			<div class="art-sheet-cc"></div>
			<div class="art-sheet-body">
				<%@include file="header.jsp"%>
				<div class="art-content-layout">
					<div class="art-content-layout-row">
						<div class="art-layout-cell art-content">
							<div class="art-post">
								<div class="art-post-tl"></div>
								<div class="art-post-tr"></div>
								<div class="art-post-bl"></div>
								<div class="art-post-br"></div>
								<div class="art-post-tc"></div>
								<div class="art-post-bc"></div>
								<div class="art-post-cl"></div>
								<div class="art-post-cr"></div>
								<div class="art-post-cc"></div>
								<div class="art-post-body">
									<!-- 购物车列表 -->
									<div>
										&nbsp;&nbsp;<img
											src="${pageContext.request.contextPath}/images/shopping_myshopping.gif"
											alt="shopping" /> <a href="#">全场免运费活动中</a>
									</div>
									<div class="shopping_list_top">您已选购以下商品</div>
									<br />
									<div class="shopping_list_border">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">

											<tr class="shopping_list_title">
												<td class="shopping_list_title_1">商品名</td>
												<td class="shopping_list_title_2">单品积分</td>
												<td class="shopping_list_title_3">市场价</td>
												<td class="shopping_list_title_4">Estore打折价</td>
												<td class="shopping_list_title_5">数量</td>
												<td class="shopping_list_title_6">删除</td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<c:set var="totalprice" value="0" />
											<c:set var="totalsave" value="0" />
											<c:forEach var="item" items="${cart}">
											<c:set var="totalprice" value="${totalprice + item.key.estoreprice*item.value }" />
											<c:set var="totalsave" value="${totalprice + (item.key.marketprice - item.key.estoreprice)*item.value }" />
												<tr class="shopping_product_list" id="shoppingProduct_05">
													<td class="shopping_product_list_1"><a href="#"
														class="blue">${item.key.name }</a></td>
													<td class="shopping_product_list_2">${item.key.estoreprice*10}</td>
													<td class="shopping_product_list_3">￥${item.key.marketprice}</td>
													<td class="shopping_product_list_4">￥${item.key.estoreprice}
														(<fmt:formatNumber
															value="${item.key.estoreprice/item.key.marketprice*10}"
															maxFractionDigits="2" minFractionDigits="2" />折)
													</td>
													<td class="shopping_product_list_5"><input type="text"
														onblur="changeNum(${item.key.id}, ${item.key.pnum}, this.value);"
														value="${item.value}" /></td>
													<td class="shopping_product_list_6"><a
														href=" ${pageContext.request.contextPath}/servlet/deleteCartItem?id=${item.key.id}"
														class="blue">删除</a></td>
												</tr>
											</c:forEach>
										</table>
										<div class="shopping_list_end">
											<ul>
												<li class="shopping_list_end_1"><input type="image"
													src="${pageContext.request.contextPath}/images/shopping_balance.gif"
													onclick="window.location='order_add.jsp';" /></li>
												<li class="shopping_list_end_2">￥${totalprice }</li>
												<li class="shopping_list_end_3">商品金额总计：</li>
												<li class="shopping_list_end_4">您共节省：<label
													class="shopping_list_end_yellow">￥${totalsave }</label></li>
											</ul>
										</div>
									</div>
									<!-- 购物车列表结束 -->
									<div class="cleared"></div>
								</div>
							</div>
						</div>
						<%@include file="sidebar.jsp"%>
					</div>
				</div>
				<div class="cleared"></div>
				<%@include file="footer.jsp"%>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>

</body>
</html>