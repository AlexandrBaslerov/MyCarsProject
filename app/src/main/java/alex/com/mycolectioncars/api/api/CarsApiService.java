package alex.com.mycolectioncars.api.api;

import alex.com.mycolectioncars.api.mvp.model.CarsResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface CarsApiService {

    @GET("get_cars")
    Observable<CarsResponse> getCars();

}
