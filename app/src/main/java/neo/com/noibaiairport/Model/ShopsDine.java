package neo.com.noibaiairport.Model;

import java.io.Serializable;

public class ShopsDine implements Serializable{
    private String mId;
    private String mName;
    private String mImage;

    public ShopsDine(String mId, String mName, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
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
}
