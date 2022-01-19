package com.runHani.demo;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_noticefile_bas")
public class NoticeFileEntity {

	@Id
	@Column(name = "attached_file_no", nullable = false, updatable = true, length = 20)
	private String attachedFileNo    ;
	
	@Column(name = "file_name", nullable = false, updatable = true, length = 150) 
	private String fileName           ;

	@Column(name = "file_save_name", nullable = false, updatable = true, length = 1000) 
	private String fileSaveName      ;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name ="reg_user", nullable = false)
	private UserEntity user;

	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	
}
