/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.parser.FFNexDataGetter;
import com.dim.swimlap.ui.CommunicationFragments;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

;

public class FragmentNavSettings extends Fragment implements View.OnClickListener {

    private Button buttonScan;
    private String fileNameToParse;
    private MeetingModel meetingModel;
    private CommunicationFragments comm;
    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1,
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING = 6,
            VIEW_MEETING_DETAILS = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_settings, container, false);
        buttonScan = (Button) view.findViewById(R.id.id_button_scan_ffnex);
        buttonScan.setOnClickListener(this);
        comm = (CommunicationFragments) this.getActivity();
        return view;
    }

    @Override
    public void onClick(View view) {
        comm.changeVisiblilityOfProgressBar(true);

        if (view.getId() == R.id.id_button_scan_ffnex) {
            Button btn = (Button) view;
            btn.setTextColor(getResources().getColor(R.color.bluesea));
            doParsing();
            comm.changeFragment(VIEW_MEETING);
        }
    }

    public void doParsing() {

        FFNexDataGetter ffnexGetter = new FFNexDataGetter();
        try {
            ffnexGetter.createDirectory();
            String[] files = ffnexGetter.getFiles();
            final String[] items = files;

            if (files == null || files.length == 0) {
                Toast.makeText(getActivity(), "NO file in swimlap directory", Toast.LENGTH_SHORT).show();

            } else if (files.length >= 0) {
                for (int indexFile = 0; indexFile < files.length; indexFile++) {
                    fileNameToParse = files[indexFile];

                    File fileToParse = ffnexGetter.getFFNexFile(fileNameToParse);
                    String stringXMLToParse = ffnexGetter.transformFileToString(fileToParse);
                    meetingModel = ffnexGetter.getResultOfParsing(stringXMLToParse, getActivity());

                    // RECORD IN DB
                    boolean hasBeenRecorded = ffnexGetter.recordParsedMeetingInDb(meetingModel, getActivity());
                    boolean hasBeenFoundInDb = ffnexGetter.recordParsingHasBeenDone(meetingModel.getId(), getActivity());

                    if (hasBeenRecorded && hasBeenFoundInDb) {
                        comm.verifyMeetingOfTheDayAfterParsing();
                        Toast.makeText(getActivity(), "MEETING !\n" + meetingModel.getName() + "\nHAS BEEN RECORDED IN PHONE", Toast.LENGTH_SHORT).show();
                    } else if (!hasBeenRecorded && hasBeenFoundInDb) {
                        Toast.makeText(getActivity(), "MEETING !\n" + meetingModel.getName() + "\nALREADY EXISTS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "A PROBLEM OCCURS DURING PARSING", Toast.LENGTH_SHORT).show();
                    }
                    ffnexGetter.moveFFNexParsed(fileNameToParse);
                }
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(), "A problem occured: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            comm.changeVisiblilityOfProgressBar(false);

        }
    }
}
