/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class SeasonModel {

    private int id;
    private String name;
    private String startDate;
    private String stopDate;

    public SeasonModel(int idAsStartYearOfSeason) {
        makeContent(idAsStartYearOfSeason);
    }

    public SeasonModel(String startDate) {
        int startYearOfSeason = Integer.valueOf(startDate.substring(0, 4));
        int monthOfMeeting = Integer.valueOf(startDate.substring(5, 7));
        if (monthOfMeeting < 8) {
            startYearOfSeason--;
        }
        makeContent(startYearOfSeason);
    }

    private void makeContent(int startYearOfSeason) {
        this.id = startYearOfSeason;
        this.name = String.valueOf(startYearOfSeason);
        this.startDate = startYearOfSeason + "-09-01";
        int secondYearOfSeason = startYearOfSeason + 1;
        this.stopDate = secondYearOfSeason + "-08-31";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }
}
