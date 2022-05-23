package com.runHani.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.runHani.entity.WeekEntity;
import com.runHani.repository.GroupRepository;
import com.runHani.repository.WeekRepository;

@Component
public class WeekTableScheduler {
	

	private WeekRepository repo;

	@Autowired
	public void findGroupList(WeekRepository repo) {
		this.repo = repo;
	}
	
	@Scheduled(cron = "45 * * * * ?")
	   public void cronJobSch() {
	      
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 2);  
		repo.deleteAll();
		for (int i = 1; i < 8; i++) {
			System.err.println(sdf.format(cal.getTime()));
			WeekEntity date = new WeekEntity();
			date.setDate(sdf.format(cal.getTime()));
			repo.save(date);
			cal.add(Calendar.DATE, 1);
		}

	      
	   }
}
