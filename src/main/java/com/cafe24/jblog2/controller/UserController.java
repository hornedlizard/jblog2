package com.cafe24.jblog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private BlogService bService;
	@Autowired
	private CategoryService cService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		uService.join(vo);
		long userNo = uService.checkId(vo.getId()).getNo();
		bService.createBlog(userNo);
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogNo(userNo);
		categoryVo.setName("미분류");
		cService.add(categoryVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo vo) {		
		UserVo authUser = uService.login(vo.getId(), vo.getPassword());
		if (authUser == null) {
			return "/user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
}
