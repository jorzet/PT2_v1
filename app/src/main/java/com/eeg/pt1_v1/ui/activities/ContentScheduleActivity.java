package com.eeg.pt1_v1.ui.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.fragments.bluetooth.BluetoothConnectionFragment;
import com.eeg.pt1_v1.fragments.recording.RecordingFragment;
import com.eeg.pt1_v1.fragments.schedule.ScheduleFragment;
import com.eeg.pt1_v1.fragments.schedule.SchedulesFragment;
import com.eeg.pt1_v1.services.database.InfoHandler;


/**
 * Created by Jorge Zepeda Tinoco on 24/07/17.
 */

public class ContentScheduleActivity extends BaseActivityLifecycle{
    private ImageView mBackButton;
    private ImageView mRoundedDate;
    private TextView mDate;
    private Toolbar mToolbar;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_schedule);

        mBackButton = (ImageView) findViewById(R.id.arrow_back);
        mRoundedDate = (ImageView) findViewById(R.id.rounded_date_schedule_container);
        mDate = (TextView) findViewById(R.id.date_container_schedule);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_container_schedule);

        mBackButton.setOnClickListener(backAction);


        InfoHandler myHandler = new InfoHandler(getApplicationContext());


        String[] date = myHandler.getExtraStored(SchedulesFragment.DATE_TEXT).split(" ");
        mDate.setText(date[1]);

        mContext = getApplicationContext();
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(myHandler.getExtraStored(SchedulesFragment.DATE_COLOR));
        TextDrawable drawable = TextDrawable.builder().buildRound(date[0], color);

        mRoundedDate.setImageDrawable(drawable);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.WHITE, color);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int color = (int) animator.getAnimatedValue();
                mToolbar.setBackgroundColor(color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(color);
                }
            }
        });
        colorAnimation.start();
        mToolbar.setBackgroundColor(color);

        Bundle extras = getIntent().getExtras();

        if(extras!=null && extras.getInt(RecordingFragment.RECORDING) == 1 || Boolean.parseBoolean(new InfoHandler(getApplication()).getExtraStored(RecordingFragment.RECORDING))){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container_schedule, new RecordingFragment());
            ft.commit();
        }
        else {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container_schedule, new BluetoothConnectionFragment());
            ft.commit();
        }
    }

    private View.OnClickListener backAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
    @Override
    public void onBackPressed() {
        InfoHandler ih = new InfoHandler(getApplication());
        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_schedule) instanceof RecordingFragment && Boolean.parseBoolean(ih.getExtraStored(RecordingFragment.RECORDING)))
            ih.saveExtraFromActivity(RecordingFragment.FROM_RECORDING, "true");
        else
            ih.saveExtraFromActivity(Palabras.SCHEDULE_POSITION, null);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
