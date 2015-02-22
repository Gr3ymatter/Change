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
import change.gr3ymatterstudios.com.change.data.RoutineDbHelper;

/**
 * Created by Afzal on 1/11/15.
 */
public class TestProvider extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();


    public void testDeleteDb() throws Throwable {
        mContext.deleteDatabase(RoutineDbHelper.DATABASE_NAME);
    }


    public void testGetType() throws Throwable{

        String type = mContext.getContentResolver().getType(RoutineEntry.CONTENT_URI);

        assertEquals(RoutineEntry.CONTENT_TYPE, type);


        type = mContext.getContentResolver().getType(RoutineEntry.buildRoutineEntryRoutineDateUri("Chest Day", "20151413"));

        assertEquals(RoutineEntry.CONTENT_TYPE, type);


        type = mContext.getContentResolver().getType(ExerciseEntry.buildExerciseUri(2L));

        assertEquals(ExerciseEntry.CONTENT_ITEM_TYPE, type);

    }


    public void testInsertReadDB() throws Throwable{

        SQLiteDatabase db = new RoutineDbHelper(this.mContext).getWritableDatabase();

        ContentValues values = getExerciseValues();

        long exerciseRowId = -1;

        exerciseRowId = db.insert(ExerciseEntry.TABLE_NAME, null, values);


        assertEquals(true, exerciseRowId != -1);

        Log.d(LOG_TAG, "New ROW ID: " + exerciseRowId);

        Cursor cursor = db.query(ExerciseEntry.TABLE_NAME, null, null, null, null, null, null);

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
        }
    }

}
