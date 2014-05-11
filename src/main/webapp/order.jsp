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
									<!-- 订单列表 -->
									<!-- 1号订单 -->
									<c:forEach items="${orders}" var="o">
										<div>订单编号：${o.id }</div>
										<div>
											订单金额：<span style="color: red;">100</span>
										</div>
										<div>下单用户：${user.nickname }</div>
										<div>
											下单时间：
											<fmt:formatDate value="${o.createtime}" pattern="yyyy-MM-dd" />
										</div>
										<div>
											支付状态： <font color="red">未支付</font> <a
												href="${pageContext.request.contextPath}/servlet/orderCancel">取消订单</a>
											<a href="pay.jsp?orderid=${order.id }&money=${order.money}">在线付款</a>
										</div>
										<div>收货人信息 ：${o.receiverinfo }</div>
										<div class="shopping_list_top">订单包含以下商品</div>
										<br />
										<div class="shopping_list_border">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr class="shopping_list_title">
													<td class="shopping_list_title_1">商品名</td>
													<td class="shopping_list_title_2">单品积分</td>
													<td class="shopping_list_title_3">市场价</td>
													<td class="shopping_list_title_4">Estore打折价</td>
													<td class="shopping_list_title_5">数量</td>
												</tr>
											</table>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<c:set var="totalprice" value="0" />
												<c:set var="totalsave" value="0" />
												<c:forEach var="i" items="${o.orderItems }">
													<c:set var="totalprice"
														value="${totalprice + i.product.estoreprice*i.buynum }" />
													<c:set var="totalsave"
														value="${totalsave + (i.product.marketprice - i.product.estoreprice)*i.buynum }" />
													<tr class="shopping_product_list" id="shoppingProduct_02">
														<td class="shopping_product_list_1"><a href="#"
															class="blue"> ${i.product.name}</a></td>
														<td class="shopping_product_list_2">${i.product.estoreprice*10}</td>
														<td class="shopping_product_list_3">￥${i.product.marketprice }</td>
														<td class="shopping_product_list_4">￥${i.product.estoreprice }
															(<fmt:formatNumber
																value="${i.product.estoreprice/i.product.marketprice*10}"
																maxFractionDigits="2" minFractionDigits="2" />折)
														</td>
														<td class="shopping_product_list_5">${i.buynum}</td>
													</tr>
												</c:forEach>

											</table>
									</c:forEach>
									<div class="shopping_list_end">
										<ul>
											<li class="shopping_list_end_1"></li>
											<li class="shopping_list_end_2">￥${totalprice }</li>
											<li class="shopping_list_end_3">商品金额总计：</li>
											<li class="shopping_list_end_4">您共节省：<label
												class="shopping_list_end_yellow">￥${totalsave }</label></li>
										</ul>
									</div>
								</div>
								<hr />

								<!-- 订单列表结束 -->
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