package pamtech.com.mvvmpizza.service.retrofit.retrofitAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YahooApiClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {
        if(ourInstance == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://query.yahooapis.com/v1/public/")
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .build())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }
}
