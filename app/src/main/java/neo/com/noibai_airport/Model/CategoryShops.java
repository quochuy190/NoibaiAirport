package neo.com.noibai_airport.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryShops {
    @SerializedName("CATEGORYID")
    private String mId;
    @SerializedName("NAME")
    private String mName;
    @SerializedName("INFO")
    private List<ShopsDine> mLisObjSer;
    @SerializedName("ERROR")
    String sERROR;
    @SerializedName("MESSAGE")
    String sMESSAGE;
    @SerializedName("RESULT")
    String sRESULT;
    public CategoryShops(String mId, String mName, List<ShopsDine> mLisObjSer) {
        this.mId = mId;
        this.mName = mName;
        this.mLisObjSer = mLisObjSer;
    }
    private static CategoryShops getObject (JSONObject jsonObject){
        return new Gson().fromJson(jsonObject.toString(),CategoryShops.class);
    }

    public  static ArrayList<CategoryShops> getList(String jsonArray) throws JSONException {
        ArrayList<CategoryShops> arrayList = new ArrayList<>();
        Type type = new TypeToken<List<CategoryShops>>(){}.getType();

        Gson gson= new Gson();
        arrayList = gson.fromJson(jsonArray,type);

        return arrayList;
    }
    public CategoryShops() {
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

    public List<ShopsDine> getmLisObjSer() {
        return mLisObjSer;
    }

    public void setmLisObjSer(List<ShopsDine> mLisObjSer) {
        this.mLisObjSer = mLisObjSer;
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
}
