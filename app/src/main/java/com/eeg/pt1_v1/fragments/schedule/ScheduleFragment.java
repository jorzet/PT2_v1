package com.eeg.pt1_v1.fragments.schedule;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eeg.pt1_v1.R;
import com.eeg.pt1_v1.adapters.ListViewAdapter;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.ui.activities.ContentScheduleActivity;

import java.util.ArrayList;

/**
 * Created by Jorge on 09/07/17.
 */

public class ScheduleFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    /* for Bundle */
    public static final String DATE_COLOR = "date_color";
    public static final String DATE_TEXT = "date_text";

    /* for View */
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
        listView.setOnItemClickListener(this);

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
        stringArrayList.add("13 Enero");
        stringArrayList.add("1 Febrero");
        stringArrayList.add("2 Marzo");
        stringArrayList.add("5 Abril");
        stringArrayList.add("30 Mayo");
        stringArrayList.add("25 Junio");
        stringArrayList.add("11 Julio");
        stringArrayList.add("2 Agosto");
        stringArrayList.add("11 Septiembre");
        stringArrayList.add("10 Octubre");
        stringArrayList.add("29 Noviembre");
        stringArrayList.add("24 Diciembre");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ContentScheduleActivity.class);
        intent.putExtra(DATE_COLOR,(String)listView.getAdapter().getItem(position));
        intent.putExtra(DATE_TEXT,stringArrayList.get(position));

        ImageView ivDate = (ImageView) view.findViewById(R.id.date_schedule);
        TextView tvDate = (TextView) view.findViewById(R.id.date);
        Pair<View, String> p1 = Pair.create((View)ivDate, "p");
        Pair<View, String> p2 = Pair.create((View)tvDate, "date_text_container_schedule");

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), p1,p2);

        startActivity(intent, transitionActivityOptions.toBundle());
    }
}
