package com.runHani.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.runHani.repository.BoardFileRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_board_bas")
@Data
public class BoardEntity {

	@Id
	@Column(name = "board_no", nullable = false, updatable = true, length = 20) 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardNo            ;
	
	@Column(name = "title", nullable = false, updatable = true, length = 50) 
	private String title;
	
	@Column(name = "board_status", nullable = false, updatable = true, length = 10) 
	@ColumnDefault("'I'")
	private String boardStatus = "I";
	
	@Column(name = "contents", updatable = true) 
	private String contents;
	
	@Column(name = "color", updatable = true) 
	private String color;
	
	@Column(name = "update_date", nullable = false, updatable = true)
	private LocalDateTime updateDate = LocalDateTime.now() ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="update_user", nullable = false)
	private UserEntity updateUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="reg_user", nullable = false)
	private UserEntity regUser;

	@OneToOne( cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	@JoinColumn(name = "attached_file_no")
	private BoardFileEntity boardFileEntity ;   


	@OneToOne( cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	@JoinColumn(name = "group_sn")
	private GroupEntity group;   

	
	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	

}
