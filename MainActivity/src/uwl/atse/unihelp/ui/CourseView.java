package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.domain.Course;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CourseView extends Activity {

	private DataSource dataSource;
	private SimpleCursorAdapter cursorAdapter;
	private String courseId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.course_view);

		dataSource = new DataSource(this);
		dataSource.open();

		courseId = getIntent().getExtras().getString(SQLiteHelper.COURSE_ID);
		Course course = dataSource.findCourseById(courseId);

		TextView tvCourseName = (TextView) findViewById(R.id.tvCourseName);
		tvCourseName.setText(course.getCourseName());

		TextView tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvLocation.setText(course.getLocation());

		displayAssignments(courseId);

	}

	public void onAddAssignment(View view) {
		Intent intent = new Intent(CourseView.this, AddCourseItem.class);
		intent.putExtra(SQLiteHelper.COURSE_ID, courseId);

		startActivity(intent);

	}

	private void displayAssignments(String courseId) {
		String[] columns = new String[] {

		SQLiteHelper.TITLE, SQLiteHelper.DESCRIPTION };

		int[] to = new int[] { R.id.tvAssignmentTitle, R.id.tvDescription };

		cursorAdapter = new SimpleCursorAdapter(this,
				R.layout.course_view_list_entry,
				dataSource.getAllAssignmentsOfCourse(courseId), columns, to, 0);

		ListView listView = (ListView) findViewById(R.id.listViewAssignments);

		listView.setAdapter(cursorAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);

				// Get the state's capital from this row in the database.
				long assignmentId = cursor.getLong(cursor
						.getColumnIndexOrThrow(SQLiteHelper.ID));
				
				Intent intent = new Intent(CourseView.this,
						AssignmentView.class);
				intent.putExtra(SQLiteHelper.ID, assignmentId);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.course_menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.deleteCourse){
			dataSource.deleteCourse(Long.valueOf(courseId));
			Toast toast = Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_LONG);
			toast.show();
			startActivity(new Intent(CourseView.this,
					DisplayCourses.class));
			
		}
		return super.onOptionsItemSelected(item);
	}
}
