/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.dim.swimlap.db.tables.DbTableClubs;
import com.dim.swimlap.db.tables.DbTableEvents;
import com.dim.swimlap.db.tables.DbTableMODEL;
import com.dim.swimlap.db.tables.DbTableMeetings;
import com.dim.swimlap.db.tables.DbTableRecords;
import com.dim.swimlap.db.tables.DbTableResults;
import com.dim.swimlap.db.tables.DbTableSeasons;
import com.dim.swimlap.db.tables.DbTableSwimmers;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private List<DbTableMODEL> tablesOfDb;

    public DbTableClubs dbTableClubs;
    public DbTableEvents dbTableEvents;
    public DbTableMeetings dbTableMeetings;
    public DbTableRecords dbTableRecords;
    public DbTableResults dbTableResults;
    public DbTableSeasons dbTableSeasons;
    public DbTableSwimmers dbTableSwimmers;

    public DbHelper(Context context, String databaseName, int dbVersion) {

        super(context, Environment.getExternalStorageDirectory() + "/swimlap/" + databaseName, null, dbVersion);

        tablesOfDb = new ArrayList<DbTableMODEL>();
        dbTableClubs = new DbTableClubs();
        tablesOfDb.add(dbTableClubs);

        dbTableEvents = new DbTableEvents();
        tablesOfDb.add(dbTableEvents);

        dbTableMeetings = new DbTableMeetings();
        tablesOfDb.add(dbTableMeetings);

        dbTableRecords = new DbTableRecords();
        tablesOfDb.add(dbTableRecords);

        dbTableResults = new DbTableResults();
        tablesOfDb.add(dbTableResults);

        dbTableSeasons = new DbTableSeasons();
        tablesOfDb.add(dbTableSeasons);

        dbTableSwimmers = new DbTableSwimmers();
        tablesOfDb.add(dbTableSwimmers);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
            sqLiteDatabase.execSQL(tablesOfDb.get(indexTable).getRequestTableCreate());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablesOfDb.get(indexTable).getTableName());
        }
        onCreate(sqLiteDatabase);
    }

//    public DbTableMODEL getTableFromDb(String tableName) {
//        DbTableMODEL dbTableMODEL = null;
//        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
//            if (tableName.equals(tablesOfDb.get(indexTable).getTableName())) {
//                dbTableMODEL = tablesOfDb.get(indexTable);
//            }
//        }
//        return dbTableMODEL;
//    }
}
