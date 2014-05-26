/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;

public class FragmentNavLap extends Fragment implements View.OnClickListener {
    private LinearLayout linearLayout;
    private Singleton singleton;
    private CommunicationFragments communication;
    private HorizontalScrollView scroll;
    private int positionButtonSelected;

    public FragmentNavLap() {
        singleton = Singleton.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_lap, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.id_linearlayout_in_horizontalscrollview);
        scroll = (HorizontalScrollView) view.findViewById(R.id.id_horizontalscrollview_nav_lap);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (singleton.buildMeetingOfTheDay(getActivity())) {
            ArrayList<EventModel> eventsOfTheDay = singleton.getAllEventsByOrderInMeeting();
            for (int indexEvent = 0; indexEvent < eventsOfTheDay.size(); indexEvent++) {
                RaceModel race = eventsOfTheDay.get(indexEvent).getRaceModel();
                String nameToPutInButton = race.getCompleteName();
                int idRace = race.getId();

                final Button button = new Button(this.getActivity());
                button.setWidth((int) getResources().getDimension(R.dimen.lap_button_race_width));
                button.setHeight((int) getResources().getDimension(R.dimen.lap_button_race_heigh));
                button.setTextSize(getResources().getDimension(R.dimen.lap_button_race_font));
                button.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                button.setSingleLine();
                button.setText(nameToPutInButton);
                button.setTag("ButtonRace_" + idRace);
                button.setOnClickListener(this);
                if (idRace == singleton.getCurrentRaceId()) {
                    button.setBackgroundResource(R.drawable.button_race_selected);
                    button.setTextColor(getResources().getColor(R.color.white));

                } else {
                    int size = nameToPutInButton.length();
                    char gender = nameToPutInButton.charAt(size - 1);
                    if (gender == 'F') {
                        button.setBackgroundResource(R.drawable.button_race_f);
                        button.setTextColor(getResources().getColor(R.color.bluesea));
                    } else {
                        button.setBackgroundResource(R.drawable.button_race_m);
                        button.setTextColor(getResources().getColor(R.color.bluesea));
                    }
                }
                linearLayout.addView(button);

                if (idRace == singleton.getCurrentRaceId()) {
                    int buttonWidth = (int) getResources().getDimension(R.dimen.lap_button_race_width);
                    positionButtonSelected = (indexEvent - 1) * buttonWidth;
                }
            }
            scroll.post(new Runnable() {
                @Override
                public void run() {
                    scroll.scrollTo(positionButtonSelected, 0);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        String[] splitedTag = tag.split("_");
        int newRaceId = Integer.valueOf(splitedTag[1]);
        modifyButtonRaceSelected(newRaceId);

        singleton.setCurrentRaceId(newRaceId);
        communication = (CommunicationFragments) this.getActivity();
        communication.replaceFragmentDataLap(newRaceId);
    }

    public void modifyButtonRaceSelected(int newIdRace) {
        Button previousSelectedButton = (Button) linearLayout.findViewWithTag("ButtonRace_" + singleton.getCurrentRaceId());
        Button newSelectedButton = (Button) linearLayout.findViewWithTag("ButtonRace_" + newIdRace);

        previousSelectedButton.setTextColor(getResources().getColor(R.color.bluesea));
        newSelectedButton.setBackgroundResource(R.drawable.button_race_selected);
        newSelectedButton.setTextColor(getResources().getColor(R.color.white));

        String nameOfButton = String.valueOf(newSelectedButton.getText());
        int size = nameOfButton.length();
        char gender = nameOfButton.charAt(size - 1);
        if (gender == 'F') {
            previousSelectedButton.setBackgroundResource(R.drawable.button_race_f);
        } else {
            previousSelectedButton.setBackgroundResource(R.drawable.button_race_m);
        }
    }


}
