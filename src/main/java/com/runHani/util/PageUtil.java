package com.runHani.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.runHani.entity.NoticeEntity;

public class PageUtil {

	
	
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

	public static  HashMap<String, Integer> calculatePagingByMap (HashMap<String,Object> pageInfo) { 
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();		
		
		Object tmpPage = pageInfo.get("pageable");
		Pageable pageable = null;
		Object totalTmp = pageInfo.get("totalCnt");
		int totalCnt = -1;
		
		
		if(tmpPage!= null &&  tmpPage instanceof Pageable && totalTmp instanceof Integer) {
			pageable = (Pageable) tmpPage;
			totalCnt = (Integer)totalTmp;
		
		int currentPage =  pageable.getPageNumber()+1;
		int pageSize = pageable.getPageSize();
		int lastPage =  totalCnt%pageSize == 0 ? (int) totalCnt/pageSize : totalCnt/pageSize +1;  
		int prePage = currentPage == 1 ? 1 : currentPage -1;
		int nextPage =  currentPage ==lastPage ? currentPage : currentPage + 1;
		int listStart = (currentPage - 2) < 1 ? 1: currentPage - 2;
		int listEnd = currentPage + 2  > lastPage ? lastPage : currentPage + 2;
		

		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("listStart", listStart);
		map.put("lastEnd", listEnd);
		}else {	
			return null;
		}
		
		return map;
	}
}
