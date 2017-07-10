package com.eeg.pt1_v1.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.adapters.Pager;

/**
 * Created by Jorge Zepeda Tinoco on 7/1/2017.
 */

public class ContentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    /* For the View */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Pager mAdapter;
    private int[] mTabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_record,
            R.drawable.ic_profile,
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
        setIcons();
        mTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
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
        startActivity(i);
        overridePendingTransition(R.anim.zoom_enter_animation, R.anim.zoom_exit_animation);
    }
}
