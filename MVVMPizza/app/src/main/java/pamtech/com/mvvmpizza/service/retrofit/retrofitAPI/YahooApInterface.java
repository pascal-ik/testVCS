package pamtech.com.mvvmpizza.service.retrofit.retrofitAPI;

import io.reactivex.Observable;
import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooApInterface {
    @GET("v1/public/yql")
    String query = "select * from local.search where zip%3D'78759' and query%3D'pizza'&format=json&diagnostics=true&callback=";
    Observable<JsonResponse> getNearByPizza(@Query("q") String query);

}
