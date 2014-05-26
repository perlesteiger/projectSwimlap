/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.objects.TimeConverter;

import java.util.ArrayList;

public class LapAdapter extends ArrayAdapter {
    private final Context context;
    public final ArrayList<ResultModel> resultModelList;
    private TimeConverter converter;
    private Singleton singleton;
    private ViewHolder viewHolder;


    public LapAdapter(Context context, ArrayList<ResultModel> resultModelList) {
        super(context, R.layout.viewforlist_data_lap, resultModelList);
        this.context = context;
        this.resultModelList = resultModelList;
        this.converter = new TimeConverter();
        singleton = Singleton.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        int resultId = resultModelList.get(position).getId();
        int raceId = resultModelList.get(position).getEventModel().getRaceModel().getId();

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_data_lap, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        // BUILD ELEMENTS OF ROW

        // BUTTON LAP
        viewHolder.buttonTakeLap = (Button) rowView.findViewById(R.id.id_button_take_lap);
        viewHolder.buttonTakeLap.setTag("lap_" + resultId + "_" + raceId + "_" + position);

        // BUTTON LAP
        viewHolder.buttonUnLap = (Button) rowView.findViewById(R.id.id_button_unlap);
        viewHolder.buttonUnLap.setTag("lap_" + resultId + "_" + raceId + "_" + position);

        //BUTTON RECORD
        viewHolder.buttonRecordLap = (Button) rowView.findViewById(R.id.id_button_record_lap);
        viewHolder.buttonRecordLap.setTag("rec_" + resultId + "_" + raceId + "_" + position);

        //BUTTON RESET
        viewHolder.buttonResetLap = (Button) rowView.findViewById(R.id.id_button_reset_lap);
        viewHolder.buttonResetLap.setTag("res_" + resultId + "_" + raceId + "_" + position);


        // TEXT VIEW
        viewHolder.textViewSwimmerDetails = (TextView) rowView.findViewById(R.id.id_textview_lap_swimmerdetails);
        viewHolder.textViewQualifiedTime = (TextView) rowView.findViewById(R.id.id_textview_qualifiedTime);


        // FILL ELEMENT WITH DATA FROM DB
        String name = resultModelList.get(position).getSwimmerModel().getName();
        String firstName = resultModelList.get(position).getSwimmerModel().getFirstname();
        String yearOfBirth = resultModelList.get(position).getSwimmerModel().getDateOfBirth().substring(0, 4);
        float qualifyingTime = resultModelList.get(position).getQualifyingTime();
        String qualifTimeAsString = converter.makeString(qualifyingTime);

        viewHolder.textViewSwimmerDetails.setText(name + "  " + firstName + "  " + yearOfBirth);
        viewHolder.textViewQualifiedTime.setText("Qualif: " + qualifTimeAsString);

        viewHolder.textViewAllLaps = (TextView) rowView.findViewById(R.id.id_textview_all_laps);
        viewHolder.textViewAllLaps.setTag("TexViewAll_" + resultId + "_" + raceId + "_" + position);

        viewHolder.textViewLastLap = (TextView) rowView.findViewById(R.id.id_textview_last);
        viewHolder.textViewLastLap.setTag("TextViewLast_" + resultId + "_" + raceId + "_" + position);
        viewHolder.textViewLastLap.setTextColor(context.getResources().getColor(R.color.bluesea));


        if (singleton.isChronoStarted()) {
            viewHolder.buttonTakeLap.setVisibility(View.VISIBLE);
            viewHolder.buttonUnLap.setVisibility(View.VISIBLE);
            viewHolder.buttonResetLap.setVisibility(View.INVISIBLE);
            viewHolder.buttonRecordLap.setVisibility((View.INVISIBLE));
        } else {
            viewHolder.buttonTakeLap.setVisibility(View.INVISIBLE);
            viewHolder.buttonUnLap.setVisibility(View.INVISIBLE);
            viewHolder.buttonResetLap.setVisibility(View.VISIBLE);
            viewHolder.buttonRecordLap.setVisibility((View.VISIBLE));
        }

        if (resultModelList.get(position).areLapsTaken() || resultModelList.get(position).getLaps().size() > 0) {
            ArrayList<String> linesToInsertInAllLaps = resultModelList.get(position).giveBackLapsToInsertInTextViewAllLaps();
            String stringToInsert = "";
            for (int indexLap = 0; indexLap < linesToInsertInAllLaps.size(); indexLap++) {
                stringToInsert += linesToInsertInAllLaps.get(indexLap);
            }
            viewHolder.textViewAllLaps.setText(stringToInsert);
            viewHolder.textViewLastLap.setText(resultModelList.get(position).giveBackLastLap());
            if (resultModelList.get(position).getnbSplitRemaining() <= 0) {
                viewHolder.textViewLastLap.setTextColor(context.getResources().getColor(R.color.redstop));
                changeColorAllLapsWhenLapsRecordedInDb(resultModelList.get(position).areLapsRecodedInDb());
            }
        } else {
            viewHolder.textViewAllLaps.setText("All laps:");
            viewHolder.textViewLastLap.setText("00:00.00");
            viewHolder.textViewLastLap.setTextColor(context.getResources().getColor(R.color.bluesea));
            viewHolder.textViewAllLaps.setTextColor(context.getResources().getColor(R.color.black));
        }
        ScrollView scrollView = (ScrollView) rowView.findViewById(R.id.id_scrollview_laps);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static Button buttonTakeLap, buttonResetLap, buttonRecordLap, buttonUnLap;
        public static TextView textViewSwimmerDetails, textViewQualifiedTime, textViewAllLaps, textViewLastLap;
    }

    public void changeColorAllLapsWhenLapsRecordedInDb(boolean areRecorded) {
        if (areRecorded) {
            viewHolder.textViewAllLaps.setTextColor(context.getResources().getColor(R.color.bluesea));
        } else {
            viewHolder.textViewAllLaps.setTextColor(context.getResources().getColor(R.color.black));
        }
    }
}

