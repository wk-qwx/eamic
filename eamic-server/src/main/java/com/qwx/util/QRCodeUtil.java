package com.qwx.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.alibaba.druid.util.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
    /**
     * 颜色
     */
    private static final int QRCOLOR = 0xFF000000;
    /**
     * 背景颜色
     */
    private static final int BGWHITE = 0xFFFFFFFF;
    /**
     * 存放路径
     */
    private static final String CODEPATH = ConfigUtil.getProperty("filePath")+"\\codefile\\";


    public static void main(String[] args) {
        try {
            System.out.println(System.currentTimeMillis());
            getQRCode("fe212ac018f4711811", "衡阳国家电网衡山维护班组", "(qwert1911190001)");
            System.out.println(System.currentTimeMillis());
            System.out.println("生成ok");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getQRCode(String data, String sunitsText, String codeText) {
        try {
        	QRCodeUtil zp = new QRCodeUtil();
            BufferedImage bim = zp.getQR_CODEBufferedImage(data, BarcodeFormat.QR_CODE, 205, 185, zp.getDecodeHintType());
            //字节数组
            return zp.addText_QRCode(bim, sunitsText, codeText);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param bim       图片
     * @param belowText 二维码下方显示文字
     * @return
     */
    public String addText_QRCode(BufferedImage bim, String sunitsText, String codeText) {
        try {
            BufferedImage image = bim;
            if (sunitsText != null && !sunitsText.equals("")) {
            	
                BufferedImage outImage = new BufferedImage(205, 230, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                outg.drawImage(image, 0, 10, image.getWidth(), image.getHeight(), null);
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("宋体", Font.PLAIN, 12));
                
                int strWidth = outg.getFontMetrics().stringWidth(sunitsText);
                outg.drawString(sunitsText, 100 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 2);
                
                int strWidth2 = outg.getFontMetrics().stringWidth(codeText);
                outg.drawString(codeText, 100 - strWidth2 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 18);
                
                
                outg.dispose();
                outImage.flush();
                image = outImage;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "jpg", baos);

            BufferedImage newBufferedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(image, 0, 0,
                    Color.WHITE, null);
            File filePath = new File(CODEPATH);
            QRCodeUtil.judeDirExists(filePath);
            ImageIO.write(newBufferedImage, "jpg", new File(CODEPATH + "SZ-" + System.currentTimeMillis() + "code.jpg"));
            //图片写到字节数组中
            String imageBase64QRCode = Base64.byteArrayToBase64(baos.toByteArray());
            baos.close();
            image.flush();
            return imageBase64QRCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绘制二维码
     *
     * @param content       扫描内容
     * @param barcodeFormat 格式
     * @param width
     * @param height
     * @param hints         二维码属性设置
     * @return 二维码图片
     */
    public BufferedImage getQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * 设置二维码属性
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }
    /**
     * 判断文件夹是否存在
     *
     * @param file
     */
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
            } else {
            }
        } else {
            file.mkdir();
        }

    }
}