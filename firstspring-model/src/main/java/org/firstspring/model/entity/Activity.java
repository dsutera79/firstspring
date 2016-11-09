package org.firstspring.model.entity;


/**
 * Entity bean User for table user
 * @author Dario
 *
 */

public class Activity {

	/**
	 * Column idActivity
	 */
	private int idActivity;
	
	/**
	 * Column nameActivity
	 */
	private String activity;
	
	private String stateActivity;
	
	/**
	 * @return the idActivity
	 */
	public int getIdActivity() {
		return idActivity;
	}

	/**
	 * @param idActivity the idActivity to set
	 */
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	
	/**
	 * @return the Activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param nameActivity the nameActivity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
	public String getStateActivity() {
		return stateActivity;
	}

	
	public void setStateActivity(String stateActivity) {
		this.stateActivity = stateActivity;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + idActivity;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (idActivity != other.idActivity)
			return false;
		return true;
	}
}
