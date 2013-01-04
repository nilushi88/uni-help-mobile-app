package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.domain.Course;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CourseView extends Activity {
	
	private DataSource dataSource;
	private SimpleCursorAdapter cursorAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.course_view);
		
		dataSource = new DataSource(this);
		dataSource.open();
		
		final String courseId = getIntent().getExtras().getString(SQLiteHelper.COURSE_ID);
		Course course = dataSource.findCourseById(courseId);
		
		TextView tvCourseName = (TextView) findViewById(R.id.tvCourseName);
		tvCourseName.setText(course.getCourseName());
		
		TextView tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvLocation.setText(course.getLocation());
		
		
		displayAssignments(courseId);
		
//		Button addAssignmentbtn = (Button) findViewById(R.id.btnAddAssignment);
		ImageButton addAssignmentbtn = (ImageButton) findViewById(R.id.imageButtonAddAssignment);
		addAssignmentbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				   Intent intent = new Intent(CourseView.this, AddCourseItem.class);
				   intent.putExtra(SQLiteHelper.COURSE_ID, courseId);
				   startActivity(intent);
				   
			}
		});
		

	}

	private void displayAssignments(String courseId) {
		 String[] columns = new String[] {
				 
				   SQLiteHelper.TITLE,
				   SQLiteHelper.DESCRIPTION
				  };
		 
		 int[] to = new int[] {
				    R.id.tvAssignmentTitle,
				    R.id.tvDescription
				  };
		 
		 cursorAdapter = new SimpleCursorAdapter(
				    this, R.layout.course_view_list_entry,
				    dataSource.getAllAssignmentsOfCourse(courseId), columns, to, 0);
		 
		 ListView listView = (ListView) findViewById(R.id.listViewAssignments);
		 
		  listView.setAdapter(cursorAdapter);
		  
	}
}
