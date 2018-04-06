package com.cafe24.jblog2.controller.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.dto.JSONResult;
import com.cafe24.security.Auth;

@Controller("BlogAPIController")
@RequestMapping("/{id}/api/blog")
public class BlogController {
	@Auth
	@ResponseBody
	@RequestMapping(value="/img", method=RequestMethod.POST)
	public JSONResult changeImg(@RequestParam("logo-file") MultipartFile multipartFile) {
		byte[] logoImg = null;
		try {
			logoImg = multipartFile.getBytes();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONResult.success(logoImg);
	}
}
