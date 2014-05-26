/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.meeting.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.ui.CommunicationFragments;
import com.dim.swimlap.ui.swimmer.detail.MeetingsSwimInSwimmerAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentDataMeetingDetails extends Fragment implements AdapterView.OnItemClickListener {

    private MeetingModel meeting;
    private TextView tvName, tvCity, tvStartdate, tvStopdate, tvPoolsize;
    private ListView swimmersListView;
    private CommunicationFragments comm;
    private ArrayList<SwimmerModel> swimmers;

    public FragmentDataMeetingDetails() {
        // NOT USED BUT DO NOT REMOVE BECAUSE EXCEPTION THROWN SOMETIMES AT BEGINING
    }

    public FragmentDataMeetingDetails(MeetingModel meeting) {
        this.meeting = meeting;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_meeting_details, container, false);

        comm = (CommunicationFragments) getActivity();

        tvName = (TextView) view.findViewById(R.id.id_textview_meeting_details_name);
        tvCity = (TextView) view.findViewById(R.id.id_textview_meeting_details_city);
        tvPoolsize = (TextView) view.findViewById(R.id.id_textview_meeting_details_poolsize);
        tvStartdate = (TextView) view.findViewById(R.id.id_textview_meeting_details_startdate);
        tvStopdate = (TextView) view.findViewById(R.id.id_textview_meeting_details_stopdate);

        tvName.setText(meeting.getName());
        tvCity.setText(meeting.getCity());
        tvPoolsize.setText(String.valueOf(meeting.getPoolSize()) + "m");
        tvStartdate.setText("FROM: " + meeting.getStartDate());
        tvStopdate.setText("TO: " + meeting.getStopDate());

        swimmers = meeting.getAllSortedSwimmersInMeetting();
        HashMap<Integer, String> racesBySwimmer = meeting.getRacesBySwimmers();

        swimmersListView = (ListView) view.findViewById(R.id.id_listview_meeting_details_swimmers);
        swimmersListView.setOnItemClickListener(this);
        MeetingsSwimInSwimmerAdapter adapter = new MeetingsSwimInSwimmerAdapter(getActivity(), swimmers, racesBySwimmer);
        swimmersListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        comm.replaceFragmentSwimmerToDetails(swimmers.get(position));
    }
}
