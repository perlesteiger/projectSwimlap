/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableSwimmers implements DbTableMODEL {
    // TABLE
    public static final String TABLE_NAME = "table_swimmers";
    // COLUMNS
    public static final String COL_SWI_ID = "col_swi_id";
    public static final String COL_SWI_NAME = "col_swi_name";
    public static final String COL_SWI_FIRST_NAME = "col_swi_firstname";
    public static final String COL_SWI_DATE_OF_BIRTH = "col_swi_date_of_birth";
    public static final String COL_SWI_GENDER = "col_swi_gender";
    public static final String COL_CLU_CLUB_CODE = "col_clu_club_code";


    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_SWI_ID + " INTEGER, " +
            COL_SWI_NAME + " TEXT, " +
            COL_SWI_FIRST_NAME + " TEXT, " +
            COL_SWI_DATE_OF_BIRTH + " TEXT, " +
            COL_SWI_GENDER + " TEXT, " +
            COL_CLU_CLUB_CODE + " TEXT " +
            ");";

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {COL_SWI_ID,
            COL_SWI_NAME,
            COL_SWI_FIRST_NAME,
            COL_SWI_DATE_OF_BIRTH,
            COL_SWI_GENDER,
            COL_CLU_CLUB_CODE};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }
}
