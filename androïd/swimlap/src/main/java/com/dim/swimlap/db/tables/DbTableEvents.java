/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableEvents implements DbTableMODEL {

/* THIS TABLE *** EVENTS *** THE LIST OF EVENTS IN A MEETING */

    // TABLE
    public static final String TABLE_NAME = "table_events";
    // COLUMNS
    public static final String COL_EVE_ID = "col_eve_id";

    public static final String COL_MEE_ID_MEET = "col_mee_meet_id";
    public static final String COL_RAC_ID_RACE = "col_rac_race_id"; // if null : type is not a race (then type is event)
    public static final String COL_EVE_ROUND_ID = "col_rou_round_id"; // event format: heat=60 , final=11 etc
    public static final String COL_EVE_ORDER = "col_eve_order"; //order of events during meeting


    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +

            COL_EVE_ID + " INTEGER , " +
            COL_MEE_ID_MEET + " INTEGER , " +

            COL_RAC_ID_RACE + " INTEGER , " +
            COL_EVE_ROUND_ID + " INTEGER , " +
            COL_EVE_ORDER + " INTEGER " +
            ");";

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {
            COL_EVE_ID,
            COL_MEE_ID_MEET,
            COL_RAC_ID_RACE,
            COL_EVE_ROUND_ID,
            COL_EVE_ORDER,
    };

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }

}
