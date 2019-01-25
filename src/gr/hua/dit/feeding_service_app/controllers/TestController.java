package gr.hua.dit.feeding_service_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.utilites.Utilities;

@Controller
public class TestController {

// Why is this here though?	
//<<<<<<< HEAD:src/gr/hua/dit/feeding_service_app/controller/TestController.java
//	@RequestMapping("/")
//	public String showHome() { 
//		return "home";
//	}
//
//=======
//>>>>>>> 189e3e0a5d168a2c5fc2806d621061110db5f53d:src/gr/hua/dit/feeding_service_app/controllers/TestController.java
	@RequestMapping("/testscore")
	public String testScore(Model model) {
		Application appl = new Application(new Student(), 0, 1, "Other", false, false);
		int score = Utilities.scoreApplication(appl);
		
		// this is stupid and just used to test if score field is updated
		if (score != -1)
			model.addAttribute("score", appl.getScore());
		else
			model.addAttribute("score", score);
		return "test-score";
	}

}
