package com.pkm.pkmDemo.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertImageFile {
	
	/** 
     * Created on 2010-7-13  
     * <p>Discription:[convert GIF->JPG GIF->PNG PNG->GIF(X) PNG->JPG ]</p> 
     * @param source 
     * @param formatName 
     * @param result 
     * @author:[shixing_11@sina.com] 
     */  
    public static void convert(String source, String formatName, String result)  
    {  
        try  
        {  
            File f = new File(source);  
            f.canRead();  
            BufferedImage src = ImageIO.read(f);  
            ImageIO.write(src, formatName, new File(result));  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
	public static void main(String[] args) {
		convert("D:\\\\Work\\\\\\\\techshino\\\\\\\\project\\\\\\\\code\\\\\\\\com.bankcomm-交行统一生物识别认证平台\\\\\\\\20170824\\\\\\\\b77b5b2f1554e65fe56412cba6dba00d2a9fb833_1.jpg"
				,"JPEG","D:\\123.jpg");
	   }
}
