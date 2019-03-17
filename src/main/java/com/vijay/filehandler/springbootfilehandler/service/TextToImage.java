package com.vijay.filehandler.springbootfilehandler.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.vijay.filehandler.springbootfilehandler.model.UserData;

@Component
public class TextToImage {

	private final Logger LOGGER = LoggerFactory.getLogger(TextToImage.class);
	
	@Value("${filedownload.dir}")
    private String localDir;
    
    public String convertTextToImage(List<UserData> userData) {
    	
        int height =0;
        
        String newFilePath = localDir+ "Test-"+generatetFileName();
        
        try {
	        BufferedImage img = new BufferedImage(700,700,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = img.createGraphics();
	
	        for(UserData userInput : userData) {
	        	List<String> lines = new ArrayList<String>();	
	        	
	        	String fontname = userInput.getFontName();
	        	int fonttype = userInput.getFontType();
	        	int fontweight = userInput.getFontWeight();
	            String multitext = userInput.getText();
	        	
	            if(multitext.contains("\n")) {
	            	lines.addAll(Arrays.asList(multitext.split("\n")));
	            }else {
	            	lines.add(multitext);
	            }
	           
	            for(String text : lines) {
		        	
		        	g2d.setFont(new Font(fontname, fonttype, fontweight));
		        	
		        	g2d.setColor(Color.BLACK);
		            height += g2d.getFontMetrics().getHeight();
		            g2d.drawString(" "+ text.trim(), 0,height);
		
		        }
	        }
	        
	        g2d.dispose();
	        ImageIO.write(img, "png", new File(newFilePath));
	        LOGGER.info(">>> Image is created successfull inside ::"+ localDir);
	        return newFilePath;
        } catch (IOException ex) {
            ex.printStackTrace();
            LOGGER.info(">>> ERROR ::: Image is not created");
            return null;
        }
    }
    
    public String generatetFileName() {
    	return new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.png'").format(new Date());
    }
}
