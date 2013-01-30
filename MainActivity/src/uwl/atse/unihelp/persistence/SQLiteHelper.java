/**
 * 
 */
package uwl.atse.unihelp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Nilu
 * 
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_ASSIGNMENT = "assignment";
	public static final String TABLE_COURSE = "course";

	public static final String ID = "_id";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String DUE_DATE = "dueDate";
	public static final String TIME_DUE = "timeDue";

	public static final String COURSE_ID = "courseId";
	public static final String NAME = "name";
	public static final String LOCATION = "location";

	private static final String DATABASE_NAME = "unihelpDB";
	private static final int DATABASE_VERSION = 1;

	public static final String CREATE_TABLE_ASSIGNMENT = "create table "
			+ TABLE_ASSIGNMENT + "(" + ID
			+ " integer primary key autoincrement, " + TITLE
			+ " text not null, " + DESCRIPTION + " text not null, " + DUE_DATE
			+ " text not null, " + TIME_DUE + " text not null, " + COURSE_ID
			+ " integer not null, " + " FOREIGN KEY(" + COURSE_ID
			+ ") REFERENCES " + TABLE_COURSE + "(" + ID + "));";

	public static final String CREATE_TABLE_COURSE = "create table "
			+ TABLE_COURSE + "(" + ID + " integer primary key autoincrement, "
			+ NAME + " text not null, " + LOCATION + " text not null);";

	/**
	 * @param context
	 */
	public SQLiteHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_ASSIGNMENT);
		database.execSQL(CREATE_TABLE_COURSE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {

		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
		onCreate(database);
	}

}
