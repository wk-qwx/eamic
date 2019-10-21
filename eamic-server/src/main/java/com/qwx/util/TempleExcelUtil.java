package com.qwx.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


/**
 * excel工具类
 * @author admin
 *
 */
public class TempleExcelUtil {

	/**
	 * 将数据写入excel模板,返回二进制流下载
	 * 
	 * @param list
	 *            数据
	 * @param response
	 * @param request
	 * @param file
	 *            模板文件
	 * @param columnNum
	 *            列数
	 * @param columnName
	 *            列名
	 * @param tempType
	 *            模板类型 0 - 自定义,1-默认模板,2-上传自定义模板
	 */
	public void createExcel(List<Object[]> list, HttpServletResponse response, HttpServletRequest request,
			MultipartFile file, Integer columnNum, List<String> columnName, Integer tempType,String tablename) {
		try {
			ServletOutputStream os = response.getOutputStream(); // 获得输出流
			response.reset(); // 清空输出流
			if(tablename == null || tablename.isEmpty()) tablename = "税源信息表";
			String fileName = new String(tablename.getBytes("gb2312"), "ISO8859-1") + ".xls";
			Workbook workbook = null;
			boolean isExcel2003 = true; // 判断是2007还是2003的excel模板
			Cell cell = null;
			
			if (tempType == 0) {
				workbook = new HSSFWorkbook(); // 创建一个excel2003文件
				CellStyle style = this.getStyle(workbook);
				// sheet 对应一个工作页
				Sheet sheet = workbook.createSheet();
				Row firstrow = sheet.createRow(0); // 下标为0的行开始
				//firstrow.createCell((short) 0).setCellValue(new HSSFRichTextString("序号"));
				myCreateCell(0, "序号", firstrow, cell, style, isExcel2003);
				for (int j = 0; j < columnNum; j++) {
					myCreateCell( j + 1, columnName.get(j), firstrow, cell, style, isExcel2003);
				}
			} else if (tempType == 2) {
				if (isExcel2007(file.getOriginalFilename())) {
					isExcel2003 = false;
					fileName = new String(tablename.getBytes("gb2312"), "ISO8859-1") + ".xlsx";// 如果是2007后的定义后缀为xlsx;
				}
				BufferedInputStream in = new BufferedInputStream(file.getInputStream());// 传入的模板
				if (isExcel2003) {
					workbook = new HSSFWorkbook(in);
				} else {
					workbook = new XSSFWorkbook(in);
				}
			} else if (tempType == 1) {
				String path = request.getServletContext().getRealPath("") + "/template/projectTable.xls"; // 这个是我的excel模板
				if (isExcel2007(path)) {
					isExcel2003 = false;
					fileName = new String(tablename.getBytes("gb2312"), "ISO8859-1") + ".xlsx";// 如果是2007后的定义后缀为xlsx;
				}
				FileInputStream in = new FileInputStream(new File(path));// 传入的模板
				if (isExcel2003) {
					workbook = new HSSFWorkbook(in);
				} else {
					workbook = new XSSFWorkbook(in);
				}
			}
			
			response.setHeader("Content-disposition", "attachment; filename=" + fileName); // 设定输出文件头
			response.setContentType("application/msexcel"); // 定义输出类型

			try {
				Sheet sheet = workbook.getSheetAt(0);//读取第一个工作页sheet
				Row row;
				CellStyle style = this.getStyle(workbook);
				int rownum = sheet.getPhysicalNumberOfRows();//设置起始行
				String value = null;
				if (tempType == 2) {
					for (int i = 0, len = list.size(); i < len; i++) {
						row = sheet.createRow(rownum);
						for (int j = 0; j < columnNum; j++) {
							if (columnNum == 1) {
								value = list.get(i)+""; //不要使用tostring，会出现转换错误
							} else {
							    value = list.get(i)[j] == null ? null : list.get(i)[j].toString();
							}
							myCreateCell(j, value, row, cell, style, isExcel2003);
						}
						rownum++;
					}
				} else {
					for (int i = 0, len = list.size(); i < len; i++) {
						row = sheet.createRow(rownum);
						myCreateCell(0, String.valueOf(i + 1), row, cell, style, isExcel2003);// 设置序号
						for (int j = 0; j < columnNum; j++) {
							if (columnNum == 1) {
								value = list.get(i)+"";
							} else {
							    value = list.get(i)[j] == null ? null : list.get(i)[j].toString();
							}
							myCreateCell(j + 1, value, row, cell, style, isExcel2003);
						}
						rownum++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			workbook.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写入值，样式
	 * @param cellnum 列数
	 * @param value 值
	 * @param row
	 * @param cell
	 * @param style 样式
	 * @param isExcel2003 是否是2003 excel, false为2007excel
	 */
	public void myCreateCell(int cellnum, String value, Row row, Cell cell, CellStyle style, Boolean isExcel2003) {

		cell = row.createCell((short) cellnum);
		if (isExcel2003) {
			cell.setCellValue(new org.apache.poi.hssf.usermodel.HSSFRichTextString(value));
		} else {
			cell.setCellValue(new org.apache.poi.xssf.usermodel.XSSFRichTextString(value));
		}
		cell.setCellStyle(style);
	}

	/**
	 * 设置样式
	 * @param workbook
	 * @return
	 */
	public CellStyle getStyle(Workbook workbook) {
		// 设置字体;
		Font font = workbook.createFont();
		// 设置字体大小;
		font.setFontHeightInPoints((short) 9);
		// 设置字体名字;
		font.setFontName("Courier New");
		// font.setItalic(true);
		// font.setStrikeout(true);
		// 设置样式;
		CellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(CellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(CellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(CellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}
	 
	/**
	 * 是否是2003的excel，返回true是.xls后缀
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {

		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	/**
	 * 是否是2007的excel，返回true是.xlsx后缀
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {

		return filePath.matches("^.+\\.(?i)(xlsx)$");

	}

	/**
	 * 文件是否是excel
	 * @param filePath
	 * @return
	 */
	public static boolean validateExcel(String filePath) {

		/** 检查文件名是否为空或者是否是Excel格式的文件 */

		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {

			return false;

		}

		/** 检查文件是否存在 */

		/*
		 * File file = new File(filePath);
		 * 
		 * if (file == null || !file.exists()) {
		 * 
		 * // errorInfo = "文件不存在";
		 * 
		 * return false;
		 * 
		 * }
		 */

		return true;
	}
	
	// 解决excel类型问题，获得数值
	public String getValue(Cell cell) {
		String value = "";
		if (null == cell) {
			return value;
		}
		switch (cell.getCellType()) {
		// 数值型
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				value = format.format(date);
				;
			} else {// 纯数字
				BigDecimal big = new BigDecimal(cell.getNumericCellValue());
				value = big.toString();
				// 解决1234.0 去掉后面的.0
				if (null != value && !"".equals(value.trim())) {
					String[] item = value.split("[.]");
					if (1 < item.length && "0".equals(item[1])) {
						value = item[0];
					}
				}
			}
			break;
		// 字符串类型
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue().toString();
			break;
		// 公式类型
		case Cell.CELL_TYPE_FORMULA:
			// 读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getStringCellValue().toString();
			}
			break;
		// 布尔类型
		case Cell.CELL_TYPE_BOOLEAN:
			value = " " + cell.getBooleanCellValue();
			break;
		// 空值
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		// 故障
		case Cell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = cell.getStringCellValue().toString();
		}
		if ("null".endsWith(value.trim())) {
			value = "";
		}
		return value;
	}
}
