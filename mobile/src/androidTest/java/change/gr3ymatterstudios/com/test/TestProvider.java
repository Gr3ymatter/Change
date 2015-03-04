package change.gr3ymatterstudios.com.test;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.Map;
import java.util.Set;

import change.gr3ymatterstudios.com.change.data.RoutineContract;
import change.gr3ymatterstudios.com.change.data.RoutineContract.ExerciseEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.RoutineEntry;
import change.gr3ymatterstudios.com.change.data.RoutineDbHelper;
import change.gr3ymatterstudios.com.change.data.RoutineProvider;

/**
 * Created by Afzal on 1/11/15.
 */
public class TestProvider extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();


    public void testDeleteDb() throws Throwable {
        mContext.deleteDatabase(RoutineDbHelper.DATABASE_NAME);
    }



    public void testUriMatcher() throws Throwable{

        final String routinename = "Chest";
        final String TEST_DATE = "20141413";
        final String USER_NAME = "Sal";

        final long exercise_id = 2L;


        final Uri TEST_USERNAME = RoutineContract.UserEntry.buildUserNameUri(USER_NAME);
        final Uri TEST_ROUTINE_WITH_DATE = RoutineEntry.buildRoutineEntryRoutineDateUri(routinename, TEST_DATE);
        final Uri TEST_ROUTINE = RoutineEntry.CONTENT_URI;

        final Uri TEST_EXERCISE_WITH_DATE_OPTIONAL_ROUTINE = RoutineEntry.buildRoutineEntryExerciseDate(routinename, exercise_id, TEST_DATE);
        Log.d(LOG_TAG, TEST_EXERCISE_WITH_DATE_OPTIONAL_ROUTINE.toString());
        final Uri TEST_EXERCISE = ExerciseEntry.CONTENT_URI;
        final Uri TEST_EXERCISE_WITH_ID = ExerciseEntry.buildExerciseUri(exercise_id);


        UriMatcher uriMatcher = RoutineProvider.buildUriMatcher();

        assertEquals("Error: USER URI matched incorrectly.",uriMatcher.match(TEST_USERNAME), RoutineProvider.USER_NAME);
        assertEquals("Error: ROUTINE URI matched incorrectly.",uriMatcher.match(TEST_ROUTINE), RoutineProvider.ROUTINE);
        assertEquals("Error: ROUTINE_WITH_DATE URI matched incorrectly.",uriMatcher.match(TEST_ROUTINE_WITH_DATE), RoutineProvider.ROUTINE_WITH_DATE);
        assertEquals("Error: TEST_EXERCISE_WITH_DATE_OPTIONAL_ROUTINE URI matched incorrectly.",uriMatcher.match(TEST_EXERCISE_WITH_DATE_OPTIONAL_ROUTINE), RoutineProvider.EXERCISE_WITH_DATE_OPTIONAL_ROUTINE);
        assertEquals("Error: TEST_EXERCISE URI matched incorrectly.",uriMatcher.match(TEST_EXERCISE), RoutineProvider.EXERCISE);
        assertEquals("Error: TEST_EXERCISE_WITH_ID URI matched incorrectly.",uriMatcher.match(TEST_EXERCISE_WITH_ID), RoutineProvider.EXERCISE_WITH_ID);



    }


    public void testGetType() throws Throwable{



        String type = mContext.getContentResolver().getType(RoutineEntry.CONTENT_URI);

        assertEquals(RoutineEntry.CONTENT_TYPE, type);


        type = mContext.getContentResolver().getType(RoutineEntry.buildRoutineEntryRoutineDateUri("Chest Day", "20151413"));

        assertEquals(RoutineEntry.CONTENT_TYPE, type);


        type = mContext.getContentResolver().getType(RoutineContract.UserEntry.buildUserNameUri("Sal"));

        assertEquals(RoutineContract.UserEntry.CONTENT_TYPE, type);

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
