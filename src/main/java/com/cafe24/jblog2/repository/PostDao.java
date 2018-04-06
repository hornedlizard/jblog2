package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}
	
	public List<PostVo> listMain(long blogNo) {
		return sqlSession.selectList("post.selectMainList", blogNo); 
	}
	
	public int countData(long categoryNo) {
		return sqlSession.selectOne("post.countData", categoryNo);
	}
}
