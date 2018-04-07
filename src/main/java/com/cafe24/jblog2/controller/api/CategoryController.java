package com.cafe24.jblog2.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog2.dto.JSONResult;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.security.Auth;

@Controller("categoryAPIController")
@RequestMapping("/{id}/api/category")
public class CategoryController {
	@Autowired
	private CategoryService service;
	@Autowired
	private UserService uService;
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public JSONResult addCategory(CategoryVo vo, @PathVariable String id) {
		vo.setBlogNo(uService.checkId(id).getNo());
		service.add(vo);
		/*List<CategoryVo> list = service.listCategory(id);
		return JSONResult.success(list == null ? "not exist" : list);*/
		CategoryVo category = service.getLast();
		return JSONResult.success(category == null ? "not exist" : category);
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public JSONResult removeCategory(CategoryVo vo, @PathVariable String id) {
		vo.setBlogNo(uService.checkId(id).getNo());
		System.out.println(vo.getNo());
		int result = service.remove(vo);
		if (result == 0) {
			return JSONResult.success("fail");
		}
		return JSONResult.success("success");
	}
	
}