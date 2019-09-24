package com.fy.instructor.service;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.ProfessionRank;

public interface ProfessionRankService {
	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText);
	
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList); 
		

	public  ProfessionRank queryUserById(int id);
		
	public  void update(ProfessionRank professionRank);
		
	public  void save(ProfessionRank professionRank);
}
