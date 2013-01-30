package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.persistence.DataSource;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourse extends Activity {

	private DataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_course);

		dataSource = new DataSource(this);
		dataSource.open();

	}

	public void onCancel(View view) {
		finish();

	}

	public void onAddCourse(View view) {
		EditText etName = (EditText) findViewById(R.id.etName);
		String courseName = etName.getText().toString();

		EditText etLocation = (EditText) findViewById(R.id.etLocation);
		String location = etLocation.getText().toString();

		dataSource.createCourse(courseName, location);
		startActivity(new Intent(AddCourse.this, DisplayCourses.class));
		
		Toast toast = Toast.makeText(getApplicationContext(), "New Course Added", Toast.LENGTH_LONG);
		toast.show();

	}

}
