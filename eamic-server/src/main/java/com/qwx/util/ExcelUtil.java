package com.qwx.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

@SuppressWarnings({ "deprecation", "rawtypes" })
public class ExcelUtil<T> {
	public void export(Collection<T> dataset, OutputStream out) {
		export("sheet", null, dataset, "yyyy-MM-dd", out);
	}

	public void export(Map<String, String> headers, Collection<T> dataset, OutputStream out) {
		export("sheet", headers, dataset, "yyyy-MM-dd", out);
	}

	public void export(Map<String, String> headers, Collection<T> dataset, String pattern, OutputStream out) {
		export("sheet", headers, dataset, pattern, out);
	}

	/**
	 * 将数据导出到Excel文件
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要导出的数据集合
	 * @param out
	 *            输出的流对象，EXCEL文档可写路径
	 * @param pattern
	 *            设定时间输出格式，默认为yyy-MM-dd
	 */
	public void export(String title, Map<String, String> headers, Collection<T> dataSet, String pattern,
			OutputStream out) {
		HSSFWorkbook workbook = new HSSFWorkbook();// 声明一个工作薄
		HSSFSheet sheet = workbook.createSheet(title);// 生成一个表格
		sheet.setDefaultColumnWidth((short)15);
		HSSFRow row = sheet.createRow(0);
		int index = 0;
		for(Entry<String, String> ent : headers.entrySet()) {
			HSSFCell cell = row.createCell(index);
			cell.setCellStyle(createCellStyle(workbook, true));
			cell.setCellValue(new HSSFRichTextString(ent.getValue()));
			index++;
		}
		index = 0;
		HSSFCellStyle style = createCellStyle(workbook, false);
		Map<String, Object> data = new HashMap<String, Object>();
		for(T t : dataSet) {
			index++;
			data.clear();
			row = sheet.createRow(index);
			convert(t, t.getClass(), data);
			int i = 0;
			for(Entry<String, String> ent : headers.entrySet()) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				Object value = data.get(ent.getKey());
				String textValue = valueFormat(value, pattern);
				if (textValue == null) continue;
				HSSFRichTextString richString = new HSSFRichTextString(textValue);
				HSSFFont font = workbook.createFont();
				font.setColor(HSSFColor.BLUE.index);
				richString.applyFont(font);
				cell.setCellValue(richString);
				i++;
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {}
	}

	private void convert(T t, Class c, Map<String, Object> map) {
		Class superClass = c.getSuperclass();
		if (!superClass.getName().equals("java.lang.Object")) convert(t, superClass, map);
		Method[] methods = c.getDeclaredMethods();
		for(int i = 0; i < methods.length; i++) {
			String key = methods[i].getName();
			if (!key.startsWith("get")) continue;
			try {
				map.put(key, methods[i].invoke(t, new Object[] {}));
			} catch (Exception e) {
				map.put(key, "");
			}
		}
	}

	private String valueFormat(Object value, String pattern) {
		if (value == null) return null;
		if (value instanceof String) return value.toString();
		if (value instanceof Boolean) return ((Boolean)value) ? "是" : "不是";
		if (value instanceof Date) return new SimpleDateFormat(pattern).format((Date)value);
		if (value instanceof byte[]) return null;
		return value.toString();
	}

	private HSSFCellStyle createCellStyle(HSSFWorkbook workbook, boolean isHeader) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(isHeader ? HSSFColor.SKY_BLUE.index : HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		if (!isHeader) style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = workbook.createFont();
		if (isHeader) {
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short)12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		} else font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		return style;
	}

}
