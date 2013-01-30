package uwl.atse.unihelp.persistence;

import uwl.atse.unihelp.domain.Assignment;
import uwl.atse.unihelp.domain.Course;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataSource {

	private SQLiteDatabase database;
	private SQLiteHelper helper;
	private String[] allCourseColumns = { SQLiteHelper.ID, SQLiteHelper.NAME,
			SQLiteHelper.LOCATION };
	private String[] allAssignmentColumns = { SQLiteHelper.ID,
			SQLiteHelper.TITLE, SQLiteHelper.DESCRIPTION,
			SQLiteHelper.DUE_DATE, SQLiteHelper.TIME_DUE,
			SQLiteHelper.COURSE_ID };

	public DataSource(Context context) {
		helper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = helper.getWritableDatabase();

	}

	public void close() {
		helper.close();
	}

	public void createCourse(String courseName, String location) {

		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.NAME, courseName);
		values.put(SQLiteHelper.LOCATION, location);

		database.insert(SQLiteHelper.TABLE_COURSE, null, values);
	}

	public long createAssignment(Assignment assignment) {

		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.TITLE, assignment.getTitle());
		values.put(SQLiteHelper.DESCRIPTION, assignment.getDescription());
		values.put(SQLiteHelper.DUE_DATE, assignment.getDueDate());
		values.put(SQLiteHelper.TIME_DUE, assignment.getTimeDue());
		values.put(SQLiteHelper.COURSE_ID, assignment.getCourseID());

		long id = database.insert(SQLiteHelper.TABLE_ASSIGNMENT, null, values);
		return id;
	}

	public Cursor getAllCourses() {

		Cursor cursor = database.query(SQLiteHelper.TABLE_COURSE,
				allCourseColumns, null, null, null, null, null);
		return cursor;

	}

	public Course findCourseById(String courseId) {
		Cursor cursor = database.query(SQLiteHelper.TABLE_COURSE,
				allCourseColumns, SQLiteHelper.ID + " = " + courseId, null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToCourse(cursor);
	}

	public Assignment findAssignmentById(long id) {
		Cursor cursor = database.query(SQLiteHelper.TABLE_ASSIGNMENT,
				allAssignmentColumns, SQLiteHelper.ID + " = " + id, null, null,
				null, null);
		Log.d("NILU", "retrived cursor from database");

		cursor.moveToFirst();
		return cursorToAssignment(cursor);
	}

	public Cursor getAllAssignmentsOfCourse(String courseId) {
		Cursor cursor = database.query(SQLiteHelper.TABLE_ASSIGNMENT,
				allAssignmentColumns,
				SQLiteHelper.COURSE_ID + " = " + courseId, null, null, null,
				null);
		return cursor;
	}

	public void deleteCourse(long courseId) {
//		long id = course.getCourseId();
		System.out.println("Course deleted with id: " + courseId);
		database.delete(SQLiteHelper.TABLE_COURSE, SQLiteHelper.ID
				+ " = " + courseId, null);
	}

	public void deleteAssignment(long assignmentid) {
//		long id = assignment.getAssignmentID();
		System.out.println("Assignment deleted with id: " + assignmentid);
		database.delete(SQLiteHelper.TABLE_ASSIGNMENT, SQLiteHelper.ID
				+ " = " + assignmentid, null);
	}

	private Course cursorToCourse(Cursor cursor) {
		Course course = new Course();
		course.setCourseId(cursor.getInt(0));
		course.setCourseName(cursor.getString(1));
		course.setLocation(cursor.getString(2));

		return course;
	}

	private Assignment cursorToAssignment(Cursor cursor) {
		Log.d("NILU", "cursor to assignment begin");

		Assignment assignment = new Assignment();
		assignment.setAssignmentID(cursor.getInt(0));
		Log.d("NILU", "assignment Id set");

		assignment.setTitle(cursor.getString(1));
		Log.d("NILU", "assignment title set");

		assignment.setDescription(cursor.getString(2));
		Log.d("NILU", "assignment description set");

		assignment.setDueDate(cursor.getString(3));
		Log.d("NILU", "assignment due date set");
		if (cursor.isNull(4)) {
			Log.d("NILU", "time is null");

		}
		Log.d("NILU", " before assignment due tome set");

		assignment.setTimeDue(cursor.getString(4));
		Log.d("NILU", "assignment due tome set");

		assignment.setCourseID(cursor.getInt(5));
		Log.d("NILU", "course id set");

		return assignment;
	}

}
