<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/> 

<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/style.css" rel="stylesheet"></link>
<link href="${rootUrl}/components/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet"></link>
<link href="${rootUrl}/components/layer-v2.3/layer/skin/layer.css" rel="stylesheet"></link>
<link href="${rootUrl}/<%=request.getParameter("modelskin")%>" rel="stylesheet"></link>
<title>绍兴公交WIFI登陆成功界面</title>
</head>
<body class="gray-bg">
<div  id="wifibg"  style="width:100%;height:100%;" onclick="clickwifibg();">
	<div style="text-align:center;position:absolute;top:50%;bottom:50%;left:0px;right:0px">
		<a>认证成功...</a><a id='setextId'></a>
	</div>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-1.9.1.min.js"></script>
	<script src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
		var modelid =<%=request.getParameter("modelid")%>;
		
		var materurlwifibg =<%=request.getParameter("materurlwifibg")%>;
		var advertidwifibg =<%=request.getParameter("advertidwifibg")%>;
		
		var materurlwifibg2 =<%=request.getParameter("materurlwifibg2")%>;
		var advertidwifibg2 =<%=request.getParameter("advertidwifibg2")%>;
		
		var materurlwifibg3 =<%=request.getParameter("materurlwifibg3")%>;
		var advertidwifibg3 =<%=request.getParameter("advertidwifibg3")%>;
		var flagnum=1;
		var n = 15;
		$('#wifibg').css("background","url('"+materurlwifibg+"') no-repeat center fixed");
		$('#wifibg').css("background-size","100% 100%");
		for(var i=0;i<=n;i++){
			(function(tempi){
				setTimeout(function(){
					var p=n-tempi;
					if(p==0){
						var url =<%=request.getParameter("url")%>;
						var _url=decodeURIComponent(url);
						if (_url) {
							window.location = _url;
						} else {
							$("#setextId").html('');
						}
					}else{
						$("#setextId").html(p + '秒自动跳转');
						if(p==15){
								flagnum=1;
							showcount(advertidwifibg,'wifibg',modelid);
						}else if(p==10){		
							$('#wifibg').css("background","url('"+materurlwifibg2+"') no-repeat center fixed");
							$('#wifibg').css("background-size","100% 100%");
							flagnum=2;
							showcount(advertidwifibg2,'wifibg',modelid);
						}else if(p==5){
							$('#wifibg').css("background","url('"+materurlwifibg3+"') no-repeat center fixed");
							$('#wifibg').css("background-size","100% 100%");
							flagnum=3;
							showcount(advertidwifibg3,'wifibg',modelid);
						}
					}
				},tempi*1000);
			})(i);
		}
		//统计次数
		
		function clickwifibg(){
			if(flagnum==1){
				locat(advertidwifibg,"wifibg",modelid);				
			}else if(flagnum==2){
				locat(advertidwifibg2,"wifibg2",modelid);				
			}else if(flagnum==3){
				locat(advertidwifibg3,"wifibg3",modelid);
			}
		}
	</script>
</body>
</html>
