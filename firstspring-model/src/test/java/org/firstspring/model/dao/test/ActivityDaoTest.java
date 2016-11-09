package org.firstspring.model.dao.test;

import java.util.List;

import org.firstspring.model.config.test.TestModelConfig;
import org.firstspring.model.dao.IActivityDao;
import org.firstspring.model.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit Test of DAO Service for entity Role
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestModelConfig.class)
@ActiveProfiles("test")



public class ActivityDaoTest {

	@Autowired
	private IActivityDao activityDao;
	
	@Test
	public void testGetActivities() {
        List<Activity> activities = activityDao.getAllActivities();
        Assert.assertNotNull(activities);

        if(!activities.isEmpty()){
	       
        	Activity primo_elemento_lista = activities.get(0);
        	Activity primo_elemento_tabella = activityDao.getActivityById(1);
        	
        	System.out.println("Primo elemento della lista recuperato con getAllActivities: " + primo_elemento_lista.getActivity() );
        	System.out.println("Primo elemento recuparato dalla tabella tramite getActivityById: " + primo_elemento_tabella.getActivity());
        	Assert.assertEquals(primo_elemento_lista.getActivity(), primo_elemento_tabella.getActivity());
        	
        	/**
        	Activity activity = activityDao.getActivityById(activities.get(0).getIdActivity());
	        Assert.assertNotNull(activity);
	        Assert.assertEquals(activities.get(0).getIdActivity(), activity.getIdActivity());
	        */
        }
    }
}
