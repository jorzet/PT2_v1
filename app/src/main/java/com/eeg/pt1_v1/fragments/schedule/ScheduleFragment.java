package com.eeg.pt1_v1.fragments.schedule;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.fragments.recording.RecordingFragment;
import com.eeg.pt1_v1.services.bluetoothservice.BluetoothService;


/**
 * Created by Jorge Zepeda Tinoco on 26/07/17.
 */

public class ScheduleFragment extends BaseFragment{

    /* To show cording the bluetooth adapter status */
    private static final String NOT_HAS_BLUETOOTH = "Tu dispoditivo no tiene Bluetooth";
    private static final String SCANNING_DEVICES = "Escaneando dispositivos";
    private static final String BLUETOOTH_TURNED_ON = "Bluetooth encendido";
    private static final String BLUETOOTH_TURNED_OFF = "Bluetooth apagado";

    /* For the AlertDialog */
    private static final String PAIR_BLUETOOTH_DEVICES = "Vincular dispositivos Bluetooth";
    private static final String CONNECTING_WITH_DEVICE = "Conectando ...";
    private static final String CANCEL_CONNECTION = "Cancelar";

    /* UI accessors */
    private Button mTestButton;
    private TextView mConnectingToDevice;
    private TextView mTestError;
    private ProgressBar mTestProgressBar;
    private ImageView mTestConnectionImageView;

    private BluetoothService mBluetoothService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return null;

        View rootView = inflater.inflate(R.layout.schedule_fragment, container,false);

        mTestButton = (Button) rootView.findViewById(R.id.test_button);
        mTestError = (TextView) rootView.findViewById(R.id.test_error);
        mConnectingToDevice = (TextView) rootView.findViewById(R.id.connection_to_device);
        mTestProgressBar = (ProgressBar) rootView.findViewById(R.id.test_progressbar);
        mTestConnectionImageView = (ImageView) rootView.findViewById(R.id.test_connection_image);

        mBluetoothService = new BluetoothService(getActivity());

        mTestButton.setOnClickListener(mTestListener);
        return rootView;
    }

    private Button.OnClickListener mTestListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            doTest();
        }
    };

    private void doTest(){
        mTestProgressBar.setVisibility(View.VISIBLE);

        switch (mBluetoothService.getBluetoothStatus()){
            case BluetoothService.BLUETOOTH_ADAPTER_NOT_AVAILABLE:
                mTestError.setText(NOT_HAS_BLUETOOTH);
                mTestError.setVisibility(View.VISIBLE);
                mTestProgressBar.setVisibility(View.GONE);
                break;
            case BluetoothService.BLUETOOTH_ADAPTER_TURNED_OFF:
                mTestError.setText(BLUETOOTH_TURNED_OFF);
                mTestError.setVisibility(View.VISIBLE);
                showTurnOnBluetoothDialog();
                mTestProgressBar.setVisibility(View.GONE);
                break;
            case BluetoothService.BLUETOOTH_ADAPTER_TURNED_ON:
                mTestError.setVisibility(View.GONE);
                mConnectingToDevice.setText(CONNECTING_WITH_DEVICE);
                mConnectingToDevice.setVisibility(View.VISIBLE);
                mTestProgressBar.setVisibility(View.VISIBLE);
                mTestButton.setVisibility(View.GONE);
                // connect to raspberry (get bluetooth MAC from MetadataInfo)
                new BluetoothConnectiuonTask().execute();

                break;
        }



    }

    private void goCalibrationFragment(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_schedule, new CalibrationFragment()).commit();
    }

    private void goRecordingFragment(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_schedule, new RecordingFragment()).commit();
    }

    private void showTurnOnBluetoothDialog(){

        if(!mBluetoothService.isEnabled()){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setCancelable(true);
            alertBuilder.setMessage("Enecender Bluetooth");
            alertBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    mBluetoothService.enableBluetooth();
                    mTestButton.setVisibility(View.GONE);
                    mTestError.setVisibility(View.GONE);
                    mTestProgressBar.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doTest();
                        }
                    }, 2000);

                }
            });
            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = alertBuilder.create();
            alert.show();
        }
    }

    private class BluetoothConnectiuonTask extends AsyncTask<Integer, String, Integer>{

        @Override
        protected Integer doInBackground(Integer... strings) {
            return mBluetoothService.connect();
        }

        @Override
        protected void onPostExecute(Integer response) {
            super.onPostExecute(response);

            if (response == BluetoothService.CODE_CONNECTION_SUCCESSFULLY) {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int response = mBluetoothService.sendUserData();
                        mTestProgressBar.setVisibility(View.GONE);

                        if (response == BluetoothService.DATA_SUCESSFULLY_SENDED) {
                            goRecordingFragment();
                        } else if (response == BluetoothService.CODE_TESTING_ERROR) {
                            String jsonError = mBluetoothService.getJsonTestingConnectionError();
                            // show the errors in app with adapter and listview
                            goCalibrationFragment();
                            mTestError.setText(jsonError);
                            mTestError.setVisibility(View.VISIBLE);
                            mTestButton.setVisibility(View.VISIBLE);
                        } else if (response == BluetoothService.ERROR_IN_SENDING) {
                            String error = mBluetoothService.getErrorInSending();
                            mTestError.setText(error);
                            mTestError.setVisibility(View.VISIBLE);
                            mTestButton.setVisibility(View.VISIBLE);
                        }
                    }
                }, 2000);


            } else if (response == BluetoothService.CODE_ERROR_CONNECTION) {
                String error = mBluetoothService.getCanNotConnectError();
                // show the error in mTestError
                mTestError.setText(error);
                mConnectingToDevice.setVisibility(View.GONE);
                mTestError.setVisibility(View.VISIBLE);
                mTestButton.setVisibility(View.VISIBLE);
            }
        }
    }

}
