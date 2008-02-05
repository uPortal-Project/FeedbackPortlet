package org.jasig.portlets.FeedbackPortlet.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jasig.portlets.FeedbackPortlet.FeedbackItem;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelFeedbackView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		
        model = (HashMap) model.get("model");
        int rowCount = 0;

        // set up the sheet and default column widths
        HSSFSheet sheet = wb.createSheet("uPortal Feedback");
        sheet.setGridsPrinted(true);

        // create header row
        HSSFRow header = sheet.createRow(rowCount);
        header.createCell((short) 0).setCellValue(new HSSFRichTextString("User Id"));
        header.createCell((short) 1).setCellValue(new HSSFRichTextString("User Name"));
        header.createCell((short) 2).setCellValue(new HSSFRichTextString("User Email"));
        header.createCell((short) 3).setCellValue(new HSSFRichTextString("User Role"));
        header.createCell((short) 4).setCellValue(new HSSFRichTextString("Useragent"));
        header.createCell((short) 5).setCellValue(new HSSFRichTextString("Submitted"));
        header.createCell((short) 6).setCellValue(new HSSFRichTextString("Tab Name"));
        header.createCell((short) 7).setCellValue(new HSSFRichTextString("Liked?"));
        header.createCell((short) 8).setCellValue(new HSSFRichTextString("Feedback"));
        
        // add each feedback item to the sheet
        List<FeedbackItem> feedback = (List<FeedbackItem>) model.get("feedback");
        for (Iterator<FeedbackItem> iter = feedback.iterator(); iter.hasNext();) {
        	rowCount++;
        	FeedbackItem item = iter.next();
            HSSFRow row = sheet.createRow(rowCount);
            row.createCell((short) 0).setCellValue(new HSSFRichTextString(item.getUserid()));
            row.createCell((short) 1).setCellValue(new HSSFRichTextString(item.getUsername()));
            row.createCell((short) 2).setCellValue(new HSSFRichTextString(item.getUseremail()));
            row.createCell((short) 3).setCellValue(new HSSFRichTextString(item.getUserrole()));
            row.createCell((short) 4).setCellValue(new HSSFRichTextString(item.getUseragent()));
            row.createCell((short) 5).setCellValue(item.getSubmissiontime());
            row.createCell((short) 6).setCellValue(new HSSFRichTextString(item.getTabname()));
            row.createCell((short) 7).setCellValue(new HSSFRichTextString(item.getFeedbacktype()));
            row.createCell((short) 8).setCellValue(new HSSFRichTextString(item.getFeedback()));
        }
        
	}

}
