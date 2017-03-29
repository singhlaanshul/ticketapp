package ticketapp.emmar.com.ticketapp.Classes;

/**
 * Created by anshulsinghla on 3/29/17.
 */

public class Constants {


    //API Urls to get data from server
    public static final String SHIFTS_DATA_URL = "https://api.myjson.com/bins/169zzz";
    public static final String VISITOR_TYPE_DETAIL_URL = "https://api.myjson.com/bins/1fsxof";

    public static final String APP_NAME = "YTS";
    public static final String PACKAGE_NAME = "com.phoebe.YTS";
    public static final String UTF8 = "UTF-8";
    public static final boolean IS_DEBUG = true;

    public static int maxTimeout = 60000;
    public static int maxReadTimeOut = 15000; /* milliseconds */
    public static int maxConnTimeOut = 20000; /* milliseconds */

    public static String requestMethodGet = "GET";

    public static final String SHIFTS = "Shifts";
    public static final String SHIFT_ID = "ID";
    public static final String SHIFT_NAME = "Name";
    public static final String SHIFT_START_HOUR = "StartHour";
    public static final String SHIFT_END_HOUR = "EndHour";

    public static final String GENDERS = "Genders";
    public static final String GENDERS_DESCRIPTION = "Description";

    public static final String VISITORS_TYPES = "VisitorsTypes";
    public static final String VISITOR_ALIAS_ID = "AliasID";
    public static final String VISITOR_AGE_CRITERIA = "AgeCriteria";
    public static final String VISITOR_DESCRIPTION = "Description";
    public static final String VISITOR_MAX_AGE = "MaxAge";
    public static final String VISITOR_MIN_AGE = "MinAge";
    public static final String VISITOR_PRICE = "Price";
    public static final String VISITOR_TICKET_CONTENT_URL = "TicketContentURL";

    public static final String RESULT = "Result";
    public static final String STATUS_CODE = "StatusCode";

    public static final String STATUS_CODE_OK = "1";
    public static final String STATUS_CODE_ERROR = "0";
}
