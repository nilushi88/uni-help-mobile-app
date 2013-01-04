package uwl.atse.unihelp.persistence;

import uwl.atse.unihelp.domain.Course;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

	private SQLiteDatabase database;
	private SQLiteHelper helper;
	private String[] allCourseColumns = { SQLiteHelper.ID, SQLiteHelper.NAME,
			SQLiteHelper.LOCATION };
	private String[] allAssignmentColumns = { SQLiteHelper.ID,
			SQLiteHelper.TITLE, SQLiteHelper.DESCRIPTION,
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

	public void createAssignment(String title, String description, String courseId) {

		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.TITLE, title);
		values.put(SQLiteHelper.DESCRIPTION, description);
		values.put(SQLiteHelper.COURSE_ID, Integer.parseInt(courseId));

		database.insert(SQLiteHelper.TABLE_ASSIGNMENT, null, values);
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
	
	public Cursor getAllAssignmentsOfCourse(String courseId) {
		Cursor cursor = database.query(SQLiteHelper.TABLE_ASSIGNMENT,
		        allAssignmentColumns, SQLiteHelper.COURSE_ID +" = " + courseId, null, null, null, null);
	 return cursor;
	}

	private Course cursorToCourse(Cursor cursor) {
		Course course = new Course();
		course.setCourseId(cursor.getInt(0));
		course.setCourseName(cursor.getString(1));
		course.setLocation(cursor.getString(2));

		return course;
	}

}
