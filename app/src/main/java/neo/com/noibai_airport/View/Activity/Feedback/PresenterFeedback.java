package neo.com.noibai_airport.View.Activity.Feedback;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.ErrorApi;
import neo.com.noibai_airport.Model.Feedback;

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
public class PresenterFeedback implements ImlFeedback.Presenter {
    private static final String TAG = "PresenterFeedback";
    public ApiServiceIml mApiService;
    public ImlFeedback.View view;

    public PresenterFeedback(ImlFeedback.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }

    @Override
    public void get_feedback(String sUserId, String sNickname, String sEmail) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getfeedback");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserId);
        mMap.put("P2", sNickname);
        mMap.put("P3", sEmail);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_error_addfeedback();
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataFault: " + objT);
                try {
                    List<Feedback> mLisFeedback = Feedback.getList(objT);
                    if(mLisFeedback.size()>0)
                        view.show_feedback(mLisFeedback);
                    Log.i(TAG, "onGetObjectDataSuccess: "+mLisFeedback.size());

                } catch (JSONException e) {
                    view.show_error_addfeedback();
                    e.printStackTrace();
                    Log.i(TAG, "onGetObjectDataSuccess: "+e);
                }
            }
        },mMap);
    }

    @Override
    public void add_new_feedback(String sUserId, String sNickname, String sEmail, String sContent, String sPublic) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "sendfeedback");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "5");
        mMap.put("P1", sUserId);
        mMap.put("P2", sNickname);
        mMap.put("P3", sEmail);
        mMap.put("P4", sContent);
        mMap.put("P5", sPublic);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_error_addfeedback();
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ErrorApi> mlisError = ErrorApi.getList(objT);
                    if (mlisError.size()>0&&mlisError.get(0).getsERROR().equals("0000")){
                        view.show_result_addfeedback();
                    }else {
                        view.show_error_addfeedback();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_error_addfeedback();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }
}
