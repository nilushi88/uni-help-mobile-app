/**
 * 
 */
package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.R.id;
import uwl.atse.unihelp.R.layout;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;

import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

/**
 * @author Nilu
 *
 */
public class AddCourseItem extends Activity implements OnClickListener{
	
	private DataSource dataSource;
	private String courseId;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_course_item);
		
		courseId = getIntent().getExtras().getString(SQLiteHelper.COURSE_ID);

		
		
		dataSource = new DataSource(this);
		dataSource.open();
		
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);
		
		Button btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		if(v.getId()== R.id.btnAdd){
			
			EditText etTitle = (EditText) findViewById(R.id.etTitle);
			String title = etTitle.getText().toString();
			
			EditText etDescription = (EditText) findViewById(R.id.etDescrip);
			String description = etDescription.getText().toString();
			
			
			dataSource.createAssignment(title, description, courseId);
			
			
			
			
		} else if (v.getId() == R.id.btnCancel){
			
		}
		
	}


}
