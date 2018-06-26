package neo.com.noibai_airport.ApiService;


import java.util.Map;

import neo.com.noibai_airport.Config.Config;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by QQ on 7/4/2017.
 */

public interface ApiSevice {
    //Log info action user
    @GET("nia/services/SqlServices/ref?response=application/json")
    Call<ResponseBody> getApiService(@QueryMap Map<String, String> data);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
