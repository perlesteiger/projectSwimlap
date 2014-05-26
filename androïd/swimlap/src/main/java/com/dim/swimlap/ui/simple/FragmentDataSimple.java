/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.simple;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentDataSimple extends Fragment {

    private ListView listViewForSimpleLap;
    private SimpleLapAdapter adapter;
    private CommunicationFragments comm;
    private ArrayList<String> raceNames;
    private HashMap<String, ArrayList<Float>> lapForRace;
    private Vibrator vibrator;

    public FragmentDataSimple(){
        // NOT USED BUT DO NOT REMOVE BECAUSE EXCEPTION THROWN SOMETIMES AT BEGINING
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
        vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);

        comm = (CommunicationFragments) getActivity();

        listViewForSimpleLap = (ListView) view.findViewById(R.id.id_listview_lap);
        TextView textViewNoMeeting = (TextView) view.findViewById(R.id.id_textview_no_meeting_in_lap);
        textViewNoMeeting.setVisibility(View.INVISIBLE);

        lapForRace = buildNameRaces();

        adapter = new SimpleLapAdapter(this.getActivity(), raceNames, lapForRace);
        listViewForSimpleLap.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }

    public void takeLap(float lap, int position) {
        vibrator.vibrate(500);
        lapForRace.get(raceNames.get(position)).add(lap);
        adapter.notifyDataSetChanged();

    }

    public void removeLastLap(int position) {
        ArrayList<Float> laps = lapForRace.get(raceNames.get(position));
        int lastLap = laps.size() - 1;
        if (lastLap >= 0) {
            laps.remove(lastLap);
        }
        adapter.notifyDataSetChanged();
    }

    private HashMap<String, ArrayList<Float>> buildNameRaces() {
        raceNames = new ArrayList<String>();
        HashMap<String, ArrayList<Float>> lapForRace = new HashMap<String, ArrayList<Float>>();
        for (int i = 1; i < 5; i++) {
            String name = "Race_" + i;
            raceNames.add(name);
            lapForRace.put(name, new ArrayList<Float>());
        }
        return lapForRace;
    }

    public void removeAllLapsTaken() {
        for (int indexRace = 0; indexRace < raceNames.size(); indexRace++) {
            lapForRace.get(raceNames.get(indexRace)).clear();
        }
        adapter.notifyDataSetChanged();
    }
}
