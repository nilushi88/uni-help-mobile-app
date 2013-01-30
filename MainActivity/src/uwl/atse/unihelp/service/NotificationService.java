package uwl.atse.unihelp.service;

import uwl.atse.unihelp.ui.NotificationDisplay;
import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends Service {

	/**
	 * Class for clients to access
	 */
	public class ServiceBinder extends Binder {
		NotificationService getService() {
			return NotificationService.this;
		}
	}

	// Unique id to identify the notification.
	private static final int NOTIFICATION = 123;
	// Name of an intent extra we can use to identify if this service was
	// started to create a notification
	public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
	// The system notification manager
	private NotificationManager mNM;

	@Override
	public void onCreate() {
		Log.d("NILU", "NotifyService" + "onCreate()");
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("NILU", "Received start id " + startId + ": " + intent);

		// If this service was started by out AlarmTask intent then we want to
		// show our notification
		if (intent.getBooleanExtra(INTENT_NOTIFY, false))
			showNotification(intent);

		// We don't care if this service is stopped as we have already delivered
		// our notification
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	// This is the object that receives interactions from clients
	private final IBinder mBinder = new ServiceBinder();

	/**
	 * Creates a notification and shows it in the OS drag-down status bar
	 */
	private void showNotification(Intent intent) {
		// This is the 'title' of the notification

		String title = intent.getStringExtra("TITLE");

		// This is the icon to use on the notification
		int icon = R.drawable.ic_popup_reminder;
		// This is the scrolling text of the notification
		String date = intent.getStringExtra("DATE");
		String time = intent.getStringExtra("TIME");
		Log.d("NILU", "SHOW NOTIFICATION::: " + title + "  " + date + "  "
				+ time);

		CharSequence text = "Due on " + date + " at " + time;
		// What time to show on the notification
		long when = System.currentTimeMillis();

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setTicker(title + " Due Soon")

		.setContentTitle(title).setSmallIcon(icon).setWhen(when)
				.setContentText(text);
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
		Notification notification = mBuilder.build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		mNM.notify(NOTIFICATION, notification);

		// Stop the service when we are finished
		stopSelf();
	}
}
