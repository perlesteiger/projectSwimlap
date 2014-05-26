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

import com.dim.swimlap.db.tables.DbTableSeasons;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.objects.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SeasonUtilities {
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableSeasons table;

    public SeasonUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableSeasons dbTableSeasons) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableSeasons;
    }

    /* GETTERS */
    public SeasonModel getSeasonByDate_FromDb(String dateAsString) {
        SeasonModel seasonToFind = null;
        if (tableIsEmpty()) {
            Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SeasonModel seasonFromDb = getDataSeason_FromDb(cursor);
                if (seasonFromDb == null) {
                    DateConverter transformer = new DateConverter();
                    seasonToFind = new SeasonModel(transformer.getTodayAsString());
                } else {
                    SimpleDateFormat format = new SimpleDateFormat(FFNEX_DATE_FORMAT);
                    try {
                        Date startDate = format.parse(seasonFromDb.getStartDate());
                        Date stopDate = format.parse(seasonFromDb.getStopDate());
                        Date currentDate = format.parse(dateAsString);
                        if (startDate.before(currentDate) && stopDate.after(currentDate)) {
                            seasonToFind = seasonFromDb;
                        }

                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
        }

        return seasonToFind;
    }

//    public SeasonModel getSeasonById_FromDb(int seasonId) {
//        SeasonModel seasonToFind = null;
//        if (!tableIsEmpty()) {
//            String condition = table.COL_SEA_ID + "=" + seasonId;
//            Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                seasonToFind = getDataSeason_FromDb(cursor);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return seasonToFind;
//    }

//    public ArrayList<SeasonModel> getAllSeasons_FromDb() {
//        ArrayList<SeasonModel> seasons = new ArrayList<SeasonModel>();
//        if (!tableIsEmpty()) {
//            String orderBy = table.COL_SEA_DATE_START + " DESC";
//            Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, orderBy);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                SeasonModel season = getDataSeason_FromDb(cursor);
//                seasons.add(season);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return seasons;
//    }

    /* GET CONTENT */
    private SeasonModel getDataSeason_FromDb(Cursor cursor) {
        SeasonModel seasonModel = new SeasonModel(cursor.getInt(0));
        seasonModel.setName(cursor.getString(1));
        seasonModel.setStartDate(cursor.getString(2));
        seasonModel.setStartDate(cursor.getString(3));

        return seasonModel;
    }

    /* ADDER */
    public void addSeason_InDb(SeasonModel seasonModel) {
        // FIRST verify the season has an correct id = year of the start date
        int startDateToBeId;
        if (seasonModel.getId() == 0) {
            startDateToBeId = Integer.valueOf(seasonModel.getStartDate().substring(0, 4));
        } else {
            startDateToBeId = seasonModel.getId();
        }
        // SECOND : verify if a season already exist with this id and delete it
        if (!tableIsEmpty()) {
            if (seasonAlready_InDb(seasonModel.getStartDate())) {
                deleteSeason_InDb(seasonModel);
            }
            //THIRD : add the new season
            ContentValues contentValues = new ContentValues();
            contentValues.put(table.COL_SEA_ID, startDateToBeId);
            contentValues.put(table.COL_SEA_NAME, seasonModel.getName());
            contentValues.put(table.COL_SEA_DATE_START, seasonModel.getStartDate());
            contentValues.put(table.COL_SEA_DATE_STOP, seasonModel.getStopDate());

            sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
        }
    }

    /* DELETER */
    public void deleteSeason_InDb(SeasonModel seasonModel) {
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_SEA_ID + " = " + seasonModel.getId(), null);
    }

    /* UPDATER */
//    public void updateSeason_InDb(SeasonModel seasonModel) {
//        deleteSeason_InDb(seasonModel);
//        addSeason_InDb(seasonModel);
//    }

    /* VERIFY ENTRY */
    public boolean seasonAlready_InDb(String startDate) {
        boolean isPresent = true;
        if (getSeasonByDate_FromDb(startDate) == null) {
            isPresent = false;
        }
        return isPresent;
    }

    public boolean tableIsEmpty() {
        boolean isEmpty = false;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }
}
