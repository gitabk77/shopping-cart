package com.abhishek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhishek.model.Category;
import com.abhishek.model.Product;
import com.abhishek.repo.CategoryRepo;
import com.abhishek.repo.ProductRepo;

@Controller
@RequestMapping("/category")
public class CategoriesController {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	/* @RequestMapping(value = {"/{slug}", "/{page}"}) */
	//@GetMapping(path = { "/{slug}", "/{page}" }, produces = "application/json")
	//@RequestMapping(value={"/{slug}", "/{page}"})
	//@RequestMapping(value={"/{slug}", "/{page}"}, method = RequestMethod.GET)
	@GetMapping("/{slug}/{page}")
	public String category(@PathVariable("slug") String slug, @PathVariable("page") Integer p, Model model) {
		
		int perPage = 3;
		int page = (p != null) ? p : 0;
		Pageable pageable = PageRequest.of(page, perPage);
		
		long count=0;
		
		if(slug.equals("all")) {
			Page<Product> products = productRepo.findAll(pageable);
			
			count = productRepo.count();
			
			model.addAttribute("products", products);
			
		}else {
			Category category = categoryRepo.findBySlug(slug);
			
			if(category == null) {
				return "redirect:/";
			}
			
			int categoryId = category.getId();
			String categoryName = category.getName();
			List<Product> products = productRepo.findAllByCategoryId(Integer.toString(categoryId), pageable);
			
			count = productRepo.countByCategoryId(Integer.toString(categoryId));
			
			model.addAttribute("products", products);
			model.addAttribute("categoryName", categoryName);
		}
		
		count = productRepo.count();
		double pageCount = Math.ceil((double)count / (double)perPage);
		/*
		 * int totalPages = products.getTotalPages(); model.addAttribute("totalPages",
		 * totalPages);
		 */
		model.addAttribute("pageCount", (int)pageCount);
        model.addAttribute("perPage", perPage);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("slug", slug);
        
        
        System.out.println("products : "+productRepo.findAll(pageable));
        System.out.println("pageCount : "+pageCount);
        System.out.println("perPage : "+perPage);
        System.out.println("count : "+count);
        System.out.println("page : "+page);
        System.out.println("slug : "+slug);
		
		return "products";
	}
}
