package com.cafe24.jblog2.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}
	
	public UserVo get(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);

		return sqlSession.selectOne("user.getByEmailPassword", map);
	}
	
	public UserVo get(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		return sqlSession.selectOne("user.getById", map);
	}
}
