
package alex.com.mycolectioncars.api.mvp.presenter;


import java.util.List;

import javax.inject.Inject;

import alex.com.mycolectioncars.api.api.CarsApiService;
import alex.com.mycolectioncars.api.base.BasePresenter;
import alex.com.mycolectioncars.api.mapper.CarMapper;
import alex.com.mycolectioncars.api.mvp.model.Car;
import alex.com.mycolectioncars.api.mvp.model.CarsResponse;
import alex.com.mycolectioncars.api.mvp.model.Storage;
import alex.com.mycolectioncars.api.mvp.view.MainView;
import rx.Observable;
import rx.Observer;

public class CarPresenter extends BasePresenter<MainView> implements Observer<CarsResponse> {

    @Inject
    protected CarsApiService mApiService;
    @Inject
    protected CarMapper mCarMapper;
    @Inject
    protected Storage mStorage;

    @Inject
    public CarPresenter() {
    }

    public void getCars() {
        getView().onShowDialog("Loading cars");
        Observable<CarsResponse> carsResponseObservable = mApiService.getCars();
        subscribe(carsResponseObservable, this);
    }


    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Cars loading complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading cars " + e.getMessage());
    }

    @Override
    public void onNext(CarsResponse carsResponse) {
        List<Car> cars = mCarMapper.mapCars(mStorage, carsResponse);
        getView().onClearItems();
        getView().onCarsLoaded(cars);
    }

    public void getCarsFromDatabase() {
        List<Car> cars = mStorage.getSavedCars();
        getView().onClearItems();
        getView().onCarsLoaded(cars);
    }
}
