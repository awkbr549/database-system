package com.dbms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @RequestMapping(value = "/") //whether GET or POST, only do one thing
    public String index(@RequestParam(value = "name",
				      required = false,
				      defaultValue = "user")
			String name,
			Model model) {
	model.addAttribute("name", name);
	return "index";
    } //index
    
} //MainController
