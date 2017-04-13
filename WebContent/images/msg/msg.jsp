<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">

</head>
<body>
<%-- <%@ include file="header.jsp" %> --%>
<div id="divcontent" style="background-color: white;border: 0px;width: 600px">
<table width="850px" border="0" cellspacing="0" style="margin-top: 10%">
  <tr>
    <td style="padding:30px; text-align:center"><table width="60%" border="0" cellspacing="0" style="margin-top:70px">
      <tr>
        <td style="width:98"><img src="${pageContext.request.contextPath}/images/IconTexto_WebDev_009.jpg" width="128" height="128" /></td>
        <td style="padding-top:30px"><font style="font-weight:bold; color:#FF0000"><s:actionmessage/></font><br />
            <br />
            <div style="margin-top: 30%"></div>
            
            <a href="${ pageContext.request.contextPath }/index.action"><img alt="返回首页" src="${pageContext.request.contextPath}/images/msg/ret.png" width="40%"></a>
          <%-- <a href="${ pageContext.request.contextPath }/index.action">首页</a>
          <a href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>
          <a href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a> --%>
         </td>
      </tr>
    </table>
    <h1>&nbsp;</h1></td>
    </tr>
</table>
</div>
<div style="margin-top: 10%"></div>
<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>