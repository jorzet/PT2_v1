package com.eeg.pt1_v1.fragments.content;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.eeg.pt1_v1.services.bluetoothservice.BluetoothService;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class BaseFragment extends Fragment {
    protected static final String TAG = "base_activity";
    protected BluetoothService mBluetoothService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBluetoothService(BluetoothService bluetoothService){
        this.mBluetoothService = bluetoothService;
    }

    public BluetoothService getBluetoothService(){
        return this.mBluetoothService;
    }
}
