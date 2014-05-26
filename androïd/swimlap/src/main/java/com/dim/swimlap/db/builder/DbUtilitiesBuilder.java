/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.DbUtilities.ClubUtilities;
import com.dim.swimlap.db.DbUtilities.EventUtilities;
import com.dim.swimlap.db.DbUtilities.MeetingUtilities;
import com.dim.swimlap.db.DbUtilities.RecordUtilities;
import com.dim.swimlap.db.DbUtilities.ResultUtilities;
import com.dim.swimlap.db.DbUtilities.SeasonUtilities;
import com.dim.swimlap.db.DbUtilities.SwimmerUtilities;

import java.sql.SQLException;

public class DbUtilitiesBuilder {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_swimlap.db";

    private ClubUtilities clubUtilities;
    private EventUtilities eventUtilities;
    private MeetingUtilities meetingUtilities;
    private RecordUtilities recordUtilities;
    private ResultUtilities resultUtilities;
    private SeasonUtilities seasonUtilities;
    private SwimmerUtilities swimmerUtilities;

    public DbUtilitiesBuilder(Context context) {
        dbHelper = new DbHelper(context, DATABASE_NAME, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        sqLiteDatabaseSwimLap = dbHelper.getWritableDatabase();
        buildUtilities();
    }

    private void buildUtilities() {
        clubUtilities = new ClubUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableClubs);
        eventUtilities = new EventUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableEvents);
        meetingUtilities = new MeetingUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableMeetings);
        recordUtilities = new RecordUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableRecords);
        resultUtilities = new ResultUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableResults);
        seasonUtilities = new SeasonUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableSeasons);
        swimmerUtilities = new SwimmerUtilities(sqLiteDatabaseSwimLap, dbHelper.dbTableSwimmers);
    }

    public void close() {
        dbHelper.close();
    }

    public ClubUtilities getClubUtilities() {
        return clubUtilities;
    }

    public EventUtilities getEventUtilities() {
        return eventUtilities;
    }

    public MeetingUtilities getMeetingUtilities() {
        return meetingUtilities;
    }

    public RecordUtilities getRecordUtilities() {
        return recordUtilities;
    }

    public ResultUtilities getResultUtilities() {
        return resultUtilities;
    }

    public SeasonUtilities getSeasonUtilities() {
        return seasonUtilities;
    }

    public SwimmerUtilities getSwimmerUtilities() {
        return swimmerUtilities;
    }
}
