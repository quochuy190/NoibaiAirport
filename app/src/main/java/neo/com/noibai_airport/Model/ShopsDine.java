package neo.com.noibai_airport.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopsDine implements Serializable{
    @SerializedName("SHOPID")
    private String mId;
    @SerializedName("NAME")
    private String mName;
    @SerializedName("AVATAR")
    private String mImage;
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("SHOPTYPE")
    private String sSERVICETYPE;
    @SerializedName("CATEGORYID")
    private String sCATEGORYID;
    @SerializedName("PARTNERID")
    private String sPARTNERID;
    @SerializedName("DESCRIPTION")
    private String sDESCRIPTION;
    @SerializedName("LOCATIONID")
    String sLOCATIONID;
    @SerializedName("PRICE")
    String sPRICE;
    @SerializedName("HOTLINE")
    String sHOTLINE;
    @SerializedName("PROMOTION")
    private String sPROMOTION;
    @SerializedName("BOOKINGLINK")
    private String sBOOKINGLINK;
    @SerializedName("MENUID")
    private String sMENUID;
    @SerializedName("NAME2")
    String sNAME2;
    @SerializedName("TERMINAL")
    String sTERMINAL;
    @SerializedName("AREA")
    String sAREA;
    @SerializedName("FLOOR")
    private String sFLOOR;
    @SerializedName("SQUARE")
    private String sSQUARE;
    @SerializedName("POSITION")
    private String sPOSITION;


    public ShopsDine(String mId, String mName, String mImage, String sERROR, String sMESSAGE,
                     String sRESULT, String sSERVICETYPE, String sCATEGORYID, String sPARTNERID,
                     String sDESCRIPTION, String sLOCATIONID, String sPRICE, String sHOTLINE, String
                             sPROMOTION, String sBOOKINGLINK, String sMENUID, String sNAME2,
                     String sTERMINAL, String sAREA, String sFLOOR, String sSQUARE, String sPOSITION) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sSERVICETYPE = sSERVICETYPE;
        this.sCATEGORYID = sCATEGORYID;
        this.sPARTNERID = sPARTNERID;
        this.sDESCRIPTION = sDESCRIPTION;
        this.sLOCATIONID = sLOCATIONID;
        this.sPRICE = sPRICE;
        this.sHOTLINE = sHOTLINE;
        this.sPROMOTION = sPROMOTION;
        this.sBOOKINGLINK = sBOOKINGLINK;
        this.sMENUID = sMENUID;
        this.sNAME2 = sNAME2;
        this.sTERMINAL = sTERMINAL;
        this.sAREA = sAREA;
        this.sFLOOR = sFLOOR;
        this.sSQUARE = sSQUARE;
        this.sPOSITION = sPOSITION;
    }

    public ShopsDine() {
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

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
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

    public String getsSERVICETYPE() {
        return sSERVICETYPE;
    }

    public void setsSERVICETYPE(String sSERVICETYPE) {
        this.sSERVICETYPE = sSERVICETYPE;
    }

    public String getsCATEGORYID() {
        return sCATEGORYID;
    }

    public void setsCATEGORYID(String sCATEGORYID) {
        this.sCATEGORYID = sCATEGORYID;
    }

    public String getsPARTNERID() {
        return sPARTNERID;
    }

    public void setsPARTNERID(String sPARTNERID) {
        this.sPARTNERID = sPARTNERID;
    }

    public String getsDESCRIPTION() {
        return sDESCRIPTION;
    }

    public void setsDESCRIPTION(String sDESCRIPTION) {
        this.sDESCRIPTION = sDESCRIPTION;
    }

    public String getsLOCATIONID() {
        return sLOCATIONID;
    }

    public void setsLOCATIONID(String sLOCATIONID) {
        this.sLOCATIONID = sLOCATIONID;
    }

    public String getsPRICE() {
        return sPRICE;
    }

    public void setsPRICE(String sPRICE) {
        this.sPRICE = sPRICE;
    }

    public String getsHOTLINE() {
        return sHOTLINE;
    }

    public void setsHOTLINE(String sHOTLINE) {
        this.sHOTLINE = sHOTLINE;
    }

    public String getsPROMOTION() {
        return sPROMOTION;
    }

    public void setsPROMOTION(String sPROMOTION) {
        this.sPROMOTION = sPROMOTION;
    }

    public String getsBOOKINGLINK() {
        return sBOOKINGLINK;
    }

    public void setsBOOKINGLINK(String sBOOKINGLINK) {
        this.sBOOKINGLINK = sBOOKINGLINK;
    }

    public String getsMENUID() {
        return sMENUID;
    }

    public void setsMENUID(String sMENUID) {
        this.sMENUID = sMENUID;
    }

    public String getsNAME2() {
        return sNAME2;
    }

    public void setsNAME2(String sNAME2) {
        this.sNAME2 = sNAME2;
    }

    public String getsTERMINAL() {
        return sTERMINAL;
    }

    public void setsTERMINAL(String sTERMINAL) {
        this.sTERMINAL = sTERMINAL;
    }

    public String getsAREA() {
        return sAREA;
    }

    public void setsAREA(String sAREA) {
        this.sAREA = sAREA;
    }

    public String getsFLOOR() {
        return sFLOOR;
    }

    public void setsFLOOR(String sFLOOR) {
        this.sFLOOR = sFLOOR;
    }

    public String getsSQUARE() {
        return sSQUARE;
    }

    public void setsSQUARE(String sSQUARE) {
        this.sSQUARE = sSQUARE;
    }

    public String getsPOSITION() {
        return sPOSITION;
    }

    public void setsPOSITION(String sPOSITION) {
        this.sPOSITION = sPOSITION;
    }
}
