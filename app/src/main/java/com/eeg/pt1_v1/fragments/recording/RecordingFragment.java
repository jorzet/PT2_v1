package com.eeg.pt1_v1.fragments.recording;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.ui.activities.ContentActivity;

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

    private String mTime;
    private long totalTimeCountInMilliSeconds = 60000; // mili-seconds
    private long everyTime = 1000; //mili-seconds
    private long timeCounter = 0;
    private CountDownTimer mCountDownTimer;
    private boolean isRecording = false;
    private boolean isNotificationAble = false;

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

    @Override
    public void onPause() {
        super.onPause();
        isNotificationAble = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        isNotificationAble = false;
        NotificationManager notificationManager = (NotificationManager) getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_chronometer_notification)
                        .setContentTitle("Tiempo restante: "+mTime)
                        .setContentText("Total de tiempo: ");

        Intent notificationIntent = new Intent(getContext(), getActivity().getClass());
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
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
                mTime = hmsTimeFormatter(millisUntilFinished);
                mChronometerRecording.setText(mTime);
                mProgressBarCircle.setProgress((int) ((millisUntilFinished*100)/totalTimeCountInMilliSeconds));
                mPorcentageProgress.setText((int) ((millisUntilFinished*100)/totalTimeCountInMilliSeconds)+"%");

                if(isNotificationAble)
                    addNotification();
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
