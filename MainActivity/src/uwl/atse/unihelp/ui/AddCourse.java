package uwl.atse.unihelp.ui;


import uwl.atse.unihelp.R;
import uwl.atse.unihelp.R.id;
import uwl.atse.unihelp.R.layout;
import uwl.atse.unihelp.persistence.DataSource;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse extends Activity implements OnClickListener {
	
	private DataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_course);
				
		dataSource = new DataSource(this);
		dataSource.open();
		
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		Button btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.btnAdd) {

			EditText etName = (EditText) findViewById(R.id.etName);
			String courseName = etName.getText().toString();
			
			EditText etLocation = (EditText) findViewById(R.id.etLocation);
			String location = etLocation.getText().toString();
	
			dataSource.createCourse(courseName, location);

		} else if (v.getId() == R.id.btnCancel) {

		}

	}

}
