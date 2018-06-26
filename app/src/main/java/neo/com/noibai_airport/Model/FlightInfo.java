package neo.com.noibai_airport.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlightInfo extends RealmObject implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @PrimaryKey
    @SerializedName("FLIGHTNUMBER")
    private String mFlight_Number;
    @SerializedName("STANDARDDEPARTURE")
    private String mTime_departure;
    @SerializedName("ESTIMATIONDEPARTURE")
    private String mTime_departure_Estimation;
    @SerializedName("STANDARDARRIVAL")
    private String mTime_arrival;
    @SerializedName("ESTIMATIONARRIVAL")
    private String mTime_arrival_Estimation;
    @SerializedName("FLIGHTDATE")
    private String mDay_start;
    @SerializedName("DURATION_TIME")
    private String mDuration_time;
    @SerializedName("STATUS")
    private String m_Notification;
    @SerializedName("ROUTE_FROM")
    private String mAirportDepartures;
    @SerializedName("ROUTE_TO")
    private String mAirportArrivals;
    @SerializedName("FLIGHTNAME")
    private String mNameAriline;
    @SerializedName("CHECKINCOUNTER")
    private String mCHECKINCOUTER;
    @SerializedName("LOBBY")
    private String mLOBBY;
    @SerializedName("BOARDINGGATE")
    private String mBOARDINGGATE;
    @SerializedName("BELT")
    private String mBELT;
    @SerializedName("TERMINAL")
    private String mTERMINAL;
    @SerializedName("AVATAR")
    private String mAVATAR;
    @SerializedName("NAME")
    private String mNAME;
    private String sFlightType;
    private boolean isFollow;
    private String sHeader;

    public FlightInfo() {
    }


    public FlightInfo(String sERROR, String sMESSAGE, String sRESULT, String mFlight_Number,
                      String mTime_departure, String mTime_departure_Estimation, String mTime_arrival,
                      String mTime_arrival_Estimation, String mDay_start, String mDuration_time,
                      String m_Notification, String mAirportDepartures, String mAirportArrivals,
                      String mNameAriline, String mCHECKINCOUTER, String mBOARDINGGATE, String mBELT,
                      String mTERMINAL, String sFlightType, boolean isFollow, String sHeader, String sLobby,
                      String mAVATAR, String mNAME
    ) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.mFlight_Number = mFlight_Number;
        this.mTime_departure = mTime_departure;
        this.mTime_departure_Estimation = mTime_departure_Estimation;
        this.mTime_arrival = mTime_arrival;
        this.mTime_arrival_Estimation = mTime_arrival_Estimation;
        this.mDay_start = mDay_start;
        this.mDuration_time = mDuration_time;
        this.m_Notification = m_Notification;
        this.mAirportDepartures = mAirportDepartures;
        this.mAirportArrivals = mAirportArrivals;
        this.mNameAriline = mNameAriline;
        this.mCHECKINCOUTER = mCHECKINCOUTER;
        this.mBOARDINGGATE = mBOARDINGGATE;
        this.mBELT = mBELT;
        this.mTERMINAL = mTERMINAL;
        this.sFlightType = sFlightType;
        this.isFollow = isFollow;
        this.sHeader = sHeader;
        this.mLOBBY = sLobby;
        this.mAVATAR = mAVATAR;
        this.mNAME = mNAME;
    }

    private static FlightInfo getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), FlightInfo.class);
    }

    public static ArrayList<FlightInfo> getList(String jsonArray) throws JSONException {
        ArrayList<FlightInfo> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<FlightInfo>>() {
        }.getType();

        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);

        return arrayList;
    }

    public String getmTime_departure() {
        return mTime_departure;
    }

    public void setmTime_departure(String mTime_departure) {
        this.mTime_departure = mTime_departure;
    }

    public String getmLOBBY() {
        return mLOBBY;
    }

    public void setmLOBBY(String mLOBBY) {
        this.mLOBBY = mLOBBY;
    }

    public String getmAVATAR() {
        return mAVATAR;
    }

    public void setmAVATAR(String mAVATAR) {
        this.mAVATAR = mAVATAR;
    }

    public String getmNAME() {
        return mNAME;
    }

    public void setmNAME(String mNAME) {
        this.mNAME = mNAME;
    }

    public String getmTime_departure_Estimation() {
        return mTime_departure_Estimation;
    }

    public void setmTime_departure_Estimation(String mTime_departure_Estimation) {
        this.mTime_departure_Estimation = mTime_departure_Estimation;
    }

    public String getmTime_arrival() {
        return mTime_arrival;
    }

    public void setmTime_arrival(String mTime_arrival) {
        this.mTime_arrival = mTime_arrival;
    }

    public String getmTime_arrival_Estimation() {
        return mTime_arrival_Estimation;
    }

    public void setmTime_arrival_Estimation(String mTime_arrival_Estimation) {
        this.mTime_arrival_Estimation = mTime_arrival_Estimation;
    }

    public String getmDuration_time() {
        return mDuration_time;
    }

    public void setmDuration_time(String mDuration_time) {
        this.mDuration_time = mDuration_time;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getsHeader() {
        return sHeader;
    }

    public void setsHeader(String sHeader) {
        this.sHeader = sHeader;
    }

    public String getsFlightType() {
        return sFlightType;
    }

    public void setsFlightType(String sFlightType) {
        this.sFlightType = sFlightType;
    }

    public String getsERROR() {
        return sERROR;
    }

    public void setsERROR(String sERROR) {
        this.sERROR = sERROR;
    }

    public String getsMESSAGE() {
        return sMESSAGE;
    }

    public void setsMESSAGE(String sMESSAGE) {
        this.sMESSAGE = sMESSAGE;
    }

    public String getsRESULT() {
        return sRESULT;
    }

    public void setsRESULT(String sRESULT) {
        this.sRESULT = sRESULT;
    }

    public String getmFlight_Number() {
        return mFlight_Number;
    }

    public void setmFlight_Number(String mFlight_Number) {
        this.mFlight_Number = mFlight_Number;
    }

    public String getmDay_start() {
        return mDay_start;
    }

    public void setmDay_start(String mDay_start) {
        this.mDay_start = mDay_start;
    }

    public String getM_Notification() {
        return m_Notification;
    }

    public void setM_Notification(String m_Notification) {
        this.m_Notification = m_Notification;
    }

    public String getmAirportDepartures() {
        return mAirportDepartures;
    }

    public void setmAirportDepartures(String mAirportDepartures) {
        this.mAirportDepartures = mAirportDepartures;
    }

    public String getmAirportArrivals() {
        return mAirportArrivals;
    }

    public void setmAirportArrivals(String mAirportArrivals) {
        this.mAirportArrivals = mAirportArrivals;
    }

    public String getmNameAriline() {
        return mNameAriline;
    }

    public void setmNameAriline(String mNameAriline) {
        this.mNameAriline = mNameAriline;
    }

    public String getmCHECKINCOUTER() {
        return mCHECKINCOUTER;
    }

    public void setmCHECKINCOUTER(String mCHECKINCOUTER) {
        this.mCHECKINCOUTER = mCHECKINCOUTER;
    }

    public String getmBOARDINGGATE() {
        return mBOARDINGGATE;
    }

    public void setmBOARDINGGATE(String mBOARDINGGATE) {
        this.mBOARDINGGATE = mBOARDINGGATE;
    }

    public String getmBELT() {
        return mBELT;
    }

    public void setmBELT(String mBELT) {
        this.mBELT = mBELT;
    }

    public String getmTERMINAL() {
        return mTERMINAL;
    }

    public void setmTERMINAL(String mTERMINAL) {
        this.mTERMINAL = mTERMINAL;
    }

}
