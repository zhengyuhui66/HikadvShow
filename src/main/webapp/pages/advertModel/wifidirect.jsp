<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/bootstrap.min.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/style.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/font-awesome-4.5.0/css/font-awesome.min.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/layer-v2.3/layer/skin/layer.css"rel="stylesheet"></link>
<title>绍兴公交WIFI登陆界面</title>
</head>

<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div style="height: 700px;">
			<div id="wifi1" style="height: 70px;">
			</div>
			<div id="wifi2" style="height: 140px;">
				<h1 class="logo-name" id="wifiId" style="">
					<i class="fa fa-wifi" style="color: rgb(26, 179, 148);margin-bottom:100px;"></i>
				</h1>
			</div>
			<div style="height: 490px">
				<h3>wifi上网身份认证</h3>
				<form class="m-t" role="form"
					method="post" action="/Wifi/logined">
					<button type="submit" class="btn btn-primary block full-width m-b"
						id="authId">一键登录</button>
		
				</form>
			</div>
		</div>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script
		src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
	var materurlwifi1 =<%=request.getParameter("materurlwifi1")%>;
	var materurlwifi2 =<%=request.getParameter("materurlwifi2")%>;
	var advertidwifi1=<%=request.getParameter("advertidwifi1")%>;
	var advertidwifi2=<%=request.getParameter("advertidwifi2")%>;
	//获取模版ID
	var modelid=<%=request.getParameter("modelid")%>;
	if (materurlwifi1) {
		$("#wifi1").css('background-image', "url('" + materurlwifi1 + "')");
	}
	if (materurlwifi2) {
		$("#wifi2").css('background-image', "url('" + materurlwifi2 + "')");
	}
	showcount(advertidwifi1,'wifi1',modelid);
	showcount(advertidwifi2,'wifi2',modelid);

	</script>
</body>
</html>
