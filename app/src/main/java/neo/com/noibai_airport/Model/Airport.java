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

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class Airport implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ROUTEID")
    String sROUTEID;
    @SerializedName("DESTINATIONID")
    String sDESTINATIONID;
    @SerializedName("NAME")
    String sName;
    @SerializedName("AIRLINENAME")
    String sAIRLINENAME;
    @SerializedName("AIRLINEID")
    String sAIRLINEID;
    @SerializedName("DESTINATIONCODE")
    String sDESTINATIONCODE;

    public Airport(String sERROR, String sMESSAGE, String sRESULT, String sROUTEID, String sDESTINATIONID, String sName, String sAIRLINENAME, String sAIRLINEID, String sDESTINATIONCODE) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sROUTEID = sROUTEID;
        this.sDESTINATIONID = sDESTINATIONID;
        this.sName = sName;
        this.sAIRLINENAME = sAIRLINENAME;
        this.sAIRLINEID = sAIRLINEID;
        this.sDESTINATIONCODE = sDESTINATIONCODE;
    }

    public Airport() {
    }

    private static Airport getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Airport.class);
    }

    public static ArrayList<Airport> getList(String jsonArray) throws JSONException {
        ArrayList<Airport> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Airport>>() {
        }.getType();

        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);

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

    public String getsAIRLINEID() {
        return sAIRLINEID;
    }

    public void setsAIRLINEID(String sAIRLINEID) {
        this.sAIRLINEID = sAIRLINEID;
    }

    public String getsROUTEID() {
        return sROUTEID;
    }

    public void setsROUTEID(String sROUTEID) {
        this.sROUTEID = sROUTEID;
    }

    public String getsDESTINATIONID() {
        return sDESTINATIONID;
    }

    public void setsDESTINATIONID(String sDESTINATIONID) {
        this.sDESTINATIONID = sDESTINATIONID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAIRLINENAME() {
        return sAIRLINENAME;
    }

    public void setsAIRLINENAME(String sAIRLINENAME) {
        this.sAIRLINENAME = sAIRLINENAME;
    }

    public String getsDESTINATIONCODE() {
        return sDESTINATIONCODE;
    }

    public void setsDESTINATIONCODE(String sDESTINATIONCODE) {
        this.sDESTINATIONCODE = sDESTINATIONCODE;
    }
}
