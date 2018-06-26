package neo.com.noibai_airport.Model;

import java.io.Serializable;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/8/2018
 * @updated 6/8/2018
 * @modified by
 * @updated on 6/8/2018
 * @since 1.0
 */
public class ObjWebview implements Serializable{
    private String sId;
    private String sName;
    private int sImage;
    private String sUrl_Vi;
    private String sUrl_En;

    public ObjWebview() {
    }

    public ObjWebview(String sId, String sName, int sImage, String sUrl_Vi, String sUrl_En) {
        this.sId = sId;
        this.sName = sName;
        this.sImage = sImage;
        this.sUrl_Vi = sUrl_Vi;
        this.sUrl_En = sUrl_En;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsImage() {
        return sImage;
    }

    public void setsImage(int sImage) {
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
}
