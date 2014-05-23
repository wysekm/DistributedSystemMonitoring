package pl.edu.agh.dsm.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	String index() {
		return "main";
	}

	@RequestMapping("favicon.ico")
	@ResponseBody
	void favicon() {
	}

	@RequestMapping("/main")
	public void mainPage() {
	}

	@RequestMapping("/login")
	public void loginPage(
			@RequestParam(value = "loginError", required = false) String error,
			Model model) {
		model.addAttribute("loginError", error);
	}

	@RequestMapping("/logoutSuccess")
	public void logoutPage() {
	}

	@RequestMapping("/search")
	public void searchPage() {
	}
}
