package pl.edu.agh.dsm.front.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.agh.dsm.front.web.view.dto.GraphInput;

import java.util.List;

@Controller
public class GraphController {
	
	@Value("${graph.refresh.interval}")
	int refreshInterval;
	
	@RequestMapping("/graphs")
	@ModelAttribute("graphInput")
	public GraphInput  measurementsPage(
			@ModelAttribute(value="graphInput") GraphInput graphInput,
			Model model) {
		model.addAttribute("refreshInterval", refreshInterval);
		return graphInput;
	}
}
