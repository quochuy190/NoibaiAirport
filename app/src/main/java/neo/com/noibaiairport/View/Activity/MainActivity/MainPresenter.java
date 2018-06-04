package neo.com.noibaiairport.View.Activity.MainActivity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibaiairport.ApiService.ApiServiceIml;
import neo.com.noibaiairport.Config.Config;
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.Listener.CallbackData;
import neo.com.noibaiairport.Model.User;
import neo.com.noibaiairport.untils.SharedPrefs;

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
    public void getInit(String sUsertype, String sAppVersion, String sDeviceModel, String sDeviceType,
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
                Log.i(TAG, "onGetObjectDataSuccess: " + objT);
                try {
                    List<User> mLisErorr = new ArrayList<>();
                    JSONObject jsonObj = new JSONObject(objT);
                    String sErorr = jsonObj.getString("ERROR");
                    if (sErorr.equals("0000")){
                        String sMESSAGE = jsonObj.getString("MESSAGE");
                        String sRESULT = jsonObj.getString("RESULT");
                        String URL_API = jsonObj.getString("URL_API");
                        Config.URL_IMAGE = jsonObj.getString("URL_IMAGE");
                        Config.URL_VIDEO = jsonObj.getString("URL_VIDEO");
                        SharedPrefs.getInstance().put(Constants.KEY_USERID, sRESULT);
                    }else {
                        String sRESULT = jsonObj.getString("RESULT");
                        Log.i(TAG, "onGetObjectDataSuccess: "+sRESULT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "onGetObjectDataSuccess: "+e);
                }
            }
        },mMap);
    }


}
