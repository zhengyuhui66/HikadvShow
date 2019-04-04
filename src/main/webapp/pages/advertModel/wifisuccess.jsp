<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/> 

<link
	href="${rootUrl}/components/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/style.css"
	rel="stylesheet"></link>
<link
	href="${rootUrl}/components/font-awesome-4.5.0/css/font-awesome.min.css"
	rel="stylesheet"></link>
<link href="${rootUrl}/components/layer-v2.3/layer/skin/layer.css"
	rel="stylesheet"></link>
<title>绍兴公交WIFI登陆成功界面</title>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div style="height: 700px;">
			<div id="wifisuccess1" style="height: 100px; width: 300px;"
				onclick="clicktrigerwifisuccess1();"></div>
			<div>
				<div style="height: 140px;">
					<h1 class="logo-name" id="wifiId">
						<i class="fa fa-hand-peace-o fa-4"
							style="color: rgb(26, 179, 148)"></i>
					</h1>
				</div>
				<div style="height: 40px; margin-top: 30px;">
					<a>认证成功...</a><a id='setextId'></a>
				</div>
			</div>
			<div id="wifisuccess2" style="height: 100px; width: 300px;"
				onclick="clicktrigerwifisuccess2();"></div>
		</div>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script
		src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
		<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
		var modelid =<%=request.getParameter("modelid")%>
		var t51 =<%=request.getParameter("materurlwifisuccess1")%>
		var t52 =<%=request.getParameter("materurlwifisuccess2")%>
		var advertidwifisuccess1 =<%=request.getParameter("advertidwifisuccess1")%>
		var advertidwifisuccess2 =<%=request.getParameter("advertidwifisuccess2")%>


		var n = 15;
		var url =<%=request.getParameter("url")%>
		if (t51) {
			$("#wifisuccess1").css('background-image', "url('" + t51 + "')");
		}
		if (t52) {
			$("#wifisuccess2").css('background-image', "url('" + t52 + "')");
		}
		for(var i=0;i<=n;i++){
			(function(tempi){
				setTimeout(function(){
					var p=n-tempi;
					if(p==0){
						if (url) {
							window.location = url;
						} else {
							$("#setextId").html('');
						}
					}else{
						$("#setextId").html(p + '秒自动跳转');
					}
					
				},tempi*1000);
				
			})(i);
			
		}
		//统计展示次数
		showcount(advertidwifisuccess1,'wifisuccess1',modelid);
		showcount(advertidwifisuccess2,'wifisuccess2',modelid);
		function clicktrigerwifisuccess1() {
			locat(advertidwifisuccess1,"wifisuccess1");
		}

		function clicktrigerwifisuccess2() {
			locat(advertidwifisuccess2,"wifisuccess2");
		}
	</script>
</body>
</html>
