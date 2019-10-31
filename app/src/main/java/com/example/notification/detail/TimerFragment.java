package com.example.notification.detail;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.notification.R;

public class TimerFragment extends Fragment {


    Button btn_start, btn_stop, btn_reset;
    Chronometer chronometer_timer;
    ImageView icanchor;
    ObjectAnimator animation;
    private long pauseOffset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chronometer_timer = (Chronometer) view.findViewById(R.id.chronometer_timer);
        btn_start = (Button) view.findViewById(R.id.btn_start);
        btn_stop = (Button) view.findViewById(R.id.btn_stop);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        icanchor = (ImageView) view.findViewById(R.id.running);
        animation = ObjectAnimator.ofFloat(icanchor, "rotation", 0, 360);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //anim
                animation.setDuration(4000);
                animation.setRepeatCount(ObjectAnimator.INFINITE);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.start();
                //time
                chronometer_timer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                chronometer_timer.start();
                //btn
                btn_start.setText("Resume");
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //anim
                animation.pause();
                //time
                chronometer_timer.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chronometer_timer.getBase();


            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //anim
                animation.end();
                //time
                chronometer_timer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                //btn
                btn_start.setText("Start");
            }
        });


    }

}

