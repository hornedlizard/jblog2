package com.cafe24.jblog2.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(long userNo) {
		sqlSession.insert("blog.insert", userNo);
	}
	
	public BlogVo getById(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return sqlSession.selectOne("blog.getById", map);
	}
	
	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
	}
}
