package com.incture.leaveme.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.incture.leaveme.DataHandle.ServerDetails;
import com.incture.leaveme.R;
import com.incture.leaveme.helper.HTTPDataHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JANANI.N on 03-09-2015.
 */
public class Landing_page extends AppCompatActivity {
    Button b1,bb;

    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private static final String NAV_ITEM_ID = "navItemId";

    private DrawerLayout mDrawerLayout;
    private int mNavItemId;
    private ActionBarDrawerToggle mDrawerToggle;

    CalendarView cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        pagesetup();

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing_page.this, ApplyLeavePage.class);
                startActivity(intent);
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        RelativeLayout l1=(RelativeLayout)findViewById(R.id.l1);
        RelativeLayout l2=(RelativeLayout)findViewById(R.id.l2);
        l1.getLayoutParams().width=width/2;
        l2.getLayoutParams().width=width/2;

      /*  b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Landing_page.this, Holiday_Calendaar.class);
                startActivity(i);
            }

        });

        bb = (Button) findViewById(R.id.button2);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Landing_page.this, Leave_history.class);
                startActivity(i);

            }

        });*/



        cal = (CalendarView) findViewById(R.id.calendarView);
        cal.setShowWeekNumber(false);

//        // Get the Drawable custom_progressbar
//        Drawable draw=getResources().getDrawable(R.drawable.custom_progressbar);
//// set the drawable as progress drawable
//        ProgressBar progressBar =(ProgressBar)findViewById(R.id.progressBar1);
//        progressBar.setProgressDrawable(draw);

        // cal.setMinDate(min.getTime());


       /* NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.drawer_item_5:
                        Intent i = new Intent(Landing_page.this, Holiday_Calendaar.class);
                        startActivity(i);
                        return true;
                *//*    case R.id.drawer_item_6:
                        Intent i2 = new Intent(Landing_page.this, Leave_history.class);
                        startActivity(i2);
                        return true;*//*
                }
                return false;
            }
        });
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        new  LeaveRecord_AsyncTask().execute();
    }

    public void pagesetup(){

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Leave");

        //Set the custom toolbar as the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */

                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //  getSupportActionBar().setTitle("Open");

            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Close");

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        ((NavigationView)findViewById(R.id.navigation_view1)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.drawer_item_1:
                        Intent i = new Intent(Landing_page.this, Landing_page.class);
                        startActivity(i);
                        return true;
                    case R.id.drawer_item_2:
                        Intent i1 = new Intent(Landing_page.this, ApplyLeavePage.class);
                        startActivity(i1);
                        return true;
                    case R.id.drawer_item_3:
                        Intent i2 = new Intent(Landing_page.this, Notifications.class);
                        startActivity(i2);
                        break;
                    case R.id.drawer_item_4:
                        Intent i3 = new Intent(Landing_page.this, Reports.class);
                        startActivity(i3);
                        break;
                    case R.id.drawer_item_5:
                        Intent i4 = new Intent(Landing_page.this, Holiday_Calendaar.class);
                        startActivity(i4);
                        break;
                    case R.id.drawer_item_6:
                        Intent i6 = new Intent(Landing_page.this, Leave_history.class);
                        startActivity(i6);
                        break;
                }
                return false;
            }

        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    class LeaveRecord_AsyncTask extends AsyncTask<String, Void, String> {
        private String urlstring = ServerDetails.LEAVE_SUMMARY;

        Context ctx = getApplicationContext();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String stream = null;
            String urlString = urlstring;
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

        protected void onPostExecute(String stream){
            String availVal= null, availableVal=null;
            double[] totalProgress = new double[3], availedProgress = new double[3];
            int[] totalInt = new int[3], availedInt = new int[3];

            Log.i("JSON", " onPostExecute ");
            //..........Process JSON DATA................
            if(stream !=null){
                try{
                    Log.i("JSON", " stream got data "+stream);
                    JSONObject reader= new JSONObject(stream);

                    JSONObject coord = reader.getJSONObject("data");

                    availVal = coord.getString("availed");

                    availableVal = coord.getString("available");


                    JSONArray jsonArray = coord.optJSONArray("category");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        totalProgress[i] = jsonObject.optDouble("total");
                        availedProgress[i] = jsonObject.optDouble("availed");


                        totalInt[i] = (int) (totalProgress[i]*2);
                        availedInt[i] = (int)(availedProgress[i]*2);


                    }

                    Log.i("JSON", " stream got data " + stream);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }

                Typeface typeface= Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
                Typeface ctypeface= Typeface.createFromAsset(getAssets(), "Roboto-Condensed.ttf");

                TextView availed = (TextView)findViewById(R.id.availed);
                availed.setText(availVal);
                availed.setTypeface(typeface);

                TextView availedtext = (TextView)findViewById(R.id.availedtext);
                availedtext.setTypeface(typeface);

                TextView available = (TextView)findViewById(R.id.available);
                available.setText(availableVal);
                available.setTypeface(typeface);

                TextView availabletext = (TextView)findViewById(R.id.availabletext);
                availabletext.setTypeface(typeface);

             /*   RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.progressBar1);
            //    progress1.setProgressColor(Color.parseColor("#0000ff"));
            //    progress1.setBackgroundColor(Color.parseColor("#ffffff"));
                progress1.setMax(totalProgress);
                progress1.setProgress(availedProgress);

                TextView textView = (TextView)findViewById(R.id.progresstext1);
                textView.setText("" + availedProgress + "/" + totalProgress);

                RoundCornerProgressBar progress2 = (RoundCornerProgressBar) findViewById(R.id.progressBar2);
                progress2.setMax(8);
                progress2.setProgress(5);

                TextView textView2 = (TextView)findViewById(R.id.progresstext2);
                textView2.setText("" + availedProgress + "/" + totalProgress);

                RoundCornerProgressBar progress3 = (RoundCornerProgressBar) findViewById(R.id.progressBar3);
             //   progress3.setProgressColor(Color.parseColor("#0000ff"));
              //  progress3.setBackgroundColor(Color.parseColor("#ffffff"));
                progress3.setMax(7);
                progress3.setProgress(6);

                TextView textView3 = (TextView)findViewById(R.id.progresstext3);
                textView3.setText("" + availedProgress + "/" + totalProgress);*/

                //Arjuns

                RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.progressBar1);
                progress1.setMax(totalInt[0]);
                progress1.setProgress(availedInt[0]);

                TextView textView = (TextView)findViewById(R.id.progresstext1);
                String text = ""+availedProgress[0]+"/"+totalProgress[0];
                textView.setText(text);


                progress1 = (RoundCornerProgressBar) findViewById(R.id.progressBar2);
                progress1.setMax(totalInt[1]);
                progress1.setProgress(availedInt[1]);

                textView = (TextView)findViewById(R.id.progresstext2);
                text = ""+availedProgress[1]+"/"+totalProgress[1];
                textView.setText(text);

                progress1 = (RoundCornerProgressBar) findViewById(R.id.progressBar4);
                progress1.setMax(totalInt[2]);
                progress1.setProgress(availedInt[2]);

                textView = (TextView)findViewById(R.id.progresstext4);
                text = ""+availedProgress[2]+"/"+totalProgress[2];
                textView.setText(text);



            } // if statement end
        } // onPostExecute() end
    }



}
