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
public class Comments implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("MEMBERID")
    String sMEMBERID;
    @SerializedName("SERVICEID")
    String sSERVICEID;
    @SerializedName("COMMENTS")
    String sCOMMENTS;
    @SerializedName("STARS")
    String sSTARS;
    @SerializedName("COMMENTTIME")
    String sCOMMENTTIME;
    @SerializedName("FEEDBACK")
    String sFEEDBACK;
    @SerializedName("FEEDBACKUSER")
    String sFEEDBACKUSER;
    @SerializedName("FEEDBACKTIME")
    String sFEEDBACKTIME;

    public Comments(String sERROR, String sMESSAGE, String sRESULT, String sMEMBERID, String sSERVICEID, String sCOMMENTS, String sSTARS, String sCOMMENTTIME, String sFEEDBACK, String sFEEDBACKUSER, String sFEEDBACKTIME) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sMEMBERID = sMEMBERID;
        this.sSERVICEID = sSERVICEID;
        this.sCOMMENTS = sCOMMENTS;
        this.sSTARS = sSTARS;
        this.sCOMMENTTIME = sCOMMENTTIME;
        this.sFEEDBACK = sFEEDBACK;
        this.sFEEDBACKUSER = sFEEDBACKUSER;
        this.sFEEDBACKTIME = sFEEDBACKTIME;
    }

    public Comments() {
    }

    private static Comments getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Comments.class);
    }

    public static ArrayList<Comments> getList(String jsonArray) throws JSONException {
        ArrayList<Comments> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Comments>>() {
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

    public String getsMEMBERID() {
        return sMEMBERID;
    }

    public void setsMEMBERID(String sMEMBERID) {
        this.sMEMBERID = sMEMBERID;
    }

    public String getsSERVICEID() {
        return sSERVICEID;
    }

    public void setsSERVICEID(String sSERVICEID) {
        this.sSERVICEID = sSERVICEID;
    }

    public String getsCOMMENTS() {
        return sCOMMENTS;
    }

    public void setsCOMMENTS(String sCOMMENTS) {
        this.sCOMMENTS = sCOMMENTS;
    }

    public String getsSTARS() {
        return sSTARS;
    }

    public void setsSTARS(String sSTARS) {
        this.sSTARS = sSTARS;
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

    public String getsFEEDBACKUSER() {
        return sFEEDBACKUSER;
    }

    public void setsFEEDBACKUSER(String sFEEDBACKUSER) {
        this.sFEEDBACKUSER = sFEEDBACKUSER;
    }

    public String getsFEEDBACKTIME() {
        return sFEEDBACKTIME;
    }

    public void setsFEEDBACKTIME(String sFEEDBACKTIME) {
        this.sFEEDBACKTIME = sFEEDBACKTIME;
    }
}
