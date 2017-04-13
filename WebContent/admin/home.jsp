<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时钟</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/clockstyle.css" media="screen" type="text/css" />

</head>

<body>

<div class="fill">
	<div class="reference"></div>
	<div class="clock" id="utility-clock">
		<div class="centre">
			<div class="dynamic"></div>
			<div class="expand round circle-1"></div>
			<div class="anchor hour">
				<div class="element thin-hand"></div>
				<div class="element fat-hand"></div>
			</div>
			<div class="anchor minute">
				<div class="element thin-hand"></div>
				<div class="element fat-hand minute-hand"></div>
			</div>
			<div class="anchor second">
				<div class="element second-hand"></div>
			</div>
			<div class="expand round circle-2"></div>
			<div class="expand round circle-3"></div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/script.js"></script>
<div style="text-align:left;">
<h1 align="center">HOMEADDSHOP</h1>
</div>

</body>
</html>