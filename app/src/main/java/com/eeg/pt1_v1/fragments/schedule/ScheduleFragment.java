package com.eeg.pt1_v1.fragments.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

/**
 * Created by ing_ragde on 09/07/17.
 */

public class ScheduleFragment extends BaseFragment{
    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.schedule_fragment, container, false);

        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        dateDisplay = (TextView) rootView.findViewById(R.id.date_display);



        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }
}
