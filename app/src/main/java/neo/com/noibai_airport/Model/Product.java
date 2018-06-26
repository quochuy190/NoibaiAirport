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

public class Product implements Serializable {
    @SerializedName("SERVICEID")
    private String mId;
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("PRODUCTNAME")
    private String sPRODUCTNAME;
    @SerializedName("PARTNERID")
    private String sPARTNERID;
    @SerializedName("PRODUCTAVATAR")
    private String sPRODUCTAVATAR;
    @SerializedName("LOCATION")
    String sLOCATION;
    @SerializedName("PRICE")
    String sPRICE;
    @SerializedName("PHONE")
    String sPHONE;
    @SerializedName("SCHEDULE_TIME")
    private String sSCHEDULE_TIME;
    @SerializedName("SUPPLIERS")
    private String sSUPPLIERS;
    @SerializedName("DATA")
    private String sDATA;
    @SerializedName("ADDRESS")
    private String sADDRESS;
    @SerializedName("DESCRIPTION")
    private String sDESCRIPTION;

    public Product(String mId, String sERROR, String sMESSAGE, String sRESULT, String sPRODUCTNAME,
                   String sPARTNERID, String sPRODUCTAVATAR, String sLOCATION, String sPRICE, String sPHONE,
                   String sSCHEDULE_TIME, String sSUPPLIERS, String sDATA, String sADDRESS, String sDESCRIPTION) {
        this.mId = mId;
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sPRODUCTNAME = sPRODUCTNAME;
        this.sPARTNERID = sPARTNERID;
        this.sPRODUCTAVATAR = sPRODUCTAVATAR;
        this.sLOCATION = sLOCATION;
        this.sPRICE = sPRICE;
        this.sPHONE = sPHONE;
        this.sSCHEDULE_TIME = sSCHEDULE_TIME;
        this.sSUPPLIERS = sSUPPLIERS;
        this.sDATA = sDATA;
        this.sADDRESS = sADDRESS;
        this.sDESCRIPTION = sDESCRIPTION;
    }

    public Product() {
    }

    private static Product getObject(JSONObject jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), Product.class);
    }

    public static ArrayList<Product> getList(String jsonArray) throws JSONException {
        ArrayList<Product> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<Product>>() {
        }.getType();

        Gson gson = new Gson();
        arrayList = gson.fromJson(jsonArray, type);

        return arrayList;
    }

    public String getsSUPPLIERS() {
        return sSUPPLIERS;
    }

    public void setsSUPPLIERS(String sSUPPLIERS) {
        this.sSUPPLIERS = sSUPPLIERS;
    }

    public String getsDATA() {
        return sDATA;
    }

    public void setsDATA(String sDATA) {
        this.sDATA = sDATA;
    }

    public String getsADDRESS() {
        return sADDRESS;
    }

    public void setsADDRESS(String sADDRESS) {
        this.sADDRESS = sADDRESS;
    }

    public String getsDESCRIPTION() {
        return sDESCRIPTION;
    }

    public void setsDESCRIPTION(String sDESCRIPTION) {
        this.sDESCRIPTION = sDESCRIPTION;
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

    public String getsPRODUCTNAME() {
        return sPRODUCTNAME;
    }

    public void setsPRODUCTNAME(String sPRODUCTNAME) {
        this.sPRODUCTNAME = sPRODUCTNAME;
    }

    public String getsPARTNERID() {
        return sPARTNERID;
    }

    public void setsPARTNERID(String sPARTNERID) {
        this.sPARTNERID = sPARTNERID;
    }

    public String getsPRODUCTAVATAR() {
        return sPRODUCTAVATAR;
    }

    public void setsPRODUCTAVATAR(String sPRODUCTAVATAR) {
        this.sPRODUCTAVATAR = sPRODUCTAVATAR;
    }

    public String getsLOCATION() {
        return sLOCATION;
    }

    public void setsLOCATION(String sLOCATION) {
        this.sLOCATION = sLOCATION;
    }

    public String getsPRICE() {
        return sPRICE;
    }

    public void setsPRICE(String sPRICE) {
        this.sPRICE = sPRICE;
    }

    public String getsPHONE() {
        return sPHONE;
    }

    public void setsPHONE(String sPHONE) {
        this.sPHONE = sPHONE;
    }

    public String getsSCHEDULE_TIME() {
        return sSCHEDULE_TIME;
    }

    public void setsSCHEDULE_TIME(String sSCHEDULE_TIME) {
        this.sSCHEDULE_TIME = sSCHEDULE_TIME;
    }
}
