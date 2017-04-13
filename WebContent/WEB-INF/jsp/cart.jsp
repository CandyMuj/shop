<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="header.jsp" %>
	
</div>	<div class="container cart">
		<div class="span24">
		<s:if test="#session.cart==null || #session.cart.CartItems.size()==0">
			<div class="step step1">
				<font style="font-size: xx-large;text-align: center;color: red">你还没有添加任何商品哦</font>	
			</div>
		</s:if>
		<s:else>
		<div class="step step1">
				购物车列表
			</div>
			
				<table>
					<tbody><tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
						<s:iterator value="#session.cart.cartItems">
						
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22">
								<img src="${pageContext.request.contextPath}/<s:property value="product.image"/>">
							</td>
							<td>
								<a target="_blank"><s:property value="product.pname"/></a>
							</td>
							<td>
								￥：<s:property value="product.shop_price"/>
							</td>
							<td class="quantity" width="60">
									<s:property value="count"/>
							</td>
							<td width="140">
								<span class="subtotal"><s:property value="subtotal"/></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath }/cart_removeCart.action?pid=<s:property value="product.pid"/>" class="delete">删除</a>
							</td>
						</tr>
							</s:iterator>	
				</tbody></table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint"><s:property value="#session.cart.total"/></em>
					商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.total"/>元</strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/cart_clearCart.action" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath }/order_saveOrder.action" id="submit" class="submit">提交订单</a>
				</div>
				</s:else>
		</div>
	</div>
	
<%@ include file="footer.jsp" %>
</body></html>