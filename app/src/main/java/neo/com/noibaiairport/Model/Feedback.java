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

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${1/6/2018}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class Feedback implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("USERID")
    String sUSERID;
    @SerializedName("NICKNAME")
    String sNICKNAME;
    @SerializedName("COMMENTS")
    String sCOMMENTS;
    @SerializedName("COMMENTTIME")
    String sCOMMENTTIME;
    @SerializedName("FEEDBACK")
    String sFEEDBACK;
    @SerializedName("FEEDBACKTIME")
    String sFEEDBACKTIME;
    @SerializedName("FEEDBACKUSER")
    String sFEEDBACKUSER;
    @SerializedName("EMAIL")
    String sEMAIL;
    @SerializedName("ISPUBLIC")
    String sISPUBLIC;


    public Feedback(String sERROR, String sMESSAGE, String sRESULT, String sUSERID, String sNICKNAME, String sCOMMENTS,
                    String sCOMMENTTIME, String sFEEDBACK, String sFEEDBACKTIME, String sFEEDBACKUSER, String sEMAIL,
                    String sISPUBLIC) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sUSERID = sUSERID;
        this.sNICKNAME = sNICKNAME;
        this.sCOMMENTS = sCOMMENTS;
        this.sCOMMENTTIME = sCOMMENTTIME;
        this.sFEEDBACK = sFEEDBACK;
        this.sFEEDBACKTIME = sFEEDBACKTIME;
        this.sFEEDBACKUSER = sFEEDBACKUSER;
        this.sEMAIL = sEMAIL;
        this.sISPUBLIC = sISPUBLIC;
    }

    public Feedback() {
    }
    private static Feedback getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),Feedback.class);
    }

    public  static ArrayList<Feedback> getList(String jsonArray) throws JSONException {
        ArrayList<Feedback> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Feedback>>(){}.getType();

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

    public String getsUSERID() {
        return sUSERID;
    }

    public void setsUSERID(String sUSERID) {
        this.sUSERID = sUSERID;
    }

    public String getsNICKNAME() {
        return sNICKNAME;
    }

    public void setsNICKNAME(String sNICKNAME) {
        this.sNICKNAME = sNICKNAME;
    }

    public String getsCOMMENTS() {
        return sCOMMENTS;
    }

    public void setsCOMMENTS(String sCOMMENTS) {
        this.sCOMMENTS = sCOMMENTS;
    }

    public String getsCOMMENTTIME() {
        return sCOMMENTTIME;
    }

    public void setsCOMMENTTIME(String sCOMMENTTIME) {
        this.sCOMMENTTIME = sCOMMENTTIME;
    }

    public String getsFEEDBACK() {
        return sFEEDBACK;
    }

    public void setsFEEDBACK(String sFEEDBACK) {
        this.sFEEDBACK = sFEEDBACK;
    }

    public String getsFEEDBACKTIME() {
        return sFEEDBACKTIME;
    }

    public void setsFEEDBACKTIME(String sFEEDBACKTIME) {
        this.sFEEDBACKTIME = sFEEDBACKTIME;
    }

    public String getsFEEDBACKUSER() {
        return sFEEDBACKUSER;
    }

    public void setsFEEDBACKUSER(String sFEEDBACKUSER) {
        this.sFEEDBACKUSER = sFEEDBACKUSER;
    }

    public String getsEMAIL() {
        return sEMAIL;
    }

    public void setsEMAIL(String sEMAIL) {
        this.sEMAIL = sEMAIL;
    }

    public String getsISPUBLIC() {
        return sISPUBLIC;
    }

    public void setsISPUBLIC(String sISPUBLIC) {
        this.sISPUBLIC = sISPUBLIC;
    }
}
