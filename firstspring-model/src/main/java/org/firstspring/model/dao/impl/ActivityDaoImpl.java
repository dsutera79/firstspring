package org.firstspring.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.firstspring.model.dao.IActivityDao;
import org.firstspring.model.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of DAO Service for entity Activity
 * 
 * @author Giuseppe Carrassa
 *
 */

@Repository
public class ActivityDaoImpl implements IActivityDao {

	/**
	 * LOGGER
	 */
	private Logger LOGGER = Logger.getLogger(ActivityDaoImpl.class);

	/**
	 * DataSource injected by Spring
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Utility method to map a ResultSet in an User bean
	 * @param rs ResultSet
	 * @return User
	 * @throws SQLException exception
	 */
	private Activity mapping(ResultSet rs) throws SQLException {
		Activity activity = new Activity();
		activity.setIdActivity(rs.getInt("idActivity"));
		activity.setActivity(rs.getString("nameActivity"));
		switch (rs.getString("stateActivity")){
			case "A":
				activity.setStateActivity("Active");
				break;
			case "C":
				activity.setStateActivity("Closed");
				break;
			case "R":
				activity.setStateActivity("Referred");
				break;
		}
		return activity;
	}
	
	
	
	private String mapState(String state){
		switch (state){
			case "Active":
				state = "A";
				break;
			case "Referred":
				state = "R";
				break;
			case "Closed":
				state = "C";
				break;
		}
		return state;
	}
	
	
	
	@Override
	public Activity getActivityById(int idActivity) {
		String sql = "SELECT * FROM Activity WHERE idActivity = ?";
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idActivity);
			Activity activity = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				activity = mapping(rs);
			}
			rs.close();
			ps.close();
			return activity;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	
	@Override
	public List<Activity> getActivitiesByState(String state){
		List<Activity> activities = new ArrayList<Activity>();
		String sql = "SELECT * FROM Activity WHERE state = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Activity activity = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				activity = mapping(rs);
				activities.add(activity);
			}
			rs.close();
			ps.close();
			return activities;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	
	@Override
	public List<Activity> getAllActivities() {
		
		List<Activity> activities = new ArrayList<Activity>();
		String sql = "SELECT * FROM Activity";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Activity activity = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				activity = mapping(rs);
				activities.add(activity);
			}
			rs.close();
			ps.close();
			return activities;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	
	
	@Override
	public void addActivity(Activity activity) {
		
		if (activity != null){
			String sql = "INSERT INTO  Activity (nameActivity, stateactivity) VALUES (?, ?)";
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,activity.getActivity());
				ps.setString(2,mapState(activity.getStateActivity()));
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						LOGGER.error(e);
					}
				}
			}
		}
	}
	
	
	@Override
	public void saveActivity(Activity activity){
		
		if(activity != null){
			String sql = "UPDATE Activity SET nameActivity = ?, stateActivity = ? WHERE idActivity = ?";
			
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,activity.getActivity());
				ps.setString(2,mapState(activity.getStateActivity()));
				ps.setInt(3,activity.getIdActivity());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						LOGGER.error(e);
					}
				}
			}
		}

	}

	
	@Override
	public void delActivity(int idActivity){
		String sql = "DELETE FROM Activity WHERE idActivity = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idActivity);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	
	
}
