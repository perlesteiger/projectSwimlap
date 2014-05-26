/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.meeting.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.MeetingModel;

import java.util.ArrayList;

public class MeetingAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<MeetingModel> meetings;

    public MeetingAdapter(Context context, ArrayList<MeetingModel> meetings) {
        super(context, R.layout.viewforlist_data_model, meetings);
        this.context = context;
        this.meetings = meetings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_data_model, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        String name = meetings.get(position).getName();
        name += "(" + meetings.get(position).getPoolSize() + "m)";
        String city = meetings.get(position).getCity();
        String startDate = meetings.get(position).getStartDate();

        viewHolder.tvMeetingName = (TextView) rowView.findViewById(R.id.id_textview_list_model_name);
        viewHolder.tvMeetingName.setText(name);
        viewHolder.tvMeetingCity = (TextView) rowView.findViewById(R.id.id_textview_list_model_second);
        viewHolder.tvMeetingCity.setText(city);
        viewHolder.tvMeetingStartDate = (TextView) rowView.findViewById(R.id.id_textview_list_model_third);
        viewHolder.tvMeetingStartDate.setText(startDate);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static TextView tvMeetingName, tvMeetingCity, tvMeetingStartDate;
    }
}

