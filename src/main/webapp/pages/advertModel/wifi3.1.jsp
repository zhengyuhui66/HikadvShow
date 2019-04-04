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

<body class="gray-bg bgimage"  id="wifibgId">
			<div  style="text-align:center;border-radius: 20px;margin:0px 0px 0px 0px;">
				<img id="wifi2" src="" width='300' height='200'/>
				<h1 class="logo-name" id="wifiId">
					<i class="fa fa-wifi wificolor"></i>
				</h1>
			</div>
			<div class="middle-box text-center loginscreen  animated fadeInDown" style="padding-top:0px;">
			
			<div style="height: 190px">
				<h3 style="color:#fff">wifi上网身份认证</h3>
				<form class="m-t" role="form" onsubmit="return toVaild()"
					method="post" action="/Wifi/logined">
					<div class="form-group">
						<div class="input-group">
							<input id="Phone" name="Phone" type="text" class="form-control"
								placeholder="手机号码" required="" /> <span class="input-group-btn">
								<button type="button" id="sendSMS"
									class="btn btn-style btn-sm btn-sendsms">发送短信</button>
							</span>
						</div>
					</div>
					<div class="form-group">
						<input id="Code" name="Code" type="text" class="form-control"
							placeholder="短信验证码" required="" maxlength="6">
					</div>
					<button type="submit" class="btn btn-style block full-width m-b"
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
							<input id="Port" name="Port" type="hidden" value="80"> <input
								id="UserMac" name="UserMac" type="hidden" value="XXXX">
									<input id="Url" name="Url" type="hidden" value="222">
				</form>
			</div>
			
			
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document"  style="width:330px;height:245px;margin:10px auto;">
				<div class="modal-content" align="center" >
					<h4 style="margin-top:20px;">协议条款:</h4>
					<div style="margin-top: 20px;width:310px;height:245px;overflow-y:auto;margin-right:20px;" align="left">
						<ol>
							<li>用户自行承担使用WiFi服务的全部责任。用户承诺：</br>
								1):用户使用WiFi服务时必须遵守所有适用于WiFi服务的国家和地方性法律、法规和国家法律，不得在网页上或者利用WIFI服务制作、复制、发布、传播上述法律、法规所禁止的内容。</br>
								2):不利用WiFi服务从事危害计算机信息网络安全的活动</br>
								3):禁止利用各种方式干扰WiFi服务运营商网络的正常运转以及其他用户正常使用WiFi服务。</br>
								4):不得将WiFi服务用于商业或者其他盈利用途。</br>
								5):遵守WiFi服务建设方及服务运营商的所有其他规定和程序。</br>
							</li>
							<li>
								用户对自己在使用WiFi服务过程中的行为所承担的法律责任包括但不限于对受到侵害者进行赔偿，在WiFi服务建设方及服务运营商首先承担了因用户行为导致的行政处罚或侵权损害赔偿责任后，用户须给予WiFi服务建设方及服务运营商等额的赔偿。
							</li>
							<li>
								WiFi服务建设方及服务运营商保留判定用户行为是否符合本服务条款的权利，如果WiFi服务建设方及服务运营商发现用户所传输的信息存在违反法律法规及本免责声明中用户所承诺的情形,依据相关法律法规，WiFi服务运营商有权立即停止传输并将在保存有关记录后向国家有关机关报告，并删除含有上述信息的地址及目录、中断直至终止提供WiFi服务。
							</li>
							<li>本免责声明中的条款应符合中国的法律、法规，与法律、 法规相抵触的部分无效，但不影响其他部分的效力。</li>
						</ol>
						<span style="float:right;">绍兴市公交集团车载4G-WiFi运营中心</span>
                    </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">同意</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="noagree();">不同意</button>
					</div>
				</div>
			</div>
		</div>
	</div>
			<div id="wifi1" style="height:100px;position:absolute;bottom:0px;left:0px;right:0px;
			background-repeat:no-repeat;
			background-size:cover;" advertidwifi1="">
		</div>
	
	<script src="${rootUrl}/components/jquery/jquery-1.9.1.min.js"></script>
	<script src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
		$('#exampleModal').modal();

		
		//获取模版ID
		var modelid=<%=request.getParameter("modelid")%>;
		var materurlwifi1 =<%=request.getParameter("materurlwifi1")%>;
		var materurlwifi2 =<%=request.getParameter("materurlwifi2")%>;
		var wifibgId=<%=request.getParameter("materurlwifibgId")%>;
		var advertidwifi1 =<%=request.getParameter("advertidwifi1")%>;
		var advertidwifi2 =<%=request.getParameter("advertidwifi2")%>;
		if (!materurlwifi2) {
			$("#wifiId").css('display', 'inline');
			$("#wifi2").css('display', 'none');
		} else {
			$("#wifiId").css('display', 'none');
			$("#wifi2").css('display', 'inline');
			$("#wifi2").attr('src',materurlwifi2);
		}
		if(t51){
			$("#wifi1").attr('src',materurlwifi1);
		}
		showcount(advertidwifi1,'wifi1',modelid);
		showcount(advertidwifi2,'wifi2',modelid);

	</script>
</body>
</html>
