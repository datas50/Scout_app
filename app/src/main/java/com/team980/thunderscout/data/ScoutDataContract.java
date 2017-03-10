package com.team980.thunderscout.data;

import android.provider.BaseColumns;

public final class ScoutDataContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ScoutDataContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class ScoutDataTable implements BaseColumns {
        public static final String TABLE_NAME = "scout_data";

        // --- INIT ---
        public static final String COLUMN_NAME_TEAM_NUMBER = "team";
        public static final String COLUMN_NAME_MATCH_NUMBER = "match_number";
        public static final String COLUMN_NAME_ALLIANCE_COLOR = "alliance_color";

        public static final String COLUMN_NAME_DATE_ADDED = "date_added";
        public static final String COLUMN_NAME_DATA_SOURCE = "data_source";

        // --- AUTO ---
        public static final String COLUMN_NAME_PILOT = "pilot";
        public static final String COLUMN_NAME_AUTO_LOW_GOAL_DUMP_AMOUNT = "auto_low_goal_dump_amount";
        public static final String COLUMN_NAME_AUTO_HIGH_GOALS = "auto_high_goals";
        public static final String COLUMN_NAME_AUTO_CROSSED_BASELINE = "auto_crossed_baseline";
        public static final String COLUMN_NAME_AUTO_GEARS_DELIVERED = "auto_gears_delivered";
        public static final String COLUMN_NAME_AUTO_GEARS_DROPPED = "auto_gears_dropped";

        // --- TELEOP ---
        public static final String COLUMN_NAME_CLIMB_TIME = "climbTime";
        public static final String COLUMN_NAME_TELEOP_GEARS_DELIVERED = "teleop_gears_delivered";
        public static final String COLUMN_NAME_TELEOP_COLLECT_GEARS_CHUTE = "collectgearschute";
        public static final String COLUMN_NAME_TELEOP_COLLECT_GEARS_FLOOR = "collectgearsfloor";
        public static final String COLUMN_NAME_TELEOP_GEARS_SCORED = "teleopgearsscored";
        public static final String COLUMN_NAME_TELEOP_GEARS_DROPPED = "teleopgearsdropped";

        public static final String COLUMN_NAME_FUEL_CAPACITY = "Fuel_capacity";

        public static final String COLUMN_NAME_SHOOTING_ACCURACY = "Shooting_accuracy";
        public static final String COLUMN_NAME_SHOOTING_CYCLES = "Shooting_cycles";
        public static final String COLUMN_NAME_LOW_DUMP_CYCLES = "Low_dump_cycles";
        public static final String COLUMN_NAME_ALTER_SHOT = "altshot";
        public static final String COLUMN_NAME_PREVENT_CLIMB = "preventclimb";
        public static final String COLUMN_NAME_BLOCKED_PEG = "blockedpeg";
        public static final String COLUMN_NAME_OTHER = "other";
        public static final String COLUMN_NAME_CLIMBING_STATS = "climbing_stats";
        public static final String COLUMN_NAME_TELEOP_LOW_GOAL_DUMPS = "teleop_low_goal_dumps";
        public static final String COLUMN_NAME_TELEOP_HIGH_GOALS = "teleop_high_goals";
        public static final String COLUMN_NAME_TELEOP_MISSED_HIGH_GOALS = "teleop_missed_high_goals";

        // --- SUMMARY ---
        public static final String COLUMN_NAME_TROUBLE_WITH = "trouble_with";
        public static final String COLUMN_NAME_COMMENTS = "comments";

    }
}

