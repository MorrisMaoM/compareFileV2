package com.mao.comparefilev2.Service;

import com.mao.comparefilev2.Model.ExcelLogModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PrintInfo {
    public void printCmdInfo(List<ExcelLogModel> excelLogModelList,String adname) throws Exception {

        MyNewHashMap<String> myNewHashMap = new MyNewHashMap();

        String ad = adname.toUpperCase(Locale.ROOT);
        String description = null;


        Robot robot = new Robot();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle areaRectangle = new Rectangle(toolkit.getScreenSize());

        BufferedImage bufferedImage = robot.createScreenCapture(areaRectangle);
        ImageProducer producer = bufferedImage.getSource();
        Image image = toolkit.createImage(producer);

        for(int i=0;i<excelLogModelList.size();i++){

            if(excelLogModelList.get(i).getStatusDescription()==null){
                description="";
            }else {
                description = " ( "+excelLogModelList.get(i).getStatusDescription()+" ) ";
            }
            myNewHashMap.put(excelLogModelList.get(i).getJcl(),excelLogModelList.get(i).getLeftFileName()+" VS "+
                    excelLogModelList.get(i).getRightFileName()+" ===== "+excelLogModelList.get(i).getCompareStatus()+description);
        }

        Iterator<String> iterator = myNewHashMap.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            String value[] = myNewHashMap.get(key).split(",");
            System.out.println(" ");
            System.out.println(ad+" JCL : "+key);
            for(int i=0;i<value.length;i++) {
                System.out.println(value[i]);
            }
            System.out.println(" ");
             CaptureScreen.captureScreen(ad+"_"+key,"ScreenShot");

        }


    }

}
