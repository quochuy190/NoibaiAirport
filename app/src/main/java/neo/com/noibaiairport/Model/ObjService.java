package neo.com.noibaiairport.Model;

public class ObjService {
    private String mId;
    private String mName;
    private String mImage;

    public ObjService(String mId, String mName, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
    }

    public ObjService() {
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
