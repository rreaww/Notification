package com.example.notification.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notification.R;
import com.example.notification.SelectFragment;
import com.example.notification.home.HomeAdapter;

public class HomeFragment extends Fragment {

    View view;
    Button btn_back, btn_addroom, btn_setting;
    RecyclerView recycler_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        recycler_home = view.findViewById(R.id.recycler_home);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_back = view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newfragment = new SelectFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_login, newfragment).commit();
            }
        });

//        btn_addroom = (Button) view.findViewById(R.id.btn_addroom);
//        btn_addroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), BarActivity.class);
//                startActivity(intent);
//            }
//        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_home.setLayoutManager(layoutManager);

        String[] strings = {"16301","16302","16303","16304"}; //ข้อความอะไรก็ได้
        HomeAdapter adapter = new HomeAdapter(strings);
        recycler_home.setAdapter(adapter);

    }
}

