package com.runHani;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.runHani.entity.UserEntity;
import com.runHani.repository.UserRepository;
import com.runHani.vo.UserSessionVO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	 private UserRepository userRepository;

	    @Autowired
	    public void setBoardRepository(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }


		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserEntity user = userRepository.findByEmail(username);
			
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}else {
				UserSessionVO userSessionVO = new UserSessionVO(user);
				return userSessionVO;
			}
		}    
    
    
    
}