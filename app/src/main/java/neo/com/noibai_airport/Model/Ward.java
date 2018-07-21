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
 * @created 7/11/2018
 * @updated 7/11/2018
 * @modified by
 * @updated on 7/11/2018
 * @since 1.0
 */
public class Ward implements Serializable {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("ID")
    String sID;
    @SerializedName("DISTRICT_NAME")
    String sDISTRICT_NAME;
    @SerializedName("COMMUNE_NAME")
    String sCOMMUNE_NAME;

    public Ward(String sERROR, String sMESSAGE, String sRESULT, String sID, String sDISTRICT_NAME, String sCOMMUNE_NAME) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sID = sID;
        this.sDISTRICT_NAME = sDISTRICT_NAME;
        this.sCOMMUNE_NAME = sCOMMUNE_NAME;
    }

    public Ward() {
    }
    private static Ward getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Ward.class);
    }

    public static ArrayList<Ward> getList(String jsonArray) throws JSONException {
        ArrayList<Ward> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Ward>>() {
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

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsDISTRICT_NAME() {
        return sDISTRICT_NAME;
    }

    public void setsDISTRICT_NAME(String sDISTRICT_NAME) {
        this.sDISTRICT_NAME = sDISTRICT_NAME;
    }

    public String getsCOMMUNE_NAME() {
        return sCOMMUNE_NAME;
    }

    public void setsCOMMUNE_NAME(String sCOMMUNE_NAME) {
        this.sCOMMUNE_NAME = sCOMMUNE_NAME;
    }
}
