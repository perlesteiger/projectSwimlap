/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.test.models;

import com.dim.swimlap.models.ClubModel;

import junit.framework.TestCase;

public class ClubModelTest extends TestCase {
    private ClubModel club;
    private int id, code;

    public void setUp() throws Exception {
        super.setUp();
        id = 123456789;
        code = 987654321;
        club = new ClubModel(id, code);

    }

    public void testGetAndSetId() throws Exception {
        assertEquals(id, club.getId());
        int newId = 24681012;
        club.setId(newId);
        assertEquals(newId, club.getId());
    }

    public void testGetAndSetName() throws Exception {
        String name = "name to test";
        club.setName(name);
        assertEquals(name, club.getName());
    }

    public void testGetCodeFFN() throws Exception {
        assertEquals(code, club.getCodeFFN());
    }
}
