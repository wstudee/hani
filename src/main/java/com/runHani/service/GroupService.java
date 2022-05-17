package com.runHani.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.FileEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.GroupFileEntity;
import com.runHani.entity.UserEntity;
import com.runHani.entity.UserGroupEntity;
import com.runHani.repository.GroupFileRepository;
import com.runHani.repository.GroupRepository;
import com.runHani.repository.UserGroupRepository;
import com.runHani.util.FileUtils;
import com.runHani.util.SessionUtil;


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
		
		return list;
	}
	
	public Page<GroupEntity> getAllList(Pageable pageable) {

		
		return groupRepository.findByGroupStatus(pageable, "O");
	}

	
	public int getListCnt() {
		UserEntity user = SessionUtil.getUserEntity();
		return groupRepository.selectMyGroupCnt(user);
	}
	


	public void registerGroup(GroupEntity group, MultipartHttpServletRequest req) {
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		
		UserEntity user = SessionUtil.getUserEntity();
		group.setLeader(user);
		
		if(fileList.size() > 0) {
			GroupFileEntity newFile = new GroupFileEntity(fileList.get(0));
			group.setFile(groupFileRepository.save(newFile));
			newFile.setGroup(group);
		}
		
		GroupEntity newGroup =  groupRepository.save(group);

		//리더도 회원가q
		UserGroupEntity newMemger = new UserGroupEntity();
		newMemger.setGroupSn(newGroup);
		newMemger.setUser(user);
		userGroupRepository.save(newMemger);
		
		
	}


	public GroupFileEntity findByAttachedFileNo(int fileNo) {
		return groupFileRepository.findByAttachedFileNo(fileNo);
	}


	public GroupEntity findById(int groupNo) {
		return groupRepository.findById(groupNo).get();
	}


	public List<UserGroupEntity> findGroupMemeber(GroupEntity group) {
		
		return userGroupRepository.findByGroupSn(group);
	}


	public void registerGroup(int groupNo) {
		
		
		UserEntity user = SessionUtil.getUserEntity();
		UserGroupEntity newMemger = new UserGroupEntity();
		newMemger.setGroupSn(groupRepository.findById(groupNo).get());
		newMemger.setUser(user);
		userGroupRepository.save(newMemger);
		
	}


	public boolean isMember(GroupEntity group) {

		String email = SessionUtil.getUserSession().getEmail();
		List<UserGroupEntity> memebers = group.getMemeberList();
		
		for(UserGroupEntity ug : memebers) {
			
			if(ug.getUser().getEmail().equals(email)){
				return true;
			}
			
		}
		
		return false;
	}


	public List<UserGroupEntity> findUserGroupByGroup(GroupEntity group) {
		
		List<UserGroupEntity> memebers = group.getMemeberList();
		
		
		
		return memebers;
	}




   

}
