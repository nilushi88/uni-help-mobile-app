/**
 * 
 */
package uwl.atse.unihelp.ui;

import java.text.DateFormat;
import java.util.Calendar;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.domain.Assignment;
import uwl.atse.unihelp.persistence.DataSource;
import uwl.atse.unihelp.persistence.SQLiteHelper;
import uwl.atse.unihelp.service.ScheduleClient;
import uwl.atse.unihelp.ui.util.DatePickerFragment;
import uwl.atse.unihelp.ui.util.NumberPickerFragment;
import uwl.atse.unihelp.ui.util.TimePickerFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Nilu
 * 
 */
public class AddCourseItem extends Activity {

	private DataSource dataSource;
	private String courseId;
	public static Calendar mCalendar = Calendar.getInstance();
	public static int remindDays = 0;
	public static int remindHours = 0;

	private ScheduleClient scheduleClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_course_item);

		scheduleClient = new ScheduleClient(this);
		scheduleClient.doBindService();

		courseId = getIntent().getExtras().getString(SQLiteHelper.COURSE_ID);

		dataSource = new DataSource(this);
		dataSource.open();

		setCurrentDateOnView();
		setCurrentTimeOnView();

	}

	public void setCurrentDateOnView() {

		TextView tvDueDate = (TextView) findViewById(R.id.tvDueDate);

		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tvDueDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(day).append("/").append(month + 1).append("/")
				.append(year).append(" "));

	}

	public void addAssignment(View view) {
		long id = createAssignment();

		DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
		DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.SHORT);

		Log.d("NILU", "Date: " + dateInstance.format(mCalendar.getTime())
				+ " Time: " + timeInstance.format(mCalendar.getTime()));
		Log.d("NILU", "Assignment ID ::::::::" + id);

		mCalendar.add(Calendar.DATE, remindDays * -1);
		mCalendar.add(Calendar.HOUR, remindHours * -1);
		Log.d("NILU",
				"Alarm ::::: Date: " + dateInstance.format(mCalendar.getTime())
						+ " Time: " + timeInstance.format(mCalendar.getTime()));

		scheduleClient.setAlarmForNotification(mCalendar, id);
		Intent intent = new Intent(AddCourseItem.this, CourseView.class);
		intent.putExtra(SQLiteHelper.COURSE_ID, courseId);
		startActivity(intent);
		
		Toast toast = Toast.makeText(getApplicationContext(), "New Assignment Added", Toast.LENGTH_LONG);
		toast.show();

	}

	public void onCancel(View view) {
		finish();

	}

	public void onDueDateSelection(View view) {
		TextView tvDueDate = (TextView) findViewById(R.id.tvDueDate);
		DialogFragment newFragment = new DatePickerFragment(tvDueDate);
		newFragment.show(this.getFragmentManager(), "datePicker");
	}

	public void onDueTimeSelection(View view) {
		TextView tvTimeDue = (TextView) findViewById(R.id.tvTimeDue);
		DialogFragment newFragment = new TimePickerFragment(tvTimeDue);
		newFragment.show(this.getFragmentManager(), "timePicker");
	}

	public void onReminderSelection(View view) {
		TextView tvRemindMeTime = (TextView) findViewById(R.id.tvRemindMeTime);

		DialogFragment newFragment = new NumberPickerFragment(tvRemindMeTime);
		newFragment.show(this.getFragmentManager(), "numberPicker");
	}

	private long createAssignment() {
		Assignment assignment = new Assignment();

		EditText etTitle = (EditText) findViewById(R.id.etTitle);
		assignment.setTitle(etTitle.getText().toString());

		EditText etDescription = (EditText) findViewById(R.id.etDescrip);
		assignment.setDescription(etDescription.getText().toString());

		TextView tvDueDate = (TextView) findViewById(R.id.tvDueDate);
		assignment.setDueDate(tvDueDate.getText().toString());

		TextView tvTimeDue = (TextView) findViewById(R.id.tvTimeDue);
		assignment.setTimeDue(tvTimeDue.getText().toString());

		assignment.setCourseID(Integer.parseInt(courseId));
		long id = dataSource.createAssignment(assignment);
		return id;
	}

	public void setCurrentTimeOnView() {

		TextView tvTimeDue = (TextView) findViewById(R.id.tvTimeDue);

		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		tvTimeDue.setText(new StringBuilder().append(hour).append(" : ")
				.append(minute));

	}

	@Override
	protected void onStop() {
		// When our activity is stopped ensure we also stop the connection to
		// the service
		// this stops us leaking our activity into the system *bad*
		if (scheduleClient != null)
			scheduleClient.doUnbindService();
		super.onStop();
	}

}
