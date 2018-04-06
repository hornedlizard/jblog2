package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.PostDao;
import com.cafe24.jblog2.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao dao;
	
	public void write(PostVo vo) {
		dao.insert(vo);
	}
	
	public List<PostVo> MainPost(long blogNo) {
		return dao.listMain(blogNo);
	}
	
	public int countData() {
		return dao.countData(-1l);
	}
}
