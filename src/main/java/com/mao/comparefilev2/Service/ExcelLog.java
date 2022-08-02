package com.mao.comparefilev2.Service;

import com.mao.comparefilev2.Model.ExcelLogModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND;

public class ExcelLog {
    public void newExcelLog(List<ExcelLogModel> excelLogModelList) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("比對結果");
            //字體設定粗體
            Font font = workbook.createFont();
            font.setBold(true);

            Font font2 = workbook.createFont();
            font2.setBold(true);
            font2.setColor(IndexedColors.RED.getIndex());
            CellStyle cellStyle3 = workbook.createCellStyle();
            cellStyle3.setFont(font2);

            CellStyle cellStyle1 = workbook.createCellStyle();
            //欄位設框線和置中
            cellStyle1.setBorderBottom(BorderStyle.THIN);
            cellStyle1.setBorderLeft(BorderStyle.THIN);
            cellStyle1.setBorderRight(BorderStyle.THIN);
            cellStyle1.setBorderTop(BorderStyle.THIN);
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);


            //設定底色
            cellStyle1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            cellStyle1.setFillPattern(SOLID_FOREGROUND);

            cellStyle1.setFont(font);


            CellStyle cellStyle2 = workbook.createCellStyle();
            //欄位設框線和置中
            cellStyle2.setBorderBottom(BorderStyle.THIN);
            cellStyle2.setBorderLeft(BorderStyle.THIN);
            cellStyle2.setBorderRight(BorderStyle.THIN);
            cellStyle2.setBorderTop(BorderStyle.THIN);
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);


            //設定底色
            cellStyle2.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle2.setFillPattern(SOLID_FOREGROUND);

            cellStyle2.setFont(font);

            for (int i = 0; i < 10; i++) {
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
            }

            XSSFRow row = sheet.createRow(0);

            Cell headerCell0 = row.createCell(0);
            Cell headerCell1 = row.createCell(1);
            Cell headerCell2 = row.createCell(2);
            Cell headerCell3 = row.createCell(3);
            Cell headerCell =  row.createCell(4);
            Cell headerCell4 = row.createCell(5);
            Cell headerCell5 = row.createCell(6);


            headerCell0.setCellValue("Left File");
            headerCell0.setCellStyle(cellStyle1);

            headerCell2.setCellValue("Left File Path");
            headerCell2.setCellStyle(cellStyle1);

            headerCell1.setCellValue("Right File");
            headerCell1.setCellStyle(cellStyle1);

            headerCell3.setCellValue("Right File Path");
            headerCell3.setCellStyle(cellStyle1);

            headerCell4.setCellValue("Status");
            headerCell4.setCellStyle(cellStyle1);

            headerCell5.setCellValue("Description");
            headerCell5.setCellStyle(cellStyle1);

            headerCell.setCellValue("JCL");
            headerCell.setCellStyle(cellStyle1);

            for(int i=0;i<excelLogModelList.size();i++){
                XSSFRow irow = sheet.createRow(i+1);
                irow.createCell(0).setCellValue(excelLogModelList.get(i).getLeftFileName());
                irow.createCell(2).setCellValue(excelLogModelList.get(i).getLeftFilePath());
                irow.createCell(1).setCellValue(excelLogModelList.get(i).getRightFileName());
                irow.createCell(3).setCellValue(excelLogModelList.get(i).getRightFilePath());
                irow.createCell(4).setCellValue(excelLogModelList.get(i).getJcl());
                XSSFCell cell4 =irow.createCell(5);
                if(excelLogModelList.get(i).getCompareStatus().equals("Fail")){
                    cell4.setCellStyle(cellStyle3);
                    cell4.setCellValue(excelLogModelList.get(i).getCompareStatus());
                }else if(excelLogModelList.get(i).getCompareStatus()==null){
                    cell4.setCellValue("");
                }else{
                    cell4.setCellValue(excelLogModelList.get(i).getCompareStatus());
                }
                irow.createCell(6).setCellValue(excelLogModelList.get(i).getStatusDescription());
            }
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            FileOutputStream op = new FileOutputStream("File_CompareLog_"+dateFormat.format(date)+".xlsx");

            workbook.write(op);
            System.out.println(dateFormat.format(date)+" Excel Log has generated");
            op.close();
            workbook.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

