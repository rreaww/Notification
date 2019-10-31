package com.example.notification.room;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.notification.detail.CountdownFragment;
import com.example.notification.detail.DetailRoomFragment;
import com.example.notification.R;
import com.example.notification.detail.TimerFragment;

public class RoomAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public RoomAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailRoomFragment Room = new DetailRoomFragment();
                return Room;
            case 1:
                TimerFragment Timer = new TimerFragment();
                return Timer;
            case 2:
                CountdownFragment Countdown = new CountdownFragment();
                return Countdown;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Room";
            case 1:
                return "Timer";
            case 2:
                return "Countdown";
        }
        return null;
    }
}
