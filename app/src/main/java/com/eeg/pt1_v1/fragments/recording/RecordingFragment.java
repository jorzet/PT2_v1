package com.eeg.pt1_v1.fragments.recording;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class RecordingFragment extends BaseFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;
        View rootView = inflater.inflate(R.layout.record_fragment, container, false);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }
}
