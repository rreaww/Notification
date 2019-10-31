package com.example.notification.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.notification.R;

import java.util.Locale;

public class CountdownFragment extends Fragment {

    TextView tv_countdown;
    Button btn_start, btn_ok, btn_stop, btn_reset;
    EditText et_setTime;
    private CountDownTimer countDownTimer;
    private long startTimeInMillis;
    private long timeLeftInMillis;
    private long endTime;
    private boolean timerRunning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_countdown = view.findViewById(R.id.tv_countdown);
        btn_start = view.findViewById(R.id.btn_start);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_stop = view.findViewById(R.id.btn_stop);
        btn_reset = view.findViewById(R.id.btn_reset);
        et_setTime = view.findViewById(R.id.et_setTime);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = et_setTime.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(getActivity(), "Plese enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                et_setTime.setText("");
            }

        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning) {
                    pauseTime();
                } else {
                    startTime();
                }

            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void setTime(long milliseconds) {
        startTimeInMillis = milliseconds;
        reset();
        closeKeyboard();
    }

    private void startTime() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updatWatch();
            }
        }.start();

        timerRunning = true;
        updatWatch();
    }

    private void pauseTime() {
        countDownTimer.cancel();
        timerRunning = false;
        updatWatch();
    }

    private void reset() {
        timeLeftInMillis = startTimeInMillis;
        updateCountDown();
        updatWatch();

    }

    private void updateCountDown() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFoematted;
        if (hours > 0) {
            timeLeftFoematted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFoematted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        tv_countdown.setText(timeLeftFoematted);
    }

    private void updatWatch() {
        if (timerRunning) {
            et_setTime.setVisibility(View.INVISIBLE);
            btn_ok.setVisibility(View.INVISIBLE);
            btn_reset.setVisibility(View.INVISIBLE);
            btn_start.setText("Pause");
        } else {
            et_setTime.setVisibility(View.VISIBLE
            );
            btn_ok.setVisibility(View.VISIBLE);
            btn_start.setText("Start");

            if (timeLeftInMillis < 1000) {
                btn_start.setVisibility(View.INVISIBLE);
            } else {
                btn_start.setVisibility(View.VISIBLE);
            }
            if (timeLeftInMillis < startTimeInMillis) {
                btn_reset.setVisibility(View.VISIBLE);
            } else {
                btn_reset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferences",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong("startTimeMillis", startTimeInMillis);
        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferences",
                Context.MODE_PRIVATE);

        startTimeInMillis = preferences.getLong("startTimeMillis", 600000);
        timeLeftInMillis = preferences.getLong("millisLeft", startTimeInMillis);
        timerRunning = preferences.getBoolean("timerRunning", false);

        updateCountDown();
        updatWatch();

        if (timerRunning) {
            endTime = preferences.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();

            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDown();
                updatWatch();
            } else {
                startTime();
            }
        }
    }
}


