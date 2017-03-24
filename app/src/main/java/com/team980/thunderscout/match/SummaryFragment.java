package com.team980.thunderscout.match;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.team980.thunderscout.R;
import com.team980.thunderscout.data.enumeration.ClimbingStats;

public class SummaryFragment extends Fragment implements android.widget.RadioGroup.OnCheckedChangeListener{

    private ScoutingFlowActivity scoutingFlowActivity;
    private RadioGroup sumgroup;
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_summary, container, false);
        sumgroup = (RadioGroup) layout.findViewById(R.id.summaryrbgroup);
        sumgroup.setOnCheckedChangeListener(this);

        return layout;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        scoutingFlowActivity = (ScoutingFlowActivity) getActivity();
    }

    public void onCheckedChanged(RadioGroup sumgroup, int checkedId1) {
        // checkedId is the RadioButton selected
        switch (checkedId1) {
            case R.id.summaryrb0:
                scoutingFlowActivity.getData().setrobotPerformance("Fully Working");
                break;
            case R.id.summaryrb1:
                scoutingFlowActivity.getData().setrobotPerformance("Partially Working");
                break;
            case R.id.summaryrb2:
                scoutingFlowActivity.getData().setrobotPerformance("No show");
                break;
            case R.id.summaryrb3:
                scoutingFlowActivity.getData().setrobotPerformance("Died");
                break;

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
