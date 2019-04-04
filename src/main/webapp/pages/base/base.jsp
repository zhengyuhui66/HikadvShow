<%@ page language="java" contentType="text/html;charset=utf-8"%>  
<%@page import="com.hik.framework.utils.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="rootUrl" value="<%=CommonUtil.getFullWebContext(request) %>" />
<c:set var="rootIp" value="<%=request.getLocalAddr() %>" />
<script src="${rootUrl}/components/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${rootUrl}/components/extjs/resources/css/ext-all-neptune.css"/>
<link rel="stylesheet" type="text/css" href="${rootUrl}/css/common.css"/>
<script src="${rootUrl}/components/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${rootUrl}/components/extjs/ext-all.js" type="text/javascript"></script>
<script src="${rootUrl}/components/json2.js" type="text/javascript"></script>

<script type="text/javascript">
var rootUrl='${rootUrl}';
var rootIp='${rootIp}';
Ext.create('Ext.window.MessageBox');
var defaultPageSize=10;
var getHaveRight = function(desc){
	var resultt=false;
	$.ajax({
		type : "GET",  //提交方式  
		url : rootUrl+'/menuController/getHaveRight',//路径  
		async:false,
		data : {
			description:desc
		},//数据，这里使用的是Json格式进行传输  
		success : function(response) {
		        var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

var getAllParentUser=function(userId){
	var resultt=null;
	$.ajax({
		type : "GET",  //提交方式  
		url : rootUrl+'/userController/getParentUser',//路径  
		async:false,
		data : {
			userid:userId
		},//数据，这里使用的是Json格式进行传输  
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}


var getSubUser=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/materialController/getSubUser',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}
// getPassWord  getUserName  getTelphone  getEmail  getCreateTime getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getUserId=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getUserId',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}
//  getUserName  getTelphone  getEmail  getCreateTime getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getPassWord=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getPassWord',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}
//getUserName  getTelphone  getEmail  getCreateTime getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getUserName=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getUserName',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

//    getEmail  getCreateTime getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getTelphone=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getTelphone',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}
//  getCreateTime getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getEmail=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getEmail',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

// getLoginTime getLastLoginTime getLoginTimes getTrid getUUID
var getCreateTime=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getCreateTime',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

// getLastLoginTime getLoginTimes getTrid getUUID
var getLoginTime=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getLoginTime',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

// getLoginTimes getTrid getUUID
var getLastLoginTime=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getLastLoginTime',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

// getTrid getUUID
var getLoginTimes=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getLoginTimes',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

// getUUID
var getTrid=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getTrid',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

//
var getUUID=function(){
	var resultt=null;
	$.ajax({  
		type : "GET",  //提交方式  
		url : rootUrl+'/getCommon/getUUID',//路径  
		async:false,
		success : function(response) {
		    var respText = Ext.JSON.decode(response);
			resultt=respText.data;
		}
	});  
	return resultt;
}

var exportExcel=function(me,url,title){
	var c=me.columns;
	var obj= new Object();
	for(var i=0;i<c.length;i++){
		if(!c[i].hidden){
			obj[c[i].dataIndex]=c[i].text;
		}
	}
	window.location=url+"title="+title+"&rowtitles="+JSON.stringify(obj)+"&start=-1&limit=-1";
}

Date.prototype.format = function(format)
	{
		 var o = {
		 "M+" : this.getMonth()+1, //month
		 "d+" : this.getDate(),    //day
		 "h+" : this.getHours(),   //hour
		 "m+" : this.getMinutes(), //minute
		 "s+" : this.getSeconds(), //second
		 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		 "S" : this.getMilliseconds() //millisecond
		 }
		 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		 for(var k in o)if(new RegExp("("+ k +")").test(format))
		 format = format.replace(RegExp.$1,
		 RegExp.$1.length==1 ? o[k] :
		 ("00"+ o[k]).substr((""+ o[k]).length));
		 return format;
	}
</script>
