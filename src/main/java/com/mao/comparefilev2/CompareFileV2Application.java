package com.mao.comparefilev2;

import com.mao.comparefilev2.Model.ExcelLogModel;
import com.mao.comparefilev2.Service.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.FileInputStream;
import java.util.*;

@SpringBootApplication
public class CompareFileV2Application {

    private static final Logger logger = LogManager.getLogger(CompareFileV2Application.class);

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CompareFileV2Application.class, args);

        System.setProperty("java.awt.headless", "false");

        List<ExcelLogModel> excelLogModelList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String funtionString = null;
        String adName=null;
        while (true) {

            System.out.println("請輸入數字選擇功能");
            System.out.println(" 1)  Compare by AD");
            System.out.println(" 2)  Compare by FileName");
            System.out.println(" 3)  Compare by Excel");
            funtionString = scanner.next();

            if (funtionString.equals("1")) {
                //用DB產JCL
//        jclFile.generateJcl();
                //用excel產JCL
                System.out.println("請輸入要比對的AD");
                adName=scanner.next();
                System.out.println("run Compare by AD");

                List<String> filePathList = new ReadExcelFilePath().readAllExcelForPath(adName);

                excelLogModelList = new RunCompare().doCompareAll(filePathList,adName);

                new PrintInfo().printCmdInfo(excelLogModelList,adName);

                new ExcelLog().newExcelLog(excelLogModelList);

                break;
            }

            if (funtionString.equals("2")) {
                List fileNameList = new ArrayList();

                int counter =1;
                String yesOrNo="123";
                while(true) {

                    System.out.println("請輸入要比對的第"+counter+"組檔案名稱 (已輸入完畢開始比對直接按ENTER)");


                    System.out.println("Left File Name :");
                    String leftFileName = scanner.next();
                    System.out.println("Right File Name :");
                    String rightFileName = scanner.next();
                    counter ++;
                    fileNameList.add(leftFileName+","+rightFileName);
                    System.out.println("是否還有下一組(y/n)   ENTER預設y");
                    String absorb=scanner.nextLine();
                    String line = scanner.nextLine();
                    if ("n".equalsIgnoreCase(line)) {
                        break;
                    }
//                    scanner.nextLine();
//                    scanner.nextLine();
                    System.out.println(line);
                }
//                System.out.println("123");
//                List<String> filePathList = new ReadExcelFilePath().readExcelForPath();
//
//                excelLogModelList = new RunCompare().doCompare(filePathList);
//
//
//
//                new ExcelLog().newExcelLog(excelLogModelList);

//                break;
            }



            if (funtionString.equals("3")) {

                System.out.println("run Compare by file");

                List<String> filePathList = new ReadExcelFilePath().readExcelForPath();

                excelLogModelList = new RunCompare().doCompare(filePathList);



                new ExcelLog().newExcelLog(excelLogModelList);

                break;
            }
        }




        Long file1Size;
        Long file2Size;


//        List<String> filePathList = new ReadExcelFilePath().readExcelForPath();
//
//        excelLogModelList = new RunCompare().doCompare(filePathList);

//        for (int i = 0; i < filePathList.size(); i++) {
//            System.out.println("第"+(i+1)+"筆");
//            ExcelLogModel excelLogModel = new ExcelLogModel();
//            try {
//                long startTime=System.currentTimeMillis();
////
//
//                String[] pathArray = filePathList.get(i).split(";");
//
//                String path1 = pathArray[0].replace(":\\", ":/");
//                String path2 = pathArray[1].replace(":\\", ":/");
//
//
//                Path logPath1 = Paths.get(path1);
//                Path logPath2 = Paths.get(path2);
//
//                excelLogModel.setLeftFilePath(path1);
//                excelLogModel.setRightFilePath(path2);
//
//                String fileName1 = String.valueOf(logPath1.getFileName());
//                String fileName2 = String.valueOf(logPath2.getFileName());
//
//                excelLogModel.setLeftFileName(fileName1);
//                excelLogModel.setRightFileName(fileName2);
//
//                System.out.println(fileName1+" VS. "+fileName2);
//                if (path1.contains(":/")) {
//                    file1Size = fileService.getFileSize(path1);
//                    file2Size = fileService.getFileSize(path2);
//
//
//                    if (file1Size.equals(file2Size)) {
//                        fileService.filesCompareByByte(path1, path2,excelLogModel);
//                    } else {
//                        System.out.println("----------------size different----------------");
//                        excelLogModel.setCompareStatus("Fail");
//                        excelLogModel.setStatusDescription("size different");
//                    }
//                }else{
//                    fileService.filesCompareByByte(path1, path2,excelLogModel);
//                }
//
//                System.out.println("第"+(i+1)+"筆結束");
//                long endTime=System.currentTimeMillis();
//
////                System.out.println("程式執行時間： " + (endTime - startTime) / 60000 + "分" + (endTime - startTime) % 60000 / 1000 + "秒");
//
//            } catch (IOException e) {
////            logger.error("檔案路徑有誤");
//                System.out.println("wrong path");
//                excelLogModel.setStatusDescription("Can't not find the file");
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                if (config != null) {
//                    try {
//                        config.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
////            System.out.println(excelLogModel);
//            excelLogModelList.add(excelLogModel);
//        }

//        new ExcelLog().newExcelLog(excelLogModelList);
        System.exit(0);
    }
}