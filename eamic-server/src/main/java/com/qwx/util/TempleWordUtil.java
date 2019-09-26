package com.qwx.util;

import org.apache.poi.xwpf.usermodel.*;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;  

/**
 * 写入word工具类
 * @author
 *
 */
public class TempleWordUtil {

    /** 
     * 替换段落里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数，导入的数据 
     */  
    public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {  
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();  
        XWPFParagraph para;  
        while (iterator.hasNext()) {  
            para = iterator.next();  
            this.replaceInPara(para, params);  
        }  
    }  
  
    /** 
     * 替换段落里面的变量 
     * 
     * @param para   要替换的段落 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {  
        List<XWPFRun> runs;  
        if (this.matcher(para.getParagraphText()).find()) {  
            runs = para.getRuns();  
  
            int start = -1;  
            int end = -1;  
            String str = "";  
            for (int i = 0; i < runs.size(); i++) {  
                XWPFRun run = runs.get(i);  
                String runText = run.toString();  
                if ('$' == runText.charAt(0)&&'{' == runText.charAt(1)) {  
                    start = i;  
                }  
                if ((start != -1)) {  
                    str += runText;  
                }  
                if ('}' == runText.charAt(runText.length() - 1)) {  
                    if (start != -1) {  
                        end = i;  
                        break;  
                    }  
                }  
            }  
  
            for (int i = start; i <= end; i++) {  
                para.removeRun(i); 
                i--;  
                end--;  
            }  
            
            if (!str.equals("") && params != null) {
            	for (String key : params.keySet()) {  
                    if (str.equals(key)) {  
                    	if (end > -1) {
                        	para.getRuns().get(end).setText(params.get(key).toString());
            			} else if (end == -1) {
            				para.createRun().setText(params.get(key)+"");
						}
                    }  
                }  
			}
            //迭代
            if (this.matcher(para.getParagraphText()).find()) {
				this.replaceInPara(para, params);
			}
            
  
  
        }  
    }  
  
    /** 
     * 替换表格里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInTable(XWPFDocument doc, Map<String, Object> params) {  
        Iterator<XWPFTable> iterator = doc.getTablesIterator();  
        XWPFTable table;  
        List<XWPFTableRow> rows;  
        List<XWPFTableCell> cells;  
        List<XWPFParagraph> paras;  
        while (iterator.hasNext()) {  
            table = iterator.next();  
            rows = table.getRows();  
            for (XWPFTableRow row : rows) {  
                cells = row.getTableCells();  
                for (XWPFTableCell cell : cells) {  
                    paras = cell.getParagraphs();  
                    for (XWPFParagraph para : paras) {  
                        this.replaceInPara(para, params);  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 正则匹配字符串 
     * 
     * @param str 
     * @return 
     */  
    private Matcher matcher(String str) {  
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(str);  
        return matcher;  
    }  
  
    /** 
     * 关闭输入流 
     * 
     * @param is 
     */  
    public void close(InputStream is) {  
        if (is != null) {  
            try {  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 关闭输出流 
     * 
     * @param os 
     */  
    public void close(OutputStream os) {  
        if (os != null) {  
            try {  
                os.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /**
     * 直接调用的接口
     * 
     * @param model 参数
     * @param fileName 文件名
     * @param templatePath 模板路径
     * @param response 
     * @throws IOException
     */
    public void exportWord(String model, String fileName, 
    		String templatePath, HttpServletResponse response) throws IOException {
    	fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".docx";

		Map<String, Object> params = new HashMap<String, Object>();
		params = JSON.parseObject(model);
		TempleWordUtil wordUtil = new TempleWordUtil();

		XWPFDocument doc;
		InputStream is = new FileInputStream(templatePath);
		// is = getClass().getClassLoader().getResourceAsStream(templatePath);
		doc = new XWPFDocument(is); // 只能使用.docx的

		wordUtil.replaceInPara(doc, params);
		// 替换表格里面的变量
		wordUtil.replaceInTable(doc, params);
		OutputStream os = response.getOutputStream();

		response.setContentType("application/msword");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);

		doc.write(os);

		close(os);
		close(is);

		os.flush();
		os.close();
    }
  
}  

