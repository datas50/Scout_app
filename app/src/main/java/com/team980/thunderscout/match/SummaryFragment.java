package com.team980.thunderscout.match;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.team980.thunderscout.R;
import com.team980.thunderscout.data.enumeration.ClimbingStats;

public class SummaryFragment extends Fragment implements View.OnClickListener{

    private ScoutingFlowActivity scoutingFlowActivity;
    private int m = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_summary, container, false);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.pilot);
        checkBox3.setOnClickListener(this);

        return layout;
    }

    public void onClick(View v){
        CheckBox checkBox3 = (CheckBox) v;
        if(checkBox3.isChecked()){
            int m = 1;
        }else{
            int m = 0;
        }

        scoutingFlowActivity.getData().setPilot(m);

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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
