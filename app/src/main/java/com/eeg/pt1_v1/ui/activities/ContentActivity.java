package com.eeg.pt1_v1.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.adapters.Pager;

/**
 * Created by Jorge Zepeda Tinoco on 7/1/2017.
 */

public class ContentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    /* For the View */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ImageView mBluetoothIcon;
    private Pager mAdapter;

    private int[] mTabIcons = {
            R.drawable.ic_profile,
            R.drawable.ic_home,
            R.drawable.ic_calendar,
    };

    private int[] mBtteryPorcentage = {
            R.drawable.ic_battery_20,
            R.drawable.ic_battery_30,
            R.drawable.ic_battery_50,
            R.drawable.ic_battery_60,
            R.drawable.ic_battery_80,
            R.drawable.ic_battery_90,
            R.drawable.ic_battery_100
    };

    /* For the Fragments tag */
    private static final String HOME_TAG = "home_tag";
    private static final String RECORD_TAG = "record_tag";
    private static final String SCHEDULE_TAG = "schedule_tag";
    private static final String PROFILE_TAG = "profile_tag";

    /* Variable for SharedPreferences */
    public boolean isMedic = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getCurrentUser();

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mBluetoothIcon = (ImageView) findViewById(R.id.small_bluetooth_icon);
        if(isMedic) {// if is medic or specialist
            mTabLayout.addTab(mTabLayout.newTab().setTag(HOME_TAG));
            mTabLayout.addTab(mTabLayout.newTab().setTag(SCHEDULE_TAG));
            mTabLayout.addTab(mTabLayout.newTab().setTag(PROFILE_TAG));
        }
        else{ // if is a patient
            mTabLayout.addTab(mTabLayout.newTab().setTag(HOME_TAG));
            mTabLayout.addTab(mTabLayout.newTab().setTag(RECORD_TAG));
            mTabLayout.addTab(mTabLayout.newTab().setTag(PROFILE_TAG));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        setIcons();
        mTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void setIcons() {
        mTabLayout.getTabAt(0).setIcon(mTabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(mTabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(mTabIcons[2]);
    }

    public void getCurrentUser(){
        //Using shared preferences

    }

    public void goBluetoothConection(View view) {
        Intent i = new Intent(ContentActivity.this, BluetoothConnectionActivity.class);

        View sharedView = view.findViewById(R.id.small_bluetooth_icon);
        String transitionName = getString(R.string.error_bluetooth_icon);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, transitionName);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
