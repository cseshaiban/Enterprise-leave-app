package com.incture.leaveme.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.incture.leaveme.R;
import com.incture.leaveme.activity.TeamCalender;
import com.incture.leaveme.data.ApprovalData;

import java.util.ArrayList;

/**
 * Created by Mohammed on 11/10/2015.
 */
public class AdapterManagerApproval  extends RecyclerView.Adapter<AdapterManagerApproval.ViewHolder> implements View.OnClickListener  {

    static Context context;
    ArrayList<ApprovalData> nList;
    private OnItemClickListener mOnItemClickListener;
    LayoutInflater layoutInflater;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemClickShow(View view, int position);
        public void onItemClickReject(View view, int position);
        public void onItemClickTeamCalendar(View view, int position);

    }
    private int expandedPosition = -1;
    Boolean expandFlag=false;

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);
        }
        expandedPosition = holder.getAdapterPosition();
        notifyItemChanged(expandedPosition);
        expandFlag=true;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public final View mView;
        public final TextView name,email,sdate,smonth,syear,sday,edate,emonth,eyear,eday,ssession,esession,leavetype,description,approveButton,rejectButton;
        public final LinearLayout ll,l2;
        public final RelativeLayout r1;
        public final ImageView imageViewShow;
        public final Button teamCal;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = (TextView) view.findViewById(R.id.name);
            email=(TextView)view.findViewById(R.id.email);

            sdate=(TextView)view.findViewById(R.id.sdate);
            smonth=(TextView)view.findViewById(R.id.smonth);
            syear=(TextView)view.findViewById(R.id.syear);
            sday=(TextView)view.findViewById(R.id.sday);
            ssession=(TextView)view.findViewById(R.id.ssession);

            edate=(TextView)view.findViewById(R.id.edate);
            emonth=(TextView)view.findViewById(R.id.emonth);
            eyear=(TextView)view.findViewById(R.id.eyear);
            eday=(TextView)view.findViewById(R.id.eday);
            esession=(TextView)view.findViewById(R.id.esession);

            leavetype=(TextView)view.findViewById(R.id.type);
            description=(TextView)view.findViewById(R.id.description);

            approveButton=(TextView)view.findViewById(R.id.approveButton);
            rejectButton=(TextView)view.findViewById(R.id.rejectButton);

            teamCal=(Button)view.findViewById(R.id.teamCalendar);

            ll=(LinearLayout)view.findViewById(R.id.expandCollapseLayout);
            l2=(LinearLayout)view.findViewById(R.id.expandCollapseLayoutHideInitial);

            r1=(RelativeLayout)view.findViewById(R.id.scrollUpLayout);
            imageViewShow=(ImageView)view.findViewById(R.id.scrollUpImageview);

/*
            Typeface typeface_regular= Typeface.createFromAsset(context.getAssets(),"Roboto-Bold.ttf");
            sday.setTypeface(typeface_regular);
            eday.setTypeface(typeface_regular);

            Typeface typeface_regular_thin= Typeface.createFromAsset(context.getAssets(),"Roboto-Thin.ttf");
            sdate.setTypeface(typeface_regular_thin);
            edate.setTypeface(typeface_regular_thin);*/

          /*  Typeface typeface= Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
            hdesc.setTypeface(typeface);
            htype.setTypeface(typeface);
            hnumday.setTypeface(typeface);*/
        }

    }

    private void deleteApproved(int position){

        try {
            Log.d("DELETE", "Position " + position);
            nList.remove(position);
            notifyDataSetChanged();
            Log.d("DELETE", "deleteApproved ");


        }catch (ArrayIndexOutOfBoundsException e){

        }catch (NullPointerException e){

        }catch (IndexOutOfBoundsException e){

        }

    }



    public AdapterManagerApproval(ArrayList<ApprovalData> items, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        nList = items;
    }


    @Override
    public AdapterManagerApproval.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
        View view = new View(context);
        view = layoutInflater.inflate(R.layout.content_approvals_item, parent, false);
          /*      break;
        }*/
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(AdapterManagerApproval.this);
        viewHolder.itemView.setTag(viewHolder);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

            final ApprovalData oList;
            oList = nList.get(position);

            holder.name.setText(oList.getName());
            holder.email.setText(oList.getEmail());
            holder.sdate.setText(oList.getSdate());
            holder.smonth.setText(oList.getSmonth());
            holder.syear.setText(oList.getSyear());
            holder.sday.setText(oList.getSday());
            holder.ssession.setText(oList.getSsession());

            holder.edate.setText(oList.getEdate());
            holder.emonth.setText(oList.getEmonth());
            holder.eyear.setText(oList.getEyear());
            holder.eday.setText(oList.getEday());

        //    holder.esession.setText(""+position);
            holder.esession.setText(oList.getEsession());

            holder.leavetype.setText(oList.getLeavetype());
            holder.description.setText(oList.getDescription());

            holder.approveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            deleteApproved(position);
                            Toast.makeText(context, "Leave Approved", Toast.LENGTH_LONG).show();

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

              /*  oList.remove(holder.getAdapterPosition());
                recyclerview.removeViewAtIndex(holder.getAdapterPosition());
                notifyDataSetChanged();
                adapter.notifyItemRemoved(holder.getAdapterPosition());*/
                }
            });

            holder.rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickReject(v, position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Reject");
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            deleteApproved(position);
                            Toast.makeText(context, "Leave Rejected", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

        holder.teamCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickTeamCalendar(v, position);
                Intent i = new Intent(context,TeamCalender.class);
                context.startActivity(i);
            }
        });


      /*  holder.cancelRevoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
                holder.expandCollapseLinearLayout.setVisibility(View.GONE);
            }
        });*/

            if (position == expandedPosition) {
                holder.l2.setVisibility(View.VISIBLE);
                holder.r1.setVisibility(View.VISIBLE);
                Log.d("EXPAND", "inside onBindVuewHolder if(position == expandedPosition) ");
            } else {
                holder.l2.setVisibility(View.GONE);
                holder.r1.setVisibility(View.GONE);
                Log.d("EXPAND", "inside onBindVuewHolder else of if(position == expandedPosition) ");
            }

            holder.r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickShow(v, position);
                    holder.l2.setVisibility(View.GONE);
                    holder.r1.setVisibility(View.GONE);
                }
            });

    }


    @Override
    public int getItemCount() {
        return nList.size();
    }


}
