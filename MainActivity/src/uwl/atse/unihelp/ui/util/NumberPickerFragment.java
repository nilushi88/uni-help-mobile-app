package uwl.atse.unihelp.ui.util;

import uwl.atse.unihelp.R;
import uwl.atse.unihelp.ui.AddCourseItem;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class NumberPickerFragment extends DialogFragment {
	private TextView tvRemindMeTime;

	public NumberPickerFragment(TextView tvRemindMeTime) {
		this.tvRemindMeTime = tvRemindMeTime;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.picker, null);

		final NumberPicker npDays = (NumberPicker) view
				.findViewById(R.id.npDays);
		npDays.setMaxValue(30);
		npDays.setMinValue(0);

		final NumberPicker npHours = (NumberPicker) view
				.findViewById(R.id.npHours);
		npHours.setMaxValue(24);
		npHours.setMinValue(0);

		builder.setView(view)
				.setTitle("Remind Me: ")
				// Add action buttons
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						AddCourseItem.remindDays = npDays.getValue();
						AddCourseItem.remindHours = npHours.getValue();

						setReminderLable();

					}

					private void setReminderLable() {
						if (AddCourseItem.remindDays == 0
								&& AddCourseItem.remindHours == 0) {
							tvRemindMeTime.setText("0 Day(s) 0 Hour(s) Before");
						} else if (AddCourseItem.remindDays > 0
								&& AddCourseItem.remindHours > 0) {
							tvRemindMeTime.setText(AddCourseItem.remindDays
									+ " Day(s) " + AddCourseItem.remindHours
									+ "Hour(s) Before");

						} else if (AddCourseItem.remindDays > 0) {
							tvRemindMeTime.setText(AddCourseItem.remindDays
									+ " Day(s) Before");

						} else if (AddCourseItem.remindHours > 0) {
							tvRemindMeTime.setText(AddCourseItem.remindHours
									+ " Hour(s) Before");

						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// TODO
							}
						});

		return builder.create();
	}

}
