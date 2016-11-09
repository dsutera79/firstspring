package org.firstspring.services.ds;

import java.util.List;
import org.firstspring.model.entity.Activity;



public interface IActivitiesService {
	
	public Activity getActivityById(int idActivity);
	
	public List<Activity> showAllActivities();
	
	public void addActivity(Activity activity);
	
	public void delActivity(int idActivity);
	
	public void saveActivity(Activity activity);
}
