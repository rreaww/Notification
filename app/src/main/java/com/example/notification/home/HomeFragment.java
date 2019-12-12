package com.example.notification.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notification.AddroomFragment;
import com.example.notification.model.Data;
import com.example.notification.R;
import com.example.notification.SelectFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private Button btn_back, btn_addroom, btn_setting;
    private RecyclerView recycler_home;
    private List<Data> dataList;
    private HomeAdapter homeAdapter;
    private DatabaseReference databaseReference;
    String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recycler_home = view.findViewById(R.id.recycler_home);
        btn_back = view.findViewById(R.id.btn_back);
        btn_addroom = view.findViewById(R.id.btn_addroom);
        btn_setting = view.findViewById(R.id.btn_setting);

        setDataList();
        binding();
        setBtn_back();
        setBtn_addroom();
//        genKey();
        return view;
    }

    private void setDataList() {

        dataList = new ArrayList<>();

        recycler_home.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_home.setLayoutManager(linearLayoutManager);

        homeAdapter = new HomeAdapter(dataList);
        recycler_home.setAdapter(homeAdapter);
    }

    private void setBtn_back() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newfragment = new SelectFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_login, newfragment).commit();
            }
        });
    }

    private void setBtn_addroom() {

        btn_addroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newfragment = new AddroomFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_login, newfragment).commit();
            }
        });
    }

    private void binding() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("room");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Data data = dataSnapshot.getValue(Data.class);
                dataList.add(data);
                Log.v(TAG, dataSnapshot.getKey());
                Log.v(TAG, data.getMoisture());
                homeAdapter.notifyDataSetChanged();
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

    private void genKey() {

        String key = databaseReference.push().getKey();
        databaseReference.child(key).child("test").setValue("5555");
        Log.v(TAG, key);
    }
}

