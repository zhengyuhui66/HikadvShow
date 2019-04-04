package com.hik.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.framework.utils.DateUtil;
import com.hik.framework.utils.JSONUtils;
import com.hik.service.VehicleWifiService3;
import com.hik.util.DataUtils;
import com.hik.util.PROCEDURCES;
import com.hik.util.SMSUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ��ԭʼ��һ��
 * wifi����2.0˵��
 * ��һ�� ����������֤
 * ��ʱһ����½
 * @author Administrator
 *
 */
@Controller
public class VehicleWifiController12 extends BaseController{
	
	@Autowired
	private VehicleWifiService3 vehicleWifiService;
    @Value("${serverhttp}")
	private String serverhttp;

    @Value("${defaultwifiauthflag}")
	private String defaultwifiauthflag;
	
	/**
	 * ��¼��֤
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/Wifi/login",method= RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		//AP��ַ
		//log.info("Wifi....login....");
		long startime = System.currentTimeMillis();
		HttpSession session = request.getSession();
		String gw_address=request.getParameter("gw_address");
		//AP�˿�
		String gw_port=request.getParameter("gw_port");
		//AP  MAC��ַ
		String gw_id=request.getParameter("gw_id");
		//�ͻ���MAC
		String mac=request.getParameter("mac");
		//�û������ַ	
		String url=request.getParameter("url");
		//log.info("gw_address:"+gw_address+"  gw_port:"+gw_port+"  gw_id:"+gw_id+" mac:"+mac+" url:"+url);
		if(gw_address==null||gw_port==null||gw_id==null||mac==null){
			//һ�����
			return null;
		}
		if(url!=null){
			url=URLEncoder.encode(url, "UTF-8");
		}
		String URL=serverhttp+defaultwifiauthflag+"?gw_address="+gw_address+"&gw_port="+gw_port+"&gw_id="+gw_id+"&mac="+mac+"&url="+url;
//		String URL=defaultwifiauthflag+"?gw_address="+gw_address+"&gw_port="+gw_port+"&gw_id="+gw_id+"&mac="+mac+"&url="+url;

		//�������
		return "redirect:"+URL;
	}
	@RequestMapping(value="/Wifi/login",method= RequestMethod.POST)
	public void loginPost(HttpServletRequest request,HttpServletResponse response){
	}
	/**
	 * ��¼��֤
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/Wifi/logining",method= RequestMethod.GET)
	public String logining(HttpServletRequest request,HttpServletResponse response){
		//AP��ַ
		long startime = System.currentTimeMillis();
		HttpSession session = request.getSession();
		String gw_address=request.getParameter("gw_address");
		//AP�˿�
		String gw_port=request.getParameter("gw_port");
		//AP  MAC��ַ
		String gw_id=request.getParameter("gw_id");
		//�ͻ���MAC
		String mac=request.getParameter("mac");
		//�û������ַ	
		String url=request.getParameter("url");
		if(gw_address==null||gw_port==null||gw_id==null||mac==null){
			return null;
		}
		session.setAttribute("gw_address",gw_address);
		session.setAttribute("gw_port",gw_port);
		session.setAttribute("gw_id",DataUtils.getMACStr(gw_id));
		session.setAttribute("mac",mac);
		String _url=null;
		try {
			if(url!=null){
				_url = URLEncoder.encode(url, "UTF-8");
				session.setAttribute("url",_url);
			}else{
				session.setAttribute("url","www.baidu.com");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String URL=null;
			//�������
			Happen happen = new Happen(null,DateUtil.getCurrentDate(),null,null);
			String apmac=DataUtils.getMACStr(gw_id);
			//log.debug("apmac::"+gw_id+"  ��ʽ����Ϊ"+apmac);
			URL=vehicleWifiService.login(DataUtils.getMACStr(gw_id),mac,session,happen);
			//log.info("������֤����==>:"+URL+"   �����ѵ�ʱ���ǣ�"+(System.currentTimeMillis()-startime));
		return "redirect:"+URL;
	}
	

	
	@RequestMapping(value="/Wifi/clickCount",method= RequestMethod.GET)
	public void clickCount(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String advertid=request.getParameter("advertid");
		String modelid=request.getParameter("modelid");
		String modelmodelid=request.getParameter("modelmodelid");
		String clickTime=DateUtil.getCurrentDate();
		
		Object tgw_id=session.getAttribute("gw_id");
		Object tmac=session.getAttribute("mac");
		Object tphone=session.getAttribute("phone");
		String gw_id=tgw_id==null?"":tgw_id.toString();
		String mac=tmac==null?"":tmac.toString();
		String phone=tphone==null?"":tphone.toString();
		String busid=null;
		String adverturl=null;
		String materid=null;
		String routeid=null;
//		List<JSONObject> authInfo=(List<JSONObject>) session.getAttribute("authInfo");
		
		Object authInfos=session.getAttribute("authInfo");
//		log.info("authInfos:::::::"+authInfos.toString());
		if(StringUtils.isBlank(advertid)||StringUtils.isBlank(modelid)||StringUtils.isBlank(modelmodelid)||authInfos==null){
			log.info("չʾ����Ϊ��Ч����");
			return;
		}
		List<JSONObject> authInfo = JSONArray.fromObject(authInfos);
		if(authInfo==null||authInfo.size()==0){
			log.info("չʾ����Ϊ��Ч�Ự");
			return;
		}
		boolean flag = true;
		//log.info("��ȡ�����ݼ��ǣ�"+authInfo);
		for(JSONObject json:authInfo){
			for(int i=1;i<=9;i++){
				String tadvertId = JSONUtils.getString(json, "advertid"+i);
				if(advertid.equals(tadvertId)){
					busid=JSONUtils.getString(json, "busid");
					adverturl=JSONUtils.getString(json, "adverturl"+i);
					materid=JSONUtils.getString(json, "materid"+i);
					routeid=JSONUtils.getString(json, "routeid");
					//log.info("��ȡ���������ϢΪ1:BUSID:"+busid+" ���URL:"+adverturl+" ����:"+materid+" ��·:"+routeid);
					flag=false;
					break;
				}
			}	
			if(flag==false){
				break;
			}
		}
		//log.info("��ȡ���������ϢΪ2:BUSID:"+busid+" ���URL:"+adverturl+" ����:"+materid+" ��·:"+routeid);
		CLICK_COUNT_LOG logg = new CLICK_COUNT_LOG(null, clickTime, busid, gw_id, phone, mac, modelid, modelmodelid, materid, adverturl, advertid, routeid);
		String URL=vehicleWifiService.saveClickCount(logg);
		//log.info( "�û�"+phone+"  ·����ID"+tgw_id+"  URL:"+adverturl);
		this.returnResponse(response, adverturl);
	}
	@RequestMapping(value="/Wifi/clickCount",method= RequestMethod.POST)
	public void clickCount2(HttpServletRequest request,HttpServletResponse response){
		return;
	}
	@RequestMapping(value="/Wifi/showCount",method= RequestMethod.GET)
	public void showCount(HttpServletRequest request,HttpServletResponse response){
		//log.info("show count");
		HttpSession session = request.getSession();
		String advertid=request.getParameter("advertid");
		String modelid=request.getParameter("modelid");
		String advposid=request.getParameter("modelmodelid");
		String clickTime=DateUtil.getCurrentDate();
		String busid=null;
		String adverturl=null;
		String materid=null;
		String routeid=null;
		//AP��ַ
		Object apmac=session.getAttribute("gw_id");
		//�ͻ��˵�ַ
		Object mac=session.getAttribute("mac");
		
		Object authInfos=session.getAttribute("authInfo");
		if(StringUtils.isBlank(advertid)){
			log.info("���IDΪ��,չʾ����Ϊ��Ч����");
			return;
		}
		
		if(StringUtils.isBlank(modelid)){
			log.info("���ģ��Ϊ��,չʾ����Ϊ��Ч����");
			return;
		}
		
		if(StringUtils.isBlank(advposid)){
			log.info("���λIDΪ��,չʾ����Ϊ��Ч����");
			return;
		}
		
		if(authInfos==null){
			log.info("SESSION���е���ϢΪ��,չʾ����Ϊ��Ч����");
			return;
		}
//		if(StringUtils.isBlank(advertid)||StringUtils.isBlank(modelid)||StringUtils.isBlank(advposid)||authInfos==null){
//			log.info("չʾ����Ϊ��Ч����");
//			return;
//		}
//		log.info("authInfos=======>"+authInfos.toString());
		List<JSONObject> authInfo = JSONArray.fromObject(authInfos);
		if(authInfo==null||authInfo.size()==0){
			log.info("չʾ����Ϊ��Ч�Ự");
			return;
		}
//		log.info("authInfos==>"+authInfos.toString());
		boolean flag = true;
		for(JSONObject json:authInfo){
			for(int i=1;i<=9;i++){
				String tadvertId = JSONUtils.getString(json, "advertid"+i);
				if(advertid.equals(tadvertId)){
					busid=JSONUtils.getString(json, "busid");
					adverturl=JSONUtils.getString(json, "adverturl"+i);
					materid=JSONUtils.getString(json, "materid"+i);
					routeid=JSONUtils.getString(json, "routeid");
					flag=false;
					break;
				}
			}
			if(flag==false){
				break;
			}
		}
		if(apmac==null||mac==null){
			return;
		}
		//log.error("=====>>>>:ͳ��չʾ����advertid:"+advertid+" modelid:"+modelid+" advposid:"+advposid+" apmac:"+apmac+" mac:"+mac);
		SHOW_COUNT_LOG count_LOG = new SHOW_COUNT_LOG(null, null, busid, apmac.toString(), null, mac.toString(), modelid, advposid, materid, adverturl, advertid, routeid);
		int  result=vehicleWifiService.saveShowCount(count_LOG);
		this.returnResponse(response, result);
	}

	@RequestMapping(value="/Wifi/logined",method= RequestMethod.POST)
	public ModelAndView logined(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		
		Object gw_address=session.getAttribute("gw_address");
		Object gw_port=session.getAttribute("gw_port");
		Object phone=session.getAttribute("phone");
		Object gw_id = session.getAttribute("gw_id");

		if(gw_address==null||gw_port==null||phone==null||gw_id==null){
			return null;
		}
		String pubip = request.getRemoteAddr();
		Map<String,Object> map = new HashMap<String,Object>();
		String token=GetToken();
		String speed=PROCEDURCES.SPEED;
		String timeout=PROCEDURCES.TIMEOUT;
		Object speeds=session.getAttribute("speed");
		Object timeouts=session.getAttribute("timeout");
		if(speeds!=null){
			speed=speeds.toString();
		}
		if(timeouts!=null){
			timeout=timeouts.toString();
		}
		String httpUrl="redirect:http://"+gw_address.toString()+":"+gw_port.toString()+"/wifidog/auth?token=" +token+ "&Phone=" +phone + "&Pubip=" + pubip + "&Speed=" +speed + "&Timeout=" +timeout;
		//log.info("��֤��ת����:"+httpUrl);
		ModelAndView result=new ModelAndView(httpUrl);
		return result;
	}
//	@RequestMapping(value="/Wifi/logined",method= RequestMethod.GET)
//	public void loginedget(HttpServletRequest request,HttpServletResponse response){}
	
	 private String GetToken()
     {
		 String dt=System.currentTimeMillis()+"";
         return dt;
     }

	 private String CheckToken(String token){
		 log.info("token======>"+token);
		 Pattern pattern = Pattern.compile("^[0-9]*$");
		  Matcher matcher = pattern.matcher(token);
		  boolean b= matcher.matches();
		  if(!b){
			  //log.info("�ж�tokenΪ����");
			  return "Auth:0";
		  }else{
			  //log.info("�ж�tokenʱ���");			  
		  }
		 long log = Long.parseLong(token);
		 long cu=System.currentTimeMillis();
		 if(cu-log>3600000l){
			 return "Auth:0";
		 }else{
			 return "Auth:1"; 
		 }
	 }
	 
	 @RequestMapping(value="/Wifi/SendSMSCode",method= RequestMethod.GET)
	 public void SendSMSCode(HttpServletRequest request,HttpServletResponse response){
		 HttpSession session = request.getSession();
		 String phone = request.getParameter("phone");
			session.setAttribute("phone",phone);
		 try{
			 String code=SMSUtils.getSMSCode();
			 String key="smscode"+phone;
			 //log.info("key======>sendSMSCode:"+key+"   code:"+code);
			 session.setAttribute(key, code);
		    	vehicleWifiService.sendSMSCode(phone,code);
				this.setResultInfo(QUERY_SUCCESS_INFO,code);
		    } catch (Exception e) {
				e.printStackTrace();
				this.setResultInfo(QUERY_FAILURE_INFO, "���Ͷ���ʧ��");
			}
		   this.returnResponse(response, this.getResultInfo());
	 }
	 
	 
	 @RequestMapping(value="/Wifi/toVaild")
	 public void toVaild(HttpServletRequest request,HttpServletResponse response){
		 HttpSession session = request.getSession();
		 String code=request.getParameter("code");
		 String phone=request.getParameter("phone");
		 JSONObject jsons=new JSONObject();
		 try{
			 String key="smscode"+phone;
			 Object vailCodetemp=session.getAttribute(key);
			 String vaildCode=null;
			 if(vailCodetemp!=null){
				 vaildCode=session.getAttribute(key).toString();
			 }
			 jsons.accumulate("smscod", vaildCode);
			 if(code!=null&&code.equals(vaildCode)){
				 //log.info("key======>toVaild:"+key+"   vaildcode:"+vaildCode+"  ��֤�ɹ�");
				 jsons.accumulate("result", true);
			 }else{
				 //log.info("key======>toVaild:"+key+"   vaildcode:"+vaildCode+" ��֤ʧ��");
				 jsons.accumulate("result", false);
			 }
			this.setResultInfo(QUERY_SUCCESS_INFO,jsons);
	    } catch (Exception e) {
			e.printStackTrace();
			this.setResultInfo(QUERY_FAILURE_INFO, null);
		}
		this.returnResponse(response, this.getResultInfo());
	 }
	 /**
	  * ȷ���Ƿ�Ҫ���У�auth:1����  auth:0������
	  * @param request
	  * @param response
	  */
	@RequestMapping(value="/Wifi/auth",method= RequestMethod.GET)
	public void Auth(HttpServletRequest request,HttpServletResponse response){
		String token=request.getParameter("token");
		String stage=request.getParameter("stage");
		String ip=request.getParameter("ip");
		String mac=request.getParameter("mac");
		String incoming=request.getParameter("incoming");
		String outgoing=request.getParameter("outgoing");
		String gw_id=request.getParameter("gw_id");
	 String result = CheckToken(token);
	 this.returnResponse(response, result);
	}
	
