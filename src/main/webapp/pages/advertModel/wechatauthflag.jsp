<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<%@ include file="/pages/base/pbase.jsp"%>
<title>绍兴公交 微信WIFI登陆界面</title>
</head>

<body>
	<div>
			<div style="height: 490px">
				<h3>正在跳转.......</h3>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var  gwIp='<%=request.getParameter("gwIp")%>';
	var  gwPort='<%=request.getParameter("gwPort")%>';
	var  gwMac='<%=request.getParameter("gwMac")%>';
	var  clientMac='<%=request.getParameter("clientMac")%>';
	var  url='<%=request.getParameter("url")%>';
	var  authType='<%=request.getParameter("authType")%>';
	var  appId='<%=request.getParameter("appId")%>';
	var  extend='<%=request.getParameter("extend")%>';
	var  shopId='<%=request.getParameter("shopId")%>';
	var  ssid='<%=request.getParameter("ssid")%>';
// wIp="+gwIp+"&gwPort="+gwPort+"&gwMac="+gwMac+"&clientMac="+clientMac+"&url="+URLEncoder.encode(url, "UTF-8")
// +"&authType="+authType+"&appId="+appId+"&extend="+extend+"&shopId="+shopId+"&ssid="+ssid;
	var str = rootUrl+"/Wifi/logining?gwIp="+gwIp+"&gwPort="+gwPort+"&gwMac="+gwMac+"&clientMac="+clientMac+"&url="+decodeURIComponent(url)
	+"&authType="+authType+"&appId="+appId+"&extend="+extend+"&shopId="+shopId+"&ssid="+ssid;
	if(clientMac=='d0:33:11:51:48:79'){
		alert(str);
	}
	
	window.location=str;
// 			gw_address="+gw_address+"&gw_port="+gw_port+"&gw_id="+gw_id+"&mac="+mac+"&url="+decodeURIComponent(url);//+URLEncoder.encode(url, "UTF-8");
</script>
</body>
</html>
