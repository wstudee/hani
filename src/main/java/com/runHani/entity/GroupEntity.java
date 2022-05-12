package com.runHani.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.runHani.repository.BoardFileRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_group_bas")
@Data
public class GroupEntity {

	@Id
	@Column(name = "sn", nullable = false, updatable = true, length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn    ;

	@Column(name = "group_name", nullable = false, updatable = true, length =50)
	private String groupName;
	
	@Column(name = "member_count", nullable = true, updatable = true, length =50)
	private int memberCount ;
		
	@Column(name = "group_status", nullable = true, updatable = true, length =50)
	private String groupStatus;
	
	
	@Column(name = "update_cnt", nullable = true, updatable = true)
	private int updateCnt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="leader", nullable = false, updatable = true)
	private UserEntity leader;
	
	@OneToMany
	@JoinColumn(name ="group_sn")
	private List<UserGroupEntity> memeberList;
	
	
	@OneToOne
	@JoinColumn(name = "attached_file_no")
	private GroupFileEntity file ;    
}
