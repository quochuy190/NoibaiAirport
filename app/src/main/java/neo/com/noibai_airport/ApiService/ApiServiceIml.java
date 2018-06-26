package neo.com.noibai_airport.ApiService;

import java.io.IOException;
import java.util.Map;

import neo.com.noibai_airport.Listener.CallbackData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description
 * @authour: $User
 * @createdate $Date
 */
public class ApiServiceIml {

    ApiSevice apiService;
    public void getApiService(final CallbackData<String> callbackData, Map<String, String> mData) {
        apiService = ApiSevice.retrofit.create(ApiSevice.class);
        Call<ResponseBody> getApiservice = apiService.getApiService( mData);
        getApiservice.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String jsonString = null;
                try {
                    if (response.body()!=null){
                        jsonString = response.body().string();
                        jsonString = jsonString.replaceAll("\\\\", "");
                        jsonString = jsonString.substring(11, jsonString.length() - 2);
                        callbackData.onGetDataSuccess(jsonString);
                    }
                } catch (IOException e) {
                    callbackData.onGetDataErrorFault(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callbackData.onGetDataErrorFault(new Exception(t));
            }
        });
    }
}
