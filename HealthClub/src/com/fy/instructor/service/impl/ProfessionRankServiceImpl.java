package com.fy.instructor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.ProfessionRankDao;
import com.fy.instructor.entity.ProfessionRank;
import com.fy.instructor.service.ProfessionRankService;

@Service
@Transactional
public class ProfessionRankServiceImpl implements ProfessionRankService {

	@Autowired
	private ProfessionRankDao professionRankDao;

	 
		/**
		 * 分页获取
		 */
		public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
			 Map<String, Object> map = new HashMap<String, Object>();
			 map = professionRankDao.queryPageInfo(limit, offset,searchType,searchText);		
			 return map;
		 }
		 
		public  void deleteById(int id){
			professionRankDao.deleteById(id);;
		}
			
		public void deleteByList( List<Integer> idList) {
			professionRankDao.deleteByList(idList);
		}
			

		public  ProfessionRank queryUserById(int id){
			return professionRankDao.queryById(id);
		}
			
		public  void update(ProfessionRank professionRank){

			professionRankDao.update(professionRank);
		}
			
		public  void save(ProfessionRank professionRank){
			professionRankDao.save(professionRank);
		}


}
