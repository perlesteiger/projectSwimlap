/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableResults implements DbTableMODEL {

    /* THIS TABLE *** RESULT *** IS THE JOIN BETWEEN AN EVENT? A SWIMMER AND A MEETING */

    // TABLE
    public static final String TABLE_NAME = "table_results";
    // COLUMNS
    public static final String COL_RES_ID = "col_res_id";

    // FOREIGN KEYS AS PRIMARY KEYS
    public static final String COL_SWI_0_ID_FFN = "col_swi_swimmer_0_id";
    public static final String COL_SWI_1_ID_FFN = "col_swi_swimmer_1_id";
    public static final String COL_SWI_2_ID_FFN = "col_swi_swimmer_2_id";
    public static final String COL_SWI_3_ID_FFN = "col_swi_swimmer_3_id";
    public static final String COL_SWI_4_ID_FFN = "col_swi_swimmer_4_id";
    public static final String COL_SWI_5_ID_FFN = "col_swi_swimmer_5_id";
    public static final String COL_SWI_6_ID_FFN = "col_swi_swimmer_6_id";
    public static final String COL_SWI_7_ID_FFN = "col_swi_swimmer_7_id";
    public static final String COL_SWI_8_ID_FFN = "col_swi_swimmer_8_id";
    public static final String COL_SWI_9_ID_FFN = "col_swi_swimmer_9_id";


    public static final String COL_MEE_ID_MEET = "col_mee_meet_id";
    public static final String COL_EVE_ID_EVENT = "col_eve_even_id";
    public static final String COL_RES_ID_RACE = "col_res_race_id";


    // SWIMMER SPECIFICATIONS
    public static final String COL_RES_QUALIFYING_TIME = "col_res_qualifying_time"; // IMPORTANT !!!
    public static final String COL_RES_SWIM_TIME = "col_res_swim_time"; // final time

    // LAP SPECIFICATION
    public static final String COL_RES_SPLIT_0 = "col_res_split_0";
    public static final String COL_RES_SPLIT_1 = "col_res_split_1";
    public static final String COL_RES_SPLIT_2 = "col_res_split_2";
    public static final String COL_RES_SPLIT_3 = "col_res_split_3";
    public static final String COL_RES_SPLIT_4 = "col_res_split_4";
    public static final String COL_RES_SPLIT_5 = "col_res_split_5";
    public static final String COL_RES_SPLIT_6 = "col_res_split_6";
    public static final String COL_RES_SPLIT_7 = "col_res_split_7";
    public static final String COL_RES_SPLIT_8 = "col_res_split_8";
    public static final String COL_RES_SPLIT_9 = "col_res_split_9";
    public static final String COL_RES_SPLIT_10 = "col_res_split_10";
    public static final String COL_RES_SPLIT_11 = "col_res_split_11";
    public static final String COL_RES_SPLIT_12 = "col_res_split_12";
    public static final String COL_RES_SPLIT_13 = "col_res_split_13";
    public static final String COL_RES_SPLIT_14 = "col_res_split_14";
    public static final String COL_RES_SPLIT_15 = "col_res_split_15";
    public static final String COL_RES_SPLIT_16 = "col_res_split_16";
    public static final String COL_RES_SPLIT_17 = "col_res_split_17";
    public static final String COL_RES_SPLIT_18 = "col_res_split_18";
    public static final String COL_RES_SPLIT_19 = "col_res_split_19";
    public static final String COL_RES_SPLIT_20 = "col_res_split_20";
    public static final String COL_RES_SPLIT_21 = "col_res_split_21";
    public static final String COL_RES_SPLIT_22 = "col_res_split_22";
    public static final String COL_RES_SPLIT_23 = "col_res_split_23";
    public static final String COL_RES_SPLIT_24 = "col_res_split_24";
    public static final String COL_RES_SPLIT_25 = "col_res_split_25";
    public static final String COL_RES_SPLIT_26 = "col_res_split_26";
    public static final String COL_RES_SPLIT_27 = "col_res_split_27";
    public static final String COL_RES_SPLIT_28 = "col_res_split_28";
    public static final String COL_RES_SPLIT_29 = "col_res_split_29";
    public static final String COL_RES_SPLIT_30 = "col_res_split_30";
    public static final String COL_RES_SPLIT_31 = "col_res_split_31";
    public static final String COL_RES_SPLIT_32 = "col_res_split_32";
    public static final String COL_RES_SPLIT_33 = "col_res_split_33";
    public static final String COL_RES_SPLIT_34 = "col_res_split_34";
    public static final String COL_RES_SPLIT_35 = "col_res_split_35";
    public static final String COL_RES_SPLIT_36 = "col_res_split_36";
    public static final String COL_RES_SPLIT_37 = "col_res_split_37";
    public static final String COL_RES_SPLIT_38 = "col_res_split_38";
    public static final String COL_RES_SPLIT_39 = "col_res_split_39";
    public static final String COL_RES_SPLIT_40 = "col_res_split_40";
    public static final String COL_RES_SPLIT_41 = "col_res_split_41";
    public static final String COL_RES_SPLIT_42 = "col_res_split_42";
    public static final String COL_RES_SPLIT_43 = "col_res_split_43";
    public static final String COL_RES_SPLIT_44 = "col_res_split_44";
    public static final String COL_RES_SPLIT_45 = "col_res_split_45";
    public static final String COL_RES_SPLIT_46 = "col_res_split_46";
    public static final String COL_RES_SPLIT_47 = "col_res_split_47";
    public static final String COL_RES_SPLIT_48 = "col_res_split_48";
    public static final String COL_RES_SPLIT_49 = "col_res_split_49";
    public static final String COL_RES_SPLIT_50 = "col_res_split_50";
    public static final String COL_RES_SPLIT_51 = "col_res_split_51";
    public static final String COL_RES_SPLIT_52 = "col_res_split_52";
    public static final String COL_RES_SPLIT_53 = "col_res_split_53";
    public static final String COL_RES_SPLIT_54 = "col_res_split_54";
    public static final String COL_RES_SPLIT_55 = "col_res_split_55";
    public static final String COL_RES_SPLIT_56 = "col_res_split_56";
    public static final String COL_RES_SPLIT_57 = "col_res_split_57";
    public static final String COL_RES_SPLIT_58 = "col_res_split_58";
    public static final String COL_RES_SPLIT_59 = "col_res_split_59";

    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +

            COL_RES_ID + " INTEGER , " +

            // FOREIGN KEY swimmer0 is the one used for individual race

            COL_SWI_0_ID_FFN + " INTEGER , " +
            COL_SWI_1_ID_FFN + " INTEGER , " +
            COL_SWI_2_ID_FFN + " INTEGER , " +
            COL_SWI_3_ID_FFN + " INTEGER , " +
            COL_SWI_4_ID_FFN + " INTEGER , " +
            COL_SWI_5_ID_FFN + " INTEGER , " +
            COL_SWI_6_ID_FFN + " INTEGER , " +
            COL_SWI_7_ID_FFN + " INTEGER , " +
            COL_SWI_8_ID_FFN + " INTEGER , " +
            COL_SWI_9_ID_FFN + " INTEGER , " +


            COL_MEE_ID_MEET + " INTEGER , " +
            COL_EVE_ID_EVENT + " INTEGER , " +
            COL_RES_ID_RACE + " INTEGER , " +

            // SWIMMER SPECIFICATIONS
            COL_RES_QUALIFYING_TIME + " REAL , " +
            COL_RES_SWIM_TIME + " REAL , " +

            // RACE SPECIFICATION
            COL_RES_SPLIT_0 + " REAL , " +
            COL_RES_SPLIT_1 + " REAL , " +
            COL_RES_SPLIT_2 + " REAL , " +
            COL_RES_SPLIT_3 + " REAL , " +
            COL_RES_SPLIT_4 + " REAL , " +
            COL_RES_SPLIT_5 + " REAL , " +
            COL_RES_SPLIT_6 + " REAL , " +
            COL_RES_SPLIT_7 + " REAL , " +
            COL_RES_SPLIT_8 + " REAL , " +
            COL_RES_SPLIT_9 + " REAL , " +
            COL_RES_SPLIT_10 + " REAL , " +
            COL_RES_SPLIT_11 + " REAL , " +
            COL_RES_SPLIT_12 + " REAL , " +
            COL_RES_SPLIT_13 + " REAL , " +
            COL_RES_SPLIT_14 + " REAL , " +
            COL_RES_SPLIT_15 + " REAL , " +
            COL_RES_SPLIT_16 + " REAL , " +
            COL_RES_SPLIT_17 + " REAL , " +
            COL_RES_SPLIT_18 + " REAL , " +
            COL_RES_SPLIT_19 + " REAL , " +
            COL_RES_SPLIT_20 + " REAL , " +
            COL_RES_SPLIT_21 + " REAL , " +
            COL_RES_SPLIT_22 + " REAL , " +
            COL_RES_SPLIT_23 + " REAL , " +
            COL_RES_SPLIT_24 + " REAL , " +
            COL_RES_SPLIT_25 + " REAL , " +
            COL_RES_SPLIT_26 + " REAL , " +
            COL_RES_SPLIT_27 + " REAL , " +
            COL_RES_SPLIT_28 + " REAL , " +
            COL_RES_SPLIT_29 + " REAL , " +
            COL_RES_SPLIT_30 + " REAL , " +
            COL_RES_SPLIT_31 + " REAL , " +
            COL_RES_SPLIT_32 + " REAL , " +
            COL_RES_SPLIT_33 + " REAL , " +
            COL_RES_SPLIT_34 + " REAL , " +
            COL_RES_SPLIT_35 + " REAL , " +
            COL_RES_SPLIT_36 + " REAL , " +
            COL_RES_SPLIT_37 + " REAL , " +
            COL_RES_SPLIT_38 + " REAL , " +
            COL_RES_SPLIT_39 + " REAL , " +
            COL_RES_SPLIT_40 + " REAL , " +
            COL_RES_SPLIT_41 + " REAL , " +
            COL_RES_SPLIT_42 + " REAL , " +
            COL_RES_SPLIT_43 + " REAL , " +
            COL_RES_SPLIT_44 + " REAL , " +
            COL_RES_SPLIT_45 + " REAL , " +
            COL_RES_SPLIT_46 + " REAL , " +
            COL_RES_SPLIT_47 + " REAL , " +
            COL_RES_SPLIT_48 + " REAL , " +
            COL_RES_SPLIT_49 + " REAL , " +
            COL_RES_SPLIT_50 + " REAL , " +
            COL_RES_SPLIT_51 + " REAL , " +
            COL_RES_SPLIT_52 + " REAL , " +
            COL_RES_SPLIT_53 + " REAL , " +
            COL_RES_SPLIT_54 + " REAL , " +
            COL_RES_SPLIT_55 + " REAL , " +
            COL_RES_SPLIT_56 + " REAL , " +
            COL_RES_SPLIT_57 + " REAL , " +
            COL_RES_SPLIT_58 + " REAL , " +
            COL_RES_SPLIT_59 + " REAL " +
            ");";

    public static final String[] TEAM_COLUMNS_AS_STRING_TAB = {COL_SWI_0_ID_FFN,
            COL_SWI_1_ID_FFN,
            COL_SWI_2_ID_FFN,
            COL_SWI_3_ID_FFN,
            COL_SWI_4_ID_FFN,
            COL_SWI_5_ID_FFN,
            COL_SWI_6_ID_FFN,
            COL_SWI_7_ID_FFN,
            COL_SWI_8_ID_FFN,
            COL_SWI_9_ID_FFN};

    public static final String[] LAPS_COLUMNS_AS_STRING_TAB = {
            COL_RES_SPLIT_0,
            COL_RES_SPLIT_1,
            COL_RES_SPLIT_2,
            COL_RES_SPLIT_3,
            COL_RES_SPLIT_4,
            COL_RES_SPLIT_5,
            COL_RES_SPLIT_6,
            COL_RES_SPLIT_7,
            COL_RES_SPLIT_8,
            COL_RES_SPLIT_9,
            COL_RES_SPLIT_10,
            COL_RES_SPLIT_11,
            COL_RES_SPLIT_12,
            COL_RES_SPLIT_13,
            COL_RES_SPLIT_14,
            COL_RES_SPLIT_15,
            COL_RES_SPLIT_16,
            COL_RES_SPLIT_17,
            COL_RES_SPLIT_18,
            COL_RES_SPLIT_19,
            COL_RES_SPLIT_20,
            COL_RES_SPLIT_21,
            COL_RES_SPLIT_22,
            COL_RES_SPLIT_23,
            COL_RES_SPLIT_24,
            COL_RES_SPLIT_25,
            COL_RES_SPLIT_26,
            COL_RES_SPLIT_27,
            COL_RES_SPLIT_28,
            COL_RES_SPLIT_29,
            COL_RES_SPLIT_30,
            COL_RES_SPLIT_31,
            COL_RES_SPLIT_32,
            COL_RES_SPLIT_33,
            COL_RES_SPLIT_34,
            COL_RES_SPLIT_35,
            COL_RES_SPLIT_36,
            COL_RES_SPLIT_37,
            COL_RES_SPLIT_38,
            COL_RES_SPLIT_39,
            COL_RES_SPLIT_40,
            COL_RES_SPLIT_41,
            COL_RES_SPLIT_42,
            COL_RES_SPLIT_43,
            COL_RES_SPLIT_44,
            COL_RES_SPLIT_45,
            COL_RES_SPLIT_46,
            COL_RES_SPLIT_47,
            COL_RES_SPLIT_48,
            COL_RES_SPLIT_49,
            COL_RES_SPLIT_50,
            COL_RES_SPLIT_51,
            COL_RES_SPLIT_52,
            COL_RES_SPLIT_53,
            COL_RES_SPLIT_54,
            COL_RES_SPLIT_55,
            COL_RES_SPLIT_56,
            COL_RES_SPLIT_57,
            COL_RES_SPLIT_58,
            COL_RES_SPLIT_59
    };

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {
            COL_RES_ID,//0

            COL_SWI_0_ID_FFN,
            COL_SWI_1_ID_FFN,
            COL_SWI_2_ID_FFN,
            COL_SWI_3_ID_FFN,
            COL_SWI_4_ID_FFN,
            COL_SWI_5_ID_FFN,
            COL_SWI_6_ID_FFN,
            COL_SWI_7_ID_FFN,
            COL_SWI_8_ID_FFN,
            COL_SWI_9_ID_FFN,

            COL_MEE_ID_MEET,//11
            COL_EVE_ID_EVENT,
            COL_RES_ID_RACE,
            COL_RES_QUALIFYING_TIME,
            COL_RES_SWIM_TIME,

            COL_RES_SPLIT_0,//16
            COL_RES_SPLIT_1,
            COL_RES_SPLIT_2,
            COL_RES_SPLIT_3,
            COL_RES_SPLIT_4,
            COL_RES_SPLIT_5,
            COL_RES_SPLIT_6,
            COL_RES_SPLIT_7,
            COL_RES_SPLIT_8,
            COL_RES_SPLIT_9,
            COL_RES_SPLIT_10,
            COL_RES_SPLIT_11,
            COL_RES_SPLIT_12,
            COL_RES_SPLIT_13,
            COL_RES_SPLIT_14,
            COL_RES_SPLIT_15,
            COL_RES_SPLIT_16,
            COL_RES_SPLIT_17,
            COL_RES_SPLIT_18,
            COL_RES_SPLIT_19,
            COL_RES_SPLIT_20,
            COL_RES_SPLIT_21,
            COL_RES_SPLIT_22,
            COL_RES_SPLIT_23,
            COL_RES_SPLIT_24,
            COL_RES_SPLIT_25,
            COL_RES_SPLIT_26,
            COL_RES_SPLIT_27,
            COL_RES_SPLIT_28,
            COL_RES_SPLIT_29,
            COL_RES_SPLIT_30,
            COL_RES_SPLIT_31,
            COL_RES_SPLIT_32,
            COL_RES_SPLIT_33,
            COL_RES_SPLIT_34,
            COL_RES_SPLIT_35,
            COL_RES_SPLIT_36,
            COL_RES_SPLIT_37,
            COL_RES_SPLIT_38,
            COL_RES_SPLIT_39,
            COL_RES_SPLIT_40,
            COL_RES_SPLIT_41,
            COL_RES_SPLIT_42,
            COL_RES_SPLIT_43,
            COL_RES_SPLIT_44,
            COL_RES_SPLIT_45,
            COL_RES_SPLIT_46,
            COL_RES_SPLIT_47,
            COL_RES_SPLIT_48,
            COL_RES_SPLIT_49,
            COL_RES_SPLIT_50,
            COL_RES_SPLIT_51,
            COL_RES_SPLIT_52,
            COL_RES_SPLIT_53,
            COL_RES_SPLIT_54,
            COL_RES_SPLIT_55,
            COL_RES_SPLIT_56,
            COL_RES_SPLIT_57,
            COL_RES_SPLIT_58,
            COL_RES_SPLIT_59};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }

}
