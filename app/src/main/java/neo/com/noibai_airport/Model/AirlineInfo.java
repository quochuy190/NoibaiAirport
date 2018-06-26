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
public class AirlineInfo implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("AIRLINEID")
    String sAIRLINEID;
    @SerializedName("NAME")
    String sName;
    @SerializedName("AVATAR")
    String sImage;
    @SerializedName("CHECKINLINK")
    String sUrl_Vi;
    String sUrl_En;
    @SerializedName("ADDRESS")
    String sAddress;
    @SerializedName("HOTLINE")
    String sPhone;
    @SerializedName("BOOKINGLINK")
    String sBOOKINGLINK;
    public AirlineInfo(String sName, String sImage, String sAddress, String sPhone, String sUrl_Vi, String sUrl_En) {
        this.sName = sName;
        this.sImage = sImage;
        this.sUrl_Vi = sUrl_Vi;
        this.sUrl_En = sUrl_En;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
    }

    public AirlineInfo() {
    }
    private static AirlineInfo getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), AirlineInfo.class);
    }

    public static ArrayList<AirlineInfo> getList(String jsonArray) throws JSONException {
        ArrayList<AirlineInfo> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<AirlineInfo>>() {
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

    public String getsBOOKINGLINK() {
        return sBOOKINGLINK;
    }

    public void setsBOOKINGLINK(String sBOOKINGLINK) {
        this.sBOOKINGLINK = sBOOKINGLINK;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsImage() {
        return sImage;
    }

    public void setsImage(String sImage) {
        this.sImage = sImage;
    }

    public String getsUrl_Vi() {
        return sUrl_Vi;
    }

    public void setsUrl_Vi(String sUrl_Vi) {
        this.sUrl_Vi = sUrl_Vi;
    }

    public String getsUrl_En() {
        return sUrl_En;
    }

    public void setsUrl_En(String sUrl_En) {
        this.sUrl_En = sUrl_En;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }
}
