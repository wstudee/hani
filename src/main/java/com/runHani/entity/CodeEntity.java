package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_common_cod")
public class CodeEntity {

	@Id
	@Column(name = "code_cl_code", nullable = false, updatable = true , length  = 10)
	private String codeClCode ;
	
	@Column(name = "code_cl_name", nullable = false, updatable = true , length  = 50)
	private String codeClName ;
	
	@Column(name = "code", nullable = false, updatable = true , length  = 10)
	private String code         ;
	
	@Column(name = "code_name", nullable = false, updatable = true,  length  = 50)
	private String codeName    ;
	
	@Column(name = "code_info", nullable = false, updatable = true, length  = 500)
	private String codeInfo    ;

	@Column(name = "update_user", nullable = false, updatable = true, length  = 150)
	private String updateUser  ;

	@Column(name = "update_date", nullable = false, updatable = true)
	private LocalDateTime updateDate = LocalDateTime.now() ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="reg_user", nullable = false)
	private UserEntity regUser;

	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	
}
