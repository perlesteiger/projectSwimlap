/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.data.RaceData;

public class RaceModel {

    private int id;
    private int distance;
    private String style;
    private boolean is_relay;
    private String gender;
    private int numberOfRelayerIfRelay;

    public RaceModel(int id) {
        this.id = id;
        buidContent();
    }

    private void buidContent() {
        RaceData raceData = new RaceData();
        distance = raceData.giveDistance(id);
        style = raceData.giveStyle(id);
        gender = raceData.giveGender(id);
        numberOfRelayerIfRelay = raceData.giveNbRelayer(id);
        is_relay = numberOfRelayerIfRelay != 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public String getCompleteName(){
        return String.valueOf(distance)+" "+style+" "+gender;
    }
}
