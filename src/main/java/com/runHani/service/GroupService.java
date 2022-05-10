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
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.GroupRepository;
import com.runHani.repository.UserRepository;
import com.runHani.util.FileUtils;
import com.runHani.vo.UserSessionVO;


@Service
public class GroupService  {
	
    private GroupRepository groupRepository;

    @Autowired
    public void setBoardRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

	public Page<GroupEntity> getListPage(SearchEntity searchEntity) {
		return null;
	}
    
    
	public List<GroupEntity> getList(SearchEntity searchEntity) {
		return groupRepository.findAll();
	}
    
   

}
