/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;

public class FragmentDataLap extends Fragment {

    private ListView listViewForLap;
    private TextView textViewNoMeetingInLap;
    private LapAdapter adapter;
    private Singleton singleton;
    private int raceIdOfThisFragment;
    private CommunicationFragments comm;
    private Vibrator vibrator;

    public FragmentDataLap(){
        // NOT USED BUT DO NOT REMOVE BECAUSE EXCEPTION THROWN SOMETIMES AT BEGINING
    }

    public FragmentDataLap(int raceId) {
        raceIdOfThisFragment = raceId;
        singleton = Singleton.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
        comm = (CommunicationFragments) getActivity();
        boolean meetingOfTheDayIsBuilt = singleton.buildMeetingOfTheDay(getActivity());
        vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);


        listViewForLap = (ListView) view.findViewById(R.id.id_listview_lap);
        textViewNoMeetingInLap = (TextView) view.findViewById(R.id.id_textview_no_meeting_in_lap);

        if (!meetingOfTheDayIsBuilt) {
            // this case appear when there no club indicate in settings
            Toast.makeText(getActivity(), "You must complete settings please.", Toast.LENGTH_SHORT).show();
            textViewNoMeetingInLap.setVisibility(View.VISIBLE);
        } else {
            textViewNoMeetingInLap.setVisibility(View.INVISIBLE);
            ArrayList<ResultModel> resultsToDisplay = singleton.getResultsByRace(raceIdOfThisFragment);
            adapter = new LapAdapter(this.getActivity(), resultsToDisplay);
            listViewForLap.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }


    public void addLapToModel(View view, float lapInMilli) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int resultId = Integer.valueOf(tag[1]);
        ResultModel resultInSingleton = singleton.getResultOfTheDay(resultId);

        int nbSplitRemaining = resultInSingleton.getnbSplitRemaining();
        if (nbSplitRemaining > 0) {
            vibrator.vibrate(500);
            resultInSingleton.checkLap(lapInMilli);
            if (nbSplitRemaining == 1) {
                resultInSingleton.setSwimTime(lapInMilli);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "All laps taken !", Toast.LENGTH_SHORT).show();
        }
    }

    public void unLapLast(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int resultId = Integer.valueOf(tag[1]);
        ResultModel resultInSingleton = singleton.getResultOfTheDay(resultId);
        resultInSingleton.removeOneWhenUnLap();
        adapter.notifyDataSetChanged();
    }

    public void changeButtonLap() {
        adapter.notifyDataSetChanged();
    }


    public void resetLaps(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int resultId = Integer.valueOf(tag[1]);
        singleton.getResultOfTheDay(resultId).resetLaps();
        adapter.notifyDataSetChanged();
    }

    public void recordLaps(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int resultId = Integer.valueOf(tag[1]);
        if (singleton.getResultOfTheDay(resultId).getnbSplitRemaining() > 0) {
            Toast.makeText(getActivity(), "Some laps are missing.\nSwimLap will delete others.", Toast.LENGTH_SHORT).show();
            resetLaps(view);
        } else {
            singleton.getResultOfTheDay(resultId).recordLapsInDB(getActivity());
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Laps has been recorded in database.", Toast.LENGTH_SHORT).show();
        }

    }

}
