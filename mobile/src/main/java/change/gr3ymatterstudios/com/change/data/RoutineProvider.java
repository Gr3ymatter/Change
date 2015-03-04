package change.gr3ymatterstudios.com.change.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Afzal on 1/25/15.
 */
public class RoutineProvider extends ContentProvider {


    private RoutineDbHelper mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();


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

    @Override
    public boolean onCreate() {
        mOpenHelper = new RoutineDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
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
