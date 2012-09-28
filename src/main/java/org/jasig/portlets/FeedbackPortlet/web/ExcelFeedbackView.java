/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlets.FeedbackPortlet.web;

import java.text.SimpleDateFormat;
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
import org.jasig.portlets.FeedbackPortlet.FeedbackQueryParameters;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * ExcelFeedbackView produces a Microsoft Excel spreadsheet view 
 * of feedback data.  All feedback attributes are simply transformed into
 * spreadsheet columns.
 * 
 * @author Jen Bourey
 */
public class ExcelFeedbackView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
        model = (HashMap) model.get("model");
        int rowCount = 0;
        response.setHeader("Content-Disposition", "attachment; filename=\"feedback.xls\""); // this encourages it to default as a 'feedback.xls' file name

        // set up the sheet
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
        SimpleDateFormat dateFormatter = new SimpleDateFormat(FeedbackQueryParameters.DATE_FORMAT);
        for (Iterator<FeedbackItem> iter = feedback.iterator(); iter.hasNext();) {
        	rowCount++;
        	FeedbackItem item = iter.next();
            HSSFRow row = sheet.createRow(rowCount);
            row.createCell((short) 0).setCellValue(new HSSFRichTextString(item.getUserid()));
            row.createCell((short) 1).setCellValue(new HSSFRichTextString(item.getUsername()));
            row.createCell((short) 2).setCellValue(new HSSFRichTextString(item.getUseremail()));
            row.createCell((short) 3).setCellValue(new HSSFRichTextString(item.getUserrole()));
            row.createCell((short) 4).setCellValue(new HSSFRichTextString(item.getUseragent()));
            row.createCell((short) 5).setCellValue(new HSSFRichTextString(dateFormatter.format(item.getSubmissiontime()) ) ); // formats to be readable
            row.createCell((short) 6).setCellValue(new HSSFRichTextString(item.getTabname()));
            row.createCell((short) 7).setCellValue(new HSSFRichTextString(item.getFeedbacktype()));
            row.createCell((short) 8).setCellValue(new HSSFRichTextString(item.getFeedback()));
        }
        
	}

}


/*
 * ExcelFeedbackView.java
 * 
 * Copyright (c) Feb 5, 2008 Yale University. All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * YALE UNIVERSITY OR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 * and this list of conditions in any related documentation and, if feasible, in
 * the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 * software developed by Yale University," in any related documentation and, if
 * feasible, in the redistributed software.
 * 
 * 3. The names "Yale" and "Yale University" must not be used to endorse or
 * promote products derived from this software.
 */