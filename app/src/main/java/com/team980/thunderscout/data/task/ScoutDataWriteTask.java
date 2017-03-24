package com.team980.thunderscout.data.task;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;

import com.team980.thunderscout.ThunderScout;
import com.team980.thunderscout.data.ScoutData;
import com.team980.thunderscout.data.ScoutDataContract;
import com.team980.thunderscout.data.ScoutDataDbHelper;
import com.team980.thunderscout.data.enumeration.ClimbingStats;
import com.team980.thunderscout.info.ThisDeviceFragment;
import com.team980.thunderscout.match.ScoutingFlowActivity;

public class ScoutDataWriteTask extends AsyncTask<Void, Integer, Void> {

    private final ScoutData data;
    private Context context;

    private LocalBroadcastManager localBroadcastManager;

    private ScoutingFlowActivity activity;

    public ScoutDataWriteTask(ScoutData data, Context context) {
        this.data = data;

        this.context = context;

        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public ScoutDataWriteTask(ScoutData data, Context context, ScoutingFlowActivity activity) {
        this(data, context);

        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        //Runs on UI thread before execution
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void[] params) {

        // Gets the data repository in write mode
        SQLiteDatabase db = new ScoutDataDbHelper(context).getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        // Init
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TEAM_NUMBER, data.getTeamNumber());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_MATCH_NUMBER, data.getMatchNumber());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALLIANCE_COLOR, data.getAllianceColor().name());

        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATE_ADDED, data.getDateAdded());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATA_SOURCE, data.getDataSource());

        // Auto
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_PILOT, data.getPilot());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_LOW_GOAL_DUMP_AMOUNT, data.getAutoLowGoalDumpAmount().name());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_HIGH_GOALS, data.getFd2());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_CROSSED_BASELINE, data.hasCrossedBaseline());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_GEARS_DELIVERED, data.getAutoGearsDelivered());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_STARTING_POSITION, data.getstartPos());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_GEARS_DROPPED, data.getAutoGearsDropped());

        // Teleop
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_CLIMB_TIME, data.getClimbtimer());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_DELIVERED, data.getTeleopGearsDelivered());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_CHUTE, data.getCollectGearsChute());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_FLOOR, data.getCollectGearsFloor());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_SCORED, data.getTeleopGearsScored());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_DROPPED, data.getTeleopGearsDropped());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_CAPACITY, data.getFd1());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_SHOOTING_ACCURACY, data.getShootingAccuracy());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_SHOOTING_CYCLES, data.getShootingCycles());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_LOW_DUMP_CYCLES, data.getLowDumpCycles());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALTER_SHOT, data.getAltshot());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_BLOCKED_PEG, data.getBlockedpeg());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_PREVENT_CLIMB, data.getPreventclimb());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_OTHER, data.getOther());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_CLIMBING_STATS, data.getClimbingStats().name());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_LOW_GOAL_DUMPS, ThunderScout.serializeObject(data.getTeleopLowGoalDumps()));
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_HIGH_GOALS, data.getTeleopHighGoals());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_MISSED_HIGH_GOALS, data.getTeleopMissedHighGoals());


        // Summary
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TROUBLE_WITH, data.getTroubleWith());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_COMMENTS, data.getComments());
        values.put(ScoutDataContract.ScoutDataTable.COLUMN_NAME_ROBOT_SUMMARY, data.getrobotPerformance());


        try {
            // Insert the new row
            db.insertOrThrow(
                    ScoutDataContract.ScoutDataTable.TABLE_NAME,
                    null,
                    values);
        } catch (final Exception e) {
            e.printStackTrace();
            if (activity != null) {
                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(new Runnable() {

                    @Override
                    public void run() {  //TODO broadcast reciever
                        activity.dataOutputCallbackFail(ScoutingFlowActivity.OPERATION_SAVE_THIS_DEVICE, e);
                    }
                });
            }
        }

        if (activity != null) {
            Handler handler = new Handler(Looper.getMainLooper());

            handler.post(new Runnable() {

                @Override
                public void run() {  //TODO broadcast reciever
                    activity.dataOutputCallbackSuccess(ScoutingFlowActivity.OPERATION_SAVE_THIS_DEVICE);
                }
            });
        }

        db.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void o) {
        //Runs on UI thread after execution
        super.onPostExecute(o);

        Intent intent = new Intent(ThisDeviceFragment.ACTION_REFRESH_VIEW_PAGER);
        localBroadcastManager.sendBroadcast(intent); //notify the UI thread so we can refresh the ViewPager automatically :D
    }
}
