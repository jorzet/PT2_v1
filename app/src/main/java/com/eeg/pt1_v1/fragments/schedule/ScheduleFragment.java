package com.eeg.pt1_v1.fragments.schedule;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;


/**
 * Created by Jorge Zepeda Tinoco on 26/07/17.
 */

public class ScheduleFragment extends BaseFragment{
    private Button mTestButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return null;

        View rootView = inflater.inflate(R.layout.schedule_fragment, container,false);

        mTestButton = (Button) rootView.findViewById(R.id.test_button);
        mTestButton.setOnClickListener(mTestListener);
        return rootView;
    }

    private Button.OnClickListener mTestListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            doTest();
        }
    };

    private void doTest(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_schedule, new CalibrationFragment()).commit();
    }

}
