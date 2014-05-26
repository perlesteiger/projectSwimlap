/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.lap.FragmentDataLap;
import com.dim.swimlap.ui.lap.FragmentNavLap;
import com.dim.swimlap.ui.meeting.detail.FragmentDataMeetingDetails;
import com.dim.swimlap.ui.meeting.detail.FragmentNavMeetingDetails;
import com.dim.swimlap.ui.meeting.list.FragmentDataMeetingList;
import com.dim.swimlap.ui.meeting.list.FragmentNavMeetingList;
import com.dim.swimlap.ui.menu.FragmentDataMenu;
import com.dim.swimlap.ui.menu.FragmentNavMenu;
import com.dim.swimlap.ui.ranking.FragmentDataRanking;
import com.dim.swimlap.ui.ranking.FragmentNavRanking;
import com.dim.swimlap.ui.settings.FragmentDataSettings;
import com.dim.swimlap.ui.settings.FragmentNavSettings;
import com.dim.swimlap.ui.simple.FragmentDataSimple;
import com.dim.swimlap.ui.simple.FragmentNavSimple;
import com.dim.swimlap.ui.swimmer.detail.FragmentDataSwimmerDetail;
import com.dim.swimlap.ui.swimmer.detail.FragmentNavSwimmerDetails;
import com.dim.swimlap.ui.swimmer.list.FragmentDataSwimmerList;
import com.dim.swimlap.ui.swimmer.list.FragmentNavSwimmer;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalContainer extends FragmentActivity implements CommunicationFragments {

    private FragmentDirect fragmentDirect;
    private FragmentTitle fragmentTitle;

    private FragmentNavMenu fragmentNavMenu;
    private FragmentDataMenu fragmentDataMenu;

    private FragmentNavLap fragmentNavLap;
    private HashMap<Integer, FragmentDataLap> mapOfFragmentLap;

    private FragmentNavSimple fragmentNavSimple;
    private FragmentDataSimple fragmentDataSimple;

    private FragmentNavMeetingList fragmentNavMeetingList;
    private FragmentDataMeetingList fragmentDataMeetingList;

    private FragmentNavSwimmer fragmentNavSwimmer;
    private FragmentDataSwimmerList fragmentDataSwimmer;

    private FragmentNavSettings fragmentNavSettings;
    private FragmentDataSettings fragmentDataSettings;

    private ArrayList<Integer> historyOfViews;
    private Singleton singleton;
    private ProgressBar progressBar;
    private RelativeLayout layout;
//    private Vibrator vibrator;


    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1,
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING = 6,
            VIEW_MEETING_DETAILS = 7,
            VIEW_SWIMMER_DETAILS = 8;

    private HashMap<Integer, String> titles;


    public GlobalContainer() {
        historyOfViews = new ArrayList<Integer>();
        titles = new HashMap<Integer, String>();
        titles.put(VIEW_MENU, "SwimLap");
        titles.put(VIEW_SIMPLE, "Simple Chronometer");
        titles.put(VIEW_LAP, "Compétition");
        titles.put(VIEW_MEETING, "Meetings");
        titles.put(VIEW_SWIMMER, "Swimmers");
        titles.put(VIEW_SETTING, "Settings");
        titles.put(VIEW_RANKING, "Ranking");
        titles.put(VIEW_MEETING_DETAILS, "Meetings");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);

//        vibrator = (Vibrator) getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);

        /* PROGRESS BAR */
        layout = (RelativeLayout) findViewById(R.id.id_global_container);
        progressBar = new ProgressBar(getApplicationContext(), null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setTag("ProgressBar");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);
        progressBar.setVisibility(View.INVISIBLE);

        /* SINGLETON */
        singleton = Singleton.getInstance();
        singleton.buildMeetingOfTheDay(getApplicationContext());

        // FRAGMENT FOR THE VIEW MENU
        fragmentDirect = new FragmentDirect();
        fragmentTitle = new FragmentTitle(titles.get(VIEW_MENU));

        fragmentNavMenu = new FragmentNavMenu();
        fragmentDataMenu = new FragmentDataMenu();

        // FRAGMENT FOR THE VIEW LAP
        fragmentNavLap = new FragmentNavLap();
        buildFragmentsForLapData();

        // FRAGMENT FOR THE VIEW SIMPLE CHRONOMETER
        fragmentNavSimple = new FragmentNavSimple();
        fragmentDataSimple = new FragmentDataSimple();

        // FRAGMENT FOR THE VIEW MEETING
        fragmentNavMeetingList = new FragmentNavMeetingList();
        fragmentDataMeetingList = new FragmentDataMeetingList();

        //FRAGMENT FOR VIEW SWIMMER
        fragmentNavSwimmer = new FragmentNavSwimmer();
        fragmentDataSwimmer = new FragmentDataSwimmerList();

        // FRAGMENT FOR THE VIEW SETTING
        fragmentNavSettings = new FragmentNavSettings();
        fragmentDataSettings = new FragmentDataSettings();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.id_IN_fragment_direct, fragmentDirect);

        transaction.add(R.id.id_IN_fragment_title, fragmentTitle);
        transaction.add(R.id.id_IN_fragment_nav, fragmentNavMenu);
        transaction.replace(R.id.id_IN_fragment_data, fragmentDataMenu);
        historyOfViews.add(VIEW_MENU);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        int indexOfCurrentView = historyOfViews.size() - 1;
        int currentView = historyOfViews.get(indexOfCurrentView);

        if (currentView == VIEW_MENU) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure to quit?\n You will lost current chronometer !")
                    .setCancelable(true)
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do nothing: don't quit application
                        }
                    })
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (currentView != VIEW_LAP) {
            changeVisiblilityOfProgressBar(true);
            fragmentTitle.setTitle(titles.get(historyOfViews.get(indexOfCurrentView - 1)));
            if (singleton.isThereMeetingToday()) {
                fragmentDirect.changeButtonDirect(historyOfViews.get(indexOfCurrentView - 1));
            }
            historyOfViews.remove(indexOfCurrentView);
            super.onBackPressed();
        }

    }

    @Override
    public void changeFragment(int code) {
        int indexOfCurrentView = historyOfViews.size() - 1;
        int currentView = historyOfViews.get(indexOfCurrentView);
        boolean thereIsMeetingToday = true;

        if (currentView != code) {
            changeVisiblilityOfProgressBar(true);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction newTransaction = manager.beginTransaction();
            if (code == VIEW_MENU) {
                fragmentTitle.setTitle(titles.get(VIEW_MENU));
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMenu);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMenu);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_SIMPLE) {
                fragmentTitle.setTitle(titles.get(VIEW_SIMPLE));
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSimple);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSimple);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_MEETING) {
                fragmentTitle.setTitle(titles.get(VIEW_MEETING));
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMeetingList);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMeetingList);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_SWIMMER) {
                fragmentTitle.setTitle(titles.get(VIEW_SWIMMER));
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSwimmer);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSwimmer);
                newTransaction.addToBackStack(null);

            } else if (code == VIEW_SETTING) {
                fragmentTitle.setTitle(titles.get(VIEW_SETTING));
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_RANKING) {
                fragmentTitle.setTitle(titles.get(VIEW_RANKING));
                FragmentNavRanking fragmentNavRanking = new FragmentNavRanking();
                FragmentDataRanking fragmentDataRanking = new FragmentDataRanking();

                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavRanking);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataRanking);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_LAP) {
                if (singleton.isThereMeetingToday()) {
                    fragmentTitle.setTitle(singleton.getMeetingName());
                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavLap);
                    addFragmentDataLapDependOnRaceId(newTransaction, singleton.getCurrentRaceId());
                } else {
                    Toast.makeText(getApplicationContext(), "No meeting Today.\nUse Simple Chronometer.", Toast.LENGTH_SHORT).show();
                    changeVisiblilityOfProgressBar(false);
                    thereIsMeetingToday = false;
                }
            } else {
                Toast.makeText(this.getApplicationContext(), "A problem appear to get the good fragment", Toast.LENGTH_SHORT).show();
            }

            fragmentDirect.changeButtonStartStop();
            if (thereIsMeetingToday) {
                historyOfViews.add(code);
            }
            fragmentDirect.changeButtonDirect(code);
            newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            newTransaction.commit();
        }
    }

    @Override
    public void replaceFragmentDataLap(int raceId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction newTransaction = manager.beginTransaction();
        addFragmentDataLapDependOnRaceId(newTransaction, raceId);
        newTransaction.addToBackStack(null);
        newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        newTransaction.commit();
    }

    private void addFragmentDataLapDependOnRaceId(FragmentTransaction newTransaction, int newRaceId) {
        FragmentDataLap fragmentLapToAdd = mapOfFragmentLap.get(newRaceId);
        newTransaction.replace(R.id.id_IN_fragment_data, fragmentLapToAdd);
    }

    @Override
    public void getGlobalLap(View view) {
//        vibrator.vibrate(500);
        float lapInMilli = fragmentDirect.getMillisecondsLap();

        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceIdClicked = Integer.valueOf(tag[2]);

        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceIdClicked);
        fragmentDataLap.addLapToModel(view, lapInMilli);

    }

    @Override
    public void unLapLast(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceId = Integer.valueOf(tag[2]);
        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceId);
        fragmentDataLap.unLapLast(view);
    }

    @Override
    public void resetLap(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceId = Integer.valueOf(tag[2]);
        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceId);
        fragmentDataLap.resetLaps(view);
    }

    @Override
    public void recordLap(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceIdClicked = Integer.valueOf(tag[2]);

        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceIdClicked);
        fragmentDataLap.recordLaps(view);
    }

    @Override
    public void inverseButtonsInLap() {
        int indexOfCurrentView = historyOfViews.size() - 1;
        int currentView = historyOfViews.get(indexOfCurrentView);

        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(singleton.getCurrentRaceId());
        if (currentView == VIEW_LAP) {
            fragmentDataLap.changeButtonLap();
        } else {
            changeFragment(VIEW_LAP);
        }
    }

    @Override
    public void replaceFragmentMeetingToDetails(MeetingModel meetingToDetails) {
        changeVisiblilityOfProgressBar(true);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction newTransaction = manager.beginTransaction();

        historyOfViews.add(VIEW_MEETING_DETAILS);

        FragmentNavMeetingDetails fragmentNav = new FragmentNavMeetingDetails(meetingToDetails);
        FragmentDataMeetingDetails fragmentData = new FragmentDataMeetingDetails(meetingToDetails);

        newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNav);
        newTransaction.replace(R.id.id_IN_fragment_data, fragmentData);

        newTransaction.addToBackStack(null);
        newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        newTransaction.commit();
    }

    @Override
    public void replaceFragmentSwimmerToDetails(SwimmerModel swimmerToDetails) {
        changeVisiblilityOfProgressBar(true);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction newTransaction = manager.beginTransaction();

        historyOfViews.add(VIEW_SWIMMER_DETAILS);

        FragmentNavSwimmerDetails fragmentNav = new FragmentNavSwimmerDetails(swimmerToDetails);
        FragmentDataSwimmerDetail fragmentData = new FragmentDataSwimmerDetail(swimmerToDetails);

        newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNav);
        newTransaction.replace(R.id.id_IN_fragment_data, fragmentData);

        newTransaction.addToBackStack(null);
        newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        newTransaction.commit();
    }

    @Override
    public void changeVisiblilityOfProgressBar(boolean mustBeVisible) {
        if (mustBeVisible) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            progressBar.animate();
            progressBar.bringToFront();
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void clickFromSimpleLap(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int position = Integer.valueOf(tag[1]);

        if (view.getId() == R.id.id_button_simple_takelap) {
//            vibrator.vibrate(500);
            float lap = fragmentNavSimple.getMillisecondsSimpleLap();
            fragmentDataSimple.takeLap(lap, position);
        } else if (view.getId() == R.id.id_button_simple_unlap) {
            fragmentDataSimple.removeLastLap(position);
        }
    }

    @Override
    public void removeAllSimpleLaps() {
        fragmentDataSimple.removeAllLapsTaken();
    }

    public void buildFragmentsForLapData() {

        if (mapOfFragmentLap == null) {
            mapOfFragmentLap = new HashMap<Integer, FragmentDataLap>();

        } else {
            mapOfFragmentLap.clear();
        }
        if (singleton.isThereMeetingToday()) {
            ArrayList<EventModel> events = singleton.getAllEventsByOrderInMeeting();
            for (int indexEvent = 0; indexEvent < events.size(); indexEvent++) {
                int raceId = events.get(indexEvent).getRaceModel().getId();
                FragmentDataLap fragmentToAdd = new FragmentDataLap(raceId);
                mapOfFragmentLap.put(raceId, fragmentToAdd);
            }
        }
    }

    @Override
    public void verifyMeetingOfTheDayAfterParsing() {
        singleton.buildMeetingOfTheDay(getApplicationContext());
        buildFragmentsForLapData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_goto_menu:
                changeFragment(VIEW_MENU);
                return true;
            case R.id.id_menu_goto_ranking:
                changeFragment(VIEW_RANKING);
                return true;
            case R.id.id_menu_goto_setting:
                changeFragment(VIEW_SETTING);
                return true;
            case R.id.id_menu_goto_simple:
                changeFragment(VIEW_SIMPLE);
                return true;
            case R.id.id_menu_goto_meeting:
                changeFragment(VIEW_MEETING);
                return true;
            case R.id.id_menu_goto_swimmer:
                changeFragment(VIEW_SWIMMER);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

