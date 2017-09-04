package com.eeg.pt1_v1.services.bluetoothservice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

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

    public static boolean isBluetoothHeadsetConnected() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()
                && mBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED;
    }

    public BluetoothAdapter isAvailable(){
        return mBluetoothAdapter;
    }

    public boolean isEnable(){
        return mBluetoothAdapter.isEnabled();
    }

    public boolean isDiscovering(){
        return mBluetoothAdapter.isDiscovering();
    }

    public int sendUserData(){
        return BluetoothService.DATA_SUCESSFULLY_SENDED;
    }

    public long getCurrentCountDown(){
        return 10L;
    }

    private void connect(BluetoothDevice connectedDevice, boolean retry) {
        //if (isConnected()) {
        //    mMainHandler.obtainMessage(DO_ON_CONNECTED, connectedDevice)
        //            .sendToTarget();
        //    return;
        //}
        BluetoothSocket socket = null;
        try {
            mBluetoothAdapter.cancelDiscovery();
            //socket = connectedDevice.createRfcommSocketToServiceRecord(MY_UUID);
            socket.connect();
            //mConnectedThread = new ConnectedThread(socket);
            //mConnectedThread.start();
            //mMainHandler.obtainMessage(DO_ON_CONNECTED,
            //        socket.getRemoteDevice()).sendToTarget();
        } catch (IOException e) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (retry) {
                connect(connectedDevice, false);
            } else {
                //mMainHandler.obtainMessage(DO_CONNECTION_FAILED).sendToTarget();
                e.printStackTrace();
            }
        }
    }


}
