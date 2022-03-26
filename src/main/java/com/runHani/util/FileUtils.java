package com.runHani.util;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.NoticeFileEntity;

public class FileUtils {

	public static List<NoticeFileEntity> parseFileinfo(MultipartHttpServletRequest req) {
		
		
		if(ObjectUtils.isEmpty(req)) {
			return null;
		}
		
		List<NoticeFileEntity> fileList = new ArrayList<NoticeFileEntity>();
		
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
	                NoticeFileEntity noticeFileEntity = new NoticeFileEntity();
	                noticeFileEntity.setFileName(multipartFile.getResource().getFilename());
	                noticeFileEntity.setFileSaveName(newFilename);
	                noticeFileEntity.setFilePath(path+"\\"+newFilename);
	                
	                fileList.add(noticeFileEntity);
	                file = new File(path+"\\"+newFilename);
	                try {
						multipartFile.transferTo(file);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
	            }
	        }
		}
		
	        return fileList;
	    }
	
	
	
}
