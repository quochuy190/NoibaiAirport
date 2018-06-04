package neo.com.noibaiairport.Model;

public class Category  {
    private String mId;
    private String mName;
    private String mType;
    private String mImage;

    public Category(String mId, String mName, String mType, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mType = mType;
        this.mImage = mImage;
    }

    public Category() {
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

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
