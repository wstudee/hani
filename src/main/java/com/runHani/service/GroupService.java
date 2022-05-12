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
import com.runHani.entity.UserGroupEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.GroupFileRepository;
import com.runHani.repository.GroupRepository;
import com.runHani.repository.UserGroupRepository;
import com.runHani.repository.UserRepository;
import com.runHani.util.FileUtils;
import com.runHani.util.SessionUtil;
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
    private UserGroupRepository userGroupRepository;
    
    @Autowired
    public void setUserGroupEntity( UserGroupRepository userGroupRepository) {
    	this.userGroupRepository = userGroupRepository;
    }
    
    
	public List<GroupEntity> getList(Pageable pageable) {
		
		UserEntity user = SessionUtil.getUserEntity();
		List<GroupEntity> list=  groupRepository.selectMyGroup(user, pageable);
		
		addFiletoList(list);
		
		return list;
	}
	
	public int getListCnt() {
		UserEntity user = SessionUtil.getUserEntity();
		return groupRepository.selectMyGroupCnt(user);
	}
	

	private void addFiletoList(List<GroupEntity> list) {
		for (GroupEntity groupEntity : list) {
			groupEntity.setFile(groupFileRepository.findByGroup(groupEntity));
			
		}
	}


	public void registerGroup(GroupEntity group, MultipartHttpServletRequest req) {
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		
		if(fileList.size() > 0) {
			GroupFileEntity newFile = new GroupFileEntity(fileList.get(0));
			groupFileRepository.save(newFile);
			newFile.setGroup(group);
		}
		
		
		UserEntity user = SessionUtil.getUserEntity();
		group.setLeader(user);
		GroupEntity newGroup =  groupRepository.save(group);

		//리더도 회원가q
		UserGroupEntity newMemger = new UserGroupEntity();
		newMemger.setGroupSn(newGroup);
		newMemger.setUser(user);
		userGroupRepository.save(newMemger);
		
		
	}


	
    
   

}
