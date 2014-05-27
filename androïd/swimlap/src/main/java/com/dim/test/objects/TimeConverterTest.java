/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.test.objects;

import com.dim.swimlap.objects.TimeConverter;

import junit.framework.TestCase;

public class TimeConverterTest extends TestCase {

    public void testMakeString() throws Exception {
        TimeConverter converter = new TimeConverter();
        String fromConverter = converter.makeString(123456);

        assertEquals("02:03.45", fromConverter);
    }

    public void testMakeForFFNex() throws Exception {
        TimeConverter converter = new TimeConverter();
        String fromConverter = converter.makeForFFNex(123456);

        assertEquals("2.0345", fromConverter);
    }
}
