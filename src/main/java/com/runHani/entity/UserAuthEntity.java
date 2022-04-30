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
@Table(name = "tb_userauth_bas")
@Data
public class UserAuthEntity {

	@Id
	@Column(name = "sn", nullable = false, updatable = true, length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn    ;

	@OneToOne
	@JoinColumn(name ="email", nullable = false)
	private UserEntity user;
	
	@Column(name = "authority", nullable = false, updatable = true)
	private String authority   ;
	
}
