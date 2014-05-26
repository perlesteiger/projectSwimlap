/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.recorder;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ClubModel;

import java.sql.SQLException;

public class RecordSettings {

    private Context context;

    public RecordSettings(Context context) {
        this.context = context;
    }

    public void recordClub(ClubModel clubModel) {
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        try {
            db.open();
            // only one club in application:
            // no need to update because the club in db is deleted before adding new one
            db.getClubUtilities().addClub_InDb(clubModel);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
