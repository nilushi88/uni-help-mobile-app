package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.R.id;
import uwl.atse.unihelp.R.layout;
import uwl.atse.unihelp.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Button displayCoursebtn = (Button) findViewById(R.id.btnCourses);
		displayCoursebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(MainActivity.this, DisplayCourses.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
