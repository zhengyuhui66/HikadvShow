<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<meta name='apple-itunes-app' content='app-id=477927812'>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/bootstrap.min.css"rel="stylesheet"></link>
<link href="${rootUrl}/components/bootstrap-3.3.5-dist/css/style.css"rel="stylesheet"></link>
<style type='text/css'>
.nbgimage{
	background:url('ningbo2.jpg') no-repeat center fixed;
	background-size:100% 100%;
}
</style>
<title>宁波城市公交</title>
</head>
<body class='nbgimage'>
<div id="buttonId" style="position:absolute;left:50%">
	<button type="text" id="downLoadId" style="display:block;background:#328FDC;border:1px solid white;border-radius:3px;"><font size="4" face="arial" color="white"><a>下载 iNingbo</a></font></button>
	<button type="text" id="openId" style="margin-top:15px;background:#328FDC;border:1px solid white;border-radius:3px; "><font size="4" face="arial" color="white"><a>打开 iNingbo</a></font></button>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
	var scale=340/640;
	var clientWidth=document.body.clientWidth;
	var width=clientWidth*scale;
	var heigthscale=65/1060;
	var clientHeight=document.body.clientHeight;
	var height=heigthscale*clientHeight;
	$("#openId").css('width',width);
	$("#openId").css('height',height);
	$("#openId").css('margin-left',-(width/2));
	$("#downLoadId").css('width',width);
	$("#downLoadId").css('height',height);
	$("#downLoadId").css('margin-left',-(width/2));
	var ttop=(1-440/1060)*100;
	$("#buttonId").css('top',ttop+'%');
	function createIframe(){
		 var iframe = document.createElement("iframe");
	     iframe.style.cssText = "display:none;width:0px;height:0px;";
	     document.body.appendChild(iframe);
	     loadIframe = iframe;
	 }
	$("#openId").click(function(){
		createIframe();
		window.location="iningbo://iningbo.com/";
		setTimeout(function(){
			   //此处如果执行则表示没有app
			   alert('请手工打开APP');
// 			window.location="http://m.5iningbo.com/wap/app";
		},5000);
	});
	
	$("#downLoadId").click(function(){
		window.location="http://m.5iningbo.com/wap/app";
	});
</script>
</body>
</html>
