/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.test.models;

import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

public class MeetingModelTest extends TestCase {
    private MeetingModel emptyMeeting, filledMeeting;
    private String swimmerSuffix;
    private int nbMaxOfSwimmer;

    public void setUp() throws Exception {
        super.setUp();
        emptyMeeting = new MeetingModel();
        filledMeeting = new MeetingModel();
        filledMeeting.setPoolSize(50);
        swimmerSuffix = "-Swimmer";
        // 9 BECAUSE USE indexResult FOR THE raceIdAND THERE IS NO raceId == 10
        nbMaxOfSwimmer = 9;
        for (int indexResult = nbMaxOfSwimmer; indexResult > 0; indexResult--) {
            ResultModel result = new ResultModel(indexResult);
            SwimmerModel swimmer = new SwimmerModel(indexResult);
            swimmer.setName(indexResult + swimmerSuffix);
            result.setSwimmerModel(swimmer);
            EventModel event = new EventModel(indexResult, 60);
            event.setOrder(indexResult);
            result.setEventModel(event);
            result.setSwimTime((float) indexResult);
            result.checkLap(new Float(100 * indexResult));
            result.checkLap(new Float(200 * indexResult));
            result.checkLap(new Float(300 * indexResult));
            result.checkLap(new Float(400 * indexResult));

            filledMeeting.addResult(result);
        }
    }

    public void testGetAndSetId() throws Exception {
        int id = 123456;
        emptyMeeting.setId(id);
        assertEquals(id, emptyMeeting.getId());
    }


    public void testGetAndSetClubCode() throws Exception {
        int clubCode = 456789;
        emptyMeeting.setClubCode(clubCode);
        assertEquals(clubCode, emptyMeeting.getClubCode());
    }


    public void testGetAndSetPoolSize() throws Exception {
        int poolSize = 50;
        emptyMeeting.setPoolSize(poolSize);
        assertEquals(poolSize, emptyMeeting.getPoolSize());
    }

    public void testGetAndSetClubId() throws Exception {
        int clubId = 987654;
        emptyMeeting.setClubId(clubId);
        assertEquals(clubId, emptyMeeting.getClubId());
    }

    public void testGetAndSetName() throws Exception {
        String name = "Meeting name to test";
        emptyMeeting.setName(name);
        assertEquals(name, emptyMeeting.getName());
    }

    public void testGetAndSetCity() throws Exception {
        String city = "Annecy";
        emptyMeeting.setCity(city);
        assertEquals(city, emptyMeeting.getCity());
    }

    public void testGetAndSetStartDate() throws Exception {
        String start = "2014-05-25";
        emptyMeeting.setStartDate(start);
        assertEquals(start, emptyMeeting.getStartDate());
    }


    public void testGetAndSetStopDate() throws Exception {
        String stop = "2014-05-25";
        emptyMeeting.setStopDate(stop);
        assertEquals(stop, emptyMeeting.getStopDate());
    }

    public void testGetAndSetSeasonModel() throws Exception {
        SeasonModel season = new SeasonModel(2014);
        emptyMeeting.setSeasonModel(season);
        assertTrue(emptyMeeting.getSeasonModel().getClass() == SeasonModel.class);
        assertEquals(season.getId(), emptyMeeting.getSeasonModel().getId());
    }

    public void testGetAllResults() throws Exception {
        assertNotNull(emptyMeeting.getAllResults());
    }

    public void testAddResult() throws Exception {
        int idResult = 123456;
        ResultModel result = new ResultModel(idResult);
        emptyMeeting.addResult(result);
        assertTrue(emptyMeeting.getAllResults().get(0).getClass() == ResultModel.class);
        assertEquals(idResult, emptyMeeting.getAllResults().get(0).getId());
        emptyMeeting.getAllResults().clear();
    }

    public void testGetAllSortedSwimmersInMeetting() throws Exception {
        ArrayList<SwimmerModel> swimmers = filledMeeting.getAllSortedSwimmersInMeetting();
        assertEquals(nbMaxOfSwimmer, swimmers.size());
        int order = 1;
        for (int indexSwimmer = 0; indexSwimmer < nbMaxOfSwimmer; indexSwimmer++) {
            String fromMeeting = swimmers.get(indexSwimmer).getName();
            String expected = order + swimmerSuffix;
            assertEquals(expected, fromMeeting);
            order++;
        }
        assertTrue(filledMeeting.getRacesBySwimmers().getClass() == HashMap.class);
        for (int indexSwimmer = 1; indexSwimmer <= nbMaxOfSwimmer; indexSwimmer++) {
            assertTrue(filledMeeting.getRacesBySwimmers().containsKey(indexSwimmer));
        }
    }

    public void testGetSortedResultsForSwimmer() throws Exception {
        ArrayList<ResultModel> results = filledMeeting.getSortedResultsForSwimmer(1);
        assertEquals(results.size(), 1);
    }
}
