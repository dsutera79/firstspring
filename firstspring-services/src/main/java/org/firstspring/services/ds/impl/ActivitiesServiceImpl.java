package org.firstspring.services.ds.impl;

import java.util.List;

import org.firstspring.model.dao.IActivityDao;
import org.firstspring.model.entity.Activity;
import org.firstspring.services.ds.IActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesServiceImpl implements IActivitiesService {

	@Autowired
	private IActivityDao activityDao;
	
	
	

	public Activity getActivityById(int idActivity){
		return activityDao.getActivityById(idActivity);
		
	}
	
	
	
	@Override
	public List<Activity> showAllActivities() {
	
		List<Activity> activities = activityDao.getAllActivities();
		return activities;
	}


	
	@Override
	public void addActivity(Activity activity){
		activityDao.addActivity(activity);
		System.out.println(activity.getActivity());
	}
	
	
	
	public void delActivity(int idActivity){
		activityDao.delActivity(idActivity);
	}
	
	
	
	public void saveActivity(Activity activity){
		activityDao.saveActivity(activity);
	}
}
