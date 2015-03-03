package change.gr3ymatterstudios.com.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.Map;
import java.util.Set;

import change.gr3ymatterstudios.com.change.data.RoutineContract.ExerciseEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.RoutineEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.UserEntry;
import change.gr3ymatterstudios.com.change.data.RoutineDbHelper;

/**
 * Created by Afzal on 1/11/15.
 */
public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();


    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(RoutineDbHelper.DATABASE_NAME);

        SQLiteDatabase db = new RoutineDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDB() throws Throwable{

        SQLiteDatabase db = new RoutineDbHelper(this.mContext).getWritableDatabase();

        printDatabaseNames(db);

        ContentValues values = getUserValues(false);

        long userRowId = -1;

        userRowId = db.insert(UserEntry.TABLE_NAME,null, values);

        assertEquals(true, userRowId != -1);


        Cursor cursor = db.query(UserEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            validateCursor(values, cursor);
        }

        values = getUserValues(true);

        userRowId = -1;

        userRowId = db.insert(UserEntry.TABLE_NAME, null, values);

        assertEquals(true, userRowId != -1);

        Log.d(LOG_TAG, "New ROW ID: " + userRowId);

        cursor = db.query(UserEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            validateCursor(values, cursor);
        }


        values = getExerciseValues();

        long exerciseRowId = -1;

        exerciseRowId = db.insert(ExerciseEntry.TABLE_NAME, null, values);


        assertEquals(true, exerciseRowId != -1);

        Log.d(LOG_TAG, "New ROW ID: " + exerciseRowId);

        cursor = db.query(ExerciseEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            validateCursor(values, cursor);

        }
        else
        {
            Log.d(LOG_TAG, "Test Failed.");
        }


        values = getRoutineValues(exerciseRowId);

        long routineRowId = -1;

        routineRowId = db.insert(RoutineEntry.TABLE_NAME, null, values);

        assertEquals(true, routineRowId != -1);

        cursor = db.query(RoutineEntry.TABLE_NAME, null,null, null, null, null, null);

        if(cursor.moveToFirst()){

            validateCursor(values, cursor);
        }

    }

    ContentValues getUserValues(boolean nullTest){
        String user_name = "Sal";
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_USER_NAME, user_name);
        if(!nullTest){
            String user_gender = "Male";
            int user_age = 28;
            float user_height =6.4f;
            float user_weight = 204.f;
            float user_bmi = 23.45f;
            float user_goal = 210.0f;

            values.put(UserEntry.COLUMN_USER_AGE, user_age);
            values.put(UserEntry.COLUMN_USER_GENDER, user_gender);
            values.put(UserEntry.COLUMN_USER_HEIGHT, user_height);
            values.put(UserEntry.COLUMN_USER_WEIGHT, user_weight);
            values.put(UserEntry.COLUMN_USER_BMI, user_bmi);
            values.put(UserEntry.COLUMN_USER_GOAL, user_goal);
        }

        return values;

    }


    ContentValues getRoutineValues(long exerciseRowId){
        String routine_title = "Upper Body";
        String routine_date = "20150405";
        long routine_exercisekey = exerciseRowId;
        int routine_set = 1;
        int routine_rep = 8;
        float routine_weight = 32.5f;
        float routine_max_weight = 32.5f;


        ContentValues values = new ContentValues();
        values.put(RoutineEntry.COLUMN_TITLE, routine_title);
        values.put(RoutineEntry.COLUMN_DATETEXT, routine_date);
        values.put(RoutineEntry.COLUMN_EXERCISE_KEY, routine_exercisekey);
        values.put(RoutineEntry.COLUMN_SET, routine_set);
        values.put(RoutineEntry.COLUMN_REPS, routine_rep);
        values.put(RoutineEntry.COLUMN_WEIGHT, routine_weight);
        values.put(RoutineEntry.COLUMN_MAX_WEIGHT, routine_max_weight);

        return values;
    }


    ContentValues getExerciseValues(){
        String exercise_name = "Bench Press";
        String exercise_category = "Chest";
        Float exercise_total_weight = 1023.4f;

        ContentValues values = new ContentValues();
        values.put(ExerciseEntry.COLUMN_NAME, exercise_name);
        values.put(ExerciseEntry.COLUMN_CATEGORY, exercise_category);
        values.put(ExerciseEntry.COLUMN_TOTAL_WEIGHT_LIFTED, exercise_total_weight);

        return values;
    }



    static public void validateCursor(ContentValues expectedValues, Cursor valueCursor){
        Set<Map.Entry<String, Object>> valueset = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry: valueset){
            String columnName = entry.getKey();
            int index = valueCursor.getColumnIndex(columnName);
            assertFalse( -1 == index);
            String expectedValue = entry.getValue().toString();
            assertEquals(expectedValue, valueCursor.getString(index));

            Log.d("TEST_CURSOR_PRINT_TAG", valueCursor.getString(index));


        }
    }

    static public void printDatabaseNames(SQLiteDatabase db){

        Cursor c  = db.rawQuery("SELECT * FROM sqlite_master WHERE type = 'table'", null);

        if(c.moveToFirst()){
            Log.d("TABLE_PRINT_LOG", c.getString(0));
        }

    }

}
