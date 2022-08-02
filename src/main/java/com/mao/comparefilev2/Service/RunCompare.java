package com.mao.comparefilev2.Service;

import com.mao.comparefilev2.Model.ExcelLogModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RunCompare {
    public List<ExcelLogModel> doCompare (List<String> filePathList){


        Long file1Size;
        Long file2Size;

        FileInputStream config = null;

        FileService fileService = new FileService();

        List<ExcelLogModel> excelLogModelList = new ArrayList<>();

        for (int i = 0; i < filePathList.size(); i++) {
            System.out.println("第"+(i+1)+"筆");
            ExcelLogModel excelLogModel = new ExcelLogModel();
            try {
                long startTime=System.currentTimeMillis();
//

                String[] pathArray = filePathList.get(i).split(";");

                String path1 = pathArray[0].replace(":\\", ":/");
                String path2 = pathArray[1].replace(":\\", ":/");


                Path logPath1 = Paths.get(path1);
                Path logPath2 = Paths.get(path2);

                excelLogModel.setLeftFilePath(path1);
                excelLogModel.setRightFilePath(path2);

                String fileName1 = String.valueOf(logPath1.getFileName());
                String fileName2 = String.valueOf(logPath2.getFileName());

                excelLogModel.setLeftFileName(fileName1);
                excelLogModel.setRightFileName(fileName2);

                System.out.println(fileName1+" VS. "+fileName2);
                if (path1.contains(":/")) {
                    file1Size = fileService.getFileSize(path1);
                    file2Size = fileService.getFileSize(path2);


                    if (file1Size.equals(file2Size)) {
                        fileService.filesCompareByByte(path1, path2,excelLogModel);
                    } else {
                        System.out.println("----------------size different----------------");
                        excelLogModel.setCompareStatus("Fail");
                        excelLogModel.setStatusDescription("size different");
                    }
                }else{
                    fileService.filesCompareByByte(path1, path2,excelLogModel);
                }

                System.out.println("第"+(i+1)+"筆結束");
                System.out.println("------------------------------------------");
                System.out.println("   ");
                long endTime=System.currentTimeMillis();

//                System.out.println("程式執行時間： " + (endTime - startTime) / 60000 + "分" + (endTime - startTime) % 60000 / 1000 + "秒");

            } catch (IOException e) {
//            logger.error("檔案路徑有誤");
                System.out.println("wrong path");
                excelLogModel.setStatusDescription("Can't not find the file");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (config != null) {
                    try {
                        config.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            System.out.println(excelLogModel);
            excelLogModelList.add(excelLogModel);
        }


        return excelLogModelList;
    }




    public List<ExcelLogModel> doCompareAll (List<String> filePathList, String ad){


        Long file1Size;
        Long file2Size;

        FileInputStream config = null;

        FileService fileService = new FileService();

        List<ExcelLogModel> excelLogModelList = new ArrayList<>();

        for (int i = 0; i < filePathList.size(); i++) {
            System.out.println("第"+(i+1)+"筆");
            ExcelLogModel excelLogModel = new ExcelLogModel();
            try {
                long startTime=System.currentTimeMillis();
//
                String[] pathJclArray = filePathList.get(i).split("-");
                String jcl = pathJclArray[1];
                String[] pathArray = pathJclArray[0].split(";");

                String path1 = pathArray[0].replace(":\\", ":/");
                String path2 = pathArray[1].replace(":\\", ":/");


                Path logPath1 = Paths.get(path1);
                Path logPath2 = Paths.get(path2);

                excelLogModel.setLeftFilePath(path1);
                excelLogModel.setRightFilePath(path2);
                excelLogModel.setJcl(jcl);

                String fileName1 = String.valueOf(logPath1.getFileName());
                String fileName2 = String.valueOf(logPath2.getFileName());

                excelLogModel.setLeftFileName(fileName1);
                excelLogModel.setRightFileName(fileName2);
                System.out.println("AD: "+ad+"   JCL: "+jcl);
                System.out.println(fileName1+" VS. "+fileName2);
                if (path1.contains(":/")) {
                    file1Size = fileService.getFileSize(path1);
                    file2Size = fileService.getFileSize(path2);


                    if (file1Size.equals(file2Size)) {
                        fileService.filesCompareByByte(path1, path2,excelLogModel);
                    } else {
                        System.out.println("----------------size different----------------");
                        excelLogModel.setCompareStatus("Fail");
                        excelLogModel.setStatusDescription("size different");
                    }
                }else{
                    fileService.filesCompareByByte(path1, path2,excelLogModel);
                }

                System.out.println("第"+(i+1)+"筆結束");
                System.out.println("------------------------------------------");
                System.out.println("   ");
                long endTime=System.currentTimeMillis();

//                System.out.println("程式執行時間： " + (endTime - startTime) / 60000 + "分" + (endTime - startTime) % 60000 / 1000 + "秒");

            } catch (IOException e) {
//            logger.error("檔案路徑有誤");
                System.out.println("wrong path");
                excelLogModel.setStatusDescription("Can't not find the file");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (config != null) {
                    try {
                        config.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

//            System.out.println(excelLogModel);
            excelLogModelList.add(excelLogModel);
        }

        System.out.println("AD: "+ad+"  總共比對 "+filePathList.size()+ "筆");
        return excelLogModelList;
    }

}
