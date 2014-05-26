/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class EventModel {

    private int id; // equals to concatenation of raceid and roundid if no id in ffnex
    private int order; //order of events during meeting

    private RaceModel raceModel;
    private RoundModel roundModel;

    public EventModel(int raceId, int roundId) {
        this.raceModel = new RaceModel(raceId);
        this.roundModel = new RoundModel(roundId);
        // IF THERE IS NO id BUILD ONE WITH raceId and roundId
        String idAsString = String.valueOf(raceId)+String.valueOf(roundId);
        this.id = Integer.valueOf(idAsString);
    }

    public EventModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RaceModel getRaceModel() {
        return raceModel;
    }

    public void setRaceModel(RaceModel raceModel) {
        this.raceModel = raceModel;
    }

    public RoundModel getRoundModel() {
        return roundModel;
    }

    public void setRoundModel(RoundModel roundModel) {
        this.roundModel = roundModel;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
