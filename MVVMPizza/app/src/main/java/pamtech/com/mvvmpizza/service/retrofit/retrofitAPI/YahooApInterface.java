package pamtech.com.mvvmpizza.service.retrofit.retrofitAPI;

import okhttp3.ResponseBody;
import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooApInterface {
    @GET("yql")
    Call<ResponseBody> getNearByPizza(@Query("q") String query,
                                      @Query("format") String format,
                                      @Query("diagnostics") Boolean diagnostics,
                                      @Query("callback") String callback);

    @GET("yqlq=select * from local.search where zip%3D'78759' and query%3D'pizza'&format=json&diagnostics=true&callback=")
    Call<JsonResponse> getDefault();

}
