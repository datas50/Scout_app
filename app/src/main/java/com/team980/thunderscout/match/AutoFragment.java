package com.team980.thunderscout.match;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.team980.thunderscout.R;
import com.team980.thunderscout.data.enumeration.FuelDumpAmount;

public class AutoFragment extends Fragment implements View.OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener {

    ScoutingFlowActivity scoutingFlowActivity;
    private int m = 0; int del = 0; int drop = 0;
    private RadioGroup highgoalgroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CheckBox crossedBaseline = (CheckBox) view.findViewById(R.id.auto_checkBoxCrossedBaseline);
        crossedBaseline.setOnClickListener(this);
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.AutoPilot);
        checkBox3.setOnClickListener(this);
        CheckBox checkBoxdel = (CheckBox) view.findViewById(R.id.AutoGearsDelivered);
        checkBoxdel.setOnClickListener(this);
        CheckBox checkBoxdrop = (CheckBox) view.findViewById(R.id.AutoDroppedGears);
        checkBoxdrop.setOnClickListener(this);
        highgoalgroup = (RadioGroup) view.findViewById(R.id.autohigh);
        highgoalgroup.setOnCheckedChangeListener(this);


        Button minus = (Button) view.findViewById(R.id.auto_buttonFuelMinus);
        Button plus = (Button) view.findViewById(R.id.auto_buttonFuelPlus);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);

        TextView textValue = (TextView) getView().findViewById(R.id.auto_textViewFuelValue);
        TextView numericalValue = (TextView) getView().findViewById(R.id.auto_textViewFuelNumericalValue);
        textValue.setText(FuelDumpAmount.NONE.toString());
        numericalValue.setText(FuelDumpAmount.NONE.getMinimumAmount() + " - " + FuelDumpAmount.NONE.getMaximumAmount());
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



    public void onCheckedChanged(RadioGroup group, int checkedId1) {

            switch (checkedId1) {
                case R.id.high0:
                    scoutingFlowActivity.getData().setFd2("0");
                    break;
                case R.id.high1:
                    scoutingFlowActivity.getData().setFd2("3");
                    break;
                case R.id.high2:
                    scoutingFlowActivity.getData().setFd2("7");
                    break;
                case R.id.high3:
                    scoutingFlowActivity.getData().setFd2("15");
                    break;
                case R.id.high4:
                    scoutingFlowActivity.getData().setFd2("25");

        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.auto_checkBoxCrossedBaseline) {
            CheckBox checkBox = (CheckBox) view;
            scoutingFlowActivity.getData().setCrossedBaseline(checkBox.isChecked());

        } else if (view.getId() == R.id.AutoPilot) {
                CheckBox checkBox3 = (CheckBox) view;
                if (checkBox3.isChecked()) {
                    m = 1;
                } else {
                    m = 0;
                }

                scoutingFlowActivity.getData().setPilot(m);
        } else if (view.getId() == R.id.AutoGearsDelivered) {
            CheckBox checkBoxdel = (CheckBox) view;
            if (checkBoxdel.isChecked()) {
                del = 1;
            } else {
                del = 0;
            }

            scoutingFlowActivity.getData().setAutoGearsDelivered(del);
        } else if (view.getId() == R.id.AutoDroppedGears) {
            CheckBox checkBoxdrop = (CheckBox) view;
            if (checkBoxdrop.isChecked()) {
                drop = 1;
            } else {
                drop = 0;
            }

            scoutingFlowActivity.getData().setAutoGearsDropped(drop);



        }else {


            FuelDumpAmount value = scoutingFlowActivity.getData().getAutoLowGoalDumpAmount();

            if (view.getId() == R.id.auto_buttonFuelPlus) {
                int newOrdinal = value.ordinal() + 1;

                if ((FuelDumpAmount.values().length - 1) < newOrdinal) {
                    value = FuelDumpAmount.values()[FuelDumpAmount.values().length - 1];
                } else {
                    value = FuelDumpAmount.values()[newOrdinal];
                }

            } else if (view.getId() == R.id.auto_buttonFuelMinus) {
                int newOrdinal = value.ordinal() - 1;

                if (newOrdinal < 0) {
                    value = FuelDumpAmount.values()[0];
                } else {
                    value = FuelDumpAmount.values()[newOrdinal];
                }
            }

            scoutingFlowActivity.getData().setAutoLowGoalDumpAmount(value);

            TextView textValue = (TextView) getView().findViewById(R.id.auto_textViewFuelValue);
            TextView numericalValue = (TextView) getView().findViewById(R.id.auto_textViewFuelNumericalValue);
            textValue.setText(value.toString());
            numericalValue.setText(value.getMinimumAmount() + " - " + value.getMaximumAmount());
        }
    }
}
