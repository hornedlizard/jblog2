package com.cafe24.jblog2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog2.service.BlogService;
import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.service.FileUploadService;
import com.cafe24.jblog2.service.PostService;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.PostVo;
import com.cafe24.jblog2.vo.PageVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService service;
	@Autowired
	private FileUploadService fileService;
	@Autowired
	private CategoryService cService;
	@Autowired
	private PostService pService;
	
	
	@RequestMapping(value="")
	public String blogMain(@PathVariable String id, Model model) {
		PageVo page = new PageVo();
		page.setPage(1);
		int totalData = pService.countData();
		page.setTotalData(totalData);
		page.paging();
		
		List<CategoryVo> category = cService.listCategory(id);
		BlogVo blog = service.blogMain(id);
		long blogNo = service.blogMain(id).getNo();
		List<PostVo> list = pService.MainPost(blogNo);
		
		model.addAttribute("category", category);
		model.addAttribute("page", page);
		model.addAttribute("blog", blog);
		model.addAttribute("posts", list);
		return "/blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String blogAdminBasic(@PathVariable String id, Model model) {
		BlogVo blog = service.blogMain(id);
		model.addAttribute("blog", blog);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String manageBlog(
			@PathVariable String id, 
			@ModelAttribute BlogVo vo, 
			@RequestParam("logo-file") MultipartFile multipartFile) {
		
		String logoUrl = fileService.restore(multipartFile);
		long no = service.blogMain(id).getNo();
		vo.setNo(no);
		vo.setLogo(logoUrl);
		service.blogModify(vo);
		return "redirect:/{id}";
	}
	
	@RequestMapping(value="/admin/category")
	public String category(@PathVariable String id, Model model) {
		BlogVo blog = service.blogMain(id);
		model.addAttribute("blog", blog);
		model.addAttribute("list", cService.listCategory(id));
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String blogWrite(@PathVariable String id, Model model) {
		BlogVo blog = service.blogMain(id);
		model.addAttribute("blog", blog);
		model.addAttribute("list", cService.listCategory(id));
		
		return "blog/blog-admin-write";
	}

	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String blogWrite(@PathVariable String id, PostVo vo) {
		pService.write(vo);
		return "redirect:/"+id;
	}

}
