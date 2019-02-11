package gr.hua.dit.feeding_service_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping
	public String showHome() {
		return "home";
	}

}
