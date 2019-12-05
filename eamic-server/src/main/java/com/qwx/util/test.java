package com.qwx.util;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 1:向数据库中添加数据
 * @author biexiansheng
 *
 */
public class test {
	private SessionFactory sessionFactory;  
    
    public Session getSession() {  
        return sessionFactory.getCurrentSession();  
    }  
  
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
    public void qwe(){    	
    	Session session = this.getSession();
        Transaction tran = session.beginTransaction();
        tran.commit();
    }
    /**
	 * 获取两个时间的时间差，精确到毫秒
	 *
	 * @param
	 * @return
	 */
	public static void TimeDifference(long start, long end) {

	    long between = end - start;
	    long day = between / (24 * 60 * 60 * 1000);
	    long hour = (between / (60 * 60 * 1000) - day * 24);
	    long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
	    long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	    //long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
	      //      - min * 60 * 1000 - s * 1000);
	    String timeDifference = day + "天" + hour + "小时" + min + "分" + s + "秒";
	    System.out.println(timeDifference);
	}
    public static void main(String[] args) {
    	try {
			long now = System.currentTimeMillis();
			SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
			long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
			
			//当前时间  距离当天晚上23:59:59  秒数 也就是今天还剩多少秒
	        long TimeNext = 24*60*60 - overTime;
	        System.out.println(TimeNext);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*try {
        	        	
        	test tt = new test();
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            System.out.println("加载数据库驱动成功");
            String url="jdbc:mysql://localhost:3306/eamicdb?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";//声明数据库test的url
            String user="root";//数据库的用户名
            String password="qwx123";//数据库的密码
            //建立数据库连接，获得连接对象conn(抛出异常即可)
            Connection conn=DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功");
            //生成一条mysql语句
            String sql="update ea_defect t set t.devicename = '1111111111' where t.id='297e20736e1146cb016e115b9d930000'";        
            Statement stmt=conn.createStatement();//创建一个Statement对象
            stmt.executeUpdate(sql);//执行sql语句
            
            tt.qwe();
            System.out.println("更新数据成功");
            conn.close();
            System.out.println("关闭数据库成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
    }
    
}