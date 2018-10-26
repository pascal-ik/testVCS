package pamtech.com.mvvmpizza.service.retrofit.retrofitAPI;

import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooApInterface {
    @GET("v1/public/yql")
    Call<JsonResponse> getNearByPizza(@Query("q") String query);




}
