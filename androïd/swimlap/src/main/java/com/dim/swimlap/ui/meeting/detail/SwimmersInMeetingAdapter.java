/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.meeting.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dim.swimlap.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SwimmersInMeetingAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String> allMeetingsName;
    private HashMap<String, String> stringOfRacesAndTime;

    public SwimmersInMeetingAdapter(Context context, ArrayList<String> allMeetingsName, HashMap<String, String> stringOfRacesAndTime) {
        super(context, R.layout.viewforlist_details, allMeetingsName);
        this.context = context;
        this.allMeetingsName = allMeetingsName;
        this.stringOfRacesAndTime = stringOfRacesAndTime;
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
        String name = allMeetingsName.get(position);
        String races = stringOfRacesAndTime.get(name);


        viewHolder.tvMeetingName = (TextView) rowView.findViewById(R.id.id_textview_details_list_name);
        viewHolder.tvMeetingName.setText(name);
        viewHolder.tvRacesWithTime = (TextView) rowView.findViewById(R.id.id_textview_details_list_races);
        viewHolder.tvRacesWithTime.setText(races);
        viewHolder.tvFirstNameToInvisible = (TextView) rowView.findViewById(R.id.id_textview_details_list_firstname);
        viewHolder.tvFirstNameToInvisible.setVisibility(View.INVISIBLE);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static TextView tvMeetingName, tvRacesWithTime, tvFirstNameToInvisible;
    }
}