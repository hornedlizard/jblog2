package com.cafe24.jblog2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog2.dto.JSONResult;
import com.cafe24.jblog2.service.UserService;
import com.cafe24.jblog2.vo.UserVo;

@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@ResponseBody
	@RequestMapping(value="/checkid")
	public JSONResult checkId(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo vo = service.checkId(id);
		return JSONResult.success(vo == null ? "not exist" : "exist");
	}
}
