package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	SqlSession sqlSession;
	
	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> listCategory(String id) {
		return sqlSession.selectList("category.getById", id);
	}
	
	public CategoryVo getLast() {
		return sqlSession.selectOne("category.getLast");
	}
	
	public int delete(CategoryVo vo) {
		return sqlSession.delete("category.delete", vo);
	}
}
