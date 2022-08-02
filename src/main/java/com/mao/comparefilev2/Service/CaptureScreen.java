package com.mao.comparefilev2.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CaptureScreen {
    public static void captureScreen(String fileName, String folder) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //儲存路徑
        File screenFile = new File(folder);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, fileName+".jpg");
        ImageIO.write(image, "jpg", f);
        //自動開啟
//        if (Desktop.isDesktopSupported()
//                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
//            Desktop.getDesktop().open(f);
    }
}
