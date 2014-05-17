package pl.edu.agh.dsm.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/search")
	public void searchPage() {
	}
}
