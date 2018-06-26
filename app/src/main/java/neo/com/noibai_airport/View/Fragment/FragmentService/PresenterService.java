package neo.com.noibai_airport.View.Fragment.FragmentService;

import android.util.Log;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import neo.com.noibai_airport.ApiService.ApiServiceIml;
import neo.com.noibai_airport.Listener.CallbackData;
import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.Product;

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
public class PresenterService implements ImlService.Presenter {
    private static final String TAG = "PresenterService";
    ApiServiceIml mApiService;
    ImlService.View view;

    public PresenterService(ImlService.View view) {
        mApiService = new ApiServiceIml();
        this.view = view;
    }

    @Override
    public void get_service_list(String sUserId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getserviceslist");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserId);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
                view.show_error_api(new String());
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<CategoryService> mCaService = CategoryService.getList(objT);
                    if (mCaService.size()>0&&mCaService.get(0).getsERROR().equals("0000")){
                        view.show_list_service(mCaService);
                    }else {
                        view.show_error_api(mCaService.get(0).getsMESSAGE());
                    }
                } catch (JSONException e) {
                    view.show_error_api(new String());
                    e.printStackTrace();
                   // view.show_error_addfeedback();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }

    @Override
    public void get_service_shops(String sUserId) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getshoplist");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "1");
        mMap.put("P1", sUserId);


        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                view.show_error_api(new String());
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<CategoryShops> mCaService = CategoryShops.getList(objT);
                    if (mCaService.size()>0&&mCaService.get(0).getsERROR().equals("0000")){
                        view.show_list_shop(mCaService);
                    }else {
                        view.show_error_api(mCaService.get(0).getsMESSAGE());
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    view.show_error_api(new String());
                    // view.show_error_addfeedback();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }

    @Override
    public void get_shop_menu(String sUserId, String sIdShop) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getmenu");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserId);
        mMap.put("P2", sIdShop);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
                view.show_error_api(new String());
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<ObjMenu> mCaService = ObjMenu.getList(objT);
                    if (mCaService.size()>0&&mCaService.get(0).getsERROR().equals("0000")){
                        view.show_list_menu_shop(mCaService);
                    }else {
                        view.show_error_api(mCaService.get(0).getsMESSAGE());
                    }
                } catch (JSONException e) {
                    view.show_error_api(new String());
                    e.printStackTrace();
                    // view.show_error_addfeedback();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }

    @Override
    public void get_list_product(String sUserId, String sIdProduct) {
        Map<String, String> mMap = new LinkedHashMap<>();
        mMap.put("Service", "getservicelistdetail");
        mMap.put("Provider", "default");
        mMap.put("ParamSize", "2");
        mMap.put("P1", sUserId);
        mMap.put("P2", sIdProduct);

        mApiService.getApiService(new CallbackData<String>() {
            @Override
            public void onGetDataErrorFault(Exception e) {
                Log.i(TAG, "onGetObjectDataSuccess: "+e);
                view.show_error_api(new String());
            }
            @Override
            public void onGetDataSuccess(String objT) {
                Log.i(TAG, "onGetDataSuccess: " + objT);
                try {
                    List<Product> mCaService = Product.getList(objT);
                    if (mCaService.size()>0&&mCaService.get(0).getsERROR().equals("0000")){
                        view.show_list_product(mCaService);
                    }else {
                        view.show_error_api(mCaService.get(0).getsMESSAGE());
                    }
                } catch (JSONException e) {
                    view.show_error_api(new String());
                    e.printStackTrace();
                    // view.show_error_addfeedback();
                    Log.i(TAG, "Log_error_api_filght: " + e);
                }
            }
        },mMap);
    }
}
