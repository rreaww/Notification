package com.example.notification.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notification.model.Data;
import com.example.notification.R;
import com.example.notification.room.RoomActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    private List<Data> dataList;
    String TAG = "HomeAdapter";

    public HomeAdapter(List<Data> result) {
        this.dataList = result;
    }

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

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Map map = (Map) dataSnapshot.getValue();
                    String value = String.valueOf(map.get("realtime"));
                    tv_time.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
        Data data = dataList.get(position);
        holder.tv_room.setText(String.valueOf(data.getRoomNumber()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
