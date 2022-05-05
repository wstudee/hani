package com.runHani.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.imageio.ImageIO;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.FileEntity;

public class FileUtils {

	public static List<FileEntity> parseFileinfo(MultipartHttpServletRequest req) {
		
		
		if(ObjectUtils.isEmpty(req)) {
			return null;
		}
		
		List<FileEntity> fileList = new ArrayList<FileEntity>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		ZonedDateTime currentDate = ZonedDateTime.now();
		
		String path ="D:\\sts-4.9.0.RELEASE\\upload\\image\\"+currentDate.format(format);
		
		File file = new File(path);
		
		
		if(!file.exists()) {file.mkdir();}
		
		Iterator<String> it = req.getFileNames();
		
		String newFilename, originalFileExtension, contentType;
		
		while(it.hasNext()) {
			List<MultipartFile> files = req.getFiles(it.next());
			
			
			for(MultipartFile multipartFile : files) {
				
				if(!multipartFile.isEmpty()) {
					
					contentType =  multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else{
	                    if(contentType.contains("image/jpeg")){
	                        originalFileExtension = ".jpg";
	                    }
	                    else if(contentType.contains("image/png")){
	                        originalFileExtension = ".png";
	                    }
	                    else if(contentType.contains("image/gif")){
	                        originalFileExtension = ".gif";
	                    }
	                    // 다른 파일 명이면 아무 일 하지 않는다
	                    else{
	                        break;
	                    }
	                }
	                newFilename = Long.toString(System.nanoTime()) + originalFileExtension;
	                // 생성 후 리스트에 추가
	                FileEntity FileEntity = new FileEntity();
	                FileEntity.setFileName(multipartFile.getResource().getFilename());
	                FileEntity.setFileSaveName(newFilename);
	                FileEntity.setFilePath(path);
	                
	                fileList.add(FileEntity);
	                file = new File(path+"\\"+newFilename);
	                
	                
	                try {
						multipartFile.transferTo(file);
						
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}finally {
						
						try {
							makeThumbnail(file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
	                
	                
	                
	            }
				
				
				
				
				
				
	        }
		}
		
	        return fileList;
	    }
	
	
	private static void makeThumbnail(File file) throws IOException {
		
		FileOutputStream out = null;
		
		Image original = ImageIO.read(file);
		int divideValue = 1;
		if(original.getWidth(null) > 500)
		{
		      divideValue = original.getWidth(null) / 500;
		}

		Image resize =
				original.getScaledInstance(original.getWidth(null)/divideValue, original.getHeight(null)/divideValue, Image.SCALE_SMOOTH);
		BufferedImage newImage =
				new BufferedImage(original.getWidth(null)/divideValue, original.getHeight(null)/divideValue, BufferedImage.TYPE_INT_RGB);
		
		
		Graphics g = newImage.getGraphics();
		g.drawImage(resize, 0, 0, null);
		g.dispose();
		

		try {
			out = new FileOutputStream(file.getParent()+"\\t_"+file.getName());
			ImageIO.write(newImage, "jpg", out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		
		
	}
	
	
	
}
