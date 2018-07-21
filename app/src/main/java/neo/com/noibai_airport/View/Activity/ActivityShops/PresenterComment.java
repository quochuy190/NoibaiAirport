package neo.com.noibai_airport.View.Activity.ActivityShops;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.Comments;
import neo.com.noibai_airport.Model.ErrorApi;

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
public class PresenterComment implements ImlComment.Presenter {
    private static final String TAG = "PresenterFeedback";
    public ApiServiceIml mApiService;
    public ImlComment.View view;

    public PresenterComment(ImlComment.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }

    @Override
    public void get_comment(String sUserId, String sServiceId, String sSubId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getcmservice");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "3");
        mMap.put("P1", sUserId);
        mMap.put("P2", sServiceId);
        mMap.put("P3", sSubId);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_error_api();
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataFault: " + objT);
                try {
                    List<Comments> listComment = Comments.getList(objT);
                    if(listComment.size()>0&&listComment.get(0).getsERROR().equals("0000"))
                        view.show_list_comment(listComment);
                    else {
                        view.show_error_api();
                    }
                    Log.i(TAG, "onGetObjectDataSuccess: "+listComment.size());

                } catch (JSONException e) {
                    view.show_error_api();
                    e.printStackTrace();
                    Log.i(TAG, "onGetObjectDataSuccess: "+e);
                }
            }
        },mMap);
    }

    @Override
    public void add_comment(String sUserId, String sMemberid, String sServiceid,
                            String sComment, String sStar,String sSubid) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "sendcmservice");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "6");
        mMap.put("P1", sUserId);
        mMap.put("P2", sMemberid);
        mMap.put("P3", sServiceid);
        mMap.put("P4", sComment);
        mMap.put("P5", sStar);
        mMap.put("P6", sSubid);
        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_error_api();
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ErrorApi> mlisError = ErrorApi.getList(objT);
                    if (mlisError.size()>0&&mlisError.get(0).getsERROR().equals("0000")){
                        view.show_result_addComments();
                    }else {
                        view.show_error_addComments();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.show_error_api();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }
}
