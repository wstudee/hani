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

import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.UserProfileFileEntity;
import com.runHani.service.BoardService;
import com.runHani.service.NoticeFileService;
import com.runHani.service.UserService;

@Controller
public class FileController {

	@Autowired
	private NoticeFileService noticeFileService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	

	@RequestMapping(value = "/noticeFile/{fileNo}", method = RequestMethod.GET)
	public void downLoadFile(@PathVariable int fileNo, HttpServletResponse response) throws IOException {

		
		NoticeFileEntity file = noticeFileService.findById(fileNo);
		
		try {
			byte[] files = FileUtils.readFileToByteArray(new File(file.getFilePath()+"\\"+file.getFileSaveName()));
			
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
	
	@RequestMapping(value = "/boardFile/{fileNo}", method = RequestMethod.GET)
	public void downLoadBoardFile(@PathVariable int fileNo, HttpServletResponse response) throws IOException {
		
		
		BoardFileEntity file = boardService.findByFileId(fileNo);
		
		try {
			byte[] files = FileUtils.readFileToByteArray(new File(file.getFilePath()+"\\"+file.getFileSaveName()));
			
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
	
	@RequestMapping(value = "/boardFile/thumbnail/{fileNo}", method = RequestMethod.GET)
	public void downLoadTthumbnailBoardFile(@PathVariable int fileNo, HttpServletResponse response) throws IOException {
		
		
		BoardFileEntity file = boardService.findByFileId(fileNo);
		
		try {
			byte[] files = FileUtils.readFileToByteArray(new File(file.getFilePath()+"\\t_"+file.getFileSaveName()));
			
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
	
	@RequestMapping(value = "/profileFile/{fileNo}", method = RequestMethod.GET)
	public void downLoadProfileFile(@PathVariable int fileNo, HttpServletResponse response) throws IOException {
		
		
		UserProfileFileEntity file = userService.findByFileId(fileNo);
		
		try {
			byte[] files = FileUtils.readFileToByteArray(new File(file.getFilePath()+"\\"+file.getFileSaveName()));
			
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
