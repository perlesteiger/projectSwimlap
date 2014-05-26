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

import com.dim.swimlap.db.tables.DbTableSwimmers;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;

public class SwimmerUtilities {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableSwimmers table;

    public SwimmerUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableSwimmers dbTableSwimmers) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableSwimmers;
    }

    /* GETTERS */
    public ArrayList<SwimmerModel> getAllSwimmers_FromDb() {
        ArrayList<SwimmerModel> allSwimmers = new ArrayList<SwimmerModel>();
        String orderBy = table.COL_SWI_NAME;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SwimmerModel swimmer = getDataSwimmer_FromDb(cursor);
            allSwimmers.add(swimmer);
            cursor.moveToNext();
        }
        cursor.close();
        return allSwimmers;
    }

    public SwimmerModel getSwimmer_FromDb(int swimmerIdToGet) {
        String[] swimmerIdAsStrings = {String.valueOf(swimmerIdToGet)};
        SwimmerModel swimmer = null;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, table.COL_SWI_ID + " = ?", swimmerIdAsStrings, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            swimmer = getDataSwimmer_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return swimmer;
    }

    /* GET CONTENT */
    private SwimmerModel getDataSwimmer_FromDb(Cursor cursor) {// cursorToFilm
        SwimmerModel swimmerModel = new SwimmerModel();
        swimmerModel.setIdFFN(cursor.getInt(0));
        swimmerModel.setName(cursor.getString(1));
        swimmerModel.setFirstname(cursor.getString(2));
        swimmerModel.setDateOfBirth(cursor.getString(3));
        swimmerModel.setGender(cursor.getString(4));
        swimmerModel.setClubModel(new ClubModel(0, cursor.getInt(5)));// id , codeFFN
        return swimmerModel;
    }


    /* ADDER */
    public void addSwimmer_InDb(SwimmerModel newSimmer) {
        if (!swimmerAlready_InDb(newSimmer.getIdFFN())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(table.COL_SWI_ID, newSimmer.getIdFFN());
            contentValues.put(table.COL_SWI_NAME, newSimmer.getName());
            contentValues.put(table.COL_SWI_FIRST_NAME, newSimmer.getFirstname());
            contentValues.put(table.COL_SWI_DATE_OF_BIRTH, newSimmer.getDateOfBirth());
            contentValues.put(table.COL_SWI_GENDER, newSimmer.getGender());
            contentValues.put(table.COL_CLU_CLUB_CODE, newSimmer.getClubModel().getCodeFFN());

            sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
        }
    }

    /* DELETER */
//    public void deleteSwimmer_InDb(int swimmerIdToDelete) {
//        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_SWI_ID + " = " + swimmerIdToDelete, null);
//    }

    /* UPDATER */
//    public void updateSwimmer_InDb(SwimmerModel modifiedSwimmer) {
//        deleteSwimmer_InDb(modifiedSwimmer.getIdFFN());
//        addSwimmer_InDb(modifiedSwimmer);
//    }

    /* VERIFY ENTRY */
    public boolean swimmerAlready_InDb(int swimmerIdToSearch) {
        boolean isPresent = true;
        if (getSwimmer_FromDb(swimmerIdToSearch) == null) {
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
