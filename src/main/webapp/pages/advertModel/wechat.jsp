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
<title>绍兴公交 微信WIFI登陆界面</title>
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
					<button type="text" class="btn btn-primary block full-width m-b"
						id="authId">微WIFI</button>
			</div>
		</div>
	</div>
	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script src="${rootUrl}/components/md5/md5.js"></script>
	<script type="text/javascript">
	
	var  appId='<%=request.getParameter("appId")%>';
	var  extend='<%=request.getParameter("extend")%>';
	var  shop_id='<%=request.getParameter("shop_id")%>';
	var  authUrl='<%=request.getParameter("authUrl")%>';
	var  ssid='<%=request.getParameter("ssid")%>';
	var  bssid='<%=request.getParameter("bssid")%>';
	var  secretkey='<%=request.getParameter("secretkey")%>';
	var  mac='<%=request.getParameter("mac")%>';
	var  gwIp='<%=request.getParameter("gwIp")%>';
	var  gwPort='<%=request.getParameter("gwPort")%>';
	var  userUrl='<%=request.getParameter("userUrl")%>';
	var flag='<%=request.getParameter("flag")%>';
	
	var extendss = extend.split(',');
	//注册回调函数
	function callback(result){
		if(result.code==200){
			OpenWeiXin();
		}else{
			alert('临时放行失败')
		}
	}
	$("#authId").click(function(){
		var ttoken=extendss[1];
		var tempneturl='http://'+gwIp+':'+gwPort+'/wifidog/tr?ttoken='+ttoken;
		var script = document.createElement('script');
		script.setAttribute('src', tempneturl);  
		document.getElementsByTagName('head')[0].appendChild(script);
		
		
	});
	// 先进行验证临时放行，然后再去微信验证
    function OpenWeiXin(){
        //通过dom操作创建script节点实现异步请求
		var date = new Date();
        var timestamp=date.getTime();
		var _url=decodeURIComponent(authUrl);
		var _authUrl=decodeURIComponent(extendss[0]);
		//extend:APip|APport|Apmac|终端mac|用户请求URL|用户认证AP的URL
		var _extendsurl=gwIp+"|"+gwPort+"|"+bssid+"|"+mac+"|"+_authUrl+"|"+userUrl+"|"+flag;
		var sign = hex_md5(appId + _extendsurl + timestamp + shop_id + _url + mac + ssid + bssid + secretkey);
		
		Wechat_GotoRedirect(
					appId,
					_extendsurl,
					timestamp,
					sign,
					shop_id,
					_url,
 					mac,
					ssid,                 
					bssid);
    }
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
//	wechat=============================================
//	/**
//	 * 微信连Wi-Fi协议3.1供运营商portal呼起微信浏览器使用
//	 */
	 var loadIframe = null;
	 var noResponse = null;
	 function errorJump()
	 {
		 var now = new Date().getTime();
		 if((now - callUpTimestamp) > 4*1000){
			 return;
		 }
		 alert('该浏览器不支持自动跳转微信请手动打开微信\n如果已跳转请忽略此提示');
	 }
	 function createIframe(){
		 var iframe = document.createElement("iframe");
	     iframe.style.cssText = "display:none;width:0px;height:0px;";
	     document.body.appendChild(iframe);
	     loadIframe = iframe;
	 }
	//注册回调函数
	function jsonpCallback(result){
		if(result && result.success){
			var ua=navigator.userAgent;              
			if (ua.indexOf("iPhone") != -1 ||ua.indexOf("iPod")!=-1||ua.indexOf("iPad") != -1){   //iPhone   
				//IOS局部刷新嗅探
				createIframe();
				loadIframe.src='http://www.taobao.com'
				 setTimeout(function () {
	                document.location = result.data;
	            }, 1000);
			}else{
			    createIframe();
			    loadIframe.src=result.data;
				noResponse = setTimeout(function(){
					errorJump();
		      	},3000);
			}
		}else if(result && !result.success){
			alert(result.data);
		}
	}
//	appId	是	商家微信公众平台账号
//	extend	是	extend里面可以放开发者需要的相关参数集合，最终将透传给运营商认证URL。extend参数只支持英文和数字，且长度不得超过300个字符。
//	timestamp	是	时间戳使用毫秒
//	sign	是	请求参数签名，具体计算方法见下方说明
//	shopId	是	AP设备所在门店的ID，即shop_id
//	authUrl	是	认证服务端URL，微信客户端将把用户微信身份信息向此URL提交并获得认证放行*
//	mac	安卓设备必需	用户手机mac地址，格式冒号分隔，字符长度17个，并且字母小写，例如：00:1f:7a:ad:5c:a8
//	ssid	是	AP设备的无线网络名称
	function Wechat_GotoRedirect(appId, extend, timestamp, sign, shopId, authUrl, mac, ssid, bssid){
		
		//将回调函数名称带到服务器端
		var url = "https://wifi.weixin.qq.com/operator/callWechatBrowser.xhtml?appId=" + appId 
																			+ "&extend=" + extend 
																			+ "&timestamp=" + timestamp 
																			+ "&sign=" + sign;
		//如果sign后面的参数有值，则是新3.1发起的流程
		if(authUrl && shopId){
			
			
			url = "https://wifi.weixin.qq.com/operator/callWechat.xhtml?appId=" + appId 
																			+ "&extend=" + extend 
																			+ "&timestamp=" + timestamp 
																			+ "&sign=" + sign
																			+ "&shopId=" + shopId
																			+ "&authUrl=" + encodeURIComponent(authUrl)
																			+ "&mac=" + mac
																			+ "&ssid=" + ssid
																			+ "&bssid=" + bssid;
		}			
		
		//通过dom操作创建script节点实现异步请求  
		var script = document.createElement('script');  
		script.setAttribute('src', url);  
		document.getElementsByTagName('head')[0].appendChild(script);
	}
	</script>
</body>
</html>
