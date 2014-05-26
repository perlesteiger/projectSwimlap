/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.objects.Singleton;

public class FragmentDirect extends Fragment implements View.OnClickListener {

    private Button buttonStart, buttonStop;
    private Button buttonDirect, buttonBackToMenu;
    private Chronometer chronometer;
    private CommunicationFragments communicationFragments;
    private Singleton singleton;
    private Vibrator vibrator;
    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container, false);
        vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);

        buttonDirect = (Button) view.findViewById(R.id.id_button_direct_to_lap);
        buttonDirect.setOnClickListener(this);

        buttonBackToMenu = (Button) view.findViewById(R.id.id_button_back_to_menu);
        buttonBackToMenu.setOnClickListener(this);
        buttonBackToMenu.setVisibility(View.INVISIBLE);

        chronometer = (Chronometer) view.findViewById(R.id.id_chrono_digit);

        buttonStart = (Button) view.findViewById(R.id.id_button_start);
        buttonStart.setOnClickListener(this);
        buttonStart.setVisibility(View.INVISIBLE);


        buttonStop = (Button) view.findViewById(R.id.id_button_stop);
        buttonStop.setOnClickListener(this);
        buttonStop.setVisibility(View.INVISIBLE);

        singleton = Singleton.getInstance();
        singleton.setChronoIsStarted(false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicationFragments = (CommunicationFragments) this.getActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_start) {
            vibrator.vibrate(500);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            singleton.setChronoIsStarted(true);
            changeButtonStartStop();
            communicationFragments.inverseButtonsInLap();
        } else if (view.getId() == R.id.id_button_stop) {
            chronometer.stop();
            singleton.setChronoIsStarted(false);
            changeButtonStartStop();
            communicationFragments.inverseButtonsInLap();
        } else if (view.getId() == R.id.id_button_direct_to_lap) {
            communicationFragments.changeFragment(VIEW_LAP);
            changeButtonDirect(VIEW_LAP);
        } else if (view.getId() == R.id.id_button_back_to_menu) {
            communicationFragments.changeFragment(VIEW_MENU);
            changeButtonDirect(VIEW_MENU);
        }
    }

    public long getMillisecondsLap() {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    public void changeButtonStartStop() {
        if (singleton.isThereMeetingToday()) {
            if (singleton.isChronoStarted()) {
                buttonStart.setVisibility(View.INVISIBLE);
                buttonStop.setVisibility(View.VISIBLE);
            } else {
                buttonStart.setVisibility(View.VISIBLE);
                buttonStop.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void changeButtonDirect(int code) {
        if (singleton.isThereMeetingToday()) {
            if (code == VIEW_LAP) {
                buttonBackToMenu.setVisibility(View.VISIBLE);
                buttonDirect.setVisibility(View.INVISIBLE);

            } else {
                buttonBackToMenu.setVisibility(View.INVISIBLE);
                buttonDirect.setVisibility(View.VISIBLE);
            }
        }

    }

}
