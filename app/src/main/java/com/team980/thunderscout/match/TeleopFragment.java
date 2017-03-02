package com.team980.thunderscout.match;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;


import com.team980.thunderscout.R;
import com.team980.thunderscout.data.enumeration.ClimbingStats;
import com.team980.thunderscout.data.enumeration.FuelDumpAmount;

public class TeleopFragment extends Fragment implements View.OnClickListener, Spinner.OnItemSelectedListener {

    private ScoutingFlowActivity scoutingFlowActivity;

    private LinearLayoutManager layoutManager;
    private DumpCounterAdapter adapter;
    private EditText timeView;
    private EditText timeView1;
    private EditText timeView2;
    private EditText timeView3;
    Handler customHandler = new Handler();
    private Integer presses=0;
    private TextView press;
    private RadioGroup radioGroup0, radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    private RadioButton radioButton0, radioButton1, radioButton2, radioButton3, radioButton4;


    private int seconds = 0;
    private int seconds1 = 0;
    private int seconds2 = 0;
    private int seconds3 = 0;
    //Is the stopwatch running?
    // [0: collect, 1: shoot high]
    private boolean running[] = {false, false, false, false};
    private boolean wasRunning[] = {false, false, false, false};
    private Integer i=0, j = 0, k = 0;

    long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,updateTime=0L;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View layout = inflater.inflate(R.layout.fragment_teleop, container, false);
        runTimer(layout);
        runTimer2(layout);
        runTimer3(layout);
        runTimer4(layout);
        Button startButton = (Button) layout.findViewById(R.id.startcollect);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) layout.findViewById(R.id.pausecollect);
        stopButton.setOnClickListener(this);
        Button startButtonShootHigh = (Button) layout.findViewById(R.id.startShootHigh);
        startButtonShootHigh.setOnClickListener(this);
        Button stopButtonShootHigh = (Button) layout.findViewById(R.id.pauseShootHigh);
        stopButtonShootHigh.setOnClickListener(this);
        Button startDefense = (Button) layout.findViewById(R.id.startDefense);
        startDefense.setOnClickListener(this);
        Button stopDefense = (Button) layout.findViewById(R.id.pauseDefense);
        stopDefense.setOnClickListener(this);
        Button startClimb = (Button) layout.findViewById(R.id.startClimb);
        startClimb.setOnClickListener(this);
        Button stopClimb = (Button) layout.findViewById(R.id.pauseClimb);
        stopClimb.setOnClickListener(this);

        CheckBox checkBox = (CheckBox) layout.findViewById(R.id.cbalteredshot);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.cbpreventedclimb);
        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.cbblockedpeg);
        checkBox.setOnClickListener(this);
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        radioGroup0 = (RadioGroup) layout.findViewById(R.id.dump1);
        radioButton0 = (RadioButton) layout.findViewById(R.id.dump1rb0);
        radioButton1 = (RadioButton) layout.findViewById(R.id.dump1rb1);
        radioButton2 = (RadioButton) layout.findViewById(R.id.dump1rb2);
        radioButton3 = (RadioButton) layout.findViewById(R.id.dump1rb3);
        radioButton4 = (RadioButton) layout.findViewById(R.id.dump1rb4);
        radioGroup0.setOnClickListener(this);

        radioGroup1 = (RadioGroup) layout.findViewById(R.id.dump2);
        radioGroup2 = (RadioGroup) layout.findViewById(R.id.dump3);
        radioGroup3 = (RadioGroup) layout.findViewById(R.id.dump4);
        radioGroup4 = (RadioGroup) layout.findViewById(R.id.dump5);


        //    Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        //    resetButton.setOnClickListener(this);
        return layout;

    }


    //@Override
    public void onPause(int ii) {
        super.onPause();
        wasRunning[ii] = running[ii];
        running[ii] = false;
    }

    //@Override
    public void onResume(int ii) {
        super.onResume();
        if (wasRunning[ii]) {
            running[ii] = true;
        }
    }


    //@Override
    public void onCreate(Bundle savedInstanceState, int ii) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running[ii] = savedInstanceState.getBoolean("running[ii]");
            wasRunning[ii] = savedInstanceState.getBoolean("wasRunning[ii]");
            if (wasRunning[ii]) {
                running[ii] = true;
            }
        }


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startcollect:
                onClickStart(v, 0);
                break;
            case R.id.pausecollect:
                onClickStop(v, 0);
                break;
            case R.id.startShootHigh:
                onClickStart(v, 1);
                break;
            case R.id.pauseShootHigh:
                onClickStop(v, 1);
                break;
            case R.id.startDefense:
                onClickStart(v, 2);
                break;
            case R.id.pauseDefense:
                onClickStop(v, 2);
                break;
            case R.id.startClimb:
                onClickStart(v, 3);
                break;
            case R.id.pauseClimb:
                onClickStop(v, 3);
                break;


            case R.id.teleop_buttonAddFuelDump:
                //onClickReset(v);
                adapter.add(FuelDumpAmount.NONE);
                break;
            case R.id.cbalteredshot:
                CheckBox checkBox = (CheckBox) v;
                if(checkBox.isChecked()){
                    int i = 1;
                }else{
                    int i = 0;
                }

                scoutingFlowActivity.getData().setAltshot(i);
            case R.id.cbblockedpeg:
                CheckBox checkBox1 = (CheckBox) v;
                if(checkBox1.isChecked()){
                    int j = 1;
                }else{
                    int j = 0;
                }

                scoutingFlowActivity.getData().setBlockedpeg(j);
            case R.id.cbpreventedclimb:
                CheckBox checkBox2 = (CheckBox) v;
                if(checkBox2.isChecked()){
                    int k = 1;
                }else{
                    int k = 0;
                }
                scoutingFlowActivity.getData().setPreventclimb(k);
                //case R.id.collectballstimereturn:
                //   scoutingFlowActivity.getData().setCollectballssw(R.id.collectballstimereturn);
            case R.id.dump1:
                int id0 = radioGroup0.getCheckedRadioButtonId();
                radioButton0 = (RadioButton) radioGroup0.findViewById(id0);
                scoutingFlowActivity.getData().setFd1(radioButton0.getText().toString());
            case R.id.dump2:
                int id1 = radioGroup1.getCheckedRadioButtonId();
                radioButton1 = (RadioButton) radioGroup1.findViewById(id1);
                scoutingFlowActivity.getData().setFd2(radioButton1.getText().toString());
            case R.id.dump3:
                int id2 = radioGroup2.getCheckedRadioButtonId();
                radioButton2 = (RadioButton) radioGroup2.findViewById(id2);
                scoutingFlowActivity.getData().setFd3(radioButton2.getText().toString());
            case R.id.dump4:
                int id3 = radioGroup3.getCheckedRadioButtonId();
                radioButton3 = (RadioButton) radioGroup3.findViewById(id3);
                scoutingFlowActivity.getData().setFd4(radioButton3.getText().toString());
            case R.id.dump5:
                int id4 = radioGroup4.getCheckedRadioButtonId();
                radioButton4 = (RadioButton) radioGroup4.findViewById(id4);
                scoutingFlowActivity.getData().setFd5(radioButton4.getText().toString());

        }
    }

    public void onClickStart(View view, int ii) {
        running[ii] = true;
    }
    public void onClickStop(View view, int ii) { running[ii] = false; }




    public void onClickReset(View view, int ii) {
        running[ii] = false;
        seconds = 0;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView fuelDumps = (RecyclerView) view.findViewById(R.id.teleop_recyclerViewFuelDumps);

        layoutManager = new LinearLayoutManager(getContext());
        fuelDumps.setLayoutManager(layoutManager);

        adapter = new DumpCounterAdapter();
        fuelDumps.setAdapter(adapter);



        if (savedInstanceState != null) {
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable("LayoutManager"));
            adapter.onRestoreInstanceState(savedInstanceState);
        }

        Button addDumpButton = (Button) view.findViewById(R.id.teleop_buttonAddFuelDump);
        addDumpButton.setOnClickListener(this);

        Spinner climbingStats = (Spinner) view.findViewById(R.id.teleop_spinnerClimbingStats);
        climbingStats.setOnItemSelectedListener(this);




    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable("LayoutManager", layoutManager.onSaveInstanceState());
        adapter.onSaveInstanceState(savedInstanceState);
        Log.d("InstanceRedux", "Saved");

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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemSelected = (String) parent.getItemAtPosition(position);

        ClimbingStats climbingStats = ClimbingStats.valueOf(itemSelected.toUpperCase().replace(' ', '_'));
        scoutingFlowActivity.getData().setClimbingStats(climbingStats);
    }

    private void runTimer(View view) {
        timeView = (EditText) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            //@Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running[0]) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runTimer2(View view) {
        timeView1 = (EditText) view.findViewById(R.id.shoothightimereturn);
        final Handler handler1 = new Handler();
        handler1.post(new Runnable() {
            @Override
            public void run() {
                int hours1 = seconds1 / 3600;
                int minutes1 = (seconds1 % 3600) / 60;
                int secs1 = seconds1 % 60;
                String time = String.format("%d:%02d:%02d", hours1, minutes1, secs1);
                timeView1.setText(time);
                if (running[1]) {
                    seconds1++;
                }
                handler1.postDelayed(this, 1000);
            }
        });
    }

    private void runTimer3(View view) {
        timeView2 = (EditText) view.findViewById(R.id.defensetimereturn);
        final Handler handler2 = new Handler();
        handler2.post(new Runnable() {
            @Override
            public void run() {
                int hours2 = seconds2 / 3600;
                int minutes2 = (seconds2 % 3600) / 60;
                int secs2 = seconds2 % 60;
                String time = String.format("%d:%02d:%02d", hours2, minutes2, secs2);
                timeView2.setText(time);
                if (running[2]) {
                    seconds2++;
                }
                handler2.postDelayed(this, 1000);
            }
        });
    }

    private void runTimer4(View view) {
        timeView3 = (EditText) view.findViewById(R.id.climbtimereturn);
        final Handler handler3 = new Handler();
        handler3.post(new Runnable() {
            @Override
            public void run() {
                int hours3 = seconds3 / 3600;
                int minutes3 = (seconds3 % 3600) / 60;
                int secs3 = seconds3 % 60;
                String time = String.format("%d:%02d:%02d", hours3, minutes3, secs3);
                timeView3.setText(time);
                if (running[3]) {
                    seconds3++;
                }
                handler3.postDelayed(this, 1000);
            }
        });
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    public DumpCounterAdapter getFuelDumpAdapter() {
        return adapter;
    }

}