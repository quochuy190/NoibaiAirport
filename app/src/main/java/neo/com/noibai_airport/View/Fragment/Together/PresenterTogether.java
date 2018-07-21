package neo.com.noibai_airport.View.Fragment.Together;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
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
public class PresenterTogether implements ImlTogether.Presenter {
    private static final String TAG = "PresenterTogether";
    ApiServiceIml mApiService;
    ImlTogether.View view;

    public PresenterTogether(ImlTogether.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }

    @Override
    public void api_get_list_district(String mUserId, String mCityId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getdistrict");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", mUserId);
        mMap.put("P2", mCityId);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_get_api_error();
                Log.i(TAG, "onGetDataErrorFault: " + e.getMessage());
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<District> mLisFlight = District.getList(objT);
                    if (mLisFlight.size() > 0 && mLisFlight.get(0).getsERROR().equals("0000")) {
                        view.show_get_list_district(mLisFlight);
                    } else {
                        view.show_get_list_district(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_list_wards(String mUserId, String mDictrictId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getward");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", mUserId);
        mMap.put("P2", mDictrictId);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_get_api_error();
                Log.i(TAG, "onGetDataErrorFault: " + e.getMessage());
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Ward> mLisFlight = Ward.getList(objT);
                    if (mLisFlight.size() > 0 && mLisFlight.get(0).getsERROR().equals("0000")) {
                        view.show_get_list_ward(mLisFlight);
                    } else {
                        view.show_get_list_district(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_list_together(String mUserId, String mDictrictId, String mPrecinctID,
                                      String mTime, String mGender, String mIndex, String mPage) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "get_listtogether");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "7");
        mMap.put("P1", mUserId);
        mMap.put("P2", mDictrictId);
        mMap.put("P3", mPrecinctID);
        mMap.put("P4", mTime);
        mMap.put("P5", mGender);
        mMap.put("P6", mIndex);
        mMap.put("P7", mPage);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_get_api_error();
                Log.i(TAG, "onGetDataErrorFault: " + e.getMessage());
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Together> mLisFlight = Together.getList(objT);
                    if (mLisFlight.size() > 0 && mLisFlight.get(0).getsERROR().equals("0000")) {
                        view.show_get_list_together(mLisFlight);
                    } else {
                        view.show_get_list_together(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }

    @Override
    public void api_get_oder_together(String mId, String mUserId, String nName, String mPhone,
                                      String mTime, String mQuantity, String mCity, String mDistrict,
                                      String mPrecinct, String mGender, String mTerminal, String mDescription,
                                      String mPrice, String mAction, String isAvailable) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "order_together");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "15");
        mMap.put("P1", mUserId);
        mMap.put("P2", mId);
        mMap.put("P3", nName);
        mMap.put("P4", mPhone);
        mMap.put("P5", mTime);
        mMap.put("P6", mQuantity);
        mMap.put("P7", mCity);
        mMap.put("P8", mDistrict);
        mMap.put("P9", mPrecinct);
        mMap.put("P10", mGender);
        mMap.put("P11", mTerminal);
        mMap.put("P12", mDescription);
        mMap.put("P13", mPrice);
        mMap.put("P14", mAction);
        mMap.put("P15", isAvailable);
        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_get_api_error();
                Log.i(TAG, "onGetDataErrorFault: " + e.getMessage());
            }

            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ErrorApi> mLisFlight = ErrorApi.getList(objT);
                    view.show_oder_together(mLisFlight.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }
}
