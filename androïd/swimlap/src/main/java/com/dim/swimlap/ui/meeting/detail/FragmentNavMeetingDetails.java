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
import android.widget.Button;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.parser.FFNexMaker;

public class FragmentNavMeetingDetails extends Fragment implements View.OnClickListener {

    private Button buttonExportFFNex;
    private MeetingModel meeting;

    public FragmentNavMeetingDetails(){
        // NOT USED BUT DO NOT REMOVE BECAUSE EXCEPTION THROWN SOMETIMES AT BEGINING
    }

    public FragmentNavMeetingDetails(MeetingModel meeting) {
        this.meeting = meeting;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_model, container, false);
        buttonExportFFNex = (Button) view.findViewById(R.id.id_button_nav_model);
        buttonExportFFNex.setText("Export this meeting as FFNex");
        buttonExportFFNex.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        FFNexMaker maker = new FFNexMaker();
        maker.makeFFNex(meeting);
        maker.sendNewFFNexByMail(getActivity(),meeting.getName());
    }
}
