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
	var  gw_address='<%=request.getParameter("gw_address")%>';
	var  gw_port='<%=request.getParameter("gw_port")%>';
	var  gw_id='<%=request.getParameter("gw_id")%>';
	var  mac='<%=request.getParameter("mac")%>';
	var  url='<%=request.getParameter("url")%>';
	
	
	str=rootUrl+"/Wifi/logining?gw_address="+gw_address+"&gw_port="+gw_port+"&gw_id="+gw_id+"&mac="+mac+"&url="+decodeURIComponent(url);//+URLEncoder.encode(url, "UTF-8");

	if(mac=='d0:33:11:51:48:79'){
		alert(str);
	}
	
	window.location=str;
	
</script>
</body>
</html>
