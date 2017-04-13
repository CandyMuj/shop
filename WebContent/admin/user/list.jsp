<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/product_addPage.action";
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong><h1>用户 列 表</h1></strong>
						</td>
					</tr>
					<tr>
					<tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="17%">
										姓名
									</td>
									<td align="center" width="10%">
										性别
									</td>
									<td align="center" width="8%">
										email
									</td>
									<td align="center" width="18%">
										电话
									</td>
									<td width="*%" align="center">
										地址
									</td>
									
								</tr>
									<s:iterator  value="pageBean.list" var="p">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#p.uid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#p.name"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#p.sex"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width=20%">
												<s:property value="#p.email"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="*%">
												<s:property value="#p.phone"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#p.addr"/>
											</td>
											
										</tr>
									</s:iterator>	
									<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%" colspan="8">
										第 <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页
										<s:if test="pageBean.page != 1">
											<a href="${ pageContext.request.contextPath }/product_adminFindAll.action?page=1">首页</a> |
											<a href="${ pageContext.request.contextPath }/product_adminFindAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a> |
										</s:if>
										<s:if test="pageBean.page != pageBean.totalPage">
											<a href="${ pageContext.request.contextPath }/product_adminFindAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a> |
											<a href="${ pageContext.request.contextPath }/product_adminFindAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
										</s:if>
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

