package org.firstspring.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.firstspring.model.entity.Activity;
import org.firstspring.services.ds.IActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ActivityController {
	
	private final static Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	private IActivitiesService activityService;

	
	//Visualizza Pagina per inserimento nuovo record
	@RequestMapping(value = "/activities/new", method = RequestMethod.GET)
	public ModelAndView addActivity() {

		ModelAndView model = new ModelAndView();
		model.setViewName("InserisciAttivita");

		return model;
	}
	
	
	//Insert nuovo Record
	@RequestMapping(value = "/activities", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Activity activity = new Activity();
		activity.setActivity(request.getParameter("nameActivity"));
		activity.setStateActivity(request.getParameter("stateActivity"));
		activityService.addActivity(activity);
		return new ModelAndView("redirect:/activities");
	}
	
	
	//Visualizza Lista Record
	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public ModelAndView showActivities() {
		
		List<Activity> listActivities  = activityService.showAllActivities();
		
		ModelAndView model = new ModelAndView();
		model.addObject("listActivities",listActivities);
		model.setViewName("VisualizzaAttivita");

		return model;
	}
	
	
	//Visualizza singolo Record
	@RequestMapping(value = "/activities/{id}", method = RequestMethod.GET)
	public ModelAndView show(){
		return null;
	}
	
	
	//Visualizza Pagina per modifica Record
	@RequestMapping(value = "/activities/{id}/edit", method = RequestMethod.GET)
	public ModelAndView modActivity(@PathVariable("id") int id) {
		Activity activity = activityService.getActivityById(id);
		return new ModelAndView("InserisciAttivita","activity", activity);
	}
	
	
	//Update Record
	@RequestMapping(value = "/activities/{id}", method = RequestMethod.PUT)
	public ModelAndView savedActivity(@PathVariable("id") Integer id, @RequestParam String nameActivity,@RequestParam String stateActivity) throws Exception {
		System.out.println("modifica");
		Activity activity = null;
		activity.setIdActivity(id);
		activity.setActivity(nameActivity);
		activity.setStateActivity(stateActivity);
		activityService.saveActivity(activity);
		return new ModelAndView("redirect:/activities");
	}


	
	
	
	//Cancella Record
	@RequestMapping(value = "/activities/{id}", method = RequestMethod.DELETE)
	public String delActivities(@PathVariable("id") int id) {
		activityService.delActivity(id);
		Activity activity = activityService.getActivityById(id);
		if (activity !=null){
			return "OK";
		} else{
			return "KO";
		}
	}



}
