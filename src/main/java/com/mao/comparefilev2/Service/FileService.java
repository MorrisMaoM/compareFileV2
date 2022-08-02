package com.mao.comparefilev2.Service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import com.jcraft.jsch.*;
import com.mao.comparefilev2.Model.ExcelLogModel;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Vector;

public class FileService {

//    private static final Logger logger = LogManager.getLogger(FileService.class);

    public long getFileSize(String filePath) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        FileChannel fc = null;
        fc = fis.getChannel();
        Long fileSize = fc.size();
        fc.close();
        fis.close();
        return fileSize;
    }

    public long filesCompareByByte(String filePath1, String filePath2, ExcelLogModel excelLogModel) throws IOException, InterruptedException,NullPointerException {


        String configProperties = "config.properties";
        Properties props = new Properties();
        props.load(new InputStreamReader(new FileInputStream(configProperties), "UTF-8"));

        String leftHost = props.getProperty("leftHost");
        String leftUser = props.getProperty("leftUser");
        String leftPassword = props.getProperty("leftPassword");
        String rightHost = props.getProperty("rightHost");
        String rightUser = props.getProperty("rightUser");
        String rightPassword = props.getProperty("rightPassword");
        int whichFileNotFound = 0;

//                properties.load(config);


        BufferedInputStream fis1 = null;
        BufferedInputStream fis2 = null;
        long fileSize1 = 0;
        long fileSize2 = 0;

        try {

            whichFileNotFound=0;
            if (!filePath1.contains(":/")) {

//                System.out.println("有進205");
                JSch jsch = new JSch();
                Session session = null;


                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");


                session = jsch.getSession(leftUser, leftHost, 22);
                session.setConfig(config);
                session.setPassword(leftPassword);
                session.connect();


                ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
                sftpChannel.connect();


                System.out.println("left file: " + filePath1);

                InputStream ins = sftpChannel.get(filePath1);
                whichFileNotFound++;
                fileSize1 = sftpChannel.lstat(filePath1).getSize();

                fis1 = new BufferedInputStream(ins);


//                ins.close();
//                session.disconnect();

            } else {
//            System.out.println("沒進205");
                fis1 = new BufferedInputStream(new FileInputStream(filePath1));
            }

            if (!filePath2.contains(":/")) {
//                System.out.println("有進134");
                JSch jsch = new JSch();
                Session session = null;


                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");

                session = jsch.getSession(rightUser, rightHost, 22);
                session.setConfig(config);
                session.setPassword(rightPassword);
                session.connect();

                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftpChannel = (ChannelSftp) channel;

                System.out.println("right file: " + filePath2);
                InputStream stream = sftpChannel.get(filePath2);
                whichFileNotFound++;
                fileSize2 = sftpChannel.lstat(filePath2).getSize();
                fis2 = new BufferedInputStream(stream);


//                    stream.close();
//                    session.disconnect();

            } else {
//                System.out.println("沒進134");
                fis2 = new BufferedInputStream(new FileInputStream(filePath2));
            }
            if (fileSize1 != fileSize2) {
                System.out.println("File size different");
//                excelLogModel.setCompareStatus("Fail");
//                excelLogModel.setStatusDescription("Files size are different");
                throw new SizeDifferentException();
            }

            Path path1 = Paths.get(filePath1);
            Path path2 = Paths.get(filePath2);

//            excelLogModel.setLeftFilePath(filePath1);
//            excelLogModel.setRightFilePath(filePath2);

            String fileName1 = String.valueOf(path1.getFileName());
            String fileName2 = String.valueOf(path2.getFileName());

//            excelLogModel.setLeftFileName(fileName1);
//            excelLogModel.setRightFileName(fileName2);


            System.out.println("Processing.....");

//            int intFis1 = fis1.read();
//            int intFis2 = fis2.read();
//        System.out.println("INT 1: "+ intFis1+"    INT 2: "+intFis2);



            int ch = 0;
            long pos = 1;

            while ((ch = fis1.read()) != -1) {
                if (ch != fis2.read()) {
//                    logger.error("----------------No "+ pos + "is different----------------");
                    System.out.println("----------------" + pos + "  is different----------------");
                    excelLogModel.setCompareStatus("Fail");
                    excelLogModel.setStatusDescription("NO." + pos + " is different");
                    return pos;
                }
                pos++;
            }
            if (fis2.read() == -1) {
//                logger.info("-----------------No different---------------");
                System.out.println("-----------------No different----------------");
                excelLogModel.setCompareStatus("Success");
                return -1;
            } else {
                return pos;
            }

        }catch (NullPointerException e){

            if(whichFileNotFound==1) {
                System.out.println("Left file not found");
                excelLogModel.setCompareStatus("Fail");
                excelLogModel.setStatusDescription("Left file not found");
            }else if(whichFileNotFound==2){
                System.out.println("Right file not found");
                excelLogModel.setCompareStatus("Fail");
                excelLogModel.setStatusDescription("Right file not found");
            }
        } catch (SizeDifferentException e) {
            excelLogModel.setCompareStatus("Fail");
            excelLogModel.setStatusDescription("File size different");
        } catch (JSchException e) {
        e.printStackTrace();
        } catch (SftpException e) {
        if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
            if(whichFileNotFound==0) {
                System.out.println("Left file not found");
                excelLogModel.setCompareStatus("Fail");
                excelLogModel.setStatusDescription("Left file not found");
            }else if(whichFileNotFound==1){
                System.out.println("Right file not found");
                excelLogModel.setCompareStatus("Fail");
                excelLogModel.setStatusDescription("Right file not found");
            }
        }

    }
        return fileSize1;
    }
class SizeDifferentException extends Exception
{
}}




