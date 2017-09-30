package com.pkm.pkmDemo.image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;  
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;  
import javax.media.jai.RenderedOp;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

//import com.sun.media.jai.codec.BMPEncodeParam;  
import com.sun.media.jai.codec.ImageCodec;  
import com.sun.media.jai.codec.ImageEncoder;  
import com.sun.media.jai.codec.JPEGEncodeParam;  
public class PngToJPEG {

	public static void main(String[] args) throws Exception{
//		BufferedImage bufferedImage;
//		try {
//			bufferedImage=ImageIO.read(new File("e:\\123.jpg"));
//			BufferedImage newBufferedImage =new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//			newBufferedImage .createGraphics().drawImage(bufferedImage,0,0,null,null);
//			
//			ImageIO.write(newBufferedImage,"jpg",new File("e:\\456.jpg")  );
//			
//			JOptionPane.showMessageDialog(null,"ok");
//			
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,e);
//		}
		
		BufferedImage image = null;
		BufferedImage imageRGB = null;
        
		// imageBytes is some png file you read
		image = ImageIO.read(new ByteArrayInputStream(FileUtils.readFileToByteArray(new File("e:\\123.jpg"))));

		// Attempt at PNG read fix
		imageRGB = new BufferedImage(image.getWidth(),
		    image.getHeight(), BufferedImage.TYPE_INT_RGB);

		// write data into an RGB buffered image, no transparency
		imageRGB.setData(image.getData());

		ImageIO.write(imageRGB,"jpg",new File("e:\\456.jpg")  );
		// return the RGB buffered image, or do ImageIO.write( ... );
//		return imageRGB; // fixed for jpeg
	}

}
