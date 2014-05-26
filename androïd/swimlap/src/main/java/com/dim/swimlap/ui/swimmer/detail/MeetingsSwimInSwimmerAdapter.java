/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MeetingsSwimInSwimmerAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SwimmerModel> swimmers;
    private HashMap<Integer, String> racesBySwimmers;

    public MeetingsSwimInSwimmerAdapter(Context context, ArrayList<SwimmerModel> swimmers, HashMap<Integer, String> racesBySwimmers) {
        super(context, R.layout.viewforlist_details, swimmers);
        this.context = context;
        this.swimmers = swimmers;
        this.racesBySwimmers = racesBySwimmers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_details, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        String name = swimmers.get(position).getName();
        String firstName = swimmers.get(position).getFirstname();
        String allRacesBySwimmer = racesBySwimmers.get(swimmers.get(position).getIdFFN());

        viewHolder.tvName = (TextView) rowView.findViewById(R.id.id_textview_details_list_name);
        viewHolder.tvName.setText(name);
        viewHolder.tvFirstName = (TextView) rowView.findViewById(R.id.id_textview_details_list_firstname);
        viewHolder.tvFirstName.setText(firstName);
        viewHolder.tvRaces = (TextView) rowView.findViewById(R.id.id_textview_details_list_races);
        viewHolder.tvRaces.setText(allRacesBySwimmer);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static TextView tvName, tvFirstName, tvRaces;
    }
}
