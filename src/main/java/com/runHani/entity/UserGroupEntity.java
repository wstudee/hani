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
@Table(name = "tb_usergroup_bas")
@Data
public class UserGroupEntity {

	@Id
	@Column(name = "sn", nullable = false, updatable = true, length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;

	@ManyToOne
	@JoinColumn( referencedColumnName = "sn" , name = "group_sn")
	private GroupEntity groupSn;
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="email", nullable = false, updatable = true)
	private UserEntity user;

	@Override
	public String toString() {
		return "UserGroupEntity [sn=" + sn + "]";
	}
	
	
}
