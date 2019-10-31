package com.example.notification.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notification.R;
import com.example.notification.room.RoomActivity;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    private String[] mDataSet;

    class Holder extends RecyclerView.ViewHolder {

        TextView tv_room, tv_time;

        public Holder(View itemView) {
            super(itemView);
            tv_room = itemView.findViewById(R.id.tv_room);
            tv_time = itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), RoomActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void setItem(int position) {
            tv_room.setText(mDataSet[position]);
            tv_time.setText("index = " + position);
        }

    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setItem(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public HomeAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }


}
