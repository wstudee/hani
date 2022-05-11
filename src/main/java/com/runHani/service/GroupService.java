package com.runHani.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.GroupFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.GroupFileRepository;
import com.runHani.repository.GroupRepository;
import com.runHani.repository.UserRepository;
import com.runHani.util.FileUtils;
import com.runHani.vo.UserSessionVO;


@Service
public class GroupService  {
	
    private GroupRepository groupRepository;

    @Autowired
    public void findGroupList(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    private GroupFileRepository groupFileRepository;
    
    @Autowired
    public void set(GroupFileRepository groupFileRepository) {
    	this.groupFileRepository = groupFileRepository;
    }

	public Page<GroupEntity> getListPage(SearchEntity searchEntity) {
		return null;
	}
    
    
	public List<GroupEntity> getList(SearchEntity searchEntity) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserSessionVO sessionUser = (UserSessionVO) principal;
		
		UserEntity user = new UserEntity(sessionUser);
		
		List<GroupEntity> list=  groupRepository.selectMyGroup(sessionUser.getEmail());
		
		return list;
	}

	public void registerGroup(GroupEntity group, MultipartHttpServletRequest req) {
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		
		if(fileList.size() > 0) {
			GroupFileEntity newFile = new GroupFileEntity(fileList.get(0));
			groupFileRepository.save(newFile);
			newFile.setGroup(group);
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserSessionVO sessionUser = (UserSessionVO) principal;
		
		UserEntity user = sessionUser.getUser();
		group.setLeader(user);

		groupRepository.save(group);
		
	}
    
   

}
