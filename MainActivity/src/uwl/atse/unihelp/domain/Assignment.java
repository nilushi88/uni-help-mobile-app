package uwl.atse.unihelp.domain;

public class Assignment {

	private int assignmentID;
	private String title;
	private String description;
	private String dueDate;
	private String timeDue;
	private int courseID;

	public Assignment() {

	}

	/**
	 * @return the assignmentID
	 */
	public int getAssignmentID() {
		return assignmentID;
	}

	/**
	 * @param assignmentID
	 *            the assignmentID to set
	 */
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID
	 *            the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the timeDue
	 */
	public String getTimeDue() {
		return timeDue;
	}

	/**
	 * @param timeDue
	 *            the timeDue to set
	 */
	public void setTimeDue(String timeDue) {
		this.timeDue = timeDue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Assignment [assignmentID=" + assignmentID + ", title=" + title
				+ ", description=" + description + ", dueDate=" + dueDate
				+ ", timeDue=" + timeDue + ", courseID=" + courseID + "]";
	}

}
