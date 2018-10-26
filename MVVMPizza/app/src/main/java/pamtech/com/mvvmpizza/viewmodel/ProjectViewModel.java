package pamtech.com.mvvmpizza.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
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

import pamtech.com.mvvmpizza.service.ResponseRepository;
import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;

public class ProjectViewModel extends AndroidViewModel {
    private static final String TAG = "ProjectViewModel";
    private final LiveData<JsonResponse> projectJsonObservable;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location mLastKnownLocation;
    private String mCurrentLocation;
    private String postalCode;

    private ResponseRepository mResponseRepository;

    private Application mApplication;

    public ProjectViewModel(/*@NonNull ResponseRepository responseRepository, */@NonNull Application application) {
        super(application);
        mApplication = application;
        getDeviceLocation();
        String query = "select * from local.search where zip%3D' "+ postalCode +" ' and query%3D'pizza'&format=json&diagnostics=true&callback=";
        mResponseRepository = new ResponseRepository();
        projectJsonObservable = mResponseRepository.getPizzaPlaces(query);
        Log.d(TAG, "ProjectViewModel: response" + projectJsonObservable);
    }



    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting device location");
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        boolean mLocationPermissionGranted;
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mApplication);
        if (ContextCompat.checkSelfPermission(mApplication.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

            try {
                if(mLocationPermissionGranted){
                    Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                    locationResult.addOnCompleteListener(new OnCompleteListener<Location>() {
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
                }
                else{
                    return;
                }
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



    public LiveData<JsonResponse> getProjectJsonObservable(){
        return projectJsonObservable;
    }
}
