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

public class Together implements Serializable{
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ID")
    private String mId;
    @SerializedName("NAME")
    private String mName;
    @SerializedName("PHONE")
    private String sPhone;
    @SerializedName("TIME_TOGO")
    String sTime;
    @SerializedName("QUANTITY")
    private String sQuantity;
    @SerializedName("CITY")
    private String sCITY;
    @SerializedName("DISTRICT")
    String sDistrict;
    @SerializedName("PRECINCT")
    String sWard;
    @SerializedName("GENDER")
    private String sGender;
    @SerializedName("TERMINAL")
    private String sTerminal;
    @SerializedName("DESCRIPTION")
    private String sDESCRIPTION;
    @SerializedName("UPDATE_TIME")
    private String sUPDATE_TIME;
    @SerializedName("PRICE_ESTIMATE")
    private String sPRICE_ESTIMATE;
    @SerializedName("DISTRICT_NAME")
    private String sDISTRICT_NAME;
    @SerializedName("PRECINCT_NAME")
    private String sPRECINCT_NAME;
    private String sTitle;
    private boolean isTitle;
    private boolean isMyOrder;
    public Together() {
    }

    public Together(String sTitle, boolean isTitle) {
        this.sTitle = sTitle;
        this.isTitle = isTitle;
    }

    public Together(String sERROR, String sMESSAGE, String sRESULT, String mId, String mName,
                    String sPhone, String sTime, String sQuantity, String sCITY, String sDistrict,
                    String sWard, String sGender, String sTerminal, String sDESCRIPTION, String sUPDATE_TIME,
                    String sPRICE_ESTIMATE, String sDISTRICT_NAME, String sPRECINCT_NAME) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.mId = mId;
        this.mName = mName;
        this.sPhone = sPhone;
        this.sTime = sTime;
        this.sQuantity = sQuantity;
        this.sCITY = sCITY;
        this.sDistrict = sDistrict;
        this.sWard = sWard;
        this.sGender = sGender;
        this.sTerminal = sTerminal;
        this.sDESCRIPTION = sDESCRIPTION;
        this.sUPDATE_TIME = sUPDATE_TIME;
        this.sPRICE_ESTIMATE = sPRICE_ESTIMATE;
        this.sDISTRICT_NAME = sDISTRICT_NAME;
        this.sPRECINCT_NAME = sPRECINCT_NAME;
    }

    private static Together getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Together.class);
    }

    public static ArrayList<Together> getList(String jsonArray) throws JSONException {
        ArrayList<Together> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Together>>() {
        }.getType();

        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);

        return arrayList;
    }

    public boolean isMyOrder() {
        return isMyOrder;
    }

    public void setMyOrder(boolean myOrder) {
        isMyOrder = myOrder;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
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

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsQuantity() {
        return sQuantity;
    }

    public void setsQuantity(String sQuantity) {
        this.sQuantity = sQuantity;
    }

    public String getsGender() {
        return sGender;
    }

    public void setsGender(String sGender) {
        this.sGender = sGender;
    }

    public String getsTerminal() {
        return sTerminal;
    }

    public void setsTerminal(String sTerminal) {
        this.sTerminal = sTerminal;
    }

    public String getsDistrict() {
        return sDistrict;
    }

    public void setsDistrict(String sDistrict) {
        this.sDistrict = sDistrict;
    }

    public String getsWard() {
        return sWard;
    }

    public void setsWard(String sWard) {
        this.sWard = sWard;
    }

    public String getsDESCRIPTION() {
        return sDESCRIPTION;
    }

    public void setsDESCRIPTION(String sDESCRIPTION) {
        this.sDESCRIPTION = sDESCRIPTION;
    }

    public String getsCITY() {
        return sCITY;
    }

    public void setsCITY(String sCITY) {
        this.sCITY = sCITY;
    }

    public String getsUPDATE_TIME() {
        return sUPDATE_TIME;
    }

    public void setsUPDATE_TIME(String sUPDATE_TIME) {
        this.sUPDATE_TIME = sUPDATE_TIME;
    }

    public String getsPRICE_ESTIMATE() {
        return sPRICE_ESTIMATE;
    }

    public void setsPRICE_ESTIMATE(String sPRICE_ESTIMATE) {
        this.sPRICE_ESTIMATE = sPRICE_ESTIMATE;
    }

    public String getsDISTRICT_NAME() {
        return sDISTRICT_NAME;
    }

    public void setsDISTRICT_NAME(String sDISTRICT_NAME) {
        this.sDISTRICT_NAME = sDISTRICT_NAME;
    }

    public String getsPRECINCT_NAME() {
        return sPRECINCT_NAME;
    }

    public void setsPRECINCT_NAME(String sPRECINCT_NAME) {
        this.sPRECINCT_NAME = sPRECINCT_NAME;
    }
}
