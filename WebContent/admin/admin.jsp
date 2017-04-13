<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>家加商城后台</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrapfont-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrapstyle.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">家加商城后台</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">HomeAddShop
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">HomeAddShop</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="${ pageContext.request.contextPath }/admin/home.jsp">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">商品管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                        	 <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/product_adminFindAll.action?page=1">商品列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/category_adminFindAll.action">一级分类列表</a>
                            </li>
                             <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/categorySecond_adminFindAll.action?page=1">二级分类列表</a>
                            </li>
                           
                        </ul>
                    </li> 
                    <!-- 用户管理               ========================================================== -->
                    <li>
                        <a href="#">
                            <i class="fa fa-desktop"></i> 
                            <span class="nav-label">用户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/user_adminFindAll.action?page=1">用户列表</a>
                            </li>
                          
                        </ul>
                    </li>
                    <!-- 订单 详情 -->
                    <li>
                        <a href="#">
                          <i class="fa fa-table"></i> 
                            <span class="nav-label">订单管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                       		<li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/order_adminFindAll.action?page=1">所有订单列表</a>
                            </li>  
                            <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/order_adminFindByState.action?page=1&state=1">未付款订单列表</a>
                            </li>  
                            <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/order_adminFindByState.action?page=1&state=2">已付款订单列表</a>
                            </li>
                          	  <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/order_adminFindByState.action?page=1&state=3">已发货订单列表</a>
                            </li>
                              <li>
                                <a class="J_menuItem" href="${pageContext.request.contextPath}/order_adminFindByState.action?page=1&state=4">交易成功列表</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
        	<div align="center">
        		<h2>WelCome HomeAddShop,nice day start from here!!!</h2>
        	</div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="${pageContext.request.contextPath }/admin/home.jsp" frameborder="0"  seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="${pageContext.request.contextPath}/js/plugins/pace/pace.min.js"></script>

</body>

</html>
