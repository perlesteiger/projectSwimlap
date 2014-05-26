/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableRecords;

public class RecordUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableRecords table;

    public RecordUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableRecords dbTableRecords) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableRecords;
    }

    /* GETTERS */
//    public RecordModel getAllRecordsOfOneSwimmer_FromDb(int idSwimmer) {
//        RecordModel recordModel = new RecordModel();
//        recordModel.setSwimmerId(idSwimmer);
//
//        String[] swimmerIdAsStrings = {String.valueOf(idSwimmer)};
//        Cursor cursor = sqLiteDatabaseSwimLap.query("table_records", table.ALL_COLUMNS_AS_STRING_TAB, table.COL_RAC_RACE_ID + " = ?", swimmerIdAsStrings, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            recordModel.setSwimtimePool25(cursor.getInt(1), cursor.getFloat(2));
//            recordModel.setSwimtimePool50(cursor.getInt(1), cursor.getFloat(3));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return recordModel;
//    }

//    private float[] getRecordOfOneSwimmerForOneRace_FromDb(int idSwimmer, int idRace) {
//        float[] record = new float[2];
//        String condition = table.COL_SWI_SWIMMER_ID + "=" + idSwimmer + " AND " + table.COL_RAC_RACE_ID + "=" + idRace;
//        Cursor cursor = sqLiteDatabaseSwimLap.query("table_records", table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            record[0] = cursor.getFloat(2);
//            record[1] = cursor.getFloat(3);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return record;
//    }
    /* GET CONTENT */
    // no getData because use an HashMap in RecordModel

    /* ADDER */
    // no adder always use updater

    /* DELETER */
//    public void deleteAllRecordFromASwimmer_InDb(int swimmerIdToDelete) {
//        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_SWI_SWIMMER_ID + " = " + swimmerIdToDelete, null);
//    }

    /* UPDATER */
//    public void updateARecord_InDb(int idSwimmer, int idRace, float newRecordTime, int poolSize) {
//        ContentValues contentValues = new ContentValues();
//        RecordModel recordModelAllReadyInDB = getAllRecordsOfOneSwimmer_FromDb(idSwimmer);
//        if (poolSize == 25 && (recordModelAllReadyInDB.getSwimtimePool25(idRace) > newRecordTime || recordModelAllReadyInDB.getSwimtimePool25(idRace) == 0)) {
//            contentValues.put(table.COL_SWI_SWIMMER_ID, idSwimmer);
//            contentValues.put(table.COL_RAC_RACE_ID, idRace);
//            contentValues.put(table.COL_REC_SWIMTIME_25, newRecordTime);
//        }
//        if (poolSize == 50 && (recordModelAllReadyInDB.getSwimtimePool50(idRace) > newRecordTime || recordModelAllReadyInDB.getSwimtimePool50(idRace) == 0)) {
//            contentValues.put(table.COL_SWI_SWIMMER_ID, idSwimmer);
//            contentValues.put(table.COL_RAC_RACE_ID, idRace);
//            contentValues.put(table.COL_REC_SWIMTIME_25, newRecordTime);
//        }
//        String condition = table.COL_SWI_SWIMMER_ID + "=" + idSwimmer + " AND " + table.COL_RAC_RACE_ID + "=" + idRace;
//        sqLiteDatabaseSwimLap.update(table.TABLE_NAME, contentValues, condition, null);
//    }

    /* VERIFY ENTRY */
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
