package com.eeg.pt1_v1.fragments.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

/**
 * Created by ing_ragde on 13/07/17.
 */

public class PatientResults extends BaseFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.patient_results_fragment, container, false);




        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }
}
