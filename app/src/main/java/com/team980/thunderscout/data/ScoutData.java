package com.team980.thunderscout.data;

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
    private int autoGearsDelivered;
    private FuelDumpAmount autoLowGoalDumpAmount;
    private int autoHighGoals;
    private int autoMissedHighGoals;
    private boolean crossedBaseline;

    // TELEOP
    private int teleopGearsDelivered;
    private int collectgearschute;
    private int collectgearsfloor;
    private int teleopgearsscored;
    private ArrayList<FuelDumpAmount> teleopLowGoalDumps; //average # of fuel
    private int teleopHighGoals;
    private int teleopMissedHighGoals;
    private ClimbingStats climbingStats;
    private long collectballssw;
    private String fd1;
    private String fd2;
    private String fd3;
    private String fd4;
    private String fd5;
    private boolean altshot;
    private boolean preventclimb;
    private boolean blockedpeg;
    private String other;



    // SUMMARY
    private String troubleWith;
    private String comments;
    private boolean pilot;


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
        setAutoGearsDelivered(other.getAutoGearsDelivered());
        setAutoLowGoalDumpAmount(other.getAutoLowGoalDumpAmount());
        setAutoHighGoals(other.getAutoHighGoals());
        setAutoMissedHighGoals(other.getAutoMissedHighGoals());
        setCrossedBaseline(other.hasCrossedBaseline());

        //Teleop
        setTeleopGearsDelivered(other.getTeleopGearsDelivered());
        teleopLowGoalDumps = other.getTeleopLowGoalDumps();
        setTeleopHighGoals(other.getTeleopHighGoals());
        setTeleopMissedHighGoals(other.getTeleopMissedHighGoals());
        setClimbingStats(other.getClimbingStats());

        //Summary
        setTroubleWith(other.getTroubleWith());
        setComments(other.getComments());
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

    public int getAutoGearsDelivered() {
        return autoGearsDelivered;
    }

    public void setAutoGearsDelivered(int autoGearsDelivered) {
        this.autoGearsDelivered = autoGearsDelivered;
    }

    public FuelDumpAmount getAutoLowGoalDumpAmount() {
        return autoLowGoalDumpAmount;
    }

    public void setAutoLowGoalDumpAmount(FuelDumpAmount autoLowGoalDumpAmount) {
        this.autoLowGoalDumpAmount = autoLowGoalDumpAmount;
    }

    public int getAutoHighGoals() {
        return autoHighGoals;
    }

    public void setAutoHighGoals(int autoHighGoals) {
        this.autoHighGoals = autoHighGoals;
    }

    public int getAutoMissedHighGoals() {
        return autoMissedHighGoals;
    }

    public void setAutoMissedHighGoals(int autoMissedHighGoals) {
        this.autoMissedHighGoals = autoMissedHighGoals;
    }

    public boolean hasCrossedBaseline() {
        return crossedBaseline;
    }

    public void setCrossedBaseline(boolean crossedBaseline) {
        this.crossedBaseline = crossedBaseline;
    }

    // --- TELEOP ---

    public int getTeleopGearsDelivered() {
        return teleopGearsDelivered;
    }

    public void setTeleopGearsDelivered(int teleopGearsDelivered) {
        this.teleopGearsDelivered = teleopGearsDelivered;
    }

    public int getCollectGearsChute() {
        return collectgearschute;
    }

    public void setCollectGearsChute(int collectgearschute) {
        this.collectgearschute = collectgearschute;
    }

    public int getCollectGearsFloor() {
        return collectgearschute;
    }

    public void setCollectGearsFloor (int collectgearsfloor) {
        this.collectgearsfloor = collectgearsfloor;
    }

    public int getTeleopGearsScored() {
        return teleopgearsscored;
    }

    public void setTeleopGearsScored (int collectgearsfloor) {
        this.teleopgearsscored = teleopgearsscored;
    }

    public long getCollectballssw() {
        return collectballssw;
    }

    public void setCollectballssw (int collectballssw) {
        this.collectballssw = collectballssw;
    }

    public String getFd1() {
        return fd1;
    }

    public void setFd1 (String fd1) {
        this.fd1 = fd1;
    }

    public String getFd2() {
        return fd2;
    }

    public void setFd2 (String fd2) {
        this.fd2 = fd2;
    }

    public String getFd3() {
        return fd3;
    }

    public void setFd3 (String fd3) {
        this.fd3 = fd3;
    }

    public String getFd4() {
        return fd4;
    }

    public void setFd4 (String fd4) {
        this.fd4 = fd4;
    }

    public String getFd5() {
        return fd5;
    }

    public void setFd5 (String fd5) {
        this.fd5 = fd5;
    }

    public Boolean getAltshot() {
        return altshot;
    }

    public void setAltshot (Boolean altshot) {
        this.altshot = altshot;
    }

    public Boolean getPreventclimb() {
        return preventclimb;
    }

    public void setPreventclimb (Boolean preventclimb) {
        this.preventclimb = preventclimb;
    }

    public Boolean getBlockedpeg() {
        return blockedpeg;
    }

    public void setBlockedpeg (Boolean blockedpeg) {
        this.blockedpeg = blockedpeg;
    }

    public String getOther() {
        return other;
    }

    public void setOther (String other) {
        this.other = other;
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

    public ClimbingStats getClimbingStats() {
        return climbingStats;
    }

    public void setClimbingStats(ClimbingStats climbingStats) {
        this.climbingStats = climbingStats;
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

    public Boolean getPilot() { return pilot; }

    public void setPilot(Boolean comments) {
        this.pilot = pilot;
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
        fieldList.add(String.valueOf(getAutoGearsDelivered()));
        fieldList.add(getAutoLowGoalDumpAmount().name());
        fieldList.add(String.valueOf(getAutoHighGoals()));
        fieldList.add(String.valueOf(getAutoMissedHighGoals()));
        fieldList.add(String.valueOf(hasCrossedBaseline()));

        //Teleop
        fieldList.add(String.valueOf(getTeleopGearsDelivered()));
        fieldList.add(getTeleopLowGoalDumps().toString());
        fieldList.add(String.valueOf(getTeleopHighGoals()));
        fieldList.add(String.valueOf(getTeleopMissedHighGoals()));
        fieldList.add(getClimbingStats().name());

        //Summary
        fieldList.add(getTroubleWith());
        fieldList.add(getComments());

        return (String[]) fieldList.toArray(new String[fieldList.size()]);
    }
}