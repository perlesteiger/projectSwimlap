/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.test.models;

import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.RoundModel;

import junit.framework.TestCase;

public class EventModelTest extends TestCase {
    private EventModel eventWithoutId, eventWithId;
    private int id, raceId, roundId;

    public void setUp() throws Exception {
        super.setUp();
        this.id = 123456;
        this.raceId = 1; // corresponds to the race 50 FREE F
        this.roundId = 60; // corresponds to "heat"
        this.eventWithoutId = new EventModel(1, 60);
        this.eventWithId = new EventModel(this.id);
    }

    public void testGetAndSetId() throws Exception {
        int builtId = Integer.valueOf(String.valueOf(this.raceId) + String.valueOf(this.roundId));
        assertEquals(builtId, eventWithoutId.getId());
        assertEquals(this.id, eventWithId.getId());
        int newId = 654321;
        eventWithId.setId(newId);
        assertEquals(newId, eventWithId.getId());
    }


    public void testGetRaceModel() throws Exception {
        assertTrue(eventWithoutId.getRaceModel().getClass() == RaceModel.class);
        assertEquals(this.raceId, eventWithoutId.getRaceModel().getId());
    }

    public void testSetRaceModel() throws Exception {
        assertNull(eventWithId.getRaceModel());
        RaceModel raceModel = new RaceModel(this.raceId);
        eventWithId.setRaceModel(raceModel);
        assertTrue(eventWithId.getRaceModel().getClass() == RaceModel.class);
        assertEquals(this.raceId, eventWithId.getRaceModel().getId());
    }

    public void testGetRoundModel() throws Exception {
        assertTrue(eventWithoutId.getRoundModel().getClass() == RoundModel.class);
        assertEquals(this.roundId, eventWithoutId.getRoundModel().getId());
    }

    public void testSetRoundModel() throws Exception {
        assertNull(eventWithId.getRoundModel());
        RoundModel roundModel = new RoundModel(this.roundId);
        eventWithId.setRoundModel(roundModel);
        assertTrue(eventWithId.getRoundModel().getClass() == RoundModel.class);
        assertEquals(this.roundId, eventWithId.getRoundModel().getId());
    }

    public void testGetAndSetOrder() throws Exception {
        int order = 10;
        eventWithId.setOrder(order);
        assertEquals(order, eventWithId.getOrder());
    }

}
