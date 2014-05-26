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

import com.dim.swimlap.db.tables.DbTableClubs;
import com.dim.swimlap.models.ClubModel;

public class ClubUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableClubs table;

    /**
     * For this version of SwimLap only one club can be recorded
     */
    public ClubUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableClubs dbTableClubs) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableClubs;
    }

    /* GETTERS */
    public ClubModel getClub_FromDb() {
        ClubModel clubModel =null;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            clubModel = getDataClub_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();

        return clubModel;
    }

    /* GET CONTENT */
    private ClubModel getDataClub_FromDb(Cursor cursor) {
        ClubModel clubModel = new ClubModel(cursor.getInt(0), cursor.getInt(2));
        clubModel.setName(cursor.getString(1));
        return clubModel;
    }

    /* ADDER */
    public void addClub_InDb(ClubModel clubModel) {
        deleteClub_FromDb();
        ContentValues contentValues = new ContentValues();
        contentValues.put(table.COL_CLU_ID, clubModel.getId());
        contentValues.put(table.COL_CLU_NAME, clubModel.getName());
        contentValues.put(table.COL_CLU_CODE_FFN, clubModel.getCodeFFN());

        sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
    public void deleteClub_FromDb() {
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, null, null);
    }
    /* UPDATER */
    // no updater because only one club

    /* VERIFY ENTRY */
//    public boolean tableIsEmpty() {
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
