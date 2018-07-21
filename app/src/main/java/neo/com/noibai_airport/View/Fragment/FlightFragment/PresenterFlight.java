package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.FlightInfo;

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
                                String sDatetime, String sAirline, String sLoad, String sPage, String sIndex, String sTerminal) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getlistflight");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "10");
        mMap.put("P1", sUserId);
        mMap.put("P2", sFlightNo);
        mMap.put("P3", sLocation);
        mMap.put("P4", sDA_type);
        mMap.put("P5", sDatetime);
        mMap.put("P6", sAirline);
        mMap.put("P7", sLoad);
        mMap.put("P8", sPage);
        mMap.put("P9", sIndex);
        mMap.put("P10", sTerminal);

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
                    List<FlightInfo> mLisFlight = FlightInfo.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_list_filghtinfo(mLisFlight);
                    }else {
                        view.show_list_filghtinfo(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }

    @Override
    public void get_list_flight_earlier(String sUserId, String sFlightNo, String sLocation,
                                        String sDA_type, String sDatetime, String sAirline,
                                        String sLoad, String sPage, String sIndex, String sTerminal) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getlistflight");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "10");
        mMap.put("P1", sUserId);
        mMap.put("P2", sFlightNo);
        mMap.put("P3", sLocation);
        mMap.put("P4", sDA_type);
        mMap.put("P5", sDatetime);
        mMap.put("P6", sAirline);
        mMap.put("P7", sLoad);
        mMap.put("P8", sPage);
        mMap.put("P9", sIndex);
        mMap.put("P10",sTerminal);
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
                    List<FlightInfo> mLisFlight = FlightInfo.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_list_filghtinfo_earlier(mLisFlight);
                    }else {
                        view.show_list_filghtinfo_earlier(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
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
                Log.i(TAG, "onGetDataErrorFault: " + e.getMessage());
                view.show_get_api_error();
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<FlightInfo> mLisFlight = FlightInfo.getList(objT);
                    if (mLisFlight.size()>0&&mLisFlight.get(0).getsERROR().equals("0000")){
                        view.show_detail_flight(mLisFlight);
                    }else {
                        view.show_get_api_error();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_get_api_error();
                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }
        }, mMap);
    }

    /**
     *
     * @param sUserId
     * @param sDAtype
     * @param sDatetime
     * @param sStatus
     * @param sFlightNo
     */
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
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ErrorApi> mlisError = ErrorApi.getList(objT);
                    if (mlisError.size()>0&&mlisError.get(0).getsERROR().equals("0000")){
                        view.show_result_flightfollow(mlisError);
                    }else {
                        view.show_result_flightfollow_error(mlisError);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_get_api_error();

                    Log.i(TAG, "Log_error_api_filght: " + e.getMessage());
                }
            }

            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_get_api_error();
                Log.i(TAG, "onGetDataErrorFault: "+e.getMessage());
            }
        }, mMap);
    }
}
