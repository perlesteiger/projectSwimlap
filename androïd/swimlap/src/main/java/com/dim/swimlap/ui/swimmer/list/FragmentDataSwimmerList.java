/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetSwimmersForLists;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;


public class FragmentDataSwimmerList extends Fragment {

    private ListView listViewForSwimmers;
    private TextView textViewNoSwimmerInLap;
    private SwimmerAdapter adapter;
    private ArrayList<SwimmerModel> swimmers;
    private CommunicationFragments comm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container, false);
        comm = (CommunicationFragments) this.getActivity();

        listViewForSwimmers = (ListView) view.findViewById(R.id.id_listview_model);
        textViewNoSwimmerInLap = (TextView) view.findViewById(R.id.id_textview_no_model_in_db);

        GetSwimmersForLists getter = new GetSwimmersForLists(getActivity());
        swimmers = getter.getSwimmerList();

        listViewForSwimmers = (ListView) view.findViewById(R.id.id_listview_model);
        listViewForSwimmers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                comm.replaceFragmentSwimmerToDetails(swimmers.get(position));
            }
        });

        if (swimmers == null || swimmers.size() == 0) {
            textViewNoSwimmerInLap.setVisibility(View.VISIBLE);
            textViewNoSwimmerInLap.setText("There is no swimmer in database.");
        } else {
            textViewNoSwimmerInLap.setVisibility(View.INVISIBLE);

            adapter = new SwimmerAdapter(this.getActivity(), swimmers);
            listViewForSwimmers.setAdapter(adapter);

        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }
}
