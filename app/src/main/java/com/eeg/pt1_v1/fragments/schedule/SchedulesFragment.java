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
import com.eeg.pt1_v1.entities.Cita;
import com.eeg.pt1_v1.entities.Palabras;
import com.eeg.pt1_v1.fragments.content.BaseFragment;
import com.eeg.pt1_v1.services.database.InfoHandler;
import com.eeg.pt1_v1.ui.activities.ContentScheduleActivity;
import com.eeg.pt1_v1.ui.dialogs.ErrorDialog;

import java.util.ArrayList;

/**
 * Created by Jorge Zepeda Tinoco on 09/07/17.
 */

public class SchedulesFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    /* for Bundle */
    public static final String DATE_COLOR = "date_color";
    public static final String DATE_TEXT = "date_text";

    /* for View */
    private ListView listView;
    private TextView mErrorSchedule;
    private ArrayList<String> stringArrayList;
    private ArrayAdapter<String> adapter;

    private ArrayList<Cita> citas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null)
            return null;

        View rootView = inflater.inflate(R.layout.schedules_fragment, container, false);



        listView = (ListView) rootView.findViewById(R.id.list_schedule);
        mErrorSchedule = (TextView) rootView.findViewById(R.id.schedule_error);
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
        String savedSchedules = new InfoHandler(getContext()).getPatientSchedulesJson();
        if(!savedSchedules.contains("Error")) {
            citas = new InfoHandler(getContext()).getPatientSchedules(savedSchedules, Cita.class);
            if (citas != null) {
                for (int i = 0; i < citas.size(); i++) {
                    stringArrayList.add(citas.get(i).getDayAndMonthFormath());
                }
            }
        }
        else{
            mErrorSchedule.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            mErrorSchedule.setText(savedSchedules);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoHandler myHandler = new InfoHandler(getContext());
        String scheduleRecording = myHandler.getExtraStored(Palabras.SCHEDULE_POSITION);
        if(scheduleRecording!=null) {
            String[] schedulePositionValues = scheduleRecording.split("-");
            if (Boolean.parseBoolean(schedulePositionValues[1]) && Integer.parseInt(schedulePositionValues[0]) == position) {
                Intent intent = new Intent(getActivity(), ContentScheduleActivity.class);

                myHandler.saveExtraFromActivity(SchedulesFragment.DATE_COLOR, (String) listView.getAdapter().getItem(position));
                myHandler.saveExtraFromActivity(SchedulesFragment.DATE_TEXT, stringArrayList.get(position));
                myHandler.saveExtraFromActivity(Palabras.SPETIALIST_SUGGESTIONS, citas.get(position).getObservaciones());
                myHandler.saveCurrentSchedule(citas.get(position));
                //intent.putExtra(SchedulesFragment.DATE_COLOR,(String)listView.getAdapter().getItem(position));
                //intent.putExtra(SchedulesFragment.DATE_TEXT,stringArrayList.get(position));
                //intent.putExtra(Palabras.SPETIALIST_SUGGESTIONS, citas.get(position).getObservaciones());

                ImageView ivDate = (ImageView) view.findViewById(R.id.date_schedule);
                TextView tvDate = (TextView) view.findViewById(R.id.date);
                Pair<View, String> p1 = Pair.create((View) ivDate, "p");
                Pair<View, String> p2 = Pair.create((View) tvDate, "date_text_container_schedule");

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), p1, p2);

                startActivity(intent, transitionActivityOptions.toBundle());
            } else
                new ErrorDialog(getContext()).showErrorNewRecording();
        }
        else{
            Intent intent = new Intent(getActivity(), ContentScheduleActivity.class);

            myHandler.saveExtraFromActivity(SchedulesFragment.DATE_COLOR, (String) listView.getAdapter().getItem(position));
            myHandler.saveExtraFromActivity(SchedulesFragment.DATE_TEXT, stringArrayList.get(position));
            myHandler.saveExtraFromActivity(Palabras.SPETIALIST_SUGGESTIONS, citas.get(position).getObservaciones());
            myHandler.saveExtraFromActivity(Palabras.SCHEDULE_POSITION, position + "-" + "false");
            myHandler.saveCurrentSchedule(citas.get(position));
            //intent.putExtra(SchedulesFragment.DATE_COLOR,(String)listView.getAdapter().getItem(position));
            //intent.putExtra(SchedulesFragment.DATE_TEXT,stringArrayList.get(position));
            //intent.putExtra(Palabras.SPETIALIST_SUGGESTIONS, citas.get(position).getObservaciones());

            ImageView ivDate = (ImageView) view.findViewById(R.id.date_schedule);
            TextView tvDate = (TextView) view.findViewById(R.id.date);
            Pair<View, String> p1 = Pair.create((View) ivDate, "p");
            Pair<View, String> p2 = Pair.create((View) tvDate, "date_text_container_schedule");

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), p1, p2);

            startActivity(intent, transitionActivityOptions.toBundle());
        }

    }
}
