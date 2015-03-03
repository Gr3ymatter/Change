package change.gr3ymatterstudios.com.change.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

/**
 * Created by Afzal on 1/7/15.
 */
public class RoutineContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.

    public static final String CONTENT_AUTHORITY = "com.gr3ymatterstudios.change";
    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    public static final String PATH_USER = "user_info";
    public static final String PATH_ROUTINE = "routine";
    public static final String PATH_EXERCISE = "exercise";


    /* TODO Uncomment for
    4b - Finishing the FetchWeatherTask
    https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/m-1675098569
    // Format used for storing dates in the database.  ALso used for converting those strings
    // back into date objects for comparison/processing.

    public static final String DATE_FORMAT = "yyyyMMdd";
    */

    /**
     * Converts Date class to a string representation, used for easy comparison and database lookup.
     * @param date The input date
     * @return a DB-friendly representation of the date, using the format defined in DATE_FORMAT.
     */
    /* TODO Uncomment for
    4b - Finishing the FetchWeatherTask
    https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/m-1675098569
    public static String getDbDateString(Date date){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
    */

    /**
     * Converts a dateText to a long Unix time representation
     * @param dateText the input date string
     * @return the Date object
     */
    /* TODO Uncomment for
    4b - Finishing the FetchWeatherTask
    https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/m-1675098569
    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }
    */


    public static final class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_USER_GENDER = "user_gender";
        public static final String COLUMN_USER_HEIGHT = "user_height";
        public static final String COLUMN_USER_WEIGHT = "user_weight";
        public static final String COLUMN_USER_GOAL = "goal_weight";
        public static final String COLUMN_USER_BMI = "user_bmi";
        public static final String COLUMN_USER_AGE = "user_age";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static Uri buildUserIDURI(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildUserNameUri(String name){
            return CONTENT_URI.buildUpon().appendPath(COLUMN_USER_NAME).build();
        }

        public static String getUserNameFromUri(Uri uri){
            return uri.getPathSegments().get(0);
        }


    }


    /**
     * Database Contract for Routines
     * Contains the following columns:
     * Routine Title, Date, Exercise id, Set No, Reps, Weight, Max Weight
     **/
    public static final class RoutineEntry implements BaseColumns {
        /**
         * TODO YOUR CODE BELOW HERE FOR QUIZ
         * QUIZ - 4a - Columns
         * https://www.udacity.com/course/viewer#!/c-ud853/l-1639338560/e-1633698595/m-1633698597
         **/

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROUTINE).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_ROUTINE;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ROUTINE;


        /**
         * TODO YOUR CODE BELOW HERE FOR QUIZ
         * QUIZ - 4b - Adding LocationEntry with ID UriBuilder
         * https://www.udacity.com/course/viewer#!/c-ud853/l-1576308909/e-1604969848/m-1604969849
         **/

        /**
         * What do i want from this table?
         * 1) Information about previous session of a specific routine. - RoutineListView
         * 2) Information about a specific exercise for all logged sessions -ExerciseDetailView
         * 3) Directory Information about routine from a specific start date to current - Calendar View
         */


        // Table Name
        public static final String TABLE_NAME = "routine";

        //Routine Title
        public static final String COLUMN_TITLE = "routine_title";
        //Date Routine Performed Stored as yyyy-mm-dd
        public static final String COLUMN_DATETEXT = "date";
        //Id of Exercise Performed Which is the Primary Key in Exercise Entry
        public static final String COLUMN_EXERCISE_KEY = "exercise_id";
        //SET #
        public static final String COLUMN_SET = "set_number";
        //Number of Reps Performed
        public static final String COLUMN_REPS = "reps";
        //Weight Lifted
        public static final String COLUMN_WEIGHT = "weight";
        //Max Weight Lifted
        public static final String COLUMN_MAX_WEIGHT = "max_weight";

        public static Uri buildRoutineEntryUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        //Returns a DIR of A specific Routine on Selectable Dates
        public static Uri buildRoutineEntryRoutineDateUri(String RoutineName, String Date){
            return CONTENT_URI.buildUpon().appendPath(RoutineName).appendQueryParameter(COLUMN_DATETEXT, Date).build();
        }

        //Returns a Dir of specific exercise for selectable logged sessions
        public static Uri buildRoutineEntryExerciseDate(String RoutineTitle, String ExerciseId, String Date){
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_DATETEXT, Date).appendQueryParameter(COLUMN_EXERCISE_KEY, ExerciseId).appendQueryParameter(COLUMN_TITLE, RoutineTitle).build();
        }


        public static String getRoutineNameFromUri(Uri uri){return uri.getPathSegments().get(0);}

        public static String getStartDateFromUri(Uri uri){ return uri.getQueryParameter(COLUMN_DATETEXT);}

        public static String getExerciseIdFromUri(Uri uri){ return uri.getQueryParameter(COLUMN_EXERCISE_KEY);}



    }


    /**
     * Exercise Table
     *
     * Contains Columns:
     * Exercise Name, Category, total_weight
     *
     *
     */
    public static final class ExerciseEntry implements BaseColumns {



        public static final String TABLE_NAME = "exercise";

        public static final String COLUMN_NAME = "exercise_name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_TOTAL_WEIGHT_LIFTED = "total_weight";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EXERCISE).build();

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_EXERCISE;
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_EXERCISE;


        public static Uri buildExerciseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static Uri buildExerciseCategoryUri(String category){
            return CONTENT_URI.buildUpon().appendPath(category).build();
        }

        //TODO: Potentially Remove
        public static Uri buildExerciseWeightUri(String weight){
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_TOTAL_WEIGHT_LIFTED, weight).build();
        }

        public static Uri buildExerciseCategoryWeightUri(String category, String Weight){
            return CONTENT_URI.buildUpon().appendPath(category).appendQueryParameter(COLUMN_TOTAL_WEIGHT_LIFTED, Weight).build();
        }


        public static String getCategoryFromUri(Uri uri){
            return uri.getPathSegments().get(0);
        }

        public static String getWeightFromUri(Uri uri) { return uri.getQueryParameter(COLUMN_TOTAL_WEIGHT_LIFTED);}

}


}
