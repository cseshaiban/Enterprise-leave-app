package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.AdapterManagerApproval;
import com.incture.leaveme.data.ApprovalData;
import com.incture.leaveme.helper.HTTPDataHandlerForApproval;
import com.incture.leaveme.utility.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mohammed on 11/11/2015.
 */
public class ManagerLeaveApprovalAsyncTask extends AsyncTask<Void,Void,String> {
    URL url;
    Context context;
    RecyclerView rv;
    ArrayList<ApprovalData> data= new ArrayList<ApprovalData>();

    public ManagerLeaveApprovalAsyncTask(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("LEAVE", "onPreExecute");
    }

    @Override
    protected String doInBackground(Void... params) {

        String stream = null;
        String urlString = url.toString();
        HTTPDataHandlerForApproval hh = new HTTPDataHandlerForApproval();
        stream = hh.GetHTTPData(urlString);
        return stream;
    }

    @Override
    protected void onPostExecute(final String o) {
        Log.d("LEAVE","onPostExecute");
        if (o != null) {
            try {
                Log.d("LEAVE", "onPostExecute inside try");
                JSONObject reader = new JSONObject(o);
                JSONArray jarray = reader.getJSONArray("data");

                for(int i=0;i<jarray.length();i++){

                    Log.d("LEAVE","onPostExecute inside try for loop");
                    JSONObject object = jarray.getJSONObject(i);

                    //Start date
                    JSONObject from = object.getJSONObject("from");
                    String fromDate= from.getString("day");
                    String ssession=from.getString("session");
                    DateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    Date fDate = fDateFormat.parse(fromDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fDate);
                    String smonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase().toString();
                    smonth = smonth.length() > 3 ? smonth.substring(0, 3) : smonth;


                    String sdate = TimeUtils.getDateMonthFromDate(fromDate);
                    String[] daysArray = new String[] { "NULL","SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT" };
                    String sday = daysArray[cal.get(Calendar.DAY_OF_WEEK)];

                    Log.d("LEAVE","onPostExecute inside try for loop fromDate :"+fromDate);

                    //Enddate
                    JSONObject to = object.getJSONObject("to");
                    String esession=to.getString("session");

                    fromDate= to.getString("day");
                    fDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    fDate = fDateFormat.parse(fromDate);
                    cal = Calendar.getInstance();
                    cal.setTime(fDate);

                    String emonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase().toString();

                    emonth = emonth.length() > 3 ? emonth.substring(0, 3) : emonth;


                  /*  switch (emonth){
                        case 'january' : emonth= "JAN";
                             break;
                        case "february" :
                    }*/
                    String edate = TimeUtils.getDateMonthFromDate(fromDate);
                    daysArray = new String[] { "NULL","SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
                    String eday = daysArray[cal.get(Calendar.DAY_OF_WEEK)];

                    Log.d("LEAVE", "onPostExecute inside try for loop toDate :" + fromDate);

                    JSONObject phone = object.getJSONObject("applier");
                    String name = phone.getString("displayName");
                    String email = phone.getString("email");

                    String type= object.getString("type");
                    String reason= object.getString("reason");
                    String responseId= object.getString("id");

                    Log.d("LEAVE","type and reason"+type + " " + reason);

                    data.add(new ApprovalData(R.drawable.shaiban, name, email, sdate, smonth, "2015", sday, ssession, edate, emonth, "2015", eday, esession, type, reason, responseId));

                    Log.d("LEAVE", "FINALLY DATA ADDED");
                }

                rv = (RecyclerView)((AppCompatActivity)context).findViewById(R.id.ApproverList);
                rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
                AdapterManagerApproval adapter = new AdapterManagerApproval(data,  new AdapterManagerApproval.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        ApprovalData oList;
                        oList = data.get(position);

                        JSONObject requestObject = new JSONObject();
                        try {
                            Boolean APPROVE_STATUS= true;
                            requestObject.put("decision",APPROVE_STATUS);
                            requestObject.put("id",oList.getResponseId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            URL requestUrl = new URL(ServerDetails.APPLY_APPROVE);
                            ApplyApproveAsyncTask applyLeaveAsyncTask = new ApplyApproveAsyncTask(context, requestUrl);
                            applyLeaveAsyncTask.execute(requestObject);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onItemClickShow(View view, int position) {

                    }

                    @Override
                    public void onItemClickReject(View view, int position) {

                        ApprovalData oList;
                        oList = data.get(position);

                        JSONObject requestObject = new JSONObject();
                        try {
                            Boolean APPROVE_STATUS= false;
                            requestObject.put("decision",APPROVE_STATUS);
                            requestObject.put("id",oList.getResponseId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            URL requestUrl = new URL(ServerDetails.APPLY_APPROVE);
                            ApplyApproveAsyncTask applyLeaveAsyncTask = new ApplyApproveAsyncTask(context, requestUrl);
                            applyLeaveAsyncTask.execute(requestObject);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                    }
                    });
                rv.setAdapter(adapter);


            }catch (JSONException e){

            }catch (ParseException e){

            }


        }

    }
}
