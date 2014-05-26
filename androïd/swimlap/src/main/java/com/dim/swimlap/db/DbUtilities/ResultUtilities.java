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

import com.dim.swimlap.db.tables.DbTableResults;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;

public class ResultUtilities {
    private SQLiteDatabase db;
    private DbTableResults table;

    public ResultUtilities(SQLiteDatabase db, DbTableResults dbTableResults) {
        this.db = db;
        this.table = dbTableResults;
    }

    /* GETTERS */
    public ArrayList<ResultModel> getAllResultsByTimeOrderByMeeting_FromDb(int idMeeting) {
        ArrayList<ResultModel> allResults = new ArrayList<ResultModel>();
        String condition = table.COL_MEE_ID_MEET + "=" + idMeeting;
        String orderBy = table.COL_RES_QUALIFYING_TIME + " DESC";
        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ResultModel resultModel = getDataResult_FromDb(cursor);
            allResults.add(resultModel);
            cursor.moveToNext();
        }
        cursor.close();
        return allResults;
    }

//    public ArrayList<ResultModel> getAllResultsBySwimmerOrderByMeeting_FromDb(int idMeeting) {
//        ArrayList<ResultModel> allResults = new ArrayList<ResultModel>();
//        String condition = table.COL_MEE_ID_MEET + "=" + idMeeting;
//        String orderBy = table.COL_SWI_0_ID_FFN;
//        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, orderBy);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            ResultModel resultModel = getDataResult_FromDb(cursor);
//            allResults.add(resultModel);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return allResults;
//    }

    public ArrayList<ResultModel> getAllResultsBySwimmer_FromDb(int idFFNSwimmer) {
        ArrayList<ResultModel> allResults = new ArrayList<ResultModel>();
        String condition = table.COL_SWI_0_ID_FFN + "=" + idFFNSwimmer;
        String orderBy = table.COL_MEE_ID_MEET + " DESC";
        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ResultModel resultModel = getDataResult_FromDb(cursor);
            allResults.add(resultModel);
            cursor.moveToNext();
        }
        cursor.close();
        return allResults;
    }

//    public ArrayList<ResultModel> getAllResultsByRace_FromDb(int idRace) {
//        ArrayList<ResultModel> allResults = new ArrayList<ResultModel>();
//        String condition = table.COL_RES_ID_RACE + "=" + idRace;
//        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            ResultModel resultModel = getDataResult_FromDb(cursor);
//            allResults.add(resultModel);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return allResults;
//    }

//    public ResultModel getResultById_Fromdb(int resultId) {
//        ResultModel resultToReturn = null;
//        String condition = table.COL_RES_ID + "=" + resultId;
//        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            resultToReturn = getDataResult_FromDb(cursor);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return resultToReturn;
//    }

    /* GET CONTENT */
    private ResultModel getDataResult_FromDb(Cursor cursor) {
        ResultModel resultModel = new ResultModel(cursor.getInt(0)); // resultId
        // if swimmer n°2 doesn't exist ==0 then get 1 swimmer else try to find 10 swimmers max
        if (cursor.getInt(2) == 0) {
            // result is not a relay
            resultModel.setSwimmerModel(new SwimmerModel(cursor.getInt(1))); // swimmerId0
        } else {
            // result is a relay loop from 1 to 10 to get all swimmers
            for (int i = 1; i < 11; i++) {
                resultModel.addSwimmersInTeam(new SwimmerModel(cursor.getInt(i)));
            }
        }
        resultModel.setMeetingId(cursor.getInt(11)); // meetingId
        EventModel eventModel = new EventModel(cursor.getInt(12)); //enventId
        eventModel.setRaceModel(new RaceModel(cursor.getInt(13)));  //raceId
        resultModel.setEventModel(eventModel); // ADD eventModel
        resultModel.setQualifyingTime(cursor.getFloat(14)); //qualifyinfTime
        resultModel.setSwimTime(cursor.getFloat(15));// swimTime
        for (int indexLap = 0; indexLap < 60; indexLap++) {
            float lapToGetFromDb = cursor.getFloat(indexLap + 16);
            if (lapToGetFromDb != 0) {
                resultModel.getLaps().add(lapToGetFromDb);
            }
        }
        return resultModel;
    }

    /* ADDER */
    public void addResult_InDb(ResultModel resultModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(table.COL_RES_ID, resultModel.getId());
        /** SWIMMERS **/
        for (int indSw = 0; indSw < table.TEAM_COLUMNS_AS_STRING_TAB.length; indSw++) {
            if (resultModel.isRelay()) {
                contentValues.put(table.TEAM_COLUMNS_AS_STRING_TAB[indSw], resultModel.getTeam().get(indSw).getIdFFN());
            } else {
                if (indSw == 0) {
                    contentValues.put(table.TEAM_COLUMNS_AS_STRING_TAB[indSw], resultModel.getSwimmerModel().getIdFFN());
                } else {
                    contentValues.put(table.TEAM_COLUMNS_AS_STRING_TAB[indSw], 0);
                }
            }
        }
        /** OTHER **/
        contentValues.put(table.COL_MEE_ID_MEET, resultModel.getMeetingId());
        contentValues.put(table.COL_EVE_ID_EVENT, resultModel.getEventModel().getId());
        contentValues.put(table.COL_RES_ID_RACE, resultModel.getEventModel().getRaceModel().getId());
        contentValues.put(table.COL_RES_QUALIFYING_TIME, resultModel.getQualifyingTime());
        contentValues.put(table.COL_RES_SWIM_TIME, resultModel.getSwimTime());

        /** LAPS **/
        ArrayList<Float> laps = resultModel.getLaps();
        for (int indexLap = 0; indexLap < laps.size(); indexLap++) {
            contentValues.put(table.LAPS_COLUMNS_AS_STRING_TAB[indexLap], resultModel.getLaps().get(indexLap));
        }
        for (int indexLap = laps.size(); indexLap < table.LAPS_COLUMNS_AS_STRING_TAB.length; indexLap++) {
            contentValues.put(table.LAPS_COLUMNS_AS_STRING_TAB[indexLap], 0);
        }

        db.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
    public void deleteResultWithId_InDb(int idResult) {
        String condition = table.COL_RES_ID + " = " + idResult;
        db.delete(table.TABLE_NAME, condition, null);
    }

//    public void deleteResultDependOnAttributes_InDb(int idSwimmer0, int idMeeting, int idEvent, int idRace) {
//        String condition = table.COL_SWI_0_ID_FFN + " = " + idSwimmer0
//                + " AND " + table.COL_MEE_ID_MEET + " = " + idMeeting
//                + " AND " + table.COL_EVE_ID_EVENT + " = " + idEvent
//                + " AND " + table.COL_RES_ID_RACE + " = " + idRace;
//        db.delete(table.TABLE_NAME, condition, null);
//    }

    /* UPDATER */
    public void updateResultForTime_InDb(ResultModel resultModel) {
        deleteResultWithId_InDb(resultModel.getId());
        addResult_InDb(resultModel);
    }

    /* VERIFY ENTRY */
//    public boolean tableIsEmpty() {
//        boolean isEmpty;
//        Cursor cursor = db.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
//
//        if (cursor.getCount() == 0) {
//            isEmpty = true;
//        } else {
//            isEmpty = false;
//        }
//        return isEmpty;
//    }
}
