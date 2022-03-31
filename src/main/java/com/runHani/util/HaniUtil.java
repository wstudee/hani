package com.runHani.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.runHani.entity.NoticeEntity;

public class HaniUtil {

	
	
	public static  HashMap<String, Integer> calculatePaging (Page resultList) { 
		
		int currentPage = resultList.getNumber()+1;
		int lastPage = resultList.getTotalPages();
		int prePage = currentPage == 1 ? 1 : currentPage -1;
		int nextPage =  currentPage ==lastPage ? currentPage : currentPage + 1;
		int listStart = (currentPage - 2) < 1 ? 1: currentPage - 2;
		int listEnd = currentPage + 2  > lastPage ? lastPage : currentPage + 2;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();		

		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("listStart", listStart);
		map.put("lastEnd", listEnd);
		
		return map;
	}
	
}
