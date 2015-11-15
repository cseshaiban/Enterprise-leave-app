package com.incture.leaveme.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.AdapterManagerApproval;
import com.incture.leaveme.data.ApprovalData;

import java.util.ArrayList;

/**
 * Created by SHAIBAN.N on 03-09-2015.
 */
public class Approvals extends AppCompatActivity {

    Context context;
    RecyclerView rv;
    ArrayList<ApprovalData> data= new ArrayList<ApprovalData>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_approvals);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Approvals");

        //Set the custom toolbar as the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton b;
        b=(FloatingActionButton)findViewById(R.id.fabBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Approvals.this, ApplyLeavePage.class);
                startActivity(i);
            }
        });
        rv = (RecyclerView)findViewById(R.id.ApproverList);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        data.add(new ApprovalData(R.drawable.shaiban, "Mohammed Shaiban", "md.shaiban@incture.com",
                "0", "NOV", "2015", "MON", "SESSION 2", "18", "NOV", "2015", "WED", "SESSION 1", "Casual Leave", "Attending Cousins wedding this thursdays " +
                "on Andamon Nicobar. Cannot miss you. Thank you. Hoping for acceptance for leave", ""));

        data.add(new ApprovalData(R.drawable.shaiban,"Vikram Thomas","md.shaiban@incture.com",
                "1","NOV","2015","MON","SESSION 2","18","NOV","2015","WED","SESSION 1","Sick Leave","Attending Cousins wedding this thursdays " +
                "on Andamon Nicobar. Cannot miss you. Thank you. Hoping for acceptance for leave",""));
        data.add(new ApprovalData(R.drawable.shaiban, "Vikram Thomas", "md.shaiban@incture.com",
                "2", "NOV", "2015", "MON", "SESSION 2", "18", "NOV", "2015", "WED", "SESSION 1", "Sick Leave", "Attending Cousins wedding this thursdays " +
                "on Andamon Nicobar. Cannot miss you. Thank you. Hoping for acceptance for leave", ""));
        data.add(new ApprovalData(R.drawable.shaiban,"Vikram Thomas","md.shaiban@incture.com",
                "3","NOV","2015","MON","SESSION 2","18","NOV","2015","WED","SESSION 1","Sick Leave","Attending Cousins wedding this thursdays " +
                "on Andamon Nicobar. Cannot miss you. Thank you. Hoping for acceptance for leave",""));

        AdapterManagerApproval adapter = new AdapterManagerApproval(data,  new AdapterManagerApproval.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemClickReject(View view, int position) {
            }

            @Override
            public void onItemClickShow(View view, int position) {
            }

        });
        rv.setAdapter(adapter);





     /*   context=this;
        try {
            Log.d("LEAVE", "inside URI");
            //   URL uri = new URL("http://172.31.99.106:8000/leave-history");
            URL uri = new URL("http://172.16.11.84:8000/approve");
            // new LeaveHistoryAsyncTask(uri, ctx).execute();

            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
                new ManagerLeaveApprovalAsyncTask(uri,context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            else
                new ManagerLeaveApprovalAsyncTask(uri,context).execute();


            Log.d("LEAVE", "inside after URi");
        }catch (MalformedURLException e) {e.printStackTrace();
        }
*/




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
