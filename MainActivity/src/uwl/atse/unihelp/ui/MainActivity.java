package uwl.atse.unihelp.ui;

import uwl.atse.unihelp.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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

				startActivity(new Intent(MainActivity.this,
						DisplayCourses.class));
			}
		});
		Button button = (Button) findViewById(R.id.buttonPicker);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("Button Clicked");

				notifyUser();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void createNotification() {
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, NotificationDisplay.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// Build notification
		// Actions are just fake
		Notification noti = new Notification.Builder(this)
				.setContentTitle("New mail from " + "test@gmail.com")
				.setContentText("Subject").setContentIntent(pIntent).build();
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		System.out.println("Manager Notified");

		notificationManager.notify(0, noti);

	}

	private void notifyUser() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this)

		.setContentTitle("My notification")
				.setSmallIcon(android.R.drawable.ic_popup_reminder)
				.setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, NotificationDisplay.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(NotificationDisplay.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		int mId = 1;
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, mBuilder.build());
	}

}
