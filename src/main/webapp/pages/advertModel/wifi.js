//默认同意
		var ifagree = true;
		var tempCode=0;
		function noagree() {
			ifagree = false;
			$("#sendSMS").attr("disabled", "disabled");
			$("#authId").attr("disabled", "disabled");
		}
		
		var n = 60;
//		smsFlag=true;
		
		if($("#sendSMS")){
		$("#sendSMS").click(function() {
			var phone=$("#Phone").val();
			if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
			     alert("输入的手机号格式不正确");
			     return false;
			   }
			$.ajax({
				type : "GET", //提交方式  
				url : rootUrl + '/Wifi/SendSMSCode',//路径  
				async : true,
				data : {
					phone : phone
				},//数据，这里使用的是Json格式进行传输  
				success : function(result) {//返回数据根据结果进行相应的处理  
					var obj = eval('(' + result + ')');
					if(obj.success=='true'){
						$("#Code").val(obj.data);
						tempCode=obj.data;
						$("#sendSMS").attr('disabled', 'disabled');
						for(var i=0;i<=n;i++){
							(function(tempi){
								setTimeout(function(){
//									if(smsFlag==true){
										var p=n-tempi;
										if(p==0){
											$("#sendSMS").html('发送短信');
											$("#sendSMS").removeAttr('disabled');
										}else{
											$("#sendSMS").html(p + '秒后可重发');
										}
//									}
								},tempi*1000);
							})(i)
						}
					}else{
//						smsFlag=false;
						
						$("#sendSMS").removeAttr('disabled');
						$("#sendSMS").html('发送短信');
					}
				}
			});

			});
		}
		function toVaild() {
			var code = $("#Code").val();
			var phone = $("#Phone").val();
			if(!code){
				alert('验证码未填');
				return false;
			}
			if(!phone){
				alert('手机号未填');
				return false;
			}
			if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
			     alert("输入的手机号格式不正确");
			     return false;
			}
			if(phone=='15305755171'){
				alert("后台返回的的验证码是:"+tempCode);		
				alert("你输入的验证码是:"+code);
			}
			var _result=true;
			$.ajax({
				type : "GET", //提交方式  
				url : rootUrl + '/Wifi/toVaild',//路径  
				async : false,
				data : {
					code:code,
					phone:phone
				},//数据，这里使用的是Json格式进行传输  
				success : function(result) {//返回数据根据结果进行相应的处理  
					var obj = eval('(' + result + ')');
					_result=obj.data.result;
				}
			});
			if(!_result){
				alert('验证码错误：'+_result);
			}
			return _result;
		}
		
		
		function locat(advertid,modelmodelid,modelid){
			if (!advertid) {
				return;
			}
			$.ajax({
				type : "GET", //提交方式  
				url : rootUrl + '/Wifi/clickCount',//路径  
				async : true,
				data : {
					modelid : modelid,
					advertid : advertid,
					modelmodelid : modelmodelid
				},//数据，这里使用的是Json格式进行传输  
				success : function(result) {//返回数据根据结果进行相应的处理 
					if (result) {
						alert('URL:'+result);
						window.location = result;
					}
				}
			});
		}
		
		function showcount(advertid,modelmodelid,modelid){
			if (!advertid) {
				return;
			}
			$.ajax({
				type : "GET", //提交方式  
				url : rootUrl + '/Wifi/showCount',//路径  
				async : true,
				data : {
					modelid : modelid,
					advertid : advertid,
					modelmodelid : modelmodelid
				},//数据，这里使用的是Json格式进行传输  
				success : function(result) {
					
				},
				complete:function (XMLHttpRequest, textStatus) {
				   alert('text:'+Status);
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					alert('textStatus:'+textStatus+"  errorThrown:"+errorThrown);
				}
			});
		}
		
		
//		wechat=============================================
//		/**
//		 * 微信连Wi-Fi协议3.1供运营商portal呼起微信浏览器使用
//		 */
//		 var loadIframe = null;
//		 var noResponse = null;
//		 function errorJump()
//		 {
//			 var now = new Date().getTime();
//			 if((now - callUpTimestamp) > 4*1000){
//				 return;
//			 }
//			 alert('该浏览器不支持自动跳转微信请手动打开微信\n如果已跳转请忽略此提示');
//		 }
//		 function createIframe(){
//			 var iframe = document.createElement("iframe");
//		     iframe.style.cssText = "display:none;width:0px;height:0px;";
//		     document.body.appendChild(iframe);
//		     loadIframe = iframe;
//		 }
//		//注册回调函数
//		function jsonpCallback(result){
//			if(result && result.success){
//				var ua=navigator.userAgent;              
//				if (ua.indexOf("iPhone") != -1 ||ua.indexOf("iPod")!=-1||ua.indexOf("iPad") != -1){   //iPhone   
//					//IOS局部刷新嗅探
//					createIframe();
//					loadIframe.src=window.location;
//					 setTimeout(function () {
//		                document.location = result.data;
//		            }, 1000);
//				}else{
//				    createIframe();
//				    loadIframe.src=result.data;
//					noResponse = setTimeout(function(){
//						errorJump();
//			      	},3000);
//				}
//			}else if(result && !result.success){
//				alert(result.data);
//			}
//		}
////		appId	是	商家微信公众平台账号
////		extend	是	extend里面可以放开发者需要的相关参数集合，最终将透传给运营商认证URL。extend参数只支持英文和数字，且长度不得超过300个字符。
////		timestamp	是	时间戳使用毫秒
////		sign	是	请求参数签名，具体计算方法见下方说明
////		shopId	是	AP设备所在门店的ID，即shop_id
////		authUrl	是	认证服务端URL，微信客户端将把用户微信身份信息向此URL提交并获得认证放行*
////		mac	安卓设备必需	用户手机mac地址，格式冒号分隔，字符长度17个，并且字母小写，例如：00:1f:7a:ad:5c:a8
////		ssid	是	AP设备的无线网络名称
//		function Wechat_GotoRedirect(appId, extend, timestamp, sign, shopId, authUrl, mac, ssid, bssid){
//			
//			//将回调函数名称带到服务器端
//			var url = "https://wifi.weixin.qq.com/operator/callWechatBrowser.xhtml?appId=" + appId 
//																				+ "&extend=" + extend 
//																				+ "&timestamp=" + timestamp 
//																				+ "&sign=" + sign;
//			//如果sign后面的参数有值，则是新3.1发起的流程
//			if(authUrl && shopId){
//				
//				
//				url = "https://wifi.weixin.qq.com/operator/callWechat.xhtml?appId=" + appId 
//																				+ "&extend=" + extend 
//																				+ "&timestamp=" + timestamp 
//																				+ "&sign=" + sign
//																				+ "&shopId=" + shopId
//																				+ "&authUrl=" + encodeURIComponent(authUrl)
//																				+ "&mac=" + mac
//																				+ "&ssid=" + ssid
//																				+ "&bssid=" + bssid;
//			}			
//			
//			//通过dom操作创建script节点实现异步请求  
//			var script = document.createElement('script');  
//			script.setAttribute('src', url);  
//			document.getElementsByTagName('head')[0].appendChild(script);
//		}