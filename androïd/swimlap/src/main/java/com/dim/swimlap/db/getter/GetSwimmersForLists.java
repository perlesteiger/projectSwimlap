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
import com.dim.swimlap.models.SwimmerModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetSwimmersForLists {

    private Context context;

    public GetSwimmersForLists(Context context) {
        this.context = context;
    }

    public ArrayList<SwimmerModel> getSwimmerList() {
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        ArrayList<SwimmerModel> swimmerList = null;
        try {
            db1.open();
            swimmerList = db1.getSwimmerUtilities().getAllSwimmers_FromDb();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }
        if (swimmerList != null) {
            fillSwimmerWithClub(swimmerList);
        }
        return swimmerList;
    }

    public void fillSwimmerWithClub(ArrayList<SwimmerModel> swimmerList) {
        DbUtilitiesBuilder db2 = new DbUtilitiesBuilder(context);
        try {
            db2.open();
            ClubModel club = db2.getClubUtilities().getClub_FromDb();
            for (int indexSwimmer = 0; indexSwimmer < swimmerList.size(); indexSwimmer++) {
                if (swimmerList.get(indexSwimmer).getClubModel().getCodeFFN() == club.getCodeFFN()) {
                    swimmerList.get(indexSwimmer).getClubModel().setName(club.getName());
                } else {
                    swimmerList.get(indexSwimmer).getClubModel().setName("Club unknown");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db2.close();
        }
    }
}
