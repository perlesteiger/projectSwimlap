/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableEvents;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.RoundModel;

public class EventUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableEvents table;

    public EventUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableEvents dbTableEvents) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableEvents;
    }

    /* GETTERS */
    public EventModel getEventModel_FromDb(int idEvent, int idMeeting) {
        EventModel eventModel = null;
        String condition = table.COL_EVE_ID + "=" + idEvent + " AND " + table.COL_MEE_ID_MEET + "=" + idMeeting;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            eventModel = getDataEvent_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return eventModel;
    }

//    public List<EventModel> getAllEventsInMeeting_FromDb(int idMeeting) {
//        List<EventModel> allEventsInMeeting = new ArrayList<EventModel>();
//        String condition = table.COL_MEE_ID_MEET + "=" + idMeeting;
//        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            EventModel event = getDataEvent_FromDb(cursor);
//            allEventsInMeeting.add(event);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return allEventsInMeeting;
//    }


    /* GET CONTENT */
    public EventModel getDataEvent_FromDb(Cursor cursor) {
        EventModel eventModel = new EventModel(cursor.getInt(0));
        // cursor.getInt(1) // meetingId
        eventModel.setRaceModel(new RaceModel(cursor.getInt(2)));
        eventModel.setRoundModel(new RoundModel(cursor.getInt(3)));
        eventModel.setOrder(cursor.getInt(4));
        return eventModel;
    }

    /* ADDER */
    public void addEvent_InDb(EventModel eventModel, int idMeeting) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(table.COL_EVE_ID, eventModel.getId());
        contentValues.put(table.COL_MEE_ID_MEET, idMeeting);
        contentValues.put(table.COL_RAC_ID_RACE, eventModel.getRaceModel().getId());
        contentValues.put(table.COL_EVE_ROUND_ID, eventModel.getRoundModel().getId());
        contentValues.put(table.COL_EVE_ORDER, eventModel.getOrder());

        sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
//    public void deleteEvent_InDb(int idEvent) {
//        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_EVE_ID + " = " + idEvent, null);
//    }

    /* UPDATER */
//    public void updateEvent_FromDb(EventModel eventModel, int idMeeting) {
//        deleteEvent_InDb(eventModel.getId());
//        addEvent_InDb(eventModel, idMeeting);
//    }

    /* VERIFY ENTRY */
    public boolean eventAlready_InDb(int idEvent, int idMeeting) {
        boolean isPresent = true;
        if (getEventModel_FromDb(idEvent, idMeeting) == null) {
            isPresent = false;
        }
        return isPresent;
    }
//    public boolean tableIsEmpty(){
//        boolean isEmpty;
//        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
//
//        if (cursor.getCount() == 0) {
//            isEmpty = true;
//        } else {
//            isEmpty = false;
//        }
//        return isEmpty;
//    }
}
