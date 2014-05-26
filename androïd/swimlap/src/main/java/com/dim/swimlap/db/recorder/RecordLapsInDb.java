/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.recorder;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ResultModel;

import java.sql.SQLException;

public class RecordLapsInDb {

    private Context context;

    public RecordLapsInDb(Context context) {
        this.context = context;
    }

    public void recordLaps(ResultModel resultModel) {
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        try {
            db.open();
            db.getResultUtilities().updateResultForTime_InDb(resultModel);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
