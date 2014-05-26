/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.getter;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ClubModel;

import java.sql.SQLException;

public class GetClubForSettings {

    private Context context;

    public GetClubForSettings(Context context) {
        this.context = context;
    }

    public ClubModel getClubRecordedInDb() {
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        ClubModel club = null;
        try {
            db.open();
            club = db.getClubUtilities().getClub_FromDb();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        if (club == null) {
            // give an empty club if no club recorded in db
            club = new ClubModel(0, 888888888);
            club.setName("Club Name");
        }
        return club;
    }
}
