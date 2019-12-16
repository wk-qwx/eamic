package com.qwx.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.jacob.com.ComThread;

public class DocUtil {
	// new DocUtil().excelToHtml("D:\\aaa.xls", "D:\\aaa.html");
	public void excelToHtml(String xlsPath, String htmlPath) {
		ActiveXComponent app = new ActiveXComponent("Excel.Application");
		try {
			app.setProperty("Visible", new Variant(false));
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.call(excels, "Open", xlsPath, false, true).toDispatch();
			Dispatch.call(excel, "SaveAs", htmlPath, 44);
			Dispatch.call(excel, "Close", false);
		} catch (Exception e) {} finally {
			if (app != null) app.invoke("Quit", new Variant[] {});
			ComThread.Release();
		}
	}

	// new DocUtil().wordToPdf("D:\\aaa.doc", "D:\\aaa.pdf");
	public void wordToPdf(String docPath, String pdfPath) {
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		try {
			app.setProperty("Visible", new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.call(docs, "Open", docPath, false, true).toDispatch();
			Dispatch.call(doc, "SaveAs", pdfPath, 17);
			Dispatch.call(doc, "ExportAsFixedFormat", pdfPath, 17);
			Dispatch.call(doc, "Close", false);
		} catch (Exception e) {} finally {
			if (app != null) app.invoke("Quit", 0);
			ComThread.Release();
		}
	}

	// new DocUtil().pptToPdf("D:\\aaa.ppt", "D:\\aaa.pdf");
	public void pptToPdf(String pptPath, String pdfPath) {
		ActiveXComponent app = new ActiveXComponent("Powerpoint.Application");
		try {
			Dispatch ppts = app.getProperty("Presentations").toDispatch();
			Dispatch ppt = Dispatch.call(ppts, "Open", pptPath, true, true, false).toDispatch();
			Dispatch.call(ppt, "SaveAs", pdfPath, 32);
			Dispatch.call(ppt, "Close");
		} catch (Exception e) {} finally {
			if (app != null) app.invoke("Quit");
			ComThread.Release();
		}
	}
}
