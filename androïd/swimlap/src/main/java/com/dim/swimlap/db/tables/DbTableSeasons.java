/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableSeasons implements DbTableMODEL {

    // TABLE
    public static final String TABLE_NAME = "table_seasons";
    // COLUMNS
    public static final String COL_SEA_ID = "col_sea_id";
    public static final String COL_SEA_NAME = "col_sea_name";
    public static final String COL_SEA_DATE_START = "col_sea_date_start";
    public static final String COL_SEA_DATE_STOP = "col_sea_date_stop";
    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_SEA_ID + " INTEGER, " +
            COL_SEA_NAME+" TEXT,"+
            COL_SEA_DATE_START + " TEXT, " +
            COL_SEA_DATE_STOP + " TEXT " +
            ");";

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {COL_SEA_ID,
            COL_SEA_NAME,
            COL_SEA_DATE_START,
            COL_SEA_DATE_STOP};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }
}
