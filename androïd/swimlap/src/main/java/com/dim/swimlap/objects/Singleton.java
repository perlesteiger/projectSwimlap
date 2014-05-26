/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import android.content.Context;

import com.dim.swimlap.db.getter.GetMeetingOfTheDay;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;

import java.util.ArrayList;

public final class Singleton {

    private static volatile Singleton instance = null;
    private MeetingModel meetingOfTheDay;
    private int currentRaceId;
    private ArrayList<EventModel> allEventsByOrder;
    private boolean chronoIsStarted;
    private boolean thereIsMeetingToday;

    private Singleton() {
        super();
    }

    private Singleton(MeetingModel meetingForSimple) {
        super();
        meetingOfTheDay = meetingForSimple;
    }


    public final static Singleton getInstance() {
        if (Singleton.instance == null) {
            synchronized (Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }

    public boolean buildMeetingOfTheDay(Context context) {
        thereIsMeetingToday = false;
        if (meetingOfTheDay != null) {
            thereIsMeetingToday = true;
        } else {
            GetMeetingOfTheDay getter = new GetMeetingOfTheDay(context);
            meetingOfTheDay = getter.getFilledMeetingOfTheDay();

        }
        if (meetingOfTheDay != null) {
            thereIsMeetingToday = true;
            buildEventByOrder();
            if (currentRaceId == 0) {
                currentRaceId = allEventsByOrder.get(0).getRaceModel().getId();
            }
        }

        return thereIsMeetingToday;
    }

    public ResultModel getResultOfTheDay(int resultId) {
        ArrayList<ResultModel> results = meetingOfTheDay.getAllResults();
        ResultModel resultToReturn = null;
        for (int index = 0; index < results.size(); index++) {
            if (results.get(index).getId() == resultId) {
                resultToReturn = results.get(index);
            }
        }
        return resultToReturn;
    }


    public ArrayList<ResultModel> getResultsByRace(int raceId) {
        ArrayList<ResultModel> resultsForIdRace = new ArrayList<ResultModel>();
        for (int indexResult = 0; indexResult < meetingOfTheDay.getAllResults().size(); indexResult++) {
            if (meetingOfTheDay.getAllResults().get(indexResult).getEventModel().getRaceModel().getId() == raceId) {
                resultsForIdRace.add(meetingOfTheDay.getAllResults().get(indexResult));
            }
        }
        return resultsForIdRace;
    }

    public ArrayList<EventModel> getAllEventsByOrderInMeeting() {
        return allEventsByOrder;
    }

    private void buildEventByOrder() {
        ArrayList<EventModel> allEvents = new ArrayList<EventModel>();
        ArrayList<ResultModel> allResultsInMeeting = getAllResultsOfDay();

        for (int indexResult = 0; indexResult < allResultsInMeeting.size(); indexResult++) {
            int idRaceToInsert = allResultsInMeeting.get(indexResult).getEventModel().getRaceModel().getId();
            // if raceid is not in allEventByOrder then insert it at tha right place
            if (!eventAlreadyInList(allEvents, idRaceToInsert)) {
                allEvents.add(allResultsInMeeting.get(indexResult).getEventModel());
            }
        }
        sortEvents(allEvents);
    }

    private void sortEvents(ArrayList<EventModel> listToSort) {
        allEventsByOrder = new ArrayList<EventModel>();
        int positionOfSmaller = 0;
        int smaller = 999999;
        while (listToSort.size() > 0) {
            for (int indexCursor = 0; indexCursor < listToSort.size(); indexCursor++) {
                if (smaller > listToSort.get(indexCursor).getOrder()) {
                    smaller = listToSort.get(indexCursor).getOrder();
                    positionOfSmaller = indexCursor;
                }
            }
            allEventsByOrder.add(listToSort.get(positionOfSmaller));
            listToSort.remove(positionOfSmaller);
            smaller = 999999;
        }
    }

    private boolean eventAlreadyInList(ArrayList<EventModel> list, int idRace) {
        boolean isAlreadyIn = false;
        for (int indexEvent = 0; indexEvent < list.size(); indexEvent++) {
            if (idRace == list.get(indexEvent).getRaceModel().getId()) {
                isAlreadyIn = true;
            }
        }
        return isAlreadyIn;
    }

    public ArrayList<ResultModel> getAllResultsOfDay() {
        return meetingOfTheDay.getAllResults();
    }

    public int getCurrentRaceId() {
        return currentRaceId;
    }

    public void setCurrentRaceId(int currentRaceId) {
        this.currentRaceId = currentRaceId;
    }

    public String getMeetingName() {
        return meetingOfTheDay.getName();
    }

    public boolean isChronoStarted() {
        return chronoIsStarted;
    }

    public void setChronoIsStarted(boolean chronoIsStarted) {
        this.chronoIsStarted = chronoIsStarted;
    }

    public boolean isThereMeetingToday() {
        return thereIsMeetingToday;
    }
}

