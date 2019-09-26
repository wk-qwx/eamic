package com.qwx.util;

import java.io.File;
import java.util.List;

public class FileUtil {
	public static String toString(List list) {
		if (list.size() == 0)
			return "";
		String str = "";
		for (Object s : list) {
			if (s == null || s.toString().isEmpty())
				continue;
			str += "'" + s.toString().replace("'", "''") + "',";
		}
		return str.substring(0, str.length() - 1);
	}
	
	//删除文件夹并删除所有文件
	public static boolean deleteAllFile(String path) {
	       boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) {
	            return flag;    
	        }
	        if (!file.isDirectory()) {
	            return flag;
	        }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	            if (path.endsWith(File.separator)) {
	                temp = new File(path + tempList[i]);
	            } else {
	                temp = new File(path + File.separator + tempList[i]);
	            }
	            if (temp.isFile()) {
	                temp.delete();
	            }
	            if (temp.isDirectory()) {
	            	deleteAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	               // delFolder(path + "/" + tempList[i]);//再删除空文件夹
	                flag = true;
	            }
	        }
		if (!file.delete()) {
			flag = false;
		}
	        return flag;
	}
	
	//删除单个文件
	public static boolean deleteFie(String path){
		boolean flag = false;
		File file = new File(path);
        if (!file.exists()) {
            return flag;    
        }
    	if (!file.delete()) {
			flag = false;
		}
	   return flag;
	}

}
