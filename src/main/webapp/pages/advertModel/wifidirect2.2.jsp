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
<link href="${rootUrl}/<%=request.getParameter("modelskin")%>" rel="stylesheet"></link>
<title>绍兴公交WIFI登陆界面</title>
</head>

<body id="wifibg" class="gray-bg"  style="padding-top:60%;">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div style="height: 50px">
			<form class="m-t" role="form"
				method="post" action="/Wifi/logined">
				<button type="submit" class="btn btn-style block full-width m-b"
					id="authId">一键登录</button>
			</form>
		</div>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
	var materurlwifibg =<%=request.getParameter("materurlwifibg")%>;
	var advertidwifibg=<%=request.getParameter("advertidwifibg")%>;
	//获取模版ID
	var modelid=<%=request.getParameter("modelid")%>;
	$('#wifibg').css("background","url('"+materurlwifibg+"') no-repeat center fixed");
	$('#wifibg').css("background-size","100% 100%");
	showcount(advertidwifibg,'wifibg',modelid);

	</script>
</body>
</html>
