package com.qwx.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log {
	
	public static final String SERVICE_FILE= "service";
    
    public static final String SERVICE_BASE_URI="catalina.home";
    public static final String SERVICE_DIR="/logs/";
    
	/**代码调式日志*/
	private static final Logger DEBUG = Logger.getLogger("debug");
	
	/**函数调用日志*/
	private static final Logger RUN= Logger.getLogger("run");
	
	private static final Logger MAINTENCE= Logger.getLogger("maintence");	

	
	private static final Logger SERVICE= Logger.getLogger(SERVICE_FILE);

	static{
		DEBUG.setLevel(Level.DEBUG);
		RUN.setLevel(Level.INFO);
		SERVICE.setLevel(Level.INFO);
		MAINTENCE.setLevel(Level.ERROR);
	}
	
	public static void pringRun(Object ob){
		if(RUN.isInfoEnabled()){
			RUN.info(ob);
		}		
	}
	
	public static void pringRunWarn(Object ob){
		if(RUN.isInfoEnabled()){
			RUN.warn(ob);
		}		
	}
	
	public static void pringRunError(Object ob){
		if(RUN.isInfoEnabled()){
			RUN.error(ob);
		}		
	}
	
	public static void printDebug(Object ob){
		if(DEBUG.isDebugEnabled()){
			DEBUG.info(ob);
		}
	}
	public static void printDebugDe(Object ob){
		if(DEBUG.isDebugEnabled()){
			DEBUG.debug(ob);
		}
	}
	
	
	public static void printMaintence(Object ob){
		if(MAINTENCE.isInfoEnabled()){
			MAINTENCE.info(ob);
		}
	}
	
	/**
	 * 
	 * @param serviceId
	 * @param status处理状态 0：成功  1:失败
	 * @param serviceOwner 0个人，1：组织，2：平台
	 * @param regStatus 0:发布 1：注册
	 */
	public static void printService(String serviceId,int status){
		if(SERVICE.isInfoEnabled()){
			SERVICE.info(serviceId+"\\t"+status);
		}
	}
	
//	public static void printService(String serviceId,int status,int serviceOwner,int regStatus){
//		if(SERVICE.isInfoEnabled()){
//			SERVICE.info(serviceId+"\\t"+status+"\t"+serviceOwner+"\t"+regStatus);
//		}
//	}

//	public static void printResouce(Object ob){
//		if(RESOURCE.isInfoEnabled()){
//			RESOURCE.info(ob);
//		}
//	}
//	public static void printResouceError(Object ob){
//			RESOURCE.error(ob);
//	}
	
	
}
