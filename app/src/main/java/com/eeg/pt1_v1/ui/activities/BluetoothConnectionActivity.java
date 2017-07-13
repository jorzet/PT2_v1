package com.eeg.pt1_v1.ui.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.eeg.pt1_v1.R;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class BluetoothConnectionActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;

    ListView mListDevicesFound;
    TextView mStateBluetooth;
    TextView mSkip;
    Toolbar mToolbar;
    BluetoothAdapter mBluetoothAdapter;

    ArrayAdapter<String> mBTArrayAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStateBluetooth = (TextView)findViewById (R.id.bluetooth_text);
        mSkip = (TextView) findViewById(R.id.skip_connection);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mListDevicesFound = (ListView)findViewById(R.id.devicesfound);
        mBTArrayAdapter = new ArrayAdapter<String>(BluetoothConnectionActivity.this, android.R.layout.simple_list_item_1);
        mListDevicesFound.setAdapter(mBTArrayAdapter);
        mSkip.setOnClickListener(mSkipListener);

        CheckBlueToothState();

        mBTArrayAdapter.clear();
        mBluetoothAdapter.startDiscovery();

        registerReceiver(ActionFoundReceiver,
                new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(ActionFoundReceiver);
    }

    private Button.OnClickListener mSkipListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(BluetoothConnectionActivity.this, ContentActivity.class);
            startActivity(intent);
            BluetoothConnectionActivity.this.finish();
        }};

    private void CheckBlueToothState(){
        if (mBluetoothAdapter == null){
            mStateBluetooth.setText("Tu dispoditivo no tiene Bluetooth");
        }else{
            if (mBluetoothAdapter.isEnabled()){
                if(mBluetoothAdapter.isDiscovering()){
                    mStateBluetooth.setText("Escaneando dispositivos");
                }else{
                    mStateBluetooth.setText("Bluetooth encendido");
                }
            }else{
                mStateBluetooth.setText("Bluetooth no disponible!");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode == REQUEST_ENABLE_BT){
            CheckBlueToothState();
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();
            }
        }};
}