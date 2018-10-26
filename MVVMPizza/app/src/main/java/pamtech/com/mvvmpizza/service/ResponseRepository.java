package pamtech.com.mvvmpizza.service;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;
import pamtech.com.mvvmpizza.service.retrofit.retrofitAPI.YahooApInterface;
import pamtech.com.mvvmpizza.service.retrofit.retrofitAPI.YahooApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ResponseRepository {
    private static final String TAG = "ResponseRepository";
    Application mApplication;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location mLastKnownLocation;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private String mCurrentLocation;
    private String postalCode;
    private boolean mLocationPermissionGranted;
    private YahooApInterface yahooApInterface;
    Location location;

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting device location");
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        mFusedLocationProviderClient = LocationServices.getF;
        if (ContextCompat.checkSelfPermission(mApplication.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            try {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener((Executor) this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location");
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            Log.d(TAG, "onComplete: last location" + mLastKnownLocation); //this will work only if device location is turned on
                            mCurrentLocation = mLastKnownLocation.getLatitude() + "," + mLastKnownLocation.getLongitude();//else this will throw a null pointer error

                            Log.d(TAG, "onComplete: " + mCurrentLocation);
                            getPostalCode();

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());

                        }
                    }
                });
            } catch (SecurityException e) {
                Log.e("Exception: %s", e.getMessage());
            }
        } else {
            ActivityCompat.requestPermissions((Activity) mApplication.getApplicationContext(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void getPostalCode() {
        try {
            List<Address> addresses;
            Geocoder geocoder = new Geocoder(mApplication.getApplicationContext(), Locale.getDefault());
            addresses = geocoder.getFromLocation(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude(), 1);

            String address = addresses.get(0).getAddressLine(0);
            Log.d(TAG, "getPostalCode: address: " + address);
            String city = addresses.get(0).getAddressLine(1);
            Log.d(TAG, "getPostalCode: city: " + city);
            String country = addresses.get(0).getAddressLine(2);
            Log.d(TAG, "getPostalCode: country: " + country);

            postalCode = addresses.get(0).getPostalCode();
            Log.d(TAG, "getPostalCode: zipcode: " + postalCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildYahooApiClient() {
        Retrofit retrofit = YahooApiClient.getInstance();
        yahooApInterface = retrofit.create(YahooApInterface.class);
    }

    public LiveData<JsonResponse> getPizzaPlaces(){
        final MutableLiveData<JsonResponse> data = new MutableLiveData<>();
        buildYahooApiClient();
        getDeviceLocation();
        String query = "select * from local.search where zip%3D' "+ postalCode +" ' and query%3D'pizza'&format=json&diagnostics=true&callback=";
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
