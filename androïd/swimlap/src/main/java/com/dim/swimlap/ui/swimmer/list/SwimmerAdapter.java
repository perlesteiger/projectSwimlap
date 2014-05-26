/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;

public class SwimmerAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SwimmerModel> swimmers;

    public SwimmerAdapter(Context context, ArrayList<SwimmerModel> swimmerList) {
        super(context, R.layout.viewforlist_data_model, swimmerList);
        this.context = context;
        this.swimmers = swimmerList;
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
        String name = swimmers.get(position).getName();
        String firstName = swimmers.get(position).getFirstname();
        String yearOfBirth = swimmers.get(position).getDateOfBirth().substring(0, 4);

        viewHolder.tvSwimmerName = (TextView) rowView.findViewById(R.id.id_textview_list_model_name);
        viewHolder.tvSwimmerName.setText(name);
        viewHolder.tvSwimmerFirstNAme = (TextView) rowView.findViewById(R.id.id_textview_list_model_second);
        viewHolder.tvSwimmerFirstNAme.setText(firstName);
        viewHolder.tvSwimmerYear = (TextView) rowView.findViewById(R.id.id_textview_list_model_third);
        viewHolder.tvSwimmerYear.setText(yearOfBirth);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static TextView tvSwimmerName, tvSwimmerFirstNAme, tvSwimmerYear;
    }
}
