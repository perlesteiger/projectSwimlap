/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import android.content.Context;

import com.dim.swimlap.db.recorder.RecordLapsInDb;
import com.dim.swimlap.objects.TimeConverter;

import java.util.ArrayList;

public class ResultModel {

    // CHARACTERISTICS
    private int id;
    private SwimmerModel swimmerModel;
    private ArrayList<SwimmerModel> team;
    private EventModel eventModel;
    private int meetingId;
    // DATA
    private float qualifyingTime;
    private float swimTime;
    private ArrayList<Float> laps;

    // UTILITIES
    private int currentLapSwimming;
    private int numberOfLap;
    private int poolSize;
    private boolean isRelay;

    private boolean someLapsAreTaken;
    private boolean lapsAreRecodedInDb;



    public ResultModel(int id) {
        this.id = id;
        laps = new ArrayList<Float>();
        // with this constructor elements must be added: swimmer or team, eventmodel , poolsize , qualifyingtime
    }



    public void buildContent(float qualifyingTime, int poolSize, int meetingId, EventModel eventModel) {
        this.eventModel = eventModel;
        this.meetingId = meetingId;
        this.poolSize = poolSize;
        // qualifying time must be in milliseconds
        this.qualifyingTime = qualifyingTime;
        numberOfLap = this.eventModel.getRaceModel().getDistance() / poolSize;
        if (laps.size()== 0) {
            someLapsAreTaken = false;
            lapsAreRecodedInDb=false;
            currentLapSwimming = 0;
        } else {
            someLapsAreTaken = true;
            lapsAreRecodedInDb=true;
            currentLapSwimming = numberOfLap;
        }
    }
    /* COMPLEX */

    public ArrayList<String> giveBackLapsToInsertInTextViewAllLaps() {
        TimeConverter converter = new TimeConverter();
        ArrayList<String> allLapsAlreadyTaken = new ArrayList<String>();

        for (int lapIndex = 0; lapIndex < laps.size(); lapIndex++) {
            String lapStringToAdd = String.valueOf(poolSize * (lapIndex + 1)) + "m : ";
            lapStringToAdd += converter.makeString(giveSplit(laps.get(lapIndex), lapIndex));
            lapStringToAdd += "  -  ";
            lapStringToAdd += converter.makeString(laps.get(lapIndex));
            lapStringToAdd += "\n";
            allLapsAlreadyTaken.add(lapStringToAdd);
        }
        return allLapsAlreadyTaken;
    }

    public String giveBackLastLap() {
        TimeConverter converter = new TimeConverter();
        int index = laps.size() - 1;
        if (index < 0) {
            return converter.makeString(0);
        } else {
            return converter.makeString(laps.get(laps.size() - 1));
        }
    }

    private float giveSplit(float lap, int currentPosition) {
        float split;
        if (currentPosition == 0) {
            split = lap;
        } else {
            split = lap - laps.get(currentPosition - 1);
        }
        return split;
    }

    public float checkLap(float lap) {
        float split = giveSplit(lap, currentLapSwimming);
        // VERIFY IF THE TIME BETWEEN LAST LAP AND THE NEW ONE IS COHERENT AND IF ALL THE LAPS ARE NOT FILLED (RACE FINISH)
        if ((currentLapSwimming < numberOfLap)) {
            laps.add(currentLapSwimming, lap);
            currentLapSwimming++;
        } else {
            split = 0;
        }
        return split;
    }

    public int getnbSplitRemaining() {
        return numberOfLap - currentLapSwimming;
    }

    public void resetLaps() {
        this.currentLapSwimming = 0;
        this.laps.clear();
        someLapsAreTaken = false;
        lapsAreRecodedInDb=false;
    }

    public boolean isRelay() {
        return isRelay;
    }

    public void setIsRelay(boolean isRelay) {
        this.isRelay = isRelay;
    }

    public void recordLapsInDB(Context context) {
        RecordLapsInDb recorder = new RecordLapsInDb(context);
        recorder.recordLaps(this);
        someLapsAreTaken = true;
        lapsAreRecodedInDb=true;
    }

    public void removeOneWhenUnLap(){
        if(currentLapSwimming>0){
            laps.remove(currentLapSwimming-1);
            currentLapSwimming--;
        }
    }
    // GETTERS AND SETTERS
    public boolean areLapsTaken() {
        return someLapsAreTaken;
    }

    public float getQualifyingTime() {
        return qualifyingTime;
    }

    public void setQualifyingTime(float qualifyingTime) {
        this.qualifyingTime = qualifyingTime;
    }

    public ArrayList<Float> getLaps() {
        return laps;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public SwimmerModel getSwimmerModel() {
        return swimmerModel;
    }

    public void setSwimmerModel(SwimmerModel swimmerModel) {
        this.swimmerModel = swimmerModel;
        isRelay = false;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public float getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(float swimTime) {
        this.swimTime = swimTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<SwimmerModel> getTeam() {
        return team;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public boolean areLapsRecodedInDb() {
        return lapsAreRecodedInDb;
    }

    public int getNumberOfLap() {
        return numberOfLap;
    }
    public void addSwimmersInTeam(SwimmerModel swimmerModel) {
        if (team == null) {
            team = new ArrayList<SwimmerModel>();
            isRelay = true;
        }
        team.add(swimmerModel);
    }
}
