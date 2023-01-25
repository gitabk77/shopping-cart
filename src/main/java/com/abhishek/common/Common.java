package com.abhishek.common;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.abhishek.model.Cart;
import com.abhishek.model.Category;
import com.abhishek.model.Page;
import com.abhishek.repo.CategoryRepo;
import com.abhishek.repo.PageRepo;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class Common {

	@Autowired
	private PageRepo pageRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@ModelAttribute
	public void sharedData(Model model, HttpSession session) {
		
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		
		List<Category> categories = categoryRepo.findAll();
		
		boolean cartActive = false;
		
		if(session.getAttribute("cart") != null) {
			
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			
			int size = 0;
			double total = 0;
			
			for(Cart value : cart.values()) {
				size += value.getQuantity();
				total += value.getQuantity() * Double.parseDouble(value.getPrice());
			}
			
			model.addAttribute("csize", size);
			model.addAttribute("ctotal", total);
			
			cartActive = true;
		}
		
		model.addAttribute("cpages", pages);
		model.addAttribute("ccategories", categories);
		model.addAttribute("cartActive", cartActive);
	}
}
