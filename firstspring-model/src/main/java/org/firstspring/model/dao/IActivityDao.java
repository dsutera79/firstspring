package org.firstspring.model.dao;

import java.util.List;

import org.firstspring.model.entity.Activity;

/**
 * Interface DAO Service for entity Activity
 * @author Giuseppe Carrassa
 *
 */

public interface IActivityDao {
	
	/**
	 * Method to find an Activity by its ID
	 * @param idActivity int
	 * @return activity
	 */
	public Activity getActivityById(int idActivity);
	
	public List<Activity> getActivitiesByState(String state);
	/**
	 * Method to get a list of all Roles in db
	 * @return List<Activity>
	 */
	public List<Activity> getAllActivities();
	
	public void addActivity(Activity activity);
	
	public void saveActivity(Activity activity);
	
	public void delActivity(int idActivity);
}
