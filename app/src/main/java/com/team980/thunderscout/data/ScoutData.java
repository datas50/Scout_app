package com.team980.thunderscout.data;

import com.team980.thunderscout.R;
import com.team980.thunderscout.data.enumeration.AllianceColor;
import com.team980.thunderscout.data.enumeration.ClimbingStats;
import com.team980.thunderscout.data.enumeration.FuelDumpAmount;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Implements data for one team from one match.
 */
public class ScoutData implements Serializable {

    /**
     * ScoutData Version 2017-2
     *
     * 2017-2: Serializable ArrayList for dumps in teleop
     * 2017-1: First 2017 spec
     */
    private static final long serialVersionUID = 4;

    public static final String SOURCE_LOCAL_DEVICE = "This device";

    // INIT
    private String teamNumber;
    private int matchNumber;
    private AllianceColor allianceColor;

    private long dateAdded;
    private String dataSource;

    // AUTO
    private int pilot;
    private String fd2;
    private FuelDumpAmount autoLowGoalDumpAmount;
    private boolean crossedBaseline;
    private int autoGearsDelivered;
    private String startPos;
    private int autoDroppedGears;
    //private int autoHighGoals;
    //private int autoMissedHighGoals;


    // TELEOP
    private String climbtimer;
    private int teleopGearsDelivered;
    private int teleopcollectgearschute;
    private int teleopcollectgearsfloor;
    private int teleopgearsscored;
    private int teleopgearsdropped;
    private String fd1;
    private String shootAcc;
    private int highCycles;
    private int dumpCycles;
    private int altshot;
    private int preventclimb;
    private int blockedpeg;
    private String other;
    private ClimbingStats climbingStats;
    private ArrayList<FuelDumpAmount> teleopLowGoalDumps; //average # of fuel
    private int teleopHighGoals;
    private int teleopMissedHighGoals;


    // SUMMARY
    private String troubleWith;
    private String comments;
    private String robotPerformance;



    public ScoutData() {
        //default values
        autoLowGoalDumpAmount = FuelDumpAmount.NONE;
        teleopLowGoalDumps = new ArrayList<>();
        climbingStats = ClimbingStats.DID_NOT_CLIMB;
    }

    /**
     * Copy constructor
     */
    public ScoutData(ScoutData other) {
        //Init
        setTeamNumber(other.getTeamNumber());
        setMatchNumber(other.getMatchNumber());
        setAllianceColor(other.getAllianceColor());
        setDateAdded(other.getDateAdded());
        setDataSource(other.getDataSource());

        //Auto
        setPilot(other.getPilot());
        setAutoLowGoalDumpAmount(other.getAutoLowGoalDumpAmount());
        setFd2(other.getFd2());
        setCrossedBaseline(other.hasCrossedBaseline());
        setAutoGearsDelivered(other.getAutoGearsDelivered());
        setstartPos(other.getstartPos());
        setAutoGearsDropped(other.getAutoGearsDropped());
        //setAutoHighGoals(other.getAutoHighGoals());
        //setAutoMissedHighGoals(other.getAutoMissedHighGoals());


        //Teleop
        setClimbtimer(other.getClimbtimer());
        setTeleopGearsDelivered(other.getTeleopGearsDelivered());
        setCollectGearsChute(other.getCollectGearsChute());
        setCollectGearsFloor(other.getCollectGearsFloor());
        setTeleopGearsScored(other.getTeleopGearsScored());
        setTeleopGearsDropped(other.getTeleopGearsDropped());
        setFd1(other.getFd1());
        setShootingAccuracy(other.getShootingAccuracy());
        setShootingCycles(other.getShootingCycles());
        setLowDumpCycles(other.getLowDumpCycles());
        setAltshot(other.getAltshot());
        setPreventclimb(other.getPreventclimb());
        setBlockedpeg(other.getBlockedpeg());
        setOther(other.getOther());
        setClimbingStats(other.getClimbingStats());
        teleopLowGoalDumps = other.getTeleopLowGoalDumps();
        setTeleopHighGoals(other.getTeleopHighGoals());
        setTeleopMissedHighGoals(other.getTeleopMissedHighGoals());


        //Summary
        setTroubleWith(other.getTroubleWith());
        setComments(other.getComments());
        setrobotPerformance(other.getrobotPerformance());

    }

