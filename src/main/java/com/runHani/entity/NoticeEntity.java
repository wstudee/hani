package com.runHani.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_notice_bas")
public class NoticeEntity {

	@Id
	@Column(name = "board_no", nullable = false, updatable = true, length = 20) 
	private String boardNo            ;
	
	@Column(name = "title", nullable = false, updatable = true, length = 50) 
	private String title;
	
	@Column(name = "board_status", nullable = false, updatable = true, length = 10) 
	private String boardStatus;
	
	@Column(name = "contents", updatable = true) 
	@Lob
	private String contents;
	
	@Column(name = "update_date", nullable = false, updatable = true)
	private LocalDateTime updateDate = LocalDateTime.now() ;
	
	@ManyToOne
	@JoinColumn(name ="update_user", nullable = false)
	private UserEntity updateUser;

	@ManyToOne
	@JoinColumn(name ="reg_user", nullable = false)
	private UserEntity regUser;
	
	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	
	@OneToMany
	@JoinColumn(name="board_no", nullable = false)
	private Collection<NoticeFileEntity> fileList;
	
}
