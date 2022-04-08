package com.runHani.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_notice_bas")
@Data
public class NoticeEntity {

	@Id
	@Column(name = "notice_no", nullable = false, updatable = true, length = 20) 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeNo            ;
	
	@Column(name = "title", nullable = false, updatable = true, length = 50) 
	private String title;
	
	@Column(name = "notice_status", nullable = false, updatable = true, length = 10) 
	@ColumnDefault("'I'")
	private String noticeStatus = "I";
	
	@Column(name = "contents", updatable = true) 
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
	
	
	@OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.ALL)
//	@OneToMany(cascade = CascadeType.PERSIST)
//	@JoinColumn(name="notice_no", nullable = false)
	private Collection<NoticeFileEntity> fileList;


	
	
	
	
	
}
