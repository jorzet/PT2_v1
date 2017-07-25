package com.eeg.pt1_v1.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.eeg.pt1_v1.R;

/**
 * Created by jorgezeped on 24/07/17.
 */

public class ContentScheduleActivity extends AppCompatActivity{
    private ImageView mRoundedDate;
    private TextView mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_schedule);

        mRoundedDate = (ImageView) findViewById(R.id.rounded_date_schedule_container);
        mDate = (TextView) findViewById(R.id.date_container_schedule);
        Bundle extras = getIntent().getExtras();

        String[] date = extras.getString("DATE_TEXT").split(" ");
        mDate.setText(date[1]);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(extras.getString("COLOR"));
        TextDrawable drawable = TextDrawable.builder().buildRound(date[0], color);

        mRoundedDate.setImageDrawable(drawable);


    }

}
