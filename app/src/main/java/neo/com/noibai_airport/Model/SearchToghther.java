package neo.com.noibai_airport.Model;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/15/2018
 * @updated 7/15/2018
 * @modified by
 * @updated on 7/15/2018
 * @since 1.0
 */
public class SearchToghther {
    private String mDistrict;
    private String mWard;
    private String mTime;
    private String mGender;

    public SearchToghther(String mDistrict, String mWard, String mTime, String mGender) {
        this.mDistrict = mDistrict;
        this.mWard = mWard;
        this.mTime = mTime;
        this.mGender = mGender;
    }

    public SearchToghther() {
    }

    public String getmDistrict() {
        return mDistrict;
    }

    public void setmDistrict(String mDistrict) {
        this.mDistrict = mDistrict;
    }

    public String getmWard() {
        return mWard;
    }

    public void setmWard(String mWard) {
        this.mWard = mWard;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }
}
