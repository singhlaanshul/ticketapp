package ticketapp.emmar.com.ticketapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ticketapp.emmar.com.ticketapp.Classes.Constants;
import ticketapp.emmar.com.ticketapp.Classes.Shift;
import ticketapp.emmar.com.ticketapp.Classes.Visitor;
import ticketapp.emmar.com.ticketapp.Parsers.HttpHandler;

import static ticketapp.emmar.com.ticketapp.Classes.Constants.SHIFTS_DATA_URL;
import static ticketapp.emmar.com.ticketapp.Classes.Constants.VISITOR_TYPE_DETAIL_URL;
import static ticketapp.emmar.com.ticketapp.Enums.ApiEnum.SHIFTS_DATA;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = HttpHandler.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<Shift> shifts = new ArrayList<>();
    ArrayList<Visitor> visitorTypes = new ArrayList<>();
    List<String> mGenders = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new getData().execute(SHIFTS_DATA);
    }

    /**
     * Async task class to get json data for shifts by making HTTP call
     */
    protected class getData extends AsyncTask<Enum, Void, Enum> {
        boolean isError = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Enum doInBackground(Enum... arg0) {
            HttpHandler handler = new HttpHandler();
            String jsonStr;

            if (arg0[0] == SHIFTS_DATA) {
                // Making a request to url and getting response
                jsonStr = handler.makeServiceCall(SHIFTS_DATA_URL);
                Log.e(TAG, "Response from url: " + jsonStr);
                isError = getShiftsData(jsonStr);
            } else {
                // Making a request to url and getting response
                jsonStr = handler.makeServiceCall(VISITOR_TYPE_DETAIL_URL);
                Log.e(TAG, "Response from url: " + jsonStr);
                isError = getVisitorDetailData(jsonStr);
            }


            return arg0[0];
        }

        @Override
        protected void onPostExecute(Enum result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (!isError) {
                //inflate layout using data
                if (result == SHIFTS_DATA) {
                    //populate shifts data
                }else{
                    //populate visitors data
                }

            } else {
                //show alert error message
                showAlertDialog(getResources().getString(R.string.api_error_message));
            }
        }

    }


    private boolean getShiftsData(String jsonStr) {
        boolean isError = false;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting result JSON object
                JSONObject resultObj = jsonObj.getJSONArray(Constants.RESULT).getJSONObject(0);
                String statusCode = resultObj.getString(Constants.STATUS_CODE);

                if (statusCode.equals(Constants.STATUS_CODE_OK)) {

                    // Getting genders JSON Array node
                    JSONArray gendersArray = jsonObj.getJSONArray(Constants.GENDERS);
                    // looping through All Shifts objects
                    for (int i = 0; i < gendersArray.length(); i++) {
                        JSONObject g = gendersArray.getJSONObject(i);
                        mGenders.add(g.getString(Constants.GENDERS_DESCRIPTION));
                    }
                    // Getting shifts JSON Array node
                    JSONArray shiftsArray = jsonObj.getJSONArray(Constants.SHIFTS);
                    // looping through All Shifts objects
                    for (int i = 0; i < shiftsArray.length(); i++) {
                        JSONObject s = shiftsArray.getJSONObject(i);
                        Shift shiftObj = new Shift();
                        shiftObj.set_id(s.getString(Constants.SHIFT_ID));
                        shiftObj.setName(s.getString(Constants.SHIFT_NAME));
                        shiftObj.setStartHour(s.getString(Constants.SHIFT_START_HOUR));
                        shiftObj.setEndHour(s.getString(Constants.SHIFT_END_HOUR));
                        shifts.add(shiftObj);
                    }
                } else {
                    //something went wrong with API response
                    isError = false;
                }


            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                isError = false;

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            isError = false;
        }

        return isError;
    }

    private boolean getVisitorDetailData(String jsonStr) {
        boolean isError = false;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting result JSON object
                JSONObject resultObj = jsonObj.getJSONArray(Constants.RESULT).getJSONObject(0);
                String statusCode = resultObj.getString(Constants.STATUS_CODE);

                if (statusCode.equals(Constants.STATUS_CODE_OK)) {

                    // Getting visitor JSON Array node
                    JSONArray visitorTypesArray = jsonObj.getJSONArray(Constants.VISITORS_TYPES);
                    // looping through All visitors objects
                    for (int i = 0; i < visitorTypesArray.length(); i++) {
                        JSONObject v = visitorTypesArray.getJSONObject(i);
                        Visitor visitorObj = new Visitor();
                        visitorObj.setAliasId(v.getString(Constants.VISITOR_ALIAS_ID));
                        visitorObj.setAgeCriteria(v.getString(Constants.VISITOR_AGE_CRITERIA));
                        visitorObj.setDescription(v.getString(Constants.VISITOR_DESCRIPTION));
                        visitorObj.setMaxAge(v.getString(Constants.VISITOR_MAX_AGE));
                        visitorObj.setMinAge(v.getString(Constants.VISITOR_MIN_AGE));
                        visitorObj.setPrice(v.getString(Constants.VISITOR_PRICE));
                        visitorObj.setTicketContenturl(v.getString(Constants.VISITOR_TICKET_CONTENT_URL));
                        visitorTypes.add(visitorObj);
                    }

                } else {
                    //something went wrong with API response
                    isError = false;
                }


            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                isError = false;

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            isError = false;
        }

        return isError;
    }

    /**
     * Method to show alert error dialog
     *
     * @param errorMsg
     */
    private void showAlertDialog(String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(errorMsg);

        // Add the ok button
        builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked cancel button
                dialog.dismiss();
            }
        });
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
