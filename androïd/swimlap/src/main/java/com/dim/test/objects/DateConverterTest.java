/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.test.objects;

import com.dim.swimlap.objects.DateConverter;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverterTest extends TestCase {
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private String dateAsString;
    private SimpleDateFormat formatter;
    private Date date;

    public void setUp() throws Exception {
        super.setUp();
        dateAsString = "2014-05-25";
        formatter = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        date = formatter.parse(dateAsString);
    }

    public void testConvertDateToString() throws Exception {
        DateConverter converter = new DateConverter();
        assertEquals(converter.convertDateToString(date), dateAsString);
    }

    public void testConvertStringToDate() throws Exception {
        DateConverter converter = new DateConverter();
        assertEquals(converter.convertStringToDate(dateAsString), date);
    }

    public void testGetTodayAsString() throws Exception {
        DateConverter converter = new DateConverter();
        String fromConverter = converter.getTodayAsString();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // jannuary is 0 but not in ffnex so the DateConverter takes it in account so +1
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String fromCalendar = year + "-";
        if (month < 9) {
            fromCalendar += "0";
        }
        fromCalendar += month + "-";
        if (day < 9) {
            fromCalendar += "0";
        }
        fromCalendar += day;
        assertEquals(fromConverter, fromCalendar);


    }
}
