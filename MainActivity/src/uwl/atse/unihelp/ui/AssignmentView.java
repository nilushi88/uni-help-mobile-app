package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.domain.Assignment;
import uwl.atse.unihelp.domain.Course;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AssignmentView extends Activity {
	
	private DataSource dataSource;
	private SimpleCursorAdapter cursorAdapter;
	private long assignmentId;
	private Assignment assignment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.course_item_display);

		dataSource = new DataSource(this);
		dataSource.open();

		assignmentId = getIntent().getExtras().getLong(SQLiteHelper.ID);
		assignment = dataSource.findAssignmentById(assignmentId);

		TextView tvAssignmentTitle = (TextView) findViewById(R.id.tvAssignmentTitle);
		tvAssignmentTitle.setText(assignment.getTitle());

		TextView tvDescription = (TextView) findViewById(R.id.tvAssignmentDescription);
		tvDescription.setText(assignment.getDescription());
		
		TextView tvDueDate = (TextView) findViewById(R.id.tvDueDate);
		tvDueDate.setText(assignment.getDueDate());
		
		TextView tvTimeDue = (TextView) findViewById(R.id.tvTimeDue);
		tvTimeDue.setText(assignment.getTimeDue());

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.assignment_menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.deleteAssignment){
			dataSource.deleteAssignment(assignmentId);
			Toast toast = Toast.makeText(getApplicationContext(), "Assignment Deleted", Toast.LENGTH_LONG);
			toast.show();
			startActivity(new Intent(AssignmentView.this,
					DisplayCourses.class));			
		}
		return super.onOptionsItemSelected(item);
	}

}
