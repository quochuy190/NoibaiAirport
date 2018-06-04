package neo.com.noibaiairport.Model;

public class Language {

    private int mId;
    private String mName;
    private String mCode;
    private boolean isChecked;

    public Language(int id, String name, String code) {
        mId = id;
        mName = name;
        mCode = code;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
