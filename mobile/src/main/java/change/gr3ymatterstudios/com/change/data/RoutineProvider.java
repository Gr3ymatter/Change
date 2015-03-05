package change.gr3ymatterstudios.com.change.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import change.gr3ymatterstudios.com.change.data.RoutineContract.ExerciseEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.RoutineEntry;
import change.gr3ymatterstudios.com.change.data.RoutineContract.UserEntry;

/**
 * Created by Afzal on 1/25/15.
 */
public class RoutineProvider extends ContentProvider {


    private RoutineDbHelper mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder getRoutineInfoWithExerciseAndDateBuilder;

    static {
        getRoutineInfoWithExerciseAndDateBuilder = new SQLiteQueryBuilder();
        getRoutineInfoWithExerciseAndDateBuilder.setTables(RoutineEntry.TABLE_NAME + " INNER JOIN " +
                ExerciseEntry.TABLE_NAME + " ON " + RoutineEntry.TABLE_NAME + "." + RoutineEntry.COLUMN_EXERCISE_KEY +
                " = " + ExerciseEntry.TABLE_NAME + "." + ExerciseEntry._ID);
    }


    public static final int USER_NAME = 99;
    public static final int ROUTINE = 100;
    public static final int ROUTINE_WITH_DATE = 101;
    public static final int EXERCISE_WITH_DATE_OPTIONAL_ROUTINE = 102;

    public static final int EXERCISE = 200;
    public static final int EXERCISE_WITH_ID = 201;


    public static UriMatcher buildUriMatcher(){

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RoutineContract.CONTENT_AUTHORITY;


        matcher.addURI(authority, RoutineContract.PATH_USER + "/*", USER_NAME);
        matcher.addURI(authority, RoutineContract.PATH_ROUTINE, ROUTINE);
        matcher.addURI(authority, RoutineContract.PATH_ROUTINE + "/*", ROUTINE_WITH_DATE);
        matcher.addURI(authority, RoutineContract.PATH_ROUTINE + "/*/#", EXERCISE_WITH_DATE_OPTIONAL_ROUTINE);

        matcher.addURI(authority, RoutineContract.PATH_EXERCISE, EXERCISE);
        matcher.addURI(authority, RoutineContract.PATH_EXERCISE + "/#", EXERCISE_WITH_ID);

        return matcher;
    }


    private static final String sUserNameSelection = UserEntry.TABLE_NAME + "." + UserEntry.COLUMN_USER_NAME + " = ? ";


    public Cursor getUserNameInfo(Uri uri, String[] projection, String sortOrder){

        String name = RoutineContract.UserEntry.getUserNameFromUri(uri);

        return mOpenHelper.getReadableDatabase().query(UserEntry.TABLE_NAME, projection, sUserNameSelection, new String[]{name}, null, null, sortOrder);
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new RoutineDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        final int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch(match){
            case USER_NAME:
                retCursor = getUserNameInfo(uri, projection, sortOrder);
                break;
            case ROUTINE:
                retCursor = getRoutineInformation(uri, projection, sortOrder);
                break;
            case ROUTINE_WITH_DATE:
                retCursor = getRoutineInfoOnDate(uri, projection, sortOrder);
                break;
            case EXERCISE_WITH_DATE_OPTIONAL_ROUTINE:
                retCursor = getExerciseInfoOnDateOfRoutine(uri, projection, sortOrder);
                break;
            case EXERCISE:
                retCursor = getAllExercise(uri, projection, sortOrder);
                break;
            case EXERCISE_WITH_ID:
                retCursor = getSpecificExercise(uri, projection, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);

        }
        retCursor.setNotificationUri(this.getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch(match){
            case USER_NAME:
                return RoutineContract.UserEntry.CONTENT_TYPE;
            case ROUTINE:
                return RoutineContract.RoutineEntry.CONTENT_TYPE;
            case ROUTINE_WITH_DATE:
                return RoutineContract.RoutineEntry.CONTENT_TYPE;
            case EXERCISE_WITH_DATE_OPTIONAL_ROUTINE:
                return RoutineContract.RoutineEntry.CONTENT_TYPE;
            case EXERCISE:
                return RoutineContract.ExerciseEntry.CONTENT_TYPE;
            case EXERCISE_WITH_ID:
                return RoutineContract.ExerciseEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
