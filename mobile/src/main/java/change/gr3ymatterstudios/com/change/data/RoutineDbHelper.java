package change.gr3ymatterstudios.com.change.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import change.gr3ymatterstudios.com.change.data.RoutineContract.ExerciseEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.RoutineEntry;


/**
 * Created by Afzal on 1/9/15.
 */
public class RoutineDbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Routine.db";

    public RoutineDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EXERCISE_TABLE = "CREATE TABLE " + ExerciseEntry.TABLE_NAME + " (" +
                ExerciseEntry._ID + " INTEGER PRIMARY KEY, " +
                ExerciseEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_TOTAL_WEIGHT_LIFTED + " REAL, " +
                "UNIQUE (" + ExerciseEntry.COLUMN_NAME + ") ON CONFLICT IGNORE" + " );";



        final String SQL_CREATE_ROUTINE_TABLE = "CREATE TABLE " + RoutineEntry.TABLE_NAME + " (" +
                RoutineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RoutineEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                RoutineEntry.COLUMN_DATETEXT + " TEXT NOT NULL, " +
                RoutineEntry.COLUMN_EXERCISE_KEY + " INTEGER NOT NULL, " +
                RoutineEntry.COLUMN_SET + " INTEGER NOT NULL, " +
                RoutineEntry.COLUMN_REPS + " INTEGER NOT NULL, " +
                RoutineEntry.COLUMN_WEIGHT + " REAL NOT NULL, " +
                RoutineEntry.COLUMN_MAX_WEIGHT + " REAL NOT NULL, " +

                " FOREIGN KEY (" + RoutineEntry.COLUMN_EXERCISE_KEY + ") REFERENCES " +
                ExerciseEntry.TABLE_NAME + " (" + ExerciseEntry._ID + "), " +
                " UNIQUE (" + RoutineEntry.COLUMN_DATETEXT + ", " + RoutineEntry.COLUMN_EXERCISE_KEY + ", " +
                RoutineEntry.COLUMN_SET + ") ON CONFLICT REPLACE );";


        db.execSQL(SQL_CREATE_EXERCISE_TABLE);
        db.execSQL(SQL_CREATE_ROUTINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + RoutineEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseEntry.TABLE_NAME);
        onCreate(db);

    }
}
