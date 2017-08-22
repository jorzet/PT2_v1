package com.eeg.pt1_v1.fragments.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.fragments.recording.RecordingFragment;

/**
 * Created by Jorge Zepeda Tinoco on 26/07/17.
 * */

public class CalibrationFragment extends BaseFragment {
    private Button mStartRecording;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;
        View rootView = inflater.inflate(R.layout.calibration_fragment, container, false);
        mStartRecording = (Button) rootView.findViewById(R.id.start_recording);
        mStartRecording.setOnClickListener(mStartRecordingListener);
        return rootView;
    }

    private Button.OnClickListener mStartRecordingListener
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doRecording();
        }
    };

    private void doRecording(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_schedule, new RecordingFragment()).commit();
    }
}
