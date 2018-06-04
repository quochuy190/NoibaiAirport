package neo.com.noibaiairport.View.Activity.MainActivity;

import java.util.List;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public interface MainInterface {
    interface Presenter{
        void getInit(String sUsertype, String sAppVersion, String sDeviceModel, String sDeviceType, String sVersionHDH, String sTokenKey);
    }
    interface View{
        void show_init(List<String> mLisErorr);
        void show_api_error();
    }
}
