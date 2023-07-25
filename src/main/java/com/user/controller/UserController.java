package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model model) {
		List<User> list = this.userRepository.findAll();
		model.addAttribute("userList", list);
		model.addAttribute("title", "Homepage : User Management");
		return "/home";
	}

	@GetMapping("/user_form")
	public String userForm() {
		return "/form";
	}

	@PostMapping("/add_user")
	public String addUser(@ModelAttribute("user") User user) {
		this.userRepository.save(user);
		return "redirect:/";
	}
	@GetMapping("/get_user/{id}")
public String getUser(@PathVariable int id,Model model) {
		model.addAttribute("user", this.userRepository.findById(id).get());
		return "update_form";
	}
	@GetMapping("/delete_user/{id}")
	public String deleteUser(@PathVariable int id) {
		this.userRepository.deleteById(id);
		return "redirect:/";
	}
}
