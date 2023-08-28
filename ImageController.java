package com.org.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.org.entity.ImageEnttity;
import com.org.repositry.ImageRepositry;

@Controller
public class ImageController {
	
	@Autowired
	ImageRepositry repo;
	
	@GetMapping("home")
	public String loadImageJsp() {
		return "image";
		
	}

	
	
	@PostMapping("upload")
	public String saveImageData(@RequestParam("file") MultipartFile file) throws IOException {
		ImageEnttity image = new ImageEnttity();
		
		image.setFileName(file.getOriginalFilename());
		image.setContentType(file.getContentType());
		image.setData(file.getBytes());
		repo.save(image);
		new ImageController().loadImageJsp();
		return "image";
		
	}
	@GetMapping("view")
	public String viewAll(Model m) {
		
		
		
		
		m.addAttribute("img", repo.findAll());
		for(ImageEnttity data:repo.findAll()) {
			System.out.println(data.getFileName()+" "+data.getContentType()+" "+data.getData());
		}
		return "view";
		
	}
	
	
	
	
}
