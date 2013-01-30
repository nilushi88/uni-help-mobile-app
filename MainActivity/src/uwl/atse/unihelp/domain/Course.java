package uwl.atse.unihelp.domain;

public class Course {

	private int courseId;
	private String courseName;
	private String Location;

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		Location = location;
	}

}
