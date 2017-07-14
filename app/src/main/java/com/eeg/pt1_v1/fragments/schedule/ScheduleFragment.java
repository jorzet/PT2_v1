package com.eeg.pt1_v1.fragments.schedule;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.adapters.ListViewAdapter;
import com.eeg.pt1_v1.fragments.content.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ing_ragde on 09/07/17.
 */

public class ScheduleFragment extends BaseFragment{
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.schedule_fragment, container, false);



        listView = (ListView) rootView.findViewById(R.id.list_schedule);

        setData();
        adapter = new ListViewAdapter(getContext(), R.layout.item_schedule_listview, stringArrayList);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    private void setData() {
        stringArrayList = new ArrayList<>();
        stringArrayList.add("13 en");
        stringArrayList.add("1 feb");
        stringArrayList.add("2 mar");
        stringArrayList.add("5 abr");
        stringArrayList.add("30 may");
        stringArrayList.add("25 jun");
        stringArrayList.add("11 jul");
        stringArrayList.add("2 ago");
        stringArrayList.add("11 sep");
        stringArrayList.add("10 oct");
        stringArrayList.add("29 nov");
        stringArrayList.add("24 dic");
    }
}
