package com.example.notification.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notification.model.Data;
import com.example.notification.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DetailRoomFragment extends Fragment {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tv_detail_subject, tv_detail_time, tv_detail_teacher, tv_detail_temp, tv_detail_moisture, tv_detail_status;
    private DatabaseReference databaseReference;
    private List<Data> dataList;
    String TAG = "DetailRoomFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailroom, container, false);

        tv_detail_temp = view.findViewById(R.id.tv_detail_temp);
        tv_detail_moisture = view.findViewById(R.id.tv_detail_moisture);

        getDetail();

        return view;
    }

    private void getDetail() {

        dataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("room");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Data data = dataSnapshot.getValue(Data.class);
                dataList.add(data);
                tv_detail_temp.setText(data.getTemp());
                tv_detail_moisture.setText(data.getMoisture());
                Log.v(TAG, data.getMoisture() + ":" + data.getTemp());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}


//        String timeCurrent = "13";
//        int timeInt = Integer.parseInt(timeCurrent) + 1;
//        String queryTime = timeCurrent + ":00 - " + timeInt + ":00";
//        // ส่งเวลามา
//        CollectionReference user = db.collection("room");
//        user.get().addOnCompleteListener(new OnCompleteListener <DocumentSnapshot> () {
//            @Override
//            public void onComplete(@NonNull Task < DocumentSnapshot > task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot doc = task.getResult();
//                    StringBuilder fields = new StringBuilder("");
//                    fields.append("Name: ").append(doc.get("Name"));
//                    fields.append("\nEmail: ").append(doc.get("Email"));
//                    fields.append("\nPhone: ").append(doc.get("Phone"));
//                    textDisplay.setText(fields.toString());
//                }
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });