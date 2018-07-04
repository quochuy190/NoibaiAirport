package neo.com.noibai_airport.View.Activity.MainActivity;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.BuildConfig;
import neo.com.noibai_airport.Config.Config;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.User;
import neo.com.noibai_airport.untils.SharedPrefs;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description Gọi api, cho mainactivity, xử lý data
 * @desc Developer NEO Company.
 * @created ${29/5/2018}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public class MainPresenter implements MainInterface.Presenter {
    private static final String TAG = "MainPresenter";
    private MainInterface.View mMainView;
    private ApiServiceIml mApiService;

    public MainPresenter(MainInterface.View mMainView) {
        this.mMainView = mMainView;
        mApiService = new ApiServiceIml();
    }

    /**
     * @param sUsertype    : app ; chatbot ;web
     * @param sAppVersion
     * @param sDeviceModel
     * @param sDeviceType
     * @param sVersionHDH  :Device version is running
     * @param sTokenKey    : Token put notifycation
     */
    @Override
    public void getInit(String sUsertype, final String sAppVersion, String sDeviceModel, String sDeviceType,
                        String sVersionHDH, String sTokenKey) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "init");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "6");
        mMap.put("P1", sUsertype);
        mMap.put("P2", sAppVersion);
        mMap.put("P3", sDeviceModel);
        mMap.put("P4", sDeviceType);
        mMap.put("P5", sVersionHDH);
        mMap.put("P6", sTokenKey);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetDataErrorFault: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<User> mLisFlight = User.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        mMainView.show_init(null);
                        Config.URL_IMAGE = mLisFlight.get(0).getsURL_IMAGE();
                        Config.URL_VIDEO = mLisFlight.get(0).getsURL_VIDEO();
                        SharedPrefs.getInstance().put(Constants.KEY_USERID, mLisFlight.get(0).getsUserId());
                        SharedPrefs.getInstance().put(Constants.KEY_VERSION, sAppVersion);
                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }

            }
        },mMap);
    }

    @Override
    public void get_All_Airline(String sUserId) {

    }

    @Override
    public void get_All_Airport(String sUserId) {

    }

    @Override
    public void get_update_info(String sUserId, String sUserType, String sName, String sEmail, String sAppver,
                                String sDevice_model, String sTokenkey, String sDevice_Type, String sOs_Version) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "updateinfo");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "9");
        mMap.put("P1", sUserId);
        mMap.put("P2", sUserType);
        mMap.put("P3", sName);
        mMap.put("P4", sEmail);
        mMap.put("P5", sAppver);
        mMap.put("P6", sDevice_model);
        mMap.put("P7", sTokenkey);
        mMap.put("P8", sDevice_Type);
        mMap.put("P9", sOs_Version);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetDataErrorFault: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<User> mLisFlight = User.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        SharedPrefs.getInstance().put(Constants.KEY_VERSION, BuildConfig.VERSION_NAME);
                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }

            }
        },mMap);

    }


}
