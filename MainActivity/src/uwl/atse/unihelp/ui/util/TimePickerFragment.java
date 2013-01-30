package uwl.atse.unihelp.ui.util;

import java.util.Calendar;

import uwl.atse.unihelp.ui.AddCourseItem;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements
		OnTimeSetListener {

	TextView tvTimeDue;

	public TimePickerFragment(TextView tvTimeDue) {
		this.tvTimeDue = tvTimeDue;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));

	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		int year = AddCourseItem.mCalendar.get(Calendar.YEAR);
		int month = AddCourseItem.mCalendar.get(Calendar.MONTH);
		int day = AddCourseItem.mCalendar.get(Calendar.DAY_OF_MONTH);

		AddCourseItem.mCalendar.set(year, month, day, hourOfDay, minute);
		tvTimeDue.setText(String.valueOf(hourOfDay) + " : "
				+ String.valueOf(minute));
	}

}
