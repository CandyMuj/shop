<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>商品分类</title>
		
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">


</head>
<body>


		<%@ include file="header.jsp" %>
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
			<s:iterator value="categoryList" var="c">
						<dl>
							<!-- 一级分类的显示 -->
							<dt>
								<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
							</dt>
								<!-- 二级分类的显示  -->
								<s:iterator value="#c.categorySeconds" var="cs">
									<dd>
										<a href="$ {pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property  value="#cs.csid"/>&page=1"><s:property  value="#cs.csname"/></a>
									</dd>
								</s:iterator>	
						</dl>
			</s:iterator>
			</div>
		</div>
	<div class="span18 last">
			
			
				<div id="result" class="result table clearfix">
						<ul>
						<s:iterator value="pageBean.list" var="p">
						
						<li>
						<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#p.pid"/>">
					
							<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;">
							   
							<span style='color:green'>
								<s:iterator>
									<s:property value="#p.pname"/>
								</s:iterator>
							</span>
							 
							<span class="price">
								商城價：￥<s:property  value="#p.shop_price"/>
							</span>
							 
						</a>
					</li>
						</s:iterator>		
						</ul>
				</div>
	<div class="pagination">
		第  <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页
			<s:if test="pageBean.page != 1">
				<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=1" class="firstPage">&nbsp;</a>		
				<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>	
			</s:if>	
			<s:iterator var="i" begin="1" end="pageBean.totalPage" step="1">
				<s:if test="pageBean.page==#i">
					<span class="currentPage"><s:property value="#i"/></span>
				</s:if>
				<s:else>
					<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBean.page != pageBean.totalPage">
				<a class="nextPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>	
	</div>
	</div>
		
</div>
	
		<%@ include file="footer.jsp" %>
</body>
</html>