package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.notification.home.HomeFragment;

public class LoginFragment extends Fragment {

    EditText et_username , et_password;
    Button btn_back, btn_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);
        btn_login = view.findViewById(R.id.btn_login);
        btn_back = view.findViewById(R.id.btn_back);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SelectFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_login, fragment).commit();
            }
        });
    }

    private void login(){
        if(et_username.getText().toString().equals("cpe59146") && et_password.getText().toString().equals("0000")){
            Fragment fragment = new HomeFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout_login, fragment).commit();
        }else {
            Toast.makeText(getActivity(),"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_LONG).show();
        }
    }
}

