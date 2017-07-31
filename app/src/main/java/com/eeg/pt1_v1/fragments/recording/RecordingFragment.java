package com.eeg.pt1_v1.fragments.recording;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class RecordingFragment extends BaseFragment{
    private TextView mPorcentageProgress;
    private ProgressBar mProgressBarCircle;
    private TextView mChronometerRecording;
    private TextView mTotalTimeRecording;
    private ImageView mStartRecording;
    private ImageView mRestartRecording;

    private int[] mStartStopRecordingIcon = {
            R.drawable.ic_start_recording,
            R.drawable.ic_stop_recording
    };

    private long totalTimeCountInMilliSeconds = 60000; // mili-seconds
    private long everyTime = 1000; //mili-seconds
    private long timeCounter = 0;
    private CountDownTimer mCountDownTimer;
    private boolean isRecording = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;
        View rootView = inflater.inflate(R.layout.record_fragment, container, false);

        mPorcentageProgress = (TextView) rootView.findViewById(R.id.porcentage_progress);
        mProgressBarCircle = (ProgressBar) rootView.findViewById(R.id.progressbar_circle);
        mChronometerRecording = (TextView) rootView.findViewById(R.id.chronometer_recording);
        mTotalTimeRecording = (TextView) rootView.findViewById(R.id.total_time);
        mStartRecording = (ImageView) rootView.findViewById(R.id.start_recording);
        mRestartRecording = (ImageView) rootView.findViewById(R.id.restart_recording);

        mStartRecording.setOnClickListener(mStartStopRecordingListener);
        mRestartRecording.setOnClickListener(mRestartRecordingListener);

        return rootView;
    }

    private ImageView.OnClickListener mStartStopRecordingListener
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(isRecording){
                mStartRecording.setImageResource(mStartStopRecordingIcon[0]);
                stopCountDownTimer();
            }
            else {
                mStartRecording.setImageResource(mStartStopRecordingIcon[1]);
                startCountDownTimer();
            }

            isRecording = !isRecording;
        }
    };

    private ImageView.OnClickListener mRestartRecordingListener
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopCountDownTimer();
            startCountDownTimer();
        }
    };

    private void startCountDownTimer() {
        mCountDownTimer = new CountDownTimer(totalTimeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TIME","time: "+millisUntilFinished+" time2: "+timeCounter);
                mChronometerRecording.setText(hmsTimeFormatter(millisUntilFinished));
                mProgressBarCircle.setProgress((int) ((timeCounter*100)/totalTimeCountInMilliSeconds));
                mPorcentageProgress.setText((int) ((timeCounter*100)/totalTimeCountInMilliSeconds)+"%");
            }

            @Override
            public void onFinish() {
                mChronometerRecording.setText(hmsTimeFormatter(totalTimeCountInMilliSeconds));
                setProgressBarValues();
                mRestartRecording.setVisibility(View.GONE);
            }
        }.start();
        mCountDownTimer.start();
    }

    private void stopCountDownTimer() {
        mCountDownTimer.cancel();
    }

    private void setProgressBarValues() {
        mProgressBarCircle.setMax((int) totalTimeCountInMilliSeconds / 1000);
        mProgressBarCircle.setProgress((int) totalTimeCountInMilliSeconds / 1000);
    }

    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }


}
