<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/base/pbase.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
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
<link href="${rootUrl}/<%=request.getParameter("modelskin")%>"
	rel="stylesheet"></link>
<html>
<head>
<style type="text/css">
#mid {
	margin: 0px 20px;
	background: #ddd;
	font-size: 20px;
}

div.rtop {
	display: block;
	background: white;
}

div.rtop div {
	display: block;
	height: 1px;
	overflow: hidden;
	background: #ddd;
}

div.r1 {
	margin: 0 3px;
}

div.r2 {
	margin: 0 2px;
}

div.r3 {
	margin: 0 1px;
}

div.rtop div.r4 {
	margin: 0 1px;
	height: 1px;
}
</style>
<title>绍兴公交WIFI登陆界面</title>
</head>
<body class="gray-bg" id="wifibgId">


	<div id="form-group"></div>

	<div id="mid">
		<div class="rtop">
			<div class="r1"></div>
			<div class="r2"></div>
			<div class="r3"></div>
			<div class="r4"></div>
		</div>
		<div>
			<form class="m-t" role="form"
					method="post" action="/Wifi/logined" style="text-align: center">
				<span><label><input id="cb_agree" type="checkbox"
						checked='true' onclick='onCkBoxClicked()' />同意上网条例</label><a
					onclick="showProc()">查看条款</a></span>
				<button type="submit" class="btn btn-style block full-width m-b"
					id="authId">一键登录</button>

			</form>
		</div>
		<div class="rtop">
			<div class="r4"></div>
			<div class="r3"></div>
			<div class="r2"></div>
			<div class="r1"></div>
		</div>
	</div>



	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-hidden="true" data-backdrop="static"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 330px; height: 245px; margin: 10px auto;">
			<div class="modal-content" align="center">
				<h4 style="margin-top: 20px;">协议条款:</h4>
				<div
					style="margin-top: 20px; width: 310px; height: 245px; overflow-y: auto; margin-right: 20px;"
					align="left">
					<ol>
						<li>用户自行承担使用WiFi服务的全部责任。用户承诺：</br>
							1):用户使用WiFi服务时必须遵守所有适用于WiFi服务的国家和地方性法律、法规和国家法律，不得在网页上或者利用WIFI服务制作、复制、发布、传播上述法律、法规所禁止的内容。</br>
							2):不利用WiFi服务从事危害计算机信息网络安全的活动</br>
							3):禁止利用各种方式干扰WiFi服务运营商网络的正常运转以及其他用户正常使用WiFi服务。</br>
							4):不得将WiFi服务用于商业或者其他盈利用途。</br> 5):遵守WiFi服务建设方及服务运营商的所有其他规定和程序。</br>
						</li>
						<li>
							用户对自己在使用WiFi服务过程中的行为所承担的法律责任包括但不限于对受到侵害者进行赔偿，在WiFi服务建设方及服务运营商首先承担了因用户行为导致的行政处罚或侵权损害赔偿责任后，用户须给予WiFi服务建设方及服务运营商等额的赔偿。
						</li>
						<li>
							WiFi服务建设方及服务运营商保留判定用户行为是否符合本服务条款的权利，如果WiFi服务建设方及服务运营商发现用户所传输的信息存在违反法律法规及本免责声明中用户所承诺的情形,依据相关法律法规，WiFi服务运营商有权立即停止传输并将在保存有关记录后向国家有关机关报告，并删除含有上述信息的地址及目录、中断直至终止提供WiFi服务。
						</li>
						<li>本免责声明中的条款应符合中国的法律、法规，与法律、 法规相抵触的部分无效，但不影响其他部分的效力。</li>
					</ol>
					<span style="float: right;">绍兴市公交集团车载4G-WiFi运营中心</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="weagree()">同意</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="noagree();">不同意</button>
				</div>
			</div>
		</div>
	</div>
	</div>


	<script src="${rootUrl}/components/jquery/jquery-2.2.3.min.js"></script>
	<script	src="${rootUrl}/components/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="${rootUrl}/components/layer-v2.3/layer/layer.js"></script>
	<script src="${rootUrl}/pages/advertModel/wifi.js"></script>
	<script>
	//请求的连接地址demo
	//http://10.10.4.92:7080/Hikadv/pages/advertModel/oneKeySingleAd.jsp?modelskin=css/advertModel/model_b.css&adHeader="热烈庆祝诸暨万达广场盛大开盘"&
	//adImage="http://image4.cityhouse.cn/shaoxing/images/ha/large/pa0003359ZJ110_1447132702486306.jpg"&adDesc="<span>房价:</span><span><em>7,1
	//00元/㎡起</em></span>","<span>地址:%20浙江省%20诸暨市%20东三环路</span>","<span>电话:%20400-869-1111%20转%2091197</span>"
	
		//var ad_header="热烈庆祝诸暨万达广场盛大开盘！";
		//var ad_img="http://image4.cityhouse.cn/shaoxing/images/ha/large/pa0003359ZJ110_1447132702486306.jpg";
		//var ad_desc=["<span>房价:</span><span><em>7,100元/㎡起</em></span>","<span>地址: 浙江省 诸暨市 东三环路</span>","<span>电话: 400-869-1111 转 91197</span>"];
		var ad_header =
	<%=request.getParameter("adHeader")%>
		;
		var ad_img =
	<%=request.getParameter("adImage")%>
		;
		var ad_desc = new Array(
	<%=request.getParameter("adDesc")%>
		);

		var contentDesc = "<img  style='left:0px;top:0px;position:absolute'  src='http://static.googleadsserving.cn/pagead/images/cn/ad_aeaeae_065.svg'/>";
		if (ad_header) {
			contentDesc += "<div><h2 style='color:#1AB394;text-align:center'>"
					+ ad_header + "</h2></div><div>";
		}
		if (ad_img) {
			contentDesc += "<img  src='" + ad_img
					+ "' style='width:100%'/></div>";
		}
		if (ad_desc) {
			contentDesc += "<div style='text-align:center; font-weight: bold; font-size: 19px;color:#C50000;'>";
			for (var i = 0; i < ad_desc.length; i++) {
				contentDesc += "<p style='border:1px solid #e8e8e8;border-left: none;border-top:none;'>"
						+ ad_desc[i] + "</p>"
			}
			contentDesc += "</div>";
		}

		if (contentDesc) {
			$("#form-group").append(contentDesc);
		}
		//默认同意
		var ifagree = true;
		function weagree() {
			ifagree = true;
			$('#cb_agree').prop('checked', true);
			$('#authId').attr('disabled', false);
		}
		function noagree() {
			ifagree = false;
			$('#authId').attr('disabled', true);
			$('#cb_agree').prop('checked', false);
		}

		function showProc() {
			$('#exampleModal').modal();//展示协议条款
		}
		function onCkBoxClicked() {
			if ($('#cb_agree').is(':checked')) {
				$('#cb_agree').prop('checked', true);
				$('#authId').attr('disabled', false);
			} else {
				$('#cb_agree').prop('checked', false);
				$('#authId').attr('disabled', true);
			}
		}
		var materurlwifi1 =<%=request.getParameter("materurlwifi1")%>;
		//获取模版ID
		var modelid=<%=request.getParameter("modelid")%>;
		showcount(advertidwifi1,'wifi1',modelid);
		
	</script>
</body>
</html>