package com.team980.thunderscout.data.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.opencsv.CSVWriter;
import com.team980.thunderscout.ThunderScout;
import com.team980.thunderscout.data.ScoutData;
import com.team980.thunderscout.data.ScoutDataContract;
import com.team980.thunderscout.data.ScoutDataDbHelper;
import com.team980.thunderscout.data.enumeration.AllianceColor;
import com.team980.thunderscout.data.enumeration.ClimbingStats;
import com.team980.thunderscout.data.enumeration.FuelDumpAmount;
import com.team980.thunderscout.info.LocalDataAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVExportTask extends AsyncTask<Void, String, File> {

    private Context context;

    public CSVExportTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    public File doInBackground(Void... params) {

        SQLiteDatabase db = new ScoutDataDbHelper(context).getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                //ScoutDataContract.ScoutDataTable._ID,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TEAM_NUMBER,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_MATCH_NUMBER,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALLIANCE_COLOR,

                ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATE_ADDED,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATA_SOURCE,

                ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_GEARS_DELIVERED,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_LOW_GOAL_DUMP_AMOUNT,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_HIGH_GOALS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_MISSED_HIGH_GOALS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_CROSSED_BASELINE,

                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_DELIVERED,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_CHUTE,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_FLOOR,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_SCORED,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_COLLECT_BALLS_TIME,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_1,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_2,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_3,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_4,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_5,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALTER_SHOT,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_PREVENT_CLIMB,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_BLOCKED_PEG,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_OTHER,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_LOW_GOAL_DUMPS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_HIGH_GOALS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_MISSED_HIGH_GOALS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_CLIMBING_STATS,

                ScoutDataContract.ScoutDataTable.COLUMN_NAME_TROUBLE_WITH,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_COMMENTS,
                ScoutDataContract.ScoutDataTable.COLUMN_NAME_PILOT
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ScoutDataContract.ScoutDataTable._ID + " DESC";

        Cursor cursor;

        try {
            cursor = db.query(
                    ScoutDataContract.ScoutDataTable.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );
        } catch (SQLiteException e) {
            e.printStackTrace();
            return null;
        }

        CSVWriter writer;

        File dir = new File(Environment.getExternalStorageDirectory(), "ThunderScout");
        /*
        if (!dir.exists()) {
            dir.mkdirs();
        }
        */
        dir.mkdir();

        File csv = new File(dir, "EXPORTED_" + System.currentTimeMillis() + ".csv");

        try {
            writer = new CSVWriter(new FileWriter(csv), ',');
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        writer.writeNext(projection); //This adds headers to the file! ;)

        if (cursor.moveToFirst()) {
            addDataToFile(writer, cursor);
        }

        while (cursor.moveToNext()) {
            addDataToFile(writer, cursor);
        }

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            //ignore
        }

        publishProgress(csv.getAbsolutePath());

        cursor.close();
        db.close();
        return csv;
    }

    private void addDataToFile(CSVWriter writer, Cursor cursor) {
        ScoutData data = initScoutData(cursor);
        writer.writeNext(data.toStringArray());
    }

    private ScoutData initScoutData(Cursor cursor) {
        ScoutData data = new ScoutData();

        // Init
        String teamNumber = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TEAM_NUMBER));

        data.setTeamNumber(teamNumber);

        int matchNumber = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_MATCH_NUMBER));

        data.setMatchNumber(matchNumber);

        String allianceColor = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALLIANCE_COLOR));

        data.setAllianceColor(AllianceColor.valueOf(allianceColor));

        String dateAdded = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATE_ADDED));

        try {
            data.setDateAdded(Long.valueOf(dateAdded));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String dataSource = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_DATA_SOURCE));

        data.setDataSource(dataSource);

        // Auto
        int autoGearsDelivered = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_GEARS_DELIVERED));

        data.setAutoGearsDelivered(autoGearsDelivered);

        String autoLowGoalDumpAmount = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_LOW_GOAL_DUMP_AMOUNT));

        data.setAutoLowGoalDumpAmount(FuelDumpAmount.valueOf(autoLowGoalDumpAmount));

        int autoHighGoals = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_HIGH_GOALS));

        data.setAutoHighGoals(autoHighGoals);

        int autoMissedHighGoals = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_MISSED_HIGH_GOALS));

        data.setAutoMissedHighGoals(autoMissedHighGoals);

        int crossedBaseline = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_AUTO_CROSSED_BASELINE));

        data.setCrossedBaseline(crossedBaseline != 0); //I2B conversion

        // Teleop

        String timeView = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_COLLECT_BALLS_TIME));

        data.setCollectballssw(timeView);

        int teleopGearsCollectedChute = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_CHUTE));

        data.setCollectGearsChute(teleopGearsCollectedChute);

        int teleopGearsCollectedFloor = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_COLLECT_GEARS_FLOOR));

        data.setCollectGearsFloor(teleopGearsCollectedFloor);

        int teleopGearsScored = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_SCORED));

        data.setTeleopGearsScored(teleopGearsScored);


        int teleopGearsDelivered = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_GEARS_DELIVERED));

        data.setTeleopGearsDelivered(teleopGearsDelivered);



        byte[] teleopLowGoalDumps = cursor.getBlob(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_LOW_GOAL_DUMPS));

        data.getTeleopLowGoalDumps().addAll((ArrayList<FuelDumpAmount>) ThunderScout.deserializeObject(teleopLowGoalDumps));

        int teleopHighGoals = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_HIGH_GOALS));

        data.setTeleopHighGoals(teleopHighGoals);

        int teleopMissedHighGoals = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TELEOP_MISSED_HIGH_GOALS));

        data.setTeleopMissedHighGoals(teleopMissedHighGoals);

        String fd1 = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_1));

        data.setFd1(fd1);

        String fd2 = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_2));

        data.setFd1(fd2);

        String fd3 = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_3));

        data.setFd1(fd3);

        String fd4 = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_4));

        data.setFd1(fd4);

        String fd5 = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_FUEL_DUMP_5));

        data.setFd1(fd5);

        // to an integer.
        int checkBox = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_ALTER_SHOT));

        data.setAltshot(checkBox);

        int checkBox1 = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_BLOCKED_PEG));

        data.setBlockedpeg(checkBox1);

        int checkBox2 = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_PREVENT_CLIMB));

        data.setPreventclimb(checkBox2);

        String other = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_OTHER));

        data.setOther(other);


        String climbingStats = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_CLIMBING_STATS));

        data.setClimbingStats(ClimbingStats.valueOf(climbingStats));

        // Summary
        String troubleWith = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_TROUBLE_WITH));

        data.setTroubleWith(troubleWith);

        String comments = cursor.getString(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_COMMENTS));

        data.setComments(comments);

        int checkBox3 = cursor.getInt(
                cursor.getColumnIndexOrThrow(ScoutDataContract.ScoutDataTable.COLUMN_NAME_PILOT));

        data.setPilot(checkBox3);

        return data;
    }

    @Override
    protected void onProgressUpdate(String[] values) {
        //Runs on UI thread when publishProgress() is called
        Toast.makeText(context, "CSV export complete: " + values[0], Toast.LENGTH_LONG).show();

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(File file) {
        //Runs on UI thread after execution

        String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(".CSV");

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(FileProvider.getUriForFile(context, "com.team980.thunderscout.provider", file), mime);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //((Activity) context).startActivityForResult(intent, 0); //shh.. this works, I guess?

        super.onPostExecute(file);
    }

}
