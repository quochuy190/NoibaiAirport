package neo.com.noibaiairport.Model;

import java.util.List;

public class CategoryService {
    private String mId;
    private String mName;
    private List<ObjService> mLisObjSer;

    public CategoryService(String mId, String mName, List<ObjService> mLisObjSer) {
        this.mId = mId;
        this.mName = mName;
        this.mLisObjSer = mLisObjSer;
    }

    public CategoryService() {
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

    public List<ObjService> getmLisObjSer() {
        return mLisObjSer;
    }

    public void setmLisObjSer(List<ObjService> mLisObjSer) {
        this.mLisObjSer = mLisObjSer;
    }
}
