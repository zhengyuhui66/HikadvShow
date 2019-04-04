package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.springframework.web.multipart.MultipartFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class test {

  public static void main(String[] args) throws Exception{
	  String u=null;
	  String u1="null";
	  if(StringUtils.isEmpty(u)){
		  
	  }
	  System.out.println(u+"===="+u1);
   }
//		  try{
//			  throw new SocketTimeoutException();
//		  }catch(Exception e){
//			  e.printStackTrace();
//		  }
// 		  TODO Auto-generated method stub
//		  java.util.Collection c = null;
//		  java.util.ArrayList al = new java.util.ArrayList();
//		  for (int i = 0; i < 10; i++) {
//		   al.add(i+"");
//		  }
//		  c = al;
//		  java.util.Iterator it = c.iterator();
//		  for (int i = 0; i < 10; i++) {
//		   System.out.println((String) it.next());
//		   if(i==5||i==7){
//		    it.remove();
//		   }
//		  }
//	//	  it.remove();
//		  System.out.println("**********************************");
//		  it = c.iterator(); // 关键的！！！
//		  while (it.hasNext()) {
//		   System.out.println((String) it.next());
//		  }

		private long runTest(int iterations){
			long startTime = System.nanoTime();
			long elapsedTime = System.nanoTime();
			return (elapsedTime-startTime)/iterations;
		}
		public static void throwTest() throws Exception {
//			try {
				System.out.println("1..");
				int a=1/0;
//			} catch (Exception e) {
				// TODO: handle exception
//				return ;
			//	System.out.println("1异常了");
		//	throw new RDRuntimeException("异常了");
//			} finally{
//				System.out.println("2..");
//			}
		}

   }
interface Page{
	public List getElementList();
}
class PageObject implements Page{
	public List elementList;

	public List getElementList() {
		return elementList;
	}

	public void setElementList(List elementList) {
		this.elementList = elementList;
	}

	public PageObject(List elementList) {
		super();
		this.elementList = elementList;
	}
	
}