    // --- INIT ---

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public AllianceColor getAllianceColor() {
        return allianceColor;
    }

    public void setAllianceColor(AllianceColor allianceColor) {
        this.allianceColor = allianceColor;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long d) {
        dateAdded = d;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String d) {
        dataSource = d;
    }

    // --- AUTO ---
    public int getPilot() { return pilot; }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public FuelDumpAmount getAutoLowGoalDumpAmount() {
        return autoLowGoalDumpAmount;
    }

    public void setAutoLowGoalDumpAmount(FuelDumpAmount autoLowGoalDumpAmount) {
        this.autoLowGoalDumpAmount = autoLowGoalDumpAmount;
    }

    public String getFd2() {
        return fd2;
    }

    public void setFd2 (String fd2) {
        this.fd2 = fd2;
    }


    public boolean hasCrossedBaseline() {
        return crossedBaseline;
    }

    public void setCrossedBaseline(boolean crossedBaseline) {
        this.crossedBaseline = crossedBaseline;
    }

    public int getAutoGearsDelivered() {
        return autoGearsDelivered;
    }

    public void setAutoGearsDelivered(int autoGearsDelivered) {
        this.autoGearsDelivered = autoGearsDelivered;
    }

    public String getstartPos() {
        return startPos;
    }

    public void setstartPos(String startPos) {
        this.startPos = startPos;
    }

    public int getAutoGearsDropped() {
        return autoDroppedGears;
    }

    public void setAutoGearsDropped(int autoDroppedGears) {
        this.autoDroppedGears = autoDroppedGears;
    }



    // --- TELEOP ---

    public String getClimbtimer() {
        return climbtimer;
    }

    public void setClimbtimer (String time_view) {
        this.climbtimer = time_view;
    }


    public int getTeleopGearsDelivered() {
        return teleopGearsDelivered;
    }

    public void setTeleopGearsDelivered(int teleopGearsDelivered) {
        this.teleopGearsDelivered = teleopGearsDelivered;
    }

    public int getCollectGearsChute() {
        return teleopcollectgearschute;
    }

    public void setCollectGearsChute(int collectgearschute) {
        this.teleopcollectgearschute = collectgearschute;
    }

    public int getCollectGearsFloor() {
        return teleopcollectgearsfloor;
    }

    public void setCollectGearsFloor (int collectgearsfloor) {
        this.teleopcollectgearsfloor = collectgearsfloor;
    }

    public int getTeleopGearsScored() {
        return teleopgearsscored;
    }

    public void setTeleopGearsScored (int teleopgearsscored) {
        this.teleopgearsscored = teleopgearsscored;
    }

    public int getTeleopGearsDropped() {
        return teleopgearsdropped;
    }

    public void setTeleopGearsDropped (int teleopgearsdropped) {
        this.teleopgearsdropped = teleopgearsdropped;
    }


    public String getFd1() {
        return fd1;
    }

    public void setFd1 (String fd1) {
        this.fd1 = fd1;
    }

    public String getShootingAccuracy() {
        return shootAcc;
    }

    public void setShootingAccuracy (String shootAcc) {
        this.shootAcc = shootAcc;
    }

    public int getShootingCycles() {
        return highCycles;
    }

    public void setShootingCycles (int highCycles) {
        this.highCycles = highCycles;
    }

    public int getLowDumpCycles() {
        return dumpCycles;
    }

    public void setLowDumpCycles (int dumpCycles) {
        this.dumpCycles = dumpCycles;
    }


    public int getAltshot() {
        return altshot;
    }

    public void setAltshot (int altshot) {
        this.altshot = altshot;
    }

    public int getPreventclimb() {
        return preventclimb;
    }

    public void setPreventclimb (int preventclimb) {
        this.preventclimb = preventclimb;
    }

