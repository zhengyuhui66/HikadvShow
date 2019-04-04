<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=1.0, maximum-scale=1.0"/> 
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/bootstrap.min.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/style.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/font-awesome-4.5.0/css/font-awesome.min.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/layer-v2.3/layer/skin/layer.css"rel="stylesheet"></link>
<title>绍兴公交WIFI登陆界面</title>
</head>

<body class="gray-bg middle-box text-center loginscreen">
<!-- 	<div class=""> -->
			<div id="wifi1" style="height: 70px;" advertidwifi1="">
			</div>
			<div id="wifi2" style="height: 140px;" advertidwifi2="">
				<h1 class="logo-name" id="wifiId" style="">
					<i class="fa fa-wifi" style="color: rgb(26, 179, 148);margin-bottom:100px;"></i>
				</h1>
			</div>
			<div style="height: 490px">
				<h3>wifi上网身份认证</h3>
				<form class="m-t" role="form" onsubmit="return toVaild()"
					method="post" action="/Wifi/logined">
					<div class="form-group">
						<div class="input-group">
							<input id="Phone" name="Phone" type="text" class="form-control"
								placeholder="手机号码" required="" /> <span class="input-group-btn">
								<button type="button" id="sendSMS"
									class="btn btn-primary btn-sm btn-sendsms">发送短信</button>
							</span>
						</div>
					</div>
					<div class="form-group">
						<input id="Code" name="Code" type="text" class="form-control"
							placeholder="短信验证码" required="" maxlength="6">
					</div>
					<button type="submit" class="btn btn-primary block full-width m-b"
						id="authId">认 证</button>
					<div class="form-group">
						<div class="validation-summary-valid" data-valmsg-summary="true">
							<ul>
								<li style="display: none"></li>
							</ul>
						</div>
					</div>
					<input id="ID" name="ID" type="hidden" value="00--0-0--00--0-0-">
						<input id="Address" name="Address" type="hidden" value="xxx">
							<input id="Port" name="Port" type="hidden" value="80"> 
							<input id="UserMac" name="UserMac" type="hidden" value="XXXX">
							<input id="Url" name="Url" type="hidden" value="222">
				</form>
			</div>
<!-- 	</div> -->
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script
		src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<%-- 	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script> --%>
<%-- 	<script src="${rootUrl}/pages/advertModel/wifi.js"></script> --%>
</body>
</html>
