package com.url.shortener.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class UrlShortenerController {
	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("home")
	public ModelAndView home(Map<String, Object> model) {
		model.put("message", this.message);
		return  new ModelAndView("home");
	}

	@RequestMapping("addUrl")
	public ModelAndView contact(Map<String, Object> model) {
		model.put("message", this.message);
		return  new ModelAndView("addUrl");
	}
	@RequestMapping("callUrl")
	public ModelAndView callUrl(Map<String, Object> model) {
		model.put("message", this.message);
		return  new ModelAndView("callUrl");
	}
	@RequestMapping("viewUrls")
	public ModelAndView viewUrls(Map<String, Object> model) {
		model.put("message", this.message);
		return  new ModelAndView("viewUrls");
	}

	@RequestMapping("dashboard")
	public ModelAndView dashboard(Map<String, Object> model) {
		return  new ModelAndView("dashboard");
	}
	
	@RequestMapping("graph")
	public ModelAndView graph(Map<String, Object> model) {
		return  new ModelAndView("graph");
	}

}
