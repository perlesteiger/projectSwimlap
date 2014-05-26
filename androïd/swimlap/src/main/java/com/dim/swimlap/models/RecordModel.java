/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import java.util.HashMap;

public class RecordModel {
    private int swimmerId;
    // HashMap <raceId , swimTime >
    private HashMap<Integer, Float> swimTimesPool25, swimTimesPool50;

    public int getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(int swimmerId) {
        this.swimmerId = swimmerId;
    }

    public Float getSwimtimePool25(int raceId) {
        return swimTimesPool25.get(raceId);
    }

    public boolean setSwimtimePool25(int idRaceToUpdate, float newRecordTime) {
        boolean isARecord = false;
        if (swimTimesPool25.get(idRaceToUpdate) > newRecordTime) {
            swimTimesPool25.remove(idRaceToUpdate);
            swimTimesPool25.put(idRaceToUpdate, newRecordTime);
            isARecord = true;
        } else {
            //record is not a record
        }
        return isARecord;
    }

    public Float getSwimtimePool50(int idRace) {
        return swimTimesPool50.get(idRace);
    }

    public boolean setSwimtimePool50(int idRaceToUpdate, float newRecordTime) {
        boolean isARecord = false;
        if (swimTimesPool25.get(idRaceToUpdate) > newRecordTime) {
            swimTimesPool25.remove(idRaceToUpdate);
            swimTimesPool25.put(idRaceToUpdate, newRecordTime);
            isARecord = true;
        } else {
            //record is not a record
        }
        return isARecord;
    }
}
