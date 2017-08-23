package com.eeg.pt1_v1.services.bluetoothservice;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Jorge Zepeda Tinoco on 22/08/17.
 */

public class BluetoothService {
    private static final int ERROR_FROM_DATA =          -1;
    private static final int DATA_SUCESSFULLY_SENDED =  0x00;
    private static final int CLIENT_NOT_ENABLE =        0x01;
    private static final int ERROR_IN_SENDING =         0x02;

    BluetoothAdapter mBluetoothAdapter;

    public BluetoothService(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public int sendUserData(){
        return BluetoothService.DATA_SUCESSFULLY_SENDED;
    }

    public long getCurrentCountDown(){

        return 10L;
    }

}
