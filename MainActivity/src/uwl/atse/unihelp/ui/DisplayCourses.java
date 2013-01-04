package uwl.atse.unihelp.ui;


import uwl.atse.unihelp.R;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DisplayCourses extends Activity {
	private DataSource dataSource;
	private SimpleCursorAdapter cursorAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_course_list);
		
		dataSource = new DataSource(this);
		dataSource.open();
		
		displayCourses();
		
		ImageButton addCoursebtn = (ImageButton) findViewById(R.id.imageButtonAddCourse);
		addCoursebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(DisplayCourses.this, AddCourse.class));
			}
		});
		
	}
	
	 private void displayCourses(){
		 String[] columns = new String[] {
				 
				   SQLiteHelper.NAME,
				   SQLiteHelper.LOCATION
				  };
		 
		 int[] to = new int[] {
				    R.id.tvCourse,
				    R.id.tvLocation
				  };
		 
		 cursorAdapter = new SimpleCursorAdapter(
				    this, R.layout.display_course_list_entry,
				    dataSource.getAllCourses(), columns, to, 0);
		 
		 ListView listView = (ListView) findViewById(R.id.listViewCourses);
		 
		  listView.setAdapter(cursorAdapter);
		  listView.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> listView, View view,
			     int position, long id) {
			   // Get the cursor, positioned to the corresponding row in the result set
			   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
			 
			   // Get the state's capital from this row in the database.
			   String courseId =  cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.ID));
//			   Toast.makeText(getApplicationContext(),
//			     countryCode, Toast.LENGTH_SHORT).show();
//			 
			   Intent intent = new Intent(DisplayCourses.this, CourseView.class);
			   intent.putExtra(SQLiteHelper.COURSE_ID, courseId);
			   startActivity(intent);
			   
			   }
			  });
	 }
	

}
