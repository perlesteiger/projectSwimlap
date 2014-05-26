/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.objects.DateConverter;
import com.dim.swimlap.objects.TimeConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MeetingModel {

    private int id;
    private String name;
    private String city;
    private Date startDate;
    private Date stopDate;
    private int poolSize;
    private boolean byTeam;
    private int clubId, clubCode;

    private SeasonModel seasonModel;
    private ArrayList<ResultModel> allResults;
    private HashMap<Integer, String> concatStringRaceBySwim;

    public MeetingModel() {
        allResults = new ArrayList<ResultModel>();
    }

    /* GETTER AND SETTERS*/
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getClubCode() {
        return clubCode;
    }

    public void setClubCode(int clubCode) {
        this.clubCode = clubCode;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        DateConverter converter = new DateConverter();
        return converter.convertDateToString(startDate);
    }

    public void setStartDate(String startDate) {
        DateConverter converter = new DateConverter();
        this.startDate = converter.convertStringToDate(startDate);
    }

    public String getStopDate() {
        DateConverter converter = new DateConverter();
        return converter.convertDateToString(stopDate);
    }

    public void setStopDate(String stopDate) {
        DateConverter converter = new DateConverter();
        this.stopDate = converter.convertStringToDate(stopDate);
    }

    public SeasonModel getSeasonModel() {
        return seasonModel;
    }

    public boolean setSeasonModel(SeasonModel seasonModel) {
        boolean seasonIsAdded = false;
        if (seasonModel != null) {
            this.seasonModel = seasonModel;
            seasonIsAdded = true;
        }
        return seasonIsAdded;
    }

    public void setByTeam(boolean byTeam) {
        this.byTeam = byTeam;
    }

    public ArrayList<ResultModel> getAllResults() {
        return allResults;
    }

    public void addResult(ResultModel resultModel) {
        resultModel.setPoolSize(poolSize);
        allResults.add(resultModel);
    }

    /* COMPLEX GETTER */
    public ArrayList<SwimmerModel> getAllSortedSwimmersInMeetting() {
        TimeConverter converter = new TimeConverter();
        if (concatStringRaceBySwim == null) {
            concatStringRaceBySwim = new HashMap<Integer, String>();
        } else {
            concatStringRaceBySwim.clear();
        }
        ArrayList<SwimmerModel> swimmers = new ArrayList<SwimmerModel>();
        for (int indexResult = 0; indexResult < allResults.size(); indexResult++) {
            int swimmerId = allResults.get(indexResult).getSwimmerModel().getIdFFN();
            if (concatStringRaceBySwim.containsKey(swimmerId)) {
                String content = concatStringRaceBySwim.get(swimmerId);
                content += allResults.get(indexResult).getEventModel().getRaceModel().getCompleteName() + "     "
                        + converter.makeString(allResults.get(indexResult).getSwimTime()) + "\n";
                concatStringRaceBySwim.remove(swimmerId);
                concatStringRaceBySwim.put(swimmerId, content);
            } else {

                String content = allResults.get(indexResult).getEventModel().getRaceModel().getCompleteName() + "     "
                        + converter.makeString(allResults.get(indexResult).getSwimTime()) + "\n";
                concatStringRaceBySwim.put(swimmerId, content);
                swimmers.add(allResults.get(indexResult).getSwimmerModel());
            }
        }
        return sortSwimmers(swimmers);
    }

    public HashMap<Integer, String> getRacesBySwimmers() {
        return concatStringRaceBySwim;
    }

    private ArrayList<SwimmerModel> sortSwimmers(ArrayList<SwimmerModel> swimmersUnSort) {
        ArrayList<SwimmerModel> swimmersSort = new ArrayList<SwimmerModel>();
        while (swimmersUnSort.size() > 1) {
            int indexToMove = 0;
            for (int i = 1; i < swimmersUnSort.size(); i++) {
                if (swimmersUnSort.get(indexToMove).getName().compareTo(swimmersUnSort.get(i).getName()) > 0) {
                    indexToMove = i;
                }
            }
            swimmersSort.add(swimmersUnSort.get(indexToMove));
            swimmersUnSort.remove(indexToMove);
        }
        if (swimmersUnSort.size() == 1) {
            swimmersSort.add(swimmersUnSort.get(0));
            swimmersUnSort.remove(0);
        }
        return swimmersSort;
    }


    public ArrayList<ResultModel> getSortedResultsForSwimmer(int swimmerId) {
        ArrayList<ResultModel> resultsForSwimmer = new ArrayList<ResultModel>();
        for (int indexResult = 0; indexResult < allResults.size(); indexResult++) {
            if (allResults.get(indexResult).getSwimmerModel().getIdFFN() == swimmerId) {
                resultsForSwimmer.add(allResults.get(indexResult));
            }
        }
        return sortResultsByRaceId(resultsForSwimmer);
    }

    private ArrayList<ResultModel> sortResultsByRaceId(ArrayList<ResultModel> resultsUnSort) {
        ArrayList<ResultModel> resultsSort = new ArrayList<ResultModel>();
        while (resultsUnSort.size() > 1) {
            int indexToMove = 0;
            for (int i = 1; i < resultsUnSort.size(); i++) {
                if (resultsUnSort.get(indexToMove).getEventModel().getRaceModel().getId() > resultsUnSort.get(i).getEventModel().getRaceModel().getId()) {
                    indexToMove = i;
                }
            }
            resultsSort.add(resultsUnSort.get(indexToMove));
            resultsUnSort.remove(indexToMove);
        }
        if (resultsUnSort.size() == 1) {
            resultsSort.add(resultsUnSort.get(0));
            resultsUnSort.remove(0);
        }
        return resultsSort;
    }
}
