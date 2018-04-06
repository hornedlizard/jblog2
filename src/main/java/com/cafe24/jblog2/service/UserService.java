package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.UserDao;
import com.cafe24.jblog2.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public void join(UserVo vo) {
		dao.insert(vo);
	}
	
	public UserVo login(String id, String password) {
		return dao.get(id, password);
	}
	
	public UserVo checkId(String id) {
		return dao.get(id);
	}
}
