package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao dao;
	
	public void add(CategoryVo vo) {
		dao.insert(vo);
	}
	
	public List<CategoryVo> listCategory(String id) {
		return dao.listCategory(id);
	}
	
	public CategoryVo getLast() {
		return dao.getLast();
	}
	
	public int remove(CategoryVo vo) {
		return dao.delete(vo);
	}
}
