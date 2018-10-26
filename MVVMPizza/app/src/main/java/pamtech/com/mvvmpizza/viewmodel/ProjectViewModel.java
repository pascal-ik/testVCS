package pamtech.com.mvvmpizza.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import pamtech.com.mvvmpizza.service.ResponseRepository;
import pamtech.com.mvvmpizza.service.retrofit.models.JsonResponse;

public class ProjectViewModel extends AndroidViewModel {
    private final LiveData<JsonResponse> projectJsonObservable;

    public ProjectViewModel(@NonNull ResponseRepository responseRepository, @NonNull Application application) {
        super(application);
        projectJsonObservable = responseRepository.getPizzaPlaces();

    }

    public LiveData<JsonResponse> getProjectJsonObservable(){
        return projectJsonObservable;
    }
}
