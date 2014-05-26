/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetMeetingsForLists;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.objects.TimeConverter;
import com.dim.swimlap.ui.CommunicationFragments;
import com.dim.swimlap.ui.meeting.detail.SwimmersInMeetingAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDataSwimmerDetail extends Fragment implements AdapterView.OnItemClickListener{
    private SwimmerModel swimmer;
    private TextView tvName, tvFirstName, tvDateOFBirth;
    private ListView meetingsListView;
    private CommunicationFragments comm;
    private ArrayList<ResultModel> resultsForOneSwimmer;
    private GetMeetingsForLists getter;

    public FragmentDataSwimmerDetail(SwimmerModel swimmer) {
        this.swimmer = swimmer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_swimmers_details, container, false);
        comm = (CommunicationFragments) getActivity();

        tvName = (TextView) view.findViewById(R.id.id_textview_swimmer_details_name);
        tvFirstName = (TextView) view.findViewById(R.id.id_textview_swimmer_details_firstname);
        tvDateOFBirth = (TextView) view.findViewById(R.id.id_textview_swimmer_details_year_of_birth);


        tvName.setText(swimmer.getName());
        tvFirstName.setText(swimmer.getFirstname());
        tvDateOFBirth.setText(swimmer.getDateOfBirth());

        // DATA TO FILL THE LIST
        getter = new GetMeetingsForLists(getActivity());
        resultsForOneSwimmer = getter.getAllResultsForSwimmerId(swimmer.getIdFFN());
        HashMap<String, String> stringOfRacesAndTime = new HashMap<String, String>();
        ArrayList<String> allMeetingsName = new ArrayList<String>();

        for (int indexResult = 0; indexResult < resultsForOneSwimmer.size(); indexResult++) {
            ResultModel result = resultsForOneSwimmer.get(indexResult);
            String meetingName = getter.getMeetingName(result.getMeetingId());
            TimeConverter converter = new TimeConverter();

            if (stringOfRacesAndTime.containsKey(meetingName)) {
                String content = stringOfRacesAndTime.get(meetingName);
                String raceName = result.getEventModel().getRaceModel().getCompleteName();
                String swimTime = converter.makeForFFNex(result.getSwimTime());
                content += raceName + "    " + swimTime + "\n";
                stringOfRacesAndTime.remove(meetingName);
                stringOfRacesAndTime.put(meetingName, content);

            } else {
                allMeetingsName.add(meetingName);
                String raceName = result.getEventModel().getRaceModel().getCompleteName();
                String swimTime = converter.makeForFFNex(result.getSwimTime());
                String content = raceName + "    " + swimTime + "\n";
                stringOfRacesAndTime.put(meetingName, content);
            }

        }

        meetingsListView = (ListView) view.findViewById(R.id.id_listview_swimmer_details_meetings_swum);
        meetingsListView.setOnItemClickListener(this);
        SwimmersInMeetingAdapter adapter = new SwimmersInMeetingAdapter(getActivity(), allMeetingsName, stringOfRacesAndTime);
        meetingsListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       int meetingId =  resultsForOneSwimmer.get(position).getMeetingId();
        MeetingModel meetingToDetails = getter.getMeetingWithId(meetingId);
        comm.replaceFragmentMeetingToDetails(meetingToDetails);
    }
}
