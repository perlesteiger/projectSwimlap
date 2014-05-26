/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.recorder;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecordParsingInDb {

    private Context context;

    public RecordParsingInDb(Context context) {
        this.context = context;
    }

    public boolean recordMeetingFromFFNex(MeetingModel meetingModel) {
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);

        boolean isRecorded = false;
        try {
            db.open();
            if (db.getMeetingUtilities().meetingAlready_InDb(meetingModel.getId())) {
                isRecorded = false;
            } else {
                /** SEASON **/
                if (!db.getSeasonUtilities().seasonAlready_InDb(meetingModel.getStartDate())) {
                    db.getSeasonUtilities().addSeason_InDb(meetingModel.getSeasonModel());
                }
                /** MEETING **/
                db.getMeetingUtilities().addMeeting_InDb(meetingModel);
                /** RESULTS **/
                ArrayList<ResultModel> allResults = meetingModel.getAllResults();
                for (int indexResult = 0; indexResult < allResults.size(); indexResult++) {
                    db.getResultUtilities().addResult_InDb(allResults.get(indexResult));

                    if (allResults.get(indexResult).isRelay()) {
                        /** TEAM  add swimmers if not yet in db**/
                        for (int indexSwInRelay = 0; indexSwInRelay < allResults.get(indexResult).getTeam().size(); indexSwInRelay++) {
                            SwimmerModel swimmer = allResults.get(indexResult).getTeam().get(indexSwInRelay);
                            if (!db.getSwimmerUtilities().swimmerAlready_InDb(swimmer.getIdFFN())) {
                                db.getSwimmerUtilities().addSwimmer_InDb(swimmer);
                            }
                        }
                        /** OR **/
                    } else {
                        /** SWIMMER if not yet in db**/
                        SwimmerModel swimmer = allResults.get(indexResult).getSwimmerModel();
                        if (!db.getSwimmerUtilities().swimmerAlready_InDb(swimmer.getIdFFN())) {
                            db.getSwimmerUtilities().addSwimmer_InDb(swimmer);

                        }
                    }

                    /** EVENT if not yet in db**/
                    EventModel event = allResults.get(indexResult).getEventModel();
                    if (!db.getEventUtilities().eventAlready_InDb(event.getId(), allResults.get(indexResult).getMeetingId())) {
                        db.getEventUtilities().addEvent_InDb(event, allResults.get(indexResult).getMeetingId());
                    }
                }
                isRecorded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return isRecorded;
    }
}
