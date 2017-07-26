package com.eeg.pt1_v1.ui.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eeg.pt1_v1.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 * Edited by Jorge Zepeda Tinoco on 26/07/17
 */

public class BluetoothConnectionActivity extends AppCompatActivity {

    /* To chech the bluetooth adapter status  */
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int BLUETOOTH_ADAPTER_NOT_HAS_BLUETOOTH = -1;
    private static final int BLUETOOTH_ADAPTER_TURNED_ON = 1;
    private static final int BLUETOOTH_ADAPTER_TURNED_OFF = 0;
    private int currentBluetoothState;

    /* To show cording the bluetooth adapter status */
    private static final String NOT_HAS_BLUETOOTH = "Tu dispoditivo no tiene Bluetooth";
    private static final String SCANNING_DEVICES = "Escaneando dispositivos";
    private static final String BLUETOOTH_TURNED_ON = "Bluetooth encendido";
    private static final String BLUETOOTH_TURNED_OFF = "Bluetooth apagdo";

    /* For the AlertDialog */
    private static final String PAIR_BLUETOOTH_DEVICES = "Vincular dispositivos Bluetooth";
    private static final String CONNECT_WITH_DEVICE = "Conectar";
    private static final String CANCEL_CONNECTION = "Cancelar";

    /* For the View */
    ListView mListDevicesFound;
    TextView mStateBluetooth;
    TextView mSkip;
    ProgressBar mProgressBar;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice bdDevice;
    ArrayList<BluetoothDevice> arrayListBluetoothDevices;
    ListItemClicked listItemClicked;
    ArrayAdapter<String> mBTArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_container);

        mStateBluetooth = (TextView) findViewById (R.id.bluetooth_text);
        mSkip = (TextView) findViewById(R.id.skip_connection);
        mListDevicesFound = (ListView) findViewById(R.id.devicesfound);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_bluetooth);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        arrayListBluetoothDevices = new ArrayList<>();
        mBTArrayAdapter = new ArrayAdapter<>(BluetoothConnectionActivity.this, android.R.layout.simple_list_item_1);
        listItemClicked = new ListItemClicked();

        mListDevicesFound.setOnItemClickListener(listItemClicked);
        mListDevicesFound.setAdapter(mBTArrayAdapter);
        mSkip.setOnClickListener(mSkipListener);

        initBluetoothStateThread();

        registerReceiver(ActionFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(ActionFoundReceiver);
    }

    private Button.OnClickListener mSkipListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            goToHome();
        }
    };

    private void initBluetoothStateThread(){
        if((currentBluetoothState = CheckBlueToothState())== BluetoothConnectionActivity.BLUETOOTH_ADAPTER_TURNED_OFF)
            turnOnBluetooth();

        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if(currentBluetoothState!=CheckBlueToothState()){
                    currentBluetoothState=CheckBlueToothState();
                    if(currentBluetoothState == BluetoothConnectionActivity.BLUETOOTH_ADAPTER_TURNED_OFF)
                        turnOnBluetooth();
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);
    }

    private int CheckBlueToothState(){
        if (mBluetoothAdapter == null){
            mStateBluetooth.setText(BluetoothConnectionActivity.NOT_HAS_BLUETOOTH);
            mProgressBar.setVisibility(View.GONE);
            return BluetoothConnectionActivity.BLUETOOTH_ADAPTER_NOT_HAS_BLUETOOTH;
        }else{
            if (mBluetoothAdapter.isEnabled()){
                if(mBluetoothAdapter.isDiscovering()){
                    mStateBluetooth.setText(BluetoothConnectionActivity.SCANNING_DEVICES);
                }else{
                    mStateBluetooth.setText(BluetoothConnectionActivity.BLUETOOTH_TURNED_ON);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mBluetoothAdapter.startDiscovery();
                }
                return BluetoothConnectionActivity.BLUETOOTH_ADAPTER_TURNED_ON;
            }else{
                mStateBluetooth.setText(BluetoothConnectionActivity.BLUETOOTH_TURNED_OFF);
                mProgressBar.setVisibility(View.GONE);
                mBTArrayAdapter.clear();
                return BluetoothConnectionActivity.BLUETOOTH_ADAPTER_TURNED_OFF;
            }
        }
    }

    private void turnOnBluetooth(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ENABLE_BT){
            CheckBlueToothState();
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTArrayAdapter.add((device.getName()!=null)?device.getName():"Unknown" + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();
                arrayListBluetoothDevices.add(device);
            }
        }
    };

    private void callThread() {
        new Thread(){
            public void run() {
                Boolean isBonded;
                try {
                    isBonded = createBond(bdDevice);
                    if(isBonded) {
                        mBTArrayAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private boolean createBond(BluetoothDevice btDevice) throws Exception {
        Class bluetoothClass = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = bluetoothClass.getMethod("createBond");
        return (Boolean) createBondMethod.invoke(btDevice);
    }

    private void goToHome(){
        Intent intent = new Intent(BluetoothConnectionActivity.this, ContentActivity.class);
        startActivity(intent);
        BluetoothConnectionActivity.this.finish();
    }

    private class ListItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bdDevice = arrayListBluetoothDevices.get(position);
            AlertDialog.Builder alert = new AlertDialog.Builder(BluetoothConnectionActivity.this);
            alert.setTitle(BluetoothConnectionActivity.PAIR_BLUETOOTH_DEVICES);
            alert.setMessage("");
            alert.setPositiveButton(BluetoothConnectionActivity.CONNECT_WITH_DEVICE, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callThread();
                    goToHome();
                }
            });

            alert.setNegativeButton(BluetoothConnectionActivity.CANCEL_CONNECTION, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }
    }
}