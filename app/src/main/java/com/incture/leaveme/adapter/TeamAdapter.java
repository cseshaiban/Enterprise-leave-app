package com.incture.leaveme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.leaveme.R;
import com.incture.leaveme.data.TeamCalendarItem;

import java.util.ArrayList;

/**
 * Created by Mohammed on 11/16/2015.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> implements View.OnClickListener {
    static Context context;
    ArrayList<TeamCalendarItem> nList;
    LayoutInflater layoutInflater;
    static int viewType=0;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView name;
        public final ImageView image,status;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            name = (TextView) view.findViewById(R.id.name);
            status=(ImageView)view.findViewById(R.id.status);
            image= (ImageView)view.findViewById(R.id.image);

        /*    Typeface typeface_regular= Typeface.createFromAsset(context.getAssets(),"Roboto-Bold.ttf");
            monthtext.setTypeface(typeface_regular);

            Typeface typeface= Typeface.createFromAsset(context.getAssets(),"Roboto-Regular.ttf");
            hdesc.setTypeface(typeface);
            htype.setTypeface(typeface);
            hnumday.setTypeface(typeface);*/
        }
    }

    public TeamAdapter(ArrayList<TeamCalendarItem> items) {
        nList = items;
    }

    @Override
    public void onClick(View view) {

//        Log.d("EXPAND", "inside onClick start");
//
//        ViewHolder holder = (ViewHolder) view.getTag();
//        //   String theString = nList.get(holder.getAdapterPosition());
//
//        // Check for an expanded view, collapse if you find one
//        if (expandedPosition >= 0) {
//            int prev = expandedPosition;
//            notifyItemChanged(prev);
//
//            Log.d("EXPAND", "inside onClick expandPosition > 0");
//        }
//        // Set the current position to "expanded"
//        expandedPosition = holder.getAdapterPosition();
//        notifyItemChanged(expandedPosition);
//
//        Log.d("EXPAND", "inside onClick end");

    }


    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
        this.viewType = viewType;

        View view = new View(context);
        view = layoutInflater.inflate(R.layout.team_calendar_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
      //  viewHolder.itemView.setOnClickListener(TeamAdapter.this);
      //  viewHolder.itemView.setTag(viewHolder);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TeamCalendarItem oList;
        oList = nList.get(position);

        holder.name.setText(oList.getName());
        if(oList.getAvail().equalsIgnoreCase("no")){
            holder.status.setVisibility(View.GONE);
        }



    }
    @Override
    public int getItemCount() {
        return nList.size();
    }


}
