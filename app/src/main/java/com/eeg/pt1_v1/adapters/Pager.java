package com.eeg.pt1_v1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eeg.pt1_v1.fragments.home.HomeFragment;
import com.eeg.pt1_v1.fragments.profile.ProfileFragment;
import com.eeg.pt1_v1.fragments.recording.RecordingFragment;
import com.eeg.pt1_v1.fragments.schedule.ScheduleFragment;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class Pager extends FragmentStatePagerAdapter{

    private int mTabCount;
    public boolean isMEdic = true;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.mTabCount= tabCount;
    }

    public void getCurrentUser(){
        //Using shared preferences

    }

    @Override
    public Fragment getItem(int position) {
        if(isMEdic) {
            switch (position) {
                case 0:
                    return new ProfileFragment();
                case 1:
                    return new ScheduleFragment();
                case 2:
                    return new ScheduleFragment();
                default:
                    return null;
            }
        }
        else{
            switch (position) {
                case 0:
                    return new ProfileFragment();
                case 1:
                    return new RecordingFragment();
                case 2:
                    return new ScheduleFragment();
                default:
                    return null;
            }
        }

    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
