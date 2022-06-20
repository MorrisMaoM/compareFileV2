package com.mao.comparefilev2.Service;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReadExcelFilePath {

    public List readExcelForPath(){

        String config = "config.properties";
        Properties props = new Properties();

        List filePathList = new ArrayList();
        try {
            props.load(new InputStreamReader(new FileInputStream(config), "UTF-8"));

            String mappingExcel = props.getProperty("excelFile");
            String leftPath = props.getProperty("leftPath");
            String rightPath = props.getProperty("rightPath");

            FileInputStream inp = new FileInputStream(mappingExcel);
            XSSFWorkbook wb = new XSSFWorkbook(inp);
            inp.close();

            XSSFSheet sheet = wb.getSheetAt(0);
            int rowLength = sheet.getLastRowNum();

            String  header1 = null;
            String  header2 = null;

            XSSFRow rowHeader = sheet.getRow(0);
            XSSFCell cellHeader1=rowHeader.getCell(0);
            XSSFCell cellHeader2=rowHeader.getCell(1);

            for(int i =1; i<=rowLength;i++){



                XSSFRow row = sheet.getRow(i);

                XSSFCell cell1 = row.getCell(0);
                XSSFCell cell2 = row.getCell(1);
//                XSSFCell cellRecfm = row.getCell(2);
                String fileAndPath1 = cell1.getStringCellValue();
                String fileAndPath2 = cell2.getStringCellValue();

                    header1 = cellHeader1.getStringCellValue();
                    header2 = cellHeader2.getStringCellValue();


                    if(header1.replace(" ","").toLowerCase().contains("leftfile")){
                        if(fileAndPath1.contains("/")||fileAndPath1.contains("\\")) {
                            filePathList.add(fileAndPath1 + ";" + fileAndPath2);
                        }else{
                            filePathList.add(leftPath + fileAndPath1 + ";" + rightPath + fileAndPath2);
                        }
                    }else if(header2.replace(" ","").toLowerCase().contains("leftfile")){
                        if(fileAndPath1.contains("/")||fileAndPath1.contains("\\")) {
                            filePathList.add(fileAndPath2 + ";" + fileAndPath1);
                        }else{
                            filePathList.add(leftPath + fileAndPath2 + ";" + rightPath + fileAndPath1);
                        }
                    }


                }







                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("excel not found");
                }

                return filePathList;
            }






}