	@RequestMapping(value="/Wifi/ping/",method= RequestMethod.GET)
    public void Ping(HttpServletRequest request,HttpServletResponse response){
		String gw_id=request.getParameter("gw_id");//=00037F8274C8&"
		String sys_uptime=request.getParameter("sys_uptime");//=76477&
		String sys_memfree=request.getParameter("sys_memfree");//=14448&"
	    String sys_load=request.getParameter("sys_load");//=0.41&
     	String wifidog_uptime=request.getParameter("wifidog_uptime");//=76446 
     	 this.returnResponse(response, "Pong");
     	 return;
    }
	
	@RequestMapping(value="/Wifi/portal",method= RequestMethod.GET)
	public String Portal(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		Object url=session.getAttribute("url");
		Object mac=session.getAttribute("gw_id");
		Object phonemac=session.getAttribute("mac");
		Object phone = session.getAttribute("phone");
		if(url==null||mac==null||phonemac==null||phone==null){
			return null;
		}
		Happen happen = new Happen(null,DateUtil.getCurrentDate(),phone.toString(),null);
		String URL=vehicleWifiService.portal(mac.toString(), phone.toString(), phonemac.toString(), session,url.toString(),happen);
		return "redirect:"+URL;
    }
	
	@RequestMapping(value="/Wifi/portal",method= RequestMethod.POST)
	public void portal2(HttpServletRequest request,HttpServletResponse response){
		return;
	}
	

	
}
