package uwl.atse.unihelp.service;

import java.text.DateFormat;
import java.util.Calendar;

import uwl.atse.unihelp.domain.Assignment;
import uwl.atse.unihelp.persistence.DataSource;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmTask implements Runnable {
	// The date selected for the alarm
	private final Calendar date;
	private final long id;

	// The android system alarm manager
	private final AlarmManager am;
	// Your context to retrieve the alarm manager from
	private final Context context;
	private DataSource dataSource;

	DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
	DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.SHORT);

	public AlarmTask(Context context, Calendar date, long id) {
		Log.d("NILU", "creating new Alarm Task " + id);

		this.context = context;
		dataSource = new DataSource(context);
		dataSource.open();
		this.am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		this.date = date;
		this.id = id;
	}

	@Override
	public void run() {
		// Request to start are service when the alarm date is upon us
		// We don't start an activity as we just want to pop up a notification
		// into the system bar not a full activity
		Log.d("NILU", "finding assignment by id :: " + id);

		Assignment assignment = dataSource.findAssignmentById(id);
		Log.d("NILU", "retrieved assignment details in Alarm Task "
				+ assignment.toString());

		String title = assignment.getTitle();
		String dueDate = assignment.getDueDate();
		String timeDue = assignment.getTimeDue();

		Intent intent = new Intent(context, NotificationService.class);

		intent.putExtra(NotificationService.INTENT_NOTIFY, true);
		intent.putExtra("TITLE", title);
		intent.putExtra("DATE", dueDate);
		intent.putExtra("TIME", timeDue);

		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, 0);

		// Sets an alarm - note this alarm will be lost if the phone is turned
		// off and on again
		am.set(AlarmManager.RTC, date.getTimeInMillis(), pendingIntent);

	}
}
