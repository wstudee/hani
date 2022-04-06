package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_noticefile_bas")
@DynamicUpdate
public class NoticeFileEntity {

	@Id
	@Column(name = "attached_file_no", nullable = false, updatable = true, length = 20)
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

	public NoticeFileEntity() {
		
	}
	
	public NoticeFileEntity(String fileName, String fileSaveName, LocalDateTime regDate) {
		super();
		this.fileName = fileName;
		this.fileSaveName = fileSaveName;
		this.regDate = regDate;
	}
	
	
	
}
