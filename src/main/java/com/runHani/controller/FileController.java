package com.runHani.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runHani.entity.NoticeFileEntity;
import com.runHani.service.NoticeFileService;

@Controller
public class FileController {

	private String baseJSPpath = "notice";

	@Autowired
	private NoticeFileService noticeFileService;

	

	@RequestMapping(value = "/noticeFile/{fileNo}", method = RequestMethod.GET)
	public void downLoadFile(@PathVariable int fileNo, HttpServletResponse response) throws IOException {


		NoticeFileEntity file = noticeFileService.findById(fileNo);
		
		try {
			byte[] files = FileUtils.readFileToByteArray(new File(file.getFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition","attachment; filename=\""+URLEncoder.encode(file.getFileName(),"UTF-8")+"\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			response.getOutputStream().close();
		}
		
		
	}
	

}
