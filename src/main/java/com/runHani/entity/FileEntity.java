package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FileEntity {

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
	

	
}
