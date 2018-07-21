package neo.com.noibai_airport.View.Fragment.Together;

import java.util.List;

import neo.com.noibai_airport.Model.District;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.Together;
import neo.com.noibai_airport.Model.Ward;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 7/11/2018
 * @updated 7/11/2018
 * @modified by
 * @updated on 7/11/2018
 * @since 1.0
 */
public interface ImlTogether {
    interface Presenter{
        void api_get_list_district(String mUserId, String mCityId);
        void api_get_list_wards(String mUserId, String mDictrictId);
        void api_get_list_together(String mUserId, String mDictrictId, String mPrecinctID , String mTime,
                                   String mGender, String mIndex, String mPage);
        void api_get_oder_together(String mId, String mUserId, String nName, String mPhone , String mTime,
                                   String mQuantity, String mCity, String mDistrict, String mPrecinct, String mGender,
                                   String mTerminal, String mDescription, String mPrice, String mAction, String isAvailable);
    }
    interface View{
        void show_get_api_error();
        void show_get_list_district(List<District> mList);
        void show_get_list_ward(List<Ward> mList);
        void show_get_list_together(List<Together> mList);
        void show_oder_together(ErrorApi errorApi);
    }
}
