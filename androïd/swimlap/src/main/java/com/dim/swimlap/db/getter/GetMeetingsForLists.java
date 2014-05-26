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
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetMeetingsForLists {
    private Context context;
    private ArrayList<MeetingModel> meetings;
    private ArrayList<MeetingModel> meetingsUnFilled;

    public GetMeetingsForLists(Context context) {
        this.context = context;
    }

    public ArrayList<MeetingModel> getAllMeetingsFilled() {
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            ClubModel club = db1.getClubUtilities().getClub_FromDb();
            if (club != null) {
                meetings = db1.getMeetingUtilities().getAllMeetings_FromDb();
                if (meetings != null) {
                    for (int indexMeeting = 0; indexMeeting < meetings.size(); indexMeeting++) {

                        fillMeetingWithResult(meetings.get(indexMeeting));
                        fillMeetingWithSeason(meetings.get(indexMeeting));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }
        return meetings;
    }

    public ArrayList<MeetingModel> getAllMeetingsUnFilled() {
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            ClubModel club = db1.getClubUtilities().getClub_FromDb();
            if (club != null) {
                meetingsUnFilled = db1.getMeetingUtilities().getAllMeetings_FromDb();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }
        return meetingsUnFilled;
    }

    public void fillMeetingWithResult(MeetingModel meetingToFill) {
        List<ResultModel> resultsInDB;
        DbUtilitiesBuilder db2 = new DbUtilitiesBuilder(context);

        try {
            db2.open();
            resultsInDB = db2.getResultUtilities().getAllResultsByTimeOrderByMeeting_FromDb(meetingToFill.getId());

            /** FOR EACH RESULT IN DB OF MEETING **/
            for (int indexResult = 0; indexResult < resultsInDB.size(); indexResult++) {
                ResultModel resultToFillThenToAdd = resultsInDB.get(indexResult);

                /** TEAM **/
                if (resultToFillThenToAdd.isRelay()) {
                    for (int indexSwimmer = 0; indexSwimmer < resultToFillThenToAdd.getTeam().size(); indexSwimmer++) {
                        int idSwimmerToFill = resultToFillThenToAdd.getTeam().get(indexSwimmer).getIdFFN();
                        resultToFillThenToAdd.getTeam().add(fillSwimmerWithClub(idSwimmerToFill, meetingToFill));
                    }
                } else {
                    /** SWIMMER **/
                    int idSwimmerToFill = resultToFillThenToAdd.getSwimmerModel().getIdFFN();
                    resultToFillThenToAdd.setSwimmerModel(fillSwimmerWithClub(idSwimmerToFill, meetingToFill));
                }
                /** EVENT **/
                int idEvent = resultToFillThenToAdd.getEventModel().getId();
                EventModel eventFromDb = db2.getEventUtilities().getEventModel_FromDb(idEvent, meetingToFill.getId());

                /** ADD EVENT AND BUILD OTHER ATTRIBUTES **/
                float qualifyingTime = resultToFillThenToAdd.getQualifyingTime();
                int poolSize = meetingToFill.getPoolSize();
                int meetingId = meetingToFill.getId();
                resultToFillThenToAdd.buildContent(qualifyingTime, poolSize, meetingId, eventFromDb);

                /** ADD EACH RESULT GETTED FROM DB IN MEETING */
                meetingToFill.addResult(resultToFillThenToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db2.close();
        }
    }

    private SwimmerModel fillSwimmerWithClub(int idSwimmerToFind, MeetingModel meetingToFill) {
        DbUtilitiesBuilder db3 = new DbUtilitiesBuilder(context);
        SwimmerModel swimmerToFill = null;
        try {
            db3.open();
            swimmerToFill = db3.getSwimmerUtilities().getSwimmer_FromDb(idSwimmerToFind);
            int clubCodeFromMeeting = meetingToFill.getClubCode();
            ClubModel clubFromDb = db3.getClubUtilities().getClub_FromDb();
            if (clubFromDb.getCodeFFN() == clubCodeFromMeeting) {

                swimmerToFill.getClubModel().setName(clubFromDb.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db3.close();
        }
        return swimmerToFill;
    }

    public void fillMeetingWithSeason(MeetingModel meetingToFill) {
        SeasonModel seasonOfMeeting = meetingToFill.getSeasonModel();
        String startDateOfMeeting = meetingToFill.getStartDate();
        DbUtilitiesBuilder db4 = new DbUtilitiesBuilder(context);

        try {
            db4.open();
            seasonOfMeeting = db4.getSeasonUtilities().getSeasonByDate_FromDb(startDateOfMeeting);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db4.close();
        }
        meetingToFill.setSeasonModel(seasonOfMeeting);
    }

    public ArrayList<ResultModel> getAllResultsForSwimmerId(int swimmerId) {
        ArrayList<ResultModel> resultsBySwimmer = null;
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);

        try {
            db.open();
            resultsBySwimmer = db.getResultUtilities().getAllResultsBySwimmer_FromDb(swimmerId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return resultsBySwimmer;
    }

    public String getMeetingName(int idMeeting) {
        String meetingName = "";
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        try {
            db.open();
            meetingName = db.getMeetingUtilities().getMeetingName_FromDb(idMeeting);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return meetingName;
    }

    public MeetingModel getMeetingWithId(int meetingId){
        MeetingModel meetingToReturn = null;
        DbUtilitiesBuilder db = new DbUtilitiesBuilder(context);
        try {
            db.open();
            meetingToReturn = db.getMeetingUtilities().getOneMeetingById_FromDb(meetingId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        fillMeetingWithResult(meetingToReturn);
        fillMeetingWithSeason(meetingToReturn);
        return meetingToReturn;
    }
}
