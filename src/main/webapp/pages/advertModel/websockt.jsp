<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/pages/base/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>
  </head>
  <script type="text/javascript">
  var socket;
  
  function tcp(){
	  if(!window.WebSocket){
		  window.WebSocket = window.MozWebSocket;
	  }
	  if(window.WebSocket){
		  socket = new WebSocket("ws://218.108.10.25:7397/websocket");
		  socket.onmessage = function(event){
		  };
		  socket.onopen = function(event){
				var ta = document.getElementById('responseText');
				ta.value = "打开WebSoket 服务正常，浏览器支持WebSoket!"+"\r\n";
				$.ajax({
					type : "GET", //提交方式  
					url : rootUrl + '/devicemangercontroller/setdevicegpssupport',//路径  
					async : true,
					data : {
						equipmentids : "{\"14306073X00037F93F4A9\":\"300\"}",
							flag:'1'
					},//数据，这里使用的是Json格式进行传输  
					success : function(result) {//返回数据根据结果进行相应的处理  
						var obj = eval('(' + result + ')');
						if(obj.success=='true'){
							
						}else{
							
						}
					}
				});
		  };
	 
		  socket.onclose = function(event){
		  };
	  }else{
			alert("您的浏览器不支持WebSocket协议！");
	  }
  }
  function send(message){
	if(!window.WebSocket){return;}
	if(socket.readyState == WebSocket.OPEN){
		socket.send(message);
	}else{
		alert("WebSocket 连接没有建立成功！");
	}
	
  }
	  
  </script>
  <body>
    <form onSubmit="return false;">
    	<input type = "text" name="message" value="Netty The Sinper"/>
    	<br/><br/>
    	<input type="button" value="发送 WebSocket 请求消息" onClick="send(this.form.message.value)"/>
    	<input type="button" value="TCP链接" onClick="tcp()"/>
    	<hr color="blue"/>
    	<h3>服务端返回的应答消息</h3>
    	<textarea id="responseText" style="width: 1024px;height: 300px;"></textarea>
    </form>
  </body>
</html>