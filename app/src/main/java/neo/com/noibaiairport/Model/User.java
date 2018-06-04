package neo.com.noibaiairport.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sUserId;
    @SerializedName("URL_API")
    String sURL_API;
    @SerializedName("URL_IMAGE")
    String sURL_IMAGE;
    @SerializedName("URL_VIDEO")
    String sURL_VIDEO;

    public User() {
    }

    public User(String sERROR, String sMESSAGE, String sRESULT, String sURL_API, String sURL_IMAGE, String sURL_VIDEO) {
        this.sERROR = sERROR;
        this.sMESSAGE = sMESSAGE;
        this.sUserId = sRESULT;
        this.sURL_API = sURL_API;
        this.sURL_IMAGE = sURL_IMAGE;
        this.sURL_VIDEO = sURL_VIDEO;
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

    public String getsUserId() {
        return sUserId;
    }

    public void setsUserId(String sRESULT) {
        this.sUserId = sRESULT;
    }

    public String getsURL_API() {
        return sURL_API;
    }

    public void setsURL_API(String sURL_API) {
        this.sURL_API = sURL_API;
    }

    public String getsURL_IMAGE() {
        return sURL_IMAGE;
    }

    public void setsURL_IMAGE(String sURL_IMAGE) {
        this.sURL_IMAGE = sURL_IMAGE;
    }

    public String getsURL_VIDEO() {
        return sURL_VIDEO;
    }

    public void setsURL_VIDEO(String sURL_VIDEO) {
        this.sURL_VIDEO = sURL_VIDEO;
    }
}

