/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.ranking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetMeetingsForLists;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.objects.TimeConverter;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDataRanking extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout layoutContainer;
    private ArrayList<MeetingModel> meetingModels;
    private CommunicationFragments comm;
    private ArrayList<View> meetingViews;
    private HashMap<Integer, Boolean> isMeetingDevelopped, isSwimmerDevelopped, isRaceDevelopped;

    public FragmentDataRanking() {
        isMeetingDevelopped = new HashMap<Integer, Boolean>();
        isSwimmerDevelopped = new HashMap<Integer, Boolean>();
        isRaceDevelopped = new HashMap<Integer, Boolean>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_ranking, container, false);

        comm = (CommunicationFragments) getActivity();
        layoutContainer = (LinearLayout) view.findViewById(R.id.id_ranking_container);
        getMeetings();
        LayoutInflater factory = LayoutInflater.from(getActivity());
        meetingViews = new ArrayList<View>();

        if (meetingModels == null || meetingModels.size() == 0) {
            TextView textViewNo = new TextView(getActivity());
            textViewNo.setText("There is no ranking in database.");
            textViewNo.setTextColor(getResources().getColor(R.color.bluesea));
            textViewNo.setTextSize(getResources().getDimension(R.dimen.ranking_textview_no_ranking_font));
            layoutContainer.addView(textViewNo);

        } else {
            layoutContainer.removeAllViews();

        }
        for (int meetingPosition = 0; meetingPosition < meetingModels.size(); meetingPosition++) {
            meetingViews.add(meetingPosition, factory.inflate(R.layout.view_ranking_meeting, null));
            meetingViews.get(meetingPosition).setTag("VIEW_meeting_" + meetingPosition);
            layoutContainer.addView(meetingViews.get(meetingPosition));

            TextView meetingText = (TextView) meetingViews.get(meetingPosition).findViewById(R.id.id_ranking_textview_meeting);
            meetingText.setText(meetingModels.get(meetingPosition).getName());
            meetingText.setTag("TV_meeting_" + meetingPosition);
            meetingText.setOnClickListener(this);
            meetingText.setOnTouchListener(this);
            meetingText.setFocusable(true);

        }
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(getResources().getColor(R.color.grey));
                break;
            case MotionEvent.ACTION_UP:
                view.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int idView = view.getId();
        String completeTag = String.valueOf(view.getTag());
        String[] tag = completeTag.split("_");
        int meetingPosition = Integer.valueOf(tag[2]);
        //tag[0]=="BTN , tag[1]=="meeting or swimmer or race , tag[2]==meetingPosition , if tag[3] then ==swimmerId , if tag[4] then ==racePosition

        if (tag[1].equals("meeting")) {
            if (isMeetingDevelopped.containsKey(idView)) {
                boolean isDevelopped = isMeetingDevelopped.get(idView);
                if (isDevelopped) {
                    removeSwimmersFromMeetingView(meetingPosition);
                    isMeetingDevelopped.remove(idView);
                    isMeetingDevelopped.put(idView, false);
                } else {
                    removeAllMeetingContent();
                    addSwimmersToMeeting(meetingPosition);
                    isMeetingDevelopped.remove(idView);
                    isMeetingDevelopped.put(idView, true);
                }
            } else {
                removeAllMeetingContent();
                addSwimmersToMeeting(meetingPosition);
                isMeetingDevelopped.put(idView, true);
            }

        } else if (tag[1].equals("swimmer")) {
            int swimmerId = Integer.valueOf(tag[3]);
            if (isSwimmerDevelopped.containsKey(idView)) {
                boolean isDevelopped = isSwimmerDevelopped.get(idView);

                if (isDevelopped) {
                    removeRaceFromSwimmerView(meetingPosition, swimmerId);
                    isSwimmerDevelopped.remove(idView);
                    isSwimmerDevelopped.put(idView, false);
                } else {
                    addRacesToSwimmer(meetingPosition, swimmerId);
                    isSwimmerDevelopped.remove(idView);
                    isSwimmerDevelopped.put(idView, true);
                }
            } else {
                addRacesToSwimmer(meetingPosition, swimmerId);
                isSwimmerDevelopped.put(idView, true);
            }

        } else if (tag[1].equals("race")) {
            int swimmerId = Integer.valueOf(tag[3]);
            int resultPosition = Integer.valueOf(tag[4]);

            if (isSwimmerDevelopped.containsKey(idView)) {
                boolean isDevelopped = isSwimmerDevelopped.get(idView);

                if (isDevelopped) {
                    removeLapsFromRace(meetingPosition, swimmerId, resultPosition);
                    isSwimmerDevelopped.remove(idView);
                    isSwimmerDevelopped.put(idView, false);
                } else {
                    addLapsToRace(meetingPosition, swimmerId, resultPosition);
                    isSwimmerDevelopped.remove(idView);
                    isSwimmerDevelopped.put(idView, true);
                }
            } else {
                addLapsToRace(meetingPosition, swimmerId, resultPosition);
                isSwimmerDevelopped.put(idView, true);
            }
        }
        view.requestFocusFromTouch();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm.changeVisiblilityOfProgressBar(false);
    }


    private void getMeetings() {
        GetMeetingsForLists getter = new GetMeetingsForLists(getActivity());
        meetingModels = getter.getAllMeetingsFilled();
    }


    private void addSwimmersToMeeting(int meetingPosition) {
        ArrayList<SwimmerModel> swimmersInMeeting = meetingModels.get(meetingPosition).getAllSortedSwimmersInMeetting();

        LayoutInflater factory = LayoutInflater.from(getActivity());
        LinearLayout meetingLayout = (LinearLayout) meetingViews.get(meetingPosition).findViewById(R.id.id_ranking_layout_meeting_tofill_with_swimmer);
        meetingLayout.removeAllViews();

        if (swimmersInMeeting.size() == 0) {
            View swimmerView = factory.inflate(R.layout.view_ranking_swimmer, null);
            swimmerView.setTag("VIEW_swimmer_" + meetingPosition + "_0");
            meetingLayout.addView(swimmerView);
            TextView swimmerText = (TextView) swimmerView.findViewById(R.id.id_ranking_textview_swimmer);
            swimmerText.setText("no swimmer for this meeting");

        } else {
            for (int swimmerPosition = 0; swimmerPosition < swimmersInMeeting.size(); swimmerPosition++) {
                View swimmerView = factory.inflate(R.layout.view_ranking_swimmer, null);
                swimmerView.setTag("VIEW_swimmer_" + meetingPosition + "_" + swimmersInMeeting.get(swimmerPosition).getIdFFN());

                meetingLayout.addView(swimmerView);

                TextView swimmerText = (TextView) swimmerView.findViewById(R.id.id_ranking_textview_swimmer);
                swimmerText.setText(swimmersInMeeting.get(swimmerPosition).getName() + " , " + swimmersInMeeting.get(swimmerPosition).getFirstname());
                swimmerText.setTag("TV_swimmer_" + meetingPosition + "_" + swimmersInMeeting.get(swimmerPosition).getIdFFN());
                swimmerText.setOnClickListener(this);
                swimmerText.setOnTouchListener(this);
                swimmerText.setFocusable(true);

            }
        }
    }

    private void removeSwimmersFromMeetingView(int meetingPosition) {
        LinearLayout layoutMeeting = (LinearLayout) meetingViews.get(meetingPosition).findViewById(R.id.id_ranking_layout_meeting_tofill_with_swimmer);
        layoutMeeting.removeAllViews();
    }

    private void removeAllMeetingContent() {
        for (int meetingPosition = 0; meetingPosition < meetingViews.size(); meetingPosition++) {
            removeSwimmersFromMeetingView(meetingPosition);
            TextView meetingText = (TextView) meetingViews.get(meetingPosition).findViewById(R.id.id_ranking_textview_meeting);
            meetingText.setTextColor(getResources().getColor(R.color.black));
            isMeetingDevelopped.clear();
            isSwimmerDevelopped.clear();
            isRaceDevelopped.clear();
        }
    }


    private void addRacesToSwimmer(int meetingPosition, int swimmerId) {
        ArrayList<ResultModel> resultsForSwimmer = meetingModels.get(meetingPosition).getSortedResultsForSwimmer(swimmerId);
        LayoutInflater factory = LayoutInflater.from(getActivity());
        LinearLayout swimmerLayoutToFill = (LinearLayout) meetingViews.get(meetingPosition)
                .findViewWithTag("VIEW_swimmer_" + meetingPosition + "_" + swimmerId)
                .findViewById(R.id.id_ranking_layout_swimmer_tofill_with_race);
        swimmerLayoutToFill.removeAllViews();

        if (resultsForSwimmer.size() == 0) {
            View raceView = factory.inflate(R.layout.view_ranking_race, null);
            raceView.setTag("VIEW_race_" + meetingPosition + "_" + swimmerId + "_0");

            swimmerLayoutToFill.addView(raceView);

            TextView raceText = (TextView) raceView.findViewById(R.id.id_ranking_textview_race);
            raceText.setText("no race found");
        } else {
            for (int resultPosition = 0; resultPosition < resultsForSwimmer.size(); resultPosition++) {
                ResultModel result = resultsForSwimmer.get(resultPosition);
                View raceView = factory.inflate(R.layout.view_ranking_race, null);
                raceView.setTag("VIEW_race_" + meetingPosition + "_" + swimmerId + "_" + resultPosition);

                swimmerLayoutToFill.addView(raceView);

                TextView raceText = (TextView) raceView.findViewById(R.id.id_ranking_textview_race);
                raceText.setText(result.getEventModel().getRaceModel().getCompleteName());
                raceText.setTag("TV_race_" + meetingPosition + "_" + swimmerId + "_" + resultPosition);
                raceText.setOnClickListener(this);
                raceText.setOnTouchListener(this);
                raceText.setFocusable(true);


                TimeConverter converter = new TimeConverter();
                TextView swimtimeText = (TextView) raceView.findViewById(R.id.id_ranking_textview_swimtime);
                swimtimeText.setText(converter.makeForFFNex(result.getSwimTime()));
            }
        }

    }

    private void removeRaceFromSwimmerView(int meetingPosition, int swimmerId) {
        LinearLayout swimmerLayoutToEmpty = (LinearLayout) meetingViews.get(meetingPosition)
                .findViewWithTag("VIEW_swimmer_" + meetingPosition + "_" + swimmerId)
                .findViewById(R.id.id_ranking_layout_swimmer_tofill_with_race);
        swimmerLayoutToEmpty.removeAllViews();
    }

    private void addLapsToRace(int meetingPosition, int swimmerId, int resultPosition) {
        ArrayList<String> allLapsAsString = meetingModels.get(meetingPosition).getSortedResultsForSwimmer(swimmerId).get(resultPosition).giveBackLapsToInsertInTextViewAllLaps();
        LayoutInflater factory = LayoutInflater.from(getActivity());
        LinearLayout raceLayoutToFill = (LinearLayout) meetingViews.get(meetingPosition)
                .findViewWithTag("VIEW_race_" + meetingPosition + "_" + swimmerId + "_" + resultPosition)
                .findViewById(R.id.id_ranking_layout_race_tofill);
        raceLayoutToFill.removeAllViews();

        if (allLapsAsString.size() == 0) {
            View lapView = factory.inflate(R.layout.view_ranking_lap, null);
            lapView.setTag("VIEW_lap_" + meetingPosition + "_" + swimmerId + "_" + resultPosition + "_0");
            raceLayoutToFill.addView(lapView);
            TextView lapText = (TextView) lapView.findViewById(R.id.id_ranking_textview_lap);
            lapText.setText("no lap for this race");
        } else {
            for (int indexLap = 0; indexLap < allLapsAsString.size(); indexLap++) {
                View lapView = factory.inflate(R.layout.view_ranking_lap, null);
                lapView.setTag("VIEW_lap_" + meetingPosition + "_" + swimmerId + "_" + resultPosition + "_" + indexLap);
                raceLayoutToFill.addView(lapView);
                TextView lapText = (TextView) lapView.findViewById(R.id.id_ranking_textview_lap);
                lapText.setText(allLapsAsString.get(indexLap));
                lapText.setHeight((int)getResources().getDimension(R.dimen.ranking_textview_row_lap_heigh));
            }
        }
    }

    private void removeLapsFromRace(int meetingPosition, int swimmerId, int resultPosition) {
        LinearLayout raceLayoutToEmpty = (LinearLayout) meetingViews.get(meetingPosition)
                .findViewWithTag("VIEW_race_" + meetingPosition + "_" + swimmerId + "_" + resultPosition)
                .findViewById(R.id.id_ranking_layout_race_tofill);
        raceLayoutToEmpty.removeAllViews();
    }


}
