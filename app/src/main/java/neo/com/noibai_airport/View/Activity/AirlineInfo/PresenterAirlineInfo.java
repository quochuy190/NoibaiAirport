package neo.com.noibai_airport.View.Activity.AirlineInfo;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.AirlineInfo;
import neo.com.noibai_airport.Model.Airport;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created 6/20/2018
 * @updated 6/20/2018
 * @modified by
 * @updated on 6/20/2018
 * @since 1.0
 */
public class PresenterAirlineInfo implements ImlAirlineInfo.Presenter {
    private static final String TAG = "PresenterFlight";
    ApiServiceIml mApiService;
    ImlAirlineInfo.View view;

    public PresenterAirlineInfo(ImlAirlineInfo.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }

    @Override
    public void get_list_airline(String sUserId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getflight");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserId);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_api_error(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<AirlineInfo> mLisFlight = AirlineInfo.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_list_airline(mLisFlight);
                    }else {
                       view.show_list_airline(null);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    view.show_api_error(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_airline_detail(String sUserId, String sIdFlight) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "flightdetail");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserId);
        mMap.put("P2", sIdFlight);
        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_api_error(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Airport> mLisFlight = Airport.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_airline_detail(mLisFlight);
                    }else {
                        view.show_airline_detail(null);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    view.show_api_error(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }

    @Override
    public void get_all_airport(String sUserId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getdestination");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserId);
        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_api_error(null);
                Log.i(TAG, "onGetDataErrorFault: " + e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Airport> mLisFlight = Airport.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_list_airport(mLisFlight);
                    }else {
                        view.show_list_airport(null);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    view.show_api_error(null);
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        }, mMap);
    }


}
