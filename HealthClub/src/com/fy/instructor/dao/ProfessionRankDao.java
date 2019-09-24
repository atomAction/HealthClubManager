package com.fy.instructor.dao;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.ProfessionRank;

public interface ProfessionRankDao {
	
	
		public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);
		public ProfessionRank queryById(int id) ;
		public void deleteById(int id) ;
		public void deleteByList(List<Integer> idList) ;
	
		public void update(ProfessionRank professionRank) ;
		public void save(ProfessionRank professionRank) ;

		public List<ProfessionRank> queryAllProfessionRank() ;

}
