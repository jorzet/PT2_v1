package com.eeg.pt1_v1.fragments.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.adapters.CalibrationCanvas;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.fragments.recording.RecordingFragment;

/**
 * Created by Jorge Zepeda Tinoco on 26/07/17.
 * */

public class CalibrationFragment extends BaseFragment {
    private Button mStartRecording;
    private TextView mSuggestion;
    private ImageView mCalibrationCanvas;
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
        mSuggestion = (TextView) rootView.findViewById(R.id.spetialist_suggestion);

        mCalibrationCanvas = (CalibrationCanvas) rootView.findViewById(R.id.image_10_20_system);
        mCalibrationCanvas.setOnTouchListener(mCalibrationListener);
        mStartRecording.setOnClickListener(mStartRecordingListener);
        //showSpetialistSuggestion();

        return rootView;
    }

    private Button.OnClickListener mStartRecordingListener
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doRecording();
        }
    };

    private View.OnTouchListener mCalibrationListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                Log.i("MyTAG: ","x: " + event.getX());
                Log.i("MyTAG ","y: " + event.getY());

                return true;
            }
            return false;
        }
    };
    private void doRecording(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_schedule, new RecordingFragment()).commit();
    }

    private void showSpetialistSuggestion(){
        String suggestions = getActivity().getIntent().getExtras().getString(Palabras.SPETIALIST_SUGGESTIONS);
        mSuggestion.setText(suggestions);
    }
}
