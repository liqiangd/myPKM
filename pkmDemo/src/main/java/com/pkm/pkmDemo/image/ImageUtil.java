package com.pkm.pkmDemo.image;
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGEncodeParam;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.misc.BASE64Decoder;

import java.awt.AlphaComposite;  
import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.RenderingHints;  
import java.awt.Transparency;  
import java.awt.geom.Area;  
import java.awt.geom.RoundRectangle2D;  
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;  
public class ImageUtil {
	 /** 
     * 针对高度与宽度进行等比缩放 
     * 
     * @param img 
     * @param maxSize 要缩放到的尺寸 
     * @param type 1:高度与宽度的最大值为maxSize进行等比缩放 , 2:高度与宽度的最小值为maxSize进行等比缩放 
     * @return 
     */  
    private static Image getScaledImage(BufferedImage img, int maxSize, int type) {  
        int w0 = img.getWidth();  
        int h0 = img.getHeight();  
        int w = w0;  
        int h = h0;  
        if (type == 1) {  
            w = w0 > h0 ? maxSize : (maxSize * w0 / h0);  
            h = w0 > h0 ? (maxSize * h0 / w0) : maxSize;  
        } else if (type == 2) {  
            w = w0 > h0 ? (maxSize * w0 / h0) : maxSize;  
            h = w0 > h0 ? maxSize : (maxSize * h0 / w0);  
        }  
        Image image = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);  
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g = result.createGraphics();  
        g.drawImage(image, 0, 0, null);//在适当的位置画出  
        return result;  
    }  
  
    /** 
     * 先按最小宽高为size等比例绽放, 然后图像居中抠出直径为size的圆形图像 
     * 
     * @param img 
     * @param size 
     * @return 
     */  
    private static BufferedImage getRoundedImage(BufferedImage img, int size) {  
        return getRoundedImage(img, size, size / 2, 2);  
    }  
  
    /** 
     * 先按最小宽高为size等比例绽放, 然后图像居中抠出半径为radius的圆形图像 
     * 
     * @param img 
     * @param size 要缩放到的尺寸 
     * @param radius 圆角半径 
     * @param type 1:高度与宽度的最大值为maxSize进行等比缩放 , 2:高度与宽度的最小值为maxSize进行等比缩放 
     * @return 
     */  
    private static BufferedImage getRoundedImage(BufferedImage img, int size, int radius, int type) {  
  
        BufferedImage result = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g = result.createGraphics();  
  
        //先按最小宽高为size等比例绽放, 然后图像居中抠出直径为size的圆形图像  
        Image fixedImg = getScaledImage(img, size, type);  
        g.drawImage(fixedImg, (size - fixedImg.getWidth(null)) / 2, (size - fixedImg.getHeight(null)) / 2, null);//在适当的位置画出  
  
        //圆角  
        if (radius > 0) {  
            RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, size, size, radius * 2, radius * 2);  
            Area clear = new Area(new Rectangle(0, 0, size, size));  
            clear.subtract(new Area(round));  
            g.setComposite(AlphaComposite.Clear);  
  
            //抗锯齿  
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
            g.fill(clear);  
            g.dispose();  
        }  
        return result;  
    }  
  
    /** 
     * 使用删除alpha值的方式去掉图像的alpha通道 
     * 
     * @param $image 
     * @return 
     */  
    protected static BufferedImage get24BitImage(BufferedImage $image) {  
        int __w = $image.getWidth();  
        int __h = $image.getHeight();  
        int[] __imgARGB = getRGBs($image.getRGB(0, 0, __w, __h, null, 0, __w));  
        BufferedImage __newImg = new BufferedImage(__w, __h, BufferedImage.TYPE_INT_RGB);  
        __newImg.setRGB(0, 0, __w, __h, __imgARGB, 0, __w);  
        return __newImg;  
    }  
  
    /** 
     * 使用绘制的方式去掉图像的alpha值 
     * 
     * @param $image 
     * @param $bgColor 
     * @return 
     */  
    protected static BufferedImage get24BitImage(BufferedImage $image, Color $bgColor) {  
        int $w = $image.getWidth();  
        int $h = $image.getHeight();  
        BufferedImage img = new BufferedImage($w, $h, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = img.createGraphics();  
        g.setColor($bgColor);  
        g.fillRect(0, 0, $w, $h);  
        g.drawRenderedImage($image, null);  
        g.dispose();  
        return img;  
    }  
  
    /** 
     * 将32位色彩转换成24位色彩（丢弃Alpha通道） 
     * 
     * @param $argb 
     * @return 
     */  
    public static int[] getRGBs(int[] $argb) {  
        int[] __rgbs = new int[$argb.length];  
        for (int i = 0; i < $argb.length; i++) {  
            __rgbs[i] = $argb[i] & 0xFFFFFF;  
        }  
        return __rgbs;  
    }  
  
    /**
     * png转img  返回base64
     * @param   源文件路径InputStream
     * @param   null
     * @param   640传个固定的长度640
     * @param   1固定值，最高的图像质量
     * @return 
     * */
    public static String toJPG(InputStream img, File save, int size, int quality) throws IOException {  
//        FileOutputStream out = new FileOutputStream(save);  
//    	long startTime=System.currentTimeMillis();
        ByteArrayOutputStream out = new ByteArrayOutputStream();                            
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
  
        BufferedImage image = (BufferedImage) getRoundedImage(ImageIO.read(img), size, 0, 2);//默认无圆角  
  
        //如果图像是透明的，就丢弃Alpha通道  
        if (image.getTransparency() == Transparency.TRANSLUCENT) {  
            image = get24BitImage(image);  
        }  
  
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);//使用jpeg编码器  
        param.setQuality(0.8f, true);//高质量jpg图片输出  
        encoder.encode(image, param);  
        byte[] byteimg=out.toByteArray();
//        System.out.println(Base64.encodeBase64String(image1));
        out.close();  
        img.close();
//        System.out.println(System.currentTimeMillis()-startTime);
        return Base64.encodeBase64String(byteimg);
    }  
  
    public static void toPNG(File img, File save, int size) throws IOException {  
        ImageIO.write((BufferedImage) getRoundedImage(ImageIO.read(img), size, 0, 2), "PNG", save);//默认无圆角  
    }  
  

    
    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){     
           StringBuilder stringBuilder = new StringBuilder();     
           if (src == null || src.length <= 0) {     
               return null;     
           }     
           for (int i = 0; i < src.length; i++) {     
               int v = src[i] & 0xFF;     
               String hv = Integer.toHexString(v);     
               if (hv.length() < 2) {     
                   stringBuilder.append(0);     
               }     
               stringBuilder.append(hv);     
           }     
           return stringBuilder.toString();     
       }
    
    /**
     * 根据文件流读取图片文件真实类型
     * @param is
     * @return
     */
    public static String getTypeByStream(InputStream is){
        byte[] b = new byte[4];  
           try {
      is.read(b, 0, b.length);
     } catch (IOException e) {
      e.printStackTrace();
     }
           String type = bytesToHexString(b).toUpperCase();
           if(type.contains("FFD8FF")){
            return "jpg";
           }else if(type.contains("89504E47")){
            return "png";
           }else if(type.contains("47494638")){
            return "gif";
           }else if(type.contains("49492A00")){
            return "tif";
           }else if(type.contains("424D")){
            return "bmp";
           }
           return type;
       }
    
    /**base64转inputStream*/
    public static InputStream BaseToInputStream(String base64string){  
        ByteArrayInputStream stream = null;
        try {
        BASE64Decoder decoder = new BASE64Decoder(); 
        byte[] bytes1 = decoder.decodeBuffer(base64string);  
        stream = new ByteArrayInputStream(bytes1);  
    } catch (Exception e) {

    }
            return stream;  
        } 
    public static void main(String[] args) throws IOException {  
        File img = new File("e:\\123.jpg");  
//        File save = new File("e:\\456.jpg");  
//        long startTime=System.currentTimeMillis();
//        toJPG(img, null, 640, 100); 
        byte[] binaryData= FileUtils.readFileToByteArray(new File("e:\\123.jpg"));
//        FileInputStream is = new FileInputStream(img);       
       String base64Imag=Base64.encodeBase64String(binaryData);
       
       //转成input 流
       InputStream in1= BaseToInputStream(base64Imag);
       InputStream in2= BaseToInputStream(base64Imag);
       
       long startTime=System.currentTimeMillis();
        String type = getTypeByStream(in1);
        System.out.println("文件类型："+type);
        System.out.println(toJPG(in2, null, 640, 100));
        in1.close();
        in2.close();
        System.out.println(System.currentTimeMillis()-startTime);
    }  
    
}
