package pamtech.com.mvvmpizza.service.retrofit.retrofitAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YahooApiClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {
        if(ourInstance == null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://query.yahooapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }
}
