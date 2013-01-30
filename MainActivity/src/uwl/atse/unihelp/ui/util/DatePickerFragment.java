package uwl.atse.unihelp.ui.util;

import java.util.Calendar;

import uwl.atse.unihelp.ui.AddCourseItem;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {

	TextView tvDueDate;

	public DatePickerFragment(TextView tvDueDate) {
		this.tvDueDate = tvDueDate;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		AddCourseItem.mCalendar.set(year, monthOfYear, dayOfMonth);
		tvDueDate.setText(String.valueOf(dayOfMonth) + "/"
				+ String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year));
	}

}
