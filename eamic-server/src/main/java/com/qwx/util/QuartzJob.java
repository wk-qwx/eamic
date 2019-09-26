package com.qwx.util;



import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;




/**
 * @author ktw
 *定时任务公共类
 */
public class QuartzJob {

	@SuppressWarnings({"deprecation", "resource" })
	public void work()  
     {  
		 System.out.println("Quartz的任务调度！！！"+(new Date()).toString());  
		 
		 HttpClient httpclient = new DefaultHttpClient();  
		  
	        try {  
	            // 创建httpget.  
	            HttpGet httpget = new HttpGet("http://192.168.1.145:8081/ghm/taxSource/findTaxSourcePage");
	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.  
	            HttpResponse response = httpclient.execute(httpget);  
	            // 获取响应实体  
	            HttpEntity entity = response.getEntity();  
	            System.out.println("--------------------------------------");  
	            // 打印响应状态  
	            System.out.println(response.getStatusLine());  
	            if (entity != null) {  
	                // 打印响应内容长度  
	                System.out.println("Response content length: "  
	                        + entity.getContentLength());  
	                // 打印响应内容  
	                System.out.println("Response content: "  
	                        + EntityUtils.toString(entity));  
	            }  
	            System.out.println("------------------------------------");  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源  
	            httpclient.getConnectionManager().shutdown();  
	        }  
	    }  
	  
        


}
