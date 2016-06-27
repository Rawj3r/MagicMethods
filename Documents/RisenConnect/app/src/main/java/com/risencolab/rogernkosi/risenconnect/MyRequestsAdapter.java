package com.risencolab.rogernkosi.risenconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyRequestsAdapter extends RecyclerView.Adapter<MyRequestsAdapter.MyViewHolder> {

    private Context mContext;
    private List<RequestModel> requestList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sender, content;
        LinearLayout itemCard;

        public MyViewHolder(View view) {
            super(view);

            sender = (TextView) view.findViewById(R.id.request_sender);
            content = (TextView) view.findViewById(R.id.request_content);
            itemCard = (LinearLayout) view.findViewById(R.id.list_layout);
        }
    }

    public MyRequestsAdapter(Context mContext, List<RequestModel> requestList) {
        this.mContext = mContext;
        this.requestList = requestList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("onCreate", "Viewholder" );

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_timerequest, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final RequestModel request = requestList.get(position);
        Log.d("requestContent", request.getRequestContent());
        holder.sender.setText(request.getSenderUsername());
        holder.content.setText(request.getRequestContent());
        holder.itemCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RateMember.class);
                i.putExtra("requestID", request.getRequestID());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }
}
