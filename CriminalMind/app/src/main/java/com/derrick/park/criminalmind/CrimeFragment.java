package com.derrick.park.criminalmind;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import static android.widget.CompoundButton.*;

/**
 * Created by park on 2017-05-31.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static final String EXTRA_ID = "com.derrick.park.criminalmind.id";



    //method that creates and returns intent based on CrimeActivity class and its fragment,
    // intent contains 1 extra: ID, formatted as a String
    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);


        //unpacking id from extra from intent
        String mId = getActivity().getIntent().getStringExtra(CrimeListFragment.EXTRA_ID);
        // turning string into UUID
        UUID id = UUID.fromString(mId);
        // to be able to call getCrime method we need to call get() method on CrimeLab object:
        CrimeLab crimes = CrimeLab.get(getContext());
        // mCrime object is crime object with matching id.
        mCrime = crimes.getCrime(id);

        // from crime object we can unpack other information: title, date etc.

        mTitleField = (EditText)  v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        // setting checkbutton checked if the crime is solved:
        if (mCrime.isSolved() == true) {
            mSolvedCheckBox.setChecked(true);
        }
        else{
            mSolvedCheckBox.setChecked(false);
        }

        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSolvedCheckBox.setChecked(isChecked);

            }
        });

        return v;
    }


}
