/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetClubForSettings;
import com.dim.swimlap.db.recorder.RecordSettings;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.ui.CommunicationFragments;

public class FragmentDataSettings extends Fragment implements View.OnClickListener {

    private Button buttonClub;
    private EditText editTextclubName, editTextclubCodeffn;
    private CommunicationFragments comm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);
        comm = (CommunicationFragments) getActivity();
        // CLUB
        buttonClub = (Button) view.findViewById(R.id.id_button_modifyclub);
        buttonClub.setOnClickListener(this);
        editTextclubName = (EditText) view.findViewById(R.id.id_edittext_clubname);
        editTextclubCodeffn = (EditText) view.findViewById(R.id.id_edittext_club_codeffn);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDataInUI();
        comm.changeVisiblilityOfProgressBar(false);
    }

    @Override
    public void onClick(View view) {
        // TO HIDE KEYBOARD
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        RecordSettings recordSettings = new RecordSettings(getActivity());
        if (view.getId() == R.id.id_button_modifyclub) {
            int codeClubFFN = Integer.valueOf(editTextclubCodeffn.getText().toString());
            ClubModel clubModel = new ClubModel(0, codeClubFFN);
            clubModel.setName(editTextclubName.getText().toString());
            recordSettings.recordClub(clubModel);

            imm.hideSoftInputFromWindow(editTextclubName.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editTextclubCodeffn.getWindowToken(), 0);

            Toast.makeText(getActivity(), "Club Recorded", Toast.LENGTH_SHORT).show();

        }
    }

    public void updateDataInUI() {
        GetClubForSettings getter = new GetClubForSettings(getActivity());
        ClubModel club = getter.getClubRecordedInDb();
        editTextclubName.setText(club.getName(), TextView.BufferType.EDITABLE);
        editTextclubCodeffn.setText(String.valueOf(club.getCodeFFN()), TextView.BufferType.EDITABLE);
    }
}
