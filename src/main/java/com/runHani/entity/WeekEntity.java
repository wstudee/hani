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
@Table(name = "tb_week_bas")
@Data
public class WeekEntity {

	@Id
	@Column(name = "sn", nullable = false, updatable = true, length = 20) 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn            ;
	
	@Column(name = "date", nullable = false, updatable = true, length = 50) 
	private String date;
	

}