    public int getBlockedpeg() {
        return blockedpeg;
    }

    public void setBlockedpeg (int blockedpeg) {
        this.blockedpeg = blockedpeg;
    }

    public String getOther() {
        return other;
    }

    public void setOther (String other) {
        this.other = other;
    }

    public ClimbingStats getClimbingStats() {
        return climbingStats;
    }

    public void setClimbingStats(ClimbingStats climbingStats) {
        this.climbingStats = climbingStats;
    }


    public ArrayList<FuelDumpAmount> getTeleopLowGoalDumps() {
        return teleopLowGoalDumps;
    }

    public int getTeleopHighGoals() {
        return teleopHighGoals;
    }

    public void setTeleopHighGoals(int teleopHighGoals) {
        this.teleopHighGoals = teleopHighGoals;
    }

    public int getTeleopMissedHighGoals() {
        return teleopMissedHighGoals;
    }

    public void setTeleopMissedHighGoals(int teleopMissedHighGoals) {
        this.teleopMissedHighGoals = teleopMissedHighGoals;
    }



    // --- SUMMARY ---

    public String getTroubleWith() {
        return troubleWith;
    }

    public void setTroubleWith(String troubleWith) {
        this.troubleWith = troubleWith;
    }

    public String getComments() { return comments; }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getrobotPerformance() { return robotPerformance; }

    public void setrobotPerformance(String robotPerformance) {
        this.robotPerformance = robotPerformance;
    }





    // --- OTHER METHODS ---

    public String[] toStringArray() {
        ArrayList<String> fieldList = new ArrayList<>();

        //Init
        fieldList.add(getTeamNumber());
        fieldList.add(String.valueOf(getMatchNumber()));
        fieldList.add(getAllianceColor().name());
        fieldList.add(String.valueOf(getDateAdded()));
        fieldList.add(getDataSource());

        //Auto
        fieldList.add(String.valueOf(getPilot()));
        fieldList.add(getAutoLowGoalDumpAmount().name());


        fieldList.add(String.valueOf(getFd2()));
        if (hasCrossedBaseline())
        {
            fieldList.add("1");
        }
        else
        {
            fieldList.add("0");
        }
        fieldList.add(String.valueOf(getAutoGearsDelivered()));
        fieldList.add(String.valueOf(getstartPos()));
        fieldList.add(String.valueOf(getAutoGearsDropped()));
        //fieldList.add(String.valueOf(getAutoHighGoals()));
        //fieldList.add(String.valueOf(getAutoMissedHighGoals()));



        //Teleop
        fieldList.add(String.valueOf(getClimbtimer()));
        fieldList.add(String.valueOf(getTeleopGearsDelivered()));
        fieldList.add(String.valueOf(getCollectGearsChute()));
        fieldList.add(String.valueOf(getCollectGearsFloor()));
        fieldList.add(String.valueOf(getTeleopGearsScored()));
        fieldList.add(String.valueOf(getTeleopGearsDropped()));
        fieldList.add(String.valueOf(getFd1()));
        fieldList.add(String.valueOf(getShootingAccuracy()));
        fieldList.add(String.valueOf(getShootingCycles()));
        fieldList.add(String.valueOf(getLowDumpCycles()));
        fieldList.add(String.valueOf(getAltshot()));
        fieldList.add(String.valueOf(getPreventclimb()));
        fieldList.add(String.valueOf(getBlockedpeg()));
        fieldList.add(String.valueOf(getOther()));
        fieldList.add(getClimbingStats().name());
        fieldList.add(getTeleopLowGoalDumps().toString());
        fieldList.add(String.valueOf(getTeleopHighGoals()));
        fieldList.add(String.valueOf(getTeleopMissedHighGoals()));


        //Summary
        fieldList.add(String.valueOf(getTroubleWith()));
        fieldList.add(String.valueOf(getComments()));
        fieldList.add(String.valueOf(getrobotPerformance()));


        return (String[]) fieldList.toArray(new String[fieldList.size()]);
    }
}
