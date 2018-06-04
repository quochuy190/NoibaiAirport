package neo.com.noibaiairport.View.Fragment.FlightFragment;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibaiairport.ApiService.ApiServiceIml;
import neo.com.noibaiairport.Listener.CallbackData;
import neo.com.noibaiairport.Model.FlightInfo;

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
public class PresenterFlight implements ImlFlight.Presenter {
    private static final String TAG = "PresenterFlight";
    ApiServiceIml mApiService;
    ImlFlight.View view;

    public PresenterFlight(ImlFlight.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }


    @Override
    public void get_list_flight(String sUserId, String sFlightNo, String sLocation, String sDA_type,
                                String sDatetime, String sAirline, String sLoad, String sPage, String sIndex) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getlistflight");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "9");
        mMap.put("P1", sUserId);
        mMap.put("P2", sFlightNo);
        mMap.put("P3", sLocation);
        mMap.put("P4", sDA_type);
        mMap.put("P5", sDatetime);
        mMap.put("P6", sAirline);
        mMap.put("P7", sLoad);
        mMap.put("P8", sPage);
        mMap.put("P9", sIndex);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<FlightInfo> mLisFlight = FlightInfo.getList(objT);
                    if (mLisFlight.size()>0){
                        view.show_list_filghtinfo(mLisFlight);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);


    }

    @Override
    public void get_detail_flight(String sUserId, String sDAtype, String sDatetime, String sFlightNo) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getflightdetail");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "4");
        mMap.put("P1", sUserId);
        mMap.put("P2", sDAtype);
        mMap.put("P3", sDatetime);
        mMap.put("P4", sFlightNo);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<FlightInfo> mLisFlight = FlightInfo.getList(objT);
                    if (mLisFlight.size()>0){
                        view.show_detail_flight(mLisFlight);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void set_follow_flight(String sUserId, String sDAtype, String sDatetime, String sStatus, String sFlightNo) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "setfollowflight");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "5");
        mMap.put("P1", sUserId);
        mMap.put("P2", sDAtype);
        mMap.put("P3", sDatetime);
        mMap.put("P4", sStatus);
        mMap.put("P5", sFlightNo);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataSuccess(String objT) {

            }

            @Override
            public void onGetDataErrorFault(Exception e) {

            }
        }, mMap);
    }
}
