package org.firstspring.web.controller;


import java.util.List;

import org.firstspring.model.entity.Activity;
import org.firstspring.services.ds.IActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@Autowired
	private IActivitiesService activityService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView helloWorld() {

		List<Activity> listActivities  = activityService.showAllActivities();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listActivities",listActivities);
		model.setViewName("VisualizzaAttivita");
		return model;
	}
	
}