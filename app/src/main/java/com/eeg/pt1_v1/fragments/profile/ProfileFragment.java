package com.eeg.pt1_v1.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class ProfileFragment extends BaseFragment {

    private TextView mSpetialist;
    private TextView mIll;
    private TextView mAboutUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;


        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }
}
