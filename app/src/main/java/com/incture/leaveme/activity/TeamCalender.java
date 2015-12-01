package com.incture.leaveme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.TeamAdapter;
import com.incture.leaveme.data.TeamCalendarItem;

import java.util.ArrayList;

/**
 * Created by Mohammed on 11/16/2015.
 */
public class TeamCalender  extends AppCompatActivity {

    ArrayList<TeamCalendarItem> data = new ArrayList<TeamCalendarItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_calender);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Team Calendar");

        data.add(new TeamCalendarItem(R.drawable.shaiban, "Mohammed Shaiban", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Aanchal Tiwari", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Anagha K A", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Dikshitha KR", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Pawan Gopal", "no"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Shridhar Joshi", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Vikram Thomas", "yes"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Ravikiran Papthimar", "no"));
        data.add(new TeamCalendarItem(R.drawable.shaiban, "Aadithya B", "yes"));

        RecyclerView rv = (RecyclerView) findViewById(R.id.teamCal);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        TeamAdapter adapter = new TeamAdapter(data);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}