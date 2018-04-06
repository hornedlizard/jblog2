package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao dao;
	
	public void createBlog(long userNo) {
		dao.insert(userNo);
	}
	
	public BlogVo blogMain(String id) {
		return dao.getById(id);
	}
	
	public void blogModify(BlogVo vo) {
		dao.update(vo);
	}
}
