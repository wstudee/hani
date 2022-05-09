package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_userprofile_bas")
public class UserProfileFileEntity {

	@Id
	@Column(name = "attached_file_no", nullable = false,  length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attachedFileNo    ;
	
	@Column(name = "file_name", nullable = false, updatable = true, length = 150) 
	private String fileName           ;

	@Column(name = "file_save_name", nullable = false, updatable = true, length = 1000) 
	private String fileSaveName      ;
	
	@Column(name = "file_path", nullable = false, updatable = true, length = 1000) 
	private String filePath      ;
	
	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "email" )
	private UserEntity user ;         
	
	
	public UserProfileFileEntity() {
		
	}
	
	public UserProfileFileEntity(String fileName, String fileSaveName, LocalDateTime regDate) {
		super();
		this.fileName = fileName;
		this.fileSaveName = fileSaveName;
		this.regDate = regDate;
	}

	public UserProfileFileEntity(FileEntity file) {
		super();
		this.fileName = file.getFileName();
		this.filePath = file.getFilePath();
		this.fileSaveName = file.getFileSaveName();
		this.regDate = file.getRegDate();
	}

	@Override
	public String toString() {
		return "UserProfileFileEntity";
	}

	
}
