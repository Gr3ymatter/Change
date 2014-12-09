package data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Afzal on 11/16/14.
 */
public class ExcerciseLoggingContract {


    public static final String CONTENT_AUTHORITY = "change.com.gr3ymatterstudios.change";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ROUTINE = "routine";
    public static final String PATH_EXCERCISE = "excercise";


    public static final String DATE_FORMAT = "yyyyMMdd";


    /**
     * Converts Date class to a string representation, used for easy comparison and database lookup.
     * @param date The input date
     * @return a DB-friendly representation of the date, using the format defined in DATE_FORMAT.
     */
    public static String getDbDateString(Date date){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }


    /**
     * Converts a dateText to a long Unix time representation
     * @param dateText the input date string
     * @return the Date object
     */
    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }

    /* Inner class that defines the table contents of the location table */
    public static final class ExcerciseEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EXCERCISE).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_EXCERCISE;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_EXCERCISE;

        // Table name
        public static final String TABLE_NAME = "excercise";

        // The location setting string is what will be sent to openweathermap
        // as the location query.
        public static final String COLUMB_WEIGHT_SETTING = "weight_setting";

        // Human readable location string, provided by the API.  Because for styling,
        // "Mountain View" is more recognizable than 94043.
        public static final String COLUMN_EXCERCISE_NAME = "excercise_name";

        public static final String COLUMN_EXCERCISE_DATE = "excercise_date";

        // In order to uniquely pinpoint the location on the map when we launch the
        // map intent, we store the latitude and longitude as returned by openweathermap.
        public static final String COLUMN_EXCERCISE_WEIGHT_LIFTED = "weight_lifted";
        public static final String COLUMN_EXCERCISE_MAX_LIFTED = "weight_max_lifted";

        public static Uri buildExcerciseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }




    public ExcerciseLoggingContract(){

    }

}
