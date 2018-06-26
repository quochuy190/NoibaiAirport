package neo.com.noibai_airport.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/14/2018
 * @updated 6/14/2018
 * @modified by
 * @updated on 6/14/2018
 * @since 1.0
 */
public class ObjMenu {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    @SerializedName("SERVICEID")
    String sSERVICEID;
    @SerializedName("PRODUCTNAME")
    String sPRODUCTNAME;
    @SerializedName("PRODUCTAVARTAR")
    String sPRODUCTAVARTAR;
    @SerializedName("PRICE")
    String sPRICE;
    @SerializedName("UNIT")
    String sUNIT;

    public ObjMenu(String sERROR, String sMESSAGE, String sRESULT, String sSERVICEID,
                   String sPRODUCTNAME, String sPRODUCTAVARTAR, String sPRICE, String sUNIT) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sRESULT = sRESULT;
        this.sSERVICEID = sSERVICEID;
        this.sPRODUCTNAME = sPRODUCTNAME;
        this.sPRODUCTAVARTAR = sPRODUCTAVARTAR;
        this.sPRICE = sPRICE;
        this.sUNIT = sUNIT;
    }

    public ObjMenu() {
    }


    private static ObjMenu getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),ObjMenu.class);
    }
    public  static ArrayList<ObjMenu> getList(String jsonArray) throws JSONException {
        ArrayList<ObjMenu> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<ObjMenu>>(){}.getType();

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

    public String getsSERVICEID() {
        return sSERVICEID;
    }

    public void setsSERVICEID(String sSERVICEID) {
        this.sSERVICEID = sSERVICEID;
    }

    public String getsPRODUCTNAME() {
        return sPRODUCTNAME;
    }

    public void setsPRODUCTNAME(String sPRODUCTNAME) {
        this.sPRODUCTNAME = sPRODUCTNAME;
    }

    public String getsPRODUCTAVARTAR() {
        return sPRODUCTAVARTAR;
    }

    public void setsPRODUCTAVARTAR(String sPRODUCTAVARTAR) {
        this.sPRODUCTAVARTAR = sPRODUCTAVARTAR;
    }

    public String getsPRICE() {
        return sPRICE;
    }

    public void setsPRICE(String sPRICE) {
        this.sPRICE = sPRICE;
    }

    public String getsUNIT() {
        return sUNIT;
    }

    public void setsUNIT(String sUNIT) {
        this.sUNIT = sUNIT;
    }
}
