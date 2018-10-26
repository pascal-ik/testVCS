package pamtech.com.mvvmpizza.service;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;
import pamtech.com.mvvmpizza.service.retrofit.retrofitAPI.YahooApInterface;
import pamtech.com.mvvmpizza.service.retrofit.retrofitAPI.YahooApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ResponseRepository {
    private static final String TAG = "ResponseRepository";
    private YahooApInterface yahooApInterface;


    public ResponseRepository() {
        buildYahooApiClient();
    }

    private void buildYahooApiClient() {
        Retrofit retrofit = YahooApiClient.getInstance();
        yahooApInterface = retrofit.create(YahooApInterface.class);
    }

    public LiveData<JsonResponse> getPizzaPlaces(String query){

        final MutableLiveData<JsonResponse> data = new MutableLiveData<>();

/*        buildYahooApiClient();*/
        yahooApInterface.getNearByPizza(query).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {
                data.setValue(response.body());
                Log.d(TAG, "onResponse: data");
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
               t.printStackTrace();
            }
        });
        return data;
    }


}
