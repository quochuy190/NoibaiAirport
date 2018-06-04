package neo.com.noibaiairport.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FlightInfo implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("FLIGHTNUMBER")
    private String mFlight_Number;
    @SerializedName("STANDARDDEPARTURE")
    private String mTime_star;
    @SerializedName("ESTIMATIONDEPARTURE")
    private String mTime_end;
    @SerializedName("FLIGHTDATE")
    private String mDay_start;
    private String mDay_end;
    @SerializedName("STATUS")
    private String m_Notification;
    @SerializedName("ROUTE_FROM")
    private String mAirportDepartures;
    @SerializedName("ROUTE_TO")
    private String mAirportArrivals;
    @SerializedName("FLIGHTNAME")
    private String mNameAriline;
    @SerializedName("CHECKINCOUTER")
    private String mCHECKINCOUTER;
    @SerializedName("BOARDINGGATE")
    private String mBOARDINGGATE;
    @SerializedName("BELT")
    private String mBELT;
    @SerializedName("TERMINAL")
    private String mTERMINAL;

    public FlightInfo() {
    }

    public FlightInfo(String sERROR, String sMESSAGE, String sRESULT, String mFlight_Number, String mTime_star,
                      String mTime_end, String mDay_start, String mDay_end, String m_Notification, String mAirportDepartures,
                      String mAirportArrivals,String mNameAriline, String mCHECKINCOUTER, String mBOARDINGGATE, String mBELT,
                      String mTERMINAL) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.mFlight_Number = mFlight_Number;
        this.mTime_star = mTime_star;
        this.mTime_end = mTime_end;
        this.mDay_start = mDay_start;
        this.mDay_end = mDay_end;
        this.m_Notification = m_Notification;
        this.mAirportDepartures = mAirportDepartures;
        this.mAirportArrivals = mAirportArrivals;
        this.mNameAriline = mNameAriline;
        this.mCHECKINCOUTER = mCHECKINCOUTER;
        this.mBOARDINGGATE = mBOARDINGGATE;
        this.mBELT = mBELT;
        this.mTERMINAL = mTERMINAL;
    }
    private static FlightInfo getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),FlightInfo.class);
    }

    public  static ArrayList<FlightInfo> getList(String jsonArray) throws JSONException {
        ArrayList<FlightInfo> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<FlightInfo>>(){}.getType();

        Gson gson= new Gson();
        arrayList = gson.fromJson(jsonArray,type);

        return arrayList;
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

    public String getmTime_star() {
        return mTime_star;
    }

    public void setmTime_star(String mTime_star) {
        this.mTime_star = mTime_star;
    }

    public String getmTime_end() {
        return mTime_end;
    }

    public void setmTime_end(String mTime_end) {
        this.mTime_end = mTime_end;
    }

    public String getmDay_start() {
        return mDay_start;
    }

    public void setmDay_start(String mDay_start) {
        this.mDay_start = mDay_start;
    }

    public String getmDay_end() {
        return mDay_end;
    }

    public void setmDay_end(String mDay_end) {
        this.mDay_end = mDay_end;
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
